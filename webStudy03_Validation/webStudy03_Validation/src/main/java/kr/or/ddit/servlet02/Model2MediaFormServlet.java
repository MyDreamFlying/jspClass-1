package kr.or.ddit.servlet02;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.BadRequestException;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.filter.wrapper.MultipartFile;

@Controller
public class Model2MediaFormServlet {
	private ServletContext application;
	boolean isMac = "mac".equals(System.getProperty("os.name").substring(0, 3).toLowerCase());
//	String folder = isMac ? System.getProperty("user.home")+"/Documents/GitHub/jspClass/contents" : "d:/contents";
	
	@RequestMapping("/02/MediaForm.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		application = req.getServletContext();
		String folder = application.getInitParameter("contentFolder");
		
		File contents = new File(folder);
		String[] children = contents.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				String mime = application.getMimeType(name);
				return mime!=null && ( mime.startsWith("image/") || mime.startsWith("video/") );
			}
		});
		
		req.setAttribute("children", children);
		String view = "02/mediaForm";
		return view;
		
	}
	
	@RequestMapping(value="/02/MediaForm.do", method = RequestMethod.POST)
	public String doPost(
			@RequestPart("uploadMedia") MultipartFile media,
			HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		application = req.getServletContext();
		String folder = application.getInitParameter("contentFolder");
		
		if(!media.isEmpty()) {
			File contents = new File(folder);
			String contentType = application.getMimeType(media.getOriginalFilename());
			if(contentType == null || !contentType.startsWith("image/")) {
				throw new BadRequestException("image만 업로드 가능");
			}
			media.transferTo(new File(contents, media.getOriginalFilename()));
		}
		
		// Post-Redirect-Get Pattern
		return "redirect:/02/MediaForm.do";
	}
	
}
