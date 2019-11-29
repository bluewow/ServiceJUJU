package com.stockmarket.www.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.stockmarket.www.controller.system.AppContext;
import com.stockmarket.www.dao.HaveStockDao;
import com.stockmarket.www.entity.CurStock;
import com.stockmarket.www.entity.HaveStock;
import com.stockmarket.www.entity.HaveStockView;

public class JdbcHaveStockDao implements HaveStockDao {

   @Override
   public List<HaveStockView> getList(int id) {
      String sql = "SELECT * FROM HAVESTOCK_VIEW WHERE MEMBER_ID = ?";

      List<HaveStockView> stockList = new ArrayList<>();
      // List 필요할 듯.... .... ....... // 담을 그릇 ㅠㅠㅠㅠㅠㅠㅠㅠ 힝 ㅠㅠㅠㅠㅠㅠ
//      AppContext.getKosdaq();
//      AppContext.getKospi();
      List<CurStock> list = new ArrayList<>();
      list.add(new CurStock("035420", "13,000", "상승", "3,000", "+", "2.5"));
      list.add(new CurStock("000660", "15,000", "하강", "3,000", "-", "3.4"));
      list.add(new CurStock("020560", "16,000", "보합", "3,000", "", "1.5"));
      list.add(new CurStock("005930", "12,000", "상승", "3,000", "+", "1.6"));
      list.add(new CurStock("005380", "11,000", "상승", "3,000", "+", "8.9"));
      list.add(new CurStock("095660", "10,500", "상승", "3,000", "+", "10.2"));
//      if (AppContext.getKosdaq() != null) {
//         list.addAll(AppContext.getKosdaq());
//      }
//      if (AppContext.getKospi() != null) {
//         list.addAll(AppContext.getKospi());
//      }

      try {
         PreparedStatement st = JdbcDaoContext.getPreparedStatement(sql);
         st.setInt(1, id);
         ResultSet rs = st.executeQuery();

         while (rs.next()) {
            int memberId = rs.getInt("MEMBER_ID");
            String stockId = rs.getString("STOCK_ID");
            int quantity = rs.getInt("QUANTITY");
            float avg = rs.getFloat("AVG");
            String stockName = rs.getString("NAME");
            // while (memberId.equals(new CurStock(stockId).getCodeNum())) {
            // if (stockId.equals(list.getCodeNum())){ // foreach.....(if...break)forEach문을
            // 종료시키기 위한 if;
            for (CurStock data : list) {
               if (stockId.equals(data.getCodeNum())) {
                  String price = data.getPrice();
                  String gain = data.getGain();
                  String percent = data.getPercent();
                  int intPrice = Integer.parseInt(data.getPrice().replaceAll(",", ""));
                  
                  HaveStockView haveStockView = new HaveStockView(memberId, stockId, quantity, avg, stockName,
                        price, gain, percent, intPrice);
                  stockList.add(haveStockView);
                  break;
               }
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
            haveStock = new HaveStock(rs.getInt("MEMBER_ID"), rs.getString("STOCK_ID"), rs.getInt("QUANTITY"),
                  rs.getInt("AVG"));
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

      String sql = "UPDATE HAVE_STOCK SET QUANTITY=?, AVG=? WHERE MEMBER_ID = ? AND STOCK_ID = ?";

      try {
         PreparedStatement st = JdbcDaoContext.getPreparedStatement(sql);
         st.setInt(1, haveStock.getQuantity());
         st.setFloat(2, haveStock.getAvg());
         st.setInt(3, haveStock.getMemberId());
         st.setString(4, haveStock.getStockId());

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

      String sql = "INSERT INTO HAVE_STOCK(MEMBER_ID, STOCK_ID, QUANTITY, AVG) VALUES (?,?,?,?)";

      try {
         PreparedStatement st = JdbcDaoContext.getPreparedStatement(sql);
         st.setInt(1, haveStock.getMemberId());
         st.setString(2, haveStock.getStockId());
         st.setInt(3, haveStock.getQuantity());
         st.setFloat(4, haveStock.getAvg());

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

      switch (testIndex) {
      case 1: // update 문 Test
         haveStock.setMemberId(2); // 2 - dogseen@gamil.com
         haveStock.setQuantity(500);
         haveStock.setStockId("095660");
         stockDao.update(haveStock);
         break;
      case 2:
         System.out.println(stockDao.getList(3));
         System.out.println("-------------------");
         System.out.println(stockDao.getList(6));
         break;
      }
      System.out.println("종료");
   }
}