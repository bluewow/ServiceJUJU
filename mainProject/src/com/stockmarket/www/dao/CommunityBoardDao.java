package com.stockmarket.www.dao;
//쿼리문...
import java.util.List;

import com.stockmarket.www.entity.CommunityBoard;

public interface CommunityBoardDao {
	List<CommunityBoard> getCommunityBoardList(int page, String Field, String Query, String Code);
	List<CommunityBoard> getReplyList(int boardId);
	int getReplyCnt(String field, String query, String stockName);
	CommunityBoard getCommunityBoardDetail(int id);
	int insertReply(CommunityBoard insertReply);
}
