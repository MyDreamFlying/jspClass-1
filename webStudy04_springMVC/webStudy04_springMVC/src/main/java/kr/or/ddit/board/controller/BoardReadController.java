package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;


import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.exception.BadRequestException;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Controller
public class BoardReadController {
	public static final String BOARDAUTH = "board.authenticated";
	@Inject
	private IBoardService service;
	@Inject
	private WebApplicationContext container;
	private ServletContext application;
	@PostConstruct
	public void init() {
		application = container.getServletContext();
	}
	
	@RequestMapping("/board/noticeList.do")
	public String noticeList(
			@RequestParam Map<String, Object> searchMap
			,@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			,Model model
			) throws IOException {
		String searchType = "type";
		String searchWord = "NOTICE";
		searchMap.put(searchType, searchWord);
		return listForHTML(searchMap, currentPage, model);
	}
	
	@ResponseBody
	@RequestMapping(value="/board/noticeList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingVO<BoardVO> noticeListForAjax(
			@RequestParam Map<String, Object> searchMap
			,@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			) throws IOException {
		String searchType = "type";
		String searchWord = "NOTICE";
		searchMap.put(searchType, searchWord);
		return listForAjax(searchMap, currentPage);
	}

	@RequestMapping(value="/board/authenticate.do", method=RequestMethod.POST)
	public String boardAuth(
			HttpServletResponse resp
			,@RequestParam("bo_no") int bo_no
			,@RequestParam("bo_pass") String bo_pass
			,HttpSession session
			) throws IOException {
		
		BoardVO search = BoardVO.builder().bo_no(bo_no).bo_pass(bo_pass).build();
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
	
	@RequestMapping(value={"/board/boardList.do"})
	public String listForHTML(
			@RequestParam Map<String, Object> searchMap
			,@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			,Model model
		){
		PagingVO<BoardVO> pagingVO = listForAjax(searchMap, currentPage);
		model.addAttribute("pagingVO", pagingVO);
		return "board/boardList";
		
	}
	
	@RequestMapping(value="/board/boardList.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<BoardVO> listForAjax(
			@RequestParam Map<String, Object> searchMap
			,@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		){
		PagingVO<BoardVO> pagingVO = new PagingVO<>();
		
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
				String thumbnail = application.getContextPath() + "/images/thumbnail-default.jpg";
				if(!imgs.isEmpty()) {
					Element img = imgs.get(0);
					thumbnail = img.attr("src");
				}
				tmp.setThumbnail(thumbnail);
			}
		}
		
		pagingVO.setDataList(boardList);
		return pagingVO;
		
	}
	
	@RequestMapping("/board/boardView.do")
	public String view(
		 @RequestParam("what") int bo_no
		 ,@ModelAttribute BoardVO search
		 ,Model model
		 ,HttpSession session
		) {
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
			model.addAttribute("board", board);
			view = "board/boardView";
		
		}else {
			view = "board/passwordForm";
		}	
		return view;
		
	}
	
	@RequestMapping(value="/board/boardView.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String preview(
			@RequestParam("what") int bo_no
			, @ModelAttribute("search") BoardVO search
			, HttpSession session
		){
		
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
		if(valid) {
			session.removeAttribute(BOARDAUTH);
			return board.getBo_content();
		}else {
			throw new BadRequestException("비밀글은 미리보기 불가.");
		}
		
	}

	
}










