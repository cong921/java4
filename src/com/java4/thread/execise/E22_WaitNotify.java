package com.java4.thread.execise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class E22_WaitNotify {
	public static void main(String[] args) throws Exception {
		final Runnable r1 = new Runnable() {
			public void run() {
				for (;;) {
					try {
						TimeUnit.MILLISECONDS.sleep(1);
						synchronized (this) {
							notify();
						}
					} catch (InterruptedException e) {
						return;
					}
				}
			}
		};
		Runnable r2 = new Runnable() {
			public void run() {
				for (;;) {
					try {
						synchronized (r1) {
							r1.wait();
						}
					} catch (InterruptedException e) {
						return;
					}
					System.out.print("Cycled ");
				}
			}
		};
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(r1);
		exec.execute(r2);
		TimeUnit.SECONDS.sleep(1);
		exec.shutdownNow();
	}
}
