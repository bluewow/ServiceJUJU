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

@WebServlet("/card/capturememo")
public class CaptureMemoController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";
		String sql = "SELECT MEMO,TITLE,REGDATE FROM CAPTURE_MEMO";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"ACORNGROUP1", "month100man");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			List<Map<String, Object>> list = new ArrayList<>();
			
			while(rs.next()){ 
				
				Map<String, Object> captureMemo = new HashMap<>();
				
				captureMemo.put("memo", rs.getInt("MEMO"));
				captureMemo.put("title", rs.getString("TITLE"));
			captureMemo.put("regdate", rs.getDate("REGDATE"));
		
				
				list.add(captureMemo);		
			}

			rs.close();
			st.close();
			con.close();
			
			request.setAttribute("list", list);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request
			.getRequestDispatcher("./card/capturememo/captureMemo.jsp") 
			.forward(request, response);
		
	}
}
	
		
		
		
		
		
		
		
		
		
		

