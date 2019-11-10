package com.stockmarket.www.service.repository;

//Quantity -> Qty
public interface TradeInterface {
	void updatePurchaseQty();
	void updateSoldQty();
	void getAssert();
	void getQty();
	void getStock(int date);
}
