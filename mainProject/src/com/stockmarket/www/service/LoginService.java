package com.stockmarket.www.service;

import com.stockmarket.www.entity.Member;

public interface LoginService {
	
//	등록된 회원인지 체크
	boolean isValidMember(String email, String pwd);
	
//	회원등록
	boolean insertMember(Member member);
	
//	회원탈퇴
	boolean deleteMember();
	
//  get Member by eMail
	Member getMember(String email);
}
