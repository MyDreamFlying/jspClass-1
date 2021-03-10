package kr.or.ddit;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.Date;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

// 한글 주석

public class HelloImg extends HttpServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse resp) 
    throws IOException, ServletException{
        
        // MIME InternetMailExteision : mailType/subType
        // text/html, text/css, application/json ..

        resp.setContentType("image/jpg; charset=utf-8");
        File img = new File("D:/contents/Koala.jpg");
        BufferedImage bImage = ImageIO.read(img);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos);
        byte[] data = bos.toByteArray();
        resp.getOutputStream().write(data);
    }

}

//javac HelloImg.java -d ../classes -cp %catalina_home%\lib\servlet-api.jar -encoding utf-8