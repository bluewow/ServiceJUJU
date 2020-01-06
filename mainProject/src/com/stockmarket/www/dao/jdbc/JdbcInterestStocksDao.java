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
		JdbcDaoContext daoContext = new JdbcDaoContext();
		Statement st = null;
		ResultSet rs = null;

		List<InterestStocks> stocklist = new ArrayList<>();

		String sql = "SELECT * FROM STOCK ";

		try {
			st = daoContext.getStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				String stockName = rs.getString("NAME");

				InterestStocks interestStocks = new InterestStocks(stockName);
				stocklist.add(interestStocks);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			daoContext.close(rs, st);
		}
		return stocklist;
	}

	@Override
	public int insert(int memberId, String StockCode) {
		int result = 0;
		
		String sql = "INSERT INTO INTEREST_STOCK (MEMBER_ID, STOCK_ID) "
				+ "VALUES (?, ?)";
		try {
			JdbcDaoContext daoContext = new JdbcDaoContext();
			PreparedStatement statement = daoContext.getPreparedStatement(sql);
			
			statement.setInt(1, memberId);
			statement.setString(2, StockCode);
			result = statement.executeUpdate();
			
			statement.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}

	@Override
	public int delete(int memberId, String StockCode) {
		
		String sql = "delete interest_stock where member_id=? and stock_id=?";

		int result = 0;

		try {
			JdbcDaoContext daoContext = new JdbcDaoContext();
			PreparedStatement st = daoContext.getPreparedStatement(sql);
			
			st.setInt(1, memberId);
			st.setString(2, StockCode);
			result = st.executeUpdate();
			
			st.close();
 			//System.out.println("StockCode : " + StockCode +"," + "memberId" + memberId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		 
		}
		return result;
	}

	@Override
	public String getInterestStocks(int id) {
		String sql = "SELECT * FROM INTEREST_STOCK WHERE MEMBER_ID=?";
		JdbcDaoContext daoContext = new JdbcDaoContext();
		PreparedStatement pst =null;
		ResultSet rs = null;
		
		
				
				
		return null;
	}
}
