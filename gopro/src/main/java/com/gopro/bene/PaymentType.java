package com.gopro.bene;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "paymenttype")
public class PaymentType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false, name = "paymenttypeid")
	private Long paymentTypeId;

	@Column(name = "payment")
	private String payment;

	@Column(name = "ispaymentactive")
	private boolean isPaymentActive;

	public PaymentType() {
		// TODO Auto-generated constructor stub
	}

	public PaymentType(Long paymentTypeId, String payment, boolean isPaymentActive) {
		super();
		this.paymentTypeId = paymentTypeId;
		this.payment = payment;
		this.isPaymentActive = isPaymentActive;
	}

	public Long getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(Long paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public boolean isPaymentActive() {
		return isPaymentActive;
	}

	public void setPaymentActive(boolean isPaymentActive) {
		this.isPaymentActive = isPaymentActive;
	}

	@Override
	public String toString() {
		return "PaymentType [paymentTypeId=" + paymentTypeId + ", payment=" + payment + ", isPaymentActive="
				+ isPaymentActive + "]";
	}

}
