package kr.or.ddit.servlet05;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/blood/getContent.do")
public class BloodContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String blood = (String) req.getParameter("blood");
		String view = String.format("/WEB-INF/views/blood/%s",blood+".jsp");
		
		req.getRequestDispatcher(view).forward(req, resp);
		
	}
}
