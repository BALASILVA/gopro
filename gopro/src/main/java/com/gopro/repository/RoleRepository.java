package com.gopro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gopro.bene.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findRoleByRoleId(int roleId);

	List<Role> findAll();

}
