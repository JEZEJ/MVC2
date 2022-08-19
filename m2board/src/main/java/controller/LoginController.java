package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.IMemberService;
import service.MemberService;
import vo.Member;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	private IMemberService memberService;
	
	// 서블릿은 get은 form으로 post는 action으로 사용함

	// form
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session.getAttribute("loginMember") != null) { // null이 아니면 로그인이 되어있는 상태
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}

		// 뷰 포워딩
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);

	}

	// Action
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if (session.getAttribute("loginMember") != null) { // null이 아니면 로그인이 되어있는 상태
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}
		
		request.setCharacterEncoding("UTF-8"); // 인코딩해주기
		
		Member member = new Member();
		
		member.setMemberId(request.getParameter("member_id"));
		member.setMemberPw(request.getParameter("member_pw"));
		
		MemberService memberService = new MemberService();
		
		Member loginMember = memberService.loginMember(member);
		
		if(loginMember == null) {
			System.out.println("로그인실패");
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
		session.setAttribute("loginMember", loginMember);
		response.sendRedirect(request.getContextPath()+"/index");
		
	}
}
