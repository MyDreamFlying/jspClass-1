package kr.or.ddit.servlet05;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bts")
public class BTSServlet2 extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, List<String>> btsMap = new LinkedHashMap<>();
		btsMap.put("B001", Arrays.asList("뷔","/WEB-INF/views/bts/bui.jsp"));
		btsMap.put("B002", Arrays.asList("제이홉","/WEB-INF/views/bts/jhop.jsp"));
		btsMap.put("B003", Arrays.asList("지민","/WEB-INF/views/bts/jimin.jsp"));
		btsMap.put("B004", Arrays.asList("진","/WEB-INF/views/bts/jin.jsp"));
		btsMap.put("B005", Arrays.asList("정국","/WEB-INF/views/bts/jungkuk.jsp"));
		btsMap.put("B006", Arrays.asList("알엠","/WEB-INF/views/bts/rm.jsp"));
		btsMap.put("B007", Arrays.asList("슈가","/WEB-INF/views/bts/suga.jsp"));
		getServletContext().setAttribute("btsMap", btsMap);
		
		req.getSession().setAttribute("btsMap", btsMap);
		String view = "/WEB-INF/views/btsForm2.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String member = req.getParameter("member");
		if(member == null || member.isEmpty()) {
			resp.sendError(400);
			return;
		}
		Map<String, List<String>> btsMap = (Map) getServletContext().getAttribute("btsMap");
		List<String> data= btsMap.get(member);
		if(data == null) {
			resp.sendError(404);
			return;
		}
		
		String view = data.get(1);
		req.setAttribute("result", view);
		req.getRequestDispatcher(view).forward(req, resp);
	}
}
