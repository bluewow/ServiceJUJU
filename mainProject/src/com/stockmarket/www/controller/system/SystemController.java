package com.stockmarket.www.controller.system;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.stockmarket.www.service.SystemService;
import com.stockmarket.www.service.basic.BasicSystemService;

@WebServlet("/main")
public class SystemController extends HttpServlet {
	//thread 함수를 한번만 실행시키기 위한 flag
	static boolean oneShotFlag;
	static String preHour; 
	String pathOfKospi = null;
	String pathOfKosdaq = null;
	SystemService service;
	
	public SystemController() {
		oneShotFlag = false;
		service = new BasicSystemService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(oneShotFlag == true) {
			request.getRequestDispatcher("main.jsp").forward(request, response);
			return;
		}
		oneShotFlag = true;
		
		//KOSPI, KOSDAQ CSV 파일의 절대경로를 가져오고 저장한다.
		ServletContext application = request.getServletContext();
		pathOfKospi = application.getRealPath("/KOSPI.csv");
		pathOfKosdaq = application.getRealPath("/KOSDAQ.csv");
		
		Thread thread = new Thread(()->{
			try {
				while(true) 
					systemThread();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		});
		
		thread.start();
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}

	private void systemThread() throws InterruptedException, IOException {
		SimpleDateFormat date = new SimpleDateFormat("HH"); //HH : 0~23시  기타형식예 "yyyy-MM-dd HH:mm:ss"
		String curHour = date.format(System.currentTimeMillis());
		
		//오전 5시 하루에 한번 KOSPI.csv KOSDAQ.csv 파일을 갱신한다.
		if(curHour.equals("5") && preHour.equals("4")) {
			service.updateMarket("KOSPI");
			service.updateMarket("KOSDAQ");
		}
			
		//주식가격 refresh by 크롤링 9시 ~ 18시까지 실행
		if(Integer.parseInt(curHour) >= 9 && Integer.parseInt(curHour) <= 18)
			service.refreshStockPrice(pathOfKospi, pathOfKosdaq);
		
		//18시 장종료후 19시 주식데이터 갱신 
		if(curHour.equals("19") && preHour.equals("18")) {
//			TODO
		}
		
		if(curHour.equals("18") && preHour.equals("17")) {
			System.out.println("실행 중인가");
			service.insertRecordAsset();
		}
		
		
		//현재 시간을 preHour flag 에 저장
		preHour = curHour;
		
		SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH : 0~23시  기타형식예 "yyyy-MM-dd HH:mm:ss"
		System.out.println(date1.format(System.currentTimeMillis()));
		//10분주기 - refreshStockPrice 함수실행시 약 7분소요로 10분주기로 변경
		Thread.sleep(1000 * 60 * 10);
	}

/*
 * =======================================================================
 * ============================= for Test ================================
 * =======================================================================
 */
	void timeTest(String mm) {
		String curHour = mm;
		
		//오전 5시 하루에 한번 KOSPI.csv KOSDAQ.csv 파일을 갱신한다.
		if(curHour.equals("5") && preHour.equals("4"))  
			System.out.println("success-1");

		//주식가격 refresh by 크롤링 9시 ~ 18시까지 실행
		if(Integer.parseInt(curHour) >= 9 && Integer.parseInt(curHour) <= 18)
			System.out.println("success-2");
			
		//18시 장종료후 19시에 주식데이터 갱신
		if(curHour.equals("19") && preHour.equals("18"))  
			System.out.println("success-3");
		
		if(curHour.equals("18") && preHour.equals("17")) {
			System.out.println("success-4");
			service.insertRecordAsset();
		}
		
		preHour = curHour;
	}
	
	public static void main(String[] args) {
		int testIndex = 0;
		SystemController system = new SystemController();
		
		Scanner input = new Scanner(System.in);
		System.out.println("숫자를 입력하시오");
		testIndex = input.nextInt();
		
		switch(testIndex) {
		case 1:	//시간 조건문 Test
			while(true) {
				Scanner sc = new Scanner(System.in);
				System.out.println("시간을 입력하시오 : ");
				system.timeTest(sc.next());
			}
		case 2:
			break;
		}
		
	}
}
