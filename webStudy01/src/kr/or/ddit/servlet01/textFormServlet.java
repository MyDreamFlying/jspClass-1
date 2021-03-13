package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 1. 텍스트 파일만 옵션으로 제공 할것
// 2. 텍스트 파일 체인지 이벤트로 , iframe에 그 내용이 출력 되도록

@WebServlet("/01/textViewer.tmpl")
public class textFormServlet extends AbstractUseTmplServlet{
	
	@Override
	protected void setContentType(HttpServletResponse resp) {
		resp.setContentType("text/html; charset=utf-8");
	}

	@Override
	protected void makeDate(HttpServletRequest req) {
		
		URL path = getClass().getResource("/datas");
		File contents = new File(path.getFile());
		
		String[] children = contents.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				
				String mime = application.getMimeType(name);
				return mime!=null && mime.startsWith("text/");
				
			}
		});

		StringBuffer options = new StringBuffer();
		for(String child : children){
			options.append(String.format("<option>%s</option>",child));
		}
		req.setAttribute("options",options);

	}
}

