package com.stockmarket.www.entity;

public class RecordAsset {
	
	private int memberId;
	private String regdate;
	private int value;
	
	@Override
	public String toString() {
		return "RecordAsset [memberId=" + memberId + ", regdate=" + regdate + ", value=" + value + "]";
	}
	
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	

}
