package com.stockmarket.www.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.stockmarket.www.dao.StockDao;

public class JdbcStockDao implements StockDao {

	@Override
	public String getStockName(String codeNum) {
		String sql = "SELECT * FROM STOCK WHERE CODENUM=?";
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = JdbcDaoContext.getPreparedStatement(sql);
			pst.setString(1, codeNum);
			rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getString("NAME");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String getStockCodeNum(String name) {
		String sql = "SELECT * FROM STOCK WHERE name=?";
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = JdbcDaoContext.getPreparedStatement(sql);
			pst.setString(1, name);
			rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getString("CODENUM");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// 단위 검사
	public static void main(String[] args) {
		JdbcStockDao stockDao = new JdbcStockDao();

		System.out.println(stockDao.getStockName("005380"));
		System.out.println(stockDao.getStockCodeNum("현대차"));
	}
}
