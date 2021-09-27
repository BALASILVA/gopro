package com.gopro.bene;

import java.util.Date;

public class DashBoardSuport {
	private Date date;
	private Long sales;
	
	public DashBoardSuport() {
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Long getSales() {
		return sales;
	}
	public void setSales(Long sales) {
		this.sales = sales;
	}
	@Override
	public String toString() {
		return "DashBoardSuport [" + (date != null ? "date=" + date + ", " : "")
				+ (sales != null ? "sales=" + sales : "") + "]";
	}
	
	
}
