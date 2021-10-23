package com.gopro.controller;

import com.gopro.bene.User;
import com.gopro.bene.UserPrincipal;
import com.gopro.constant.UserImplConstant;
import com.gopro.AuthendicationFacade.AuthendicationFacade;
import com.gopro.bene.Authority;
import com.gopro.bene.SearchCredentialDTO;
import com.gopro.bene.Shop;
import com.gopro.exception.ExceptionHandling;
import com.gopro.exception.domain.EmailExistException;
import com.gopro.exception.domain.UserNotFoundException;
import com.gopro.exception.domain.UsernameExistException;
import com.gopro.service.AuthorityService;
import com.gopro.service.UserService;
import com.gopro.utility.JWTTokenProvider;
import com.gopro.validation.UserValidation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.gopro.constant.SecurityConstant.JWT_TOKEN_HEADER;
import static org.springframework.http.HttpStatus.OK;

import java.util.InputMismatchException;
import java.util.List;



@RestController
@RequestMapping(path = { "/", "/user" })
public class UserController extends ExceptionHandling {
	private AuthenticationManager authenticationManager;
	private UserService userService;
	private AuthorityService authorityService;
	private JWTTokenProvider jwtTokenProvider;
	private AuthendicationFacade authendicationFacade;
	private UserValidation userValidation;

	Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	public UserController(AuthenticationManager authenticationManager, UserService userService,
			JWTTokenProvider jwtTokenProvider,AuthorityService authorityService,AuthendicationFacade authendicationFacade,UserValidation userValidation) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.jwtTokenProvider = jwtTokenProvider;
		this.authorityService = authorityService;
		this.authendicationFacade = authendicationFacade;
		this.userValidation = userValidation;
	}

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user) {

		LOGGER.info("User "+user.getEmail()+ " Try to Login");
		userValidation.validateLogInUserDetail(user.getEmail(), user.getPassword());
		authenticate(user.getEmail(), user.getPassword());
		User loginUser = userService.findUserByUsername(user.getEmail());

		int userRoleId = loginUser.getRoleObject().getRoleId();
		List<Authority>  userAuthorityList = authorityService.findAuthorityByRoleId(userRoleId);
		String[] userAuthorityArr = new String[userAuthorityList.size()];
		for (int i = 0; i < userAuthorityList.size(); i++) {
			userAuthorityArr[i]=userAuthorityList.get(i).getAuthority();
		}
		loginUser.setAuthorities(userAuthorityArr);
		
		//User loginUser = userService.findUserByEmail(user.getEmail());
		UserPrincipal userPrincipal = new UserPrincipal(loginUser);
		
		userPrincipal.setUser(loginUser);
		HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
		LOGGER.info("User "+user.getEmail()+ " Sucessfully Login");
		return new ResponseEntity<>(loginUser, jwtHeader, OK);
	}

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user)
			throws UserNotFoundException, UsernameExistException, EmailExistException,InputMismatchException, Exception  {
			LOGGER.info("/register controller START  with userName "+ user.getFirstName() + "Email :: "+user.getEmail());
			userValidation.validationUserForRegister(user);	
			User newUser = userService.register(user.getFirstName(),user.getShopList().get(0), user.getEmail(),user.getPhoneNumber(), user.getPassword(), user.getPhoneNumber(), user.getOtp());
			LOGGER.info("/register controller END called with userName "+ user.getFirstName() + "Email :: "+user.getEmail());
			return new ResponseEntity<>(newUser, OK);
	}

	@PostMapping("/add")
	public ResponseEntity<User> addNewUser(@RequestBody User user)
			throws UserNotFoundException, UsernameExistException, EmailExistException,Exception {
		userValidation.validateAddSubUserDetails(user.getFirstName(),user.getLastName(),user.getEmail(),user.getPhoneNumber(),user.getAddressLine1(),user.getShopList(),user.getRoleObject(),user.getRemarks());
		User newUser = userService.addNewUser(user.getFirstName(),user.getLastName(),user.getEmail(),user.getPhoneNumber(),user.getAddressLine1(),user.getShopList(),user.getRoleObject(),user.getRemarks());
		if(newUser == null) {
			LOGGER.error("TecnicalError occured User Detail is null");
			throw new Exception(UserImplConstant.TECHINICAL_ERROR_OCCURED);
		}
		return new ResponseEntity<>(newUser, OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<User> updateUser(@RequestBody User user)
			throws UserNotFoundException, UsernameExistException, EmailExistException, Exception {		
		userValidation.validateAddSubUserDetails(user.getFirstName(),user.getLastName(),user.getEmail(),user.getPhoneNumber(),user.getAddressLine1(),user.getShopList(),user.getRoleObject(),user.getRemarks());
		User updatedUser = userService.updateUser(user.getId(),user.getFirstName(),user.getPhoneNumber(),user.getAddressLine1(),user.getShopList(),user.getRoleObject(),user.getRemarks());
		if(updatedUser == null) {
			LOGGER.error("Updare Failed Techical Error occured User Detail is null");
			throw new Exception(UserImplConstant.TECHINICAL_ERROR_OCCURED);
		}
		return new ResponseEntity<>(updatedUser, OK);
	}
	
	@PostMapping("/updatedefaultshop")
	public ResponseEntity<Boolean> updateDefaultShop(@RequestBody Shop shop)
			throws UserNotFoundException, UsernameExistException, EmailExistException {
		boolean isUpdated  = userService.updateDefaultShop(shop);
		return new ResponseEntity<>(isUpdated , OK);
	}
	
	
	@PostMapping
	public ResponseEntity<Page<User>> getAllUser(@RequestBody SearchCredentialDTO searchCredentialDTO)
			throws Exception {
		Page<User> userList = userService.getAllUserPaginationAndSorting(searchCredentialDTO);
		return new ResponseEntity<>(userList, OK);
	}	
	
	@PostMapping("/changepassword")
	public ResponseEntity<Boolean> changePassword(@RequestBody User user)
			throws UserNotFoundException, UsernameExistException, EmailExistException {		
		userValidation.validateChangePassword(user.getPassword());
		userValidation.validateChangePassword(user.getNewPassword());		
		User loginUser = authendicationFacade.getCurrentUserDetails();		
		authenticate(loginUser.getEmail(), user.getPassword());		
		boolean isUpdated = userService.changePassword(loginUser.getId(),user.getNewPassword());		
		return new ResponseEntity<>(isUpdated, OK);
	}
	
	@GetMapping(value="/getuserformail")
	public ResponseEntity<List<User>> getUserForSendMail()
			throws UserNotFoundException, UsernameExistException, EmailExistException {
			List<User> userList = userService.getUserForSendMail();
		return new ResponseEntity<>(userList, OK);
	}
	
	@GetMapping(value="/hasnewmail")
	public ResponseEntity<Boolean> newMailViewed()
			throws UserNotFoundException, UsernameExistException, EmailExistException {
			boolean updated = userService.haveNewMail();
		return new ResponseEntity<>(updated, OK);
	}
	
	
	@PostMapping(value="/ismailavailable")
	public ResponseEntity<Boolean> isMailAvailable(@RequestBody User user)
	{
		boolean isMailAvailable = userService.isMailAvailable(user.getEmail());
		return new ResponseEntity<>(isMailAvailable, OK);
	}	
	
	private HttpHeaders getJwtHeader(UserPrincipal user) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(user));
		return headers;
	}

	private void authenticate(String username, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}
}
