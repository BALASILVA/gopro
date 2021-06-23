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

	
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	 * "seq_generator_invoiceproductmapid")
	 * 
	 * @SequenceGenerator(name = "seq_generator_invoiceproductmapid", sequenceName =
	 * "seq_invoiceproductmapid", allocationSize = 1, initialValue = 1)
	 */
	
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

	@ManyToOne
	@JoinColumn(name = "invoiseid")
	private Invoice invoice;

	public InvoiceProductMap() {
		// TODO Auto-generated constructor stub
	}

	public InvoiceProductMap(Long invoiceProductMapId, Long productId, String productName, int noOfProduct,
			Double pricePerItem, Double totalPriceOfProduct, Invoice invoice) {
		super();
		this.invoiceProductMapId = invoiceProductMapId;
		this.productId = productId;
		this.productName = productName;
		this.noOfProduct = noOfProduct;
		this.pricePerItem = pricePerItem;
		this.totalPriceOfProduct = totalPriceOfProduct;
		this.invoice = invoice;
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

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	@Override
	public String toString() {
		return "InvoiceProductMap [invoiceProductMapId=" + invoiceProductMapId + ", productId=" + productId
				+ ", productName=" + productName + ", noOfProduct=" + noOfProduct + ", pricePerItem=" + pricePerItem
				+ ", totalPriceOfProduct=" + totalPriceOfProduct + ", invoice=" + invoice + "]";
	}

}
