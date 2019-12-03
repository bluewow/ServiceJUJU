package com.stockmarket.www.entity;

import java.util.Date;

public class CaptureMemo {
	private int id;
	private String title;
	private Date regdate;
	private String content;
	private int PER;
	private int PBR;
	private int ROE;
	private int debtRatio;		// 부채 비율
	private int totalAssets;	// 자산 총계
	private String codeNum;
	private int memberId;

	// for update
	public CaptureMemo(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public CaptureMemo(String title, Date regdate) {
		this.title = title;
		this.regdate = regdate;
	}

	// for insert
	public CaptureMemo(String title, String content, int PER, int PBR, int ROE,
			int debtRatio, int totalAssets, String codeNum, int memberId) {
		this.title = title;
		this.content = content;
		this.PER = PER;
		this.PBR = PBR;
		this.ROE = ROE;
		this.debtRatio = debtRatio;
		this.totalAssets = totalAssets;
		this.codeNum = codeNum;
		this.memberId = memberId;
	}

	public CaptureMemo(int id, String title, Date regdate, String content, int PER, int PBR,
			int ROE, int debtRatio, int totalAssets, String codeNum, int memberId) {
		this.id = id;
		this.title = title;
		this.regdate = regdate;
		this.content = content;
		this.PER = PER;
		this.PBR = PBR;
		this.ROE = ROE;
		this.debtRatio = debtRatio;
		this.totalAssets = totalAssets;
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

	public int getPER() {
		return PER;
	}

	public void setPER(int PER) {
		this.PER = PER;
	}

	public int getPBR() {
		return PBR;
	}

	public void setPBR(int PBR) {
		this.PBR = PBR;
	}

	public int getROE() {
		return ROE;
	}

	public void setROE(int ROE) {
		this.ROE = ROE;
	}

	public int getDebtRatio() {
		return debtRatio;
	}

	public void setDebtRatio(int debtRatio) {
		this.debtRatio = debtRatio;
	}

	public int getTotalAssets() {
		return totalAssets;
	}

	public void setTotalAssets(int totalAssets) {
		this.totalAssets = totalAssets;
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
				+ ", highprice=" + PER + ", maketprice=" + PBR + ", dealingnum=" + ROE
				+ ", preclosingprice=" + debtRatio + ",favorite=" + totalAssets + ", codeNum=" + codeNum
				+ ", memberId=" + memberId + "]";
	}

}
