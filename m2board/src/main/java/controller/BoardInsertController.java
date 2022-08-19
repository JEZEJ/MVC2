package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import service.IBoardService;
import vo.Board;


@WebServlet("/boardInsert")
public class BoardInsertController extends HttpServlet {
	
	private IBoardService boardService;

	//form
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/insertBoard.jsp").forward(request, response);
	
	}
	
	//action
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		boardService = new BoardService();
		Board board = new Board();
		
		String writer = request.getParameter("member_id");
		String title = request.getParameter("board_title");
		String content = request.getParameter("board_content");
		
		board.setMemberId(writer);
		board.setTitle(title);
		board.setBoardContent(content);
		
		if(boardService.insertBoard(board) == 0) {
			response.sendRedirect(request.getContextPath() + "/boardInsert");
		} else {
			response.sendRedirect(request.getContextPath() + "/boardList"); //boardList서블릿
		}		
		
		
		
	}

}
