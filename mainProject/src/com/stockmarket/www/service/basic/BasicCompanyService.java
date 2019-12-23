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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.stockmarket.www.dao.UpjongDao;
import com.stockmarket.www.dao.csv.CSVStockDataDao;
import com.stockmarket.www.dao.jdbc.JdbcUpjongDao;
import com.stockmarket.www.entity.Company;
import com.stockmarket.www.service.CompanyService;

public class BasicCompanyService implements CompanyService {

	private CSVStockDataDao csvStockDataDao;
	private String csvFilePath;
	private Map<String, Integer>crawlData = new HashMap<>(); //종목명, count 수
	private Map<String, Integer>crawlDataOrder = new LinkedHashMap<>(); //종목명, count 수 내림차순
	
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

 	
	@Override
	public List<Company> getCompanyListFromNaverByThema(String companyName) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<String> search(String search) {
		List<String> result = null;
		
		//1차 : 검색어 + "주식" && "종목" && "테마"의 네이버검색 결과를 종목명과 매칭한다
		//     자연어처리
		try {
			filterFirst(search);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//내림차순 정렬
		filterOrder();
		
		//2차 : 1차 결과의 리스트  1,2,3위의 네이버업종 종목리스트와 일치하는 항목만 최종결과값에 포함한다 
		try {
			result = filterSecond();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;	
	}

	private Document naverCrawling(String url) throws IOException  {
		Document doc = null;	//크롤링 결과를 담는 Document
		Response response = null; //jsoup connect 결과 반환 
		
		response = Jsoup.connect(url)
				.method(Connection.Method.GET)
				.execute();
		
		doc = response.parse();
		return doc;
	}
	
	// 1차필터
	private void filterFirst(String search) throws IOException {
		String[] keyWord = {"주식", "종목", "테마" };
		String[] removeTarget = 
			{"큐레이션", "브레이크", "디딤돌", "트레이딩", "SBS뉴스", "트레이더", "트레이",
			 "레이더", "레이튼", "플레이어", "레이시온", "오디오", "스튜디오", "키움증권 클립", 
			 "키움증권 2", "키움증권 재생중", "한국경제TV 재생", "아시아경제 https" , "아시아경제 최신",
			 "아시아경제 2", "NH투자증권 공식", "※키움증권", "레이어", "KNN뉴스", "SBSCNBC뉴스"}; //크롤링 결과의 제거 대상. 지속적인 업데이트 필요

		//TEMP CSV 파일 삭제예정 ---------------------------------------
		CSVStockDataDao data = new CSVStockDataDao();
		String Path1 = "C:\\Users\\acorn\\Documents\\프로젝트\\ServiceJUJU\\mainProject\\WebContent\\fileUpload\\KOSPI.csv";
		String Path2 = "C:\\Users\\acorn\\Documents\\프로젝트\\ServiceJUJU\\mainProject\\WebContent\\fileUpload\\KOSDAQ.csv";
		List<String> stockList = data.getColumnData(0, Path1);
		stockList.addAll(data.getColumnData(0, Path2));
		//---------------------------------------------------------
		
		for(int i = 0; i < keyWord.length; i++) {
			String str = search + " " + keyWord[i];
			String url = "https://search.naver.com/search.naver?query=" + str; 
			String text = null;

			Document doc = naverCrawling(url);
			text = doc.select("#main_pack").text(); //본문 text
			for(String s : removeTarget) {			//지정된 문자를 제거한다
				text = text.replaceAll(s, "");
			}
			
//			System.out.println(text);	//for debugging
			// <종목명, count> 저장
			for(int j = 0; j < stockList.size() ; j++) {
				String stockName = stockList.get(j);
				crawlData.put(
						stockName, 
						crawlData.get((Object)stockName)==null? 
							StringUtils.countMatches(text, stockName):
							StringUtils.countMatches(text, stockName) + crawlData.get((Object)stockName));
				
				if(crawlData.get((Object)stockName) == 0)
					crawlData.remove((Object) stockName);
			}
		}
	}
	
	private void filterOrder() {
		List<Entry<String, Integer>> list = new ArrayList<>(crawlData.entrySet());
        list.sort(Entry.<String, Integer>comparingByValue().reversed());

        for (Entry<String, Integer> entry : list) 
        	crawlDataOrder.put(entry.getKey(), entry.getValue());
        
//        System.out.println(crawlDataOrder);		//for debugging
	}
	
	// 2차필터
	private List<String> filterSecond() throws IOException {
		List<String> storageCodeNum = new ArrayList<String>();
		List<String> beforeCompany = new ArrayList<String>();	//중복제거전 회사목록
		List<String> afterCompany = new ArrayList<String>(); //중복제거된 회사목록
		
		//TEMP CSV 파일 삭제예정 ---------------------------------------
		String Path1 = "C:\\Users\\acorn\\Documents\\프로젝트\\ServiceJUJU\\mainProject\\WebContent\\fileUpload\\KOSPI.csv";
		String Path2 = "C:\\Users\\acorn\\Documents\\프로젝트\\ServiceJUJU\\mainProject\\WebContent\\fileUpload\\KOSDAQ.csv";
		CSVStockDataDao kospi = new CSVStockDataDao(Path1);
		CSVStockDataDao kosdaq = new CSVStockDataDao(Path2);
		
		for(String k : crawlDataOrder.keySet()) {
			if(kospi.searchCompany(k) != null)
				storageCodeNum.add(kospi.searchCompany(k).getCodeNum());
			if(kosdaq.searchCompany(k) != null)
				storageCodeNum.add(kosdaq.searchCompany(k).getCodeNum());
		}
	
		//TEMP 크롤링 파일 삭제예정 ---------------------------------------
		String defaultURL = "https://finance.naver.com";
		int index = 0;
		List<Integer> limit = new ArrayList<>(crawlDataOrder.values());
		
		for(String k : storageCodeNum) {
			index++;
			String url = "https://finance.naver.com/item/main.nhn?code=" + k;
			Document doc = naverCrawling(url);
			
			url = defaultURL + doc.select(".section.trade_compare > a").attr("href");
			doc = naverCrawling(url);
			
			// 업종관련 회사리스트			
			String companyList = doc.select("tr td a").text();
			String[] list = companyList.split("  ");			
			
			for(String e : crawlDataOrder.keySet()) {
				for(String m : list) {
					m = m.trim();
					if(m.equals(e)) 	//crawling data 와 업종리스트가 매칭될 경우 최종 회사 리스트에 추가된다
						beforeCompany.add(e);
				}
			}
			
			if(index >= 3) {	//count 수 기준으로 1, 2, 3 등까지 적용한다
				if(limit.get(index - 1) == limit.get(index)) 
					continue;

				break;
			}
		}
		
		for(String comp : beforeCompany) {
			if(!afterCompany.contains(comp))
				afterCompany.add(comp);
		}
			
//		for(String v : afterCompany)	// for debugging
//			System.out.println(v);
	
		return afterCompany;
	}

	
	

}
