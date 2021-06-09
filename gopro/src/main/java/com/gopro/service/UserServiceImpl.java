package com.gopro.service;

import com.gopro.AuthendicationFacade.AuthendicationFacade;
import com.gopro.bene.Authority;
import com.gopro.bene.Product;
import com.gopro.bene.Role;
import com.gopro.bene.SearchCredentialDTO;
import com.gopro.bene.Shop;
import com.gopro.bene.User;
import com.gopro.bene.UserPrincipal;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static com.gopro.enumeration.Role.ROLE_SUPER_ADMIN;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
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

	@Autowired
	public UserServiceImpl(AuthorityService authorityService, UserRepository userRepository,
			BCryptPasswordEncoder passwordEncoder, RoleService roleService, ShopService shopService,
			@Lazy AuthendicationFacade authendicationFacade) {
		this.authorityService = authorityService;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleService = roleService;
		this.shopService = shopService;
		this.authendicationFacade = authendicationFacade;
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
	public User register(String userName, String shopName, String email, String password)
			throws UserNotFoundException, UsernameExistException, EmailExistException {
		validateNewUsernameAndEmail(EMPTY, email);
		User user = new User();
		Shop shop = new Shop();
		shop.setShopName(shopName);

		Shop shopFromDb = shopService.save(shop);

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
		user.setProfileImageUrl(getTemporaryProfileImageUrl());
		userRepository.save(user);
		return user;
	}

	@Override
	public User addNewUser(Long parentUserId, String firstName, String email, String phoneNumber, String addressLine1,
			List<Shop> shopList, Role role, String remarks)
			throws UserNotFoundException, UsernameExistException, EmailExistException {
		validateNewUsernameAndEmail(EMPTY, email);
		User user = new User();
		List<Shop> shopListFromInputId = shopService.findAllShopById(shopList);

		if (shopListFromInputId.size() > 0) {
			user.setDefaultShopId(shopListFromInputId.get(0).getShopId());
		}

		user.setRoleObject(role);
		user.setShopList(shopListFromInputId);
		user.setParentUserId(parentUserId);
		String pass = generatePassword();
		String encodedPassword = encodePassword(pass);
		System.out.println("USerPasswoord" + pass);
		user.setFirstName(firstName);
		user.setEmail(email);
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
		System.out.println(roleByRoleId);
		user.setRole(roleByRoleId.getRoleName());
		user.setProfileImageUrl(getTemporaryProfileImageUrl());
		userRepository.save(user);
		return user;
	}

	@Override
	public Page<User> getAllUserPaginationAndSorting(SearchCredentialDTO searchCredentialDTO) {
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
					searchCredentialDTO.getParentUserId(), searchCredentialDTO.getFirstName(),
					searchCredentialDTO.getEmail(), searchCredentialDTO.getPhoneNumber(),
					searchCredentialDTO.getRoleId() == 0 ? null : (long) searchCredentialDTO.getRoleId(),
					searchCredentialDTO.getSearchKeyWord(), pageable);
			System.out.println(searchCredentialDTO.getId()+""+
					searchCredentialDTO.getParentUserId());
			System.out.println(list.getSize());
			System.out.println(list.getContent().toString());
			return list;
		} catch (Exception ex) {
			System.out.println("Exception" + ex);
			return null;
		}
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public User findUserByUsername(String username) {
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
				throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
			}

			if (userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())) {
				throw new EmailExistException(EMAIL_ALREADY_EXISTS);
			}
			return currentUser;
		} else {
			if (userByNewEmail != null) {
				throw new EmailExistException(EMAIL_ALREADY_EXISTS);
			}
			return null;
		}
	}

	@Override
	public User updateUser(Long id,String firstName, String phoneNumber, String addressLine1, List<Shop> shopList,
			Role roleObject, String remarks) throws UserNotFoundException {
		System.out.println(remarks);
		User userFromDataBase = findUserById(id);
		
		if(userFromDataBase == null)
		{
			throw new UsernameNotFoundException(NO_USER_FOUND_BY_ID + id);
		}
		

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


}
