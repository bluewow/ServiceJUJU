package com.stockmarket.www.service.basic;

import com.stockmarket.www.dao.jdbc.JdbcMemberDao;
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
	public int getQty(int id) {
		//get Member
		//수량 DB(member)
		return 0;
	}
	
	@Override
	public boolean setQty(int id, int qty) {
		//set Member
		//set 수량 db
		int memberQty = 0;
		
		memberQty = getQty(id);
		if(memberQty + qty < 0) {
			System.out.println("마이너스 수량");
			return false;
		} else {
			//set 수량 db
			return true;
		}
	}
	

	@Override
	public void getStock(int date) {
		
	}


}
