package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.board.dao.IAttachDAO;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.AttachVO;

@Controller
public class BoardFileController {
	private static final Logger logger = LoggerFactory.getLogger(BoardFileController.class);
	
	@Inject
	private WebApplicationContext container;
	private ServletContext application;
//	@Inject
//	public void setContainer(WebApplicationContext container) {
//		this.container = container;
//		application = container.getServletContext();
//	}
	@Value("#{appInfo.boardImages}")
	private String saveFolderUrl;
	private File saveFolder;
	
	@PostConstruct
	public void init() {
		application = container.getServletContext();
		String saveFolderPath = application.getRealPath(saveFolderUrl);
		saveFolder = new File(saveFolderPath);
		logger.info("{}초기화, {}생성됨.", getClass().getSimpleName(), saveFolder.getAbsolutePath());
	}
	
	IAttachDAO attachDAO;
	IBoardService service;
	
	@RequestMapping(value="/board/download.do")
	public String download(
			@RequestParam("what") int what 
			,@RequestHeader(value = "User-Agent", required=true) String agent
			,Model model
			,HttpServletResponse resp
			) throws IOException {
		
		// 첨부파일 번호로 파일 정보 조회
		AttachVO attach = service.download(what);
		model.addAttribute("attach", attach);
		return "downloadView";
	}
	
	// 해당 boardImage는 첨부파일이 아닌 내부 에디터에서 사용하는 메서드
	@ResponseBody
	@RequestMapping(value="/board/boardImage.do"
				, method = RequestMethod.POST
				,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> imageUpload(
		@RequestPart("upload") MultipartFile upload
		) throws IOException{

		Map<String, Object> resultMap = new HashMap<>();
		if(!upload.isEmpty()) {
			String saveName = UUID.randomUUID().toString();
			upload.transferTo(new File(saveFolder, saveName));
			int uploaded = 1;
			String fileName = upload.getOriginalFilename();
			String url = application.getContextPath() + saveFolderUrl + "/" + saveName;
			resultMap.put("uploaded",uploaded);
			resultMap.put("fileName",fileName);
			resultMap.put("url",url);
		}
		
		return resultMap;
	}
	
	
}
