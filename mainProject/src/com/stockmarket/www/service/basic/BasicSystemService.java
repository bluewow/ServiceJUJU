package com.stockmarket.www.service.basic;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.stockmarket.www.controller.system.AppContext;
import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.dao.csv.CSVStockDataDao;
import com.stockmarket.www.dao.jdbc.JdbcMemberDao;
import com.stockmarket.www.entity.RecordAsset;
import com.stockmarket.www.entity.CurStock;
import com.stockmarket.www.entity.HaveStockView;
import com.stockmarket.www.service.SystemService;

public class BasicSystemService implements SystemService{
	private static final int STOCK_CODE_NUM = 1;
	// for update Market 
	//<th> 회사명|종목코드|업종|주요제품|상장일|결산월|대표자명|홈페이지|지역 </th>
	private static final int COMPANY_INFO_COLUMN = 9;    
	private static final String KOSPI = "stockMkt";
	private static final String KOSDAQ = "kosdaqMkt";
	List<String[]> companyList;
	String[] dataBuffer;
	CSVStockDataDao log = new CSVStockDataDao();
	MemberDao memberDao;
	//RecordAsset
	
	/*-------------------------- refreshStockPrice ----------------------------*/
	public void refreshStockPrice(String pathOfKospi, String pathOfKosdaq) {
		CSVStockDataDao data = new CSVStockDataDao();
		List<String> codeNums = new ArrayList<>();
		List<CurStock> kospi;
		List<CurStock> kosdaq;
		
		//CSV 를 참조하여 KOSPI, KOSDAQ 모든 종목에 대한 종목코드를 가져온다
		codeNums = data.getColumnData(STOCK_CODE_NUM, pathOfKospi);
		kospi = getCurrentStockPrice(codeNums);
		
		codeNums = data.getColumnData(STOCK_CODE_NUM, pathOfKosdaq);
		kosdaq = getCurrentStockPrice(codeNums);
		
		//TODO
		//갱신된 데이터를 넘겨주는 작업
		
	}
	
	private List<CurStock> getCurrentStockPrice(List<String> codeNums) {
		Document doc = null;
		CurStock curStockInfo = new CurStock();
		List<CurStock> data = new ArrayList<>();
		
		for(String codeNum : codeNums) {
			String url = "https://finance.naver.com/item/main.nhn?code=" + codeNum;
			try {
				doc = Jsoup.connect(url).ignoreContentType(true)
										.timeout(5000)
										.get();
			} catch (IOException e) {
				AppContext.setLog("네이버 금융 크롤링도중 IOException 발생", BasicSystemService.class.getName());
				e.printStackTrace();
			}
			
			//현재가, 상태(상승 or 하락), 금액, +/-, percent 를 가져오는 CSS query 문 
			Elements status = doc.select(".no_today span:eq(0), .no_exday em span:lt(2)");
			if(status == null) {
				AppContext.setLog("네이버 금융 크롤링 데이터가 null 일 경우", BasicSystemService.class.getName());
				return null;
			}
			
			//요청한 페이지에 대한 실패시 데이터를 저장하지 않는다. codeNum + 크롤링 데이타  + %
			if(status.text().length() != 0) 
				data.add(curStockInfo.parser(codeNum + " " + status.text()));
		}
		return data;
	}

	
	/*-------------------------- update Market ----------------------------*/
	@Override
	public boolean updateMarket(String market) {
		Document doc = null;
		CSVStockDataDao data = new CSVStockDataDao();
		companyList = new ArrayList<String[]>();
		dataBuffer = new String[COMPANY_INFO_COLUMN];
		String type = null;
		
		if(market.equals("KOSPI"))
			type = "stockMkt";
		
		if(market.equals("KOSDAQ"))
			type = "kosdaqMkt";
		
		String url = "http://kind.krx.co.kr/corpgeneral/corpList.do"
				+ "?method=download"
				+ "&searchType=13"
				+ "&orderMode=3"
				+ "&orderStat=D"
				+ "&marketType=" + type; //stockMkt 코스피  kosdaqMkt 코스닥
		
		try {
			doc = Jsoup.connect(url).ignoreContentType(true)
									.timeout(5000)
									.post();
		} catch (IOException e) {
			AppContext.setLog("코스피/코스닥 excel 파일다운로드시 IOException 발생", BasicSystemService.class.getName());
			e.printStackTrace();
		}
		
		//tr Tag 이하를 선택
		Elements contents = doc.select("tr");
		if(contents == null) {
			AppContext.setLog("코스피/코스닥 파일다운로드 데이터가 null 인경우", BasicSystemService.class.getName());
			return false;
		}

		//반복되는 th, td tag 로 sorting 한다
		write(contents, "th"); 
		write(contents, "td"); 
		try {
			// KOSPI.csv or KOSDAQ.csv 를 생성한다
			data.makeCSV("WebContent/fileUpload/"+market, companyList);
		} catch (IOException e) {
			AppContext.setLog("코스피/코스닥 csv 파일 생성중 IOException 발생", BasicSystemService.class.getName());
			e.printStackTrace();
		}
		return true;
	}

	private void write(Elements contents, String tag) {
		int cnt = 0;
		for(Element element : contents.select(tag)) {
			dataBuffer[cnt] = element.text();
			cnt++;
			if(cnt % COMPANY_INFO_COLUMN == 0) {
				cnt = 0;
				String[] data = new String[COMPANY_INFO_COLUMN];
				for(int i = 0; i < data.length; i++)
					data[i] = dataBuffer[i];
				
				companyList.add(data);
			}
		}
	}
	/*-------------------------- insert Asset Record ----------------------------*/
	
	@Override
	public int insertRecordAsset(RecordAsset recordAsset) {
		memberDao = new JdbcMemberDao();
	
//		int sum = 0;
//		sum = memberDao.getMember(memberId).getvMoney();
//		
//		List<HaveStockView> list = new ArrayList<>();
//		list.addAll(haveStockDao.getList(memberId));
//		for (HaveStockView data : list) {
//			// (보유종목당 현재가 및 보유수량 확인용)
//			// System.out.println(data.getPrice()+","+data.getQuantity()); 
//			sum += Integer.parseInt(data.getPrice().replaceAll(",",""))*data.getQuantity();
//		}
//		// 가상머니+(현재가*보유수량)+(현재가*보유수량).....
//		// List<HaveStock> quantHaveStocks = haveStockDao.getQuantity(memberId);
		return 0;	
	}
	
	
/*
 * =======================================================================
 * ============================= for Test ================================
 * =======================================================================
 */
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
		case 3: //kospi.csv kosdaq.csv 파일 생성 TEST
				sys.updateMarket("KOSPI");
				sys.updateMarket("KOSDAQ");
				System.out.println("finished");
			break;
		}
	}

}
