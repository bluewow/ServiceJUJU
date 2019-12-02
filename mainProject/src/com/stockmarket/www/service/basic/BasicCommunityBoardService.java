package com.stockmarket.www.service.basic;

import java.util.List;

import com.stockmarket.www.dao.CommunityBoardDao;
import com.stockmarket.www.dao.jdbc.JdbcCommunityBoardDao;
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
	public List<CommunityBoard> getCommunityBoardList(int page) {
		return getCommunityBoardList(page, "title", "", "");
	}

	@Override
	public List<CommunityBoard> getCommunityBoardList(int page, String field, String query, String stockName) {
		// TODO Auto-generated method stub
		return communityBoardDao.getCommunityBoardList(page, field, query, stockName);
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
		// TODO Auto-generated method stub
		return 0;
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



}
