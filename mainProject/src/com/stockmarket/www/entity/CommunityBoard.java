package com.stockmarket.www.entity;

import java.util.Date;

public class CommunityBoard {
	private int id;
	private String title;
	private String writerId;
	private Date regdate;
	private int hit;
	private String content;
	private String stockName;

	// select

	public CommunityBoard() {
	}

	public CommunityBoard(int id, String title, String writerId, Date regdate, int hit, String content,
			String stockcode) {
		this.id = id;
		this.title = title;
		this.writerId = writerId;
		this.regdate = regdate;
		this.hit = hit;
		this.content = content;
		this.stockName = stockcode;
	}

	// insert, update
	public CommunityBoard(String title, String writerId, Date regdate, int hit, String content, String stockName) {
		this.title = title;
		this.writerId = writerId;
		this.regdate = regdate;
		this.hit = hit;
		this.content = content;
		this.stockName = stockName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	@Override
	public String toString() {
		return "CommunityBoard [id=" + id + ", title=" + title + ", writerId=" + writerId + ", regdate=" + regdate
				+ ", hit=" + hit + ", content=" + content + ", stockName=" + stockName + "]";
	}

}
