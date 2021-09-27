package com.gopro.bene;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SearchCredentialDTO {

	// Comman
	private int page;
	private int size;
	private String searchKeyWord;
	private Date fromDate;
	private Date toDate;
	private String shortBy;
	private String shortOrderAscOrDsc;
	private String moduleName;
	private boolean isActive;
	private boolean isNotLocked;
	private boolean isManualTrriger;

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
	private Double startPrice;
	private Double endPrice;
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

	// Invoice Module
	private Long invoiceId;
	private Long customerMobileNo;
	private int noOfProduct;
	private Double totalPrice;
	private String paymentType;
	private String userName;
	private String remarks;

	// Notification Module
	private Long notificationId;
	private String notificationType;
	private String Subject;
	private boolean isSystemGenerated;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private Date notificationLatUpdateDate;

	// Notification Meesage Map
	private Long notificationMessageMapId;
	private Long sendFrom;
	private String message;
	private Date time;

	// Notification User Map
	private Long NotificationUserMapId;
	private String mappingType;
	private Long sendTo;
	private boolean isFavorite;
	private boolean isDeleted;
	private boolean isReaded;
	private Date readTime;

	// Role Module
	private int roleId;
	private String roleName;

	// DashBorad
	private String salesListDayOrMounth;

	public SearchCredentialDTO() {
	}

	public SearchCredentialDTO(int page, int size, String searchKeyWord, Date fromDate, Date toDate, String shortBy,
			String shortOrderAscOrDsc, String moduleName, boolean isActive, boolean isNotLocked,
			boolean isManualTrriger, Long id, String userId, String username, String firstName, String email,
			String phoneNumber, Long parentUserId, Long defaultShopId, Long productId, String productName, Long price,
			Double startPrice, Double endPrice, Long availableStock, Long startAvailableStock, Long endAvailableStock,
			Long totalSale, Long startTotalSale, Long endTotalSale, Date lastLoadDate, Date startLastLoadDate,
			Date endLastLoadDate, Long shopId, String shopName, String addressLineOne, String addressLineTwo,
			String addressLineThree, String district, String state, String pinCode, Long invoiceId,
			Long customerMobileNo, int noOfProduct, Double totalPrice, String paymentType, String userName2,
			String remarks, Long notificationId, String notificationType, String subject, boolean isSystemGenerated,
			Date notificationLatUpdateDate, Long notificationMessageMapId, Long sendFrom, String message, Date time,
			Long notificationUserMapId, String mappingType, Long sendTo, boolean isFavorite, boolean isDeleted,
			boolean isReaded, Date readTime, int roleId, String roleName, String salesListDayOrMounth) {
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
		this.isManualTrriger = isManualTrriger;
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
		this.invoiceId = invoiceId;
		this.customerMobileNo = customerMobileNo;
		this.noOfProduct = noOfProduct;
		this.totalPrice = totalPrice;
		this.paymentType = paymentType;
		userName = userName2;
		this.remarks = remarks;
		this.notificationId = notificationId;
		this.notificationType = notificationType;
		Subject = subject;
		this.isSystemGenerated = isSystemGenerated;
		this.notificationLatUpdateDate = notificationLatUpdateDate;
		this.notificationMessageMapId = notificationMessageMapId;
		this.sendFrom = sendFrom;
		this.message = message;
		this.time = time;
		NotificationUserMapId = notificationUserMapId;
		this.mappingType = mappingType;
		this.sendTo = sendTo;
		this.isFavorite = isFavorite;
		this.isDeleted = isDeleted;
		this.isReaded = isReaded;
		this.readTime = readTime;
		this.roleId = roleId;
		this.roleName = roleName;
		this.salesListDayOrMounth = salesListDayOrMounth;
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

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
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

	public boolean getIsManualTrriger() {
		return isManualTrriger;
	}

	public void setManualTrriger(boolean isManualTrriger) {
		this.isManualTrriger = isManualTrriger;
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

	public Double getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(Double startPrice) {
		this.startPrice = startPrice;
	}

	public Double getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(Double endPrice) {
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

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Long getCustomerMobileNo() {
		return customerMobileNo;
	}

	public void setCustomerMobileNo(Long customerMobileNo) {
		this.customerMobileNo = customerMobileNo;
	}

	public int getNoOfProduct() {
		return noOfProduct;
	}

	public void setNoOfProduct(int noOfProduct) {
		this.noOfProduct = noOfProduct;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	public boolean isSystemGenerated() {
		return isSystemGenerated;
	}

	public void setSystemGenerated(boolean isSystemGenerated) {
		this.isSystemGenerated = isSystemGenerated;
	}

	public Date getNotificationLatUpdateDate() {
		return notificationLatUpdateDate;
	}

	public void setNotificationLatUpdateDate(Date notificationLatUpdateDate) {
		this.notificationLatUpdateDate = notificationLatUpdateDate;
	}

	public Long getNotificationMessageMapId() {
		return notificationMessageMapId;
	}

	public void setNotificationMessageMapId(Long notificationMessageMapId) {
		this.notificationMessageMapId = notificationMessageMapId;
	}

	public Long getSendFrom() {
		return sendFrom;
	}

	public void setSendFrom(Long sendFrom) {
		this.sendFrom = sendFrom;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Long getNotificationUserMapId() {
		return NotificationUserMapId;
	}

	public void setNotificationUserMapId(Long notificationUserMapId) {
		NotificationUserMapId = notificationUserMapId;
	}

	public String getMappingType() {
		return mappingType;
	}

	public void setMappingType(String mappingType) {
		this.mappingType = mappingType;
	}

	public Long getSendTo() {
		return sendTo;
	}

	public void setSendTo(Long sendTo) {
		this.sendTo = sendTo;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public boolean isReaded() {
		return isReaded;
	}

	public void setReaded(boolean isReaded) {
		this.isReaded = isReaded;
	}

	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
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

	public String getSalesListDayOrMounth() {
		return salesListDayOrMounth;
	}

	public void setSalesListDayOrMounth(String salesListDayOrMounth) {
		this.salesListDayOrMounth = salesListDayOrMounth;
	}

	@Override
	public String toString() {
		return "SearchCredentialDTO [page=" + page + ", size=" + size + ", "
				+ (searchKeyWord != null ? "searchKeyWord=" + searchKeyWord + ", " : "")
				+ (fromDate != null ? "fromDate=" + fromDate + ", " : "")
				+ (toDate != null ? "toDate=" + toDate + ", " : "")
				+ (shortBy != null ? "shortBy=" + shortBy + ", " : "")
				+ (shortOrderAscOrDsc != null ? "shortOrderAscOrDsc=" + shortOrderAscOrDsc + ", " : "")
				+ (moduleName != null ? "moduleName=" + moduleName + ", " : "") + "isActive=" + isActive
				+ ", isNotLocked=" + isNotLocked + ", isManualTrriger=" + isManualTrriger + ", "
				+ (Id != null ? "Id=" + Id + ", " : "") + (userId != null ? "userId=" + userId + ", " : "")
				+ (username != null ? "username=" + username + ", " : "")
				+ (firstName != null ? "firstName=" + firstName + ", " : "")
				+ (email != null ? "email=" + email + ", " : "")
				+ (phoneNumber != null ? "phoneNumber=" + phoneNumber + ", " : "")
				+ (parentUserId != null ? "parentUserId=" + parentUserId + ", " : "")
				+ (defaultShopId != null ? "defaultShopId=" + defaultShopId + ", " : "")
				+ (productId != null ? "productId=" + productId + ", " : "")
				+ (productName != null ? "productName=" + productName + ", " : "")
				+ (price != null ? "price=" + price + ", " : "")
				+ (startPrice != null ? "startPrice=" + startPrice + ", " : "")
				+ (endPrice != null ? "endPrice=" + endPrice + ", " : "")
				+ (availableStock != null ? "availableStock=" + availableStock + ", " : "")
				+ (startAvailableStock != null ? "startAvailableStock=" + startAvailableStock + ", " : "")
				+ (endAvailableStock != null ? "endAvailableStock=" + endAvailableStock + ", " : "")
				+ (totalSale != null ? "totalSale=" + totalSale + ", " : "")
				+ (startTotalSale != null ? "startTotalSale=" + startTotalSale + ", " : "")
				+ (endTotalSale != null ? "endTotalSale=" + endTotalSale + ", " : "")
				+ (lastLoadDate != null ? "lastLoadDate=" + lastLoadDate + ", " : "")
				+ (startLastLoadDate != null ? "startLastLoadDate=" + startLastLoadDate + ", " : "")
				+ (endLastLoadDate != null ? "endLastLoadDate=" + endLastLoadDate + ", " : "")
				+ (shopId != null ? "shopId=" + shopId + ", " : "")
				+ (shopName != null ? "shopName=" + shopName + ", " : "")
				+ (addressLineOne != null ? "addressLineOne=" + addressLineOne + ", " : "")
				+ (addressLineTwo != null ? "addressLineTwo=" + addressLineTwo + ", " : "")
				+ (addressLineThree != null ? "addressLineThree=" + addressLineThree + ", " : "")
				+ (district != null ? "district=" + district + ", " : "")
				+ (state != null ? "state=" + state + ", " : "") + (pinCode != null ? "pinCode=" + pinCode + ", " : "")
				+ (invoiceId != null ? "invoiceId=" + invoiceId + ", " : "")
				+ (customerMobileNo != null ? "customerMobileNo=" + customerMobileNo + ", " : "") + "noOfProduct="
				+ noOfProduct + ", " + (totalPrice != null ? "totalPrice=" + totalPrice + ", " : "")
				+ (paymentType != null ? "paymentType=" + paymentType + ", " : "")
				+ (userName != null ? "userName=" + userName + ", " : "")
				+ (remarks != null ? "remarks=" + remarks + ", " : "")
				+ (notificationId != null ? "notificationId=" + notificationId + ", " : "")
				+ (notificationType != null ? "notificationType=" + notificationType + ", " : "")
				+ (Subject != null ? "Subject=" + Subject + ", " : "") + "isSystemGenerated=" + isSystemGenerated + ", "
				+ (notificationLatUpdateDate != null ? "notificationLatUpdateDate=" + notificationLatUpdateDate + ", "
						: "")
				+ (notificationMessageMapId != null ? "notificationMessageMapId=" + notificationMessageMapId + ", "
						: "")
				+ (sendFrom != null ? "sendFrom=" + sendFrom + ", " : "")
				+ (message != null ? "message=" + message + ", " : "") + (time != null ? "time=" + time + ", " : "")
				+ (NotificationUserMapId != null ? "NotificationUserMapId=" + NotificationUserMapId + ", " : "")
				+ (mappingType != null ? "mappingType=" + mappingType + ", " : "")
				+ (sendTo != null ? "sendTo=" + sendTo + ", " : "") + "isFavorite=" + isFavorite + ", isDeleted="
				+ isDeleted + ", isReaded=" + isReaded + ", " + (readTime != null ? "readTime=" + readTime + ", " : "")
				+ "roleId=" + roleId + ", " + (roleName != null ? "roleName=" + roleName + ", " : "")
				+ (salesListDayOrMounth != null ? "salesListDayOrMounth=" + salesListDayOrMounth : "") + "]";
	}

}
