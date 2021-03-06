package com.gopro.bene;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gopro.queryconstant.QueryConstant;

@SqlResultSetMapping(name = "findAllInvoiceMapping", classes = @ConstructorResult(targetClass = com.gopro.bene.Invoice.class, columns = {
		@ColumnResult(name = "invoiceId", type = Long.class),
		@ColumnResult(name = "customerMobileNo", type = Long.class),
		@ColumnResult(name = "noOfProduct", type = Integer.class),
		@ColumnResult(name = "totalPrice", type = Double.class),
		@ColumnResult(name = "paymentType", type = String.class), @ColumnResult(name = "date", type = Date.class),
		@ColumnResult(name = "shopId", type = Long.class), @ColumnResult(name = "userId", type = Long.class),
		@ColumnResult(name = "userName", type = String.class), @ColumnResult(name = "remarks", type = String.class) }))
@NamedNativeQuery(name = "findAllInvoiceMapping", query = QueryConstant.FIND_ALL_INVOICE_SEARCH, resultClass = Invoice.class, resultSetMapping = "findAllInvoiceMapping")

@SqlResultSetMapping(name = "getDefaultShopSalesListByWeek", classes = @ConstructorResult(targetClass = com.gopro.bene.Invoice.class, columns = {
		@ColumnResult(name = "totalPrice", type = Double.class),
		@ColumnResult(name = "date", type = Date.class)
		}))
@NamedNativeQuery(name = "getDefaultShopSalesListByWeek", query = QueryConstant.SALES_LIST_BY_WEEK_DEFAUL_SHOP, resultClass = Invoice.class, resultSetMapping = "getDefaultShopSalesListByWeek")

@SqlResultSetMapping(name = "getDefaultShopSalesListByMounth", classes = @ConstructorResult(targetClass = com.gopro.bene.Invoice.class, columns = {
		@ColumnResult(name = "totalPrice", type = Double.class),
		@ColumnResult(name = "date", type = Date.class)
		}))
@NamedNativeQuery(name = "getDefaultShopSalesListByMounth", query = QueryConstant.SALES_LIST_BY_MOUNTH_DEFAUL_SHOP, resultClass = Invoice.class, resultSetMapping = "getDefaultShopSalesListByMounth")
@Entity
@Table(name = "invoice")
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoiceid", nullable = false, updatable = false)
	private Long invoiceId;

	@Column(name = "customermobileno")
	private Long customerMobileNo;

	@OneToMany(targetEntity = InvoiceProductMap.class,cascade = { CascadeType.ALL })
	@JoinColumn(name="invoiceid",referencedColumnName = "invoiceid")
	private List<InvoiceProductMap> invoiceProductMap;

	@Column(name = "noofproduct")
	private int noOfProduct;

	@Column(name = "totalprice")
	private Double totalPrice;

	@Column(name = "paymenttype")
	private String paymentType;

	@Column(name = "isreprinted")
	private boolean isRePrinted;

	@Column(name = "nooftimereprinted")
	private int noOfTimeRePrinted;

	@Column(name = "date")
	private Date date;

	@Column(name = "shopid")
	private Long shopId;

	@Column(name = "userid")
	private Long userId;

	@Column(name = "username")
	private String userName;

	@Column(name = "remarks")
	private String remarks;
	
	private Long totalPriceBulkInvoice;
	
	//@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "customerid")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Customer customer;

	public Invoice() {
	}

	// Param constructor for native Query
	// Dont removed This constructor
	public Invoice(Long invoiceId, Long customerMobileNo, int noOfProduct, Double totalPrice, String paymentType,
			Date date, Long shopId, Long userId, String userName, String remarks) {
		super();
		this.invoiceId = invoiceId;
		this.customerMobileNo = customerMobileNo;
		this.noOfProduct = noOfProduct;
		this.totalPrice = totalPrice;
		this.paymentType = paymentType;
		this.date = date;
		this.shopId = shopId;
		this.userId = userId;
		this.userName = userName;
		this.remarks = remarks;
	}
	
	public Invoice(Double totalPrice, Date date) {
		this.totalPrice = totalPrice;
		this.date = date;
	}

	// Dont removed This constructor
	
	
	
	public Invoice(Long invoiceId, Long customerMobileNo, List<InvoiceProductMap> invoiceProductMap, int noOfProduct,
			Double totalPrice, String paymentType, boolean isRePrinted, int noOfTimeRePrinted, Date date, Long shopId,
			Long userId, String userName, String remarks, Long totalPriceBulkInvoice, Customer customer) {
		super();
		this.invoiceId = invoiceId;
		this.customerMobileNo = customerMobileNo;
		this.invoiceProductMap = invoiceProductMap;
		this.noOfProduct = noOfProduct;
		this.totalPrice = totalPrice;
		this.paymentType = paymentType;
		this.isRePrinted = isRePrinted;
		this.noOfTimeRePrinted = noOfTimeRePrinted;
		this.date = date;
		this.shopId = shopId;
		this.userId = userId;
		this.userName = userName;
		this.remarks = remarks;
		this.totalPriceBulkInvoice = totalPriceBulkInvoice;
		this.customer = customer;
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

	public List<InvoiceProductMap> getInvoiceProductMap() {
		return invoiceProductMap;
	}

	public void setInvoiceProductMap(List<InvoiceProductMap> invoiceProductMap) {
		this.invoiceProductMap = invoiceProductMap;
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

	public boolean isRePrinted() {
		return isRePrinted;
	}

	public void setRePrinted(boolean isRePrinted) {
		this.isRePrinted = isRePrinted;
	}

	public int getNoOfTimeRePrinted() {
		return noOfTimeRePrinted;
	}

	public void setNoOfTimeRePrinted(int noOfTimeRePrinted) {
		this.noOfTimeRePrinted = noOfTimeRePrinted;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Long getTotalPriceBulkInvoice() {
		return totalPriceBulkInvoice;
	}

	public void setTotalPriceBulkInvoice(Long totalPriceBulkInvoice) {
		this.totalPriceBulkInvoice = totalPriceBulkInvoice;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Invoice [" + (invoiceId != null ? "invoiceId=" + invoiceId + ", " : "")
				+ (customerMobileNo != null ? "customerMobileNo=" + customerMobileNo + ", " : "")
				+ (invoiceProductMap != null ? "invoiceProductMap=" + invoiceProductMap + ", " : "") + "noOfProduct="
				+ noOfProduct + ", " + (totalPrice != null ? "totalPrice=" + totalPrice + ", " : "")
				+ (paymentType != null ? "paymentType=" + paymentType + ", " : "") + "isRePrinted=" + isRePrinted
				+ ", noOfTimeRePrinted=" + noOfTimeRePrinted + ", " + (date != null ? "date=" + date + ", " : "")
				+ (shopId != null ? "shopId=" + shopId + ", " : "") + (userId != null ? "userId=" + userId + ", " : "")
				+ (userName != null ? "userName=" + userName + ", " : "")
				+ (remarks != null ? "remarks=" + remarks + ", " : "")
				+ (totalPriceBulkInvoice != null ? "totalPriceBulkInvoice=" + totalPriceBulkInvoice + ", " : "")
				+ (customer != null ? "customer=" + customer : "") + "]";
	}


	
	

	
}
