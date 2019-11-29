package com.stockmarket.www.dao.jdbc;

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
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			pst = JdbcDaoContext.getPreparedStatement(sql);

			pst.setString(1, "%" + stockName + "%");
			pst.setString(2, "%" + query + "%");
			pst.setInt(3, 1 + 10 * (page - 1));
			pst.setInt(4, 10 * page);

			rs = pst.executeQuery();
			while (rs.next()) {
				CommunityBoard communityBoard = new CommunityBoard(rs.getInt("ID"), rs.getString("TITLE"),
						rs.getString("WRITER_ID"), rs.getDate("REGDATE"), rs.getInt("HIT"), rs.getString("STOCKNAME"),
						rs.getInt("REPLY_CNT"));
				list.add(communityBoard);
			}

			rs.close();
			pst.close();

		} catch (ClassNotFoundException | SQLException e) {
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
		return list;
	}

	@Override
	public int getReplyCnt(String field, String query, String stockName) {

		int count = 0;

		String sql = "SELECT REPLY_CNT FROM BOARD_VIEW WHERE " + field + " LIKE ?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {

			pst = JdbcDaoContext.getPreparedStatement(sql);

			pst.setString(1, "%" + query + "%");

			rs = pst.executeQuery();

			if (rs.next()) {
				count = rs.getInt("COUNT");
			}
			rs.close();
			pst.close();

		} catch (ClassNotFoundException | SQLException e) {
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
		return count;
	}

}
