package com.stockmarket.www.service.basic;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.stockmarket.www.dao.csv.CSVStockDataDao;
import com.stockmarket.www.entity.Company;
import com.stockmarket.www.service.SearchCompanyService;

public class BasicSearchCompanyService implements SearchCompanyService {
	
	private CSVStockDataDao csvStockDataDao;
	
	
	
	
	public BasicSearchCompanyService() {
		
		csvStockDataDao = new CSVStockDataDao();
	}




	@Override
	public Company searchCompany(String search, String csvFilePath) {
		
		return csvStockDataDao.searchCompany(search, csvFilePath);
		
		
	}

	
	

	
	

}
