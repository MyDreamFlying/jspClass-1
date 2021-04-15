package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberViewController {
	IMemberService service = new MemberServiceImpl();
	
	@RequestMapping("/member/memberView.do")
	public String mypageCheckPassword(
			@RequestParam(value="who") String mem_id
			,HttpServletRequest req) throws ServletException, IOException {
		
		MemberVO detailMember = service.retrieveMember(mem_id);
		req.setAttribute("member", detailMember);
		return "member/memberView";
		
	}
	
}
