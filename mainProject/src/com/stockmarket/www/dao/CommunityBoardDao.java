package com.stockmarket.www.dao;
//쿼리문...
import java.util.List;

import com.stockmarket.www.entity.CommunityBoard;

public interface CommunityBoardDao {
	List<CommunityBoard> getCommunityBoardList(int page, String Field, String Query, String Code);
	int getReplyCnt(String field, String query, String stockName);
	CommunityBoard getCommunityBoardDetail(int id);
	List<CommunityBoard> getReplyList(int boardId);
	int insertReply(String title, String writerNickname, int boardId);
	int insertReply(CommunityBoard insertReply);
}
