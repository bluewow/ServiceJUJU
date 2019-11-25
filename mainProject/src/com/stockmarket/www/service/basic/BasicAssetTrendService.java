package com.stockmarket.www.service.basic;

import java.util.List;

import com.stockmarket.www.dao.HaveStockDao;
import com.stockmarket.www.dao.RecoedAssetDao;
import com.stockmarket.www.dao.jdbc.JDBCRecordAssetDao;
import com.stockmarket.www.dao.jdbc.JdbcHaveStockDao;
import com.stockmarket.www.entity.HaveStock;
import com.stockmarket.www.entity.RecordAsset;
import com.stockmarket.www.service.AssetTrendService;

public class BasicAssetTrendService implements AssetTrendService {
	
	private RecoedAssetDao recordAssetDao; 
	private HaveStockDao haveStockDao; 
	
	
	public BasicAssetTrendService() {
		recordAssetDao = new JDBCRecordAssetDao();
		haveStockDao = new JdbcHaveStockDao();
	}

	@Override
	public List<RecordAsset> getRecordAsset(int memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAssetPresent(int memberId) {
		// 현재 보유 자산
		// 가상머니+(현재가*보유수량)+(현재가*보유수량).....
		//List<HaveStock> quantHaveStocks = haveStockDao.getQuantity(memberId);
		return 0;
	}

}
