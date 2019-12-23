package com.stockmarket.www.dao;

import java.util.List;

import com.stockmarket.www.entity.Upjong;

public interface UpjongDao {
	List<Upjong> getList();
	
	int insert(List<Upjong> list);
	int delete();
	

}
