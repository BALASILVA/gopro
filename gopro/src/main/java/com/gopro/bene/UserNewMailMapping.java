package com.gopro.bene;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "usernewmailmapping")
public class UserNewMailMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_seq_generator")
	@SequenceGenerator(name = "user_seq_generator", sequenceName = "user_seq", allocationSize = 1, initialValue = 1)
	@Column(name = "id", nullable = false, updatable = false)
	private Long userNewMailMappingId;

	@NonNull
	@Column(name = "userid",nullable = false)
	private String userId;

	@NonNull
	@Column(name = "username",nullable = false)
	private String userName;

	@Column(name = " hasnewmail", columnDefinition = "boolean default false",nullable = false)
	private boolean hasNewMail;

	@Column(name = " hasnewreprintmail", columnDefinition = "boolean default false",nullable = false)
	private boolean hasNewRePrintMail;

	@Column(name = "hasnewdraftmail", columnDefinition = "boolean default false",nullable = false)
	private boolean hasNewDraftMail;

	
	@Column(name = "updatetime",nullable = false)
	private Date updateTime;

	public UserNewMailMapping(Long userNewMailMappingId, String userId, String userName, boolean hasNewMail,
			boolean hasNewRePrintMail, boolean hasNewDraftMail, Date updateTime) {
		super();
		this.userNewMailMappingId = userNewMailMappingId;
		this.userId = userId;
		this.userName = userName;
		this.hasNewMail = hasNewMail;
		this.hasNewRePrintMail = hasNewRePrintMail;
		this.hasNewDraftMail = hasNewDraftMail;
		this.updateTime = updateTime;
	}

	public Long getUserNewMailMappingId() {
		return userNewMailMappingId;
	}

	public void setUserNewMailMappingId(Long userNewMailMappingId) {
		this.userNewMailMappingId = userNewMailMappingId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isHasNewMail() {
		return hasNewMail;
	}

	public void setHasNewMail(boolean hasNewMail) {
		this.hasNewMail = hasNewMail;
	}

	public boolean isHasNewRePrintMail() {
		return hasNewRePrintMail;
	}

	public void setHasNewRePrintMail(boolean hasNewRePrintMail) {
		this.hasNewRePrintMail = hasNewRePrintMail;
	}

	public boolean isHasNewDraftMail() {
		return hasNewDraftMail;
	}

	public void setHasNewDraftMail(boolean hasNewDraftMail) {
		this.hasNewDraftMail = hasNewDraftMail;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "UserNewMailMapping ["
				+ (userNewMailMappingId != null ? "userNewMailMappingId=" + userNewMailMappingId + ", " : "")
				+ (userId != null ? "userId=" + userId + ", " : "")
				+ (userName != null ? "userName=" + userName + ", " : "") + "hasNewMail=" + hasNewMail
				+ ", hasNewRePrintMail=" + hasNewRePrintMail + ", hasNewDraftMail=" + hasNewDraftMail + ", "
				+ (updateTime != null ? "updateTime=" + updateTime : "") + "]";
	}

}
