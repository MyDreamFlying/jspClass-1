package kr.or.ddit.container.collection;

import java.util.Calendar;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CalendarContextTestView {
	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = 
				new ClassPathXmlApplicationContext("kr/or/ddit/container/conf/calendar-context.xml");
		
		context.registerShutdownHook();
		
		Calendar calendar = context.getBean(Calendar.class);
		System.out.printf("%tc\n",calendar);
		Thread.sleep(2000);
		calendar = context.getBean(Calendar.class);
		System.out.printf("%tc",calendar);
	}
}
