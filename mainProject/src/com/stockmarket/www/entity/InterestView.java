package com.stockmarket.www.entity;

public class InterestView {
	private String stockname;
	private String price;
	private String percent;
	
	public InterestView() {
		// TODO Auto-generated constructor stub
	}
	
	public InterestView(String stockname) {
		this.stockname = stockname;
	}
	
	public InterestView(String stockname, String price, String percent) {
		this.stockname = stockname;
		this.price = price;
		this.percent = percent;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public InterestView(String stockname, String price) {
		this.stockname = stockname;
		this.price = price;
	}

	public String getStockname() {
		return stockname;
	}
	public void setStockname(String stockname) {
		this.stockname = stockname;
	}
	@Override
	public String toString() {
		return "InterestView [stockname=" + stockname+ "]";
	}
}
