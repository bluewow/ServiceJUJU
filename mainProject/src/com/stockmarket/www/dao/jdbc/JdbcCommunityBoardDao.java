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
	public List<CommunityBoard> getCommunityBoardList(int page, String field, String query, String stockCode) {
		System.out.println(page);
		System.out.println(field);
		System.out.println(query);
		System.out.println(stockCode);
		List<CommunityBoard> list = new ArrayList<>();

		String sql = "SELECT * FROM (SELECT ROWNUM NUM, B.* FROM(SELECT * FROM (SELECT * FROM BOARD_VIEW WHERE STOCKNAME LIKE ?) WHERE "
				+ field + " LIKE ? ORDER BY ID DESC) B) WHERE NUM BETWEEN ? AND ?";
		PreparedStatement pst = null;
		ResultSet rs = null;

		JdbcDaoContext daoContext = new JdbcDaoContext();
		try {

			pst = daoContext.getPreparedStatement(sql);

			pst.setString(1, "%" + stockCode + "%");
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

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			daoContext.close(rs, pst);
		}
		return list;
	}

	@Override
	public int getReplyCnt(String field, String query, String stockName) {

		int count = 0;

		String sql = "SELECT REPLY_CNT FROM BOARD_VIEW WHERE " + field + " LIKE ?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		JdbcDaoContext daoContext = new JdbcDaoContext();
		try {

			pst = daoContext.getPreparedStatement(sql);

			pst.setString(1, "%" + query + "%");

			rs = pst.executeQuery();

			if (rs.next()) {
				count = rs.getInt("COUNT");
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			daoContext.close(rs, pst);
		}
		return count;
	}

	@Override
	public CommunityBoard getCommunityBoardDetail(int id) {

		String sql = "SELECT b.ID ID, B.TITLE TITLE, B.WRITER_ID WRITER_ID, B.REGDATE REGDATE, B.CONTENT CONTENT, B.HIT HIT , S.NAME STOCKNAME FROM BOARD B LEFT OUTER JOIN STOCK S ON b.STOCKCODE = s.CODENUM WHERE ID=?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		CommunityBoard communityBoard = null;
		JdbcDaoContext daoContext = new JdbcDaoContext();
		try {
			pst = daoContext.getPreparedStatement(sql);
			pst.setInt(1, id);

			rs = pst.executeQuery();
			rs.next();
			communityBoard = new CommunityBoard(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("WRITER_ID"),
					rs.getDate("REGDATE"), rs.getInt("HIT"), rs.getString("CONTENT"), rs.getString("STOCKNAME"));

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			daoContext.close(rs, pst);
		}
		return communityBoard;
	}

	@Override
	public List<CommunityBoard> getReplyList(int boardId) {

		List<CommunityBoard> list = new ArrayList<>();

		String sql = "SELECT * FROM REPLY WHERE BOARD_ID=? ORDER BY REGDATE DESC";
		PreparedStatement pst = null;
		ResultSet rs = null;
		JdbcDaoContext daoContext = new JdbcDaoContext();
		try {

			pst = daoContext.getPreparedStatement(sql);

			pst.setInt(1, boardId);

			rs = pst.executeQuery();
			while (rs.next()) {
				CommunityBoard communityBoard = new CommunityBoard(rs.getInt("ID"), rs.getString("RE_CONTENT"),
						rs.getString("WRITER_ID"), rs.getDate("REGDATE"), rs.getInt("BOARD_ID"));
				list.add(communityBoard);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			daoContext.close(rs, pst);
		}
		return list;
	}

	@Override
	public int insertReply(CommunityBoard insertReply) {
		int result = 0;
		String sql = "INSERT INTO REPLY (ID, RE_CONTENT, WRITER_ID, REGDATE, BOARD_ID) "
				+ "VALUES ((SELECT NVL(MAX(ID),0)+1 FROM REPLY), ?, ?, SYSTIMESTAMP, ?)";
		PreparedStatement pst = null;
		JdbcDaoContext daoContext = new JdbcDaoContext();

		try {

			pst = daoContext.getPreparedStatement(sql);

			pst.setString(1, insertReply.getReContent());
			pst.setString(2, insertReply.getWriterId());
			pst.setInt(3, insertReply.getId());

			result = pst.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			daoContext.close(pst);
		}
		return result;
	}



}
