package com.java4.concurrent.pipe;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Sender implements Runnable{
	private Random Rand=new Random(47);
	private PipedWriter out=new PipedWriter();
	public PipedWriter getOut() {
		return out;
	}
	@Override
	public void run() {
		try {
			while(true)
				for(char c='A';c<='z';c++) {
					out.write(c);
					TimeUnit.MILLISECONDS.sleep(500);
				}
		} catch (IOException  e) {
			System.out.println(e+" Sender write  exception");
		}catch (InterruptedException e) {
			System.out.println(e+" Sender sleep interrupted");
		}
	}
	
}
class Receiver implements Runnable{
	private PipedReader in;
	
	public Receiver(Sender sender) throws IOException {
		in=new PipedReader(sender.getOut());
	}

	@Override
	public void run() {
		try {
			while(true) {
				//Blocks until characters are ther:
				System.out.println("Read: "+(char)in.read()+", ");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
public class PipedIO {
	public static void main(String[] args) throws IOException, InterruptedException {
		Sender sender =new Sender();
		Receiver receiver = new Receiver(sender);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(sender);
		exec.execute(receiver);
		TimeUnit.SECONDS.sleep(34);
		exec.shutdownNow();
	}
}
