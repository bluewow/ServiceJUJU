package com.stockmarket.www.entity;

public class HaveStockView extends HaveStock {
	
	private String stockName;
	private String price;
	private int income ;
	private String gain;
	private String percent;
	
	public HaveStockView() {
		// TODO Auto-generated constructor stub
	}
	
	public HaveStockView(int memberId, String stockId, int quantity, int sum, String stockName, String price,
			String gain, String percent) {
		super(memberId, stockId, quantity, sum);
		this.stockName = stockName;
		this.price = price;
		this.gain = gain;
		this.percent = percent;
	}

	public HaveStockView(int income) {
		this.income = income;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getGain() {
		return gain;
	}
	public void setGain(String gain) {
		this.gain = gain;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}

	@Override
	public String toString() {
		return "HaveStockView [stockName=" + stockName + ", price=" + price + ", intPrice=" + income + ", gain="
				+ gain + ", percent=" + percent + super.toString() + "]";
	}



}
