package kr.or.ddit.board.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class BoardReadController {
	private IBoardService service = BoardServiceImpl.getInstance();
	
	@RequestMapping("/board/boardList.do")
	public String selectBoardList(
			@ModelAttribute(value = "SearchVO") SearchVO searchVO,
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			HttpServletRequest req
			) {
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		System.out.println("start : " + startDate);
		System.out.println("END : " + endDate);
		
		PagingVO<BoardVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleSearch(searchVO);
		
		int totalRecord = service.retrieveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<BoardVO> boardList = service.retrieveBoardList(pagingVO);
		pagingVO.setDataList(boardList);
		
		req.setAttribute("pagingVO", pagingVO);
		return "board/boardList";
	}
}
