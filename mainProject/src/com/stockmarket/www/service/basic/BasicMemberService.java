package com.stockmarket.www.service.basic;

import java.util.List;

import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.dao.jdbc.JdbcMemberDao;
import com.stockmarket.www.entity.Member;
import com.stockmarket.www.service.MemberService;

public class BasicMemberService implements MemberService {
	private MemberDao memberDao;
	
	public BasicMemberService() {
		memberDao = new JdbcMemberDao();
	}
	
	@Override
	public List<Member> getMemberList() {
		return memberDao.getRankerList();
	}

	@Override
	public Member getMember(int id) {
		return memberDao.getMember(id);
	}
}
