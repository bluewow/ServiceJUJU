package com.stockmarket.www.controller.system;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;



// TODO
// 5분 간격으로 주식 가격 크롤링
// 장종료후 주식데이터 갱신

@WebServlet("/main")
public class SystemController extends HttpServlet {
	//thread 함수를 한번만 실행시키기 위한 flag
	static boolean oneShotFlag = false;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if(oneShotFlag == true) {
			request.getRequestDispatcher("main.jsp").forward(request, response);
			return;
		}

		oneShotFlag = true;
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
		String format_time1 = date.format(System.currentTimeMillis());
//		System.out.println(format_time1);
		
		//주식가격 refresh by 크롤링
		refreshStockPrice();
		
		//6시 장종료후 주식데이터 갱신 
		

		Thread.sleep(1000 * 60 * 5);
	}

	private void refreshStockPrice() throws IOException {
		Document doc = null;
		String url = "https://finance.naver.com/item/main.nhn?code=140410";
		
		
		doc = Jsoup.connect(url).ignoreContentType(true)
								.timeout(5000)
								.get();
		
		//현재가 가져오기 (임시)
		Element element = doc.selectFirst("p.no_today span");
//		System.out.println(element.text());
		
		
		
		
	}
	
//	for TEST
	public static void main(String[] args) throws IOException {
		SystemController sys = new SystemController();
		
		sys.refreshStockPrice();
	}
}
