package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import command.DBUtil;
import repository.BoardDao;

public class BoardService implements IBoardService {

	@Override
	public Map<String, Object> getBoardList(final int rowPerPage, int currentPage) {

		Map<String, Object> map = null;

		Connection conn = null;
		int beginRow = 0;
		beginRow = (currentPage - 1) * rowPerPage;

		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);
			
			BoardDao boardDao = new BoardDao(); // BoardDao 객체생성
			
			map = new HashMap<>();
			map.put("list", boardDao.selectBoardListByPage(conn, rowPerPage, beginRow));
			
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
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

}
