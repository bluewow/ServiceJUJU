package com.stockmarket.www.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stockmarket.www.controller.system.AppContext;
import com.stockmarket.www.dao.InterestViewDao;
import com.stockmarket.www.dao.StockDao;
import com.stockmarket.www.entity.CurStock;
import com.stockmarket.www.entity.InterestView;

public class JdbcInterestViewDao implements InterestViewDao {
	StockDao stockDao;

	@Override
	public List<InterestView> getInterestStockList(int id) {
		JdbcDaoContext daoContext = new JdbcDaoContext();
		
		stockDao = new JdbcStockDao();
		PreparedStatement st = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM INTEREST_STOCK_VIEW WHERE ID = ?";

		List<InterestView> interestlist = new ArrayList<>();

		List<CurStock> list = new ArrayList<>();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (AppContext.getStockMarket() != null) {
					list.addAll(AppContext.getStockMarket());
				}
			}
		}).start();

		try {
			st = daoContext.getPreparedStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();

			while (rs.next()) {
				String stockName = rs.getString("STOCKNAME");
				for (CurStock data : list) {
					if (stockDao.getStockCodeNum(stockName).equals(data.getCodeNum())) {
						String price = data.getPrice();
						String percent = data.getPercent();
						InterestView interestview = new InterestView(stockName, price, percent);
						interestlist.add(interestview);
						break;
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} // oracle.jdbc.driver.OracleDriver 객체를 만듬
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			daoContext.close(rs, st);
		}
		return interestlist;
	}
}
