package com.stockmarket.www.controller.search;

import java.io.IOException;
import java.util.Iterator;

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
		request.getRequestDispatcher("search.jsp").forward(request, response);
		
//==== (아래는) 크롤링을 위한 코드====================================
		
		String url = "http://www.financipe.com/snc";
		Document doc = null;
		
		String[] recommendKeyword = new String[4];
		doc = Jsoup.connect(url).get();
		
		Elements element = doc.select("div#cloud_container");
		Iterator<Element> ie1 = element.select("#div_cloud_word_0").iterator();
		
		
		
		
		while (ie1.hasNext()) {
			System.out.println(ie1.next().text());
		}
		
		
		
	}
}
