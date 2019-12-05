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
		Member member = memberDao.getMember(id);
		if(member == null)
			return 0;
		
		return member.getvMoney();
	}

	@Override
	public int getStockAssets(int id, String stockId) {
		JdbcHaveStockDao stockDao = new JdbcHaveStockDao();
		
		HaveStock haveStock = stockDao.get(id, stockId);
		if(haveStock == null)
			return 0;
		
		return haveStock.getSum();
	}
	
	@Override
	public int getQty(int id, String stockId) {
		JdbcHaveStockDao stockDao = new JdbcHaveStockDao();
		
		HaveStock haveStock = stockDao.get(id, stockId);
		if(haveStock == null)
			return 0;
		
		return haveStock.getQuantity();
	}
	
	@Override
	public boolean setQty(int id, String stockId, int qty, int curPrice) {
		JdbcHaveStockDao stockDao = new JdbcHaveStockDao();
		
		HaveStock haveStock = stockDao.get(id, stockId);
		if(haveStock.getQuantity() + qty < 0) {
			System.out.println("마이너스 수량");
			return false;
		} else {
			Member member = memberDao.getMember(id);
			memberDao.updateMember(id, member.getvMoney() + (-qty * curPrice));
			haveStock.setQuantity(haveStock.getQuantity() + qty);
			haveStock.setSum(haveStock.getSum() + qty * curPrice);
			stockDao.update(haveStock);
			return true;
		}
	}
	

	@Override
	public void getStock(int date) {
		
	}


}
