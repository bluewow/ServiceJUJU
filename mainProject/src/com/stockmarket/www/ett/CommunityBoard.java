package com.stockmarket.www.ett;

import java.util.Date;

public class CommunityBoard {
	private int id;
	private String title;
	private String writerId;
	private Date regdate;
	private int hit;
	private String content;
	private String stockcode;

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
		this.stockcode = stockcode;
	}

	// insert, update
	public CommunityBoard(String title, String writerId, Date regdate, int hit, String content, String stockcode) {
		this.title = title;
		this.writerId = writerId;
		this.regdate = regdate;
		this.hit = hit;
		this.content = content;
		this.stockcode = stockcode;
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

	public String getStockcode() {
		return stockcode;
	}

	public void setStockcode(String stockcode) {
		this.stockcode = stockcode;
	}

	@Override
	public String toString() {
		return "CommunityBoard [id=" + id + ", title=" + title + ", writerId=" + writerId + ", regdate=" + regdate
				+ ", hit=" + hit + ", content=" + content + ", stockcode=" + stockcode + "]";
	}

}
