package com.stockmarket.www.controller.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.dao.jdbc.JdbcMemberDao;
import com.stockmarket.www.entity.CommunityBoard;
import com.stockmarket.www.service.CommunityBoardService;
import com.stockmarket.www.service.basic.BasicCommunityBoardService;

@WebServlet("/card/board/stock_reg_board")
public class RegBoardJsonController extends HttpServlet  {

	private CommunityBoardService communityBoardService;
	
	public RegBoardJsonController() {
		communityBoardService = new BasicCommunityBoardService();
	}
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object tempId = session.getAttribute("id");
		int writerId = -1;
		
		if(tempId != null)
			writerId = (Integer)tempId;
		MemberDao memberDao = new JdbcMemberDao();
		String writerNickname = memberDao.getMember(writerId).getNickName();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		
		
		CommunityBoard insertBoard = new CommunityBoard(title, content, writerNickname);

		int result = communityBoardService.insertCommunityBoard(insertBoard);
		
		response.setCharacterEncoding("UTF-8"); // UTP-8로 보내는 코드
		response.setContentType("text/html;charset=UTF-8"); // UTP-8로 보내는 코드
		PrintWriter out = response.getWriter();
		System.out.println("result :"+result);
		out.print(result);
	}
	
	
	
	
}
