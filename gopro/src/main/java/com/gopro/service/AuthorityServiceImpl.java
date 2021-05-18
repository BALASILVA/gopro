package com.gopro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gopro.bene.Authority;
import com.gopro.repository.AuthorityRepo;

@Service
public class AuthorityServiceImpl implements AuthorityService {
	
	AuthorityRepo authRepo;
	
	
	@Autowired
	public AuthorityServiceImpl(AuthorityRepo authRepo) {
		super();
		this.authRepo = authRepo;
	}



	public List<Authority> findAuthorityByRoleId(int roleId)
	{
		return authRepo.findAuthorityByRoleId(roleId);
	}
}
