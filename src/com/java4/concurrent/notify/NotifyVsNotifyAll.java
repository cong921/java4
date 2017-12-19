package com.java4.concurrent.notify;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Blocker{
	synchronized void waitingCall(){
		try{
			while(!Thread.interrupted()){
				wait();
				System.out.println(Thread.currentThread()+" ");
			}
		}catch(InterruptedException e){
			//OK to exit this way
		}
	}
	synchronized void prod(){notify();}
	synchronized void prodAll(){notifyAll();}
}
class Task implements Runnable{
	static Blocker blocker=new Blocker();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		blocker.waitingCall();
	}
	
}
class Task2 implements Runnable{
static Blocker blocker=new Blocker();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		blocker.waitingCall();
	}
	
}
public class NotifyVsNotifyAll {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i=0;i<5;i++)
			exec.execute(new Task());
		exec.execute(new Task2());
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			boolean prod=true;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(prod){
					System.out.println("notify() ");
					Task.blocker.prod();
					prod=false;
				}else{
					System.out.println("notifyAll()");
					Task.blocker.prodAll();
					prod=true;
				}
			}
		}, 400, 400);//Run every 0.4 second
		TimeUnit.SECONDS.sleep(5);//Run for a while...
		timer.cancel();
		System.out.println("Timer cancaled");
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.println("Task2.blocker.prodAll()");
		Task2.blocker.prodAll();
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.println("Shutting down");
		exec.shutdownNow();
	}
}
