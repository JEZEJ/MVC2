package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import vo.Board;

public class BoardDao implements IBoardDao{
	

	@Override
	public List<Board> selectBoardListByPage(Connection conn, int rowPerPage, int beginRow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectBoardCnt() {
		// TODO Auto-generated method stub
		return 0;
	}

	// 게시판
	@Override
	public Board selectBoardList(Connection conn, Board board) {
		
		System.out.println("BoardDao>>selectBoardList실행");
		
		String sql = "SELECT board_no,board_title,board_writer,create_date,board_read,board_nice FROM board";
		
		return null;
	}

	// 게시판 상세보기
	@Override
	public Board selectBoardOne(Connection conn, Board board) {
		
		System.out.println("BoardDao>>selectBoardOne실행");
		
		String sql = "SELECT board_no,board_title,board_writer,create_date,board_read,board_nice FROM board WHERE board_no=?";
		
		
		return null;
	}
	
}
