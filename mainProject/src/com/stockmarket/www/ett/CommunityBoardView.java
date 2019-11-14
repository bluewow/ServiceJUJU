package com.stockmarket.www.ett;

import java.util.Date;

public class CommunityBoardView extends CommunityBoard {
	private int cmtCount;
	
	public int getCmtCount() {
		return cmtCount;
	}

	public void setCmtCount(int cmtCount) {
		this.cmtCount = cmtCount;
	}

	public CommunityBoardView(int id, String title, String writerId, Date regdate, int hit, String content,
			String stockcode, int cmtCount) {
		super(id, title, writerId, regdate, hit, content, stockcode);
		this.cmtCount = cmtCount;
	}
	
}
