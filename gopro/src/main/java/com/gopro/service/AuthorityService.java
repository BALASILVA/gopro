package com.gopro.service;

import java.util.List;

import com.gopro.bene.Authority;

public interface AuthorityService {
	List<Authority> findAuthorityByRoleId(int roleId);
}
