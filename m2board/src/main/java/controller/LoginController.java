package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IMemberService;


@WebServlet("/login")
public class LoginController extends HttpServlet {
	
	private IMemberService memberService;

	// form
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ID = request.getParameter("member_id");
		String PW = request.getParameter("member_pw");
		//뷰 포워딩
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	
	}

}
