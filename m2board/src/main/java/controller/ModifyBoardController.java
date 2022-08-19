package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IBoardService;

@WebServlet("/modifyBoard")
public class ModifyBoardController extends HttpServlet {
	
	private IBoardService boardServie;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/modifyBoard.jsp").forward(request, response); 
		
		// 컨트롤러가 실행되면 폼으로 이동
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
