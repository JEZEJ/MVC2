package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Member;

public class MemberDao implements IMemberDao{
	
	
	@Override // 로그인
	public Member selectMember(Connection conn, Member parammember) throws SQLException {
		
		System.out.println("MemberDao안에있는 selectMember실행");
		
		Member loginMember = null;
		
		String sql = "SELECT member_id,member_name FROM member WHERE member_id = ? AND member_pw=PASSWORD(?)"; // 아이디랑 비밀번호가 맞으면 로그인하기
		
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, parammember.getMemberId());
			stmt.setString(2, parammember.getMemberPw()); 
			
			System.out.println("MemberDao.selectMember안에있는 stmt값 : " + stmt);
			
			rset = stmt.executeQuery(); // 쿼리 실행해주기
			
			if(rset.next()) {
				
				loginMember = new Member();		 // 아이디랑 이름만 불러서 출력
				loginMember.setMemberId(rset.getString("member_id"));
				loginMember.setMemberName(rset.getString("member_name"));
				
				System.out.println("loginMember안에 들어가있는 값 : "+loginMember.toString());
				
			}
			
		} finally {
			
			if (rset != null) { rset.close(); }
			if (stmt != null) { stmt.close(); }
		}
		
		return loginMember;
	}

	
}
