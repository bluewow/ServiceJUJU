package com.stockmarket.www.dao;

import java.util.List;

import com.stockmarket.www.ett.Member;

//회원제재
public interface MemberRestrainDAO {
	List<Member> getMemberList();
	Member getMember(int id);
}
