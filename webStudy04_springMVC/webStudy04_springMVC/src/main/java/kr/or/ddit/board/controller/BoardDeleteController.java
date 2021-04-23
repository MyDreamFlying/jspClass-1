package kr.or.ddit.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BoardVO;

@Controller
public class BoardDeleteController {
	private IBoardService service = BoardServiceImpl.getInstance();
	
	@RequestMapping(value="/board/boardDelete.do", method = RequestMethod.POST)
	public String boardDelete(
			@ModelAttribute("board") BoardVO board
			,HttpSession session
			) {
		String view = "redirect:/board/boardView.do?what="+board.getBo_no();
		String message = null;
		
		// 공지글이 아닐 경우에는 비밀번호 검증을 한다.
		if(!"NOTICE".equals(board.getBo_type()) && !service.boardAuthenticate(board)) {
			message = "비밀번호가 일치하지 않음.";
		}else {
			ServiceResult result = service.removeBoard(board);
			if(ServiceResult.OK.equals(result)) {
				view = "redirect:/board/boardList.do";
			}else {
				message = "서버 오류";
			}
		}
		
		session.setAttribute("message", message);
		
		return view;
	}
	
}
