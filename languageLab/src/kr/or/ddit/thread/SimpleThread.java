package kr.or.ddit.thread;

/**
 * @author shane
 * 1. 1부터 100까지의 숫자를 매 1초마다 한번씩 1씩 증가하면서 콘솔에 출력 
 * 2. 1번의 작업을 매 2초 마다 한번씩 반복.
 * 3. 최대 쓰레드의 갯수는 10개 미만으로 제한.
 */
public class SimpleThread implements Runnable {
	
	public static void main(String[] args) {
		
		Thread[] repeats = new Thread[10];
		
		for(int i=0; i<10; i++) {
			repeats[i] = new Thread(new SimpleThread());
		}
		
		int i = 0;
		
		while(true) {
			repeats[i++].start();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	

	
	@Override
	public void run() {
		for(int i=1; i<=100; i++) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
