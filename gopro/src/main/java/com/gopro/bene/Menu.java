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

import org.springframework.lang.NonNull;

@Entity
@Table(name = "menu")
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "menuid")
	private Long menuId;

	@NonNull
	@Column(name = "menuqueue", unique = true)
	private int menuQueue;

	@NonNull
	@Column(name = "menuname")
	private String menuName;

	@NonNull
	@Column(name = "menuiconname")
	private String menuIconName;

	@Column(name = "menuiconcolour")
	private String menuIconColour;

	@Column(name = "menutextcolour")
	private String menuTextColour;

	@NonNull
	@Column(name = "link")
	private String link;

	@NonNull
	@Column(name = "haslink")
	private boolean hasLink;

	@NonNull
	@Column(name = "haschildmenu")
	private boolean hasChildMenu;

	@Column(name = "isopendefault")
	private boolean isOpenDefault; // Open child by default

	// rigth button is button in right side of menu EG: Compose Button
	@NonNull
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
	private boolean isActive;

	@Column(name = "createddate")
	private Date createdDate;

	@OneToMany(targetEntity = ChildMenu.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "menuId", referencedColumnName = "menuId")
	List<ChildMenu> childMenuList = new ArrayList<ChildMenu>();

	public Menu() {
	}

	public Menu(Long menuId, int menuQueue, String menuName, String menuIconName, String menuIconColour,
			String menuTextColour, String link, boolean hasLink, boolean hasChildMenu, boolean isOpenDefault,
			boolean hasRightButton, String rightButtonLink, String rightButtonName, String rightButtonIconName,
			String rightButtonIconColour, boolean isActive, Date createdDate, List<ChildMenu> childMenuList) {
		super();
		this.menuId = menuId;
		this.menuQueue = menuQueue;
		this.menuName = menuName;
		this.menuIconName = menuIconName;
		this.menuIconColour = menuIconColour;
		this.menuTextColour = menuTextColour;
		this.link = link;
		this.hasLink = hasLink;
		this.hasChildMenu = hasChildMenu;
		this.isOpenDefault = isOpenDefault;
		this.hasRightButton = hasRightButton;
		this.rightButtonLink = rightButtonLink;
		this.rightButtonName = rightButtonName;
		this.rightButtonIconName = rightButtonIconName;
		this.rightButtonIconColour = rightButtonIconColour;
		this.isActive = isActive;
		this.createdDate = createdDate;
		this.childMenuList = childMenuList;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public int getMenuQueue() {
		return menuQueue;
	}

	public void setMenuQueue(int menuQueue) {
		this.menuQueue = menuQueue;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuIconName() {
		return menuIconName;
	}

	public void setMenuIconName(String menuIconName) {
		this.menuIconName = menuIconName;
	}

	public String getMenuIconColour() {
		return menuIconColour;
	}

	public void setMenuIconColour(String menuIconColour) {
		this.menuIconColour = menuIconColour;
	}

	public String getMenuTextColour() {
		return menuTextColour;
	}

	public void setMenuTextColour(String menuTextColour) {
		this.menuTextColour = menuTextColour;
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

	public boolean isHasChildMenu() {
		return hasChildMenu;
	}

	public void setHasChildMenu(boolean hasChildMenu) {
		this.hasChildMenu = hasChildMenu;
	}

	public boolean isOpenDefault() {
		return isOpenDefault;
	}

	public void setOpenDefault(boolean isOpenDefault) {
		this.isOpenDefault = isOpenDefault;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<ChildMenu> getChildMenuList() {
		return childMenuList;
	}

	public void setChildMenuList(List<ChildMenu> childMenuList) {
		this.childMenuList = childMenuList;
	}

	@Override
	public String toString() {
		return "Menu [" + (menuId != null ? "menuId=" + menuId + ", " : "") + "menuQueue=" + menuQueue + ", "
				+ (menuName != null ? "menuName=" + menuName + ", " : "")
				+ (menuIconName != null ? "menuIconName=" + menuIconName + ", " : "")
				+ (menuIconColour != null ? "menuIconColour=" + menuIconColour + ", " : "")
				+ (menuTextColour != null ? "menuTextColour=" + menuTextColour + ", " : "")
				+ (link != null ? "link=" + link + ", " : "") + "hasLink=" + hasLink + ", hasChildMenu=" + hasChildMenu
				+ ", isOpenDefault=" + isOpenDefault + ", hasRightButton=" + hasRightButton + ", "
				+ (rightButtonLink != null ? "rightButtonLink=" + rightButtonLink + ", " : "")
				+ (rightButtonName != null ? "rightButtonName=" + rightButtonName + ", " : "")
				+ (rightButtonIconName != null ? "rightButtonIconName=" + rightButtonIconName + ", " : "")
				+ (rightButtonIconColour != null ? "rightButtonIconColour=" + rightButtonIconColour + ", " : "")
				+ "isActive=" + isActive + ", " + (childMenuList != null ? "childMenuList=" + childMenuList : "") + "]";
	}

}
