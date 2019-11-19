package com.stockmarket.www.entity;

public class Company {
//("회사명 : " + line[0] + ", 종목코드 : " + line[2] + ",  웹사이트 : " + line[7] );
	private String company;
	private String stockItemName;
	private String website;
	
	public Company() {
		
	}
	
	public Company(String company, String stockItemName, String website) {
	
		this.company = company;
		this.stockItemName = stockItemName;
		this.website = website;
	}

	

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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
		return "Company [company=" + company + ", stockItemName=" + stockItemName + ", website=" + website + "]";
	}
	
	
	
}
