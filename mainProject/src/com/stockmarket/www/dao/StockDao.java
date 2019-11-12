package com.stockmarket.www.dao;

import com.stockmarket.www.ett.Stock;

public interface StockDao {
	Stock getStockCodeNum(int codeNum);
}
