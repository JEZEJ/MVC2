package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.PartialResultException;

import vo.Board;
import vo.Member;

public class BoardDao implements IBoardDao { // 인터페이스 IBoardDao를 사용

	@Override // 게시판 목록 보여주기
	public List<Board> selectBoardListByPage(Connection conn, int rowPerPage, int beginRow) throws SQLException {
		// board_no,member_id,board_title,board_content,create_date,board_read

		System.out.println("BoardDao안에있는 selectBoardListByPage실행");

		String sql = "SELECT board_no,member_id,board_title,board_content,create_date,board_read,board_nice FROM board ORDER BY board_no DESC LIMIT ?,?";

		List<Board> list = new ArrayList<Board>();

		PreparedStatement stmt = null;
		Board board = new Board();
		ResultSet rset = null;

		try {

			stmt = conn.prepareStatement(sql); // 쿼리실행
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rset = stmt.executeQuery();

			while (rset.next()) {

				board = new Board();

				board.setBoardNo(rset.getInt("board_no"));
				board.setMemberId(rset.getString("member_id"));
				board.setTitle(rset.getString("board_title"));
				board.setBoardContent(rset.getString("board_content"));
				board.setCreateDate(rset.getString("create_date"));
				board.setBoardRead(rset.getInt("board_read"));
				board.setBoardNice(rset.getInt("board_nice"));

				list.add(board); // list에 값 넣어주기
			}

			System.out.println("BoardDao.selectBoardListByPage.list값 >> " + list);

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			if (rset != null) {
				rset.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}

		return list;
	}

	@Override
	public int selectBoardCnt(Connection conn, int rowPerPage) throws SQLException {

		System.out.println("BoardDao안에있는 selectBoardCnt실행");

		String sql = "SELECT count(*) FROM board";
		int totalRow = 0;
		int lastPage = 0;

		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {

			stmt = conn.prepareStatement(sql);
			rset = stmt.executeQuery();

			if (rset.next()) {
				totalRow = rset.getInt("count(*)");
			}
			lastPage = totalRow / rowPerPage;
			if (totalRow % rowPerPage != 0) {
				lastPage += 1;
			}
		} finally {
			if (rset != null) {
				rset.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}

		return lastPage;
	}

	@Override // 게시판 상세보기
	public List<Board> selectBoardOne(Connection conn, int boardNo) throws SQLException {

		System.out.println("BoardDao안에있는 selectBoardOne실행");

		List<Board> list = null;
		Board boardOne = null;

		String sql = "SELECT board_no,member_id,board_title,board_content,create_date,board_read,board_nice FROM board WHERE board_no=?";
		// 보드넘버를 넘겨서 보드넘버에 해당하는 값을 상세보기로 보여주기

		PreparedStatement stmt = null; // 쿼리실행
		ResultSet rset = null;

		try {

			list = new ArrayList<Board>();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, boardNo);
			System.out.println("stmt.setInt로 board_no에 넣어준값 : " + boardNo);

			rset = stmt.executeQuery();

			if (rset.next()) {

				boardOne = new Board();
				boardOne.setBoardNo(rset.getInt("board_no"));
				boardOne.setMemberId(rset.getString("member_id"));
				boardOne.setTitle(rset.getString("board_title"));
				boardOne.setBoardContent(rset.getString("board_content"));
				boardOne.setCreateDate(rset.getString("create_date"));
				boardOne.setBoardRead(rset.getInt("board_read"));
				boardOne.setBoardNice(rset.getInt("board_nice"));

				list.add(boardOne);

				System.out.println("BoardDao.selectBoardOne에 있는 boardOne값 >>" + boardOne);

			}

		} finally {

			if (rset != null) {
				rset.close();
			}
			if (stmt != null) {
				stmt.close();
			}

		}

		return list;
	}

	
	@Override // 게시판 입력
	public int insertBoard(Connection conn, Board board) throws SQLException {

		System.out.println("BoardDao안에있는 insertBoard실행");

		int row = 0;

		String sql = "INSERT INTO board (member_id,board_title,board_content,create_date,board_read,board_nice) VALUES (?,?,?,NOW(),0,0)";

		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, board.getMemberId());
			stmt.setString(2, board.getTitle());
			stmt.setString(3, board.getBoardContent());

			row = stmt.executeUpdate();
		} finally {

			if (rset != null) { rset.close(); }
			if (stmt != null) { stmt.close(); }
		}

		System.out.println("BoardDao.insertBoard 안에 있는 row : " + row);

		return row;
	}

	@Override // 조회수 올리기 -- 해야함
	public int updateReadCount(Connection conn, int BoardNo) throws SQLException {

		System.out.println("BoardDao안에있는 updateReadCount실행");

		int row = 0;

		String sql = "UPDATE board SET board_read = board_read + 1 WHERE board_no = ?";
		// UPDATE board SET board_read = board_read + 1 WHERE board_no = 1;
		// +1을 주면 board_no으로 볼때 1씩 증가해줌

		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, BoardNo); // int BoardNo값이 들어옴

			row = stmt.executeUpdate();

		} finally {

			if (stmt != null) { stmt.close(); }
		}
		return row;
	}

	@Override // 좋아요 올리기
	public int updateNice(Connection conn, int BoardNo) throws SQLException {

		System.out.println("BoardDao안에있는 updateNice실행");

		int row = 0;

		String sql = "UPDATE board SET board_nice = board_nice + 1 WHERE board_no = ?"; // 좋아요 nice 1씩 올리기

		PreparedStatement stmt = null;

		try {

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, BoardNo);

			row = stmt.executeUpdate();
			
		} finally {
			if (stmt != null) { stmt.close(); }
		}
		
		return row;

	}

}
