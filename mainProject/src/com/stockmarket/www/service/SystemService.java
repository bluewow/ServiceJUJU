package com.stockmarket.www.service;

public interface SystemService {
	/*
	 * TODO
	 * 하루에 한번 KOSPI.csv KOSDAQ.csv 파일을 갱신한다.
	 * 장종료후 주식데이터 갱신
	 * log 시스템
	 * 
	*/
	
	/*
	 *  5분간격으로 현재가 데이터를 갱신한다. 
	 *  갱신된 데이터를 공유한다
	*/ 
	void refreshStockPrice(String pathOfKospi, String pathOfKosdaq);
}
