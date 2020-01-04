package com.stockmarket.www.controller.list;

import java.io.IOException;
import java.io.PrintWriter;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/card/company/list-json")
public class ListControllerJSONController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
		
		//System.out.println("불러지니?");
		
		HttpSession session = request.getSession();
		Object tempId = session.getAttribute("id");
		int id = -1;
		
		if(tempId != null)
			id = (Integer)tempId;
		
		String attention = request.getParameter("attention");
		System.out.println(attention);
		
		System.out.println(id);
				
	}
	
	
	
}
