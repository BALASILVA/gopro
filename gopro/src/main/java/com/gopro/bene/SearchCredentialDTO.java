package com.gopro.bene;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

public class SearchCredentialDTO {

	// Comman
	private int page;
	private int size;
	private String searchKeyWord;
	private String fromDate;
	private String toDate;
	private String shortBy;
	private String shortOrderAscOrDsc;
	private String moduleName;
	private boolean isActive;
	private boolean isNotLocked;

	// User Module
	private Long Id;
	private String userId;
	private String username;
	private String firstName;
	private String email;
	private String phoneNumber;
	private Long parentUserId;
	private Long defaultShopId;

	// Product Module
	private Long productId;
	private String productName;
	private Long price;
	private Long startPrice;
	private Long endPrice;
	private Long availableStock;
	private Long startAvailableStock;
	private Long endAvailableStock;
	private Long totalSale;
	private Long startTotalSale;
	private Long endTotalSale;
	private Date lastLoadDate;
	private Date startLastLoadDate;
	private Date endLastLoadDate;

	// Shop Module
	private Long shopId;
	private String shopName;
	private String addressLineOne;
	private String addressLineTwo;
	private String addressLineThree;
	private String district;
	private String state;
	private String pinCode;

	// Role Module
	private int roleId;
	private String roleName;

	public SearchCredentialDTO() {
	}

