package com.gopro.bene;

import java.util.List;

public class DashBoard {

	private String defaultShopName;
	private Long defaultShopTotalSales;
	private Long allShopTotalSales;
	private List<Invoice> defaultShopSalesListWeek;
	private List<Invoice> defaultShopSalesListMounth;
	private List<Invoice> allShopsSalesList;

	public DashBoard() {
	}

	public DashBoard(String defaultShopName, Long defaultShopTotalSales, Long allShopTotalSales,
			List<Invoice> defaultShopSalesListWeek, List<Invoice> defaultShopSalesListMounth,
			List<Invoice> allShopsSalesList) {
		super();
		this.defaultShopName = defaultShopName;
		this.defaultShopTotalSales = defaultShopTotalSales;
		this.allShopTotalSales = allShopTotalSales;
		this.defaultShopSalesListWeek = defaultShopSalesListWeek;
		this.defaultShopSalesListMounth = defaultShopSalesListMounth;
		this.allShopsSalesList = allShopsSalesList;
	}

	public String getDefaultShopName() {
		return defaultShopName;
	}

	public void setDefaultShopName(String defaultShopName) {
		this.defaultShopName = defaultShopName;
	}

	public Long getDefaultShopTotalSales() {
		return defaultShopTotalSales;
	}

	public void setDefaultShopTotalSales(Long defaultShopTotalSales) {
		this.defaultShopTotalSales = defaultShopTotalSales;
	}

	public Long getAllShopTotalSales() {
		return allShopTotalSales;
	}

	public void setAllShopTotalSales(Long allShopTotalSales) {
		this.allShopTotalSales = allShopTotalSales;
	}

	public List<Invoice> getDefaultShopSalesListWeek() {
		return defaultShopSalesListWeek;
	}

	public void setDefaultShopSalesListWeek(List<Invoice> defaultShopSalesListWeek) {
		this.defaultShopSalesListWeek = defaultShopSalesListWeek;
	}

	public List<Invoice> getDefaultShopSalesListMounth() {
		return defaultShopSalesListMounth;
	}

	public void setDefaultShopSalesListMounth(List<Invoice> defaultShopSalesListMounth) {
		this.defaultShopSalesListMounth = defaultShopSalesListMounth;
	}

	public List<Invoice> getAllShopsSalesList() {
		return allShopsSalesList;
	}

	public void setAllShopsSalesList(List<Invoice> allShopsSalesList) {
		this.allShopsSalesList = allShopsSalesList;
	}

	@Override
	public String toString() {
		return "DashBoard [" + (defaultShopName != null ? "defaultShopName=" + defaultShopName + ", " : "")
				+ (defaultShopTotalSales != null ? "defaultShopTotalSales=" + defaultShopTotalSales + ", " : "")
				+ (allShopTotalSales != null ? "allShopTotalSales=" + allShopTotalSales + ", " : "")
				+ (defaultShopSalesListWeek != null ? "defaultShopSalesListWeek=" + defaultShopSalesListWeek + ", "
						: "")
				+ (defaultShopSalesListMounth != null
						? "defaultShopSalesListMounth=" + defaultShopSalesListMounth + ", "
						: "")
				+ (allShopsSalesList != null ? "allShopsSalesList=" + allShopsSalesList : "") + "]";
	}

}
