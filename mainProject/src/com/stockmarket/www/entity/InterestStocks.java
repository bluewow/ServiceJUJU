package com.stockmarket.www.entity;

public class InterestStocks {
	private int id;
	private String stockName;
	private int currentPrice;
	private int quantity;
	public InterestStocks() {
		
	}
	public InterestStocks(String stockName) {
	this.stockName = stockName;
	}
	public InterestStocks(int id, String stockName, int currentPrice, int quantity) {
		this.id = id;
		this.stockName = stockName;
		this.currentPrice = currentPrice;
		this.quantity = quantity;
	}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public int getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(int currentPrice) {
		this.currentPrice = currentPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "InterestStocks [id=" + id + ", stockName=" + stockName + ", currentPrice=" + currentPrice
				+ ", quantity=" + quantity + "]";
	}

}
