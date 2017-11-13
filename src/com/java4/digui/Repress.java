package com.java4.digui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Repress {
	public static void main(String[] args) {
		byte[] arr={'a','b','b','b'};
		repress(arr);
	}

	private static void repress(byte[] arr) {
		List<byte[]> newList=new ArrayList<>();
		List<byte[]> list = Arrays.asList(arr);
		for(int i=0;i<list.size();i++){
			if(i+1<list.size()) return;
			if(list.get(i)==list.get(i+1)){
				if(list.get(i+1)==list.get(i+2)){
					
				}
				newList.add(list.get(i));
			}
		}
	}
}
