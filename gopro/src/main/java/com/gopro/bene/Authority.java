package com.gopro.bene;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Authority {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="authorityid", nullable = false, updatable = false)
	private int authorityId;
	
	@Column(name="roleid")
	private int roleId;
	
	private String authority;

	public Authority() {
	}

	public Authority(int roleId, String authority) {
		super();
		this.roleId = roleId;
		this.authority = authority;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
