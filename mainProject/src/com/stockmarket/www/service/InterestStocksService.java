package com.stockmarket.www.service;

import java.util.List;

import com.stockmarket.www.entity.InterestStocks;

public interface InterestStocksService {
	
	List<InterestStocks> getInterestStockList(); 

	void deleteStock(int userid, String deletestock);
}
