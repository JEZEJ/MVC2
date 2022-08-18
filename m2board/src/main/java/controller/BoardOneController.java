package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import service.IBoardService;
import vo.Board;


@WebServlet("/boardOne")
public class BoardOneController extends HttpServlet {
	
	private IBoardService boardService; 
       	
	//폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		BoardService boardService = new BoardService(); // 일단 서비스 부르기
		List<Board> list = boardService.getBoardOne(boardNo);
		
		if(list != null) {
			request.setAttribute("list", list);
		}
	
		//뷰 포워딩
		request.getRequestDispatcher("/WEB-INF/view/boardOne.jsp").forward(request, response);
	}
	
}
