package com.stockmarket.www.service.basic;

import java.util.List;

import com.stockmarket.www.dao.CaptureMemoDao;
import com.stockmarket.www.dao.jdbc.JdbcCaptureMemoDao;
import com.stockmarket.www.entity.CaptureMemo;
import com.stockmarket.www.service.CaptureMemoService;

public class BasicCaptureMemo implements CaptureMemoService {
	
	private CaptureMemoDao captureMemoDao;

	public BasicCaptureMemo() {
		captureMemoDao = new JdbcCaptureMemoDao();
		
	}

	@Override
	public List<CaptureMemo> getcCaptureMemoList() {
		
		return getCaptureMemoList (1,"stockName","","");
	}

	@Override
	public List<CaptureMemo> getCaptureMemoList(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CaptureMemo getMemo(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertCaptuerMemo(CaptureMemo captureMemo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCaptureMemo(CaptureMemo captureMemo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCaptureMemo(CaptureMemo captureMemo) {
		// TODO Auto-generated method stub
		return 0;
	}





}
