package com.stockmarket.www.controller.managestocks;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stockmarket.www.service.HoldingStocksService;
import com.stockmarket.www.service.InterestStocksService;
import com.stockmarket.www.service.basic.BasicHoldingStocksService;
import com.stockmarket.www.service.basic.BasicInterestStocksService;


@WebServlet("/card/managestocks/holdinglist")
public class HoldingStocksController extends HttpServlet{

   
	private HoldingStocksService HoldingStocksInterface;
	
	@Override
	public void init() throws ServletException {
		super.init();
	}
	

	public HoldingStocksController() {
		HoldingStocksInterface = new BasicHoldingStocksService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("id");
		
		request.setAttribute("list", HoldingStocksInterface.getInterestHoldingList(userId));
		request.getRequestDispatcher("holdinglist.jsp").forward(request, response);
	}

}
