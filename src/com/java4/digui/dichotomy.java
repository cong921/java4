package com.java4.digui;
/**
 * 二分查找法
 * @author Bob
 *
 */
public class dichotomy {
	public static void main(String[] args) {
		int[] arr={-33,-32,-1,0,2,2,3,4,5,6,7,8,9};
		System.out.println(getindexFromTo(2,0,arr.length-1,arr));;
	}
	/*
	 * 递归的写法
	 */
	private static int getindexFromTo(int i, int from, int to, int[] arr) {
		if(arr[from]>i||arr[to]<i)
			return -1;
		if(arr[from]==i)
			return from;
		if(arr[to]==i)
			return to;
		if(arr[(from+to)/2]==i)
			return (from+to)/2;
		if(arr[(from+to)/2]>i)
			return getindexFromTo(i, from, (from+to)/2, arr);
		if(arr[(from+to)/2]<i)
			return getindexFromTo(i, (from+to)/2, to, arr);
		return -1;
		
	}
	/*
	 * 一般的写法
	 */
	public static int getIndex(int i,int[] arr){
		if(arr[0]>i||arr[arr.length-1]<i)
			return -1;
		int len=arr.length-1;
		for (int index = 0; index < len; index++) {
			if(arr[index]==i)
				return index;
			if(arr[len]==i)
				return len;
			if(arr[(index+len)/2]==i)
				return (index+len)/2;
			if(arr[(index+len)/2]>i){
				len=(index+len)/2;
				continue;
			}else{
				index=(index+len)/2;
				continue;
			}
		}
		return -1;
	}
	
}
