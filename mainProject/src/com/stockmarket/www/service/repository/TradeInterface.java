package com.stockmarket.www.service.repository;

//Quantity -> Qty
public interface TradeInterface {
	/*
	 * 구매수량을 입력받아 처리한다
	 * TODO
	 * Post 방식
	 * DB -> 보유종목 테이블을 이용
	 * DB -> 회원 테이블을 이용
	 */
	void updatePurchaseQty();
	
	/*
	 * 매도수량을 입력받아 처리한다
	 * TODO
	 * Post 방식
	 * DB -> 보유종목 테이블을 이용
	 * DB -> 회원 테이블을 이용
	 */
	void updateSoldQty();
	
	/*
	 * 자산정보를 갱신한다
	 * TODO
	 * DB -> 회원 테이블을 이용
	 */
	void getAssert();
	
	/*
	 * 종목수량을 갱신한다
	 * TODO
	 * DB -> 보유종목 테이블을 이용
	 */
	void getQty();
	
	/*
	 * 일봉/주봉/월봉 데이터를 가져온다
	 * TODO
	 * Stock 정보 사용(Not DB)
	 */
	void getStock(int date);
}
