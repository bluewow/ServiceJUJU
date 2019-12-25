package com.stockmarket.www.service.basic;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.stockmarket.www.controller.system.AppContext;
import com.stockmarket.www.controller.system.SystemLib;
import com.stockmarket.www.dao.HaveStockDao;
import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.dao.RecordAssetDao;
import com.stockmarket.www.dao.StockDetailDao;
import com.stockmarket.www.dao.UpjongDao;
import com.stockmarket.www.dao.csv.CSVStockDataDao;
import com.stockmarket.www.dao.jdbc.JDBCRecordAssetDao;
import com.stockmarket.www.dao.jdbc.JdbcHaveStockDao;
import com.stockmarket.www.dao.jdbc.JdbcMemberDao;
import com.stockmarket.www.dao.jdbc.JdbcStockDetailDao;
import com.stockmarket.www.dao.jdbc.JdbcUpjongDao;
import com.stockmarket.www.entity.CurStock;
import com.stockmarket.www.entity.HaveStockView;
import com.stockmarket.www.entity.Member;
import com.stockmarket.www.entity.RecordAsset;
import com.stockmarket.www.entity.StockDetail;
import com.stockmarket.www.entity.Upjong;
import com.stockmarket.www.service.SystemService;

public class BasicSystemService implements SystemService {
	private UpjongDao upjongDao;
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
	StockDetailDao stockDetailDao;
	
	public BasicSystemService() {
		stockDetailDao = new JdbcStockDetailDao();
		upjongDao = new JdbcUpjongDao();
	}
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

	public void setStockDataAll(String codeNum) {
		List<StockDetail> list = new ArrayList<StockDetail>();
		Gson gson = new Gson();
		
		//일별시세 게시판
		String url = "https://m.stock.naver.com/api/item/getTrendList.nhn?code=" + codeNum + "&size=1000";
		Document doc = SystemLib.naverCrawling(url);
		
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(doc.text());
		String values = jsonElement.getAsJsonObject().get("result").toString();

		//크롤링 데이터를 객체에 저장
		StockDetail[] stockDetail= gson.fromJson(values, StockDetail[].class);
		for(StockDetail obj : stockDetail) {
			System.out.println(obj);
		}
		stockDetailDao.insert(stockDetail);
		stockDetailDao.deleteAll();
		System.out.println("END");
		
	}
	
	public List<StockDetail> getStockDetail(String codeNum) {
		return stockDetailDao.get(codeNum);
	}
	
	public void upjongCrawling() {
		
		String upjongUrl = "https://finance.naver.com/sise/sise_group.nhn?type=upjong";
		Document doc = null;

		try {
			doc = Jsoup.connect(upjongUrl).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// tr tag에 업종 링크를 선택

		Elements Industrytable = doc.select("#contentarea_left");

		Iterator<Element> IndustryAtag = Industrytable.select("tr a").iterator();
		Iterator<Element> IndustryName = Industrytable.select("tr a").iterator();
		// IndustryAtag.next().attr("href") => a 링크만 뽑아냄
		// IndustryAtag.next() => 업종 명만 뽑아냄
		ArrayList<String> upjongAtag = new ArrayList<>();
		ArrayList<String> upjonName = new ArrayList<>();
		int cnt = 0;

		// 1. 업종명과 해당링크를 얻는다.
		while (IndustryAtag.hasNext()) 
			upjongAtag.add(IndustryAtag.next().attr("href"));
		
		// 2. 업종명에 해당하는 링크를 타고 들어가서 상세 종목명을 얻는다.
		while (IndustryName.hasNext()) 
			upjonName.add(IndustryName.next().text().trim());
		
		
		// 2차 작업 - 업종과 주식종목을 매칭
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<Upjong> upjongList = new ArrayList<>();

		int totalCnt = 0;
		for (int i = 0; i < upjongAtag.size(); i++) {
			List<String> list = new ArrayList<String>();
			String url = "https://finance.naver.com" + upjongAtag.get(i);
			 

			try {
				doc = Jsoup.connect(url).get();
			} catch (IOException e) {
				e.printStackTrace();
			}

			Elements companyList = doc.select("tbody a");
			String detailCompanyList = companyList.select("a").text().trim();
			String[] companyArray = detailCompanyList.split("  ");

			for (String string : companyArray) {
				list.add(string);
			}
			map.put(upjonName.get(i), list);
		} //-업종명 넣는작업
		
		List<String> getData = new ArrayList<String>();
		for(String k : map.keySet()) { //업종
			getData = map.get(k);
			for(String j : getData) { //한 업종내의 종목들
				Upjong upjong = new Upjong(k, j);
				upjongList.add(upjong);
				totalCnt++;
//            	   System.out.println(k + " : " + j); //for debugging
			}
//			System.out.println(k); //for debugging
		}
		upjongDao.insert(upjongList);
		System.out.println("end"+ totalCnt);
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
//				searchTest("미사일");
//				searchTest("키움증권");
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
				sys.setStockDataAll("095660");
//				service.getAllDailyPrice("004170");
				return;
			case 8: //stockdetailDao 의 저장된 데이터를 가져온다
				StockDetailDao s;
				s = new JdbcStockDetailDao();
				List<StockDetail> stock = s.get("095660");
				for(StockDetail obj : stock)
					System.out.println(obj.toString());
				
				return;
			case 9:
				sys.upjongCrawling();
				return;
			case 10:
				JdbcUpjongDao upjongDao = new JdbcUpjongDao();
				upjongDao.delete();
			}
			
			System.out.println("종료");
		}
	}//finished main
	
	
}
