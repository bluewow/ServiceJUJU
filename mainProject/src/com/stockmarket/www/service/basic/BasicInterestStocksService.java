package com.stockmarket.www.service.basic;

import java.util.List;

import com.stockmarket.www.dao.InterestStocksDao;
import com.stockmarket.www.dao.jdbc.JdbcInterestStocksDao;
import com.stockmarket.www.entity.InterestStocks;
import com.stockmarket.www.service.InterestStocksService;

public class BasicInterestStocksService implements InterestStocksService {

	
	InterestStocksDao interestStocksDao ;
	
	public BasicInterestStocksService() {
		interestStocksDao = new JdbcInterestStocksDao();
	}
	
	
	@Override
	public List<InterestStocks> getInterestStockList() {
		
		return interestStocksDao.getInterestStockList();
	}

//	@Override
//	public List<InterestStocks> getInterestStockList(String stockName, int currentPrice, int quentity) {
//		
//		return null;
//	}

	@Override
	public void deleteStock(int userId, String delStockName) {
		interestStocksDao.delete(userId,delStockName);
		
	}

}
