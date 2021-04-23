package kr.or.ddit.example.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import kr.or.ddit.example.service.IExampleService;

public class ExampleSpringView {
	
	public static void main(String[] args) {
		ApplicationContext container = new ClassPathXmlApplicationContext("kr/or/ddit/example/config/example-context.xml");
//		IExampleService service = (IExampleService)container.getBean("exampleServiceImpl");
//		IExampleService service = container.getBean(IExampleService.class);
		IExampleService service = container.getBean("exampleServiceImpl", IExampleService.class);
		String info = service.readData("a001");
		System.out.println(info);
	}
}
