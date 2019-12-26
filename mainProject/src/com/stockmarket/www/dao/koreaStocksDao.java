package com.stockmarket.www.dao;

import java.util.List;

import com.stockmarket.www.entity.koreaStocks;
//koreaStocks = 코스피+ 코스닥
public interface koreaStocksDao {
	List<koreaStocks> getList();
	
	int insert(List<koreaStocks> list);
	int delete();
}
