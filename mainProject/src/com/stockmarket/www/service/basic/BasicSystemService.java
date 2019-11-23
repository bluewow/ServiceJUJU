package com.stockmarket.www.service.basic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.stockmarket.www.dao.csv.CSVStockDataDao;
import com.stockmarket.www.service.SystemService;

public class BasicSystemService implements SystemService{
	private static final int STOCK_CODE_NUM = 1;
	
	public void refreshStockPrice(String pathOfKospi, String pathOfKosdaq) {
		CSVStockDataDao data = new CSVStockDataDao();
		List<String> codeNums = new ArrayList<>();
		
		//CSV 를 참조하여 KOSPI, KOSDAQ 모든 종목에 대한 종목코드를 가져온다
		codeNums = data.getColumnData(STOCK_CODE_NUM, pathOfKospi);
		getCurrentStockPrice(codeNums);
		
//		codeNums = data.getColumnData(STOCK_CODE_NUM, pathOfKosdaq);
//		getCurrentStockPrice(codeNums);
		
		
		//TODO
		//갱신된 데이터를 넘겨주는 작업
		
	}
	
	private void getCurrentStockPrice(List<String> codeNums) {
		Document doc = null;
		
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
				return;
			}
			
			//요청한 페이지에 대한 실패시 데이터를 저장하지 않는다.
//			if(status.text().length() != 0)
//				System.out.println(status.text()+"%");
		}
			
	}

	//	for TEST
	public static void main(String[] args) throws IOException {
		BasicSystemService sys = new BasicSystemService();
		
		//TEST - KOSPI, KOSDAQ 데이터 크롤링 및 callback
		sys.refreshStockPrice("C:\\work\\study\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\stockMarket\\KOSPI.csv",
								"KOSDQ.csv");
	}
}
