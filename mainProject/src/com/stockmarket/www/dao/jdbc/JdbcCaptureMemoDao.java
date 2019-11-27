package com.stockmarket.www.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stockmarket.www.dao.CaptureMemoDao;
import com.stockmarket.www.entity.CaptureMemo;
import com.stockmarket.www.entity.CommunityBoard;

public class JdbcCaptureMemoDao implements CaptureMemoDao {
	

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
	public List<CaptureMemo> getCaptureMemoList(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int id) {
		int result = 0;
		String sql = "DELETE NOTICE WHERE ID=?";
		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"ACORNGROUP1", "month100man");
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id);
			
			result = st.executeUpdate();
			
			st.close();
			con.close();
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
		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"ACORN", "newlec");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, captureMemo.getTitle());
			
			st.setInt(2, captureMemo.getId());
			
			result = st.executeUpdate();
			
			st.close();
			con.close();
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



