package com.java4.thread;

import java.io.IOException;

public class ShutDown {
	public static void main(String[] args) throws IOException {
		Runtime.getRuntime().exec(System.getenv("windir")+"/system32/shutdown.exe -s -f");
	}
}
