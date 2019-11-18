package com.stockmarket.www.service;

public interface SearchCompanyService {

	
	
	String[] searchCompany(String search);
	/*
	 * 사용자의 입력을 받아 dataStroage 안에 있는 KOSPI.CSV 파일에 
	 * 사용자가 입력한 회사명, 종목코드, 홈페이지 주소를 스트링배열에 담아 리턴한다.
	 * 
	*/
}
