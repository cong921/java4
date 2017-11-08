package com.java4.thread;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FibonacciThread implements Callable<Integer> {
	public  int fibonacci=0;
	public FibonacciThread(int fibonacci) {
		this.fibonacci = fibonacci;
	}
	private int f(int i){
		return i>2?f(i-1)+f(i-2):1;
	}
	@Override
	public Integer call() {
		int sum=0;
		for (int i = 1; i <=fibonacci; i++) {
			sum+=f(i);
		}
		return sum;
	}
	public static void main(String[] args) {
		ExecutorService executor=Executors.newCachedThreadPool();
		ArrayList<Future<Integer>> list=new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			System.out.println("i="+i);
			list.add(executor.submit(new FibonacciThread(i)));
		}
		for (Future<Integer> future : list) {
			try {
				System.out.println(future.get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
}
