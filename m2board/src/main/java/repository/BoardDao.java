package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Board;

public class BoardDao implements IBoardDao {

	@Override // 게시판 목록 보여주기
	public List<Board> selectBoardListByPage(Connection conn, int rowPerPage, int beginRow) throws SQLException {
	//board_no,member_id,board_title,board_content,create_date,board_read
		
		String sql = "SELECT board_no,member_id,board_title,board_content,create_date,board_read FROM board ORDER BY board_no DESC LIMIT ?,?";
		
		List<Board> list = new ArrayList<Board>();

		PreparedStatement stmt = null;
		Board board = new Board();
		ResultSet rset = null;

		try {
			
			stmt = conn.prepareStatement(sql); //쿼리실행
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

				list.add(board); // list에 값 넣어주기
			}
			
			System.out.println("BoardDao>>selectBoardListByPage>>list값 >> " + list);
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		} finally {

			if (rset != null) { rset.close(); }
			if (stmt != null) { stmt.close(); }
		}

		return list;
	}
	
	// 게시판 상세보기
	@Override
	public Board selectBoardOne(Connection conn, Board board) {

		System.out.println("BoardDao>>selectBoardOne실행");

		String sql = "SELECT board_no,board_title,board_writer,create_date,board_read,board_nice FROM board WHERE board_no=?";

		return null;
	}

	//count
	@Override
	public int selectBoardCnt(Connection conn,int rowPerPage) throws SQLException {
		
		String sql = "SELECT count(*) FROM board";
		int totalRow = 0;
		int lastPage = 0;
		
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		try {
			
			stmt = conn.prepareStatement(sql);
			rset = stmt.executeQuery();
			
			if(rset.next()) {
				totalRow = rset.getInt("count(*)");
			}
			lastPage = totalRow / rowPerPage;
			if(totalRow % rowPerPage != 0) {
				lastPage += 1;
			}
		}finally {
			if(rset!=null) {rset.close();}
			if(stmt!=null) {stmt.close();}
		}
		
		return lastPage;
	}


}
