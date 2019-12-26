package com.stockmarket.www.dao;

import java.util.List;

import com.stockmarket.www.entity.koreaStocks;

public interface koreaStocksDao {
	List<koreaStocks> getList();
	
	int insert(List<koreaStocks> list);
	int delete();
	
	koreaStocks searchCompany(String compnayName);
}
