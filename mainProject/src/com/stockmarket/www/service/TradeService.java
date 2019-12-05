package com.stockmarket.www.service;

//Quantity -> Qty
public interface TradeService {
	/* 구매수량을 입력받아 처리한다 */
	boolean updatePurchaseQty(int qty);
	
	/* 매도수량을 입력받아 처리한다  */
	boolean updateSoldQty(int qty);
	
	/* 자산정보를 가져온다	 */
	int getAssets(int id);
	
	/* 해당종목의 자산을 가져온다 */
	int getStockAssets(int id, String stockId);
	
	/* 종목수량을 가져한다 */
	int getQty(int id, String stockId);

	/* 종목수량  및 sum 값을 변경한다 */
	boolean setQty(int id, String stockId, int qty, int curPrice);
	
	/*일봉/주봉/월봉 데이터를 가져온다  */
	void getStock(int date);
}
