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
	public int insertCaptuerMemo(CaptureMemo captureMemo) {
	
		return captureMemoDao.insert(captureMemo);
	}
	@Override
	public List<CaptureMemo> getCaptureMemmoList(int page) {
	
		return getCaptureMemmoList(page);
	}




	@Override
	public int updateCaptureMemo(CaptureMemo captureMemo) {
		
		return captureMemoDao.update(captureMemo);
	}



	@Override
	public int deleteCaptureMemo(int id) {
		
		return captureMemoDao.delete(id);
	}

	@Override
	public CaptureMemo getMemo(int id) {
		// TODO Auto-generated method stub
		return null;
	}







	
	
	
	
	
	
	
	
	

}
