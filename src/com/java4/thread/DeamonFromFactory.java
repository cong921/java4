package com.java4.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DeamonFromFactory implements Runnable {

	@Override
	public void run() {
		while(true) {
			try {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread()+" "+this);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Intercepted");
				e.printStackTrace();
			}
			
		}
	}
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec=Executors.newCachedThreadPool(new DeamonThreadFactory());
		for (int i = 0; i <10; i++) {
			exec.execute(new DeamonFromFactory());
		}
		System.out.println("All Deamon started");
		TimeUnit.MILLISECONDS.sleep(10500);
	}
	
}
