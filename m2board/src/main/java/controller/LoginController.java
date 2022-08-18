package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.IMemberService;
import vo.Member;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private IMemberService memberService;
       
	//로그인폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	HttpSession session = request.getSession();
	if(session.getAttribute("loginMember") != null) { // 로그인 되어 있는 상태 
		response.sendRedirect(request.getContextPath() + "/index");
		return;
	}
	RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		
	}

	//로그인액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) { // 로그인 되어 있는 상태 
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		Member paramMember = new Member();
		
		//new
		Member member = memberService.getMemberByLogin(paramMember);
		if(member == null) { // 로그인실패
			
			System.out.println("로그인 실패");
			response.sendRedirect(request.getContextPath() + "/loginController");
			return; //함수를 끝내야하니깐 return주기
					
		}
		
	}

}
