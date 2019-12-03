package com.stockmarket.www.service.basic;

import java.util.List;

import com.stockmarket.www.dao.MemberDao;
import com.stockmarket.www.dao.jdbc.JdbcMemberDao;
import com.stockmarket.www.entity.Member;
import com.stockmarket.www.service.RankingService;

public class BasicRankingService implements RankingService{
	private MemberDao memberDao;
	
	public BasicRankingService() {
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

	@Override
	public int getMemberRank(int id) {
		return memberDao.getMemberRank(id);
	}
}
