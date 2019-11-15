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
	public List<CommunityBoard> getCommunityBoardList(int page) {
		// TODO Auto-generated method stub
		return communityBoardDao.getCommunityBoardList(page, "title", "", "");
	}

	@Override
	public List<CommunityBoard> getStockBoardList(int page, String code) {
		// TODO Auto-generated method stub
		return communityBoardDao.getCommunityBoardList(page, "title", "", code);
	}

	@Override
	public List<CommunityBoard> getSearchBoardList(int page, String field, String query) {
		// TODO Auto-generated method stub
		return communityBoardDao.getCommunityBoardList(page, field, query, "");
	}

	@Override
	public List<CommunityBoard> getSearchStockBoardList(int page, String field, String query, String code) {
		// communityBoardDao
		return communityBoardDao.getCommunityBoardList(page, field, query, code);
	}

	@Override
	public CommunityBoard getBoard(int id) {
		// TODO Auto-generated method stub
		return null;
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


}
