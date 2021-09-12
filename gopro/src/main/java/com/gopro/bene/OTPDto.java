package com.gopro.bene;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OTPDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "otpid")
	Long otpId;

	@Column(name = "username")
	String userName;

	@Column(name = "phoneno")
	String phoneNo;

	@Column(name = "email")
	String email;

	@Column(name = "otp")
	Long otp;

	@Column(name = "date")
	Date date;

	@Column(name = "issms")
	boolean isSms;

	@Column(name = "isemail")
	boolean isEmail;

	public OTPDto() {
	}

	public OTPDto(Long otpId, String userName, String phoneNo, String email, Long otp, Date date, boolean isSms,
			boolean isEmail) {
		super();
		this.otpId = otpId;
		this.userName = userName;
		this.phoneNo = phoneNo;
		this.email = email;
		this.otp = otp;
		this.date = date;
		this.isSms = isSms;
		this.isEmail = isEmail;
	}

	public Long getOtpId() {
		return otpId;
	}

	public void setOtpId(Long otpId) {
		this.otpId = otpId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getOtp() {
		return otp;
	}

	public void setOtp(Long otp) {
		this.otp = otp;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isSms() {
		return isSms;
	}

	public void setSms(boolean isSms) {
		this.isSms = isSms;
	}

	public boolean isEmail() {
		return isEmail;
	}

	public void setIsEmail(boolean isEmail) {
		this.isEmail = isEmail;
	}

	@Override
	public String toString() {
		return "OTPDto [" + (otpId != null ? "otpId=" + otpId + ", " : "")
				+ (userName != null ? "userName=" + userName + ", " : "")
				+ (phoneNo != null ? "phoneNo=" + phoneNo + ", " : "") + (email != null ? "email=" + email + ", " : "")
				+ (otp != null ? "otp=" + otp + ", " : "") + (date != null ? "date=" + date + ", " : "") + "isSms="
				+ isSms + ", isEmail=" + isEmail + "]";
	}

}
