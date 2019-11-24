package com.stockmarket.www.entity;

public class CurrentStockInfo {
	private String codeNum;
	private String price;
	private String status; //상승 or 하락
	private String stuatsPrice; //
	private String statusPercent; //+ or -
	private String percent;
	
	public CurrentStockInfo parser(String text) {
		CurrentStockInfo curStockInfo = new CurrentStockInfo();
		
		String[] data = text.split(" ");
		curStockInfo.codeNum = data[0];
		curStockInfo.price = data[1];
		curStockInfo.status = data[2];
		curStockInfo.stuatsPrice = data[3];
		curStockInfo.statusPercent = data[4];
		curStockInfo.percent = data[5];
		
		return curStockInfo;
	}
	

	public String getPrice() {
		return price;
	}

	public String getStatus() {
		return status;
	}

	public String getStuatsPrice() {
		return stuatsPrice;
	}

	public String getStatusPercent() {
		return statusPercent;
	}

	public String getPercent() {
		return percent;
	}


	@Override
	public String toString() {
		return "CurrentStockInfo [codeNum=" + codeNum + ", price=" + price + ", status=" + status + ", stuatsPrice="
				+ stuatsPrice + ", statusPercent=" + statusPercent + ", percent=" + percent + "]";
	}
	
	
}
