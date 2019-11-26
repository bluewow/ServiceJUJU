package com.stockmarket.www.dao;

import java.util.List;

import com.stockmarket.www.entity.RecordAsset;

public interface RecoedAssetDao {
	
	// 자산추이 그래프
	List<RecordAsset> getList(int memberId);
	
	int insert(int memberId, String regdate, int asset);
	int delete(int memberId);

}
