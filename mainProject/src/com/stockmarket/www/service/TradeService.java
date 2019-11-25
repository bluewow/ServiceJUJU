package com.stockmarket.www.service;

//Quantity -> Qty
public interface TradeService {
	/*
	 * 구매수량을 입력받아 처리한다
	 * TODO
	 * DB -> 보유종목 테이블을 이용
	 * DB -> 회원 테이블을 이용
	 */
	boolean updatePurchaseQty(int qty);
	
	/*
	 * 매도수량을 입력받아 처리한다
	 * TODO
	 * DB -> 보유종목 테이블을 이용
	 * DB -> 회원 테이블을 이용
	 */
	boolean updateSoldQty(int qty);
	
	/*
	 * 자산정보를 갱신한다 
	 */
	int getAssets(int id);
	
	/*
	 * 종목수량을 갱신한다
	 * TODO
	 * DB -> 보유종목 테이블을 이용
	 */
	int getQty(int id, String stockId);

	/*
	 * 종목수량을 변경한다
	 * TODO
	 * DB -> 보유종목 테이블을 이용
	 */
	boolean setQty(int id, String stockId, int qty);
	
	/*
	 * 일봉/주봉/월봉 데이터를 가져온다
	 * TODO
	 * Stock 정보 사용(Not DB)
	 */
	void getStock(int date);
}
