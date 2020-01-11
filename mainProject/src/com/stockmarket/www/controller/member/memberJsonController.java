package com.stockmarket.www.controller.member;

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
import com.stockmarket.www.entity.Member;
import com.stockmarket.www.service.MemberService;
import com.stockmarket.www.service.basic.BasicMemberService;

@WebServlet("/member-profile")
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String loginNickname = request.getParameter("loginNickname");
		String profileImg = request.getParameter("profileImg");
		String currentPwd = request.getParameter("currentPwd");
		String newPwd = request.getParameter("newPwd");

		
		if (loginNickname != null) {
			Object tempId = session.getAttribute("id");
			
			int id = -1;

			if (tempId != null)
				id = (Integer) tempId;
			MemberDao memberDao = new JdbcMemberDao();
			int ProfileImgNum = memberDao.getMember(id).getProfileImg();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(ProfileImgNum);
			// 프로필이미지값이 널이면 비밀번호 수정
		} else if (profileImg == null) {
			System.out.println(currentPwd);
			System.out.println(newPwd);
//			Object tempId = session.getAttribute("id");
//			
//			int writerId = -1;
//
//			if (tempId != null)
//				writerId = (Integer) tempId;
//			MemberDao memberDao = new JdbcMemberDao();
//			String writerNickname = memberDao.getMember(writerId).getNickName();
//
//			int result = MemberService.insertCommunityBoard(insertBoard);
//
//			response.setCharacterEncoding("UTF-8"); // UTP-8로 보내는 코드
//			response.setContentType("text/html;charset=UTF-8"); // UTP-8로 보내는 코드
//			PrintWriter out = response.getWriter();
//			out.print(result);

			// 프로필이미지값이 들어있으면 프로필 이미지 수정
		} else if (profileImg != null) {
			Object tempId = session.getAttribute("id");
			
			int id = -1;

			if (tempId != null)
				id = (Integer) tempId;
			
			int profileImg_ = Integer.parseInt(profileImg);
			int result = memberService.updateMember(id, profileImg_, "imgChange");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(result);
		}
	}
}
