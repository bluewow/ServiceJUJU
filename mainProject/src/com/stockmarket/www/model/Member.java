package com.stockmarket.www.model;

import java.util.Date;

public class Member {
	private int id;
	private String email;
	private String password;
	private String nickName;
	private int vMoney;
	private Date regdate;
	
	public Member(){
		this("", "", "", 0);
	}
	
	public Member(String email, String password, String nickName, int vMoney) {
		this.email = email;
		this.password = password;
		this.nickName = nickName;
		this.vMoney = vMoney;
	}
	
	// getter and setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getvMoney() {
		return vMoney;
	}
	public void setvMoney(int vMoney) {
		this.vMoney = vMoney;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	
}
