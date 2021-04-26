package kr.or.ddit.board.view;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.vo.AttachVO;

public class DownloadView extends AbstractView {
	private static final Logger logger = LoggerFactory.getLogger(DownloadView.class);
	
	@Value("#{appInfo.attachPath}")
	private File saveFolder;
	
	@PostConstruct
	public void init() {
		logger.info("{}초기화, {}주입됨.", getClass().getSimpleName(), saveFolder.getAbsolutePath());
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		
		AttachVO attach = (AttachVO) model.get("attach");
		String saveFolderPath = "/Users/shane/Documents/GitHub/jspClass/attaches";
		File saveFolder = new File(saveFolderPath);
		
		String agent = req.getHeader("User-Agent");
		
		// 조회로 받아온 파일정보를 토대로 파일 받아오기
		File attachFile = new File(saveFolder, attach.getAtt_savename());
		
		// 사용자의 OS/ 브라우저 분석
		String filename = attach.getAtt_filename();
		if(StringUtils.containsIgnoreCase(agent, "trident")) {
			filename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", " ");
		}else {
			byte[] bytes = filename.getBytes();
			filename = new String(bytes, "ISO-8859-1");
		}
		
		// 해당 파일로 응답 스트림 생성 해서 응답데이터로 보내기
		resp.setHeader("Content-Disposition", "attachment; filename=\""+filename+"\"");
		resp.setContentType("application/octet-stream; charset=utf-8");
		resp.setContentLengthLong(attach.getAtt_size());
		try(
			OutputStream out = resp.getOutputStream();
		){
			FileUtils.copyFile(attachFile, out);
		}

	}

}
