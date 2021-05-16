package com.gopro.bene;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Product {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq_generator")
    @SequenceGenerator(name="product_seq_generator", sequenceName = "product_seq")
	@Column(nullable = false, updatable = false)
	private Long productId;
	private String productName;
	private long price;
	private long availableStock;
	private long totalSale;
	private String remarks;
	private String isActive;
	private String lastLoadDate;
	private Date createdDate;
	public Product() {
	}
	public Product(Long productId, String productName, long price, long availableStock, long totalSale, String remarks,
			String isActive, String lastLoadDate, Date createdDate) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.availableStock = availableStock;
		this.totalSale = totalSale;
		this.remarks = remarks;
		this.isActive = isActive;
		this.lastLoadDate = lastLoadDate;
		this.createdDate = createdDate;
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
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getAvailableStock() {
		return availableStock;
	}
	public void setAvailableStock(long availableStock) {
		this.availableStock = availableStock;
	}
	public long getTotalSale() {
		return totalSale;
	}
	public void setTotalSale(long totalSale) {
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
	public String getLastLoadDate() {
		return lastLoadDate;
	}
	public void setLastLoadDate(String lastLoadDate) {
		this.lastLoadDate = lastLoadDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
