package com.java4.thread.execise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Execise_21  {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Task1());
		exec.execute(new Task2());
		exec.shutdown();
	}

}

class Flag {
	public static volatile boolean FLAG = false;
}

class Task1 implements Runnable {

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Flag.FLAG = true;
	}

}

class Task2 implements Runnable {

	@Override
	public void run() {
		synchronized (this) {
			long currentTimeMillis = System.currentTimeMillis();
			if(Flag.FLAG) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("change FLAG to false");
			Flag.FLAG = false;
			/*while (true) {
				if (Flag.FLAG) {
					System.out.println("change FLAG to false");
					Flag.FLAG = false;
					break;
				}
			}	*/
			long currentTimeMillis1 = System.currentTimeMillis();
			System.out.println(currentTimeMillis1-currentTimeMillis);
		}
	}

}
