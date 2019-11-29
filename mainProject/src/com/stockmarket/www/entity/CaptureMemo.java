package com.stockmarket.www.entity;

import java.util.Date;

public class CaptureMemo {
	private int id;
	private String title;
	private Date regdate;
	private String content;
	private int highprice;
	private int maketprice;
	private int dealingnum;
	private int preclosingprice;
	private String favorite;

	public CaptureMemo() {
		

	}
	
	public CaptureMemo(String title, Date regdate, String content) {
		
		this.title = title;
		this.regdate = regdate;
		this.content = content;
	}

	// for inserting
	public CaptureMemo(String title, String content) {

		this.title = title;
		this.content = content;

	}

   //for selecting
	public CaptureMemo(int id, String title, Date regdate, String content, int highprice, int maketprice, int dealingnum,
			int preclosingprice, String favorite) {
	
		this.id = id;
		this.title = title;
		this.regdate = regdate;
		this.content = content;
		this.highprice = highprice;
		this.maketprice = maketprice;
		this.dealingnum = dealingnum;
		this.preclosingprice = preclosingprice;
		this.favorite = favorite;
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

	public int getHighprice() {
		return highprice;
	}

	public void setHighprice(int highprice) {
		this.highprice = highprice;
	}

	public int getMaketprice() {
		return maketprice;
	}

	public void setMaketprice(int maketprice) {
		this.maketprice = maketprice;
	}

	public int getDealingnum() {
		return dealingnum;
	}

	public void setDealingnum(int dealingnum) {
		this.dealingnum = dealingnum;
	}

	public int getPreclosingprice() {
		return preclosingprice;
	}

	public void setPreclosingprice(int preclosingprice) {
		this.preclosingprice = preclosingprice;
	}

	public String getFavorite() {
		return favorite;
	}

	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}

	@Override
	public String toString() {
		return "CaptureMemo [id=" + id + ", title=" + title + ", redate=" + regdate + ", content=" + content + ", highprice="
				+ highprice + ", maketprice=" + maketprice + ", dealingnum=" + dealingnum + ", preclosingprice="
				+ preclosingprice + ",favorite=" + favorite + "]";
	}

}
