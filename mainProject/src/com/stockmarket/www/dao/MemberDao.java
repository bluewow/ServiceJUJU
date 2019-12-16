package com.stockmarket.www.dao;

import java.util.List;

import com.stockmarket.www.entity.Member;

public interface MemberDao {
	List<Member> getMemberList();
	List<Member> getRankerList();
	
	Member getMember(int id);
	Member getMemberByEmail(String query);	
	int getMemberRank(int id);
	int updateMember(int id, long vmoney);
	int updateMember(int id, String cardPos);
	int insertMember(Member member);
}
