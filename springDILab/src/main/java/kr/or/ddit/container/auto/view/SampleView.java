package kr.or.ddit.container.auto.view;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import kr.or.ddit.container.auto.service.ISampleService;

@Component
public class SampleView {
	
	private ISampleService service;
	
	@Resource(name = "sampleService")
//	@Autowired
	@Required
	public void setService(ISampleService service) {
		this.service = service;
	}
	
	public void view() {
		System.out.println(service.readData("a001"));
	}
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				new ClassPathXmlApplicationContext("kr/or/ddit/container/conf/autoDI-context.xml");
		context.registerShutdownHook();
		
		SampleView sampleView = context.getBean(SampleView.class);
		
		sampleView.view();
		
		
	}
}
