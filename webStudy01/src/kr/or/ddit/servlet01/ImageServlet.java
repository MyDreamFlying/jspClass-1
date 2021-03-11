package kr.or.ddit.servlet01;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

// 한글 주석

public class ImageServlet extends HttpServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse resp) 
    throws IOException, ServletException{
    	String imageFilename = req.getParameter("image");
    	if(imageFilename == null || imageFilename.isEmpty()) {
    		resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    		return;
    	}
    	String folder = "d:/contents";
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