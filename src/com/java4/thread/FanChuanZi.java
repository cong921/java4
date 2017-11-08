package com.java4.thread;

import java.util.ArrayList;
import java.util.List;

public class FanChuanZi {
	
	public static void main(String[] args) {
		System.out.println(getStr("ccababac"));
		
	}
	public static String getStr(String s){
		
		List<String> list=new ArrayList<String>();
		for (int i = 1; i <s.length()/2+1; i++) {
			for (int j = 1; j < s.length(); j++) {
				if(j-i>=0&&j+i<s.length()&&s.substring(j-i,j+i+1).equals(getReverse(s.substring(j-i,j+i+1)))){
					list.add(s.substring(j-i,j+i+1));
				}
			}
		}
		System.out.println(list);
		String maxString=list.get(0);
		for (int i=0;i<list.size()-1;i++) {
			if(list.get(i+1).length()>list.get(i).length())
				maxString=list.get(i+1);
		}
		return maxString;
	}
	private static String getReverse(String s){
		StringBuilder sb=new StringBuilder();
		char[] charArray = s.toCharArray();
		for (int i=charArray.length-1;i>=0;i--) {
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}
	
}
