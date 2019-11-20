package com.stockmarket.www.dao;

import java.util.List;

import com.stockmarket.www.entity.Member;

public interface MemberDao {
	List<Member> getMemberList();
	
	Member getMember(int id);
	Member getMember(String field, String query);	
	int updateMember(int id, int vmoney);
}
