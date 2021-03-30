package kr.or.ddit.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/login/loginCheck.do")
public class LoginCheckServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private IAuthenticateService service = new AuthenticateServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		if(session.isNew()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		String mem_id = req.getParameter("mem_id");
		String mem_pass = req.getParameter("mem_pass");
		String saveId = req.getParameter("saveId");	//아이디 저장하기 체크되면 saveId 미체크시 null을 받아온다.
		
		if("saveId".equals(saveId)) {	// 쿠키에 아이디 한달간 저장
			Cookie savedIdCookie = new Cookie("savedId",mem_id);
			savedIdCookie.setPath(String.format("%s/login",req.getContextPath()));
			savedIdCookie.setMaxAge(30*24*60*60);
			resp.addCookie(savedIdCookie);
		}else {	// 저장된 아이디 쿠키 삭제
			Cookie savedIdCookie = new Cookie("savedId",null);
			savedIdCookie.setPath(String.format("%s/login",req.getContextPath()));
			savedIdCookie.setMaxAge(0);
			resp.addCookie(savedIdCookie);
		}
		
		String view = null;
		boolean redirect = false;
		boolean valid = validate(mem_id, mem_pass);
		String message = null;
		MemberVO member = new MemberVO(mem_id, mem_pass);
		
		if(valid) {
		// 인증 (id == password)
			ServiceResult result = service.authenticate(member);
			
			switch(result) {
			case OK:
				redirect = true;
				view = "/";
				session.setAttribute("authMember", member);
				session.setAttribute("message", "");
				break;
			case NOTEXIST:
				redirect = true;
				view = "/login/loginForm.jsp";
				message = "아이디 오류";
				break;
			case INVALIDPASSWORD:
				redirect = true;
				// 인증 실패시 loginForm.jsp로 이동
				view = "/login/loginForm.jsp";
				// 2) 인증 실패
				message = "비번 오류";
				break;
			}
			
		}else {
			// 1)검증
			redirect = true;
			view = "/login/loginForm.jsp";
			message = "아이디나 비번 누락";
			req.getSession().setAttribute("message", message);
		}
		
		if(message != null) {
			session.setAttribute("message", message);
		}
		
		// view로 이동
		if(redirect) {
			resp.sendRedirect(req.getContextPath() + view);
		}else {
			req.setAttribute("message", message);
			req.getRequestDispatcher(view).forward(req,resp);
		}
		
	}


	private boolean validate(String mem_id, String mem_pass) {
		boolean valid = true;
		valid = !(mem_id == null || mem_id.isEmpty() || mem_pass==null || mem_pass.isEmpty());
		return valid;
	}
}
