package com.stockmarket.www.controller.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.stockmarket.www.service.SearchCompanyService;
import com.stockmarket.www.service.basic.BasicSearchCompanyService;

@WebServlet("/card/search/search")
public class SearchCompanyController extends HttpServlet{

	private SearchCompanyService seachCompanyService;
	
	public SearchCompanyController() {
		seachCompanyService = new BasicSearchCompanyService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	====CSV 파일을 읽고, 검색된 회사 정보를 찾아 jsp에 전달하는 코드
		
		String search = "";

		ServletContext application = request.getServletContext();
		String csvUrlPath = "/fileUpload/KOSPI.csv";
		String csvFilePath = application.getRealPath(csvUrlPath);
		
		String search_ = request.getParameter("search");
		if (search_ !=null && !search_.equals("")) {
			search = search_;
		}

		request.setAttribute("search", seachCompanyService.searchCompany(search, csvFilePath));
		
		
//==== (아래는) 크롤링을 위한 코드====================================
//https://finance.naver.com/sise/lastsearch2.nhn(네이버 증권)에서 검색 상위종목 4개 추출		
		
		
		String urlKeywordTop4 = "https://finance.naver.com/sise/lastsearch2.nhn";

		Document doc = null;
		
		String[] recommendKeyword = new String[4];
		doc = Jsoup.connect(urlKeywordTop4).get();
		
		Elements element = doc.select("tbody");
		Iterator<Element> ie1 = element.select("a.tltle").iterator();
		
		for (int i = 0; i < 4; i++) {
			recommendKeyword[i] = ie1.next().text();
//			System.out.println(recommendKeyword[i]);
		}
		
		request.setAttribute("recommendKeyword", recommendKeyword);
		request.getRequestDispatcher("search.jsp").forward(request, response);
		
		/* 네이버  업종별 크롤링을 위한 코드========================================*/		
		
		String urlSector = "https://finance.naver.com/sise/theme.nhn";
		
		Document docurlSector = null;
		
		//List<Map<String, Object>> newsList = new ArrayList<>();
		
		
		docurlSector = Jsoup.connect(urlSector).get();
		
		Elements elementSector = docurlSector.select(".col_type1");
		//System.out.println(elementSector); //  
		
		Elements SectorAtag = docurlSector.select(".col_type1 a");
		//System.out.println(SectorAtag); // a 태그만 출력
		
		Iterator<Element> ie1Atag = elementSector.select("a").iterator();
//		Iterator<Element> ie1Sector = SectorAtag.select("a").iterator();
		
//		Iterator<Element> ie1Sector = elementSector.select("a").iterator();
		
		//System.out.println(SectorAtag.get(1).attr("href"));
		//참고: https://finance.naver.com/ + 주소값
		int cnt = 0;
//		while (ie1Sector.hasNext()) {
//			Map<Object, Object> sector = new HashMap();
//			sector.put("섹터명",ie1Sector.next());
//			sector.put("주소명",SectorAtag.get(cnt).attr("href"));
//			
//			
// 
//		}
		
//		while (ie1Atag.hasNext()) {
//			Map<Object, Object> sector = new HashMap();
//			sector.put("섹터명",ie1Atag.next().text());
//			sector.put("주소명","https://finance.naver.com" + SectorAtag.get(cnt).attr("href"));
//		};
		
	}
}
