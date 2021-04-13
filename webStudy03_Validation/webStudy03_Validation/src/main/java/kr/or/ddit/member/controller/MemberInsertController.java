package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.BadRequestException;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
import kr.or.ddit.validator.CommonValidator;
import kr.or.ddit.validator.InsertGroup;
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
			@RequestPart(value="mem_image", required=false) MultipartFile mem_image,
			@ModelAttribute("member") MemberVO member
			, HttpServletRequest req) throws ServletException, IOException {
		
//		Locale.setDefault(Locale.KOREAN);
		
		if(mem_image != null && !mem_image.isEmpty()) {
			String mime = mem_image.getContentType();
			if(!mime.startsWith("image/")) {
				throw new BadRequestException("이미지 이외의 프로필은 처리 불가.");
			}
			member.setMem_img(mem_image.getBytes());
		}
		
		// 검증
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		
		boolean valid = new CommonValidator<MemberVO>().validate(member, errors, InsertGroup.class);
		
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
	
}
