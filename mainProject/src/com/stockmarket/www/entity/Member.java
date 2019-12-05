package com.stockmarket.www.entity;

public class Member {
	private int id;
	private String email;
	private String nickName;
	private String password;
	private int vMoney;
	private String cardPos;
	
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
	public Member(int id, String email, String nickName, String password, int vMoney, String cardPos) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.nickName = nickName;
		this.vMoney = vMoney;
		this.cardPos = cardPos;		
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

	public String getCardPos() {
		return cardPos;
	}

	public void setCardPos(String cardPos) {
		this.cardPos = cardPos;
	}
	
	
}
