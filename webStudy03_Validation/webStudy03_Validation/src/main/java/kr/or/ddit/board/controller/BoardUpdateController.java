package kr.or.ddit.board.controller;

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
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BoardVO;

@Controller
public class BoardUpdateController {
	private IBoardService service = BoardServiceImpl.getInstance();
	
	@RequestMapping("/board/boarUpdate.do")
	public String updateForm(
			HttpServletRequest req
			,@RequestParam(value="bo_no") int bo_no) {
		BoardVO board = new BoardVO();
		board.setBo_no(bo_no);
		board = service.retrieveBoard(board);
		req.setAttribute("board",board);
		
		return "board/boardForm";
	}
	
	@RequestMapping(value="/board/boarUpdate.do", method = RequestMethod.POST)
	public String updateForm(
			HttpServletRequest req
			,@RequestPart("bo_files") MultipartFile[] bo_files
			,@ModelAttribute("board") BoardVO board) {
		Map<String, List<String>> errors = new LinkedHashMap<>();
		
		String view = "board/boardForm";
		String message = null;
		req.setAttribute("errors", errors);
		
		// 지울 첨부파일들 등록
		String[] attArr = req.getParameterValues("att_no");
		if(attArr != null) {
			final int SIZE = attArr.length;
			int[] deleteList = new int[SIZE];
			
			for(int i=0; i<SIZE; i++) {
				deleteList[i] = Integer.parseInt(attArr[i]); 
			}
			board.setDeleteAttachList(deleteList);
		}
		
		// 비밀번호 검증 먼저 하기
		if(!service.boardAuthenticate(board)) {
			message = "비밀번호가 일치하지 않음.";
		}else {
			
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
			
			ServiceResult result = service.modifyBoard(board);
			
			if(ServiceResult.OK.equals(result)) {
				view = "redirect:/board/boardView.do?what="+board.getBo_no();
			}else {
				message = "서버 오류";
			}
		}
		
		req.setAttribute("message", message);
		return view;
	}
	
}









