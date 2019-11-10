package com.stockmarket.www.service.repository;

public interface AnalysisInterface {
	/* 
	 * 관심도, 재무상황, 미정 이상 3가지 사항에 대한 분석을 진행한다   
	 * TODO
	 * NaverTrend Data 사용
	 * Stock 정보 사용(Not DB)
	 * 
	 */
	void content();
	
	/*
	 * 급등, 우상향, 우하향, 급락에 대한 예측을 진행한다
	 * TODO
	 * NaverTrend Data 사용
	 * Stock 정보 사용(Not DB)
	 * 
	 */
	void predict();
	
	/*
	 * 종목 가격에 대해 갱신한다
	 * TODO
	 * Server -> Client 호출 필요
	 * 
	 */
	void refreshPrice();
}
