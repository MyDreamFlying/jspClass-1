package kr.or.ddit.board.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BoardVO;

@Controller
public class BoardUpdateController {
	
	@Inject
	private IBoardService service;
	
	@RequestMapping("/board/boarUpdate.do")
	public String updateForm(
			Model model
			,@RequestParam(value="bo_no") int bo_no){
		BoardVO board = BoardVO.builder().bo_no(bo_no).build();
		board = service.retrieveBoard(board);
		model.addAttribute("board",board);
		
		return "board/boardForm";
	}
	
	@RequestMapping(value="/board/boarUpdate.do", method = RequestMethod.POST)
	public String update(
			Model model
			,HttpServletRequest req
			,@ModelAttribute("board") BoardVO board) {
		Map<String, List<String>> errors = new LinkedHashMap<>();
		
		String view = "board/boardForm";
		String message = null;
		model.addAttribute("errors", errors);
		
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
		
		// 공지가 아닐경우엔 비밀번호 검증 먼저 하기
		if("BOARD".equals(board.getBo_type()) &&!service.boardAuthenticate(board)) {
			message = "비밀번호가 일치하지 않음.";
		}else {
			ServiceResult result = service.modifyBoard(board);
			
			if(ServiceResult.OK.equals(result)) {
				view = "redirect:/board/boardView.do?what="+board.getBo_no();
			}else {
				message = "서버 오류";
			}
		}
		
		model.addAttribute("message", message);
		return view;
	}
	
}









