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

	@Override
	public List<CommunityBoard> getCommunityBoardList() {
		return getCommunityBoardList(1, "title", "", "");
	}

	@Override
	public List<CommunityBoard> getCommunityBoardList(int page, String stockCode) {
		return getCommunityBoardList(page, "title", "", stockCode);
	}

	@Override
	public List<CommunityBoard> getCommunityBoardList(int page) {
		return getCommunityBoardList(page, "title", "", "");
	}

	@Override
	public List<CommunityBoard> getCommunityBoardList(int page, String field, String query, String stockCode) {
		// TODO Auto-generated method stub
		return communityBoardDao.getCommunityBoardList(page, field, query, stockCode);
	}

	@Override
	public CommunityBoard getBoard(int id) {
		// TODO Auto-generated method stub
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

	@Override
	public int insertCommunityBoard(CommunityBoard communityBoard) {
		return communityBoardDao.insertCommunityBoard(communityBoard);
	}

	@Override
	public int updateCommunityBoard(CommunityBoard communityBoard) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCommunityBoard(CommunityBoard communityBoard) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertFavoriteCommunityBoard(CommunityBoard communityBoard) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteFavoriteCommunityBoard(CommunityBoard communityBoard) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCommunityBoardreplyCnt(String field, String query, String stockName) {
		// TODO Auto-generated method stub
		return communityBoardDao.getReplyCnt(field,query,stockName);
	}

	@Override
	public List<CommunityBoard> getCommunityBoardReplyList(int boardId) {
		// TODO Auto-generated method stub
		return communityBoardDao.getReplyList(boardId);
	}

	@Override
	public int insertReply(CommunityBoard insertReply) {
		// TODO Auto-generated method stub
		return communityBoardDao.insertReply(insertReply);
	}

	@Override
	public int lastReplyNum(int boardId) {
		// TODO Auto-generated method stub
		return communityBoardDao.lastReplyNum(boardId);
	}

	@Override
	public int updateReply(CommunityBoard updateReply) {
		// TODO Auto-generated method stub
		return communityBoardDao.updateReply(updateReply);
	}

	@Override
	public int deleteReply(int replyId) {
		// TODO Auto-generated method stub
		return communityBoardDao.deleteReply(replyId);
	}


}
