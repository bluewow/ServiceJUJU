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

		String sql = "SELECT * FROM (SELECT ROWNUM NUM, N.* FROM(SELECT * FROM (SELECT * FROM BOARD_VIEW WHERE STOCKNAME LIKE '%"
				+ stockName + "%') WHERE " + field + " LIKE ? ORDER BY REGDATE DESC) N) WHERE NUM BETWEEN ? AND ?";

		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ACORNGROUP1", "month100man");
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, "%" + query + "%");
			st.setInt(2, 1 + 10 * (page - 1));
			st.setInt(3, 10 * page);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				CommunityBoard communityBoard = new CommunityBoard(rs.getInt("ID"), rs.getString("TITLE"),
						rs.getString("WRITER"), rs.getDate("REGDATE"), rs.getInt("HIT"), rs.getString("CONTENT"),
						rs.getString("STOCKNAME"));
				list.add(communityBoard);
			}

			st.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getCommunityBoardListCount(String field, String query) {
		// TODO Auto-generated method stub
		return 0;
	}

}
