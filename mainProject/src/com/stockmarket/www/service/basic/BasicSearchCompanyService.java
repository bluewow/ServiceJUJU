package com.stockmarket.www.service.basic;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.stockmarket.www.service.SearchCompanyService;

public class BasicSearchCompanyService implements SearchCompanyService {

	@Override
	public String[] searchCompany(String search) {
		 String csvFile = "../dataStroage/KOSPI.csv";
		 CSVReader reader = null;
//		 String companyname = "";
//		 String stockcode = "";
//		 String homepageAddress="";
		 
		 String[] searchResult = new String[2]; 
		 
	        try {
	            
	            try {
	                reader = new CSVReader(new FileReader(csvFile));
	                String[] line;
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


