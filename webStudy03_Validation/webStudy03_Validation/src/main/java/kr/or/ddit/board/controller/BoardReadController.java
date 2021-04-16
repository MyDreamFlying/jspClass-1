package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Controller
public class BoardReadController {
	private IBoardService service = BoardServiceImpl.getInstance();
	
	@RequestMapping("/board/boardList.do")
	public String selectBoardList(
			@RequestParam(value="startDate", required=false) String startDate,
			@RequestParam(value="endDate", required=false) String endDate,
			@RequestParam(value="searchType", required=false) String searchType,
			@RequestParam(value="searchWord", required=false) String searchWord,
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			HttpServletRequest req
			){
		PagingVO<BoardVO> pagingVO = new PagingVO<>();
		
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("startDate", startDate);
		searchMap.put("endDate", endDate);
		searchMap.put("searchType", searchType);
		searchMap.put("searchWord", searchWord);
		
		pagingVO.setSearchMap(searchMap);
		pagingVO.setCurrentPage(currentPage);
		
		int totalRecord = service.retrieveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<BoardVO> boardList = service.retrieveBoardList(pagingVO);
		pagingVO.setDataList(boardList);
		
		req.setAttribute("pagingVO", pagingVO);
		return "board/boardList";
	}
	
	@RequestMapping("/board/boardView.do")
	public String viewForAjax(
			@RequestParam(value="what", required=false) String bo_num,
			HttpServletResponse resp
		) throws IOException{
		resp.setContentType("text/plain; charset=UTF-8");
		try(
			PrintWriter out = resp.getWriter();	
			){
			out.println("server side text"+bo_num);
		}
		return null;
	}
}
