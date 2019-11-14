package com.stockmarket.www.controller.board;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/card/board/board")
public class BoardController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Map<String, Object>> list = new ArrayList<>();

		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
		String sql = "SELECT * FROM NOTICE";
	
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "ACORNGROUP1", "month100man");
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

				while(rs.next()) {
					
				Map<String, Object> notice = new HashMap();
				
				notice.put("id", rs.getInt("ID"));
				notice.put("title", rs.getString("TITLE"));
				notice.put("writerId", rs.getString("WRITER_ID"));
				notice.put("regdate", rs.getDate("REGDATE"));
				notice.put("hit", rs.getInt("HIT"));
				notice.put("content", rs.getInt("CONTENT"));
				notice.put("stockcode", rs.getInt("STOCKCODE"));
				
				list.add(notice);
			}; 
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
			request.setAttribute("list", list);
			request.getRequestDispatcher("board.jsp").forward(request, response);

		
	}
}
