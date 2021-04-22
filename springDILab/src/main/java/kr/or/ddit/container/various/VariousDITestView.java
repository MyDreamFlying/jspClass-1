package kr.or.ddit.container.various;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

public class VariousDITestView {
	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext container = new ClassPathXmlApplicationContext("classpath:kr/or/ddit/container/conf/variousDI-context.xml");
		VariousDIVO vo1 = container.getBean("vo1",VariousDIVO.class);
		
		Resource res = vo1.getFsr();
		URI uri = res.getURI();
		File file = new File(uri);
		
		FileReader rd = new FileReader(file);
		int i;
		while((i = rd.read()) != -1){
			System.out.print((char) i);
		}
		
	}
}
