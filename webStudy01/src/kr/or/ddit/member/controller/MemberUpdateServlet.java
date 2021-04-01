package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberUpdate.do")
public class MemberUpdateServlet extends HttpServlet{
	private IMemberService service = new MemberServiceImpl();
	
	private void addCommandAttribute(HttpServletRequest req) {
		req.setAttribute("command", "update");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addCommandAttribute(req);
		HttpSession session = req.getSession();
		MemberVO authMember = (MemberVO)session.getAttribute("authMember");
		String authId = authMember.getMem_id();
		MemberVO member = service.retrieveMember(authId);
		req.setAttribute("member", member);
		
		String view = "/WEB-INF/views/member/memberForm.jsp";
		req.getRequestDispatcher(view).forward(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addCommandAttribute(req);
		req.setCharacterEncoding("UTF-8");
		
		// 1. 요청 접수
		MemberVO member = new MemberVO();
		HttpSession session = req.getSession();
		MemberVO authMember = (MemberVO)session.getAttribute("authMember");
		String authId = authMember.getMem_id();
		member.setMem_id(authId);
		req.setAttribute("member", member);
		
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}

		// 2. 검증
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("erros", errors);
		boolean valid = validate(member, errors);
		boolean redirect = false;
		String view = null;
		String message = null;
		
		if(valid) {
			ServiceResult result = service.modifyMember(member);
			switch(result) {
			case INVALIDPASSWORD:
				view = "/WEB-INF/views/member/memberForm.jsp";
				message = "비번 오류";
				break;
			case OK:
				redirect = true;
				view = "/mypage.do";
				break;
			default :
				view = "/WEB-INF/views/member/memberForm.jsp";
				message = "서버 오류, 잠시 후 다시 시도하세요.";
				break;
			}
		}else {	// 검증에 통과하지 못했을 경우 
			view = "/WEB-INF/views/member/memberForm.jsp";
		}
		
		req.setAttribute("message", message);
		
		if(redirect) {
			resp.sendRedirect(req.getContextPath()+view);
		}else {
			req.getRequestDispatcher(view).forward(req,resp);
		}
		
	}
	
	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
//		if(member.getMem_id() == null || member.getMem_id().isEmpty()) {
//			valid = false;
//			errors.put("mem_id", "아이디 누락");
//		}
		
		return valid;
		
	}
}
