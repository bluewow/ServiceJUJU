package com.stockmarket.www.service.repository;

import java.util.List;

import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.dao.jdbc.JdbcMemberDao;
import com.stockmarket.www.ett.Member;
import com.stockmarket.www.service.RankingService;

public class JdbcRankingService implements RankingService{
	private MemberDao memberDao;
	
	public JdbcRankingService() {
		memberDao = new JdbcMemberDao();
	}
	
	@Override
	public List<Member> getMemberList() {
		return memberDao.getMemberList();
	}

	@Override
	public Member getMember(int id) {
		return memberDao.getMember(id);
	}
}
