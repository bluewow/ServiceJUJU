package com.stockmarket.www.service.basic;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.stockmarket.www.service.SearchCompanyService;

public class BasicSearchCompanyService implements SearchCompanyService {

	@Override
	public String[] searchCompany(String search, String csvFilePath) {
		 
		 CSVReader reader = null;
//		 String companyname = "";
//		 String stockcode = "";
//		 String homepageAddress="";
		 
		 search = "대한항공";
		 String[] searchResult = new String[3]; 
		 
	        try {
	        	reader = new CSVReader(new FileReader(csvFilePath));
	        	String[] line;
	           try {
	                while ((line = reader.readNext()) != null) {
	                    if (line[0].equals(search)) {
	                    	
	                    	searchResult[0] = line[0];
	                    	searchResult[1] = line[2];
	                    	searchResult[2] = line[7];
	                        
	                        
	                        System.out.println("회사명 : " + line[0] + ", 종목코드 : " + line[2] + ",  웹사이트 : " + line[7] + "]");
	                    }
	                }
	            } catch (CsvValidationException e) {

	                e.printStackTrace();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return searchResult;
	    }
	
	    
		
	}


