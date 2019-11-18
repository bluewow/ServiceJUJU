package com.stockmarket.www.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.entity.Member;

public class JdbcMemberDao implements MemberDao {
	@Override
	public List<Member> getMemberList() {
		// 가상머니 상위 50위까지 뽑아오는 쿼리
		String sql = "SELECT * FROM MEMBER ORDER BY VMONEY " + 
		"DESC OFFSET 0 ROWS FETCH NEXT 50 ROWS ONLY";
		List<Member> members = new ArrayList<>();
		
		try {
			Statement statement = JdbcDaoContext.getStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				String email = resultSet.getString("EMAIL");
				String nickName = resultSet.getString("NICKNAME");
				String password = resultSet.getString("PASSWORD");
				int vmoney = resultSet.getInt("VMONEY");
				Date regdate = resultSet.getDate("REGDATE");

				Member member = new Member(id, email, nickName, password, vmoney);
				members.add(member);
			}
			resultSet.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}

	@Override
	public Member getMember(int id) {
		String sql = "SELECT * FROM MEMBER WHERE ID=" + id;
		Member member = null;

		try {
			Statement statement = JdbcDaoContext.getStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			if (resultSet.next()) {
				String email = resultSet.getString("EMAIL");
				String nickName = resultSet.getString("NICKNAME");
				String password = resultSet.getString("PASSWORD");
				int vmoney = resultSet.getInt("VMONEY");

				member = new Member(id, email, nickName, password, vmoney);
			}
			resultSet.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

	@Override
	public int updateMember(int id, int vmoney) {
		int result = 0;

		String sql = "UPDATE MEMBER SET VMONEY = ? WHERE ID = ?";
		try {
			PreparedStatement statement = JdbcDaoContext.getPreparedStatement(sql);

			statement.setInt(1, vmoney);
			statement.setInt(2, id);
			
			result = statement.executeUpdate();
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
