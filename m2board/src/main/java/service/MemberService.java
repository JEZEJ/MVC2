package service;

import java.sql.Connection;
import java.sql.SQLException;

import command.DBUtil;
import repository.MemberDao;
import vo.Member;

public class MemberService implements IMemberService {

	@Override // 회원가입
	public int addMember(Member member) {

		int row = 0;
		Connection conn = null;

		try {

			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);

			MemberDao memberDao = new MemberDao();
			row = memberDao.insertMember(conn, member);

			if (row == 0) {
				throw new Exception();
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return row;
	}

	@Override // 로그인
	public Member loginMember(Member parammember) {

		System.out.println("MemberService안에있는 loginMember실행");

		Connection conn = null;
		Member member = new Member();

		try {

			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);

			MemberDao memberDao = new MemberDao();
			member = memberDao.selectMember(conn, parammember);

			if (member == null) {

				throw new Exception();
			}

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();

			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {

			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return member;
	}

}
