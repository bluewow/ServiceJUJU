package com.stockmarket.www.controller.trade;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/card/trade/analysis")
public class AnalysisController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//종목정보
		request.setAttribute("companyName", "네오위즈");
		
		//% 정보
		request.setAttribute("predict_1", "13");
		request.setAttribute("predict_2", "37");
		request.setAttribute("predict_3", "21");
		request.setAttribute("predict_4", "29");
		
		//analysis 정보
		request.setAttribute("content_1", "13");
		request.setAttribute("content_2", "37");
		request.setAttribute("content_3", "50");
		
		//arrow 방향 
		request.setAttribute("direction", "up");
//		request.setAttribute("direction", "down");
		
		request.getRequestDispatcher("analysis.jsp").forward(request, response);
	}
}
