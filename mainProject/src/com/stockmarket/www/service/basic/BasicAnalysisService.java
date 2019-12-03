package com.stockmarket.www.service.basic;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.stockmarket.www.controller.system.AppContext;
import com.stockmarket.www.entity.CaptureMemo;
import com.stockmarket.www.service.AnalysisService;

public class BasicAnalysisService implements AnalysisService{

	@Override
	public void content() {
		
	}

	@Override
	public void predict() {
		
	}

	@Override
	public void refreshPrice() {
		
	}

	@Override
	public CaptureMemo captureDataCrawling(String codeNum) {
//		CaptureMemo data = new CaptureMemo();
		Document doc = null;
		String url = "https://finance.naver.com/item/main.nhn?code=095660";
		
		try {
			doc = Jsoup.connect(url).ignoreContentType(true).timeout(5000).get();
		} catch (IOException e) {
			AppContext.setLog("캡처 데이타 크롤링도중 IOException 발생", BasicAnalysisService.class.getName());
			e.printStackTrace();
		}
		System.out.println("=====start=====");
		//PER - PBR - ROE - 시가총액
		Elements element0 = doc.select(".aside_invest_info");
		System.out.println(element0);
		//get 부채비율
		Elements element1 = doc.select("div.section.cop_analysis div.sub_section tr:eq(6) td:nth-last-child(2)");
//		System.out.println(element1.text());
		System.out.println("=====end=====");
//		Elements status = doc.select(".no_today span:eq(0), .no_exday em span:lt(2)");
		
		return null;
	}

}
