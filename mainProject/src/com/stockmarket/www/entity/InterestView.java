package com.stockmarket.www.entity;

public class InterestView {
	private String stockname;
	private int id;
	
	public InterestView() {
		// TODO Auto-generated constructor stub
	}
	
	public InterestView(String stockname) {
		this.stockname = stockname;
	}
	
	public InterestView(String stockname, int id) {
		this.stockname = stockname;
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
