package kr.or.ddit.servlet02;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/02/MediaForm.do")
public class Model2MediaFormServlet extends HttpServlet {
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		application = config.getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String folder = "d:/contents";
		String isMac = System.getProperty("os.name").substring(0, 3).toLowerCase();
		if("mac".equals(isMac)) folder = System.getProperty("user.home")+"/Documents/GitHub/jspClass/contents";
		
		File contents = new File(folder);
		String[] children = contents.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				String mime = application.getMimeType(name);
				return mime!=null && ( mime.startsWith("image/") || mime.startsWith("video/") );
			}
		});
		
		req.setAttribute("children", children);
		String view = "/WEB-INF/views/02/mediaForm.jsp";
		req.getRequestDispatcher(view).forward(req,resp);
		
	}
}
