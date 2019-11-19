package com.stockmarket.www.dao;

import java.util.List;

import com.stockmarket.www.entity.HaveStock;

public interface HaveStockDao {
	
	List<HaveStock> getList(int memberId);
	
	HaveStock get(int memberId, String stockId);

	int update(HaveStock haveStock);
	int insert(HaveStock haveStock);
	int delete(int memberId, String stockId);

}
