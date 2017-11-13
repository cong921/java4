package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Thread1 implements Runnable {

	@Override
	public void run() {
		System.out.println(Thread.currentThread());
		try {
			long time=(long) (Math.random()*9000+1000);
			System.out.println(time);
			TimeUnit.MILLISECONDS.sleep(time);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			newCachedThreadPool.execute(new Thread1());
			
		}
		newCachedThreadPool.shutdown();
	}
}
