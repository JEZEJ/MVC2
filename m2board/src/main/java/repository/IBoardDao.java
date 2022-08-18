package repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import vo.Board;
import vo.Member;

public interface IBoardDao {
	
	// 게시판 목록 -> 페이징할거라서 rowPerPage, beginRow주기 
	List<Board> selectBoardListByPage(Connection conn,int rowPerPage, int beginRow) throws SQLException; 
		
	int selectBoardCnt(Connection conn,int rowPerPage) throws SQLException;	
	
	// 게시판 상세보기
	List<Board> selectBoardOne(Connection conn,int boardNo) throws SQLException;

	// 게시판 입력하기
	int insertBoard(Connection conn,Board board);
	
	
}
