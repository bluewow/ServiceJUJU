package com.stockmarket.www.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stockmarket.www.service.CommunityBoardService;
import com.stockmarket.www.service.basic.BasicCommunityBoardService;

@WebServlet("/card/board/community_board")
public class CommunityBoardController extends HttpServlet {
	
	private CommunityBoardService communityBoardService;
	
	
	public CommunityBoardController() {
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
		
		request.setAttribute("CommunityBoard", communityBoardService.getCommunityBoardList(page,field,query,stockName)); // 컨트롤러가 할 일은 데이터를 준비하는 일
		request.getRequestDispatcher("/card/board/community_board.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		super.doPost(request, response);
	}

}
