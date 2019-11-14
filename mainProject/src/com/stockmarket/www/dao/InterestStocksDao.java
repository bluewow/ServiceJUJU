package com.stockmarket.www.dao;

import java.util.List;

import com.stockmarket.www.ett.InterestStocks;

public interface InterestStocksDao {
	
	List<InterestStocks> getInterestStockList();
	List<InterestStocks> getInterestStockList(String stockName, int currentPrice, int quentity);
	
	int delete(int id);
}
