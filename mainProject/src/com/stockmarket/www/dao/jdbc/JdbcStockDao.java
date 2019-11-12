package com.stockmarket.www.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import com.stockmarket.www.dao.StockDao;
import com.stockmarket.www.ett.Member;
import com.stockmarket.www.ett.Stock;

public class JdbcStockDao implements StockDao{

	@Override
	public Stock getStockCodeNum(int codeNum) {
		String sql = "SELECT * FROM STOCK WHERE CODENUM=" + codeNum;
		
		try {
			Statement statement = JdbcDaoContext.getStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			if(rs.next()) {
				Stock stock = new Stock();
				stock.setCodeNum(rs.getInt("CODENUM"));
				stock.setName(rs.getString("NAME"));
				return stock;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
