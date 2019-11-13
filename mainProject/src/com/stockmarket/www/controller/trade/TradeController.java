package com.stockmarket.www.controller.trade;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stockmarket.www.service.TradeService;

//TODO
//Return false 처리

@WebServlet("/card/trading/trade")
public class TradeController extends HttpServlet{
	TradeService service;
	
	public TradeController() {
		service = new TradeService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int dummy = 0;
		
		//종목정보 from 검색페이지
//		request.setAttribute("companyName", request.getAttribute("companyName"));
		request.setAttribute("companyName", "네오위즈");
		
		//종목가격 
		//종목 등락률
		//일봉, 주봉, 월봉
		
		//자산상황 from 회원테이블(DB)
//		request.setAttribute("assets", service.getAssets(id));
		request.setAttribute("myAssets", "50,000");
		
		//매수-매도
		String trade = request.getParameter("trade");
		if(trade != null) {
			String qty = null;
			
			switch(trade) {
			//구매수량, 매도수량
			case "매       수":
				qty = request.getParameter("PurchaseQty");
				if(qty != null && qty != "") {
					service.setQty(dummy, Integer.parseInt(qty));
				}
				break;
			case "매       도":
				qty = request.getParameter("SoldQty");
				if(qty != null && qty != "")
					service.setQty(dummy, -Integer.parseInt(qty));
				break;
			default:
				break;
			}
		}

		//보유수량
//		request.setAttribute("assets", service.getQty());
		request.setAttribute("myQuantity", "130");
		
		request.getRequestDispatcher("trading.jsp").forward(request, response);
	}
}
