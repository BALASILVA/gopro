package com.gopro.service;

import com.gopro.bene.Authority;
import com.gopro.bene.Role;
import com.gopro.bene.Shop;
import com.gopro.bene.User;
import com.gopro.bene.UserPrincipal;
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

	@Autowired
	public UserServiceImpl(AuthorityService authorityService,UserRepository userRepository, BCryptPasswordEncoder passwordEncoder,
			RoleService roleService, ShopService shopService) {
		this.authorityService = authorityService;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleService = roleService;
		this.shopService = shopService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByUsername(username);
		if (user == null) {
			LOGGER.error(NO_USER_FOUND_BY_USERNAME + username);
			throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
		} else {
			int userRoleId = user.getRoleObject().getRoleId();
			List<Authority>  userAuthorityList = authorityService.findAuthorityByRoleId(userRoleId);
			String[] userAuthorityArr = new String[userAuthorityList.size()];
			for (int i = 0; i < userAuthorityList.size(); i++) {
				userAuthorityArr[i]=userAuthorityList.get(i).getAuthority();
			}
			user.setAuthorities(userAuthorityArr);
			
			user.setLastLoginDateDisplay(user.getLastLoginDate());
			user.setLastLoginDate(new Date());
			userRepository.save(user);
			System.out.println(user);
			UserPrincipal userPrincipal = new UserPrincipal(user);
			LOGGER.info(FOUND_USER_BY_USERNAME + username);
			return userPrincipal;
		}
	}

	@Override
	public User register(String userName,String shopName, String email, String password)
			throws UserNotFoundException, UsernameExistException, EmailExistException {
		validateNewUsernameAndEmail(EMPTY, email);
		User user = new User();
		Shop shop = new Shop();
		shop.setShopName(shopName);
		
		List<Shop> shopList = new ArrayList<Shop>();
		shopList.add(shop);
		user.setShopList(shopList);
		Role role = roleService.findRoleByRoleId(SUPER_ADMIN_ROLE_ID);
		user.setRoleObject(role);
		user.setParentUserId(PARENT_USER_ID_SUPER_ADMIN);
		String encodedPassword = encodePassword(password);
		user.setFirstName(userName);
		user.setEmail(email);
		//Here we Authendicate by email. In spring security manager have authendicatebyuserName class only
		//For this purpose we store email in userName column.  
		//So we no need to write logic for Authendication by email
		user.setUsername(email);
		user.setJoinDate(new Date());
		user.setPassword(encodedPassword);
		user.setActive(true);
		user.setNotLocked(true);
		user.setRole(ROLE_SUPER_ADMIN.name());
		//user.setAuthorities(ROLE_SUPER_ADMIN.getAuthorities());
		user.setProfileImageUrl(getTemporaryProfileImageUrl());
		userRepository.save(user);
		return user;
	}


	@Override
	public User addNewUser(Long parentUserId, String userName, String email, String phoneNumber, String addressLine1,
			List<Shop> shopList, Role role, String remarks)
			throws UserNotFoundException, UsernameExistException, EmailExistException {
		validateNewUsernameAndEmail(EMPTY, email);
		User user = new User();
		List<Shop> shopListFromInputId = shopService.findAllShopById(shopList);
		user.setRoleObject(role);
		user.setShopList(shopListFromInputId);
		user.setParentUserId(parentUserId);
		String pass = generatePassword();
		String encodedPassword = encodePassword(pass);
		System.out.println("USerPasswoord"+pass);
		user.setFirstName(userName);
		user.setEmail(email);
		//Here we Authendicate by email. In spring security manager have authendicatebyuserName class only
		//For this purpose we store email in userName column.  
		//So we no need to write logic for Authendication by email
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
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public User findUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
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



}
