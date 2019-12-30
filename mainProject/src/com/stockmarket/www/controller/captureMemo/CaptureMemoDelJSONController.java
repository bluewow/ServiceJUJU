package com.stockmarket.www.controller.captureMemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stockmarket.www.entity.CaptureMemo;
import com.stockmarket.www.entity.CaptureMemoView;
import com.stockmarket.www.service.CaptureMemoService;
import com.stockmarket.www.service.basic.BasicCaptureMemoService;

@WebServlet("/card/capturememo/captureMemo-json-del")
public class CaptureMemoDelJSONController extends HttpServlet {
	private CaptureMemoService service;

	public CaptureMemoDelJSONController() {
		service = new BasicCaptureMemoService();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String memoId = request.getParameter("memoId");
		
		int result = service.delete(Integer.parseInt(memoId));
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
	    
	    PrintWriter out = response.getWriter();
		out.print(result);
	}
}