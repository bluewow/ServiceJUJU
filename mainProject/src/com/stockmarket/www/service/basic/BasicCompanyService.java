package com.stockmarket.www.service.basic;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
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

	@Override
	public List<Company> getCompanyListFromNaverByThema(String companyName) {
		/*
		 * 네이버 업종별 크롤링을 위한
		 * 코드==========================================================================
		 */

		String urlSector = "https://finance.naver.com/sise/theme.nhn";

		Document docurlSector = null;

		try {
			docurlSector = Jsoup.connect(urlSector).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Elements elementSector = docurlSector.select(".col_type1");
		// System.out.println(elementSector); //

		Elements SectorAtag = docurlSector.select(".col_type1 a");
		// System.out.println(SectorAtag); // a 태그만 출력

		// Iterator<Element> ie1Atag = elementSector.select("a").iterator();
		Iterator<Element> sectorIterator = SectorAtag.select("a").iterator();

		// 참고: https://finance.naver.com/ + 주소값
		int cnt = 0;

		ArrayList<String> sectorNames = new ArrayList<String>();
		ArrayList<String> sectorHomepageURLs = new ArrayList<String>();
		
		// 테마 목록에서 기업정보 얻어내기
		
		int sectorIndex = 0;
		
		for(int i = 0; i<sectorNames.size(); i++)
			if (sectorNames.get(i).equals(companyName)) {
				sectorIndex = i;
				break;
			}
		
		
		// company 목록 가져오기
		
		if (sectorIndex !=0) {
			List<Company> companyArrayList = new ArrayList<Company>();
			
			
			String sectorUrl = "https://finance.naver.com/" +sectorHomepageURLs.get(sectorIndex);
			Document sectorUrlDoc = null;
			
			try {
				sectorUrlDoc = Jsoup.connect(sectorUrl).get();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			
			
			
			Elements stockElement = sectorUrlDoc.select(".type_5");
			Iterator<Element> stockElement_ = stockElement.select("td a").iterator();
							
			
			
			
			
			while (stockElement_.hasNext()) {
				
				companyArrayList.add(csvStockDataDao.searchCompany(stockElement_.next().text()));
				
				//1. 가져온 기업이름으로 'StockDataDao'에서 search 한다.
				
				//2. 그 결과로 Company
				
				
			}
			
			HashSet<Company> companyArrayList2 = new HashSet<Company>(companyArrayList); // HashSet에 arr데이터 삽입
			ArrayList<Company> companyArrayList3 = new ArrayList<Company>(companyArrayList2); // 중복이 제거된 HashSet을 다시 ArrayList에 삽입

			return companyArrayList3;
		}
		/*
		 * List<Company> companyArrayList = new ArrayList<Company>();
		 * 
		 * 
		 * String sectorUrl = "https://finance.naver.com/"
		 * +sectorHomepageURLs.get(sectorIndex); Document sectorUrlDoc = null;
		 * 
		 * try { sectorUrlDoc = Jsoup.connect(sectorUrl).get(); } catch (IOException e)
		 * {
		 * 
		 * e.printStackTrace(); }
		 * 
		 * 
		 * 
		 * 
		 * Elements stockElement = sectorUrlDoc.select(".type_5"); Iterator<Element>
		 * stockElement_ = stockElement.select("td a").iterator();
		 * 
		 * 
		 * 
		 * 
		 * 
		 * while (stockElement_.hasNext()) {
		 * 
		 * companyArrayList.add(csvStockDataDao.searchCompany(stockElement_.next().text(
		 * )));
		 * 
		 * //1. 가져온 기업이름으로 'StockDataDao'에서 search 한다.
		 * 
		 * //2. 그 결과로 Company
		 * 
		 * 
		 * }
		 * 
		 * HashSet<Company> companyArrayList2 = new HashSet<Company>(companyArrayList);
		 * // HashSet에 arr데이터 삽입 ArrayList<Company> companyArrayList3 = new
		 * ArrayList<Company>(companyArrayList2); // 중복이 제거된 HashSet을 다시 ArrayList에 삽입
		 * 
		 * return companyArrayList3;
		 */
		return null;

	}

}
