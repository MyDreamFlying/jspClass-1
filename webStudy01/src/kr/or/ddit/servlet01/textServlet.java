package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/01/text.do")
public class TextServlet extends HttpServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse resp) 
    throws IOException, ServletException{
    	String textFilename = req.getParameter("textFile");
    	
		URL path = getClass().getResource("/datas");
		File folder = new File(path.getFile());
		
    	if(textFilename == null || textFilename.isEmpty()) {
    		resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    		return;
    	}
    	
    	File textFile = new File(folder,textFilename);
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

        FileInputStream fis = new FileInputStream(textFile);

        
        OutputStream os = resp.getOutputStream();
        byte []buffer = new byte[1024];

        int pointer = -1;
        while((pointer = fis.read(buffer)) != -1){
            os.write(buffer, 0, pointer);
        }
    }

}

