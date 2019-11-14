package com.stockmarket.www.dao;

import java.util.List;

import com.stockmarket.www.ett.InterestStocks;

public interface InterestStocksDao {
	
	List<InterestStocks> getInterestStockList();
	
	int delete(int id);
}
