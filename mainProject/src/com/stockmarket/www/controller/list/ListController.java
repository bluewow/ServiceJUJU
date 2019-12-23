package com.stockmarket.www.controller.list;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.stockmarket.www.entity.Company;
import com.stockmarket.www.service.CompanyService;
import com.stockmarket.www.service.basic.BasicCompanyService;

import oracle.jdbc.proxy.annotation.Post;

@WebServlet("/card/company/list")
public class ListController extends HttpServlet {

	private CompanyService companyService;
	private List<Company> searchCompanyList;

	public ListController() {
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//==== (아래는) 크롤링을 위한 코드====================================
//https://finance.naver.com/sise/lastsearch2.nhn(네이버 증권)에서 검색 상위종목 4개 추출		
		
		String urlKeywordTop4 = "https://finance.naver.com/sise/lastsearch2.nhn";
		
		Document doc = null;
		
		String[] recommendKeyword = new String[3];
		doc = Jsoup.connect(urlKeywordTop4).get();
		
		Elements element = doc.select("tbody");
		Iterator<Element> ie1 = element.select("a.tltle").iterator();
		
		for (int i = 0; i < 3; i++) {
			recommendKeyword[i] = ie1.next().text();
		}
		
		request.setAttribute("recommendKeyword", recommendKeyword);
		
//	====CSV 파일을 읽고, 검색된 회사 정보를 찾아 jsp에 전달하는 코드
		ServletContext application = getServletContext();
		String csvUrlPath = "/fileUpload/KOSPI.csv";
		String csvFilePath = application.getRealPath(csvUrlPath);

		companyService = new BasicCompanyService(csvFilePath);

		String companyName = "";
	

		String companyName_ = request.getParameter("companyName");
		
		
		
		if (companyName_ != null && !companyName_.equals("")) {
			companyName = companyName_;
		} else {
			request.getRequestDispatcher("list.jsp").forward(request, response);
		    return;
		}
		
		searchCompanyList = new ArrayList<Company>();
		
		if (companyService.searchCompany(companyName) != null) {
			searchCompanyList.add(companyService.searchCompany(companyName));
		}

		
		request.setAttribute("search", searchCompanyList);


		/*
		 * 네이버 테마별 크롤링을 위한
		 * 코드==========================================================================
		 */

		// request.setAttribute("sectorList",companyService.getCompanyListFromNaverByThema(companyName));
		// System.out.println(companyService.getCompanyListFromNaverByThema(companyName));

		List<String> test =	companyService.search(companyName);
		//회사 이름 = > list String
		
		for (String string : test) {
			System.out.println(string);
		}
		
		
		request.getRequestDispatcher("list.jsp").forward(request, response);

	}
}
