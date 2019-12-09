package com.stockmarket.www.service.basic;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.stockmarket.www.controller.system.AppContext;
import com.stockmarket.www.dao.HaveStockDao;
import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.dao.RecordAssetDao;
import com.stockmarket.www.dao.csv.CSVStockDataDao;
import com.stockmarket.www.dao.jdbc.JDBCRecordAssetDao;
import com.stockmarket.www.dao.jdbc.JdbcHaveStockDao;
import com.stockmarket.www.dao.jdbc.JdbcMemberDao;
import com.stockmarket.www.entity.CurStock;
import com.stockmarket.www.entity.HaveStockView;
import com.stockmarket.www.entity.Member;
import com.stockmarket.www.entity.RecordAsset;
import com.stockmarket.www.service.CompanyService;
import com.stockmarket.www.service.SystemService;

public class BasicSystemService implements SystemService {
	private static final int STOCK_CODE_NUM = 1;
	// for update Market
	// <th> 회사명|종목코드|업종|주요제품|상장일|결산월|대표자명|홈페이지|지역 </th>
	private static final int COMPANY_INFO_COLUMN = 9;
	private static final String KOSPI = "stockMkt";
	private static final String KOSDAQ = "kosdaqMkt";
	List<String[]> companyList;
	String[] dataBuffer;
	MemberDao memberDao;
	HaveStockDao haveStockDao;
	RecordAssetDao recordAssetDao;

	/*-------------------------- refreshStockPrice ----------------------------*/
	public void refreshStockPrice(String pathOfKospi, String pathOfKosdaq) {
		CSVStockDataDao data = new CSVStockDataDao();
		List<String> codeNums = new ArrayList<>();
		List<CurStock> kospi;
		List<CurStock> kosdaq;

		// CSV 를 참조하여 KOSPI, KOSDAQ 모든 종목에 대한 종목코드를 가져온다
		codeNums = data.getColumnData(STOCK_CODE_NUM, pathOfKospi);
		kospi = getCurrentStockPrice(codeNums);

		codeNums = data.getColumnData(STOCK_CODE_NUM, pathOfKosdaq);
		kosdaq = getCurrentStockPrice(codeNums);
		
		AppContext.setKospi(kospi);
		AppContext.setKosdaq(kosdaq);
	}

