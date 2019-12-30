package com.stockmarket.www.controller.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.dao.jdbc.JdbcMemberDao;
import com.stockmarket.www.entity.CommunityBoard;
import com.stockmarket.www.service.CommunityBoardService;
import com.stockmarket.www.service.basic.BasicCommunityBoardService;

@WebServlet("/card/board/favorie")
public class FavoriteJsonController extends HttpServlet {

	private CommunityBoardService communityBoardService;

	public FavoriteJsonController() {
		communityBoardService = new BasicCommunityBoardService();
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		String status = request.getParameter("status");
		Object tempId = session.getAttribute("id");
		int loginId = -1;
		if (tempId != null)
			loginId = (Integer) tempId;
		
		if(status=="check") {

		CommunityBoard selectFavoriteBoard = new CommunityBoard(boardId, loginId, status);
		
		int result = communityBoardService.selectFavoriteBoard(selectFavoriteBoard);
		
		System.out.println(result);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(result);
		};
//			
	}
}
