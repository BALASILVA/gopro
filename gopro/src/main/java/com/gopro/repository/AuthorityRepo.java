package com.gopro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gopro.bene.Authority;

public interface AuthorityRepo extends JpaRepository<Authority, Integer> {
	List<Authority> findAuthorityByRoleId(int roleId);
}
