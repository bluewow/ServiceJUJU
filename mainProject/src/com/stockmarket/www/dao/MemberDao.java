package com.stockmarket.www.dao;

import java.util.List;

import com.stockmarket.www.ett.Member;

public interface MemberDao {
	List<Member> getMemberList();
	
	Member getMember(int id);
	int updateMember(int id, int vmoney);
}