	public SearchCredentialDTO(int page, int size, String searchKeyWord, String fromDate, String toDate, String shortBy,
			String shortOrderAscOrDsc, String moduleName, boolean isActive, boolean isNotLocked, Long id, String userId,
			String username, String firstName, String email, String phoneNumber, Long parentUserId, Long defaultShopId,
			Long productId, String productName, Long price, Long startPrice, Long endPrice, Long availableStock,
			Long startAvailableStock, Long endAvailableStock, Long totalSale, Long startTotalSale, Long endTotalSale,
			Date lastLoadDate, Date startLastLoadDate, Date endLastLoadDate, Long shopId, String shopName,
			String addressLineOne, String addressLineTwo, String addressLineThree, String district, String state,
			String pinCode, int roleId, String roleName) {
		super();
		this.page = page;
		this.size = size;
		this.searchKeyWord = searchKeyWord;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.shortBy = shortBy;
		this.shortOrderAscOrDsc = shortOrderAscOrDsc;
		this.moduleName = moduleName;
		this.isActive = isActive;
		this.isNotLocked = isNotLocked;
		Id = id;
		this.userId = userId;
		this.username = username;
		this.firstName = firstName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.parentUserId = parentUserId;
		this.defaultShopId = defaultShopId;
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.startPrice = startPrice;
		this.endPrice = endPrice;
		this.availableStock = availableStock;
		this.startAvailableStock = startAvailableStock;
		this.endAvailableStock = endAvailableStock;
		this.totalSale = totalSale;
		this.startTotalSale = startTotalSale;
		this.endTotalSale = endTotalSale;
		this.lastLoadDate = lastLoadDate;
		this.startLastLoadDate = startLastLoadDate;
		this.endLastLoadDate = endLastLoadDate;
		this.shopId = shopId;
		this.shopName = shopName;
		this.addressLineOne = addressLineOne;
		this.addressLineTwo = addressLineTwo;
		this.addressLineThree = addressLineThree;
		this.district = district;
		this.state = state;
		this.pinCode = pinCode;
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getSearchKeyWord() {
		return searchKeyWord;
	}

	public void setSearchKeyWord(String searchKeyWord) {
		this.searchKeyWord = searchKeyWord;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getShortBy() {
		return shortBy;
	}

	public void setShortBy(String shortBy) {
		this.shortBy = shortBy;
	}

	public String getShortOrderAscOrDsc() {
		return shortOrderAscOrDsc;
	}

	public void setShortOrderAscOrDsc(String shortOrderAscOrDsc) {
		this.shortOrderAscOrDsc = shortOrderAscOrDsc;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public Long getDefaultShopId() {
		return defaultShopId;
	}

	public void setDefaultShopId(Long defaultShopId) {
		this.defaultShopId = defaultShopId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(Long startPrice) {
		this.startPrice = startPrice;
	}

	public Long getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(Long endPrice) {
		this.endPrice = endPrice;
	}

	public Long getAvailableStock() {
		return availableStock;
	}

	public void setAvailableStock(Long availableStock) {
		this.availableStock = availableStock;
	}

	public Long getStartAvailableStock() {
		return startAvailableStock;
	}

	public void setStartAvailableStock(Long startAvailableStock) {
		this.startAvailableStock = startAvailableStock;
	}

	public Long getEndAvailableStock() {
		return endAvailableStock;
	}

	public void setEndAvailableStock(Long endAvailableStock) {
		this.endAvailableStock = endAvailableStock;
	}

	public Long getTotalSale() {
		return totalSale;
	}

	public void setTotalSale(Long totalSale) {
		this.totalSale = totalSale;
	}

	public Long getStartTotalSale() {
		return startTotalSale;
	}

	public void setStartTotalSale(Long startTotalSale) {
		this.startTotalSale = startTotalSale;
	}

	public Long getEndTotalSale() {
		return endTotalSale;
	}

	public void setEndTotalSale(Long endTotalSale) {
		this.endTotalSale = endTotalSale;
	}

	public Date getLastLoadDate() {
		return lastLoadDate;
	}

	public void setLastLoadDate(Date lastLoadDate) {
		this.lastLoadDate = lastLoadDate;
	}

	public Date getStartLastLoadDate() {
		return startLastLoadDate;
	}

	public void setStartLastLoadDate(Date startLastLoadDate) {
		this.startLastLoadDate = startLastLoadDate;
	}

	public Date getEndLastLoadDate() {
		return endLastLoadDate;
	}

	public void setEndLastLoadDate(Date endLastLoadDate) {
		this.endLastLoadDate = endLastLoadDate;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getAddressLineOne() {
		return addressLineOne;
	}

	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}

	public String getAddressLineTwo() {
		return addressLineTwo;
	}

	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
	}

	public String getAddressLineThree() {
		return addressLineThree;
	}

	public void setAddressLineThree(String addressLineThree) {
		this.addressLineThree = addressLineThree;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
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

	@Override
	public String toString() {
		return "SearchCredentialDTO [page=" + page + ", size=" + size + ", searchKeyWord=" + searchKeyWord
				+ ", fromDate=" + fromDate + ", toDate=" + toDate + ", shortBy=" + shortBy + ", shortOrderAscOrDsc="
				+ shortOrderAscOrDsc + ", moduleName=" + moduleName + ", isActive=" + isActive + ", isNotLocked="
				+ isNotLocked + ", Id=" + Id + ", userId=" + userId + ", username=" + username + ", firstName="
				+ firstName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", parentUserId=" + parentUserId
				+ ", defaultShopId=" + defaultShopId + ", productId=" + productId + ", productName=" + productName
				+ ", price=" + price + ", startPrice=" + startPrice + ", endPrice=" + endPrice + ", availableStock="
				+ availableStock + ", startAvailableStock=" + startAvailableStock + ", endAvailableStock="
				+ endAvailableStock + ", totalSale=" + totalSale + ", startTotalSale=" + startTotalSale
				+ ", endTotalSale=" + endTotalSale + ", lastLoadDate=" + lastLoadDate + ", startLastLoadDate="
				+ startLastLoadDate + ", endLastLoadDate=" + endLastLoadDate + ", shopId=" + shopId + ", shopName="
				+ shopName + ", addressLineOne=" + addressLineOne + ", addressLineTwo=" + addressLineTwo
				+ ", addressLineThree=" + addressLineThree + ", district=" + district + ", state=" + state
				+ ", pinCode=" + pinCode + ", roleId=" + roleId + ", roleName=" + roleName + "]";
	}

}
