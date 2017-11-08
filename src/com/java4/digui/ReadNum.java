package com.java4.digui;

public class ReadNum {
	public static void main(String[] args) {
		System.out.println(readNum(1));;
	}

	private static String readNum(int i) {
		String[] c={"零","一","二","三","四","五","六","七","八","九","十"};
		String s="";
		if(i<0||i>99)
			return null;
		if(i>=0&&i<=10)
			return c[i];
		if(i>10&&i<20)
			return c[9]+c[Integer.parseInt(String.valueOf(i).substring(1))];
		if(String.valueOf(i).endsWith("0"))
			return c[Integer.parseInt(String.valueOf(i).substring(0, 1))]+"十";
		if(!String.valueOf(i).endsWith("0"))
			return s=c[Integer.parseInt(String.valueOf(i).substring(0, 1))]+"十"+c[Integer.parseInt(String.valueOf(i).substring(1))];
		return null;
	}
}
