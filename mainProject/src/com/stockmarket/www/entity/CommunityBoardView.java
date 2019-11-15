package com.stockmarket.www.entity;

import java.util.Date;

public class CommunityBoardView extends CommunityBoard {
	private int replyCount;
	
	public int getCmtCount() {
		return replyCount;
	}

	public void setCmtCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public CommunityBoardView(int id, String title, String writerId, Date regdate, int hit, String content,
			String stockName, int replyCount) {
		super(id, title, writerId, regdate, hit, content, stockName);
		this.replyCount = replyCount;
	}
	
}
