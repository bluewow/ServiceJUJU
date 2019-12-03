package com.stockmarket.www.controller.trade;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stockmarket.www.entity.CaptureMemo;
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
		String capture = request.getParameter("capture"); 
		
		//캡쳐 버튼 Press 시
		if(capture != null) {  
			HttpSession session = request.getSession();
			int memberId = (int)session.getAttribute("id");
			
			CaptureMemo result = service.captureDataCrawling("095660", memberId);
			System.out.println(result.toString());
			
			PrintWriter out = response.getWriter();
			out.print(result);      
			return;
		}
		
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
