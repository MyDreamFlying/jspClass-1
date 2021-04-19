package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
import kr.or.ddit.utils.RegexUtils;
import kr.or.ddit.validator.CommonValidator;
import kr.or.ddit.validator.InsertGroup;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BoardVO;

@Controller
public class BoardInsertController {
	private IBoardService service = BoardServiceImpl.getInstance();
	private String[] filteringTokens = new String[] {"말미잘", "해삼"};
	
	@RequestMapping("/board/boardInsert.do")
	public String form(
			@ModelAttribute("board") BoardVO board) {
		board.setBo_type("BOARD");
		return "board/boardForm";
	}
	
	@RequestMapping(value="/board/boardInsert.do", method = RequestMethod.POST)
	public String insert(
			HttpServletRequest req,
			@RequestPart("bo_files") MultipartFile[] bo_files,
			@ModelAttribute("board") BoardVO board) throws IOException {

		if(bo_files != null) {
			List<AttachVO> attachList = new ArrayList<>();
			for(MultipartFile file : bo_files) {
				// BoardVO에 AttachVO들 등록
				if(!file.isEmpty()) {
					AttachVO attachFile = new AttachVO(file);
					attachList.add(attachFile);
				}
			}
			if(attachList.size() > 0)
				board.setAttachList(attachList);
		}
		
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		
		String message = null;
		String view = null;
		boolean valid = new CommonValidator<BoardVO>().validate(board, errors, InsertGroup.class);
		
		if(valid) {
			String replaceText = RegexUtils.filteringTokens(board.getBo_content(), '#', filteringTokens);
			board.setBo_content(replaceText);
			ServiceResult result = service.createBoard(board);
			if(ServiceResult.OK.equals(result)) {
				view = "redirect:/board/boardView.do?what="+board.getBo_no();
			}else {
				message = "서버 오류";
				view = "board/boardForm";
			}
		}else {
			view = "board/boardForm";
		}
		
		req.setAttribute("message", message);
		
		return view;
		
	}
	
}
