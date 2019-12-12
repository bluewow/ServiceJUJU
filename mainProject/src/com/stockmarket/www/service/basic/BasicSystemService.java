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
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
import com.stockmarket.www.entity.Company;
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
				searchTest("미사일");
//				searchTest("브라운더스트");
//				searchTest("철도");
//				searchTest("기저귀");
//				searchTest("4차산업");
//				searchTest("손흥민");
//				searchTest("트럼프");
//				searchTest("비트코인");
//				searchTest("네오위즈");
//				searchTest("암호화폐");
//				searchTest("로봇");
//				searchTest("SLAM");
//				searchTest("조국");
			case 7:
				return;
			}
			System.out.println("종료");
		}
	}//finished main
	
//		case3 검색어 + "주식" 검색 결과와 주식명 대비
//		case3 검색어 + "종목" 검색 결과와 주식명 대비
//		"테마주"
//		검색결과 count 에 따른 정확도 --1차필터
//		1,2 or 3위 종목의 업종리스트 찾아 검색된 종목이 포함되어있다면 추가 한다 -- 2차필터  
//		증권사, 신문사 이름, 고유명사 --3차필터
	//  조국 같은 테마주는 업종에서 필터링 되지 않는다.
	private static void searchTest(String search) throws IOException {
		Response response = null;
		Document doc = null;
		Map<String, Integer>result = new HashMap<String, Integer>();
		String[] arr = {"주식", "종목", "테마" };
		CSVStockDataDao data = new CSVStockDataDao();
		String Path1 = "C:\\work\\Repository\\stockMarket\\mainProject\\WebContent\\fileUpload\\KOSPI.csv";
		String Path2 = "C:\\work\\Repository\\stockMarket\\mainProject\\WebContent\\fileUpload\\KOSDAQ.csv";
		List<String> stockList = new ArrayList<>();
		stockList = data.getColumnData(0, Path1);
		stockList.addAll(data.getColumnData(0, Path2));
		
		////////////////1차필터
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
					result.put(stockList.get(j), StringUtils.countMatches(doc.select("#main_pack").text(), stockList.get(j)));
					Object temp = (Object)(stockList.get(j));
					result.put(stockList.get(j), result.get(temp)  //오디오클립 요소 제거
							- StringUtils.countMatches(doc.select(".section.sp_music_audio._sp_nmusic_audio._prs_aud_cll").text(), stockList.get(j)));
				}
			} else {
				for(int j = 0; j < stockList.size() ; j++) {
					Object temp = (Object)(stockList.get(j));
					result.put(stockList.get(j), result.get(temp) + StringUtils.countMatches(doc.select("#main_pack").text(), stockList.get(j)));
					result.put(stockList.get(j), result.get(temp) //오디오클립 요소 제거 
							- StringUtils.countMatches(doc.select(".section.sp_music_audio._sp_nmusic_audio._prs_aud_cll").text(), stockList.get(j)));
				}
			}
		}
//		for (String key : result.keySet()) {
//			if(result.get(key) != 0)
//				System.out.println(key + " : " + result.get(key));
//
//		}
		////////////////// 2차필터
		//LinkedHashMap preserve the ordering of elements in which they are inserted
		LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
		//Use Comparator.reverseOrder() for reverse ordering
		result.entrySet()
	    .stream()
	    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
	    .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
	 
		System.out.println("Reverse Sorted Map   : " + reverseSortedMap);
		List<Company> company = new ArrayList<>();
		List<String> storageCompany = new ArrayList<String>();
		List<String> storageCodeNum = new ArrayList<String>();
		List<String> finalCompany = new ArrayList<String>();
		CSVStockDataDao kospi = new CSVStockDataDao(Path1);
		CSVStockDataDao kosdaq = new CSVStockDataDao(Path2);
		String tempCompany = null;
		List<Integer> tempCount = new ArrayList<>();
		for(String key : reverseSortedMap.keySet()) {
			if(result.get(key) != 0) {
				storageCompany.add(key);
				tempCount.add(result.get(key));
				System.out.println(key + " : " + result.get(key));
			}
		}

//		//종목명으로 codeNum 얻어오기 : 카운팅된 숫자가 같을경우 종목명 선별??
//		if(kospi.searchCompany(key) != null)
//			System.out.println(kospi.searchCompany(key).getcompanyName() + " : " + kospi.searchCompany(key).getCodeNum());
//		if(kosdaq.searchCompany(key) != null)
//			System.out.println(kosdaq.searchCompany(key).getcompanyName() + " : " + kosdaq.searchCompany(key).getCodeNum());
//		
//		
//		for(Company c : company) {
//			System.out.println(c.getcompanyName() + " : " + c.getStockItemName());
//		}
		
		for(String k : storageCompany) {
			if(kospi.searchCompany(k) != null)
				storageCodeNum.add(kospi.searchCompany(k).getCodeNum());
			if(kosdaq.searchCompany(k) != null)
				storageCodeNum.add(kosdaq.searchCompany(k).getCodeNum());
				
//			if(kospi.searchCompany(key) != null)
//				System.out.println(kospi.searchCompany(key).getcompanyName() + " : " + kospi.searchCompany(key).getCodeNum());
//			if(kosdaq.searchCompany(key) != null)
//				System.out.println(kosdaq.searchCompany(key).getcompanyName() + " : " + kosdaq.searchCompany(key).getCodeNum());
		}
	
		String defaultURL = "https://finance.naver.com";
		int index = 0;
		for(String k : storageCodeNum) {
			index++;
			System.out.println("print : " + k + " ");
		
			String url = "https://finance.naver.com/item/main.nhn?code=" + k;
			System.out.println("print : " + url);
			try {
				response = Jsoup.connect(url)
						.method(Connection.Method.GET)
						.execute();
			} catch (IOException e) {
				e.printStackTrace();
			}
			doc = response.parse();
//			System.out.println(doc.select(".section.trade_compare > a").attr("href"));
			url = defaultURL + doc.select(".section.trade_compare > a").attr("href");
			try {
				response = Jsoup.connect(url)
						.method(Connection.Method.GET)
						.execute();
			} catch (IOException e) {
				e.printStackTrace();
			}
			doc = response.parse();
//			System.out.println(doc);
			System.out.println("tempCompany : " + doc.select("tr td a").text());
			System.out.println("storaged : " + storageCompany);
			tempCompany = doc.select("tr td a").text();
			System.out.println("TTTTTTTTEST : " + tempCompany);
//			tempCompany ="이마트  이마트  이마트";
			String[] temp = tempCompany.split("  ");			
//			for(String m : temp) {
//				m = m.trim();
//				System.out.println(m + "," + m.length());
//			}
			
			for(String e : storageCompany) {
				for(String m : temp) {
//					System.out.println(m + " , " + e);
					m = m.trim();
					if(m.equals(e))
						finalCompany.add(e);
				}
			}
			if(index >= 3) {
				if(tempCount.get(index) == tempCount.get(index+1)) {
					continue;
				}
				break;
			}
		}
		System.out.println("=========");
		for(String v : finalCompany)
			System.out.println(v);
		
		
		
		//업종명내에  주식종목리스트가 존재한다면  ok 
		
		//주식종목 리스트와 counting 된 종목과 비교한 결과가 최종값
			
	}
}
