package com.gopro.bene;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "invoiceproductmap")
public class InvoiceProductMap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoiceproductmapid", nullable = false, updatable = false)
	private Long invoiceProductMapId;

	@Column(name = "productid")
	private Long productId;

	@Column(name = "productname")
	private String productName;

	@Column(name = "noOfProduct")
	private int noOfProduct;

	@Column(name = "priceperitem")
	private Double pricePerItem;

	@Column(name = "totalpriceofproduct")
	private Double totalPriceOfProduct;

	public InvoiceProductMap() {
		// TODO Auto-generated constructor stub
	}

	public InvoiceProductMap(Long invoiceProductMapId, Long productId, String productName, int noOfProduct,
			Double pricePerItem, Double totalPriceOfProduct) {
		super();
		this.invoiceProductMapId = invoiceProductMapId;
		this.productId = productId;
		this.productName = productName;
		this.noOfProduct = noOfProduct;
		this.pricePerItem = pricePerItem;
		this.totalPriceOfProduct = totalPriceOfProduct;
	}

	public Long getInvoiceProductMapId() {
		return invoiceProductMapId;
	}

	public void setInvoiceProductMapId(Long invoiceProductMapId) {
		this.invoiceProductMapId = invoiceProductMapId;
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

	public int getNoOfProduct() {
		return noOfProduct;
	}

	public void setNoOfProduct(int noOfProduct) {
		this.noOfProduct = noOfProduct;
	}

	public Double getPricePerItem() {
		return pricePerItem;
	}

	public void setPricePerItem(Double pricePerItem) {
		this.pricePerItem = pricePerItem;
	}

	public Double getTotalPriceOfProduct() {
		return totalPriceOfProduct;
	}

	public void setTotalPriceOfProduct(Double totalPriceOfProduct) {
		this.totalPriceOfProduct = totalPriceOfProduct;
	}

	@Override
	public String toString() {
		return "InvoiceProductMap [invoiceProductMapId=" + invoiceProductMapId + ", productId=" + productId
				+ ", productName=" + productName + ", noOfProduct=" + noOfProduct + ", pricePerItem=" + pricePerItem
				+ ", totalPriceOfProduct=" + totalPriceOfProduct + "]";
	}

}
