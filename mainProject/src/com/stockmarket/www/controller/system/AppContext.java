package com.stockmarket.www.controller.system;

import java.util.List;
import java.util.Scanner;

import com.stockmarket.www.dao.csv.CSVStockDataDao;
import com.stockmarket.www.entity.CurStock;
import com.stockmarket.www.service.basic.BasicSystemService;

/*
 * SingleTone 
 *  - LOG 및 크롤링 데이터의 주소를 공유하기 위해 생성
 * 
 * 
 * holder에 의한 초기화 사용 
 * 참조 : https://limkydev.tistory.com/67
 *   
 *   
 * 로그기록 Example
 *   - AppContext.setLog("Example", 클래스이름.class.getName());
 *   - AppContext.setLog("왜 1212", BasicSystemService.class.getName());
 *   
 * 크롤링 데이터
 *   - AppContext.getKospi();
 *   - AppContext.getKosdaq();
 * 
 * */


public class AppContext {
	CSVStockDataDao log = new CSVStockDataDao();
	static List<CurStock> kospi;
	static List<CurStock> kosdaq;
	
    private AppContext() { }
    private static class SingleTonHolder {
        private static final AppContext instance = new AppContext();
    }
     
    public static AppContext getInstance() {
        return SingleTonHolder.instance;
    }
    
    public static void setLog(String reason, String className) {
		getInstance().log.makeCSV(reason, className);
	}

    public static List<CurStock> getKospi() {
		return kospi;
	}

	public static void setKospi(List<CurStock> kospi) {
		AppContext.kospi = kospi;
	}

	public static List<CurStock> getKosdaq() {
		return kosdaq;
	}

	public static void setKosdaq(List<CurStock> kosdaq) {
		AppContext.kosdaq = kosdaq;
	}

	/*
 * =======================================================================
 * ============================= for Test ================================
 * =======================================================================
 */
	public static void main(String[] args) {
		AppContext app_1 = AppContext.getInstance();
		AppContext app_2 = AppContext.getInstance();
		
		Scanner sc = new Scanner(System.in);
		
		switch(sc.nextInt()) {
		case 1:
			//Single Tone 테스트
			if(app_1==app_2)
				System.out.println("같다");
			else 
				System.out.println("다르다");
			break;
		case 2:
			//로그 기록
			AppContext.setLog("왜 1212", BasicSystemService.class.getName());
			break;
		case 3:
			AppContext.getKospi();
//			AppContext.getKosdaq();
			break;
		}
	}
}
