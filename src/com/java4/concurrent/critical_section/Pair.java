package com.java4.concurrent.critical_section;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Pair {
	private int x,y;
	public Pair(int x,int y){
		this.x=x;
		this.y=y;
	}
	public Pair(){
		this(0,0);
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void incrementX(){
		x++;
	}
	public void incrementY(){
		y++;
	}
	@Override
	public String toString() {
		return "Pair [x=" + x + ", y=" + y + "]";
	}
	public class PairValuesNotEqualException extends RuntimeException {

		public PairValuesNotEqualException() {
			super("Pair values not equal: "+Pair.this);
			// TODO Auto-generated constructor stub
		}

		

	}
	public void checkState(){
		if(x!=y){
			throw new PairValuesNotEqualException();
		}
	}
	
}

abstract class PairManage{
	AtomicInteger checkCounter=new AtomicInteger(0);
	protected Pair p=new Pair();
	private List<Pair> storage=Collections.synchronizedList(new ArrayList<Pair>());
	public synchronized Pair getPair(){
		return new Pair(p.getX(), p.getY());
	}
	protected void store(Pair p){
		storage.add(p);
		try {
			TimeUnit.MILLISECONDS.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public abstract void increment();
}
class PairManage1 extends PairManage{
	public synchronized void increment(){
		p.incrementX();
		p.incrementY();
		store(getPair());
	}
}
class PairManager2 extends PairManage{
	public void increment(){
		Pair temp = null;
		synchronized (this) {
			p.incrementX();
			p.incrementY();
		}
		store(temp);
	}
}
class PairManipulator implements Runnable{
	private PairManage pm;

	public PairManipulator(PairManage pm) {
		super();
		this.pm = pm;
	}
	public void run(){
		while (true) {
			pm.increment();
			
		}
	}
	@Override
	public String toString() {
		return "Pair: "+pm.getPair()+" checkCounter= "+pm.checkCounter.get();
	}
}
class PairChecker implements Runnable{
	private PairManage pm;

	public PairChecker(PairManage pm) {
		super();
		this.pm = pm;
	}
	public void run(){
		while(true){
			pm.checkCounter.incrementAndGet();
			pm.getPair().checkState();
		}
	}
}