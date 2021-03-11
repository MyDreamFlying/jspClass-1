package kr.or.ddit.servlet01;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 과제 1. 이미지를 제외한 나머지는 리스트에 포함되지 않도록 변경하기
// 과제2. 개발환경 구축 완료 보고서

public class ImageFormServlet extends HttpServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse resp) 
    throws IOException, ServletException{
        String mime = "text/html; charset=utf-8";
        resp.setContentType(mime);

        String folder = "d:/contents";
       	String isMac = System.getProperty("os.name").substring(0, 3).toLowerCase();
    	if("mac".equals(isMac)) {
    		folder = System.getProperty("user.home")+"/Documents/GitHub/jspClass/images";
    	}
    	
        File contents = new File(folder);
        String[] children = contents.list();
        StringBuffer html = new StringBuffer("<html><body>");
        html.append("<form action='image.do'><select name='image'>");
        for(String child : children){
        	if(getServletContext().getMimeType(child)!=null && getServletContext().getMimeType(child).startsWith("image/"))
        		html.append(String.format("<option>%s</option>",child));
        }
        html.append("</select><input type='submit' value='전송'></form></body></html>");
        PrintWriter out = resp.getWriter();
        try {
        	out.println(html);
        }finally{
        	if(out!=null) out.close();
        }
    }

}

//javac ImageFormServlet.java -d ../classes -encoding utf-8