package kr.or.ddit.servlet03;

import java.io.IOException;
import java.text.NumberFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/03/factorial")
public class FactorialServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/factorialForm.jsp").forward(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		double number = Double.parseDouble(req.getParameter("single"));
		double factorial = 1;
		for(int i=2; i<=number; i++) {
			factorial *= i;
		}
		String result = NumberFormat.getInstance().format(factorial);
		req.setAttribute("result", result);
		req.setAttribute("number", number);
		req.getRequestDispatcher("/WEB-INF/views/factorialForm.jsp").forward(req,resp);
	}
	
}
