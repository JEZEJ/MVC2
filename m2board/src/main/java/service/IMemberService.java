package service;

import java.sql.SQLException;

import vo.Member;

public interface IMemberService {
	
		// 로그인
		Member loginMember(Member member) ;
		
}
