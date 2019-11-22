package com.stockmarket.www.service;

import com.stockmarket.www.entity.Company;

public interface SearchCompanyService {

	Company searchCompany(String search, String csvFilePath);
	/*
	 * 사용자의 입력을 받아 dataStroage 안에 있는 KOSPI.CSV 파일에 
	 * 사용자가 입력한 회사명, 종목코드, 홈페이지 주소를 Company(entity)에 담아 리턴한다.
	 * 
	*/
}
