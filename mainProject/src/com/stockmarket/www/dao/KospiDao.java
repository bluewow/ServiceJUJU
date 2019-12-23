package com.stockmarket.www.dao;

import java.util.List;

import com.stockmarket.www.entity.Kospi;

public interface KospiDao {
	List<Kospi> getList();
	
	int insert(List<Kospi> list);
	int delete();
}
