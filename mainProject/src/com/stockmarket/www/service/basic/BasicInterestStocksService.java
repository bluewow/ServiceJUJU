package com.stockmarket.www.service.basic;

import java.util.List;

import com.stockmarket.www.dao.InterestStocksDao;
import com.stockmarket.www.dao.jdbc.JdbcInterestStocksDao;
import com.stockmarket.www.ett.InterestStocks;
import com.stockmarket.www.service.InterestStocksService;

public class BasicInterestStocksService implements InterestStocksService {

	
	InterestStocksDao interestStocksDao ;
	
	public BasicInterestStocksService() {
		interestStocksDao = new JdbcInterestStocksDao();
	}
	
	
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
