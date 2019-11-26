package com.stockmarket.www.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stockmarket.www.dao.CommunityBoardDao;
import com.stockmarket.www.entity.CommunityBoard;

public class JdbcCommunityBoardDao implements CommunityBoardDao {

	@Override
	public List<CommunityBoard> getCommunityBoardList(int page, String field, String query, String stockName) {

		List<CommunityBoard> list = new ArrayList<>();

		String sql = "SELECT * FROM (SELECT ROWNUM NUM, B.* FROM(SELECT * FROM (SELECT * FROM BOARD_VIEW WHERE STOCKNAME LIKE ?) WHERE "
				+ field + " LIKE ? ORDER BY ID DESC) B) WHERE NUM BETWEEN ? AND ?";

		try {

			PreparedStatement statement = JdbcDaoContext.getPreparedStatement(sql);

			statement.setString(1, "%" + stockName + "%");
			statement.setString(2, "%" + query + "%");
			statement.setInt(3, 1 + 10 * (page - 1));
			statement.setInt(4, 20 * page);

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				CommunityBoard communityBoard = new CommunityBoard(resultSet.getInt("ID"), resultSet.getString("TITLE"),
						resultSet.getString("WRITER_ID"), resultSet.getDate("REGDATE"), resultSet.getInt("HIT"), resultSet.getString("STOCKNAME"),
						resultSet.getInt("REPLY_CNT"));
				list.add(communityBoard);
			}

			resultSet.close();
			statement.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getReplyCnt(String field, String query, String stockName) {

		int count = 0;

		String sql = "SELECT REPLY_CNT FROM BOARD_VIEW WHERE " + field + " LIKE ?";

		try {
			
			PreparedStatement st = JdbcDaoContext.getPreparedStatement(sql);

			st.setString(1, "%" + query + "%");

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				count = rs.getInt("COUNT");
			}
			rs.close();
			st.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

}
