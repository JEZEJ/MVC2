package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.IMemberService;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	
	private IMemberService memberService;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(); //session값 사용할거라
		
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/login");
		
		
	}

}
