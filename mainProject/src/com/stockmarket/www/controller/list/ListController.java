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
import com.stockmarket.www.entity.KoreaStocks;
import com.stockmarket.www.service.CompanyService;
import com.stockmarket.www.service.basic.BasicCompanyService;

import oracle.jdbc.proxy.annotation.Post;

@WebServlet("/card/company/list")
public class ListController extends HttpServlet {

	private CompanyService companyService;
	private List<KoreaStocks> searchCompanyList;

	public ListController() {
		companyService = new BasicCompanyService();
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
		
//	==== 검색된 회사 정보를 찾아 jsp에 전달하는 코드
		

		String companyName = "";
		String companyName_ = request.getParameter("companyName");
		
		if (companyName_ != null && !companyName_.equals("")) {
			companyName = companyName_;
		} else {
			request.getRequestDispatcher("list.jsp").forward(request, response);
		    return;
		}
		
		searchCompanyList = new ArrayList<KoreaStocks>();
		
		//System.out.println(companyService.searchCompanyNames("커피"));
		
		List<String> list =	companyService.searchCompanyNames(companyName);
		// 기현이 형의 검색 알고리즘을 통해 회사 이름을 List에 String 형식으로 담는다.
		
		for (int i = 0; i < list.size(); i++) {
			searchCompanyList.add(companyService.searchCompany(list.get(i))); 
		}
		// 위에 담긴 list에 있는 회사 종목을 하나씩 꺼내어 검색한 뒤에 회사정보를 koreaStocks 엔티티로  리턴한다.
		// koreaStocks 엔티티를 searchCompanyList에 담는다.
		
		// 로그인 유무 확인
		HttpSession session = request.getSession();
		Object tempId = session.getAttribute("id");
		int id = -1;

		if (tempId != null)
			id = (Integer) tempId;
		
		
		
		//System.out.println(id);
		
		request.setAttribute("search", searchCompanyList);
		request.setAttribute("logIn", id);
		
		//회사 이름 = > list String
//		for (String string : list) {
//			System.out.println(string);
//		}
		
		
		request.getRequestDispatcher("list.jsp").forward(request, response);
		

	}
}
