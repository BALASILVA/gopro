package com.gopro.bene;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false, name = "productid")
	private Long productId;

	@Column(name = "productname")
	private String productName;

	@Column(name = "barcode")
	private String barCode;

	@Column(name = "issysgenbarcode")
	private String isSysGenBarCode;

	@Column(name = "price")
	private Long price;

	@Column(name = "availablestock")
	private Long availableStock;

	@Column(name = "totalsale")
	private Long totalSale;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "isactive")
	private String isActive;

	@Column(name = "lastloaddate")
	private Date lastLoadDate;

	@Column(name = "createddate")
	private Date createdDate;

	@ManyToOne
	@JoinColumn(name = "shopid", referencedColumnName = "shopid")
	@JsonIgnore
	private Shop shop;

	public Product() {
	}

	public Product(Long productId, String productName, String barCode, String isSysGenBarCode, Long price,
			Long availableStock, Long totalSale, String remarks, String isActive, Date lastLoadDate, Date createdDate,
			Shop shop) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.barCode = barCode;
		this.isSysGenBarCode = isSysGenBarCode;
		this.price = price;
		this.availableStock = availableStock;
		this.totalSale = totalSale;
		this.remarks = remarks;
		this.isActive = isActive;
		this.lastLoadDate = lastLoadDate;
		this.createdDate = createdDate;
		this.shop = shop;
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

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getIsSysGenBarCode() {
		return isSysGenBarCode;
	}

	public void setIsSysGenBarCode(String isSysGenBarCode) {
		this.isSysGenBarCode = isSysGenBarCode;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getAvailableStock() {
		return availableStock;
	}

	public void setAvailableStock(Long availableStock) {
		this.availableStock = availableStock;
	}

	public Long getTotalSale() {
		return totalSale;
	}

	public void setTotalSale(Long totalSale) {
		this.totalSale = totalSale;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Date getLastLoadDate() {
		return lastLoadDate;
	}

	public void setLastLoadDate(Date lastLoadDate) {
		this.lastLoadDate = lastLoadDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", barCode=" + barCode
				+ ", isSysGenBarCode=" + isSysGenBarCode + ", price=" + price + ", availableStock=" + availableStock
				+ ", totalSale=" + totalSale + ", remarks=" + remarks + ", isActive=" + isActive + ", lastLoadDate="
				+ lastLoadDate + ", createdDate=" + createdDate + ", shop=" + shop + "]";
	}

}
