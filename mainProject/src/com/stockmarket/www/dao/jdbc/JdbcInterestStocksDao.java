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

				InterestStocks interesctStocks = new InterestStocks(stockName);
				stocklist.add(interesctStocks);
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
	public int insert(int id, String email, String stockName) {
		return 0;
	}

	@Override
	public int delete(int id, String delStockName) {
		JdbcDaoContext daoContext = new JdbcDaoContext();
		PreparedStatement st = null;
		
		String sql = "delete interest_stock where member_id=? and stock_id=?";

		int result = 0;

		try {
			st = daoContext.getPreparedStatement(sql);
			String stockName = jdbcstockdao.getStockCodeNum(delStockName);
			st.setInt(1, id);
			st.setString(2, stockName);

			result = st.executeUpdate();

// 			System.out.println("stockname:" + stockName +"," + id + delStockName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			daoContext.close(st);
		}
		return result;
	}
}
