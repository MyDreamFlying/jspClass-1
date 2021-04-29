package kr.or.ddit.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PrintNumberTimerTask{
	private int number;

	@Scheduled(cron="0 0 3 ? * MON")
	public void pojoRun() {
		System.out.printf("%d - %s(of %d)\n", ++number, Thread.currentThread().getName(), Thread.activeCount());
	}

}
