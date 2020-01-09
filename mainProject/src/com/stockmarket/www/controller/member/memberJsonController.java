package com.stockmarket.www.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stockmarket.www.service.MemberService;
import com.stockmarket.www.service.basic.BasicMemberService;


@WebServlet("/card/board/member")
public class memberJsonController extends HttpServlet {

	private MemberService memberService;

	public memberJsonController() {
		memberService = new BasicMemberService();
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	super.doGet(request, response);
}
		

	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String title = request.getParameter("profileImg");
		String content = request.getParameter("content");
		String status = request.getParameter("status");
		String boardIds = request.getParameter("boardId");
		String stockCode = request.getParameter("stockCode");

		super.doPost(request, response);
	}
}