	private List<CurStock> getCurrentStockPrice(List<String> codeNums) {
		Document doc = null;
		CurStock curStockInfo = new CurStock();
		List<CurStock> data = new ArrayList<>();

		for (String codeNum : codeNums) {
			String url = "https://finance.naver.com/item/main.nhn?code=" + codeNum;
			try {
				doc = Jsoup.connect(url).ignoreContentType(true).timeout(5000).get();
			} catch (IOException e) {
				AppContext.setLog("네이버 금융 크롤링도중 IOException 발생", BasicSystemService.class.getName());
				e.printStackTrace();
			}

			// 현재가, 상태(상승 or 하락), 금액, +/-, percent 를 가져오는 CSS query 문
			Elements status = doc.select(".no_today span:eq(0), .no_exday em span:lt(2)");
			if(status == null) {
				AppContext.setLog("네이버 금융 크롤링 데이터가 null 일 경우", BasicSystemService.class.getName());
				return null;
			}

			// 요청한 페이지에 대한 실패시 데이터를 저장하지 않는다. codeNum + 크롤링 데이타 + %
			if (status.text().length() != 0)
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

		if (market.equals("KOSPI"))
			type = "stockMkt";

		if (market.equals("KOSDAQ"))
			type = "kosdaqMkt";

		String url = "http://kind.krx.co.kr/corpgeneral/corpList.do" + "?method=download" + "&searchType=13"
				+ "&orderMode=3" + "&orderStat=D" + "&marketType=" + type; // stockMkt 코스피 kosdaqMkt 코스닥

		try {
			doc = Jsoup.connect(url).ignoreContentType(true).timeout(5000).post();
		} catch (IOException e) {
			AppContext.setLog("코스피/코스닥 excel 파일다운로드시 IOException 발생", BasicSystemService.class.getName());
			e.printStackTrace();
		}

		// tr Tag 이하를 선택
		Elements contents = doc.select("tr");
		if(contents == null) {
			AppContext.setLog("코스피/코스닥 파일다운로드 데이터가 null 인경우", BasicSystemService.class.getName());
			return false;
		}

		// 반복되는 th, td tag 로 sorting 한다
		write(contents, "th");
		write(contents, "td");
		try {
			// KOSPI.csv or KOSDAQ.csv 를 생성한다
			data.makeCSV("WebContent/fileUpload/" + market, companyList);
		} catch (IOException e) {
			AppContext.setLog("코스피/코스닥 csv 파일 생성중 IOException 발생", BasicSystemService.class.getName());
			e.printStackTrace();
		}
		return true;
	}

	private void write(Elements contents, String tag) {
		int cnt = 0;
		for (Element element : contents.select(tag)) {
			dataBuffer[cnt] = element.text();
			cnt++;
			if (cnt % COMPANY_INFO_COLUMN == 0) {
				cnt = 0;
				String[] data = new String[COMPANY_INFO_COLUMN];
				for (int i = 0; i < data.length; i++)
					data[i] = dataBuffer[i];

				companyList.add(data);
			}
		}
	}
	/*-------------------------- insert Asset Record ----------------------------*/

	@Override
	public int insertRecordAsset() {
		memberDao = new JdbcMemberDao();
		haveStockDao = new JdbcHaveStockDao();
		recordAssetDao = new JDBCRecordAssetDao();

		int value = 0;
		int result = 0;
		SimpleDateFormat date = new SimpleDateFormat("YYMMdd");
		String regdate = date.format(System.currentTimeMillis());
		System.out.println(regdate);

		List<Member> memberList = new ArrayList<>();
		memberList.addAll(memberDao.getMemberList());
		for (Member memberData: memberList) {
			int memberId = memberData.getId();
			List<HaveStockView> list = new ArrayList<>();
			list.addAll(haveStockDao.getList(memberId));
			for (HaveStockView data : list) {
				// (보유종목당 현재가 및 보유수량 확인용)
				// System.out.println(data.getPrice()+","+data.getQuantity());
				value += Integer.parseInt(data.getPrice().replaceAll(",", "")) * data.getQuantity();
				result += recordAssetDao.insert(new RecordAsset(memberId, regdate, value));
				System.out.println(result);
				break;
			}
		}
		return result;
	}

	/*
	 * =======================================================================
	 * ============================= for Test ================================
	 * =======================================================================
	 */
	public static void main(String[] args) throws IOException {
		int testIndex = 0;
		BasicSystemService sys = new BasicSystemService();
		
		while(true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("숫자를 입력하시오");
			testIndex = sc.nextInt();
			
			switch(testIndex) {
			case 1: //코스피 코스닥 전종목 현재가 갱신
				//TEST - KOSPI, KOSDAQ 데이터 크롤링 및 callback
				sys.refreshStockPrice("C:\\work\\study\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\stockMarket\\KOSPI.csv",
								      "C:\\work\\study\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\stockMarket\\KOSDAQ.csv");
				break;
			
			case 2: 
				//refreshStockPrice 함수 처리 시간 체크(100Mbps 이하 환경(기현집) 에서 약 7분 소요) 2019-11-24 20:37:54 ~ 2019-11-24 20:44:53
				//refreshStockPrice 함수 처리 시간 체크(100Mbps 환경에서 약 4~5분 소요) 2019-11-28 16:21:19 ~ 2019-11-28 16:17:33
				SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println(date.format(System.currentTimeMillis()));
	
				sys.refreshStockPrice("C:\\Users\\acorn\\Desktop\\Work\\Recent\\mainProject\\WebContent\\fileUpload\\KOSPI.csv",
					      			  "C:\\Users\\acorn\\Desktop\\Work\\Recent\\mainProject\\WebContent\\fileUpload\\KOSDAQ.csv");
						
				System.out.println(date.format(System.currentTimeMillis()));
				break;
			case 3: //kospi.csv kosdaq.csv 파일 생성 TEST
					sys.updateMarket("KOSPI");
					sys.updateMarket("KOSDAQ");
					System.out.println("finished");
				break;
			case 4: //single tone Test for 코스피/코스닥 크롤링 데이터
					List<CurStock> kospi;
					List<CurStock> kosdaq;
					
					kospi = AppContext.getKospi();
					kosdaq = AppContext.getKosdaq();

					if(kospi != null) {
						for(CurStock cur : kospi) {
							System.out.println(cur.toString());
						}
					}
					System.out.println("next");
					sc.nextInt();
					if(kosdaq != null) {
						for(CurStock cur : kosdaq) {
							System.out.println(cur.toString());
						}
					}
					System.out.println("finished-4");
				break;
			case 5: // 보유 자산 리스트 업로드 테스트
				System.out.println("케이스 5");
				sys.insertRecordAsset();
				break;
			case 6:  // search test
//				searchTest("지우개");
//				searchTest("새벽배송");
//				searchTest("카카오톡");
//				searchTest("카카오");
//				searchTest("반도체");
//				searchTest("김정은");
//				searchTest("브라운더스트");
				searchTest("철도");
			case 7:
				return;
			}
			System.out.println("종료");
		}
	}//finished main
	
	private static void searchTest(String search) throws IOException {
		Response response = null;
		Document doc = null;
		
		//전기장비, 전기제품, 건강
		String[] list = {
				"에너지", "게임", "운송", "전기", "통신", "출판", "핸드셋", "문구", "건강", "미디어",
				"반도체", "투자", "소프트", "판매", "생물", "항공사", "광고", "기계", "유통", 
				"생명", "기업", "디스플레이", "사무", "전자", "종이", "목재", "소매",
				"화장품", "교육", "백화점", "건축", "해운사", "호텔", "레저", "레스토랑",
				"상업", "섬유", "의류", "포장재", "조선", "제약", "컴퓨터", "유틸리티",
				"식품", "가구", "전력", "자동차", "화학", "철강", "도로", "철도", "무선", "통신",
				"가스", "자재", "가정", "무역", "기타", "은행", "생명", "IT", "it", "음료",
				"석유", "우주", "국방", "방송", "보험", "부동산", "비철금속", "철금속", "카드",
				"금융", "담배", "증권", "방위"
		};

//		case 1 search + 업종명 검색결과 업종명이름으로 검색된 횟수
//		실패 		
//		새벽배송 검색 결과
//		새벽배송 게임 : 45
//		새벽배송 전기 : 45
//		새벽배송 건강 : 43
//		새벽배송 소프트 : 40
//		새벽배송 광고 : 50
//		새벽배송 백화점 : 44
//		새벽배송 호텔 : 61
//		새벽배송 식품 : 53
//		새벽배송 카드 : 46
//		for(int i = 0; i < list.length; i++) {
//			String str = search + " " + list[i];
//			String url = "https://search.naver.com/search.naver?query=" + str; 
//			try {
//				response = Jsoup.connect(url)
//			            .method(Connection.Method.GET)
//	                    .execute();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//	
//			doc = response.parse();
//			System.out.println(str + " : " + StringUtils.countMatches(doc.select("body").text(), list[i]));
//		}
		
//		case 2 search + 업체 검색결과를 defined 된 단어와 매칭
//		_sp_each_source 제외? ex) 중소기업신문, 머니투데이 
//		Map<String, Integer>result = new HashMap<String, Integer>();
//		String str = search + " " + "업체";
//		String url = "https://search.naver.com/search.naver?query=" + str; 
//		try {
//			response = Jsoup.connect(url)
//					.method(Connection.Method.GET)
//					.execute();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		doc = response.parse();
//		for(int i = 0; i < list.length; i++) {
////			System.out.println(str + " " + list[i] + " : " + StringUtils.countMatches(doc.select("body").text(), list[i]));
//			result.put(search + list[i] , StringUtils.countMatches(doc.select("body").text(), list[i]));
//		}
//
//		str = search + " " + "업종";
//		url = "https://search.naver.com/search.naver?query=" + str; 
//		try {
//			response = Jsoup.connect(url)
//					.method(Connection.Method.GET)
//					.execute();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		doc = response.parse();
//		for(int i = 0; i < list.length; i++) {
////			System.out.println(str + " " + list[i] + " : " + StringUtils.countMatches(doc.select("body").text(), list[i]));
//			Object temp = (Object)(search+list[i]);
//			result.put(search + list[i], result.get(temp) + StringUtils.countMatches(doc.select("body").text(), list[i]));
//		}
//		
//		for (String key : result.keySet()) {
//		    System.out.println(key + " : " + result.get(key));
//
//		}
		
		
//		case3 검색어 + "주식" 검색 결과와 주식명 대비
//		case3 검색어 + "종목" 검색 결과와 주식명 대비
//		"테마주"
//		검색결과 count 에 따른 정확도
//		1위의 종목의 업종리스트를 더한다
		Map<String, Integer>result = new HashMap<String, Integer>();
		String[] arr = {"주식", "종목", "테마주"};
//		String[] stockList = null;
		CSVStockDataDao data = new CSVStockDataDao();
		String Path1 = "C:\\work\\Repository\\stockMarket\\mainProject\\WebContent\\fileUpload\\KOSPI.csv";
		String Path2 = "C:\\work\\Repository\\stockMarket\\mainProject\\WebContent\\fileUpload\\KOSDAQ.csv";
		List<String> stockList = new ArrayList<>();
		stockList = data.getColumnData(0, Path1);
		stockList.addAll(data.getColumnData(0, Path2));
//		for(String str : stockList) 
//			System.out.println(str);
		
		for(int i = 0; i < arr.length; i++) {
			String str = search + " " + arr[i];
			String url = "https://search.naver.com/search.naver?query=" + str; 
			try {
				response = Jsoup.connect(url)
						.method(Connection.Method.GET)
						.execute();
			} catch (IOException e) {
				e.printStackTrace();
			}
			doc = response.parse();
			System.out.println(doc.select("#main_pack").text());
			if(i == 0) {
				for(int j = 0; j < stockList.size() ; j++) {
					result.put(stockList.get(j) , StringUtils.countMatches(doc.select("#main_pack").text(), stockList.get(j)));
				}
//				for (String key : result.keySet()) {
//					if(result.get(key) != 0)
//						System.out.println(key + " : " + result.get(key));
//
//				}
			} else {
				for(int j = 0; j < stockList.size() ; j++) {
					Object temp = (Object)(stockList.get(j));
					result.put(stockList.get(j), result.get(temp) + StringUtils.countMatches(doc.select("#main_pack").text(), stockList.get(j)));
				}
//				for (String key : result.keySet()) {
//					if(result.get(key) != 0)
//						System.out.println(key + " : " + result.get(key));
//
//				}
			}
			
		}
		for (String key : result.keySet()) {
			if(result.get(key) != 0)
				System.out.println(key + " : " + result.get(key));

		}
		
	}
}
