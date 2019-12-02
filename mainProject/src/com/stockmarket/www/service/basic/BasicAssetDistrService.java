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
	
	private int getProfitByStockId(String stockId, int memberId) {
		int value = 0;
		
		int quantity = haveStockDao.getView(memberId, stockId).getQuantity();
		int sum = haveStockDao.getView(memberId, stockId).getSum();
		int presentValue = Integer.parseInt(haveStockDao.getView(memberId, stockId).getPrice().replaceAll(",", ""));
		
		//System.out.println("수량: "+quantity);
		//System.out.println("누적값: "+sum);
		//System.out.println("현재값: "+presentValue);
		
		value = sum - (quantity*presentValue);
		
		return value;	
	}
	
	private int getProfitAll(int memberId) {
		
		
		int value = 0;
		
		List<HaveStockView> list = new ArrayList<>();
		list.addAll(haveStockDao.getList(memberId));
		for (HaveStockView data : list) {
			value += getProfitByStockId(data.getStockId(), memberId);
		}	
		return value;	
	}

	@Override
	public List<HaveStockView> getHaveStockList(int memberId) {
		List<Map<String, Double>> distrList = new ArrayList<>();
		
		String stockId = "";

		List<HaveStockView> list = new ArrayList<>();
		list.addAll(haveStockDao.getList(memberId));
		for(HaveStockView data:list) {
			
			//stockId = get
			
			Map<String, Double> distr = new HashMap<>();
			
			//distr.
			
			//distrList.add();
			
		}
		
		return list;
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
		}
		System.out.println("종료");
	}

}

