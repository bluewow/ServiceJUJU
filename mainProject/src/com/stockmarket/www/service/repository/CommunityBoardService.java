package com.stockmarket.www.service.repository;

import java.util.List;

import com.stockmarket.www.ett.CommunityBoard;
import com.stockmarket.www.service.CommunityBoardServiceInterface;

public class CommunityBoardService implements CommunityBoardServiceInterface {

	private CommunityBoard communityBoard;

	public CommunityBoardService() {
		communityBoard = new CommunityBoard();
	}

	@Override
	public List<CommunityBoard> getCommunityBoardList(int page) {

		return getCommunityBoardList(page);
	}

	@Override
	public List<CommunityBoard> getStockBoardList(int page, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommunityBoard> getSearchBoardList(int page, String field, String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommunityBoard> getSearchStockBoardList(int page, String field, String query, String code) {
		// TODO Auto-generated method stub
		return null;
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
