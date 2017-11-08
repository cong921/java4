package com.java4.decorate;

public class Child extends Test {
	private String s;

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public Child(String s) {
		super();
		this.s = s;
	}
	public Child() {
	}

	public static void main(String[] args) {
		new Child();
	}
}

