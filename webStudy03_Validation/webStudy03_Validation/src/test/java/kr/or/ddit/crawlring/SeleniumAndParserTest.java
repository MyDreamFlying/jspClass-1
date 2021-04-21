package kr.or.ddit.crawlring;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumAndParserTest {

	@Test
	public void test() {
		System.setProperty("webdriver.chrome.driver","/opt/WebDriver/bin/chromedriver");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.naver.com");
		try {
			Thread.sleep(2000);
			// TODO Auto-generated catch block
		
			String source = driver.getPageSource();
			
			Document dom = Jsoup.parse(source);
			Element themecast = dom.getElementById("themecast");
			Elements elements = themecast.select(".title");
			Element title = elements.get(0);
			System.out.println(title);
			
			driver.close();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
