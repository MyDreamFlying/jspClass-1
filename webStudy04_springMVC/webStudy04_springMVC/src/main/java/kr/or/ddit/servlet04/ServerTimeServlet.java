package kr.or.ddit.servlet04;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/04/serverTime")
public class ServerTimeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TimeZone tz = TimeZone.getTimeZone("Asia/Pyongyang");
		String locParam = req.getParameter("loc");
		Locale selectedLocale = req.getLocale();
		if(locParam != null && !locParam.isEmpty()){
			selectedLocale = Locale.forLanguageTag(locParam);
		}
		
		String result = String.format(selectedLocale, "%tc", Calendar.getInstance(tz));
		resp.setContentType("text/html; charset=UTF-8");
		resp.getWriter().println(result);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
