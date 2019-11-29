package com.stockmarket.www.entity;

public class InterestView {
	private String stockname;
	private String price;
	private String percent;
	private int id;
	
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

	public InterestView(String stockname, String price, int id) {
		this.stockname = stockname;
		this.price = price;
		this.id = id;
	}

	public String getStockname() {
		return stockname;
	}
	public void setStockname(String stockname) {
		this.stockname = stockname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "InterestView [stockname=" + stockname + ", id=" + id + "]";
	}
}
