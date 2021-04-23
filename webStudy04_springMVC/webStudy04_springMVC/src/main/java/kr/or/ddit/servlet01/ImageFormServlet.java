package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 1. 텍스트 파일만 옵션으로 제공 할것
// 2. 텍스트 파일 체인지 이벤트로 , iframe에 그 내용이 출력 되도록

@WebServlet("/01/imageForm.tmpl")
public class ImageFormServlet extends AbstractUseTmplServlet{
	
	@Override
	protected void setContentType(HttpServletResponse resp) {
		resp.setContentType("text/html; charset=utf-8");
	}

	@Override
	protected void makeData(HttpServletRequest req) {

		String folder = "d:/contents";
		String isMac = System.getProperty("os.name").substring(0, 3).toLowerCase();
		if("mac".equals(isMac)) folder = System.getProperty("user.home")+"/Documents/GitHub/jspClass/contents";
		
		File contents = new File(folder);
		String[] children = contents.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				String mime = application.getMimeType(name);
				return mime!=null && mime.startsWith("image/");
			}
		});

		Date today = new Date();
		req.setAttribute("today",today);
		
		StringBuffer options = new StringBuffer();
		for(String child : children){
			options.append(String.format("<option>%s</option>",child));
		}
		req.setAttribute("options",options);

	}
}

