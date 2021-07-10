package com.gopro.bene;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notificationusermap")
public class NotificationUserMap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notificationusermapid", nullable = false)
	private Long notificationUserMapId;

	@Column(name = "sendto")
	private Long sendTo;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	// CC or To or Bcc
	@Column(name = "mappingtype", nullable = false)
	private String mappingType; 
	
	@Column(name = "isfavorite")
	private boolean isFavorite;

	@Column(name = "isdeleted")
	private boolean isDeleted;

	@Column(name = "isreaded")
	private boolean isReaded;

	@Column(name = "readtime")
	private Date readTime;

	@Column(name = "notificationmessagemapid")
	private Long notificationMessageMapId;

	public NotificationUserMap() {
		// TODO Auto-generated constructor stub
	}

	public NotificationUserMap(Long notificationUserMapId, Long sendTo, String firstName, String lastName,
			String mappingType, boolean isFavorite, boolean isDeleted, boolean isReaded, Date readTime,
			Long notificationMessageMapId) {
		super();
		this.notificationUserMapId = notificationUserMapId;
		this.sendTo = sendTo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mappingType = mappingType;
		this.isFavorite = isFavorite;
		this.isDeleted = isDeleted;
		this.isReaded = isReaded;
		this.readTime = readTime;
		this.notificationMessageMapId = notificationMessageMapId;
	}

	public Long getNotificationUserMapId() {
		return notificationUserMapId;
	}

	public void setNotificationUserMapId(Long notificationUserMapId) {
		this.notificationUserMapId = notificationUserMapId;
	}

	public Long getSendTo() {
		return sendTo;
	}

	public void setSendTo(Long sendTo) {
		this.sendTo = sendTo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMappingType() {
		return mappingType;
	}

	public void setMappingType(String mappingType) {
		this.mappingType = mappingType;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public boolean isReaded() {
		return isReaded;
	}

	public void setReaded(boolean isReaded) {
		this.isReaded = isReaded;
	}

	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

	public Long getNotificationMessageMapId() {
		return notificationMessageMapId;
	}

	public void setNotificationMessageMapId(Long notificationMessageMapId) {
		this.notificationMessageMapId = notificationMessageMapId;
	}

	@Override
	public String toString() {
		return "NotificationUserMap ["
				+ (notificationUserMapId != null ? "notificationUserMapId=" + notificationUserMapId + ", " : "")
				+ (sendTo != null ? "sendTo=" + sendTo + ", " : "")
				+ (firstName != null ? "firstName=" + firstName + ", " : "")
				+ (lastName != null ? "lastName=" + lastName + ", " : "")
				+ (mappingType != null ? "mappingType=" + mappingType + ", " : "") + "isFavorite=" + isFavorite
				+ ", isDeleted=" + isDeleted + ", isReaded=" + isReaded + ", "
				+ (readTime != null ? "readTime=" + readTime + ", " : "")
				+ (notificationMessageMapId != null ? "notificationMessageMapId=" + notificationMessageMapId : "")
				+ "]";
	}

}
