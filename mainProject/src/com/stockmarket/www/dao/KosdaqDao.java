package com.stockmarket.www.dao;

import java.util.List;

import com.stockmarket.www.entity.Kosdaq;

public interface KosdaqDao {

	List<Kosdaq> getList();
	
	int insert(List<Kosdaq> list);
	int delete();
}
