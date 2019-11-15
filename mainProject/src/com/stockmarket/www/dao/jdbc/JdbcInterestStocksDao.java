package com.stockmarket.www.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stockmarket.www.dao.InterestStocksDao;
import com.stockmarket.www.entity.InterestStocks;
import com.stockmarket.www.entity.Member;

public class JdbcInterestStocksDao implements InterestStocksDao {

	
	
	@Override
	public List<InterestStocks> getInterestStockList() {
	
		String sql = "SELECT * FROM STOCK ";
		List<InterestStocks> stocklist = new ArrayList<>();
		
		try {
			
			Statement st = JdbcDaoContext.getStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				String stockName = rs.getString("NAME");

				InterestStocks interesctStocks = new InterestStocks(stockName);
				stocklist.add(interesctStocks);
			}
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return stocklist;
	}


	@Override
	public int delete(int id) {

		
		
		return 0;
	}


}
