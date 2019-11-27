package com.stockmarket.www.entity;

public class HaveStock {
	
	private int memberId;
	private String stockId;
	private int quantity;
	private int avg;
	
	public HaveStock() {
		// TODO Auto-generated constructor stub
	}
	
	public HaveStock(int memberId, String stockId, int quantity, int avg) {
		super();
		this.memberId = memberId;
		this.stockId = stockId;
		this.quantity = quantity;
		this.avg = avg;
	}


	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantuty) {
		this.quantity = quantuty;
	}
	
	public int getAvg() {
		return avg;
	}

	public void setAvg(int avg) {
		this.avg = avg;
	}

	@Override
	public String toString() {
		return "HaveStock [memberId=" + memberId + ", stockId=" + stockId + ", quantity=" + quantity + ", avg=" + avg
				+ "]";
	}
}
