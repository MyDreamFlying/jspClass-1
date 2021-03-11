package kr.or.ddit;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DummyServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain; charset=utf-8");
		URL url = this.getClass().getResource("/kr/or/ddit/dummy.xml");
		resp.getWriter().println("안녕하십니까" + url);

	}
}
