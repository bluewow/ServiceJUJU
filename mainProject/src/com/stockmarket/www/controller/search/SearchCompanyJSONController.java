package com.stockmarket.www.controller.search;

import java.io.IOException;
import java.io.PrintWriter;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/card/search/search-logIncheck")
public class SearchCompanyJSONController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("id") == null) {
			response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>\r\n" +
                    "    window.alert(\"인증이 필요한 요청입니다.. \\r\\n\" +\r\n" +
                    "             \"로그인페이지로 이동합니다..\");\r\n" +
                    "    \r\n" +
                 
                    "</script>");
		}
		
		request.getRequestDispatcher("search.jsp").forward(request, response);
				
	}
	
	
	
}
