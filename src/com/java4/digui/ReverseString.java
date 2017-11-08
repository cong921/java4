package com.java4.digui;

public class ReverseString {
	public static String reverse(String s){
		if(s.length()==1)
			return s;
		return reverse(s.substring(1))+s.substring(0, 1);
	}
	public static void main(String[] args) {
		System.out.println(reverse("abcdefghijklmmnopjaslkdflkadslkfalkdsflkasd;lkf;lkasdflkaslkdflkasdflkj"));
	}
}
