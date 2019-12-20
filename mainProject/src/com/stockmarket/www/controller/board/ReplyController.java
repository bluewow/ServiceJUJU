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
		

		HttpSession session = request.getSession();
		String reContent = request.getParameter("reContent");
		String boardId_ = request.getParameter("boardId");
		String replyIds = request.getParameter("replyId");
		String status = request.getParameter("status");
		
	
		if(status==null) {
			Object tempId = session.getAttribute("id");
			int writerId = -1;
			
			if(tempId != null)
				writerId = (Integer)tempId;
			MemberDao memberDao = new JdbcMemberDao();
			String writerNickname = memberDao.getMember(writerId).getNickName();
			
			int boardId = Integer.parseInt(boardId_);
			CommunityBoard insertReply = new CommunityBoard(reContent, writerNickname, boardId);
	
			int result = communityBoardService.insertReply(insertReply);
			
			int lastReplyNum = communityBoardService.lastReplyNum(boardId);
			
			
	
			response.setCharacterEncoding("UTF-8"); // UTP-8로 보내는 코드
			response.setContentType("text/html;charset=UTF-8"); // UTP-8로 보내는 코드
			PrintWriter out = response.getWriter();
			System.out.println("result :"+result);
			System.out.println("lastReplyNum :"+lastReplyNum);
			out.print(lastReplyNum);
			
		} else if(status.equals("del")){
			int replyId = -1;
			replyId = Integer.parseInt(replyIds);	
			
			CommunityBoard deleteReply = new CommunityBoard(replyId, "del");
			int result = communityBoardService.deleteReply(replyId);
			
			response.setCharacterEncoding("UTF-8"); // UTP-8로 보내는 코드
			response.setContentType("text/html;charset=UTF-8"); // UTP-8로 보내는 코드
			PrintWriter out = response.getWriter();
			
			out.print(result);
			
			
		} else if(status.equals("modi")) {
			
			System.out.println("모다이~~~");
		} 
	}
}
