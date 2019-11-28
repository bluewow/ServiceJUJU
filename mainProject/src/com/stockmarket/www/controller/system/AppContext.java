package com.stockmarket.www.controller.system;

import com.stockmarket.www.dao.csv.CSVStockDataDao;
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
 *  로그기록 Example
 *  AppContext.setLog("Example", 클래스이름.class.getName());
 *  AppContext.setLog("왜 1212", BasicSystemService.class.getName());
 * */



public class AppContext {
	CSVStockDataDao log = new CSVStockDataDao();
	
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


	//for Test
	public static void main(String[] args) {
		AppContext app_1 = AppContext.getInstance();
		AppContext app_2 = AppContext.getInstance();
		
		//Single Tone 테스트
		if(app_1==app_2)
			System.out.println("같다");
		else 
			System.out.println("다르다");
		
		//로그 기록
		AppContext.setLog("왜 1212", BasicSystemService.class.getName());
	}
}
