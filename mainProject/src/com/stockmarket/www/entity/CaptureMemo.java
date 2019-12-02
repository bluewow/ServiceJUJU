package com.stockmarket.www.entity;

import java.util.Date;

public class CaptureMemo {
	private int id;
	private String title;
	private Date regdate;
	private String content;
	private int highPrice;
	private int maketPrice;
	private int tradingVolume;
	private int preClosingPrice;
	private String favorite;
	private String codeNum;
	private int memberId;

	public CaptureMemo(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public CaptureMemo(String title, Date regdate) {
		this.title = title;
		this.regdate = regdate;
	}

	public CaptureMemo(String title, String content, int highPrice, int maketPrice, int tradingVolume,
			int preClosingPrice, String favorite, String codeNum, int memberId) {
		this.title = title;
		this.content = content;
		this.highPrice = highPrice;
		this.maketPrice = maketPrice;
		this.tradingVolume = tradingVolume;
		this.preClosingPrice = preClosingPrice;
		this.favorite = favorite;
		this.codeNum = codeNum;
		this.memberId = memberId;
	}

	public CaptureMemo(int id, String title, Date regdate, String content, int highPrice, int maketPrice,
			int tradingVolume, int preClosingPrice, String favorite, String codeNum, int memberId) {
		this.id = id;
		this.title = title;
		this.regdate = regdate;
		this.content = content;
		this.highPrice = highPrice;
		this.maketPrice = maketPrice;
		this.tradingVolume = tradingVolume;
		this.preClosingPrice = preClosingPrice;
		this.favorite = favorite;
		this.codeNum = codeNum;
		this.memberId = memberId;
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

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(int highPrice) {
		this.highPrice = highPrice;
	}

	public int getMaketPrice() {
		return maketPrice;
	}

	public void setMaketPrice(int maketPrice) {
		this.maketPrice = maketPrice;
	}

	public int getTradingVolume() {
		return tradingVolume;
	}

	public void setTradingVolume(int tradingVolume) {
		this.tradingVolume = tradingVolume;
	}

	public int getPreClosingPrice() {
		return preClosingPrice;
	}

	public void setPreClosingPrice(int preClosingPrice) {
		this.preClosingPrice = preClosingPrice;
	}

	public String getFavorite() {
		return favorite;
	}

	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}

	public String getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "CaptureMemo [id=" + id + ", title=" + title + ", redate=" + regdate + ", content=" + content
				+ ", highprice=" + highPrice + ", maketprice=" + maketPrice + ", dealingnum=" + tradingVolume
				+ ", preclosingprice=" + preClosingPrice + ",favorite=" + favorite + ", codeNum=" + codeNum
				+ ", memberId=" + memberId + "]";
	}

}
