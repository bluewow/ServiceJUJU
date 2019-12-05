package com.stockmarket.www.service;
//업무적인...
import java.util.List;

import com.stockmarket.www.entity.CommunityBoard;

public interface CommunityBoardService {
	/*
	 * 게시판 목록
	 * */
	List<CommunityBoard> getCommunityBoardList();
	List<CommunityBoard> getCommunityBoardList(int page);
	List<CommunityBoard> getCommunityBoardList(int page, String field, String query, String stockName);

	/*
	 * 댓글목록
	 * */
	List<CommunityBoard> getCommunityBoardReplyList(int boardId);
	
	/*
	 * 게시판 내용
	 * */
	CommunityBoard getBoard(int id);
	CommunityBoard getPrevBoardByCurrentId(int id);
	CommunityBoard getNextBoardByCurrentId(int id);
	/*
	 * 게시판 삽입 편집
	 * */
	int insertCommunityBoard(CommunityBoard communityBoard);
	int updateCommunityBoard(CommunityBoard communityBoard);
	int deleteCommunityBoard(CommunityBoard communityBoard);
	/*
	 * 게시판 즐겨찾기 추가 삭제
	 * */
	int insertFavoriteCommunityBoard(CommunityBoard communityBoard);
	int deleteFavoriteCommunityBoard(CommunityBoard communityBoard);
	int getCommunityBoardreplyCnt(String field, String query, String stockName);
	/*
	 * 댓글 추가 삭제
	 * */
	int insertReply(CommunityBoard insertReply);


}

