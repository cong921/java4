package com.java4.concurrent;

public class SerialNumberGenerator {
	private static volatile int serialNumber=0;
	public static int nextSerialNumber(){
		return serialNumber++;//not thread-safe
	}
}
