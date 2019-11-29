package com.stockmarket.www.controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.RemoteEndpoint.Basic;

import com.stockmarket.www.service.basic.BasicLoginService;

//TODO
//제재 회원 처리
//session 시간이 지날경우 화면상에 로그아웃 표시 또는 팝업창을 구현해야한다 

@WebServlet("/login")
public class loginController extends HttpServlet{
	
	BasicLoginService loginService;
	
	public loginController() {
		loginService = new BasicLoginService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//returnURL 처리 생략
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String userEmail = request.getParameter("userEmail");
		String pwd = request.getParameter("pwd");
		//LOGOUT / LOGIN / ELSE
//		String status = request.getHeader("status");
//
//		
//		if(status == null) {
//			request.getRequestDispatcher("main.jsp").forward(request, response);
//			return;
//		}
		
		//로그아웃 시도
//		if(status.equals("LOGOUT")) {
//			if(session != null)
//				session.invalidate();
//			
//			request.getRequestDispatcher("main.jsp").forward(request, response);
//			return;
//		}
		
		//로그인 시도
//		if(status.equals("LOGIN")) {
			//회원가입상태를 체크
			if(isValidLogInfo(userEmail, pwd)) {
				int id = loginService.getIdbyEmail(userEmail);
				//id 값을 session 에 저장한다
				if(id != 0)
					session.setAttribute("id", id);
				
				request.getRequestDispatcher("main.jsp").forward(request, response);
				
			} else {
				if(session != null)
					session.invalidate();
				
				request.getRequestDispatcher("main.jsp").forward(request, response);
			}
//		}
		
	}

	private boolean isValidLogInfo(String email, String pwd) {

		if(email == null || email.equals("")) 
			return false;

		if(loginService.isValidMember(email, pwd)) 

			return true;
		else 
			return false;
	}
}
