package kr.or.ddit.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/login/loginCheck.do")
public class LoginCheckServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String loginForm = "loginForm.jsp";
		String index = req.getContextPath()+"/index.jsp";
		
		//	요청 분석
		String mem_id = req.getParameter("mem_id");
		String mem_pass = req.getParameter("mem_pass");
		
		//	인증 , view로 이동
		//	인증 성공시 index.jsp로 이동(현재 요청 정보 삭제)
		//	인증 실패시 loginForm.jsp로 이동
		//	1) 검증
		//	2) 인증 실패(비밀번호가 잘못되었다고 간주 -> 아이디 상태 유지)
		if(mem_id == null || mem_id.length()==0 || mem_pass == null || mem_pass.length()==0) {
			req.setAttribute("mem_id", mem_id);
			RequestDispatcher rd = req.getRequestDispatcher(loginForm);
			rd.forward(req, resp);
		}else if( mem_id.equals(mem_pass)) {
			resp.sendRedirect(index);
		}else {
			req.setAttribute("mem_id", mem_id);
			RequestDispatcher rd = req.getRequestDispatcher(loginForm);
			rd.forward(req, resp);
		}
		
	}
}
