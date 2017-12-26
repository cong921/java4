package com.java4.concurrent.exercise28;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import com.java4.thread.LiftOff;
class LiftOffRunner implements Runnable{
	private BlockingQueue<LiftOff> rockets;
	public LiftOffRunner(BlockingQueue<LiftOff> rockets) {
		super();
		this.rockets = rockets;
	}
	public void add(LiftOff lo) {
		try {
			rockets.put(lo);
		}catch (InterruptedException e) {
			System.out.println("Interrupted during put()");
		}
	}
	@Override

	public void run() {
		try {
			while(!Thread.interrupted()) {
				LiftOff rocket = rockets.take();
				rocket.run();//Use this thread
			}
		} catch (InterruptedException e) {
			System.out.println("Waking from take()");
		}
		System.out.println("Exiting LiftOffRunner");
	}
	
}
public class TestBlockingQueues_28 implements Runnable{
	static void getkey() {
		try {
			//Compensate for Windows/Linux difference in the
			//lenght of the result produced by the Enter key;
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
	static void getkey(String message) {
		System.out.println(message);
		getkey();
	}
	static void test(String msg,BlockingQueue<LiftOff> queue) {
		System.out.println(msg);
		LiftOffRunner runner = new LiftOffRunner(queue);
		Thread t = new Thread(runner);
		t.start();
		for(int i=0;i<5;i++)
			runner.add(new LiftOff(5));
		getkey("Press 'Enter' ("+msg+")");
		t.interrupt();
		System.out.println("Finished "+msg+" test");
	}
	public static void main(String[] args) {
		test("LinkedBlockingQueue",new LinkedBlockingQueue<LiftOff>());//Unlimited size
		test("ArrayBlockingQueue",new ArrayBlockingQueue<LiftOff>(3));//Fixed size
		test("SynchronousQueue",new SynchronousQueue<LiftOff>());//Size of 1
	}
	@Override
	public void run() {
		
	}
	
}

