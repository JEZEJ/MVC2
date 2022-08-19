package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import command.DBUtil;
import repository.BoardDao;
import vo.Board;
import vo.Member;

public class BoardService implements IBoardService {

	@Override
	public Map<String, Object> getBoardList(final int rowPerPage, int currentPage) {

		System.out.println("BoardService안에있는 getBoardList 실행");

		Map<String, Object> map = null;

		Connection conn = null;
		int beginRow = 0;
		beginRow = (currentPage - 1) * rowPerPage;

		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);

			BoardDao boardDao = new BoardDao(); // BoardDao 객체생성

			map = new HashMap<>();
			map.put("list", boardDao.selectBoardListByPage(conn, rowPerPage, beginRow)); // Dao로 넘어감

			System.out.println("BoardService.getBoardList.map >> " + map);

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
		return map;
	}

	// 게시판 상세보기
	@Override
	public List<Board> getBoardOne(int boardNo) { // Service에서 Dao로 넘어감

		System.out.println("BoardService안에있는 getBoardOne 실행");

		List<Board> list = null;
		Connection conn = null;

		try {

			conn = new DBUtil().getConnection(); // mariadb랑 연동해줌
			BoardDao boardDao = new BoardDao(); // BoardDao실행
			list = boardDao.selectBoardOne(conn, boardNo);

			if (list == null) {

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

		return list;
	}

	@Override // 게시글 입력하기
	public int insertBoard(Board board) {

		int row = 0;
		Connection conn = null;

		try {

			conn = new DBUtil().getConnection(); // mariadb랑 연동해줌
			conn.setAutoCommit(false);

			BoardDao boardDao = new BoardDao();
			row = boardDao.insertBoard(conn, board);

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
	

	@Override // 조회수 수정
	public int modifyRead(int boardNo) {

		System.out.println("BoardService안에있는 modifyRead 실행");

		int row = 0;
		Connection conn = null;

		try {

			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);

			BoardDao boardDao = new BoardDao();
			row = boardDao.updateReadCount(conn, boardNo);

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

	@Override // 좋아요 수정
	public int modifyNice(int BoardNo) {

		System.out.println("BoardService안에있는 modifyNice 실행");

		int row = 0;
		Connection conn = null;

		try {

			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);

			BoardDao boardDao = new BoardDao();
			row = boardDao.updateNice(conn, BoardNo);

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

}
