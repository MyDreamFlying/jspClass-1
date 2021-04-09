package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberInsertController{
	private IMemberService service = new MemberServiceImpl();
	
	@RequestMapping("/member/memberInsert.do")
	public String form() throws ServletException, IOException {
			return "member/memberForm";
	}
	
	@RequestMapping(value="/member/memberInsert.do", method=RequestMethod.POST)
	public String process(
			@ModelAttribute("member") MemberVO member
			, HttpServletRequest req) throws ServletException, IOException {
		
		// 검증
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("erros", errors);
		boolean valid = validate(member, errors);
		String view = null;
		String message = null;
		
		if(valid) {
			ServiceResult result = service.createMember(member);
			switch(result) {
			case PKDUPLICATED:
				view = "member/memberForm";
				message = "아이디 중복";
				break;
			case OK:
				view = "redirect:/login/loginForm.jsp";
				break;
			default :
				view = "member/memberForm";
				message = "서버 오류, 잠시 후 다시 시도하세요.";
				break;
			}
		}else {	// 검증에 통과하지 못했을 경우 
			view = "member/memberForm";
		}
		
		req.setAttribute("message", message);
		return view;
		
	}
	
	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		if(member.getMem_id() == null || member.getMem_id().isEmpty()) {
			valid = false;
			errors.put("mem_id", "아이디 누락");
		}
		
		return valid;
		
	}
}
