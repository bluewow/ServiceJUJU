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
import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.dao.jdbc.JdbcMemberDao;
import com.stockmarket.www.entity.CommunityBoard;
import com.stockmarket.www.service.CommunityBoardService;
import com.stockmarket.www.service.basic.BasicCommunityBoardService;

@WebServlet("/card/board/Reply")
public class ReplyController extends HttpServlet {

	private CommunityBoardService communityBoardService;

	public ReplyController() {
		communityBoardService = new BasicCommunityBoardService();
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("받았어?");
		HttpSession session = request.getSession();
		Object tempId = session.getAttribute("id");
		int writerId = -1;
		
		if(tempId != null)
			writerId = (Integer)tempId;
		MemberDao memberDao = new JdbcMemberDao();
		String wrtierNickname = memberDao.getMember(writerId).getNickName();
		
		String reContent = request.getParameter("reContent");
		String boardId_ = request.getParameter("boardId");
		int boardId = Integer.parseInt(boardId_);
		System.out.println(reContent+boardId+wrtierNickname);
		CommunityBoard insertReply = new CommunityBoard(reContent, wrtierNickname, boardId);

		int result = communityBoardService.insertReply(insertReply);
		
		response.setCharacterEncoding("UTF-8"); // UTP-8로 보내는 코드
		response.setContentType("text/html;charset=UTF-8"); // UTP-8로 보내는 코드
		PrintWriter out = response.getWriter();
		System.out.println("result :"+result);
		out.print(result);
	}
}
