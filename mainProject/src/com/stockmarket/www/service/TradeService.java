package com.stockmarket.www.service;

import com.stockmarket.www.service.repository.TradeInterface;

public class TradeService implements TradeInterface{
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
	public int getAssets() {
		//get Member
		//자산 DB(member)
		return 0;
	}

	@Override
	public int getQty() {
		//get Member
		//수량 DB(member)
		return 0;
	}

	@Override
	public void getStock(int date) {
		
	}

}
