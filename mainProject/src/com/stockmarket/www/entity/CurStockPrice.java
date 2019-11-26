package com.stockmarket.www.entity;

public class CurStockPrice {
	private String codeNum;
	private String price;
	private String status; //상승 or 하락
	private String stuatsPrice; //
	private String statusPercent; //+ or -
	private String percent; //%
	
	public CurStockPrice() {
	}
	
	public CurStockPrice(String codeNum, String price, String status, String stuatsPrice, String statusPercent,
			String percent) {
		super();
		this.codeNum = codeNum;
		this.price = price;
		this.status = status;
		this.stuatsPrice = stuatsPrice;
		this.statusPercent = statusPercent;
		this.percent = percent;
	}


	public CurStockPrice parser(String text) {
		CurStockPrice curStockInfo = new CurStockPrice();
		
		String[] data = text.split(" ");
		curStockInfo.codeNum = data[0];
		curStockInfo.price = data[1];
		curStockInfo.status = data[2];
		curStockInfo.stuatsPrice = data[3];
		curStockInfo.statusPercent = data[4];
		curStockInfo.percent = data[5];
		
		return curStockInfo;
	}
	

	public String getCodeNum() {
		return codeNum;
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


	public static void main(String[] args) {
		CurStockPrice stock = new CurStockPrice();

		/*종목코드, 현재가, 상승 or 하강, 가격, +/-, %*/
		System.out.println(stock.parser("005960 13,000 상승 3,000 + 23.2").toString());
	}
	
}
