package com.stockmarket.www.dao;

import java.util.List;
import com.stockmarket.www.entity.CaptureMemo;
import com.stockmarket.www.entity.CaptureMemoView;

public interface CaptureMemoDao {
	List<CaptureMemoView> getList();

	int insert(CaptureMemo captureMemo);	// 캡처메모 저장
	int update(CaptureMemo captureMemo);	// 캡처메모 수정
	int delete(int id);						// 캡처메모 삭제 

}
