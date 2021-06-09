package com.gopro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gopro.bene.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findRoleByRoleId(int roleId);

	List<Role> findAll();

	@Query(value = "select * from role where roleid > :userroleid", nativeQuery = true)
	List<Role> findAllChildRoleForLoginUser(@Param("userroleid") int roleId);

}
