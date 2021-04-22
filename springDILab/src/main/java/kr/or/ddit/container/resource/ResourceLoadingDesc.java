package kr.or.ddit.container.resource;

import java.io.IOException;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

public class ResourceLoadingDesc {
	public static void main(String[] args) throws IOException {
		Resource cpr = new ClassPathResource("log4j2.xml");
		System.out.println(cpr.getFile().exists());
		Resource fsr = new FileSystemResource("d:/contents/텍스트파일.txt");
		fsr.exists();
		System.out.println(fsr.exists());
		UrlResource urlr = new UrlResource("https://blog.kakaocdn.net/dn/0mySg/btqCUccOGVk/nQ68nZiNKoIEGNJkooELF1/img.jpg");
		System.out.println(urlr.contentLength());
		
		ConfigurableApplicationContext container = new ClassPathXmlApplicationContext();
		
		cpr = container.getResource("classpath:log4j2.xml");
		System.out.println(cpr);
		
		fsr = container.getResource("file:///Users/shane/Documents/GitHub/jspClass/contents/오래된 노래_utf8.txt");
		System.out.println(fsr);
		
		urlr = (UrlResource) container.getResource("https://blog.kakaocdn.net/dn/0mySg/btqCUccOGVk/nQ68nZiNKoIEGNJkooELF1/img.jpg");
		System.out.println(urlr);
				
	}
}
