package com.stockmarket.www.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import com.stockmarket.www.dao.InterestStocksDao;
import com.stockmarket.www.entity.InterestStocks;

public class JdbcInterestStocksDao implements InterestStocksDao {

	JdbcStockDao jdbcstockdao = new JdbcStockDao();
	
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
			
			rs.close();
			st.close();
			
			return stocklist;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insert(int id, String email, String stockName) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int delete(int id, String delStockName) {
		int result = 0;
         String sql ="delete interest_stock where member_id=? and stock_id=?";
         
         try {
        	PreparedStatement st = JdbcDaoContext.getPreparedStatement(sql);
        	String stockName = jdbcstockdao.getStockCodeNum(delStockName);
 			st.setInt(1,id);
 			st.setString(2,stockName);
 						
 			result = st.executeUpdate();

// 			System.out.println("stockname:" + stockName +"," + id + delStockName);
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
