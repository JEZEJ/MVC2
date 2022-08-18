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

			if(list == null) {
			
				throw new Exception(); }
			
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
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}



}
