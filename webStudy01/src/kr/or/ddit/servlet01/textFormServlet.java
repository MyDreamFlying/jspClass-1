package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 1. 텍스트 파일만 옵션으로 제공 할것
// 2. 텍스트 파일 체인지 이벤트로 , iframe에 그 내용이 출력 되도록

@WebServlet("/01/textViewer.tmpl")
public class textFormServlet extends AbstractUseTmplServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void setContentType(HttpServletResponse resp) {
		resp.setContentType("text/html; charset=utf-8");
	}

	@Override
	protected void makeData(HttpServletRequest req) {

		URL path = getClass().getResource("/datas");
		File contents = new File(path.getFile());

		ArrayList<String> children = getChildren(contents);

		StringBuffer options = new StringBuffer();
		for(String child : children){
			options.append(String.format("<option>%s</option>",child));
		}
		req.setAttribute("options",options);

	}

	private ArrayList<String>  getChildren(File contents) {
		ArrayList<String> list = new ArrayList<>();

		File[] children = contents.listFiles();
		
		for(File child : children) {
			if(child.isDirectory()) {
				list.addAll(getChildren(child));
			}else {
				String mime = application.getMimeType(child.getName());
				
				if(mime!=null && mime.startsWith("text/"))
					list.add(String.format("%s%s", getParent(child),child.getName()));
			}
		}
		return list;
	}
	
	private StringBuffer getParent(File file) {
		StringBuffer parent = new StringBuffer();
		if(!"datas".equals(file.getParentFile().getName())) {
			parent.insert(0,getParent(file.getParentFile()) + file.getParentFile().getName());
			parent.append("/");
		}
		return parent;
		
	}

}

