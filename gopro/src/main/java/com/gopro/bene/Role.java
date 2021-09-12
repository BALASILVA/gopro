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
	@Column(nullable = false, updatable = false, name = "roleid")
	private int roleId;

	@Column(name = "rolename")
	private String roleName;

	@Column(name = "isactive")
	private String isActive;

	@Column(name = "description")
	private String description;

	@Column(name = "seqno")
	private int seqNo;

	@ManyToMany
	@JoinColumn(name = "menuid")
	@Column(name = "menulist")
	private List<Menu> menuList;

	public Role() {
	}

	public Role(int roleId, String roleName, String isActive, String description, int seqNo, List<Menu> menuList) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.isActive = isActive;
		this.description = description;
		this.seqNo = seqNo;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", " + (roleName != null ? "roleName=" + roleName + ", " : "")
				+ (isActive != null ? "isActive=" + isActive + ", " : "")
				+ (description != null ? "description=" + description + ", " : "") + "seqNo=" + seqNo + ", "
				+ (menuList != null ? "menuList=" + menuList : "") + "]";
	}

}
