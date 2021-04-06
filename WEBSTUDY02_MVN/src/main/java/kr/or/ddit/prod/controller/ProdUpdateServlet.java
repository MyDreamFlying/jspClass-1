package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/prod/prodUpdate.do")
public class ProdUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/WEB-INF/views/prod/prodForm.jsp";
		
		boolean redirect = view.startsWith("redirect:");
		if(redirect) {
			view = view.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + view);
		}else {
			req.getRequestDispatcher(view).forward(req,resp);		
		}	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
}
