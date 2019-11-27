package com.stockmarket.www.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.stockmarket.www.dao.HaveStockDao;
import com.stockmarket.www.dao.csv.CSVStockDataDao;
import com.stockmarket.www.entity.CurStock;
import com.stockmarket.www.entity.HaveStock;
import com.stockmarket.www.entity.HaveStockView;

public class JdbcHaveStockDao implements HaveStockDao {

	
	
	@Override
	public List<HaveStockView> getList(int id) {
		
		List<HaveStockView> stockList = new ArrayList<>();
		// List 필요할 듯.... .... ....... // 담을 그릇 ㅠㅠㅠㅠㅠㅠㅠㅠ 힝 ㅠㅠㅠㅠㅠㅠ
		CurStock curStock = new CurStock("095660", "13,000", "상승", "3,000", "+", "23.2");
		// curStock.getList(String codenum);
		
		String sql = "SELECT * FROM HAVESTOCK_VIEW WHERE MEMBER_ID = ?";
	
		try {
			PreparedStatement st = JdbcDaoContext.getPreparedStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				int memberId = rs.getInt("MEMBER_ID");
				String stockId = rs.getString("STOCK_ID");
				int quantity = rs.getInt("QUANTITY");
				String stockName = rs.getString("NAME");
				//while (memberId.equals(new CurStock(stockId).getCodeNum())) {
				if (stockId.equals(curStock.getCodeNum())){ // foreach.....(if...break)forEach문을 종료시키기 위한 if;
					String price = curStock.getPrice();
					String gain = curStock.getGain(); 
					String percent = curStock.getPercent();
					
					HaveStockView haveStockView = new HaveStockView(memberId, stockId, quantity, stockName, price, gain, percent);
					stockList.add(haveStockView);		
					break;
				}		
			}
			rs.close();
			st.close();	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return stockList;
	}

	@Override
	public HaveStock get(int memberId, String stockId) {

		HaveStock haveStock = null;
		
		String sql = "SELECT * FROM HAVE_STOCK WHERE MEMBER_ID = ? AND STOCK_ID = ?";		
		
		try {

			PreparedStatement st = JdbcDaoContext.getPreparedStatement(sql);
			st.setInt(1, memberId);
			st.setString(2, stockId);
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				haveStock = new HaveStock(
							rs.getInt("MEMBER_ID")
							,rs.getString("STOCK_ID")
							,rs.getInt("QUANTITY"));
			}
			rs.close();
			st.close();			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return haveStock;
	}

	@Override
	public int update(HaveStock haveStock) {
		int result = 0;

		String sql = "UPDATE HAVE_STOCK SET QUANTITY=? WHERE MEMBER_ID = ? AND STOCK_ID = ?";

		try {
			PreparedStatement st = JdbcDaoContext.getPreparedStatement(sql);
			st.setInt(1, haveStock.getQuantity());
			st.setInt(2, haveStock.getMemberId());
			st.setString(3, haveStock.getStockId());

			result = st.executeUpdate();

			st.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insert(HaveStock haveStock) {
		int result = 0;

		String sql = "INSERT INTO HAVE_STOCK(MEMBER_ID, STOCK_ID, QUANTITY) VALUES (?,?,?)";

		try {
			PreparedStatement st = JdbcDaoContext.getPreparedStatement(sql);
			st.setInt(1, haveStock.getMemberId());
			st.setString(2, haveStock.getStockId());
			st.setInt(3, haveStock.getQuantity());

			result = st.executeUpdate();

			st.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(int memberId, String stockId) {
		int result = 0;

		String sql = "DELETE HAVE_STOCK WHERE MEMBER_ID = ? AND STOCK_ID = ?";

		try {
			PreparedStatement st = JdbcDaoContext.getPreparedStatement(sql);
			st.setInt(1, memberId);
			st.setString(2, stockId);

			result = st.executeUpdate();

			st.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/*
	 * =======================================================================
	 * ============================= for Test ================================
	 * =======================================================================
	 */	
	public static void main(String[] args) {
		int testIndex = 0;
		HaveStock haveStock = new HaveStock();
		JdbcHaveStockDao stockDao = new JdbcHaveStockDao();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("숫자를 입력하시오");
		testIndex = sc.nextInt();

		switch(testIndex) {
		case 1:	//update 문 Test
			haveStock.setMemberId(2); // 2 - dogseen@gamil.com
			haveStock.setQuantity(500);	
			haveStock.setStockId("095660"); 
			stockDao.update(haveStock);
			break;
		case 2:
			System.out.println(stockDao.getList(1));
			break;
		}
		System.out.println("종료");
	}
}
