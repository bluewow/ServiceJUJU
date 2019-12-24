package com.stockmarket.www.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.stockmarket.www.dao.UpjongDao;
import com.stockmarket.www.entity.Upjong;

public class JdbcUpjongDao implements UpjongDao {

	@Override
	public List<Upjong> getList() {
		
		return null;
	}

	@Override
	public int insert(List<Upjong> list) {
		int result = 0;
		
		String sql = "INSERT INTO Upjong (UPJONG, STOCKNAME) "
				+ "VALUES (?, ?)";
		
		try {
			JdbcDaoContext daoContext = new JdbcDaoContext();
			PreparedStatement statement = daoContext.getPreparedStatement(sql);
			for(int i = 0 ; i <list.size(); i++) {
				statement.setString(1, list.get(i).getUpjong());
				statement.setString(2, list.get(i).getStockName());
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
		String sql = "DELETE FROM UPJONG";
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
