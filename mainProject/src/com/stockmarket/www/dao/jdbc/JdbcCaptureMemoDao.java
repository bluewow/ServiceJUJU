package com.stockmarket.www.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stockmarket.www.dao.CaptureMemoDao;
import com.stockmarket.www.entity.CaptureMemo;
import com.stockmarket.www.entity.CommunityBoard;

public class JdbcCaptureMemoDao implements CaptureMemoDao {
	
	
	@Override
	public java.util.List<CaptureMemo> getList(int page) {
		
		
		List<CaptureMemo> list = new ArrayList<>();
		
		String sql = "SELECT MEMO,TITLE,REGDATE FROM CAPTURE_MEMO";
		
		try {
			
		Statement st = JdbcDaoContext.getStatement();
			ResultSet rs = st.executeQuery(sql);
			
			
			while(rs.next()){ 
				
				
				String memo = rs.getString("MEMO");
				String title = rs.getString("TITLE");
				Date regdate = rs.getDate("DATE");
				
				CaptureMemo captureMemo = new CaptureMemo(title,regdate,memo);
				
				
				
				list.add(captureMemo);		
			}
			
			rs.close();
			st.close();
			
			
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insert(CaptureMemo captureMemo) {
		int result = 0;
		
		String sql = "INSERT INTO CAPTURE_MEMO(TITLE)"
				+"VALUES(?)";
		
		try {
			
			PreparedStatement st = JdbcDaoContext.getPreparedStatement(sql);
			st.setString(1, captureMemo.getTitle());
		
			
			result = st.executeUpdate();
			
			st.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int delete(int id) {
		int result = 0;
		String sql = "DELETE NOTICE WHERE ID=?";
		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";
		
		try {
			
			PreparedStatement st = JdbcDaoContext.getPreparedStatement(sql);
			
			st.setInt(1, id);
			
			result = st.executeUpdate();
			
			st.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int update(CaptureMemo captureMemo) {
		int result = 0;
		
		String sql = "UPDATE NOTICE SET TITLE=? WHERE ID=?";
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			PreparedStatement st = JdbcDaoContext.getPreparedStatement(sql);
			st.setString(1, captureMemo.getTitle());
			
			st.setInt(2, captureMemo.getId());
			
			result = st.executeUpdate();
			
			st.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}


	
	



}



