package com.gopro.controller;

import com.gopro.bene.User;
import com.gopro.bene.UserPrincipal;
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

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.gopro.constant.SecurityConstant.JWT_TOKEN_HEADER;
import static org.springframework.http.HttpStatus.OK;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping(path = { "/", "/user" })
public class UserController extends ExceptionHandling {
	private AuthenticationManager authenticationManager;
	private UserService userService;
	private AuthorityService authorityService;
	private JWTTokenProvider jwtTokenProvider;
	

	@Autowired
	public UserController(AuthenticationManager authenticationManager, UserService userService,
			JWTTokenProvider jwtTokenProvider,AuthorityService authorityService) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.jwtTokenProvider = jwtTokenProvider;
		this.authorityService = authorityService;
	}

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user) {
		//User Name and Email are same in DB
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
		return new ResponseEntity<>(loginUser, jwtHeader, OK);
	}

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user)
			throws UserNotFoundException, UsernameExistException, EmailExistException {
		User newUser = userService.register(user.getUsername(),user.getShopList().get(0).getShopName(), user.getEmail(), user.getPassword());
		return new ResponseEntity<>(newUser, OK);
	}

	@PostMapping("/add")
	public ResponseEntity<User> addNewUser(@RequestBody User user)
			throws UserNotFoundException, UsernameExistException, EmailExistException {
		User newUser = userService.addNewUser(user.getFirstName(),user.getEmail(),user.getPhoneNumber(),user.getAddressLine1(),user.getShopList(),user.getRoleObject(),user.getRemarks());
		return new ResponseEntity<>(newUser, OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<User> updateUser(@RequestBody User user)
			throws UserNotFoundException, UsernameExistException, EmailExistException {
		System.out.println(user.getRemarks());
		User updatedUser = userService.updateUser(user.getId(),user.getFirstName(),user.getPhoneNumber(),user.getAddressLine1(),user.getShopList(),user.getRoleObject(),user.getRemarks());
		
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
			throws UserNotFoundException, UsernameExistException, EmailExistException {
		Page<User> userList = userService.getAllUserPaginationAndSorting(searchCredentialDTO);
		return new ResponseEntity<>(userList, OK);
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
