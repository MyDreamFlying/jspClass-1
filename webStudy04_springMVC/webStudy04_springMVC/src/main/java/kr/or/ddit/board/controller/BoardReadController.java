package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Controller
public class BoardReadController {
	public static final String BOARDAUTH = "board.authenticated";
	private IBoardService service = BoardServiceImpl.getInstance();
	
	@RequestMapping("/board/noticeList.do")
	public String noticeList(HttpServletRequest req
			,@RequestParam(value="searchType", required=false) String searchType
			,@RequestParam(value="searchWord", required=false) String searchWord
			,@RequestParam(value="page", required=false, defaultValue = "1") int currentPage
			,@RequestParam(value="startDate", required=false) String startDate
			,@RequestParam(value="endDate", required=false) String endDate
			) {
		searchType = "type";
		searchWord = "NOTICE";
		return selectBoardList(startDate, endDate, searchType, searchWord, currentPage, req);
	}

	@RequestMapping(value="/board/authenticate.do", method=RequestMethod.POST)
	public String boardAuth(
			HttpServletResponse resp
			,@RequestParam("bo_no") int bo_no
			,@RequestParam("bo_pass") String bo_pass
			,HttpSession session
			) throws IOException {
		
		BoardVO search = new BoardVO();
		search.setBo_no(bo_no);
		search.setBo_pass(bo_pass);
		String result = "fail";
		
		if(service.boardAuthenticate(search)) {
			session.setAttribute(BOARDAUTH, search);
			result = "success";
		}
		
		try(
			PrintWriter out = resp.getWriter();
		){
			out.write(result);
		}
		
		return null;
	}
	
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
		
		for(BoardVO tmp : boardList) {
			String source = tmp.getBo_content();
			if(source != null) {
				Document dom = Jsoup.parse(source);
				Elements imgs = dom.getElementsByTag("img");
				String thumbnail = req.getContextPath() + "/images/thumbnail-default.jpg";
				if(!imgs.isEmpty()) {
					Element img = imgs.get(0);
					thumbnail = img.attr("src");
				}
				
				tmp.setThumbnail(thumbnail);
			}
			
		}
		
		pagingVO.setDataList(boardList);
		
		req.setAttribute("pagingVO", pagingVO);
		return "board/boardList";
	}
	
	@RequestMapping("/board/boardView.do")
	public String viewForAjax(
			@RequestParam("what") int bo_no
			, HttpServletRequest req
			, HttpServletResponse resp
			, HttpSession session
		) throws IOException{
		String accept = req.getHeader("Accept");
		
		BoardVO search = new BoardVO();
		req.setAttribute("search", search);
		search.setBo_no(bo_no);
		BoardVO board = service.retrieveBoard(search);
		
		boolean valid = true;
		if("Y".equals(board.getBo_sec())) {
			BoardVO authenticated = 
					(BoardVO) session.getAttribute(BOARDAUTH);
			if(authenticated==null || authenticated.getBo_no() != bo_no) {
				valid = false;
			}
		}
		
		String view = null;
		if(valid) {
			if(accept.contains("plain")) {
				resp.setContentType("text/plain;charset=UTF-8");
				try(
						PrintWriter out = resp.getWriter();	
						){
					out.println(board.getBo_content());
				}
			}else {
				req.setAttribute("board", board);
				view = "board/boardView";
			}
		}else {
			view = "board/passwordForm";
		}// if(valid) end
		
		session.removeAttribute(BOARDAUTH);
		return view;
	}
	
}










