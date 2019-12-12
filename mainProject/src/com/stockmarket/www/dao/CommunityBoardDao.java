package com.stockmarket.www.dao;
//쿼리문...
import java.util.List;

import com.stockmarket.www.entity.CommunityBoard;

public interface CommunityBoardDao {
	List<CommunityBoard> getCommunityBoardList(int page, String Field, String Query, String Code);
	List<CommunityBoard> getReplyList(int boardId);
	CommunityBoard getCommunityBoardDetail(int id);
	int insertCommunityBoard(CommunityBoard communityBoard);
	int getReplyCnt(String field, String query, String stockName);
	int insertReply(CommunityBoard insertReply);
}
