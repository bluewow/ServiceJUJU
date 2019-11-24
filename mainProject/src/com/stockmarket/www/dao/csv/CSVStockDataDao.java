package com.stockmarket.www.dao.csv;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.stockmarket.www.entity.Company;


/*
 * Column 인덱스를 통해 특정 데이타를 가져온다
 * 0 : "회사명"
 * 1 : "종목코드"
 * 2 : "업종"
 * 3 : "주요제품"
 * 4 : "상장일"
 * 5 : "결산월"
 * 6 : "대표자명"
 * 7 : "홈페이지"
 * 8 : "지역"
 * 
 * Path : 해당 CSV 파일의 realPath 를 요구한다
 * */
public class CSVStockDataDao {
	public List<String> getColumnData(int column, String Path) {
		CSVReader file = null;
		List<String> list = new ArrayList<>();

		try {
			file = new CSVReader(new FileReader(Path));
			String[] data;
	
			boolean firstFileLine = true;
			while ((data = file.readNext()) != null) {
				//CSV 파일의 유효하지 않은 data인 첫번째 라인을  무시한다
				if(firstFileLine == true) {
					firstFileLine = false;
					continue;
				}
				
				int index = 0;
				for(String str : data) {
					if(index == column) 
						list.add(str);

					index++;
				}
			}
		} catch (IOException e) {
//			TODO
//			LOG 추가
			e.printStackTrace();
		} catch (CsvValidationException e) {
//			TODO
//			LOG 추가
			e.printStackTrace();
		}
		
		return list;
	}
	
	//Unit Test
	/*	
	public static void main(String[] args) {
		int testIndex = 0;
		CSVStockDataDao data = new CSVStockDataDao();
		List<String> test = new ArrayList<>();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("숫자를 입력하시오");
		testIndex = sc.nextInt();

		//TEST
		//1 - 코스피 종목코드 얻기
		//2 - 유효하지 않은 Path
		switch(testIndex) {
		case 1:
			String Path = "C:\\work\\study\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\stockMarket\\KOSPI.csv";
			test = data.getColumnData(1, Path);
			
			for(String str : test) 
				System.out.println(str);
			
			break;
		
		case 2:
			test = data.getColumnData(1, "abcd");
			
			for(String str : test) 
				System.out.println(str);
			
			break;
		}
	}
	 */
}
