package com.czw.algorithms.第五章.字符串排序;

/**
 * @Author: czw
 * @CreateTime: 2019-12-28 14:04
 * @UpdeteTime: 2019-12-28 14:04
 * @Description:三向字符串快速排序
 */
public class Quick3string {
	public static void sort(String[] a){
		sort(a,0,a.length-1,0);
	}

	private static void sort(String[] a,int lo,int hi,int d){
		if (hi<=lo)
			return;
		int lt=lo,gt=hi;
		int v=charAt(a[lo],d);
		int i=lo+1;
		// 类似三向快排
		while (i<=gt){
			int t=charAt(a[i],d);
			if (t<v)
				exch(a,lt++,i++);
			else if (t>v)
				exch(a,i,gt--);
			else
				i++;
		}
		sort(a,lo,lt-1,d);
		if (v>=0)
			sort(a,lt,gt,d+1);
		sort(a,gt+1,hi,d);
	}
	private static int charAt(String a,int d){
		return a.charAt(d);
	}
	private static void exch(String[] a,int i,int j){
		String temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
}
