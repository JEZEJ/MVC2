package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Member;

public class MemberDao implements IMemberDao{

	@Override // 로그인
	public Member selectMember(Connection conn, Member parammember) throws SQLException {
		
		System.out.println("BoardDao안에있는 selectMember실행");
		
		Member loginMember = null;
		
		String sql = "SELECT member_id,member_pw FROM member WHERE member_id=?' AND member_pw=PASSWORD('?')"; // 아이디랑 비밀번호가 맞으면 로그인하기
		
		PreparedStatement stmt = null;
		ResultSet rset = null;
		Member member = new Member();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw()); //member vo에서 memberid랑 pass를 가져와서 stmt(쿼리) 안에 넣어줘라
			rset = stmt.executeQuery();
			
			System.out.println("member.getMemberId()의 값 : " + member.getMemberId());
			System.out.println("member.getMemberPw()의 값 : " + member.getMemberPw());
			
			if(rset.next()) {
				
				loginMember = new Member();
				
				loginMember.setMemberId(rset.getString("member_id"));
				loginMember.setMemberPw(rset.getString("member_pw"));
				
			}
			
		} finally {
			
			if (rset != null) { rset.close(); }
			if (stmt != null) { stmt.close(); }
		}
		
		return loginMember;
	}
}
