package com.gopro.bene;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Shop {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shop_seq_generator")
    @SequenceGenerator(name="shop_seq_generator", sequenceName = "shop_seq")
	@Column(nullable = false, updatable = false)
	private Long shopId;

	@Column(nullable = false, unique = true)
	private String shopName;
	private String addressLineOne;
	private String addressLineTwo;
	private String addressLineThree;
	private String district;
	private String state;
	private String pinCode;
	private Date createdDate;

	//@OneToMany(targetEntity = Shop.class, mappedBy = "shopId")
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "productId")
	private List<Product> products;

	public Shop() {
	}

	public Shop(Long shopId, String shopName, String addressLineOne, String addressLineTwo, String addressLineThree,
			String district, String state, String pinCode, Date createdDate, List<Product> products) {
		super();
		this.shopId = shopId;
		this.shopName = shopName;
		this.addressLineOne = addressLineOne;
		this.addressLineTwo = addressLineTwo;
		this.addressLineThree = addressLineThree;
		this.district = district;
		this.state = state;
		this.pinCode = pinCode;
		this.createdDate = createdDate;
		this.products = products;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Shop [shopId=" + shopId + ", shopName=" + shopName + ", addressLineOne=" + addressLineOne
				+ ", addressLineTwo=" + addressLineTwo + ", addressLineThree=" + addressLineThree + ", district="
				+ district + ", state=" + state + ", pinCode=" + pinCode + ", createdDate=" + createdDate
				+ ", products=" + products + "]";
	}
	
	


}
