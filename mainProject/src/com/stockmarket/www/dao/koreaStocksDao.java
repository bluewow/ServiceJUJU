package com.stockmarket.www.dao;

import java.util.List;

import com.stockmarket.www.entity.koreaStocks;

public interface koreaStocksDao {
	public koreaStocks get(String codeNum);
	List<koreaStocks> getList();
	
	int insert(List<koreaStocks> list);
	int update(String src, String target);
	int delete();
	
	koreaStocks searchCompany(String compnayName);
}
