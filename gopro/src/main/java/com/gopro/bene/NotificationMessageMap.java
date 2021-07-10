package com.gopro.bene;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "notificationmessagemap")
public class NotificationMessageMap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notificationmessagemapid", nullable = false)
	private Long notificationMessageMapId;

	@Column(name = "sendfrom", nullable = false)
	private Long sendFrom;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "message")
	private String message;

	// Column used for map message to user in Reply Or Reply all action
	@Column(name = "isreplyorreplyallaction", columnDefinition = "boolean default false", nullable = false)
	private boolean isReplyOrReplyAllAction;

	@Column(name = "notificationid")
	private Long notificationId;

	@Column(name = "time", nullable = false)
	private Date time;

	@OneToMany(targetEntity = NotificationUserMap.class, cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "notificationmessagemapid", referencedColumnName = "notificationmessagemapid")
	private List<NotificationUserMap> notificationUserMap = new ArrayList<NotificationUserMap>();

	public NotificationMessageMap() {
	}

	public NotificationMessageMap(Long notificationMessageMapId, Long sendFrom, String firstName, String lastName,
			String message, boolean isReplyOrReplyAllAction, Long notificationId, Date time,
			List<NotificationUserMap> notificationUserMap) {
		super();
		this.notificationMessageMapId = notificationMessageMapId;
		this.sendFrom = sendFrom;
		this.firstName = firstName;
		this.lastName = lastName;
		this.message = message;
		this.isReplyOrReplyAllAction = isReplyOrReplyAllAction;
		this.notificationId = notificationId;
		this.time = time;
		this.notificationUserMap = notificationUserMap;
	}

	public Long getNotificationMessageMapId() {
		return notificationMessageMapId;
	}

	public void setNotificationMessageMapId(Long notificationMessageMapId) {
		this.notificationMessageMapId = notificationMessageMapId;
	}

	public Long getSendFrom() {
		return sendFrom;
	}

	public void setSendFrom(Long sendFrom) {
		this.sendFrom = sendFrom;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isReplyOrReplyAllAction() {
		return isReplyOrReplyAllAction;
	}

	public void setReplyOrReplyAllAction(boolean isReplyOrReplyAllAction) {
		this.isReplyOrReplyAllAction = isReplyOrReplyAllAction;
	}

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public List<NotificationUserMap> getNotificationUserMap() {
		return notificationUserMap;
	}

	public void setNotificationUserMap(List<NotificationUserMap> notificationUserMap) {
		this.notificationUserMap = notificationUserMap;
	}

	@Override
	public String toString() {
		return "NotificationMessageMap ["
				+ (notificationMessageMapId != null ? "notificationMessageMapId=" + notificationMessageMapId + ", "
						: "")
				+ (sendFrom != null ? "sendFrom=" + sendFrom + ", " : "")
				+ (firstName != null ? "firstName=" + firstName + ", " : "")
				+ (lastName != null ? "lastName=" + lastName + ", " : "")
				+ (message != null ? "message=" + message + ", " : "") + "isReplyOrReplyAllAction="
				+ isReplyOrReplyAllAction + ", "
				+ (notificationId != null ? "notificationId=" + notificationId + ", " : "")
				+ (time != null ? "time=" + time + ", " : "")
				+ (notificationUserMap != null ? "notificationUserMap=" + notificationUserMap : "") + "]";
	}

}
