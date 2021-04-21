package kr.or.ddit.board.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.dao.AttachDAOImpl;
import kr.or.ddit.board.dao.IAttachDAO;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
import kr.or.ddit.vo.AttachVO;

@Controller
public class BoardFileController {
	IAttachDAO attachDAO = AttachDAOImpl.getInstance();
	String savedfolder ="/Users/shane/Documents/GitHub/jspClass/attaches";
	
	@RequestMapping(value="/board/download.do")
	public String download(
			@RequestParam("what") int what 
			,HttpServletResponse resp
			,HttpServletRequest req
			) throws IOException {
		
		// 첨부파일 번호로 파일 정보 조회
		AttachVO attach = attachDAO.selectAttaches(what);
		
		// 조회로 받아온 파일정보를 토대로 파일 받아오기
		File attachFile = new File(savedfolder, attach.getAtt_savename());
		
		// 해당 파일로 응답 스트림 생성 해서 응답데이터로 보내기
		String disposition = "attachment; filename=\""+attach.getAtt_filename()+"\"";
		String encedDisposition = new String(disposition.getBytes("UTF-8"), "ISO-8859-1");
		resp.setHeader("Content-Disposition", encedDisposition);
		resp.setContentType("application/octet-stream; charset=utf-8");
		resp.setContentLengthLong(attach.getAtt_size());
		try(
			FileInputStream fis = new FileInputStream(attachFile);
			OutputStream out = resp.getOutputStream();
		){
			byte []buffer = new byte[4096];
			int pointer = -1;
			while((pointer = fis.read(buffer)) != -1) {
				out.write(buffer, 0, pointer);
			}
		}
		
		return null;
	}
	
	// 해당 boardImage는 첨부파일이 아닌 내부 에디터에서 사용하는 메서드
	@RequestMapping(value="/board/boardImage.do", method = RequestMethod.POST)
	public String imageUpload(
		@RequestPart("upload") MultipartFile upload
		,HttpServletResponse resp
		,HttpServletRequest req
		) throws IOException {
		String saveFolderUrl = "/boardImages";
		String saveFolderPath = req.getServletContext().getRealPath(saveFolderUrl);
		File saveFolder = new File(saveFolderPath);
		Map<String, Object> resultMap = new HashMap<>();
		if(!upload.isEmpty()) {
			upload.saveTo(saveFolder);
			int uploaded = 1;
			String fileName = upload.getOriginalFilename();
			String url = req.getContextPath() + saveFolderUrl + "/" + upload.getUniqueSaveName();
			resultMap.put("uploaded",uploaded);
			resultMap.put("fileName",fileName);
			resultMap.put("url",url);
		}
		
		resp.setContentType("application/json; charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		try(
			PrintWriter out = resp.getWriter();
			){
			mapper.writeValue(out, resultMap);
		}
		
		return null;
	}
	
	
}
