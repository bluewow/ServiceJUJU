package com.stockmarket.www.service.basic;

import java.util.List;

import com.stockmarket.www.dao.CaptureMemoDao;
import com.stockmarket.www.dao.jdbc.JdbcCaptureMemoDao;
import com.stockmarket.www.entity.CaptureMemo;
import com.stockmarket.www.entity.CaptureMemoView;
import com.stockmarket.www.service.CaptureMemoService;

public class BasicCaptureMemoService implements CaptureMemoService {
	private CaptureMemoDao captureMemoDao;

	public BasicCaptureMemoService() {
		captureMemoDao = new JdbcCaptureMemoDao();	
	}

	@Override
	public List<CaptureMemoView> getCaptureMemoList(int page) {
		return captureMemoDao.getList(page);
	}

	@Override
	public CaptureMemo getMemo(int id) {
		return null;
	}

	@Override
	public int updateCaptureMemo(CaptureMemo captureMemo) {
		return captureMemoDao.update(captureMemo);
	}

	@Override
	public int deleteCaptureMemo(int id) {
		return captureMemoDao.delete(id);
	}





}
