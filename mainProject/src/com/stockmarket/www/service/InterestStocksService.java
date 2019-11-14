package com.stockmarket.www.service;

import java.util.List;

import com.stockmarket.www.ett.InterestStocks;

public interface InterestStocksService {
	
	List<InterestStocks> getInterestStockList(); 
	List<InterestStocks> getInterestStockList(String stockName,int currentPrice,int quentity); 
    
	int delete(int id);
}
