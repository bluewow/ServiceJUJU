package com.stockmarket.www.controller.interestStocks;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stockmarket.www.service.InterestStocksService;
import com.stockmarket.www.service.repository.InterestStocksInterface;


@WebServlet("/card/list")
public class InterestStocksController extends HttpServlet {

	private InterestStocksInterface interestStocksInterface;

	public InterestStocksController() {
		interestStocksInterface = new InterestStocksService();
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

}
