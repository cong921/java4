package com.java4.concurrent.exchanger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.java4.concurrent.semaphore.Fat;
import com.java4.util.BasicGenerator;
import com.java4.util.Generator;




class ExchangerProduce<T> implements Runnable{
	private Generator<T> generator;
	private Exchanger<List<T>> exchanger;
	private List<T> holder;
	
	public ExchangerProduce( Exchanger<List<T>> exchanger,Generator<T> generator, List<T> holder) {
		super();
		this.generator = generator;
		this.exchanger = exchanger;
		this.holder = holder;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
class ExchangeCounsumer<T> implements Runnable{
	private Exchanger<List<T>> exchanger;
	private List<T> holder;
	private volatile T value;
	
	public ExchangeCounsumer(Exchanger<List<T>> exchanger, List<T> holder) {
		super();
		this.exchanger = exchanger;
		this.holder = holder;
	}

	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				holder=exchanger.exchange(holder);
				for (T t : holder) {
					value=t;//Fetch out value
					holder.remove(t);//OK for CopyOnWriteArrayList
				}
			}
		}catch (InterruptedException e) {
			// OK to terminate this way.
		}
		System.out.println("Final value: "+value);
	}
	
}
public class ExchangerDemo {
	static int size=10;
	static int delay=5;
	public static void main(String[] args) throws InterruptedException {
		if(args.length>0)
			size=new Integer(args[0]);
		if(args.length>1)
			delay=new Integer(args[1]);
		ExecutorService exec = Executors.newCachedThreadPool();
		Exchanger<List<Fat>> xc = new Exchanger<List<Fat>>();
		List<Fat> producerList=new CopyOnWriteArrayList<Fat>(),
				consumerList=new CopyOnWriteArrayList<Fat>();
		exec.execute(new ExchangerProduce<Fat>(xc,BasicGenerator.create(Fat.class),producerList));
		exec.execute(new ExchangeCounsumer<Fat>(xc, consumerList));
		TimeUnit.SECONDS.sleep(delay);
		exec.shutdownNow();
	}
}
