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
	 *  실시가 종목 가격 갱신 및 공유
	*/ 
	void refreshStockPrice(String pathOfKospi, String pathOfKosdaq);
}
