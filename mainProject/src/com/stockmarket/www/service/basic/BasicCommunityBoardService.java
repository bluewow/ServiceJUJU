package com.stockmarket.www.service.basic;

import java.util.List;

import com.stockmarket.www.dao.CommunityBoardDao;
import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.dao.jdbc.JdbcCommunityBoardDao;
import com.stockmarket.www.dao.jdbc.JdbcMemberDao;
import com.stockmarket.www.entity.CommunityBoard;
import com.stockmarket.www.service.CommunityBoardService;

public class BasicCommunityBoardService implements CommunityBoardService {

	private CommunityBoardDao communityBoardDao;

	public BasicCommunityBoardService() {
		communityBoardDao = new JdbcCommunityBoardDao();
	}
	
	//게시판 목록 보기	
	@Override
	public List<CommunityBoard> getCommunityBoardList() {
		return getCommunityBoardList(1, "title", "", "");
	}
	
	//게시판 목록 보기	
	@Override
	public List<CommunityBoard> getCommunityBoardList(int page, String stockCode) {
		return getCommunityBoardList(page, "title", "", stockCode);
	}
	
	//게시판 목록 보기	
	@Override
	public List<CommunityBoard> getCommunityBoardList(int page) {
		return getCommunityBoardList(page, "title", "", "");
	}

	//게시판 목록 보기
	@Override
	public List<CommunityBoard> getCommunityBoardList(int page, String field, String query, String stockCode) {
		return communityBoardDao.getCommunityBoardList(page, field, query, stockCode);
	}

	//게시글과 댓글 보기
	@Override
	public CommunityBoard getBoard(int id) {
		return communityBoardDao.getCommunityBoardDetail(id);
	}

	@Override
	public CommunityBoard getPrevBoardByCurrentId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommunityBoard getNextBoardByCurrentId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	//게시글 쓰기
	@Override
	public int insertCommunityBoard(CommunityBoard communityBoard) {
		return communityBoardDao.insertCommunityBoard(communityBoard);
	}
	
	//게시글 수정하기
	@Override
	public int updateCommunityBoard(CommunityBoard communityBoard) {
		// TODO Auto-generated method stub
		return 0;
	}

	//게시글 삭제하기
	@Override
	public int deleteCommunityBoard(CommunityBoard communityBoard) {
		// TODO Auto-generated method stub
		return 0;
	}

	//게시글 즐겨찾기 추가
	@Override
	public int insertFavoriteCommunityBoard(CommunityBoard communityBoard) {
		// TODO Auto-generated method stub
		return 0;
	}

	//게시글 즐겨찾기 삭제
	@Override
	public int deleteFavoriteCommunityBoard(CommunityBoard communityBoard) {
		// TODO Auto-generated method stub
		return 0;
	}

	//게시글 댓글개수 보기
	@Override
	public int getCommunityBoardreplyCnt(String field, String query, String stockName) {
		// TODO Auto-generated method stub
		return communityBoardDao.getReplyCnt(field,query,stockName);
	}

	//게시글 댓글보기
	@Override
	public List<CommunityBoard> getCommunityBoardReplyList(int boardId) {
		// TODO Auto-generated method stub
		return communityBoardDao.getReplyList(boardId);
	}

	//게시글 댓글 쓰기
	@Override
	public int insertReply(CommunityBoard insertReply) {
		// TODO Auto-generated method stub
		return communityBoardDao.insertReply(insertReply);
	}

	//게시글의 마지막 댓글 번호 알기
	@Override
	public int lastReplyNum(int boardId) {
		// TODO Auto-generated method stub
		return communityBoardDao.lastReplyNum(boardId);
	}

	//게시글 댓글 수정하기
	@Override
	public int updateReply(CommunityBoard updateReply) {
		// TODO Auto-generated method stub
		return communityBoardDao.updateReply(updateReply);
	}

	//게시글 댓글 삭제하기
	@Override
	public int deleteReply(int replyId) {
		// TODO Auto-generated method stub
		return communityBoardDao.deleteReply(replyId);
	}


}
