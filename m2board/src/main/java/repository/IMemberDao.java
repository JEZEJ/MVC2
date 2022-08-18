package repository;

import java.sql.Connection;

import vo.Member;

public interface IMemberDao {
	// 매개값 : id , pw
	// 반환값 : 세션에 저장될 Member의 정보 일부
	Member selectMember(Connection conn,Member member);

}
