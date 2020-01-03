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
	public List<CaptureMemoView> getList(int id) {
		return captureMemoDao.getList(id);
	}
	
	@Override
	public CaptureMemo get(int id) {
		return captureMemoDao.get(id);
	}

	@Override
	public int update(CaptureMemo captureMemo) {
		return captureMemoDao.update(captureMemo);
	}

	@Override
	public int delete(int id) {
		return captureMemoDao.delete(id);
	}

	@Override
	public int insert(CaptureMemoView memoView) {
		return captureMemoDao.insert(memoView);
	}
}
