package com.stockmarket.www.controller.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.stockmarket.www.entity.CommunityBoard;
import com.stockmarket.www.entity.Member;
import com.stockmarket.www.service.CommunityBoardService;
import com.stockmarket.www.service.basic.BasicCommunityBoardService;

@WebServlet("/card/board/detail1111")
public class DetailController extends HttpServlet {

	private CommunityBoardService communityBoardService;

	public DetailController() {
		communityBoardService = new BasicCommunityBoardService();
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int boardId = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		Object userId = session.getAttribute("id");

		CommunityBoard list = communityBoardService.getBoard(boardId);
		request.setAttribute("detail", communityBoardService.getBoard(boardId));
		
		request.getRequestDispatcher("../../card/board/detail.jsp").forward(request, response);

//			
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			super.doPost(request, response);
	}
}
