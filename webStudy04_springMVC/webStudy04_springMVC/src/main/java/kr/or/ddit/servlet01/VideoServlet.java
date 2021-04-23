package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 한글 주석

@WebServlet("/01/video.do")
public class VideoServlet extends HttpServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse resp) 
    throws IOException, ServletException{
    	String mediaFilename = req.getParameter("media");
    	if(mediaFilename == null || mediaFilename.isEmpty()) {
    		resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    		return;
    	}
    	
    	String folder = "d:/contents";
    	String isMac = System.getProperty("os.name").substring(0, 3).toLowerCase();
    	if("mac".equals(isMac)) {
    		folder = System.getProperty("user.home")+"/Documents/GitHub/jspClass/contents";
    	}
    	
    	File mediaFile = new File(folder,mediaFilename);
    	if(!mediaFile.exists()) {
    		resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    		return;
    	}
    	
    	String mime = getServletContext().getMimeType(mediaFilename);
    	if(mime==null || !mime.startsWith("video/")) {
    		resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
    		return;
    	}
    	
        resp.setContentType(mime);

        FileInputStream fis = new FileInputStream(mediaFile);

        
        OutputStream os = resp.getOutputStream();
        byte []buffer = new byte[4096];

        int pointer = -1;
        while((pointer = fis.read(buffer)) != -1){
            os.write(buffer, 0, pointer);
        }
    }

}

//javac ImageServlet.java -d ../classes -encoding utf-8