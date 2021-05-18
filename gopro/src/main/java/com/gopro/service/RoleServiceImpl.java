package com.gopro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gopro.bene.Role;
import com.gopro.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	private RoleRepository roleRepository;

	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}


	@Override
	public Role findRoleByRoleId(int roleId) {
		Role role = new Role();
		try {
			 role = roleRepository.findRoleByRoleId(roleId);;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		
		return role;
	}


	@Override
	public List<Role> findAllRoleWithOutSuperAdmin() {
		return roleRepository.findAll();
	}


	@Override
	public Role getRoleById(int roleId) {
		return roleRepository.findRoleByRoleId(roleId);
	}

}
