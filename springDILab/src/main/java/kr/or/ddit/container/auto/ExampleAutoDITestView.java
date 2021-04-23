package kr.or.ddit.container.auto;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import kr.or.ddit.example.service.IExampleService;

@Component
public class ExampleAutoDITestView {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("kr/or/ddit/container/conf/exampleAuto-context.xml");
//		IExampleService service = (IExampleService)container.getBean("exampleServiceImpl");
//		IExampleService service = container.getBean(IExampleService.class);
		
		context.registerShutdownHook();
		IExampleService service1 = context.getBean(IExampleService.class);
		IExampleService service2 = context.getBean(IExampleService.class);
		
		System.out.println(service1 == service2);
		System.out.println(service1.readData("a001"));
		
		
		
	}
}
