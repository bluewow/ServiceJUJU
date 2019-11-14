package com.stockmarket.www.service;

import java.util.List;

import com.stockmarket.www.dao.InterestStocksDao;
import com.stockmarket.www.dao.jdbc.JdbcInterestStocksDao;
import com.stockmarket.www.ett.InterestStocks;
import com.stockmarket.www.service.repository.InterestStocksInterface;

public class InterestStocksService implements InterestStocksInterface {

	
	InterestStocksDao interestStocksDao ;
	
	public InterestStocksService() {
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
