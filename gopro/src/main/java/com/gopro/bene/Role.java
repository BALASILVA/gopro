package com.gopro.bene;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false,name="roleid")
	private int roleId;
	
	@Column(name="rolename")	
	private String roleName;
	
	@Column(name="isactive")
	private String isActive;
	
	@ManyToMany
	@JoinColumn(name = "menuid")
	@Column(name="menulist")
	private List<Menu> menuList;

	public Role() {
	}

	public Role(int roleId, String roleName, String isActive, List<Menu> menuList) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.isActive = isActive;
		this.menuList = menuList;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", isActive=" + isActive + ", menuList=" + menuList
				+ "]";
	}
}
