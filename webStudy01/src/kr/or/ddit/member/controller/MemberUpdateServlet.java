package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/memberUpdate.do")
public class MemberUpdateServlet extends HttpServlet{
	
	private void addCommandAttribute(HttpServletRequest req) {
		req.setAttribute("command", "update");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addCommandAttribute(req);
		String view = "/WEB-INF/views/member/memberForm.jsp";
		req.getRequestDispatcher(view).forward(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addCommandAttribute(req);
		// 1. 요청 접수
		// 2. 검증
		// 3. 로직
		// 4. 모델 전달
		// 5. 뷰 선택
		// 6. 이동
	}
}
