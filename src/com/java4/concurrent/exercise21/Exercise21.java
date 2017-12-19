package com.java4.concurrent.exercise21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Exercise21 {
	public static void main(String[] args) {
		Runnable coop1 = new Coop1(), coop2 = new Coop2(coop1);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(coop1);
		exec.execute(coop2);
		Thread.yield();
		exec.shutdown();
	}
}

class Coop1 implements Runnable {
	public Coop1() {
		System.out.println("Constructed Coop1");
	}

	public void run() {
		System.out.println("Coop1 going into wait");
		synchronized (this) {
			try {
				wait();
			} catch (InterruptedException ignore) {
			}
		}
		System.out.println("Coop1 exited wait");
	}
}

class Coop2 implements Runnable {
	Runnable otherTask;

	public Coop2(Runnable otherTask) {
		this.otherTask = otherTask;
		System.out.println("Constructed Coop2");
	}

	public void run() {
		System.out.println("Coop2 pausing 5 seconds");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException ignore) {
		}
		System.out.println("Coop2 calling notifyAll");
		synchronized (otherTask) {
			otherTask.notifyAll();
		}
	}
}