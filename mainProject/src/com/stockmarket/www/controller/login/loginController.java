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

@WebServlet("/login")
public class loginController extends HttpServlet{
	
	BasicLoginService loginService;
	
	public loginController() {
		loginService = new BasicLoginService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//returnURL 처리 생략
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String pwd = request.getParameter("pwd");
		HttpSession session = request.getSession();
		
		if(isValidLogInfo(userId, pwd)) {
			session.setAttribute("loginId", userId);
			request.getRequestDispatcher("main.jsp").forward(request, response);
		} else {
			if(session != null)
				session.invalidate();

			request.getRequestDispatcher("main.jsp").forward(request, response);
		}
	}

	private boolean isValidLogInfo(String userId, String pwd) {
		//TODO 
		//제재 회원
		
		if(userId == null && userId.equals("")) 
			return false;

		if(loginService.isValidMember(userId, pwd)) 
			return true;
		else 
			return false;
	}
}
