package com.stockmarket.www.service.basic;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.stockmarket.www.dao.csv.CSVStockDataDao;
import com.stockmarket.www.entity.CurrentStockInfo;
import com.stockmarket.www.service.SystemService;

public class BasicSystemService implements SystemService{
	private static final int STOCK_CODE_NUM = 1;
	
	public void refreshStockPrice(String pathOfKospi, String pathOfKosdaq) {
		CSVStockDataDao data = new CSVStockDataDao();
		List<String> codeNums = new ArrayList<>();
		List<CurrentStockInfo> kospi;
		List<CurrentStockInfo> kosdaq;
		
		//CSV 를 참조하여 KOSPI, KOSDAQ 모든 종목에 대한 종목코드를 가져온다
		codeNums = data.getColumnData(STOCK_CODE_NUM, pathOfKospi);
		kospi = getCurrentStockPrice(codeNums);
		
		codeNums = data.getColumnData(STOCK_CODE_NUM, pathOfKosdaq);
		kosdaq = getCurrentStockPrice(codeNums);
		
		//TODO
		//갱신된 데이터를 넘겨주는 작업
		
	}
	
	private List<CurrentStockInfo> getCurrentStockPrice(List<String> codeNums) {
		Document doc = null;
		CurrentStockInfo curStockInfo = new CurrentStockInfo();
		List<CurrentStockInfo> data = new ArrayList<>();
		
		for(String codeNum : codeNums) {
			String url = "https://finance.naver.com/item/main.nhn?code=" + codeNum;
			try {
				doc = Jsoup.connect(url).ignoreContentType(true)
										.timeout(5000)
										.get();
			} catch (IOException e) {
				//TODO
				//LOG 기록
				e.printStackTrace();
			}
			
			//현재가, 상태(상승 or 하락), 금액, +/-, percent 를 가져오는 CSS query 문 
			Elements status = doc.select(".no_today span:eq(0), .no_exday em span:lt(2)");
			if(status == null) {
				//TODO
				//LOG 기록
				return null;
			}
			
			//요청한 페이지에 대한 실패시 데이터를 저장하지 않는다. codeNum + 크롤링 데이타  + %
			if(status.text().length() != 0) 
				data.add(curStockInfo.parser(codeNum + " " + status.text()+"%"));
		}
		return data;
	}

/*	
//	for TEST
	public static void main(String[] args) throws IOException {
		int testIndex = 0;
		BasicSystemService sys = new BasicSystemService();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("숫자를 입력하시오");
		testIndex = sc.nextInt();
		
		switch(testIndex) {
		case 1: //코스피 코스닥 전종목 현재가 갱신
			//TEST - KOSPI, KOSDAQ 데이터 크롤링 및 callback
			sys.refreshStockPrice("C:\\work\\study\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\stockMarket\\KOSPI.csv",
							      "C:\\work\\study\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\stockMarket\\KOSDAQ.csv");
			break;
		
		case 2: //refreshStockPrice 함수 처리 시간 체크(100M 환경에서 약 7분 소요) 2019-11-24 20:37:54 ~ 2019-11-24 20:44:53
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(date.format(System.currentTimeMillis()));

			sys.refreshStockPrice("C:\\work\\study\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\stockMarket\\KOSPI.csv",
				      			  "C:\\work\\study\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\stockMarket\\KOSDAQ.csv");
					
			System.out.println(date.format(System.currentTimeMillis()));
			break;
		}
	}
*/	
}
