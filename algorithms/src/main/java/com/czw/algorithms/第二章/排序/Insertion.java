package com.czw.algorithms.第二章.排序;

import com.czw.algorithms.util.Common;

/**
 * @Author: czw
 * @CreateTime: 2019-12-27 09:34
 * @UpdeteTime: 2019-12-27 09:34
 * @Description:插入排序，对于小数组效率和有序数组效率高
 */
public class Insertion {
	/**
	 *原理：当前索引的左边元素都是有序的，然后将右边的元素插入到已经有序的元素中
	 * @param a
	 */
	public static void sort(Comparable[] a){
		int N=a.length;
		// 当前索引从1开始，把{a[0]}当作左边有序数组，然后进行插入
		for (int i=1;i<N;i++){
			// 左边有序，所以a[j]>a[j-1]时结束循环
			for (int j=i;j>0&& Common.less(a[j],a[j-1]);j--)
				Common.exch(a,j,j-1);
		}
	}
}
