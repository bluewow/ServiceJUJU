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
import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.entity.StockDetail;
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
			int result = 0;
			//매수-매도 실행
			result = tradeProcess(memberId, request);
			updateResultPrice(request, response, memberId, "095660", result);
			return;
		}

		//종목가격 
		//종목 등락률
		int sum = service.getStockAssets(memberId, "095660");
		int qty = service.getQty(memberId, "095660");
		//Get 종목정보, 해당 종목의 보유 자산, 평균 매수가, 보유수량
		request.setAttribute("companyName", "네오위즈");
		request.setAttribute("myAssets", service.getAssets(memberId));
		request.setAttribute("aveAssets", qty == 0 ? 0 : (sum / qty));
		request.setAttribute("myQuantity", qty);
		
		request.getRequestDispatcher("trading.jsp").forward(request, response);
	}

/////////////////////////////////////////////////////////
/////////////////// 매수 - 매도 관련 함수 /////////////////////
/////////////////////////////////////////////////////////
	
	private int tradeProcess(int memberId, HttpServletRequest request) {
		String trade = request.getParameter("button");
		String qty = request.getParameter("Purse/Sold");
		 
		//result - 0:ok, 1:vmoney부족, 2: 거래정지목록, 
		//		   3:장내시간이 아님, 4:수량이 0이하가 되는 경우 거래x, 
		//		   5:수량이 0이 되는 경우 6:보유종목이 아닌경우 거래x
		if(trade != null && qty != null && qty != "") {
			switch(trade) {
			case "buy": //구매
				if(service.checkVmoney(memberId, Integer.parseInt(qty), 20000) != 0)
					return 1;
				if(service.checkHaveStock(memberId, "095660") == false)
					service.addHaveStock(memberId, "095660");
				
				service.tradeBuySell(memberId, "095660", Integer.parseInt(qty), 20000);
				break;
			case "sell": //매도
				if(service.checkHaveStock(memberId, "095660") == false)
					return 6;
				if(service.checkMinusHaveStock(memberId, "095660", Integer.parseInt(qty))) 
					return 4;
				
				service.tradeBuySell(memberId, "095660", -Integer.parseInt(qty), 20000);
				
				//매도후 수량이 0인 경우 db 삭제
				if(service.checkZeroHaveStock(memberId, "095660")) { 
					service.delHaveStock(memberId, "095660");
					return 5;
				}
				break;
			default:
				break;
			}
		}
		return 0;
	}

	

	private void updateResultPrice(HttpServletRequest request, HttpServletResponse response, int memberId, String codeNum, int result) throws IOException {
		int[] data = new int[4]; 
		
		int sum = service.getStockAssets(memberId, codeNum);
		int qty = service.getQty(memberId, codeNum);
		
		//평균단가
		data[0] = qty == 0 ? 0 : sum / qty;
		//보유수량
		data[1] = qty;
		//가상머니
		data[2] = (int) service.getAssets(memberId);
		//결과 
		data[3] = result;
		
		Gson gson = new Gson();
        String json = gson.toJson(data);
        
		PrintWriter out = response.getWriter();
		out.write(json);  
	}

/////////////////////////////////////////////////////////
/////////////////// 일봉/주봉/월봉 관련 함수 /////////////////////
/////////////////////////////////////////////////////////
	
	private void dateButtonStatus(String date, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int[][] data = null;
		// Converting multidimensional array into JSON
		switch(date) {
		case "일봉":	
			List<StockDetail> list = service.getDailyPrice("095660");
			System.out.println(list.size());
			data = new int[list.size()][2];
			for(int i = 0; i < list.size(); i++) {
				data[i][0] = Integer.parseInt(list.get(i).getBizdate());
				data[i][1] = list.get(i).getClose_val();
			}
			break;
//		case "주봉":
//			int[][] data2 = {{5, 4}, {4, 3}, {2, 1}};
//			data = data2;
//			break;
//		case "월봉":	
//			int[][] data3 = {{10, 20}, {40, 50}, {20, 30}};
//			data = data3;
//			break;
		}
		
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
