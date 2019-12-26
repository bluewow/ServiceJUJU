package com.stockmarket.www.controller.captureMemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.stockmarket.www.entity.CaptureMemo;
import com.stockmarket.www.entity.CaptureMemoView;
import com.stockmarket.www.service.CaptureMemoService;
import com.stockmarket.www.service.basic.BasicCaptureMemoService;

@WebServlet("/card/capturememo/captureMemo")
public class CaptureMemoController extends HttpServlet {
	private CaptureMemoService service;
	
	public CaptureMemoController() {
		service = new BasicCaptureMemoService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CaptureMemoView> captureMemoViews = service.getCaptureMemoList();
		request.setAttribute("list", captureMemoViews);
		
		request.getRequestDispatcher("captureMemo.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
	

	}
}