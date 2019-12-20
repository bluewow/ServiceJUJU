package com.stockmarket.www.service;

import java.util.ArrayList;
import java.util.List;

import com.stockmarket.www.entity.Company;

public interface CompanyService {

	Company searchCompany(String search);
	/*
	 * 사용자의 입력을 받아 dataStroage 안에 있는 KOSPI.CSV 파일에 
	 * 사용자가 입력한 회사명, 종목코드, 홈페이지 주소를 Company(entity)에 담아 리턴한다.
	 * 
	*/

	void setFilePath(String csvFilePath);

	List<Company> getCompanyListFromNaverByThema(String companyName);
	
	void stockIndustryCrawling();
	
	/*
	 * search 알고리즘을 사용하여 검색어와 관련된 회사목록을 추출한다
	 * input
	 * 	search : 검색어
	 * 
	 * 문제점
	 * - 1차 필터에서 CJ대한통운 과 같은 단어는 CJ, CJ대한통운으로 결과같이 나온다 
	 * - 조국과 같은 "테마주"는 업종에서 필터링 되지 않는다.
	 */
	public List<String> search(String search);
	
	
}
