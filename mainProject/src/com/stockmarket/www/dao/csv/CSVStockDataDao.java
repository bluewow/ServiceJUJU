package com.stockmarket.www.dao.csv;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
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
	
	private Company company;
	
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
	
	public void makeCSV(String filePath, List<String[]> data) throws IOException {
		CSVWriter cw = new CSVWriter(new OutputStreamWriter(new FileOutputStream(filePath+".csv"), "UTF-8"));
		Iterator<String[]> it = data.iterator();
		
		while (it.hasNext()) {
			String[] s = (String[]) it.next();
			cw.writeNext(s);
		}
		cw.close();
	}
	


	public Company searchCompany (String search, String csvFilePath) {
		CSVReader reader = null;

		try {
			reader = new CSVReader(new FileReader(csvFilePath));
			String[] line;

			while ((line = reader.readNext()) != null) {
				if (line[0].equals(search)) {
					
					company = new Company(line[0], line[2], line[7]);
					
//					System.out.println("회사명 : " + line[0] + ", 종목코드 : " + line[2] + ", 웹사이트 : " +
//					line[7] );
				}
			}

		} catch (IOException e) {
			e.printStackTrace();

		} catch (CsvValidationException e) {

			e.printStackTrace();
		}

		return company;
	}



	


	
	
	
	
/*	
//Unit Test
=======
/*
 * =======================================================================
 * ============================= for Test ================================
 * =======================================================================
 */

	public static void main(String[] args) {
		int testIndex = 0;
		CSVStockDataDao data = new CSVStockDataDao();
		List<String> test = new ArrayList<>();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("숫자를 입력하시오");
		testIndex = sc.nextInt();

		switch(testIndex) {
		case 1: // 코스피 종목코드 얻기
			String Path1 = "C:\\work\\study\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\stockMarket\\KOSPI.csv";
			test = data.getColumnData(1, Path1);
			
			for(String str : test) 
				System.out.println(str);
			
			break;
			
		case 2: // 코스닥 종목코드 얻기
			String Path2 = "C:\\work\\study\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\stockMarket\\KOSDAQ.csv";
			test = data.getColumnData(1, Path2);
			
			for(String str : test) 
				System.out.println(str);
			
			break;
		
		case 3: // 유효하지 않은 Path
			test = data.getColumnData(1, "abcd");
			
			for(String str : test) 
				System.out.println(str);
			
			break;
		}
	}
}
