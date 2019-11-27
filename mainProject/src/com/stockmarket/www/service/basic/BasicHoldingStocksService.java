package com.stockmarket.www.service.basic;

import java.util.List;

import com.stockmarket.www.dao.HaveStockDao;
import com.stockmarket.www.dao.jdbc.JdbcHaveStockDao;
import com.stockmarket.www.entity.CurStock;
import com.stockmarket.www.entity.HaveStockView;
import com.stockmarket.www.service.HoldingStocksService;

public class BasicHoldingStocksService implements HoldingStocksService {

	HaveStockDao jdbcHaveStockDao; 
	
	public BasicHoldingStocksService() {
		// TODO Auto-generated constructor stub
		jdbcHaveStockDao = new JdbcHaveStockDao();
	}
	
	@Override
	public List<HaveStockView> getInterestHoldingList(int userId) {
		
		return jdbcHaveStockDao.getList(userId);
	}
	
	public static void main(String[] args) {
		
		HaveStockDao jdbcHaveStockDao = new JdbcHaveStockDao();
		List<HaveStockView> list = jdbcHaveStockDao.getList(3);
		
		for(HaveStockView data : list) {
			String StockName = data.getStockName();
			int quantity = data.getQuantity();
			double price = Integer.parseInt(data.getPrice().replaceAll(",", ""));
			
			
			System.out.println("StockName:"+StockName+"price:"+price+"quntity:"+ quantity);
	    }
	}
}


