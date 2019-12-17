package com.stockmarket.www.service.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.stockmarket.www.dao.HaveStockDao;
import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.dao.jdbc.JdbcHaveStockDao;
import com.stockmarket.www.dao.jdbc.JdbcMemberDao;
import com.stockmarket.www.entity.HaveStockView;
import com.stockmarket.www.service.AssetDistrService;
import com.stockmarket.www.service.AssetTrendService;

public class BasicAssetDistrService implements AssetDistrService {

	private MemberDao memberDao;
	private HaveStockDao haveStockDao;
	private AssetTrendService assetTrendService;

	private static Map<String, Object> nonAraayList = new HashMap<>(); // 종목명 / 분포율 정렬 전
	private static Map<String, Object> ArrayList = new LinkedHashMap<>(); // 종목명 / 분포율 내림차순 정렬

	public BasicAssetDistrService() {
		haveStockDao = new JdbcHaveStockDao();
		assetTrendService = new BasicAssetTrendService();
		memberDao = new JdbcMemberDao();
	}

	/*
	 * 자산 분포도 ? 한 회원이 가지고 있는 모든 자산에서 회원이 보유하고 있는 주식 종목들의 자산 비율을 나타낸 것 한 종목의 자산 / 모든
	 * 종목의 자산을 더한 값 내림차순으로 정렬
	 */

	// 멤버의 하나의 보유 종목의 자산
	private float getSumByStockId(String stockId, int memberId) {

		float presentPrice = Float.parseFloat(haveStockDao.getView(memberId, stockId).getPrice().replaceAll(",", ""));
		int quantuty = haveStockDao.getView(memberId, stockId).getQuantity();

		// System.out.println(presentPrice);
		// System.out.println(quantuty);

		return presentPrice * quantuty;
	}

	// 멤버의 모든 종목의 자산을 더한 값 = 현 보유 자산 - 주식 가상 머니
	private float getSumAll(int memberId) {

		long assetPresnt = assetTrendService.getAssetPresent(memberId);
		long vMoney = memberDao.getMember(memberId).getvMoney();
		// System.out.println(vMoney);

		return assetPresnt - vMoney;
	}

	// 멤버의 자산을 정렬 후 맵 리스트로 반환
	@Override
	public List<HaveStockView> getHaveStockList(int memberId) {

		List<HaveStockView> arrayList = new ArrayList<>();
		
		List<HaveStockView> list = new ArrayList<>();
		list.addAll(haveStockDao.getList(memberId));
		for (HaveStockView data : list) {
			HaveStockView haveStockView = new HaveStockView();
			float profit = getSumByStockId(data.getStockId(), memberId);
			float profits = getSumAll(memberId);
			float ratio = (profit / profits) * 100;
			
			haveStockView.setStockName(data.getStockName());
			haveStockView.setRatio(ratio);

			arrayList.add(haveStockView);
		}
		Collections.sort(arrayList, new Comparator<HaveStockView>() {
			@Override
			public int compare(HaveStockView v1, HaveStockView v2) {
				return Float.valueOf(v2.getRatio()).compareTo(Float.valueOf(v1.getRatio()));
			}	
		});
		// sortByValue();
		return arrayList;
	}
	
	/*
	@Override
	public Map<String, Float> getHaveStockList(int memberId) {
		
		List<HaveStockView> list = new ArrayList<>();
		list.addAll(haveStockDao.getList(memberId));
		for (HaveStockView data : list) {
			
			float profit = getSumByStockId(data.getStockId(), memberId);
			float profits = getSumAll(memberId);
			
			float ratio = (profit / profits) * 100;
			String stockName = "\"" + data.getStockName() + "\"";
			
			nonAraayList.put(stockName, ratio);
		}
		sortByValue();
		return ArrayList;
	}
	*/

//	private static void sortByValue() {
//		List<Entry<String, Float>> list = new ArrayList<>(nonAraayList.entrySet());
//		list.sort(Entry.<String, Float>comparingByValue().reversed());
//
//		for (Entry<String, Float> entry : list) {
//			ArrayList.put(entry.getKey(), entry.getValue());
//		}
//	}

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

		switch (testIndex) {
		case 1: // getAssetPresent용 테스트
			System.out.println(assetDistr.getSumByStockId("035420", 3));
			break;
		case 2: // getRecordAsset용 테스트
			System.out.println(assetDistr.getSumAll(3));
			break;
		case 3: // getRecordAsset용 테스트
			System.out.println(assetDistr.getHaveStockList(3));
			break;
		case 4:
			System.out.println(assetDistr.getHaveStockList(3));
			break;
		}
		System.out.println("종료");
	}

}
