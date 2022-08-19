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

		// 뷰 포워딩 (뷰 있는곳으로 이동)
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);

	}

	// Action
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8"); // 인코딩해주기
		
		String id = request.getParameter("member_id");
		String pw = request.getParameter("member_pw");
		String name = request.getParameter("memberName");
		
		Member member = new Member();
		member.setMemberId(id);
		member.setMemberPw(pw);
		
		MemberService memberService = new MemberService();
		
		Member loginMember = memberService.loginMember(member);
		System.out.println("loginMember : "+loginMember);
		
		
		if(loginMember == null) {
			System.out.println("로그인실패");
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		} else {
			System.out.println("성공");
			// 로그인이 성공했을때 세션에 값 저장
			HttpSession session = request.getSession(); // 세션을 꺼내쓰고 싶으면 선언
			session.setAttribute("loginMember",member); // 이 안에 id pw두개가 들어가있음
		}
		
		response.sendRedirect(request.getContextPath()+"/index");
		
	}
}
