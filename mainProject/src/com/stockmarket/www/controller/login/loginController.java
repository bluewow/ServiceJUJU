package com.stockmarket.www.controller.login;

import java.io.IOException;
import java.io.PrintWriter;

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
//session 시간이 지날경우 화면상에 로그아웃 표시 또는 팝업창을 구현해야한다 (session 만료처리) 


@WebServlet("/login")
public class loginController extends HttpServlet{
	
	BasicLoginService loginService;
	
	public loginController() {
		loginService = new BasicLoginService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String logout = request.getParameter("loginStatus");

		if(logout == null) 
			response.sendRedirect("/main.jsp");
		
		//로그아웃 시도
		if(logout.equals("logout")) {
			//invalidate 는 session 객체를 무효화 시킨다. 메모리에는 여전히 남아있어 sessionScope 로 참조됨.
			if(request.isRequestedSessionIdValid()) {
				session.setAttribute("id", null);
				session.invalidate(); //sessionScope.id 가 유효함??

				response.sendRedirect("/main.jsp");
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String userEmail = request.getParameter("userEmail");
		String pwd = request.getParameter("pwd");
		
		//로그인 시도
		//회원가입상태를 체크
		if(isValidLogInfo(userEmail, pwd)) {
			int id = loginService.getIdbyEmail(userEmail);
			//id 값을 session 에 저장한다
			if(id != 0)
				session.setAttribute("id", id);
		} 

		response.sendRedirect("/main.jsp");
		
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
