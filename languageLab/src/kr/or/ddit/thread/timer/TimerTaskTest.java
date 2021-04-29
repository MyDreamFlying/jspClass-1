package kr.or.ddit.thread.timer;

import java.util.Timer;

public class TimerTaskTest {
	public static void main(String[] args) {
		Timer timer = new Timer();
		PrintNumberTimerTask task = new PrintNumberTimerTask();
		timer.schedule(task, 0, 1000);
	}
}
