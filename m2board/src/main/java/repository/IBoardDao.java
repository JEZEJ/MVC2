package repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import vo.Board;

public interface IBoardDao {
	
	
	List<Board> selectBoardListByPage(Connection conn,int rowPerPage, int beginRow); // 페이징할거라서 rowPerPage, beginRow주기 
		
	int selectBoardCnt();	
	
	Board selectBoardList(Connection conn,Board board);
	Board selectBoardOne(Connection conn,Board board);

}
