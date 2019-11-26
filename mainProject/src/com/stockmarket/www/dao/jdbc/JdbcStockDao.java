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

		try {
			PreparedStatement statement = JdbcDaoContext.getPreparedStatement(sql);
			statement.setString(1, codeNum);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				return rs.getString("NAME");
			}
			rs.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public String getStockCodeNum(String name) {
		String sql = "SELECT * FROM STOCK WHERE name=?";

		try {
			PreparedStatement statement = JdbcDaoContext.getPreparedStatement(sql);
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				return rs.getString("CODENUM");
			}
			rs.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
