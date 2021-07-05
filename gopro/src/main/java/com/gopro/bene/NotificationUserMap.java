package com.gopro.bene;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "notificationusermap")
public class NotificationUserMap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notificationusermapid")
	private Long notificationUserMapId;

	@Column(name = "sendto")
	private Long sendTo;

	@Column(name = "mappingtype")
	private String mappingType; // CC or To or Bcc

	@Column(name = "isfavorite")
	private boolean isFavorite;

	@Column(name = "isdeleted")
	private boolean isDeleted;

	@Column(name = "isreaded")
	private boolean isReaded;

	@Column(name = "readtime")
	private Date readTime;

	public NotificationUserMap() {
		// TODO Auto-generated constructor stub
	}

	public NotificationUserMap(Long notificationUserMapId, Long sendTo, String mappingType, boolean isFavorite,
			boolean isDeleted, boolean isReaded, Date readTime) {
		super();
		this.notificationUserMapId = notificationUserMapId;
		this.sendTo = sendTo;
		this.mappingType = mappingType;
		this.isFavorite = isFavorite;
		this.isDeleted = isDeleted;
		this.isReaded = isReaded;
		this.readTime = readTime;
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

	@Override
	public String toString() {
		return "NotificationUserMap [notificationUserMapId=" + notificationUserMapId + ", sendTo=" + sendTo
				+ ", mappingType=" + mappingType + ", isFavorite=" + isFavorite + ", isDeleted=" + isDeleted
				+ ", isReaded=" + isReaded + ", readTime=" + readTime + "]";
	}

}
