package com.stockmarket.www.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import com.stockmarket.www.dao.StockDao;
import com.stockmarket.www.entity.Member;
import com.stockmarket.www.entity.Stock;

public class JdbcStockDao implements StockDao{
	

	@Override
	public String getStockName(String codeNum) {
		String sql = "SELECT * FROM STOCK WHERE CODENUM=?";
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			statement = JdbcDaoContext.getPreparedStatement(sql);
			statement.setString(1, codeNum);
			rs = statement.executeQuery();
			
			if(rs.next()) {
				return rs.getString("NAME");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	
	@Override
	public String getStockCodeNum(String name) {
		String sql = "SELECT * FROM STOCK WHERE name=?";
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			statement = JdbcDaoContext.getPreparedStatement(sql);
			statement.setString(1, name);
			rs = statement.executeQuery();
			
			if(rs.next()) {
				return rs.getString("CODENUM");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
     //단위 검사
	public static void main(String[] args) {
		JdbcStockDao stockDao = new JdbcStockDao();
		
		System.out.println(stockDao.getStockName("005380"));
		System.out.println(stockDao.getStockCodeNum("현대차"));
	}
}
