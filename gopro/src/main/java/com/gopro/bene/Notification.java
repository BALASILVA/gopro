package com.gopro.bene;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "notification")
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notificationid")
	private Long notificationId;

	@Column(name = "shopid")
	private Long shopId;

	@Column(name = "shopname")
	private String shopName;

	@Column(name = "invoiceid")
	private Long invoiceId;

	@Column(name = "notificationtype")
	private String notificationType;

	@Column(name = "subject")
	private String Subject;

	@Column(name = "issystemgenerated")
	private boolean isSystemGenerated;

	@Column(name = "isdeleted")
	private boolean isDeleted;
	
	@Column (name ="notificationstartdata")
	private Date notificationStartData;
	
	@Column(name = "notificationlatupdatedate")
	private Date notificationLatUpdateDate;

	@OneToMany(targetEntity = NotificationMessageMap.class , cascade = { CascadeType.ALL })
	@JoinColumn(name="notificationid",referencedColumnName = "notificationid")
	private List<NotificationMessageMap> notificationMessageMap = new ArrayList<NotificationMessageMap>();

	public Notification() {
	}

	public Notification(Long notificationId, Long shopId, String shopName, Long invoiceId, String notificationType,
			String subject, boolean isSystemGenerated, boolean isDeleted, Date notificationStartData,
			Date notificationLatUpdateDate, List<NotificationMessageMap> notificationMessageMap) {
		super();
		this.notificationId = notificationId;
		this.shopId = shopId;
		this.shopName = shopName;
		this.invoiceId = invoiceId;
		this.notificationType = notificationType;
		Subject = subject;
		this.isSystemGenerated = isSystemGenerated;
		this.isDeleted = isDeleted;
		this.notificationStartData = notificationStartData;
		this.notificationLatUpdateDate = notificationLatUpdateDate;
		this.notificationMessageMap = notificationMessageMap;
	}

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
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

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	public boolean isSystemGenerated() {
		return isSystemGenerated;
	}

	public void setSystemGenerated(boolean isSystemGenerated) {
		this.isSystemGenerated = isSystemGenerated;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getNotificationStartData() {
		return notificationStartData;
	}

	public void setNotificationStartData(Date notificationStartData) {
		this.notificationStartData = notificationStartData;
	}

	public Date getNotificationLatUpdateDate() {
		return notificationLatUpdateDate;
	}

	public void setNotificationLatUpdateDate(Date notificationLatUpdateDate) {
		this.notificationLatUpdateDate = notificationLatUpdateDate;
	}

	public List<NotificationMessageMap> getNotificationMessageMap() {
		return notificationMessageMap;
	}

	public void setNotificationMessageMap(List<NotificationMessageMap> notificationMessageMap) {
		this.notificationMessageMap = notificationMessageMap;
	}

	@Override
	public String toString() {
		return "Notification [notificationId=" + notificationId + ", shopId=" + shopId + ", shopName=" + shopName
				+ ", invoiceId=" + invoiceId + ", notificationType=" + notificationType + ", Subject=" + Subject
				+ ", isSystemGenerated=" + isSystemGenerated + ", isDeleted=" + isDeleted + ", notificationStartData="
				+ notificationStartData + ", notificationLatUpdateDate=" + notificationLatUpdateDate
				+ ", notificationMessageMap=" + notificationMessageMap + "]";
	}


	

}
