package com.stockmarket.www.dao.jdbc;

import java.util.List;

import com.stockmarket.www.dao.InterestStocksDao;
import com.stockmarket.www.ett.InterestStocks;

public class JdbcInterestStocksDao implements InterestStocksDao {

	@Override
	public List<InterestStocks> getInterestStockList() {
	
		return null;
	}

	@Override
	public List<InterestStocks> getInterestStockList(String stockName, int currentPrice, int quentity) {
	
		return null;
	}

	@Override
	public int delete(int id) {

		return 0;
	}


}
