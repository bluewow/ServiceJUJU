package com.stockmarket.www.service;

import java.util.List;

import com.stockmarket.www.ett.InterestStocks;

public interface InterestStocksService {
	
	List<InterestStocks> getInterestStockList(); 
    
	int delete(int id);
}
