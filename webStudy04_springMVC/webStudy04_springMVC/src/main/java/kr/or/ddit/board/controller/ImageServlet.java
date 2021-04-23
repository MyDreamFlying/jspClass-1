package kr.or.ddit.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ImageServlet{

	@RequestMapping("/board/image.do")
    public String imageProcess(
    		@RequestParam("fileName") String imageFilename
    		,HttpServletRequest req
    		,HttpServletResponse resp
    		) throws IOException{
		
    	if(imageFilename == null || imageFilename.isEmpty()) {
    		resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    		return null;
    	}
    	
    	String folder ="/Users/shane/Documents/GitHub/jspClass/attaches";
    	
    	File imageFile = new File(folder,imageFilename);
    	if(!imageFile.exists()) {
    		resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    		return null;
    	}
    	
        try(
        		FileInputStream fis = new FileInputStream(imageFile);
        		OutputStream os = resp.getOutputStream();
    	){
        	byte []buffer = new byte[4096];
        	
        	int pointer = -1;
        	while((pointer = fis.read(buffer)) != -1){
        		os.write(buffer, 0, pointer);
        	}
        }
        
        return null;
    }

}

