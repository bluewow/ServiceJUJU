package com.stockmarket.www.service;

import java.util.List;

import com.stockmarket.www.entity.CaptureMemo;
import com.stockmarket.www.entity.CommunityBoard;

public interface CaptureMemoService {
	
//	캡쳐메모 목록
	List<CaptureMemo> getCaptureMemmoList(int page);
// 캡쳐메모 내용
	CaptureMemo getMemo(int id);

// 캡쳐메모 저장 수정 삭제
	int insertCaptuerMemo(CaptureMemo captureMemo);
	int updateCaptureMemo(CaptureMemo captureMemo);
	int deleteCaptureMemo(int id);
	

}
