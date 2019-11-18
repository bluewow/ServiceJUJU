package com.stockmarket.www.controller.interestStocks;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stockmarket.www.service.InterestStocksService;
import com.stockmarket.www.service.basic.BasicInterestStocksService;


@WebServlet("/card/list/holdinglist")
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		super.doPost(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("list", interestStocksInterface.getInterestStockList());
		request.getRequestDispatcher("holdinglist.jsp").forward(request, response);
	}

}
