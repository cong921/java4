package com.java4.override;
import junit.framework.Assert;

public class Father {
	protected int methodO(){
		return 1;
	}
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		int a=2;
		Assert.assertTrue(a>1);
//		Assert.failSame("sdfasdf");
		System.out.println("sdf");
	}
}
