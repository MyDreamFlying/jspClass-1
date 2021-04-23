package kr.or.ddit.container.collection;

import org.springframework.beans.factory.FactoryBean;

public class StringArrayFactoryBean implements FactoryBean<String[]> {

	@Override
	public String[] getObject() throws Exception {
		// TODO Auto-generated method stub
		return new String[] {"value1", "value2"};
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return String[].class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
