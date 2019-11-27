package com.stockmarket.www.entity;

public class CurStock {
	private String codeNum;
	private String price;
	private String gain; //상승 or 하락
	private String gainPrice; //
	private String signMark; //+ or -
	private String percent; //%
	
	public CurStock() {
	}
	
	public CurStock(String codeNum, String price, String gain, String stuatsPrice, String statusPercent,
			String percent) {
		super();
		this.codeNum = codeNum;
		this.price = price;
		this.gain = gain;
		this.gainPrice = stuatsPrice;
		this.signMark = statusPercent;
		this.percent = percent;
	}

	public CurStock parser(String text) {
		CurStock curStockInfo = new CurStock();
		
		String[] data = text.split(" ");
		curStockInfo.codeNum = data[0];
		curStockInfo.price = data[1];
		curStockInfo.gain = data[2];
		curStockInfo.gainPrice = data[3];
		curStockInfo.signMark = data[4];
		curStockInfo.percent = data[5];
		
		return curStockInfo;
	}
	
	public String getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
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

	public String getGainPrice() {
		return gainPrice;
	}

	public void setGainPrice(String gainPrice) {
		this.gainPrice = gainPrice;
	}

	public String getSignMark() {
		return signMark;
	}

	public void setSignMark(String signMark) {
		this.signMark = signMark;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	@Override
	public String toString() {
		return "CurrentStockInfo [codeNum=" + codeNum + ", price=" + price + ", gain=" + gain + ", stuatsPrice="
				+ gainPrice + ", signMark=" + signMark + ", percent=" + percent + "]";
	}


	public static void main(String[] args) {
		CurStock stock = new CurStock();

		/*종목코드, 현재가, 상승 or 하강, 가격, +/-, %*/
		System.out.println(stock.parser("095660 13,000 상승 3,000 + 23.2").toString());
	}
	
}
