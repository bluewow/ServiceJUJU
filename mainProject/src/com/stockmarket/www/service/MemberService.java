package com.stockmarket.www.service;

import java.util.List;

import com.stockmarket.www.entity.Member;

public interface MemberService {

	List<Member> getMemberList();
	
	Member getMember(int id);
}
