package com.stockmarket.www.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.stockmarket.www.dao.KosdaqDao;
import com.stockmarket.www.entity.Kosdaq;

public class JdbcKosdaqDao implements KosdaqDao {

	@Override
	public List<Kosdaq> getList() {
		
		return null;
	}

	@Override
	public int insert(List<Kosdaq> list) {
		int result = 0;
		
		String sql = "INSERT INTO KOSDAQ (COMPANYNAME, STOCKCODE, SECTORS, MAINPRODUCT, STOCKEDDAY, SETTLEMENTMONTH, REPRESENTATIVENAME, WEBSITE, LOCATION) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			JdbcDaoContext daoContext = new JdbcDaoContext();
			PreparedStatement statement = daoContext.getPreparedStatement(sql);
			for(int i = 0 ; i <list.size(); i++) {
				statement.setString(1, list.get(i).getCompanyName());
				statement.setString(2, list.get(i).getStockCode());
				statement.setString(3, list.get(i).getSectors());
				statement.setString(4, list.get(i).getMainProduct());
				statement.setString(5, list.get(i).getStockedDay());
				statement.setString(6, list.get(i).getSettlementMonth());
				statement.setString(7, list.get(i).getRepresentativeName());
				statement.setString(8, list.get(i).getWebsite());
				statement.setString(9, list.get(i).getLocation());
				result = statement.executeUpdate();
			}
			statement.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int delete() {
		int result = 0;
		String sql = "DELETE FROM KOSDAQ";
		JdbcDaoContext context = new JdbcDaoContext();
		try {
			PreparedStatement st = context.getPreparedStatement(sql);
			result = st.executeUpdate();
			context.close(st);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
