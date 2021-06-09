package com.gopro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopro.bene.Role;
import com.gopro.service.RoleService;

@RestController
@RequestMapping(path = { "/", "/role" })
public class RoleController {
	
	RoleService roleService;
	
	
    @Autowired
	public RoleController(RoleService roleService) {
		super();
		this.roleService = roleService;
	}



	@GetMapping
    public List<Role> findAllRoleWithOutSuperAdmin() {
        return roleService.findAllRoleWithOutSuperAdmin();
    }
	
	@GetMapping("/userrole")
    public List<Role> findAllRoleByUserId() {
        return roleService.findAllChildRoleForLoginUser();
    }
	
}
