package com.gopro.AuthendicationFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.gopro.bene.User;
import com.gopro.service.UserService;

@Component
public class AuthendicationFacadeImpl implements AuthendicationFacade {

	private UserService userService;

	@Autowired
	public AuthendicationFacadeImpl(UserService userService) {
		super();
		this.userService = userService;
	}

	@Override
	public User getCurrentUserDetails() {
		Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = authentication.toString();
		//User user = userService.findUserByUsername(username);
		User user = userService.findUserByUsername("dino@gmail.com");
		return user;
	}
}
