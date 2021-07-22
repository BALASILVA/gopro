package com.gopro.bene;

import java.util.ArrayList;
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
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.gopro.queryconstant.QueryConstant;

@SqlResultSetMapping(name = "findAllNotification", classes = @ConstructorResult(targetClass = com.gopro.bene.Notification.class, columns = {
		@ColumnResult(name = "notificationId", type = Long.class),
		@ColumnResult(name = "notificationType", type = String.class),
		@ColumnResult(name = "Subject", type = String.class),
		@ColumnResult(name = "notificationStartData", type = Date.class),
		@ColumnResult(name = "notificationLatUpdateDate", type = Date.class) }))
// Need to create one more filed constructor with above fields
@NamedNativeQuery(name = "findAllNotification", query = QueryConstant.FIND_ALL_NOTIFICATION_SEARCH, resultClass = Notification.class, resultSetMapping = "findAllNotification")

@SqlResultSetMapping(name = "findNotificationById", classes = @ConstructorResult(targetClass = com.gopro.bene.Notification.class, columns = {
		@ColumnResult(name = "notificationId", type = Long.class),
		@ColumnResult(name = "notificationType", type = String.class),
		@ColumnResult(name = "Subject", type = String.class),
		@ColumnResult(name = "notificationStartData", type = Date.class),
		@ColumnResult(name = "notificationLatUpdateDate", type = Date.class) }))
// Need to create one more filed constructor with above fields
@NamedNativeQuery(name = "findNotificationById", query = QueryConstant.FIND_NOTIFICATION_NOTIFICATIONID , resultClass = Notification.class, resultSetMapping = "findNotificationById")

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
	
	private int count;

	@Column(name = "notificationstartdata")
	private Date notificationStartData;

	@Column(name = "notificationlatupdatedate")
	private Date notificationLatUpdateDate;

	@OneToMany(targetEntity = NotificationMessageMap.class, cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "notificationid", referencedColumnName = "notificationid")
	private List<NotificationMessageMap> notificationMessageMap = new ArrayList<NotificationMessageMap>();

	@OneToMany(targetEntity = NotificationDetail.class, cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "notificationid", referencedColumnName = "notificationid")
	private List<NotificationDetail> notificationDetail = new ArrayList<NotificationDetail>();

	public Notification() {
	}

	// Dont remove This construct
	// Customise constrictor For JPA Query START
	public Notification(Long notificationId, String notificationType, String subject, Date notificationStartData,
			Date notificationLatUpdateDate) {
		this.notificationId = notificationId;
		this.notificationType = notificationType;
		this.Subject = subject;
		this.notificationStartData = notificationStartData;
		this.notificationLatUpdateDate = notificationLatUpdateDate;
	}
	// Customise constrictor For JPA Query END

	public Notification(Long notificationId, Long shopId, String shopName, Long invoiceId, String notificationType,
			String subject, boolean isSystemGenerated, boolean isDeleted, int count, Date notificationStartData,
			Date notificationLatUpdateDate, List<NotificationMessageMap> notificationMessageMap,
			List<NotificationDetail> notificationDetail) {
		super();
		this.notificationId = notificationId;
		this.shopId = shopId;
		this.shopName = shopName;
		this.invoiceId = invoiceId;
		this.notificationType = notificationType;
		Subject = subject;
		this.isSystemGenerated = isSystemGenerated;
		this.isDeleted = isDeleted;
		this.count = count;
		this.notificationStartData = notificationStartData;
		this.notificationLatUpdateDate = notificationLatUpdateDate;
		this.notificationMessageMap = notificationMessageMap;
		this.notificationDetail = notificationDetail;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

	public List<NotificationDetail> getNotificationDetail() {
		return notificationDetail;
	}

	public void setNotificationDetail(List<NotificationDetail> notificationDetail) {
		this.notificationDetail = notificationDetail;
	}

	@Override
	public String toString() {
		return "Notification [" + (notificationId != null ? "notificationId=" + notificationId + ", " : "")
				+ (shopId != null ? "shopId=" + shopId + ", " : "")
				+ (shopName != null ? "shopName=" + shopName + ", " : "")
				+ (invoiceId != null ? "invoiceId=" + invoiceId + ", " : "")
				+ (notificationType != null ? "notificationType=" + notificationType + ", " : "")
				+ (Subject != null ? "Subject=" + Subject + ", " : "") + "isSystemGenerated=" + isSystemGenerated
				+ ", isDeleted=" + isDeleted + ", count=" + count + ", "
				+ (notificationStartData != null ? "notificationStartData=" + notificationStartData + ", " : "")
				+ (notificationLatUpdateDate != null ? "notificationLatUpdateDate=" + notificationLatUpdateDate + ", "
						: "")
				+ (notificationMessageMap != null ? "notificationMessageMap=" + notificationMessageMap + ", " : "")
				+ (notificationDetail != null ? "notificationDetail=" + notificationDetail : "") + "]";
	}


}
