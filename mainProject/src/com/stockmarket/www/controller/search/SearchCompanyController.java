package com.stockmarket.www.controller.search;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stockmarket.www.service.SearchCompanyService;
import com.stockmarket.www.service.basic.BasicSearchCompanyService;

@WebServlet("/card/searchCompany")
public class SearchCompanyController extends HttpServlet{

	private SearchCompanyService seachCompanyService;
	
	public SearchCompanyController() {
		seachCompanyService = new BasicSearchCompanyService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = "";
		
		ServletContext application = request.getServletContext();
		String csvUrlPath = "/KOSPI.csv";
		String csvFilePath = application.getRealPath(csvUrlPath);
		
		
		String search_ = request.getParameter("search");
		if (search_ !=null && !search_.equals("")) {
			search = search_;
		}
		
		request.setAttribute("search", seachCompanyService.searchCompany(search, csvFilePath));
		request.getRequestDispatcher("searchCompany").forward(request, response);
	}
}
