package com.stockmarket.www.controller.managestocks;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stockmarket.www.entity.CurStock;
import com.stockmarket.www.entity.InterestView;
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
		
			request.setAttribute("list", interestViewInterface.getInterestViewList(userId));
			
//			for(InterestView data : interestlist)
//			{
//				System.out.println(data.toString());
//			}
			request.getRequestDispatcher("interestlist.jsp").forward(request, response);	
		

		
	}
}
