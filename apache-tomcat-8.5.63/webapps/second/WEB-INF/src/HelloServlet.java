package kr.or.ddit;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.Date;

// 한글 주석

public class HelloServlet extends HttpServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse resp) 
    throws IOException, ServletException{
        
        // MIME InternetMailExteision : mailType/subType
        // text/html, text/css, application/json ..

        resp.setContentType("text/html; charset=utf-8");

        String data = "<html>"
                    +   "<body>"
                    +       "<h4>서버시간 : "
                    +           new Date()+"</h4>"
                    +       "<h4 id='timerArea'></h4>"
                    +       "<script type='text/javascript'>var today = new Date();"
                    +           " document.getElementById('timerArea').innerHTML=today;</script>"
                    +   "</body>"
                    +"</html>";
        PrintWriter out = resp.getWriter();
        out.println(data);
    }

}

//javac HelloServlet.java -d ../classes -cp %catalina_home%\lib\servlet-api.jar