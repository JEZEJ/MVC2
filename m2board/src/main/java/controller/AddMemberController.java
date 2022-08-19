package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addMember")
public class AddMemberController extends HttpServlet { // 회원가입 서블릿
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
		request.getRequestDispatcher("/WEB-INF/view/addMember.jsp").forward(request, response); 
		// WEB-INF/view/ 안에있는 addMember.jsp 가 실행됨
		//.forward줘야지 뷰가 보임
		
	}


}
