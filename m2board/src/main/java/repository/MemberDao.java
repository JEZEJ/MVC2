package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.PartialResultException;

import vo.Member;

public class MemberDao implements IMemberDao{
	
	@Override // 회원가입
	public int insertMember(Connection conn, Member member) throws SQLException {
		
		System.out.println("MemberDao안에있는 insertMember실행");
		
		int row = 0;
		
		// ex INSERT INTO member (member_id,member_pw,member_name,member_age,member_address,member_phone,create_date) VALUES ('zeze',PASSWORD('1234'),'제제',20,'서울시','01012345678',NOW());
		String sql = "INSERT INTO member (member_id,member_pw,member_name,member_age,member_address,member_detailAddress,member_phone,create_date) VALUES (?,PASSWORD(?),?,?,?,?,?,NOW())";
		
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		try {
			
			// sql쿼리문에 set해주기
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			stmt.setString(3, member.getMemberName());
			stmt.setInt(4, member.getMemberAge());
			stmt.setString(5, member.getMemberAddress());
			stmt.setString(6, member.getMemberDetailAddress());
			stmt.setString(7, member.getMemberPhone()); 
			
			row = stmt.executeUpdate();
			
			
		} finally {
			
			if(rset != null) {rset.close();}
			if(stmt != null) {stmt.close();}
			
			System.out.println("MemberDao.insertMember안에있는 row값 : " + row);
			
		}
		
		return row;
	}
	
	
	@Override // 로그인
	public Member selectMember(Connection conn, Member parammember) throws SQLException {
		
		System.out.println("MemberDao안에있는 selectMember실행");
		
		Member loginMember = null;
		
		// 아이디랑 비밀번호가 맞으면 아이디랑 이름값 출력하기
		String sql = "SELECT member_id, member_name FROM member WHERE member_id = ? AND member_pw=PASSWORD(?)"; 
				
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, parammember.getMemberId()); //vo에 있는 id값을 가져와서 넣어줌
			stmt.setString(2, parammember.getMemberPw()); 
			
			System.out.println("MemberDao.selectMember안에있는 stmt값 : " + stmt);
			
			rset = stmt.executeQuery(); // 쿼리 실행해주기
			
			if(rset.next()) {
				
				loginMember = new Member();	// 아이디랑 이름만 불러서 출력
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
