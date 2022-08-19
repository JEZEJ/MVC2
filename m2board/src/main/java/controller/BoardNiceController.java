package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import service.IBoardService;

@WebServlet("/boardNice")
public class BoardNiceController extends HttpServlet {
       
	private IBoardService boardService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boardService = new BoardService();
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int nice = Integer.parseInt(request.getParameter("boardNice"));
		
		//디버깅 값 확인해주기
		System.out.println(boardNo);
		System.out.println(nice);
		
		if(boardService.modifyNice(boardNo) == 0) {
			response.sendRedirect(request.getContextPath() + "/boardOne?boardNo="+boardNo);
		} else {
			response.sendRedirect(request.getContextPath() + "/boardList");
		}
		
	}

}
