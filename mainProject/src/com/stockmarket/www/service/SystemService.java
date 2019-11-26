package com.stockmarket.www.service;

import com.stockmarket.www.entity.RecordAsset;

public interface SystemService {
	/*
	 * TODO
	 * 
	 * 장종료후 주식데이터 갱신
	 * log 시스템
	 * 토, 일요일 및 장쉬는 날 tracking
	 * singleTone 생성하여 path 설정
	 *  - systemService 적용
	 *  - CSV DAO 적용
	*/
	
	/*
	 *  실시가 종목 가격 갱신 및 공유
	*/ 
	void refreshStockPrice(String pathOfKospi, String pathOfKosdaq);
	
	/*
	 *  하루에 한번 KOSPI.csv KOSDAQ.csv 파일을 갱신한다. 
	*/
	boolean updateMarket(String market);
	
	int insertRecordAsset(RecordAsset recordAsset);
}
