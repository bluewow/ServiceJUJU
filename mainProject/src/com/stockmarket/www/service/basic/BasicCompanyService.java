package com.stockmarket.www.service.basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.stockmarket.www.dao.csv.CSVStockDataDao;
import com.stockmarket.www.entity.Company;
import com.stockmarket.www.service.CompanyService;

public class BasicCompanyService implements CompanyService {

	private CSVStockDataDao csvStockDataDao;
	private String csvFilePath;

	// ====================================
	/* private CompanyService companyService; */

	public BasicCompanyService(String csvFilePath) {

		csvStockDataDao = new CSVStockDataDao(csvFilePath);
		/* companyService = new BasicCompanyService(csvFilePath); */
	}

	@Override
	public Company searchCompany(String search) {

		return csvStockDataDao.searchCompany(search);

	}

	@Override
	public void setFilePath(String csvFilePath) {
		this.csvFilePath = csvFilePath;

	}

//	/* 네이버 업종 크롤링 */
	@Override
	public void stockIndustryCrawling() {
//		String upjongUrl = "https://finance.naver.com/sise/sise_group.nhn?type=upjong";
//		Document doc = null;
//
//		try {
//			doc = Jsoup.connect(upjongUrl).get();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		// tr tag에 업종 링크를 선택
//
//		Elements Industrytable = doc.select("#contentarea_left");
//
//		Iterator<Element> IndustryAtag = Industrytable.select("tr a").iterator();
//		Iterator<Element> IndustryName = Industrytable.select("tr a").iterator();
//		// IndustryAtag.next().attr("href") => a 링크만 뽑아냄
//		// IndustryAtag.next() => 업종 명만 뽑아냄
//		ArrayList<String> upjongAtag = new ArrayList<>();
//		ArrayList<String> upjonName = new ArrayList<>();
//		int cnt = 0;
//
//		while (IndustryName.hasNext()) {
//			upjonName.add(IndustryName.next().text());
//		}
//
//		while (IndustryAtag.hasNext()) {
//			upjongAtag.add(IndustryAtag.next().attr("href"));
//		}
//		// System.out.println(upjongAtag); ==== 여기까지 출력 됨....
//
//		// 1. 업종명과 링크를 얻는다.
//		// 2. 업종명에 해당하는 링크를 타고 들어가서 상세 종목명을 얻는다.
//
//		ArrayList<String> detailIndustryList = new ArrayList<>();
//		List<String> data = new ArrayList<>();
//
//		for (int i = 0; i < 5; i++) {
//			String upjongDetailUrl = "https://finance.naver.com" + upjongAtag.get(i);
//			Document upjongDetail = null;
//
//			detailIndustryList.add(upjonName.get(i));
//			// 업종명을 detailIndustryList에 추가
//
//			try {
//				upjongDetail = Jsoup.connect(upjongDetailUrl).get();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//			Elements detailIndustryTable = upjongDetail.select("tbody a");
//			Iterator<Element> detailIndustryName = detailIndustryTable.select("a").iterator();
//			
//			 while (detailIndustryName.hasNext()) {
//				detailIndustryList.add(detailIndustryName.next().text());
//			}
//			 detailIndustryList.add("\n");
//			
////			for (int j = 0; j < detailIndustryList.size(); j++) {
////				detailIndustryList.remove("");
////			}
//			// 공백을 제거 코드
//		}
//		System.out.println(detailIndustryList);
//		
//		//data.addAll(detailIndustryList);
//		//System.out.println(detailIndustryList);
//		String upjong="UPJONG";
//		
//		System.out.println(data);
//		try {
//			csvStockDataDao.makeCSV(upjong, data);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			//https://wildpup.cafe24.com/archives/82
//		}
	}
	
	@Override
	public List<Company> getCompanyListFromNaverByThema(String companyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void stockIndustryCrawling() {
		// TODO Auto-generated method stub
		
	}

}
