package com.stockmarket.www.service.basic;

import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.dao.jdbc.JdbcMemberDao;
import com.stockmarket.www.entity.Member;
import com.stockmarket.www.service.MainService;

public class BasicMainService implements MainService {
	private MemberDao memberDao;
	
	public BasicMainService() {
		memberDao = new JdbcMemberDao();
	}
	
	@Override
	public Member getMember(int id) {
		return memberDao.getMember(id);
	}

}
