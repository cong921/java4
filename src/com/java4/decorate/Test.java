package com.java4.decorate;

public class Test {
	public static void main(String[] args) {
		ConcreateConponent c=new ConcreateConponent();
		ConcreateDecorate d=new ConcreateDecorate();
		d.setComponent(c);
		d.operation();
	}
	public int getInt(){
		return 1;
	}
}
