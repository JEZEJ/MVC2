package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IMemberService;
import service.MemberService;
import vo.Member;

@WebServlet("/addMember")
public class AddMemberController extends HttpServlet { // 회원가입 서블릿
	
	private IMemberService memberService;
	
	
	// form은 jsp파일 form이 보이게 경로를 설정해줌
	// addMember.jsp 회원가입 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
		request.getRequestDispatcher("/WEB-INF/view/addMember.jsp").forward(request, response); 
		// WEB-INF/view/ 안에있는 addMember.jsp 가 실행됨
		//.forward줘야지 뷰가 보임
		
	}
	
	// action
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8"); // 인코딩
		
		memberService = new MemberService();
		Member member = new Member();
		
		// 회원가입할때 들어갈 값
		
		String id = request.getParameter("member_id");
		String pw = request.getParameter("member_pw");
		String name = request.getParameter("member_name");
		int age = Integer.parseInt(request.getParameter("member_age"));
		String addr = request.getParameter("member_address");
		String detailAddr = request.getParameter("member_detailAddress");
		String phone = request.getParameter("member_phone");
		
		member.setMemberId(id);
		member.setMemberPw(pw);
		member.setMemberName(name);
		member.setMemberAge(age);
		member.setMemberAddress(addr);
		member.setMemberDetailAddress(detailAddr);
		member.setMemberPhone(phone);
		
		if(memberService.addMember(member) == 0) {
			response.sendRedirect(request.getContextPath() + "/login");
		} else {
			response.sendRedirect(request.getContextPath() + "/login"); 
		}	
		
	}
}
