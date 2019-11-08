package com.stockmarket.www.service.inheritance;

import java.util.List;

import com.stockmarket.www.model.Member;

public interface MemberService {
	List<Member> getMemberList();
	
	Member getMember();
}
