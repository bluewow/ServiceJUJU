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

import sun.swing.SwingUtilities2.Section;

@WebServlet("/card/com/search-json")
public class ListControllerJSONController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("불러지니?");
		
		HttpSession session = request.getSession();
		Object tempId = session.getAttribute("id");
		//랭크 참조..
		
		int id = -1;
		
		if(tempId != null)
			id = (Integer)tempId;
		
		String companyName = request.getParameter("cn");
		
		System.out.println(id);
		System.out.println(companyName);
		
				
	}
	
	
	
}
