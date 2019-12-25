package com.stockmarket.www.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stockmarket.www.dao.UpjongDao;
import com.stockmarket.www.entity.Member;
import com.stockmarket.www.entity.Upjong;

public class JdbcUpjongDao implements UpjongDao {

	@Override
	public String getUpjong(String stockName) {
		String sql = "SELECT UPJONG FROM UPJONG WHERE STOCKNAME=?";
		JdbcDaoContext daoContext = new JdbcDaoContext();
		PreparedStatement pst =null;
		ResultSet rs = null;
		
		try {
			pst = daoContext.getPreparedStatement(sql);
			pst.setString(1, stockName);
			rs = pst.executeQuery();

			if (rs.next()) 
				return rs.getString("UPJONG");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			daoContext.close(rs, pst);
		}
		return null;
	}

	@Override
	public List<String> getStockNames(String upjong) {
		String sql = "SELECT STOCKNAME FROM UPJONG WHERE UPJONG=?";
		JdbcDaoContext daoContext = new JdbcDaoContext();
		PreparedStatement pst =null;
		ResultSet rs = null;
		
		try {
			List<String> list = new ArrayList<String>();
			pst = daoContext.getPreparedStatement(sql);
			pst.setString(1, upjong);
			rs = pst.executeQuery();

			while(rs.next()) {
				list.add(rs.getString("STOCKNAME"));
			}
			return list;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			daoContext.close(rs, pst);
		}
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
	
	public static void main(String[] args) {
		JdbcUpjongDao dao = new JdbcUpjongDao();
		System.out.println(dao.getUpjong("지어소프트"));
		System.out.println(dao.getStockNames(dao.getUpjong("지어소프트")));
	}
}
