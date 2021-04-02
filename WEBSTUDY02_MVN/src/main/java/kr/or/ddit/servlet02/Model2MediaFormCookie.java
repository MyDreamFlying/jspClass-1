package kr.or.ddit.servlet02;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/02/MediaFormCookie.do")
public class Model2MediaFormCookie extends HttpServlet {
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		application = config.getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String data = req.getParameter("data");
		String encodedData = URLEncoder.encode(data,"utf-8");
		Cookie cookie = new Cookie("selecteds", encodedData);
		cookie.setPath("/");
		cookie.setMaxAge(3*24*60*60);
		resp.addCookie(cookie);
	}
}
