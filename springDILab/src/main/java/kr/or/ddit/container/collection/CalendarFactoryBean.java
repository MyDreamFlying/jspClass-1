package kr.or.ddit.container.collection;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.AbstractFactoryBean;

public class CalendarFactoryBean extends AbstractFactoryBean<Calendar> {
	
	public CalendarFactoryBean() {
		setSingleton(false);
	}

	@Override
	public Class<?> getObjectType() {
		return Calendar.class;
	}

	@Override
	protected Calendar createInstance() throws Exception {
		return Calendar.getInstance();
	}
	
	

}
