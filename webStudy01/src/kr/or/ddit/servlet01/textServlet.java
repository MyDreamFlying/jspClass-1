package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class textServlet extends HttpServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse resp) 
    throws IOException, ServletException{
    	String textFilename = req.getParameter("textFile");
    	
    	if(textFilename == null || textFilename.isEmpty()) {
    		resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    		return;
    	}
    	
    	File textFile = new File("",textFilename);
    	if(!textFile.exists()) {
    		resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    		return;
    	}
    	
    	String mime = getServletContext().getMimeType(textFilename);
    	if(mime==null || !mime.startsWith("text/")) {
    		resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
    		return;
    	}
    	
        resp.setContentType(mime);

        FileInputStream fis = new FileInputStream(textFilename);

        
        OutputStream os = resp.getOutputStream();
        byte []buffer = new byte[1024];

        int pointer = -1;
        while((pointer = fis.read(buffer)) != -1){
            os.write(buffer, 0, pointer);
        }
    }

}

