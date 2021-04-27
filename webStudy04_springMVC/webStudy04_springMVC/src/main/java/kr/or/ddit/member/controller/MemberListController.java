package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class MemberListController {
	@Inject
	private IMemberService service;
	
	@RequestMapping("/member/memberList.do")
	public String memberList(
			@ModelAttribute(value = "SearchVO") SearchVO searchVO
			,@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			,HttpServletRequest req) throws ServletException, IOException {
		
		PagingVO<MemberVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleSearch(searchVO);
		
		int totalRecord = service.retrieveMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<MemberVO> memberList = service.retrieveMemberList(pagingVO);
		pagingVO.setDataList(memberList);
		
		req.setAttribute("pagingVO", pagingVO);
		return "member/memberList";
		
	}
	
	@RequestMapping("/member/memberView.do")
	public String memberView(
			@RequestParam(value="who") String mem_id
			,HttpServletRequest req){
		
		MemberVO detailMember = service.retrieveMember(mem_id);
		req.setAttribute("member", detailMember);
		return "member/mypage";
	}
}
