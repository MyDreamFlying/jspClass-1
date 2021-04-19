package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.dao.AttachDAOImpl;
import kr.or.ddit.board.dao.IAttachDAO;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BoardVO;

@Controller
public class BoardInsertController {
	private IBoardService service = BoardServiceImpl.getInstance();
	private IAttachDAO attaachDao = AttachDAOImpl.getInstance();
	
	@RequestMapping("/board/boardInsert.do")
	public String form(
			@ModelAttribute("board") BoardVO board) {
		board.setBo_type("BOARD");
		return "board/boardForm";
	}
	
	@RequestMapping(value="/board/boardInsert.do", method = RequestMethod.POST)
	public String insert(
			@RequestPart("bo_files") MultipartFile[] bo_files,
			@ModelAttribute("board") BoardVO board) throws IOException {
		
		ServiceResult result = service.createBoard(board);
		int newBo_no = board.getBo_no();
		
		String saveFolderUrl ="/Users/shane/Documents/GitHub/jspClass/attaches";
		File saveFolder = new File(saveFolderUrl);
		List<AttachVO> attachList = new ArrayList<>();
		board.setAttachList(attachList);
		
		for(MultipartFile file : bo_files) {
			// 파일 시스템에 저장 및 BoardVO에 AttachVO들 등록
			if(file!=null && !file.isEmpty()) {
				file.saveTo(saveFolder);
				
				AttachVO attachFile = new AttachVO();
				attachFile.setBo_no(newBo_no);
				attachFile.setAtt_filename(file.getOriginalFilename());
				attachFile.setAtt_contenttype(file.getContentType());
				attachFile.setAtt_savename(file.getUniqueSaveName());
				attachFile.setAtt_size(file.getFileSize());
				attachList.add(attachFile);
			}
		}
		// DB에 등록하기
		attaachDao.insertAttaches(board);
		
		
		String view = null;
		switch(result) {
		case OK:
			view = "redirect:/board/boardRead.do?what="+newBo_no;
			break;
		default:
			view = "board/boardForm";
			break;
		}
		
		return view;
		
	}
	
}
