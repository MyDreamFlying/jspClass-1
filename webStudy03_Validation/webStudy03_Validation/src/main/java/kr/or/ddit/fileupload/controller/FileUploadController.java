package kr.or.ddit.fileupload.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.filter.wrapper.MultipartFile;

@Controller
public class FileUploadController {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@RequestMapping("/fileUpload.do")
	public String form() {
		return "fileupload/uploadForm";
	}
	
	@RequestMapping(value="/fileUpload.do", method = RequestMethod.POST)
	public String upload(
			@RequestParam("uploader") String uploader,
			@RequestPart (value="uploadFile1") MultipartFile[] file1,
			@RequestPart (value="uploadFile2", required=false) MultipartFile[] file2,
			HttpSession session,
			HttpServletRequest req) throws IOException, ServletException {
		
		ServletContext application = req.getServletContext();
		String saveFolderUrl = "/prodImages";
		File saveFolder = new File(application.getRealPath(saveFolderUrl));
		
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
		
		if(file1.length >0) {
			file1[0].saveTo(saveFolder);
			String saveFileUrl = saveFolderUrl + "/" + file1[0].getUniqueSaveName();
			session.setAttribute("uploadFile1", saveFileUrl);
			logger.info("saveFile1 : {}", saveFileUrl);
		}
		if(file2 != null && file2.length > 0) {
			file2[0].saveTo(saveFolder);
			String saveFileUrl = saveFolderUrl + "/" + file2[0].getUniqueSaveName();
			session.setAttribute("uploadFile2", saveFileUrl);
			logger.info("saveFile2 : {}", saveFileUrl);
		}
		
//		if(req instanceof MultipartHttpServletRequest) {
//			MultipartHttpServletRequest wrapper = (MultipartHttpServletRequest) req;
//			Map<String, List<MultipartFile>> fileMap = wrapper.getFileMap();
//			for(Entry<String, List<MultipartFile>> entry : fileMap.entrySet()) {
//				String partName = entry.getKey();
//				List<MultipartFile> files = entry.getValue();
//				for(MultipartFile file : files) {
//					if(file.isEmpty()) continue;
//					file.saveTo(saveFolder);
//					String saveFileUrl = saveFolderUrl + "/" + file.getUniqueSaveName();
//					session.setAttribute(partName, saveFileUrl);
//					logger.info("saveFile1 : {}", saveFileUrl);
//				}
//			}
//		}
		
//		Collection<Part> parts =  req.getParts();
//		int index = 1;
//		for(Part tmp : parts) {
//			if(tmp.getContentType() == null) continue;
//			String saveFileName = processPart(tmp, saveFolder);
//			String saveFileUrl = saveFolderUrl + "/" + saveFileName;
//			session.setAttribute("uploadFile"+ index++, saveFileUrl);
//			logger.info("saveFile1 : {}", saveFileUrl);
//		}
		
		logger.info("uploader : {}", uploader);
		return "redirect:/fileUpload.do";
	}

	
}
