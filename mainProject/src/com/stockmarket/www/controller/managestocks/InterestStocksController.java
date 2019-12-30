package com.stockmarket.www.controller.managestocks;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stockmarket.www.service.InterestStocksService;
import com.stockmarket.www.service.InterestViewService;
import com.stockmarket.www.service.basic.BasicInterestStocksService;
import com.stockmarket.www.service.basic.BasicInterestViewService;

@WebServlet("/card/managestocks/interestlist")
public class InterestStocksController extends HttpServlet {

	private InterestStocksService interestStocksInterface;
	private InterestViewService interestViewInterface;

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public InterestStocksController() {
		interestStocksInterface =
				new BasicInterestStocksService();
		interestViewInterface = new BasicInterestViewService();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//        String stockName = request.getParameter("stockname");
//        System.out.println(stockName);
//		super.doPost(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("id");
		boolean firstSetting = true;
		
		String delStockName = request.getParameter("delStockName");
		String  codeNum = request.getParameter("codeNum");
		interestStocksInterface.deleteStock(userId,delStockName);
		
		if(codeNum != null || firstSetting) {
			firstSetting =false;
			request.setAttribute("list", interestViewInterface.getInterestViewList(userId));
			request.getRequestDispatcher("interestlist.jsp").forward(request, response);	
		}
		

		
	}
}
