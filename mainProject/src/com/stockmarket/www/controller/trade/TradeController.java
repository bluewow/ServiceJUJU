package com.stockmarket.www.controller.trade;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.stockmarket.www.service.TradeService;
import com.stockmarket.www.service.basic.BasicTradeService;

//TODO
//Return false 처리

@WebServlet("/card/trade/trade")
public class TradeController extends HttpServlet{
	TradeService service;
	
	public TradeController() {
		service = new BasicTradeService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int memberId = (int)session.getAttribute("id");
		
		//일봉, 주봉, 월봉 - ajax 요청
		String date = request.getParameter("date"); 
		if(date != null) {
			dateButtonStatus(date, request, response);
			return;
		}

		//가격정보 reflesh - ajax 요청
		String price = request.getParameter("replaceEvent");
		if(price != null) { //price is only "on"
			//매수-매도 실행
			tradeProcess(memberId, request);
			refleshPrice(request, response, memberId, "095660");
			return;
		}

		//종목가격 
		//종목 등락률
		int sum = service.getStockAssets(memberId, "095660");
		int qty = service.getQty(memberId, "095660");
		//Get 종목정보, 해당 종목의 보유 자산, 평균 매수가, 보유수량
		request.setAttribute("companyName", "네오위즈");
		request.setAttribute("myAssets", sum);
		request.setAttribute("aveAssets", sum / qty);
		request.setAttribute("myQuantity", qty);
		
		request.getRequestDispatcher("trading.jsp").forward(request, response);
	}

	private boolean tradeProcess(int memberId, HttpServletRequest request) {
		String trade = request.getParameter("button");

		if(trade != null) {
			String qty = null;
			switch(trade) {
			case "buy": //구매
				qty = request.getParameter("Purse/Sold");
				if(qty != null && qty != "") 
					service.setQty(memberId, "095660", Integer.parseInt(qty), 20000);
				break;
			case "sell": //매도
				qty = request.getParameter("Purse/Sold");
				if(qty != null && qty != "")
					service.setQty(memberId, "095660", -Integer.parseInt(qty), 20000);
				break;
			default:
				break;
			}
		}
		return true;
	}

	private void dateButtonStatus(String date, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int[][] data = {};
		// Converting multidimensional array into JSON
		switch(date) {
		case "일봉":	
			int[][] data1 = {{1, 2}, {3, 4}, {4, 5}};
			data = data1;
			break;
		case "주봉":
			int[][] data2 = {{5, 4}, {4, 3}, {2, 1}};
			data = data2;
			break;
		case "월봉":	
			int[][] data3 = {{10, 20}, {40, 50}, {20, 30}};
			data = data3;
			break;
		}
		
		Gson gson = new Gson();
        String json = gson.toJson(data);
        
		PrintWriter out = response.getWriter();
		out.write(json);      
	}

	private void refleshPrice(HttpServletRequest request, HttpServletResponse response, int memberId, String codeNum) throws IOException {
		int[] data = new int[3]; 
		
		int sum = service.getStockAssets(memberId, codeNum);
		int qty = service.getQty(memberId, codeNum);
		
		//해당 종목의 보유자산
		data[0] = sum;
		//평균매수
		data[1] = sum / qty;
		//보유수량
		data[2] = qty;
		
		Gson gson = new Gson();
        String json = gson.toJson(data);
        
		PrintWriter out = response.getWriter();
		out.write(json);  
	}
	
	//TEST
	public static void main(String[] args) {
	        int[] numbers = {1, 1, 2, 3, 5, 8, 13};
	        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

	        // Create a new instance of Gson
	        Gson gson = new Gson();

	        // Convert numbers array into JSON string.
	        String numbersJson = gson.toJson(numbers);

	        // Convert strings array into JSON string
	        String daysJson = gson.toJson(days);
	        System.out.println("numbersJson = " + numbersJson);
	        System.out.println("daysJson = " + daysJson);

	        // Convert from JSON string to a primitive array of int.
	        int[] fibonacci = gson.fromJson(numbersJson, int[].class);
	        for (int number : fibonacci) {
	            System.out.print(number + " ");
	        }
	        System.out.println("");

	        // Convert from JSON string to a string array.
	        String[] weekDays = gson.fromJson(daysJson, String[].class);
	        for (String weekDay : weekDays) {
	            System.out.print(weekDay + " ");
	        }
	        System.out.println("");
	        
	        // Converting multidimensional array into JSON
	        int[][] data = {{1, 2}, {3, 4}, {4, 5}};
	        String json = gson.toJson(data);
	        System.out.println("Data = " + json);
	        
	        // Convert JSON string into multidimensional array of int.
	        int[][] dataMap = gson.fromJson(json, int[][].class);
	        for (int[] i : dataMap) {
	            for (int j : i) {
	                System.out.print(j + " ");
	            }
	            System.out.println("");
	        }
	        
	        
	}
}
