package kr.or.ddit.servlet01;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.*;

import java.awt.image.BufferedImage;

// 한글 주석

@WebServlet("/01/image.do")
public class ImageServlet extends HttpServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse resp) 
    throws IOException, ServletException{
    	String imageFilename = req.getParameter("image");
    	if(imageFilename == null || imageFilename.isEmpty()) {
    		resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    		return;
    	}
    	
    	String folder = "d:/contents";
    	String isMac = System.getProperty("os.name").substring(0, 3).toLowerCase();
    	if("mac".equals(isMac)) {
    		folder = System.getProperty("user.home")+"/Documents/GitHub/jspClass/contents";
    	}
    	
    	File imageFile = new File(folder,imageFilename);
    	if(!imageFile.exists()) {
    		resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    		return;
    	}
    	
    	String mime = getServletContext().getMimeType(imageFilename);
    	if(mime==null || !mime.startsWith("image/")) {
    		resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
    		return;
    	}
    	
        resp.setContentType(mime);

        FileInputStream fis = new FileInputStream(imageFile);

        
        OutputStream os = resp.getOutputStream();
        byte []buffer = new byte[1024];

        int pointer = -1;
        while((pointer = fis.read(buffer)) != -1){
            os.write(buffer, 0, pointer);
        }
    }

}

//javac ImageServlet.java -d ../classes -encoding utf-8