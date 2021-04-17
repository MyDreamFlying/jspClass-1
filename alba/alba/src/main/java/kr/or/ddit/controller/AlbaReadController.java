package kr.or.ddit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.service.AlbaService;
import kr.or.ddit.service.AlbaServiceImpl;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingVO;

@Controller
public class AlbaReadController {
	private AlbaService service = AlbaServiceImpl.getInstance();
	
	@RequestMapping("/albaList.do")
	public String albaList(
			@ModelAttribute("detailSearch") AlbaVO detailSearch
			,@RequestParam(value="page", required=false, defaultValue = "1") int currentPage
			,HttpServletRequest req
			) {
		
		PagingVO<AlbaVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		
		int totalRecord = service.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<AlbaVO> albaList = service.retrieveAlbaList(pagingVO);
		pagingVO.setDataList(albaList);
		
		req.setAttribute("pagingVO", pagingVO);
		return "alba/albaList";
		
	}
	
}
