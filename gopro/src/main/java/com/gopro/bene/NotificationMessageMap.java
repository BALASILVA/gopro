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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "notificationmessagemap")
public class NotificationMessageMap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notificationmessagemapid")
	private Long notificationMessageMapId;

	@Column(name = "sendfrom")
	private Long sendFrom;

	@Column(name = "message")
	private String message;

	@Column(name = "time")
	private Date time;

	@OneToMany(targetEntity = NotificationUserMap.class,cascade = { CascadeType.ALL })
	@JoinColumn(name="notificationmessagemapid", referencedColumnName = "notificationmessagemapid")
	private List<NotificationUserMap> notificationUserMap = new ArrayList<NotificationUserMap>();

	public NotificationMessageMap() {
	}

	public NotificationMessageMap(Long notificationMessageMapId, Long sendFrom, String message, Date time,
			List<NotificationUserMap> notificationUserMap) {
		super();
		this.notificationMessageMapId = notificationMessageMapId;
		this.sendFrom = sendFrom;
		this.message = message;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
		return "NotificationMessageMap [notificationMessageMapId=" + notificationMessageMapId + ", sendFrom=" + sendFrom
				+ ", message=" + message + ", time=" + time + ", notificationUserMap=" + notificationUserMap + "]";
	}

}
