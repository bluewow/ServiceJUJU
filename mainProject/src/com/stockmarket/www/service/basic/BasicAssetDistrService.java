package com.stockmarket.www.service.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.stockmarket.www.dao.HaveStockDao;
import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.dao.jdbc.JdbcHaveStockDao;
import com.stockmarket.www.entity.HaveStockView;
import com.stockmarket.www.service.AssetDistrService;
import com.stockmarket.www.service.AssetTrendService;

public class BasicAssetDistrService implements AssetDistrService {
	
	private MemberDao memberDao;
	private HaveStockDao haveStockDao;
	private AssetTrendService assetTrendService;
	
	public BasicAssetDistrService() {
		haveStockDao = new JdbcHaveStockDao();
		assetTrendService = new BasicAssetTrendService();
	}
	
	private float getProfitByStockId(String stockId, int memberId) {
		
		int quantity = haveStockDao.getView(memberId, stockId).getQuantity();
		int sum = haveStockDao.getView(memberId, stockId).getSum();
		int presentValue = Integer.parseInt(haveStockDao.getView(memberId, stockId).getPrice().replaceAll(",", ""));
		
		//System.out.println("수량: "+quantity);
		//System.out.println("누적값: "+sum);
		//System.out.println("현재값: "+presentValue);
		
		return sum - (quantity*presentValue);	
	}
	
	private float getProfitAll(int memberId) {
		int value = 0;
		
		List<HaveStockView> list = new ArrayList<>();
		list.addAll(haveStockDao.getList(memberId));
		for (HaveStockView data : list) {
			value += getProfitByStockId(data.getStockId(), memberId);
		}	
		return value;	
	}

	@Override
	public List<Map<String, Object>> getHaveStockList(int memberId) {
		List<Map<String, Object>> distrList = new ArrayList<>();

		List<HaveStockView> list = new ArrayList<>();
		list.addAll(haveStockDao.getList(memberId));
		for(HaveStockView data:list) {
			Map<String, Object> distr = new HashMap<>();
			float ratio = (getProfitByStockId(data.getStockId(), memberId))/(getProfitAll(memberId))*100;
			distr.put("ratio", ratio);
			distr.put("stockName", data.getStockName());
			distrList.add(distr);
		}
		return distrList;
	}
	
	/*
	 * =======================================================================
	 * ============================= for Test ================================
	 * =======================================================================
	 */	
	public static void main(String[] args) {
		int testIndex = 0;
		BasicAssetDistrService assetDistr = new BasicAssetDistrService();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("숫자를 입력하시오");
		testIndex = sc.nextInt();

		switch(testIndex) {
		case 1:	// getAssetPresent용 테스트
			System.out.println(assetDistr.getProfitByStockId("035420", 3));
			break;
		case 2:	// getRecordAsset용 테스트
			System.out.println(assetDistr.getProfitAll(3));
			break;
		case 3:	// getRecordAsset용 테스트
			System.out.println(assetDistr.getHaveStockList(3));
			break;
		}
		System.out.println("종료");
	}

}

