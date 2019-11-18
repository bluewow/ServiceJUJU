package com.stockmarket.www.dao.jdbc;

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
	public String getStockName(int codeNum) {
		String sql = "SELECT * FROM STOCK WHERE CODENUM=" + codeNum;
		
		try {
			Statement statement = JdbcDaoContext.getStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			if(rs.next()) {
				return rs.getString("NAME");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
