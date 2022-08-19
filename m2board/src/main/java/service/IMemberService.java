package service;

import java.sql.SQLException;

import vo.Member;

public interface IMemberService {
	
		// 회원가입
		int addMember(Member member);
	
		// 로그인
		Member loginMember(Member member) ;
		
}
