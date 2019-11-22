package com.stockmarket.www.controller.managestocks;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stockmarket.www.service.InterestStocksService;
import com.stockmarket.www.service.basic.BasicInterestStocksService;

@WebServlet("/card/managestocks/interestlist")
public class InterestStocksController extends HttpServlet {

	private InterestStocksService interestStocksInterface;

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public InterestStocksController() {
		interestStocksInterface = new BasicInterestStocksService();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		super.doPost(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String result = (String) session.getAttribute("loginId");

		if (result == null)
			response.sendRedirect("/error?code=2");
		else
			response.sendRedirect("main");

		request.setAttribute("list", interestStocksInterface.getInterestStockList());
		request.getRequestDispatcher("interestlist.jsp").forward(request, response);
	}

}
