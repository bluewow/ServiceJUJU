package com.stockmarket.www.dao.jdbc;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.stockmarket.www.dao.InterestViewDao;
import com.stockmarket.www.entity.InterestView;

public class JdbcInterestViewDao implements InterestViewDao {

	

	
	
	@Override
	public List<InterestView> getInterestStockList(int id) {
		String sql = "SELECT * FROM INTEREST_STOCK_VIEW WHERE ID = ?";
		
		List<InterestView> interestlist = new ArrayList<>();
		
		try {
			PreparedStatement st = JdbcDaoContext.getPreparedStatement(sql);
			st.setInt(1, id);
			ResultSet rs =st.executeQuery();
			
			while(rs.next()) {
				String stockname = rs.getString("STOCKNAME");

				InterestView interestview = new InterestView(stockname);
				interestlist.add(interestview);
				
			}
			rs.close();
			st.close();	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // oracle.jdbc.driver.OracleDriver 객체를 만듬
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return interestlist;
	}
}
