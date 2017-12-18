package com.java4.thread;

import java.util.concurrent.ThreadFactory;

public class DeamonThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	}

}
