package kr.or.ddit.listener;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.Constants;

/**
 * Application Lifecycle Listener implementation class CustomServletContextListner
 *
 */
public class CustomServletContextListner implements ServletContextListener {
	private static final Logger logger = LoggerFactory.getLogger(CustomServletContextListner.class);
	private ServletContext application;
    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	logger.info("{}에서 어플리케이션 초기화 이벤트 처리", getClass().getName());
    	application = sce.getServletContext();
    	application.setAttribute(Constants.SESSION_COUNT_ATTR_NAME, 0);
    	application.setAttribute(Constants.SESSION_CONNECT_LIST, new ArrayList<HttpSession>());
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	logger.info("{}에서 어플리케이션 소멸 이벤트 처리", getClass().getName());
    	application.setAttribute(Constants.SESSION_COUNT_ATTR_NAME, 0);
    }

	
}
