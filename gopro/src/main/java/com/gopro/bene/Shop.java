package com.gopro.bene;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;

import org.hibernate.annotations.ColumnDefault;

import com.gopro.queryconstant.UserQueryConstant;


@SqlResultSetMapping(name = "findAllShop", classes = @ConstructorResult(targetClass = com.gopro.bene.Shop.class, columns = {
		@ColumnResult(name = "shopId", type = Long.class),
		@ColumnResult(name = "shopName", type = String.class),
		@ColumnResult(name = "phone", type = String.class),
		@ColumnResult(name = "totalsale", type = Long.class)					
	 }))
@NamedNativeQuery(name = "findAllShop", query = UserQueryConstant.FIND_SHOPS_BY_PARENT_USER, resultClass = Shop.class, resultSetMapping = "findAllShop")


@Entity
public class Shop {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shop_seq_generator")
	@SequenceGenerator(name = "shop_seq_generator", sequenceName = "shop_seq", allocationSize = 1, initialValue = 1)
	@Column(name = "shopid", nullable = false, updatable = false)
	private Long shopId;

	@Column(name = "shopname", nullable = false)
	private String shopName;

	@ColumnDefault("0")
	@Column(name = "todaysale")
	private Long todaySale;

	@ColumnDefault("0")
	@Column(name = "totalsale")
	private Long totalSale;

	@Column(name = "email")
	private String email;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "addresslineone")
	private String addressLineOne;

	@Column(name = "addresslinetwo")
	private String addressLineTwo;

	@Column(name = "addresslinethree")
	private String addressLineThree;

	@Column(name = "district")
	private String district;

	@Column(name = "state")
	private String state;

	@Column(name = "pincode")
	private String pinCode;

	@Column(name = "createddate")
	private Date createdDate;

	@Column(name = "userid")
	private Long userId;

	// User Id of supper admin (or) parent user

	public Shop() {
		// TODO Auto-generated constructor stub
	}

	public Shop(Long shopId, String shopName, Long todaySale, Long totalSale, String email, String phone,
			String addressLineOne, String addressLineTwo, String addressLineThree, String district, String state,
			String pinCode, Date createdDate, Long userId) {
		super();
		this.shopId = shopId;
		this.shopName = shopName;
		this.todaySale = todaySale;
		this.totalSale = totalSale;
		this.email = email;
		this.phone = phone;
		this.addressLineOne = addressLineOne;
		this.addressLineTwo = addressLineTwo;
		this.addressLineThree = addressLineThree;
		this.district = district;
		this.state = state;
		this.pinCode = pinCode;
		this.createdDate = createdDate;
		this.userId = userId;
	}

	
	/////Query Constructors Dont remove//////
	public Shop(Long shopId, String shopName, String phone, Long totalSale) {
		super();
		this.shopId = shopId;
		this.shopName = shopName;
		this.phone = phone;
		this.totalSale = totalSale;		
	}
	/////Query Constructors Dont remove//////

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getTotalSale() {
		return totalSale;
	}

	public void setTotalSale(Long totalSale) {
		this.totalSale = totalSale;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTodaySale() {
		return todaySale;
	}

	public void setTodaySale(Long todaySale) {
		this.todaySale = todaySale;
	}

	@Override
	public String toString() {
		return "Shop [" + (shopId != null ? "shopId=" + shopId + ", " : "")
				+ (shopName != null ? "shopName=" + shopName + ", " : "")
				+ (todaySale != null ? "todaySale=" + todaySale + ", " : "")
				+ (totalSale != null ? "totalSale=" + totalSale + ", " : "")
				+ (email != null ? "email=" + email + ", " : "") + (phone != null ? "phone=" + phone + ", " : "")
				+ (addressLineOne != null ? "addressLineOne=" + addressLineOne + ", " : "")
				+ (addressLineTwo != null ? "addressLineTwo=" + addressLineTwo + ", " : "")
				+ (addressLineThree != null ? "addressLineThree=" + addressLineThree + ", " : "")
				+ (district != null ? "district=" + district + ", " : "")
				+ (state != null ? "state=" + state + ", " : "") + (pinCode != null ? "pinCode=" + pinCode + ", " : "")
				+ (createdDate != null ? "createdDate=" + createdDate + ", " : "")
				+ (userId != null ? "userId=" + userId : "") + "]";
	}

}
