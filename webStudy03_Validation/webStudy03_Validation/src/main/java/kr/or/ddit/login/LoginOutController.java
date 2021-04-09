package kr.or.ddit.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.vo.MemberVO;

@Controller
public class LoginOutController{
	private IAuthenticateService service = new AuthenticateServiceImpl();
	private static final Logger logger = LoggerFactory.getLogger(LoginOutController.class);

	@RequestMapping(value="/login/loginCheck.do", method=RequestMethod.POST)
	public String loginCheck(
			HttpServletRequest req
			, HttpServletResponse resp
			, HttpSession session) throws ServletException, IOException {
		if(session.isNew()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
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
		boolean valid = validate(mem_id, mem_pass);
		String message = null;
		MemberVO member = new MemberVO(mem_id, mem_pass);
		
		if(logger.isDebugEnabled())
			logger.debug("인증 전 MemberVO : {}",member);
		
		if(valid) {
		// 인증 (id == password)
			ServiceResult result = service.authenticate(member);
			if(logger.isInfoEnabled())
				logger.info("인증 후 MemberVO : {}",member);
			switch(result) {
			case OK:
				view = "redirect:/";
				session.setAttribute("authMember", member);
				session.setAttribute("message", "");
				break;
			case NOTEXIST:
				view = "redirect:/login/loginForm.jsp";
				message = "아이디 오류";
				break;
			case INVALIDPASSWORD:
				// 인증 실패시 loginForm.jsp로 이동
				view = "redirect:/login/loginForm.jsp";
				// 2) 인증 실패
				message = "비번 오류";
				break;
			}
			
		}else {
			// 1)검증
			view = "redirect:/login/loginForm.jsp";
			message = "아이디나 비번 누락";
			req.getSession().setAttribute("message", message);
		}
		
		session.setAttribute("message", message);
		
		return view;
		
	}
	
	@RequestMapping(value="/login/logout.do", method = RequestMethod.POST)
	public String logout(
			HttpSession session
			, HttpServletResponse resp) throws ServletException, IOException {
		
		if(session.isNew()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		session.invalidate();
		return "redirect:/";
	}


	private boolean validate(String mem_id, String mem_pass) {
		boolean valid = true;
		valid = !(mem_id == null || mem_id.isEmpty() || mem_pass==null || mem_pass.isEmpty());
		return valid;
	}
}
