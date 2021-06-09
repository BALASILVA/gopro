package com.gopro.service;

import java.util.List;

import com.gopro.bene.Role;

public interface RoleService {

    Role findRoleByRoleId(int roleId);
    
    List<Role> findAllRoleWithOutSuperAdmin();

	Role getRoleById(int roleId);

	List<Role> findAllChildRoleForLoginUser();

}
