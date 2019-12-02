package com.stockmarket.www.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stockmarket.www.dao.CaptureMemoDao;
import com.stockmarket.www.entity.CaptureMemo;
import com.stockmarket.www.entity.CaptureMemoView;

public class JdbcCaptureMemoDao implements CaptureMemoDao {
	@Override
	public List<CaptureMemoView> getList(int page) {
		List<CaptureMemoView> captureMemos = new ArrayList<>();
		
		String sql = "SELECT S.NAME, C.TITLE, C.REGDATE "
				+ "FROM CAPTURE_MEMO C JOIN STOCK S ON C.CODENUM = S.CODENUM";

		try {
			Statement statement = JdbcDaoContext.getStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {				
				String name = resultSet.getString("NAME");
				String title = resultSet.getString("TITLE");
				Date regdate = resultSet.getDate("regdate");
				
				CaptureMemoView captureMemo = new CaptureMemoView(name, title, regdate);
				
				captureMemos.add(captureMemo);
			}
			resultSet.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return captureMemos;
	}

	@Override
	public int insert(CaptureMemo captureMemo) {
		int result = 0;

		String sql = "INSERT INTO CAPTURE_MEMO(CONTENT, TITLE, HIGH_PRICE, MARKET_PRICE, "
				+ "TRADING_VOLUME, PRE_CLOSING_PRICE, FAVORITE, CODENUM, MEMBER_ID)" 
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement statement = JdbcDaoContext.getPreparedStatement(sql);
			statement.setString(1, captureMemo.getContent());
			statement.setString(2, captureMemo.getTitle());
			statement.setInt(3, captureMemo.getHighPrice());
			statement.setInt(4, captureMemo.getMaketPrice());
			statement.setInt(5, captureMemo.getTradingVolume());
			statement.setInt(6, captureMemo.getPreClosingPrice());
			statement.setString(7, captureMemo.getFavorite());
			statement.setString(8, captureMemo.getCodeNum());
			statement.setInt(9, captureMemo.getMemberId());

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
	public int delete(int id) {
		int result = 0;
		
		String sql = "DELETE CAPTURE_MEMO WHERE ID=?";

		try {
			PreparedStatement statement = JdbcDaoContext.getPreparedStatement(sql);

			statement.setInt(1, id);

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
	public int update(CaptureMemo captureMemo) {
		int result = 0;

		String sql = "UPDATE CAPTURE_MEMO SET TITLE=?, CONTENT=? WHERE ID=?";

		try {
			PreparedStatement statement = JdbcDaoContext.getPreparedStatement(sql);
			statement.setString(1, captureMemo.getTitle());
			statement.setString(2, captureMemo.getTitle());
			statement.setInt(3, captureMemo.getId());

			result = statement.executeUpdate();

			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
//	public static void main(String[] args) {
//		JdbcCaptureMemoDao dao = new JdbcCaptureMemoDao();
//		List<CaptureMemoView> list = dao.getList(1);
//		
//		for(CaptureMemoView c:list) {
//			System.out.println(c.toString());
//		}
//		
//		CaptureMemo captureMemo = new CaptureMemo("aaaaaaaaaa", "aaaaaaaasdsdsd", 11, 12, 13, 11, "sff", "005380", 2);
//		captureMemo.setId(12);
//		System.out.println(captureMemo.toString());
//		int result = dao.insert(captureMemo);
//		int result = dao.update(captureMemo);
//		
//		System.out.println(result);
//	}
}
