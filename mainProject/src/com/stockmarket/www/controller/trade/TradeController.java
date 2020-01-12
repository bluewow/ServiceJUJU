package com.stockmarket.www.controller.trade;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.stockmarket.www.controller.system.AppContext;
import com.stockmarket.www.dao.KoreaStocksDao;
import com.stockmarket.www.dao.jdbc.JdbckoreaStocksDao;
import com.stockmarket.www.entity.CurStock;
import com.stockmarket.www.entity.StockDetail;
import com.stockmarket.www.entity.TradeCardData;
import com.stockmarket.www.service.TradeService;
import com.stockmarket.www.service.basic.BasicTradeService;

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
		
		String codeNum = request.getParameter("codeNum");
		if(codeNum != null) {
			Map<String, CurStock> stocks = AppContext.getStockMarket();
			CurStock curStock = null; 
			TradeCardData data = null;
			
			//크롤링 데이터가 유효한 경우 curStock 을 set 한다
			for(String getCodeNume : stocks.keySet()) {
				if(getCodeNume.equals(codeNum)) {
					curStock = new CurStock(); 
					curStock.setSellQuantityMap(stocks.get(getCodeNume).getSellQuantityMap());
					curStock.setBuyQuantityMap(stocks.get(getCodeNume).getBuyQuantityMap());
					break;
				}
			}

			if(curStock == null) {
				data = new TradeCardData();
				data.setvMoney(service.getAssets(memberId));
				data.setQuantity(service.getQty(memberId, codeNum));
			}
				
			if(curStock != null) {
				data = new TradeCardData(
						curStock.getSellQuantityMap().size(), curStock.getBuyQuantityMap().size());
				data.setvMoney(service.getAssets(memberId));
				data.setQuantity(service.getQty(memberId, codeNum));
				int i = 0;
				for(Integer sell : curStock.getSellQuantityMap().keySet()) {
					data.setSellPrice(i, sell);
					data.setSellQuantity(i, curStock.getSellQuantityMap().get(sell));
					i++;
				}
				i = 0;
				for(Integer buy : curStock.getBuyQuantityMap().keySet()) {
					data.setBuyPrice(i, buy);
					data.setBuyQuantity(i, curStock.getBuyQuantityMap().get(buy));
					i++;
				}
//				System.out.println(data.toString());
			}
			
			Gson gson = new Gson();
			String json = gson.toJson(data);
			PrintWriter out = response.getWriter();
			out.print(json);
			return;
		}
		
		//매수-매도 실행 
		String price = request.getParameter("replaceEvent");
		if(price != null) { //price is only "on"
			int result = 0;
			result = tradeProcess(memberId, request);
			JSONObject json = updateResultPrice(memberId, request, result);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(json);
			return;
		}

		request.getRequestDispatcher("trading.jsp").forward(request, response);
	}

/////////////////////////////////////////////////////////
/////////////////// 매수 - 매도 관련 함수 /////////////////////
/////////////////////////////////////////////////////////
	
	private int tradeProcess(int memberId, HttpServletRequest request) {
		String trade = request.getParameter("button");
		String qty = request.getParameter("Purse/Sold");
		String codeNum = request.getParameter("codeNum");
		 
		//result - 0:ok, 1:vmoney부족, 2: 거래정지목록, 
		//		   3:장내시간이 아님, 4:수량이 0이하가 되는 경우 거래x, 
		//		   5:수량이 0이 되는 경우 6:보유종목이 아닌경우 거래x
		if(trade != null && qty != null && qty != "") {
			switch(trade) {
			case "buy": //구매
				if(service.checkVmoney(memberId, Integer.parseInt(qty), 20000) != 0)
					return 1;
				if(service.checkHaveStock(memberId, codeNum) == false)
					service.addHaveStock(memberId, codeNum);
				
				service.tradeBuySell(memberId, codeNum, Integer.parseInt(qty), 20000);
				break;
			case "sell": //매도
				if(service.checkHaveStock(memberId, codeNum) == false)
					return 6;
				if(service.checkMinusHaveStock(memberId, codeNum, Integer.parseInt(qty))) 
					return 4;
				
				service.tradeBuySell(memberId, codeNum, -Integer.parseInt(qty), 20000);
				
				//매도후 수량이 0인 경우 db 삭제
				if(service.checkZeroHaveStock(memberId, codeNum)) { 
					service.delHaveStock(memberId, codeNum);
					return 5;
				}
				break;
			default: //"pass"
				return 99;
			}
		}
		return 0;
	}

	

	private JSONObject updateResultPrice(int memberId,  HttpServletRequest request, int result) {
		HashMap<Object, Object>map = new HashMap<>();
		String codeNum = request.getParameter("codeNum");
		int sum = service.getStockAssets(memberId, codeNum);
		int qty = service.getQty(memberId, codeNum);
		String companyName = service.getCompanyName(codeNum);
		
		map.put("avgPrice", qty == 0 ? 0 : sum / qty);
		map.put("quantity", qty);
		map.put("vMoney", (int) service.getAssets(memberId));
		map.put("result", result);
		map.put("codeNum", codeNum);
		map.put("name", companyName);
		JSONObject data = new JSONObject(map);
		return data;
	}
}
