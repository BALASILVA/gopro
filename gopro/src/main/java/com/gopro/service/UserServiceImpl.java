package com.gopro.service;


import com.gopro.AuthendicationFacade.AuthendicationFacade;

import com.gopro.bene.Authority;
import com.gopro.bene.Role;
import com.gopro.bene.SearchCredentialDTO;
import com.gopro.bene.Shop;
import com.gopro.bene.User;
import com.gopro.bene.UserPrincipal;
import com.gopro.constant.ExceptionMessage;
import com.gopro.constant.NotificationConstant;
import com.gopro.constant.UserImplConstant;
import com.gopro.exception.domain.EmailExistException;
import com.gopro.exception.domain.UserNotFoundException;
import com.gopro.exception.domain.UsernameExistException;
import com.gopro.repository.UserRepository;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static com.gopro.enumeration.Role.ROLE_SUPER_ADMIN;
import static com.gopro.constant.SearchGridConstant.DEFAULT_USER_MODULE_PAGE_SIZE;

import javax.mail.SendFailedException;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.gopro.constant.UserImplConstant.*;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	private UserRepository userRepository;
	private BCryptPasswordEncoder passwordEncoder;
	private RoleService roleService;
	private ShopService shopService;
	private AuthorityService authorityService;
	private AuthendicationFacade authendicationFacade;
	private MenuService menuService;
	private EmailSenderService emailSenderService;
	private OTPService otpService;

	@Autowired
	public UserServiceImpl(AuthorityService authorityService, UserRepository userRepository,
			BCryptPasswordEncoder passwordEncoder, RoleService roleService, ShopService shopService,
			MenuService menuService,
			EmailSenderService emailSenderService,
			OTPService  otpService, 
			@Lazy AuthendicationFacade authendicationFacade) {
		this.authorityService = authorityService;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleService = roleService;
		this.shopService = shopService;
		this.authendicationFacade = authendicationFacade;
		this.emailSenderService = emailSenderService;
		this.otpService = otpService;
		this.menuService =  menuService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByUsername(username);
		if (user == null) {
			LOGGER.error(NO_USER_FOUND_BY_USERNAME + username);
			throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
		} else {
			int userRoleId = user.getRoleObject().getRoleId();
			List<Authority> userAuthorityList = authorityService.findAuthorityByRoleId(userRoleId);
			String[] userAuthorityArr = new String[userAuthorityList.size()];
			for (int i = 0; i < userAuthorityList.size(); i++) {
				userAuthorityArr[i] = userAuthorityList.get(i).getAuthority();
			}
			user.setAuthorities(userAuthorityArr);

			user.setLastLoginDateDisplay(user.getLastLoginDate());
			user.setLastLoginDate(new Date());
			userRepository.save(user);
			UserPrincipal userPrincipal = new UserPrincipal(user);
			LOGGER.info(FOUND_USER_BY_USERNAME + username);
			return userPrincipal;
		}
	}

	@Override
	public User register(String userName, Shop shop, String email,String phone, String password, String phoneNumber,Long otp)
			throws UserNotFoundException, UsernameExistException, EmailExistException {
		
		LOGGER.info("Registering User For User Name :: "+userName);
		validateOTP(userName,email,otp);
		validateNewUsernameAndEmail(EMPTY, email);
		shop.setPhone(phoneNumber);
		shop.setEmail(email);
		Shop shopFromDb = shopService.saveWithOurUserId(shop);
		
		User user = new User();		
		List<Shop> shopList = new ArrayList<Shop>();
		shopList.add(shopFromDb);
		user.setDefaultShopId(shopFromDb.getShopId());
		user.setShopList(shopList);
		
		Role role = roleService.findRoleByRoleId(SUPER_ADMIN_ROLE_ID);
		user.setRoleObject(role);
		user.setParentUserId(PARENT_USER_ID_SUPER_ADMIN);
		
		String encodedPassword = encodePassword(password);
		user.setFirstName(userName);
		user.setEmail(email);
		// Here we Authendicate by email. In spring security manager have
		// authendicatebyuserName class only
		// For this purpose we store email in userName column.
		// So we no need to write logic for Authendication by email
		user.setUsername(email);
		user.setJoinDate(new Date());
		user.setPassword(encodedPassword);
		user.setActive(true);
		user.setNotLocked(true);
		user.setRole(ROLE_SUPER_ADMIN.name());
		// user.setAuthorities(ROLE_SUPER_ADMIN.getAuthorities());
		//user.setProfileImageUrl(getTemporaryProfileImageUrl());
		user = userRepository.save(user);
		shop.setUserId(user.getId());
		boolean isUpdated = shopService.updateUserId(shop.getShopId(),user.getId());
		LOGGER.info("User Created For User Name :: "+userName);
		return user;
	}


	@Override
	public User addNewUser( String firstName,String lastName, String email, String phoneNumber, String addressLine1,
			List<Shop> shopList, Role role, String remarks)
			throws UserNotFoundException, UsernameExistException, EmailExistException,Exception {
		
		LOGGER.info("Createing new Sub User With name:: "+firstName + " email :: "+email);
		
		validateNewUsernameAndEmail(EMPTY, email);
		
		User logedInUser = authendicationFacade.getCurrentUserDetails();
		
		User user = new User();
		List<Shop> shopListFromInputId = shopService.findAllShopById(shopList);

		if (shopListFromInputId.size() > 0) {
			user.setDefaultShopId(shopListFromInputId.get(0).getShopId());
		}
        
		user.setRoleObject(role);
		user.setShopList(shopListFromInputId);
		String pass = generatePassword();
		String encodedPassword = encodePassword(pass);
		System.out.println("USerPasswoord" + pass);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhoneNumber(phoneNumber);
		user.setRemarks(remarks);
		// Here we Authendicate by email. In spring security manager have
		// authendicatebyuserName class only
		// For this purpose we store email in userName column.
		// So we no need to write logic for Authendication by email
		user.setUsername(email);
		user.setAddressLine1(addressLine1);
		user.setJoinDate(new Date());
		user.setPassword(encodedPassword);
		user.setActive(true);
		user.setNotLocked(true);
		Role roleByRoleId = roleService.getRoleById(role.getRoleId());	
		user.setRole(roleByRoleId.getRoleName());
		
		if(logedInUser.getParentUserId()==0)
		{
			//log in user is super admin
			user.setParentUserId(logedInUser.getId());
		}
		else
		{
			//log in user is non super admin
			user.setParentUserId(logedInUser.getParentUserId());
		}
		
		//user.setProfileImageUrl(getTemporaryProfileImageUrl());
		User userFromDB = userRepository.save(user);
		LOGGER.info("Inserted in DB, Createing new Sub User With name:: "+firstName + " email :: "+email);
		boolean isMailSend = false;
		if(userFromDB != null) {
			LOGGER.info("Try to send User Name and Password For new Sub User with email :: "+user.getEmail());
			isMailSend = emailSenderService.sendOTP(user.getEmail(), "Your user Name"+user.getEmail() +"  Your password: "+pass,  "Accound Creted");
			LOGGER.info("Sucessfully send User Name and Password For new Sub User with email :: "+user.getEmail());
		}
		
		if(!isMailSend) {
			LOGGER.error("Failed to send User Name and Password For new Sub User with email :: "+user.getEmail());
			deleteUser(userFromDB);
			throw new SendFailedException(ExceptionMessage.SEND_MAIL_EXCEPTION);			
		}
		
		return user;
	}

	@Override
	public boolean deleteUser(User userFromDB) {
		try {
			LOGGER.info("Deleting User With User with email :: "+userFromDB.getEmail());
			userRepository.deleteUserShopMaping(userFromDB.getId());
			userRepository.deleteByIdCustom(userFromDB.getId());
			LOGGER.info("Sucessfully Deleted User With User with email :: "+userFromDB.getEmail());
		}
		catch(Exception ex)
		{
			LOGGER.info("Exception Occur While deleting user Email :: "+userFromDB.getEmail()+ " || Ex-Message :: "+ex.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean changePassword(Long userId, String newPassword) {
		LOGGER.info("Change Password for User Id :: "+userId);		
		User userFromDb = authendicationFacade.getCurrentUserDetails();
		String encPassword = encodePassword(newPassword);		
		int updateCount = userRepository.updatePassword(userId,encPassword);
		LOGGER.info("Sucessfully Updated Password for User Id :: "+userId);
		return updateCount>0;
	}
	
	@Override
	public Page<User> getAllUserPaginationAndSorting(SearchCredentialDTO searchCredentialDTO) throws Exception {
		User userFromDb = authendicationFacade.getCurrentUserDetails();
		
		// Updating User details from data base for security
		searchCredentialDTO.setId(userFromDb.getId());
		searchCredentialDTO.setParentUserId(userFromDb.getParentUserId());

		if (userFromDb.getParentUserId() == 0 && userFromDb.getRoleObject().getRoleId() == UserImplConstant.SUPER_ADMIN_ROLE_ID) {
			// For Super Admin Only
			searchCredentialDTO.setParentUserId(userFromDb.getId());
		}
		else if(userFromDb.getParentUserId() == 0 && (userFromDb.getRoleObject().getRoleId() == UserImplConstant.MANAGER_ROLE_ID
				|| userFromDb.getRoleObject().getRoleId() == UserImplConstant.SUPERVISOR_ROLE_ID))
		{
			// For Manager Or Admin
		}

		Sort sort = null;
		if (StringUtils.isEmpty(searchCredentialDTO.getShortBy())) {
			searchCredentialDTO.setShortBy("id");
		}

		sort = Sort.by(searchCredentialDTO.getShortBy());
		if (StringUtils.isNotEmpty(searchCredentialDTO.getShortOrderAscOrDsc())) {
			if (searchCredentialDTO.getShortOrderAscOrDsc().equalsIgnoreCase("DESC")) {
				sort = sort.descending();
			} else if (searchCredentialDTO.getShortOrderAscOrDsc().equalsIgnoreCase("ASC")) {
				sort = sort.ascending();
			} else {
				sort = sort.ascending();
			}
		}

		Pageable pageable = null;
		if(searchCredentialDTO.getSize() == 0)
		{			
			searchCredentialDTO.setSize(DEFAULT_USER_MODULE_PAGE_SIZE);
		}
		
		if (sort != null) {
			pageable = PageRequest.of(searchCredentialDTO.getPage(), searchCredentialDTO.getSize(), sort);
		} else {
			pageable = PageRequest.of(searchCredentialDTO.getPage(), searchCredentialDTO.getSize());
		}
		if (StringUtils.isEmpty(searchCredentialDTO.getSearchKeyWord())) {
			searchCredentialDTO.setSearchKeyWord("");
		}
		searchCredentialDTO.setSearchKeyWord("%" + searchCredentialDTO.getSearchKeyWord() + "%");
		
		try {
			Page<User> list = userRepository.getAllUserPaginationAndSorting(searchCredentialDTO.getId(),
					searchCredentialDTO.getParentUserId(),userFromDb.getRoleObject().getRoleId(), searchCredentialDTO.getFirstName(),
					searchCredentialDTO.getEmail(), searchCredentialDTO.getPhoneNumber(),
					searchCredentialDTO.getRoleId() == 0 ? null : (long) searchCredentialDTO.getRoleId(),
					searchCredentialDTO.getSearchKeyWord(), pageable);
			return list;
		} catch (Exception ex) {
			LOGGER.error("Exception Occred "+ex.getMessage());
			throw new Exception();
		}
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public User findUserByUsername(String username) {
		User user = userRepository.findUserByUsername(username);					
		user.getRoleObject().setMenuList(menuService.findMenuByUserId(user.getId()));
		return user;
	}
	
	@Override
	public User findUserByUsernameForUserId(String username) {
		User user = userRepository.findUserByUsername(username);							
		return user;
	}

	@Override
	public User findUserById(Long id) throws UserNotFoundException{
		 Optional<User> user = userRepository.findById(id);
		 return user.orElse(null);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	private String getTemporaryProfileImageUrl() {
		return ServletUriComponentsBuilder.fromCurrentContextPath().path(DEFAULT_USER_IMAGE_PATH).toUriString();
	}

	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

	private String generatePassword() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

	private String generateUserId() {
		return RandomStringUtils.randomNumeric(10);
	}

	private User validateNewUsernameAndEmail(String currentUsername, String newEmail)
			throws UserNotFoundException, UsernameExistException, EmailExistException {
		User userByNewEmail = findUserByEmail(newEmail);
		if (StringUtils.isNotBlank(currentUsername)) {
			User currentUser = findUserByUsername(currentUsername);
			if (currentUser == null) {
				LOGGER.error(NO_USER_FOUND_BY_USERNAME + currentUsername);
				throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
			}

			if (userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())) {
				LOGGER.error(EMAIL_ALREADY_EXISTS + newEmail);
				throw new EmailExistException(EMAIL_ALREADY_EXISTS);
			}
			return currentUser;
		} else {
			if (userByNewEmail != null) {
				LOGGER.error(EMAIL_ALREADY_EXISTS + newEmail);
				throw new EmailExistException(EMAIL_ALREADY_EXISTS);
			}
			return null;
		}
	}

	@Override
	public User updateUser(Long id,String firstName, String phoneNumber, String addressLine1, List<Shop> shopList,
			Role roleObject, String remarks) throws Exception {
		
		
		User userFromDataBase = findUserById(id);
		
		if(userFromDataBase == null)
		{
			LOGGER.error(NO_USER_FOUND_BY_ID + id);
			throw new UsernameNotFoundException(NO_USER_FOUND_BY_ID + id);
		}
		try {
		List<Shop> selectedShop = new ArrayList<Shop>();
		//All Parent Shopes
		List<Shop> allShopOfParentUser = shopService.getAllShopByParentUserId(userFromDataBase.getParentUserId());
		//Compare edited shop List and parent shop list ( Validation )
	    selectedShop = allShopOfParentUser.stream()
	    .filter(parentObj -> shopList.stream()
	        .anyMatch(selectObj -> selectObj.getShopId().equals(parentObj.getShopId()) 
	           ))
	    .collect(Collectors.toList());
		
		Role roleFromDataBase = roleService.getRoleById(roleObject.getRoleId());		
		
		//Updating edited data
		userFromDataBase.setFirstName(firstName);
		userFromDataBase.setPhoneNumber(phoneNumber);
		userFromDataBase.setAddressLine1(addressLine1);
		userFromDataBase.setShopList(selectedShop);
		userFromDataBase.setRoleObject(roleFromDataBase);
		userFromDataBase.setRole(roleFromDataBase.getRoleName());
		userFromDataBase.setRemarks(remarks);
		

		//Updating User
		return userRepository.save(userFromDataBase);
		}
		catch (Exception e) {
			LOGGER.error("Exception Occured "+e.getMessage());
			throw new Exception();
		}
	}

	@Override
	public boolean updateDefaultShop(Shop shop) {
		User logInUser = authendicationFacade.getCurrentUserDetails();		
		try {	
			LOGGER.info("Try to update default shop for user :: "+logInUser.getId()+" || Shop Id :: "+shop.getShopId());
			Shop selectShop = null;			
			List<Shop> shops = shopService.getAllShopByParentUserId(logInUser.getId());
			
			for(Shop shopItr : shops) {
				if(shopItr.getShopId().equals(shop.getShopId()))
				{
					selectShop = shopItr;
					break;
				}
			}
			
			if (selectShop != null) {				
				userRepository.updateDefaultShopId(selectShop.getShopId(), logInUser.getId());
				LOGGER.info("Sucessfully update default shop for user :: "+logInUser.getId()+" || Shop Id :: "+selectShop.getShopId());
				return true;
			}
			else { 
				LOGGER.error("Shop Id "+shop.getShopId()+" Not Matches Found For User "+logInUser.getId());
				throw new BadCredentialsException("Tecnical Error Occured");
			}
		} catch (Exception ex) {
			LOGGER.error("Exception Occured Ex-Message"+ ex.getMessage());
			throw new BadCredentialsException("Tecnical Error Occured");			
		}
	}

	@Override
	public List<User> getUserForReprintNotification(User logedInUser) {
		//int roleId[] = NotificationConstant.INVOICE_REPRINT_NOTIFICTION_TO_SEND_ROLE_ARR;
		List<Integer> roles = NotificationConstant.INVOICE_REPRINT_NOTIFICTION_TO_SEND_ROLE_LIST;
		List<User> user = userRepository.getUserByRole(logedInUser.getParentUserId(), roles);		
		return user;
	}

	@Override
	public List<User> getAdminANDManagerUser(Long parentUserID) {
		List<Integer> roles = NotificationConstant.ADMIN_MANAGER_ROLEID;
		List<User> user = userRepository.getUserByRole(parentUserID, roles);		
		return user;
	}

	
	private boolean validateOTP(String userName, String email, Long otp) {
		if(otpService.isValidOTP(email,otp))
		{
			return true;
		}
		else {
			 throw new BadCredentialsException("Invalid OTP");
		}
	}
	
	@Override
	public List<User> getUserForSendMail() {

		User logInUser = authendicationFacade.getCurrentUserDetails();
		
		Long parentUserId  = 0L;
		
		if(logInUser.getParentUserId() == PARENT_USER_ID_SUPER_ADMIN)
			parentUserId  = logInUser.getId();
		else
			parentUserId = logInUser.getParentUserId();
		
		if(parentUserId  == 0L) {
			//Exception TO be hadled
			return null;
		}
		return userRepository.getUserForSendMail(parentUserId);
		
	}

	@Override
	public boolean haveNewMail() {
		String logInUserName = authendicationFacade.getCurrentUserName();
		return userRepository.haveNewMail(logInUserName);
	}
	

	@Override	
	public boolean updateNewMailTrue(List<Long> userId) {	
		 userRepository.updateNewMailTrue(true,userId);
		return true;
		
	}

	@Override
	public boolean updateNewMailFalse(Long userId) {
		 userRepository.updateNewMailFalse(false,userId);
		 return true;
	}

	@Override
	public boolean updateProfileImageUrl(Long id, String imageName) {
		int count = userRepository.updateProfileImageUrl(id,imageName);
		return count>0;
	}

	@Override
	public boolean isMailAvailable(String email) {
		if(StringUtils.isEmpty(email))		
			return true;
		
		User userByNewEmail = findUserByEmail(email);		
		if(userByNewEmail == null)
			return true;
		return false;
	}



	
}
