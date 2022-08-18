package service;

import java.sql.Connection;
import java.sql.SQLException;

import command.DBUtil;
import repository.MemberDao;
import vo.Member;

public class MemberService implements IMemberService {

	@Override
	public Member loginMember(Member parammember) {
		
		Connection conn = null;
		
		Member member = new Member();
		
		try {
			
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);
			
			MemberDao memberDao = new MemberDao();
			member = memberDao.selectMember(conn, parammember);
			
			conn.commit();
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
		
		return member;
	}

}
