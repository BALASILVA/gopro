package com.gopro.bene;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customerid")
	private Long customerId;

	@Column(name = "customermobileno")
	private Long customerMobileNo;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "countrycode")
	private String countryCode;

	@Column(name = "remarks")
	private String remarks;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(Long customerId, Long customerMobileNo, String name, String email, String countryCode,
			String remarks) {
		super();
		this.customerId = customerId;
		this.customerMobileNo = customerMobileNo;
		this.name = name;
		this.email = email;
		this.countryCode = countryCode;
		this.remarks = remarks;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getCustomerMobileNo() {
		return customerMobileNo;
	}

	public void setCustomerMobileNo(Long customerMobileNo) {
		this.customerMobileNo = customerMobileNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerMobileNo=" + customerMobileNo + ", name=" + name
				+ ", email=" + email + ", countryCode=" + countryCode + ", remarks=" + remarks + "]";
	}

}
