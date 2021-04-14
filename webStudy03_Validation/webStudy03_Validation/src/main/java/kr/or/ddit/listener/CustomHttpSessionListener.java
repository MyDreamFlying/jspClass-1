package kr.or.ddit.listener;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.Constants;
import kr.or.ddit.vo.MemberVO;

@WebListener
public class CustomHttpSessionListener implements HttpSessionListener{
	private static final Logger logger = LoggerFactory.getLogger(CustomHttpSessionListener.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		logger.info("세션 생성 : {}", session.getId());
		ServletContext application = session.getServletContext();
		int sessionCount = (Integer) application.getAttribute(Constants.SESSION_COUNT_ATTR_NAME);
		application.setAttribute(Constants.SESSION_COUNT_ATTR_NAME, sessionCount + 1);
		ArrayList<HttpSession> conList = 
				(ArrayList<HttpSession>) application.getAttribute(Constants.SESSION_CONNECT_LIST);
		conList.add(session);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		logger.info("세션 소멸 : {}", session.getId());
		ServletContext application = session.getServletContext();
		ArrayList<HttpSession> conList = 
				(ArrayList<HttpSession>) application.getAttribute(Constants.SESSION_CONNECT_LIST);
		conList.remove(session);
	}
	
}
