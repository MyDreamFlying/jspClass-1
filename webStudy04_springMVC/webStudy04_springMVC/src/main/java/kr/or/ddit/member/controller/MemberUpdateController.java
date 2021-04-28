package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

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
import kr.or.ddit.validator.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberUpdateController {
	@Inject
	private IMemberService service = new MemberServiceImpl();
	
	private void addCommandAttribute(Model model) {
		model.addAttribute("command", "update");
	}
	
	@RequestMapping("/member/memberUpdate.do")
	public String memberUpdateForm(
			HttpSession session
			,Model model) throws ServletException, IOException {
		
		addCommandAttribute(model);
		
		MemberVO authMember = (MemberVO)session.getAttribute("authMember");
		String authId = authMember.getMem_id();
		MemberVO member = service.retrieveMember(authId);
		model.addAttribute("member", member);
		return "member/memberForm";
	}

	@RequestMapping(value="/member/memberUpdate.do", method=RequestMethod.POST )
	public String memberUpdate(
			@RequestPart(value="mem_image", required=false) MultipartFile mem_image,
			@Validated(UpdateGroup.class) @ModelAttribute("member") MemberVO member
			,HttpSession session
			,Errors errors
			,Model model) throws IOException {
		
		addCommandAttribute(model);
		
		if(mem_image != null && !mem_image.isEmpty()) {
			String mime = mem_image.getContentType();
			if(!mime.startsWith("image/")) {
				throw new BadRequestException("이미지 이외의 프로필은 처리 불가.");
			}
			member.setMem_img(mem_image.getBytes());
		}
		
		// 1. 요청 접수
		MemberVO authMember = (MemberVO)session.getAttribute("authMember");
		String authId = authMember.getMem_id();
		
		member.setMem_id(authId);
		model.addAttribute("member", member);
		
		// 2. 검증
		boolean valid = !errors.hasErrors();
		
		String view = null;
		String message = null;
		
		if(valid) {
			ServiceResult result = service.modifyMember(member);
			switch(result) {
			case INVALIDPASSWORD:
				view = "member/memberForm";
				message = "비번 오류";
				break;
			case OK:
				view = "redirect:/mypage.do";
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
