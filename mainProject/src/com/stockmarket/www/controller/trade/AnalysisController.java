package com.stockmarket.www.controller.trade;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stockmarket.www.service.AnalysisService;
import com.stockmarket.www.service.basic.BasicAnalysisService;

@WebServlet("/card/trade/analysis")
public class AnalysisController extends HttpServlet{
	AnalysisService service;
	
	public AnalysisController() {
		service = new BasicAnalysisService();
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//캡쳐 버튼 Press 시
		String capture = request.getParameter("capture"); 
		if(capture != null) {  
			HttpSession session = request.getSession();
			int memberId = (int)session.getAttribute("id");
			
			String result = service.captureDataCrawling("095660", memberId);
			
			PrintWriter out = response.getWriter();
			out.print(result);      
			return;
		}
		
		String codeNum = request.getParameter("codeNum");
		if(codeNum != null) {
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(service.getStockName(codeNum));
			return;
		}
		
		request.getRequestDispatcher("analysis.jsp").forward(request, response);
	}
}
