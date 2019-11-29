package com.stockmarket.www.dao;

import java.util.List;

import com.stockmarket.www.entity.CaptureMemo;

public interface CaptureMemoDao {

	List<CaptureMemo> getList(int page);

	
	
	
	
	int insert(CaptureMemo captureMemo);//저장
	int update(CaptureMemo captureMemo);//수정
	int delete(int id);//삭제 

	

}
