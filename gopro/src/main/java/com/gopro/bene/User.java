package com.gopro.bene;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_generator")
	@SequenceGenerator(name = "user_seq_generator", sequenceName = "user_seq")
	@Column(nullable = false, updatable = false)
	private Long Id;

	private String userId;

	private String firstName;
	private String lastName;
	private String username;
	private String password;
	@Column(nullable = false, unique = true)
	private String email;
	private String phoneNumber;
	private Long parentUserId;
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "shopId")
	private List<Shop> shopList;
	private String profileImageUrl;
	private Date lastLoginDate;
	private Date lastLoginDateDisplay;
	private Date joinDate;
	private String role; // ROLE_USER{ read, edit }, ROLE_ADMIN {delete}
	private String[] authorities;
	private boolean isActive;
	private boolean isNotLocked;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String state;
	private String pinCode;
	private String remarks;
	@OneToOne
	@JoinColumn(name = "roleId")
	private Role roleObject;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(Long id, String userId, String firstName, String lastName, String username, String password,
			String email, String phoneNumber, Long parentUserId, List<Shop> shopList, String profileImageUrl,
			Date lastLoginDate, Date lastLoginDateDisplay, Date joinDate, String role, String[] authorities,
			boolean isActive, boolean isNotLocked, String addressLine1, String addressLine2, String addressLine3,
			String state, String pinCode, String remarks, Role roleObject) {
		super();
		Id = id;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.parentUserId = parentUserId;
		this.shopList = shopList;
		this.profileImageUrl = profileImageUrl;
		this.lastLoginDate = lastLoginDate;
		this.lastLoginDateDisplay = lastLoginDateDisplay;
		this.joinDate = joinDate;
		this.role = role;
		this.authorities = authorities;
		this.isActive = isActive;
		this.isNotLocked = isNotLocked;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressLine3 = addressLine3;
		this.state = state;
		this.pinCode = pinCode;
		this.remarks = remarks;
		this.roleObject = roleObject;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getParentUserId() {
		return parentUserId;
	}

	public void setParentUserId(Long parentUserId) {
		this.parentUserId = parentUserId;
	}

	public List<Shop> getShopList() {
		return shopList;
	}

	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Date getLastLoginDateDisplay() {
		return lastLoginDateDisplay;
	}

	public void setLastLoginDateDisplay(Date lastLoginDateDisplay) {
		this.lastLoginDateDisplay = lastLoginDateDisplay;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String[] getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String[] authorities) {
		this.authorities = authorities;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isNotLocked() {
		return isNotLocked;
	}

	public void setNotLocked(boolean isNotLocked) {
		this.isNotLocked = isNotLocked;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Role getRoleObject() {
		return roleObject;
	}

	public void setRoleObject(Role roleObject) {
		this.roleObject = roleObject;
	}

	@Override
	public String toString() {
		return "User [Id=" + Id + ", userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", username=" + username + ", password=" + password + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", parentUserId=" + parentUserId + ", shopList=" + shopList + ", profileImageUrl="
				+ profileImageUrl + ", lastLoginDate=" + lastLoginDate + ", lastLoginDateDisplay="
				+ lastLoginDateDisplay + ", joinDate=" + joinDate + ", role=" + role + ", authorities="
				+ Arrays.toString(authorities) + ", isActive=" + isActive + ", isNotLocked=" + isNotLocked
				+ ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", addressLine3=" + addressLine3
				+ ", state=" + state + ", pinCode=" + pinCode + ", remarks=" + remarks + ", roleObject=" + roleObject
				+ "]";
	}

}
