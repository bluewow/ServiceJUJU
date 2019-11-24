package com.stockmarket.www.controller.system;

import java.io.IOException;
import java.text.SimpleDateFormat;

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

import com.stockmarket.www.service.basic.BasicSystemService;

@WebServlet("/main")
public class SystemController extends HttpServlet {
	//thread 함수를 한번만 실행시키기 위한 flag
	static boolean oneShotFlag;
	String pathOfKospi = null;
	String pathOfKosdaq = null;
	BasicSystemService service;
	
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
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = date.format(System.currentTimeMillis());
		
		//오전 5시 하루에 한번 KOSPI.csv KOSDAQ.csv 파일을 갱신한다.

		//주식가격 refresh by 크롤링 9시 ~ 6시까지 실행
		service.refreshStockPrice(pathOfKospi, pathOfKosdaq);
		
		//6시 장종료후 주식데이터 갱신 

		//10분주기 - refreshStockPrice 함수실행시 약 7분소요
		Thread.sleep(1000 * 60 * 10);
	}

	//for Test
//	/*
	void timeTest(String mm, String ss) {
		//오전 5시 하루에 한번 KOSPI.csv KOSDAQ.csv 파일을 갱신한다.

		//주식가격 refresh by 크롤링 9시 ~ 6시까지 실행
		
		//6시 장종료후 주식데이터 갱신
	}
	
	public static void main(String[] args) {
	
	}
//	*/
}
