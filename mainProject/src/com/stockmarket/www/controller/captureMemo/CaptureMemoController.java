package com.stockmarket.www.controller.captureMemo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stockmarket.www.dao.jdbc.JdbcDaoContext;

@WebServlet("/card/capturememo/captureMemo")
public class CaptureMemoController extends HttpServlet {
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		request
			.getRequestDispatcher("captureMemo.jsp") 
			.forward(request, response);
		
	}
}