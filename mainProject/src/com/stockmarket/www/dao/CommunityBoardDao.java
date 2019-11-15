package com.stockmarket.www.dao;
//쿼리문...
import java.util.List;

import com.stockmarket.www.ett.CommunityBoard;

public interface CommunityBoardDao {
	List<CommunityBoard> getCommunityBoardList(int page, String Field, String Query, String Code);
	int getCommunityBoardListCount(String field, String query);
}
