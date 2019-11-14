package com.stockmarket.www.service.repository;

import java.util.List;

import com.stockmarket.www.ett.InterestStocks;

public interface InterestStocksInterface {
	
	List<InterestStocks> getInterestStockList(); 
	List<InterestStocks> getInterestStockList(String stockName,int currentPrice,int quentity); 
    
	int delete(int id);
}
