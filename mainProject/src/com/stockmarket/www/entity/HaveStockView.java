package com.stockmarket.www.entity;

public class HaveStockView extends HaveStock {
	
	private String stockName;
	private String price;
	private int intPrice;
	private String gain;
	private String percent;
	
	public HaveStockView() {
		// TODO Auto-generated constructor stub
	}
	
	public HaveStockView(int memberId, String stockId, int quantity, float avg, String stockName, String price,
			String gain, String percent) {
		super(memberId, stockId, quantity, avg);
		this.stockName = stockName;
		this.price = price;
		this.gain = gain;
		this.percent = percent;
	}
	
	public HaveStockView(int memberId, String stockId, int quantity, int avg, String stockName, int intprice,
			String gain, String percent) {
		super(memberId, stockId, quantity, avg);
		this.stockName = stockName;
		this.intPrice = intprice;
		this.gain = gain;
		this.percent = percent;
	}

	public int getIntprice() {
		return intPrice;
	}

	public void setIntprice(int intprice) {
		this.intPrice = intprice;
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
		return "HaveStockView [stockName=" + stockName + ", price=" + price + ", gain=" + gain + ", percent=" + percent
				+ ", toString()=" + super.toString() + "]";
	}

}
