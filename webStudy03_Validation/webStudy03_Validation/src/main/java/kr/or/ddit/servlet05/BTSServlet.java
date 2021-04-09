package kr.or.ddit.servlet05;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/bts")
public class BTSServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	Map<String, String> BtsMap;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		BtsMap = new LinkedHashMap<>();
		BtsMap.put("bui", "뷔");
		BtsMap.put("jhop", "제이홉");
		BtsMap.put("jimin", "지민");
		BtsMap.put("jin", "진");
		BtsMap.put("jungkuk", "정국");
		BtsMap.put("rm", "알엠");
		BtsMap.put("suga", "슈가");
		config.getServletContext().setAttribute("BtsMap", BtsMap);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/WEB-INF/views/btsForm.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String member = req.getParameter("member");
		String view = "/WEB-INF/views/bts/"+member+".jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}
}
