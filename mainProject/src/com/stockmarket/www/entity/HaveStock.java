package com.stockmarket.www.entity;

public class HaveStock {
	
	private int memberId;
	private String stockId;
	private int quantuty;
	
	public HaveStock() {
		// TODO Auto-generated constructor stub
	}
	
	public HaveStock(int memberId, String stockId, int quantuty) {
		this.memberId = memberId;
		this.stockId = stockId;
		this.quantuty = quantuty;
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
	public int getQuantuty() {
		return quantuty;
	}
	public void setQuantuty(int quantuty) {
		this.quantuty = quantuty;
	}
	
	@Override
	public String toString() {
		return "HaveStock [memberId=" + memberId + ", stockId=" + stockId + ", quantuty=" + quantuty + "]";
	}
	
}
