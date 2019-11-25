package com.stockmarket.www.service.basic;

import com.stockmarket.www.dao.jdbc.JdbcHaveStockDao;
import com.stockmarket.www.dao.jdbc.JdbcMemberDao;
import com.stockmarket.www.entity.HaveStock;
import com.stockmarket.www.entity.Member;
import com.stockmarket.www.service.TradeService;

public class BasicTradeService implements TradeService{
	JdbcMemberDao memberDao;
	
	public BasicTradeService() {
		memberDao = new JdbcMemberDao();
	}
	
	@Override
	public boolean updatePurchaseQty(int qty) {
		//성공/실패 DB(수량)		
		return false;
	}

	@Override
	public boolean updateSoldQty(int qty) {
		//성공/실패 DB(수량)
		return false;
	}

	@Override
	public int getAssets(int id) {
		Member member = new Member();
		member = memberDao.getMember(id);
		
		return member.getvMoney();
	}

	@Override
	public int getQty(int id, String stockId) {
		HaveStock haveStock = new HaveStock();
		JdbcHaveStockDao stockDao = new JdbcHaveStockDao();
		
		haveStock = stockDao.get(id, stockId);
		
		return haveStock.getQuantity();
	}
	
	@Override
	public boolean setQty(int id, String stockId, int qty) {
		HaveStock haveStock = new HaveStock();
		JdbcHaveStockDao stockDao = new JdbcHaveStockDao();
		
		haveStock = stockDao.get(id, stockId);
		if(haveStock.getQuantity() + qty < 0) {
			System.out.println("마이너스 수량");
			return false;
		} else {
			haveStock.setQuantity(haveStock.getQuantity() + qty);
			stockDao.update(haveStock);
			return true;
		}
	}
	

	@Override
	public void getStock(int date) {
		
	}


}
