package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IBoardService;


@WebServlet("/boardRead")
public class BoardReadController extends HttpServlet {
	
	private IBoardService boardService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int read = Integer.parseInt(request.getParameter("boardRead"));
		
		if(boardService.modifyRead(boardNo)==0) {
			response.sendRedirect(request.getContextPath() + "/boardOne?boardNo="+boardNo);
		} else {
			response.sendRedirect(request.getContextPath() + "/boardList");
		}
		}
	}

