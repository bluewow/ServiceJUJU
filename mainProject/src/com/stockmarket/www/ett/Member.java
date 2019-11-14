package com.stockmarket.www.ett;

import java.util.Date;

public class Member {
	private int id;
	private String email;
	private String nickName;
	private String password;
	private int vMoney;
	
	public Member() {
	
	}
	
	// insert, update를 위한 생성자
	public Member(String email, String nickName, String password, int vMoney) {
		this.email = email;
		this.password = password;
		this.nickName = nickName;
		this.vMoney = vMoney;
	}
	
	// select를 위한 생성자
	public Member(int id, String email, String nickName, String password, int vMoney) {
		this.id = id;
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
}
