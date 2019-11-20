package com.stockmarket.www.entity;

public class Company {
//("회사명 : " + line[0] + ", 종목코드 : " + line[2] + ",  웹사이트 : " + line[7] );
	private String companyName;
	private String stockItemName;
	private String website;
	
	public Company() {
		
	}
	
	public Company(String companyName, String stockItemName, String website) {
	
		this.companyName = companyName;
		this.stockItemName = stockItemName;
		this.website = website;
	}

	

	public String getcompanyName() {
		return companyName;
	}

	public void setcompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStockItemName() {
		return stockItemName;
	}

	public void setStockItemName(String stockItemName) {
		this.stockItemName = stockItemName;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Override
	public String toString() {
		return "Company [companyName=" + companyName + ", stockItemName=" + stockItemName + ", website=" + website + "]";
	}
	
	
	
}
