package com.gopro.bene;

import javax.persistence.*;

import com.gopro.queryconstant.UserQueryConstant;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SqlResultSetMapping(name = "findUserForSendMail", classes = @ConstructorResult(targetClass = com.gopro.bene.User.class, columns = {
		@ColumnResult(name = "Id", type = Long.class), @ColumnResult(name = "username", type = String.class),
		@ColumnResult(name = "firstName", type = String.class), @ColumnResult(name = "lastName", type = String.class),
		@ColumnResult(name = "phoneNumber", type = String.class),
		@ColumnResult(name = "profileImageUrl", type = String.class) }))
@NamedNativeQuery(name = "findUserForSendMail", query = UserQueryConstant.FIND_USERS_FOR_SEND_MAIL, resultClass = User.class, resultSetMapping = "findUserForSendMail")
@Entity
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_seq_generator")
	@SequenceGenerator(name = "user_seq_generator", sequenceName = "user_seq", allocationSize = 1, initialValue = 1)
	@Column(name = "id", nullable = false, updatable = false)
	private Long Id;

	@Column(name = "userid")
	private String userId;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	private String newPassword;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "phonenumber")
	private String phoneNumber;

	@Column(name = "parentuserid", nullable = false, updatable = false)
	private Long parentUserId;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "shopid")
	@Column(name = "shoplist")
	private List<Shop> shopList;

	@Column(name = "defaultshopid")
	private Long defaultShopId;

	@Column(name = "profileimageurl")
	private String profileImageUrl;

	@Column(name = "lastlogindate")
	private Date lastLoginDate;

	@Column(name = "lastlogindatedisplay")
	private Date lastLoginDateDisplay;

	@Column(name = "joindate")
	private Date joinDate;

	private String role; // ROLE_USER{ read, edit }, ROLE_ADMIN {delete}
	private String[] authorities;

	@Column(name = "isactive")
	private boolean isActive;

	@Column(name = "isnotlocked")
	private boolean isNotLocked;

	@Column(name = "addressline1")
	private String addressLine1;

	@Column(name = "addressline2")
	private String addressLine2;

	@Column(name = "addressline3")
	private String addressLine3;

	@Column(name = "state")
	private String state;

	@Column(name = "pincode")
	private String pinCode;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "otp")
	private Long otp;

	@Column(name = " hasnewmail", columnDefinition = "boolean default false", nullable = false)
	private boolean hasNewMail;

	@OneToOne
	@JoinColumn(name = "roleid")
	private Role roleObject;

	///// Query Constructors Dont remove//////
	// Query Name =
	// Method Name =
	public User(Long id, String username) {
		this.Id = id;
		this.username = username;
	}

	///// Query Constructors Dont remove///////

	///// Query Constructors Dont remove//////
	public User(Long id, String firstName, String lastName, String username, String phoneNumber,
			String profileImageUrl) {
		this.Id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.phoneNumber = phoneNumber;
		this.profileImageUrl = profileImageUrl;
	}
	///// Query Constructors Dont remove//////

	public User() {

	}

	public User(Long id, String userId, String firstName, String lastName, String username, String password,
			String newPassword, String email, String phoneNumber, Long parentUserId, List<Shop> shopList,
			Long defaultShopId, String profileImageUrl, Date lastLoginDate, Date lastLoginDateDisplay, Date joinDate,
			String role, String[] authorities, boolean isActive, boolean isNotLocked, String addressLine1,
			String addressLine2, String addressLine3, String state, String pinCode, String remarks, Long otp,
			boolean hasNewMail, Role roleObject) {
		super();
		Id = id;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.newPassword = newPassword;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.parentUserId = parentUserId;
		this.shopList = shopList;
		this.defaultShopId = defaultShopId;
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
		this.otp = otp;
		this.hasNewMail = hasNewMail;
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

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
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

	public Long getDefaultShopId() {
		return defaultShopId;
	}

	public void setDefaultShopId(Long defaultShopId) {
		this.defaultShopId = defaultShopId;
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

	public boolean isHasNewMail() {
		return hasNewMail;
	}

	public void setHasNewMail(boolean hasNewMail) {
		this.hasNewMail = hasNewMail;
	}

	public Role getRoleObject() {
		return roleObject;
	}

	public void setRoleObject(Role roleObject) {
		this.roleObject = roleObject;
	}

	public Long getOtp() {
		return otp;
	}

	public void setOtp(Long otp) {
		this.otp = otp;
	}

	@Override
	public String toString() {
		return "User [" + (Id != null ? "Id=" + Id + ", " : "") + (userId != null ? "userId=" + userId + ", " : "")
				+ (firstName != null ? "firstName=" + firstName + ", " : "")
				+ (lastName != null ? "lastName=" + lastName + ", " : "")
				+ (username != null ? "username=" + username + ", " : "")
				+ (password != null ? "password=" + password + ", " : "")
				+ (newPassword != null ? "newPassword=" + newPassword + ", " : "")
				+ (email != null ? "email=" + email + ", " : "")
				+ (phoneNumber != null ? "phoneNumber=" + phoneNumber + ", " : "")
				+ (parentUserId != null ? "parentUserId=" + parentUserId + ", " : "")
				+ (shopList != null ? "shopList=" + shopList + ", " : "")
				+ (defaultShopId != null ? "defaultShopId=" + defaultShopId + ", " : "")
				+ (profileImageUrl != null ? "profileImageUrl=" + profileImageUrl + ", " : "")
				+ (lastLoginDate != null ? "lastLoginDate=" + lastLoginDate + ", " : "")
				+ (lastLoginDateDisplay != null ? "lastLoginDateDisplay=" + lastLoginDateDisplay + ", " : "")
				+ (joinDate != null ? "joinDate=" + joinDate + ", " : "") + (role != null ? "role=" + role + ", " : "")
				+ (authorities != null ? "authorities=" + Arrays.toString(authorities) + ", " : "") + "isActive="
				+ isActive + ", isNotLocked=" + isNotLocked + ", "
				+ (addressLine1 != null ? "addressLine1=" + addressLine1 + ", " : "")
				+ (addressLine2 != null ? "addressLine2=" + addressLine2 + ", " : "")
				+ (addressLine3 != null ? "addressLine3=" + addressLine3 + ", " : "")
				+ (state != null ? "state=" + state + ", " : "") + (pinCode != null ? "pinCode=" + pinCode + ", " : "")
				+ (remarks != null ? "remarks=" + remarks + ", " : "") + (otp != null ? "otp=" + otp + ", " : "")
				+ "hasNewMail=" + hasNewMail + ", " + (roleObject != null ? "roleObject=" + roleObject : "") + "]";
	}

}
