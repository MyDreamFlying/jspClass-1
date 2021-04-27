package kr.or.ddit.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.BadRequestException;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
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
			@Validated(InsertGroup.class) @ModelAttribute("member") MemberVO member
			,Errors errors
			,Model model
		) throws IOException{
		
//		Locale.setDefault(Locale.KOREAN);
		
		if(mem_image != null && !mem_image.isEmpty()) {
			String mime = mem_image.getContentType();
			if(!mime.startsWith("image/")) {
				throw new BadRequestException("이미지 이외의 프로필은 처리 불가.");
			}
			member.setMem_img(mem_image.getBytes());
		}
		
		// 검증
		
		boolean valid = !errors.hasErrors();
		
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
		
		model.addAttribute("message", message);
		return view;
		
	}
	
}
