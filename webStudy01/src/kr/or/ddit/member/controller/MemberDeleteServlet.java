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

@WebServlet("/member/memberDelete.do")
public class MemberDeleteServlet extends HttpServlet{
	private IMemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		MemberVO authMember = (MemberVO)session.getAttribute("authMember");
		
		String authId = authMember.getMem_id();
		
		// 세션 이용해 회원 탈퇴 
		service.removeMember(authMember);
		
		// 회원 세션 제거
		
		session.invalidate();
		
		// 첫 화면으로 redirect 시키기
		String view = "../";
		resp.sendRedirect(view);
	}
	
}
