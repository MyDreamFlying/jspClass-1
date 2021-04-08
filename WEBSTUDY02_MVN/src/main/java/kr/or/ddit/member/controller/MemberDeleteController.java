package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberDeleteController{
	private IMemberService service = new MemberServiceImpl();
	
	@RequestMapping(value="/member/memberDelete.do", method=RequestMethod.POST)
	public String memberDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String password = req.getParameter("password");
		if(password==null || password.isEmpty()) {
			resp.sendError(400);
			return null;
		}
		
		HttpSession session = req.getSession();
		MemberVO authMember = (MemberVO)session.getAttribute("authMember");
		String authId = authMember.getMem_id();
		
		// 삭제 서비스 호출 
		ServiceResult result = service.removeMember(new MemberVO(authId,password));
		
		String view = null;
		switch(result) {
		case INVALIDPASSWORD:
			view = "redirect:/mypage.do";
			session.setAttribute("message", "비번 오류");
			break;
		case OK:
			session.invalidate();
			view = "redirect:/";
			break;
		default:
			view = "redirect:/mypage.do";
			session.setAttribute("message", "서버 오류");
			break;
		}
		return view;
		
	}
}
