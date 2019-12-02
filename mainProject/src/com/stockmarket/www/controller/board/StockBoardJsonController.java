package com.stockmarket.www.controller.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.stockmarket.www.entity.CommunityBoard;
import com.stockmarket.www.service.CommunityBoardService;
import com.stockmarket.www.service.basic.BasicCommunityBoardService;

@WebServlet("/card/board/stock_board_list")
public class StockBoardJsonController extends HttpServlet {
	
	private CommunityBoardService communityBoardService;
	
	
	public StockBoardJsonController() {
		communityBoardService = new BasicCommunityBoardService();
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int page = 1;
		String field = "TITLE";
		String query= "";
		String stockName= "";
		
		String page_ = request.getParameter("p");
		if(page_ != null && !page_.equals(""))
			page = Integer.parseInt(page_);
		
		String field_ = request.getParameter("f");
		if(field_ !=null && !field_.equals(""))
			field = field_;

		String query_ = request.getParameter("q");
		if(query_ !=null && !query_.equals(""))
			query = query_;
		
		String stockName_ = request.getParameter("s");
		if(stockName_ !=null && !stockName_.equals(""))
			stockName = stockName_;
		List<CommunityBoard> list = communityBoardService.getCommunityBoardList(page,field,query,stockName);
	      Gson gson = new Gson();
	      String json = gson.toJson(list);
	      
	      response.setCharacterEncoding("UTF-8");
	       response.setContentType("text/html; charset=UTF-8");
	       
	       
	       PrintWriter out = response.getWriter();
	       out.write(json); 
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
	}
}
