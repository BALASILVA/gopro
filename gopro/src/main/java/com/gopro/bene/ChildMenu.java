package com.gopro.bene;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "childmenu")
public class ChildMenu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "childmenuid")
	private Long childMenuId;

	@NonNull
	@Column(name = "childmenuqueue")
	private int childMenuQueue;

	@NonNull
	@Column(name = "childmenuname")
	private String childMenuName;

	@NonNull
	@Column(name = "childmenuiconname")
	private String childMenuIconName;

	@Column(name = "childmenutextcolour")
	private String childMenuTextColour;

	@Column(name = "childmenuiconcolour")
	private String childMenuIconColour;

	@NonNull
	@Column(name = "link")
	private String link;

	@NonNull
	@Column(name = "haslink")
	private boolean hasLink;

	// rigth button is button in right side of menu EG: Compose Button
	@Column(name = "hasrightbutton")
	private boolean hasRightButton;

	@Column(name = "rightbuttonlink")
	private String rightButtonLink;

	@Column(name = "rightbuttonname")
	private String rightButtonName;

	@Column(name = "rightbuttoniconname")
	private String rightButtonIconName;

	@Column(name = "rightbuttoniconcolour")
	private String rightButtonIconColour;

	@NonNull
	@Column(name = "isactive")
	private String isActive;

	@Column(name = "createddate")
	private Date createdDate;

	public ChildMenu() {
	}

	public ChildMenu(Long childMenuId, int childMenuQueue, String childMenuName, String childMenuIconName,
			String childMenuTextColour, String childMenuIconColour, String link, boolean hasLink,
			boolean hasRightButton, String rightButtonLink, String rightButtonName, String rightButtonIconName,
			String rightButtonIconColour, String isActive, Date createdDate) {
		super();
		this.childMenuId = childMenuId;
		this.childMenuQueue = childMenuQueue;
		this.childMenuName = childMenuName;
		this.childMenuIconName = childMenuIconName;
		this.childMenuTextColour = childMenuTextColour;
		this.childMenuIconColour = childMenuIconColour;
		this.link = link;
		this.hasLink = hasLink;
		this.hasRightButton = hasRightButton;
		this.rightButtonLink = rightButtonLink;
		this.rightButtonName = rightButtonName;
		this.rightButtonIconName = rightButtonIconName;
		this.rightButtonIconColour = rightButtonIconColour;
		this.isActive = isActive;
		this.createdDate = createdDate;
	}

	public Long getChildMenuId() {
		return childMenuId;
	}

	public void setChildMenuId(Long childMenuId) {
		this.childMenuId = childMenuId;
	}

	public int getChildMenuQueue() {
		return childMenuQueue;
	}

	public void setChildMenuQueue(int childMenuQueue) {
		this.childMenuQueue = childMenuQueue;
	}

	public String getChildMenuName() {
		return childMenuName;
	}

	public void setChildMenuName(String childMenuName) {
		this.childMenuName = childMenuName;
	}

	public String getChildMenuIconName() {
		return childMenuIconName;
	}

	public void setChildMenuIconName(String childMenuIconName) {
		this.childMenuIconName = childMenuIconName;
	}

	public String getChildMenuTextColour() {
		return childMenuTextColour;
	}

	public void setChildMenuTextColour(String childMenuTextColour) {
		this.childMenuTextColour = childMenuTextColour;
	}

	public String getChildMenuIconColour() {
		return childMenuIconColour;
	}

	public void setChildMenuIconColour(String childMenuIconColour) {
		this.childMenuIconColour = childMenuIconColour;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public boolean isHasLink() {
		return hasLink;
	}

	public void setHasLink(boolean hasLink) {
		this.hasLink = hasLink;
	}

	public boolean isHasRightButton() {
		return hasRightButton;
	}

	public void setHasRightButton(boolean hasRightButton) {
		this.hasRightButton = hasRightButton;
	}

	public String getRightButtonLink() {
		return rightButtonLink;
	}

	public void setRightButtonLink(String rightButtonLink) {
		this.rightButtonLink = rightButtonLink;
	}

	public String getRightButtonName() {
		return rightButtonName;
	}

	public void setRightButtonName(String rightButtonName) {
		this.rightButtonName = rightButtonName;
	}

	public String getRightButtonIconName() {
		return rightButtonIconName;
	}

	public void setRightButtonIconName(String rightButtonIconName) {
		this.rightButtonIconName = rightButtonIconName;
	}

	public String getRightButtonIconColour() {
		return rightButtonIconColour;
	}

	public void setRightButtonIconColour(String rightButtonIconColour) {
		this.rightButtonIconColour = rightButtonIconColour;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "ChildMenu [" + (childMenuId != null ? "childMenuId=" + childMenuId + ", " : "") + "childMenuQueue="
				+ childMenuQueue + ", " + (childMenuName != null ? "childMenuName=" + childMenuName + ", " : "")
				+ (childMenuIconName != null ? "childMenuIconName=" + childMenuIconName + ", " : "")
				+ (childMenuTextColour != null ? "childMenuTextColour=" + childMenuTextColour + ", " : "")
				+ (childMenuIconColour != null ? "childMenuIconColour=" + childMenuIconColour + ", " : "")
				+ (link != null ? "link=" + link + ", " : "") + "hasLink=" + hasLink + ", hasRightButton="
				+ hasRightButton + ", " + (rightButtonLink != null ? "rightButtonLink=" + rightButtonLink + ", " : "")
				+ (rightButtonName != null ? "rightButtonName=" + rightButtonName + ", " : "")
				+ (rightButtonIconName != null ? "rightButtonIconName=" + rightButtonIconName + ", " : "")
				+ (rightButtonIconColour != null ? "rightButtonIconColour=" + rightButtonIconColour + ", " : "")
				+ (isActive != null ? "isActive=" + isActive : "") + "]";
	}



}
