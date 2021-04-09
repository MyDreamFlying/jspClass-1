package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class MemberListController {
	private IMemberService service = new MemberServiceImpl();
	
	@RequestMapping("/member/memberList.do")
	public String memberList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			,@RequestParam(value="searchType", required=false, defaultValue="") String searchType
			,@RequestParam(value="searchWord", required=false, defaultValue="") String searchWord
			,HttpServletRequest req) throws ServletException, IOException {
		
		SearchVO searchVO = new SearchVO(searchType, searchWord);
		
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
}
