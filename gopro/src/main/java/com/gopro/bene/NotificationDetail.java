package com.gopro.bene;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notificationdetail")
public class NotificationDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notificationdetailid", nullable = false)
	private Long notificationDetailId;

	@Column(name = "userid", nullable = false)
	private Long userId;

	@Column(name = "isimportant", columnDefinition = "boolean default false", nullable = false)
	private boolean isImportant;

	@Column(name = "isfavorite", columnDefinition = "boolean default false", nullable = false)
	private boolean isFavorite;

	@Column(name = "notificationid")
	private Long notificationId;

	public NotificationDetail() {
	}

	public NotificationDetail(Long notificationDetailId, Long userId, boolean isImportant, boolean isFavorite,
			Long notificationId) {
		this.notificationDetailId = notificationDetailId;
		this.userId = userId;
		this.isImportant = isImportant;
		this.isFavorite = isFavorite;
		this.notificationId = notificationId;
	}

	public Long getNotificationDetailId() {
		return notificationDetailId;
	}

	public void setNotificationDetailId(Long notificationDetailId) {
		this.notificationDetailId = notificationDetailId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean getIsImportant() {
		return isImportant;
	}

	public void setIsImportant(boolean isImportant) {
		this.isImportant = isImportant;
	}

	public boolean getIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	@Override
	public String toString() {
		return "NotificationDetail ["
				+ (notificationDetailId != null ? "notificationDetailId=" + notificationDetailId + ", " : "")
				+ (userId != null ? "userId=" + userId + ", " : "") + "isImportant=" + isImportant + ", isFavorite="
				+ isFavorite + ", " + (notificationId != null ? "notificationId=" + notificationId : "") + "]";
	}

}
