package com.stockmarket.www.controller.test;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import com.stockmarket.www.controller.system.AppContext;
import com.stockmarket.www.dao.StockDetailDao;
import com.stockmarket.www.dao.jdbc.JdbcStockDetailDao;
import com.stockmarket.www.dao.jdbc.JdbcUpjongDao;
import com.stockmarket.www.dao.jdbc.JdbckoreaStocksDao;
import com.stockmarket.www.entity.CurStock;
import com.stockmarket.www.entity.StockDetail;
import com.stockmarket.www.entity.koreaStocks;
import com.stockmarket.www.service.basic.BasicSystemService;

public class TestSystemService {

	public void test() {
		int testIndex = 0;
		BasicSystemService sys = new BasicSystemService();
		
		while (true) {
			Scanner sc = new Scanner(System.in);
			viewPrint();
			testIndex = sc.nextInt();	
			
			switch (testIndex) {
			case 1: // 코스피 코스닥 전종목 현재가 갱신
				// TEST - KOSPI, KOSDAQ 데이터 크롤링 및 callback
				sys.refreshStockPrice(
						"C:\\work\\study\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\stockMarket\\KOSPI.csv",
						"C:\\work\\study\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\stockMarket\\KOSDAQ.csv");
				break;

			case 2:
				// refreshStockPrice 함수 처리 시간 체크(100Mbps 이하 환경(기현집) 에서 약 7분 소요) 2019-11-24
				// 20:37:54 ~ 2019-11-24 20:44:53
				// refreshStockPrice 함수 처리 시간 체크(100Mbps 환경에서 약 4~5분 소요) 2019-11-28 16:21:19 ~
				// 2019-11-28 16:17:33
				SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println(date.format(System.currentTimeMillis()));

				sys.refreshStockPrice(
						"C:\\Users\\acorn\\Desktop\\Work\\Recent\\mainProject\\WebContent\\fileUpload\\KOSPI.csv",
						"C:\\Users\\acorn\\Desktop\\Work\\Recent\\mainProject\\WebContent\\fileUpload\\KOSDAQ.csv");

				System.out.println(date.format(System.currentTimeMillis()));
				break;
			case 3: // single tone Test for 코스피/코스닥 크롤링 데이터
				List<CurStock> kospi;
				List<CurStock> kosdaq;

				kospi = AppContext.getKospi();
				kosdaq = AppContext.getKosdaq();

				if (kospi != null) {
					for (CurStock cur : kospi) {
						System.out.println(cur.toString());
					}
				}
				System.out.println("next");
				sc.nextInt();
				if (kosdaq != null) {
					for (CurStock cur : kosdaq) {
						System.out.println(cur.toString());
					}
				}
				System.out.println("finished-4");
				break;
			case 5: // 보유 자산 리스트 업로드 테스트
				System.out.println("케이스 5");
				sys.insertRecordAsset();
				break;
			case 7:
				sys.setStockDataAll("095660");
//				service.getAllDailyPrice("004170");
				return;
			case 8: // stockdetailDao 의 저장된 데이터를 가져온다
				StockDetailDao s;
				s = new JdbcStockDetailDao();
				List<StockDetail> stock = s.get("095660");
				for (StockDetail obj : stock)
					System.out.println(obj.toString());

				return;
			case 9:
				sys.upjongCrawling();
				return;
			case 10:
				JdbcUpjongDao upjongDao = new JdbcUpjongDao();
				upjongDao.delete();

			
			case 11:
				JdbckoreaStocksDao koreaDao = new JdbckoreaStocksDao();
				List<koreaStocks> list = koreaDao.getList();
				int cnt = 0;
				for(koreaStocks korea : list) {
					cnt++;
					System.out.println(korea.getCompanyName());
				}
				System.out.println("koreaDao.getList 종료 cnt : " + cnt);
				break;
			case 99:
				System.out.println("Test - SystemService 종료");
				return;
			}
		}
	}

	private void viewPrint() {
		System.out.println("-----------------------------");
		System.out.println("1.코스피 코스닥 전종목 현재가 갱신");
		System.out.println("2.함수 처리 시간 체크 ");
		System.out.println("3.single tone Test for 코스피/코스닥 크롤링 데이터 ");
		System.out.println("4. ");
		System.out.println("5. ");
		System.out.println("6. ");
		System.out.println("7. ");
		System.out.println("8. ");
		System.out.println("9. ");
		System.out.println("99. 종료");
		System.out.println("-----------------------------");
		System.out.println("숫자를 입력하시오");
	}

}
