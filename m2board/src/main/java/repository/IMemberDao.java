package repository;

import java.sql.Connection;
import java.sql.SQLException;

import vo.Member;

public interface IMemberDao {
	
	
	// 로그인
	Member selectMember(Connection conn,Member member) throws SQLException; //ID,PASSWORD 두개를 받아줘야함
	
	// 회원가입
	

}
