package com.czw.algorithms.第五章.字符串排序;

/**
 * @Author: czw
 * @CreateTime: 2019-12-27 09:13
 * @UpdeteTime: 2019-12-27 09:13
 * @Description:高位优先的字符串排序
 */
public class MSD {
	// 基数
	private static int R = 256;
	// 小数组的切换阈值
	private static final int M = 1;
	// 辅助数组
	private static String[] aux;

	// 当返回-1时，说明指定位置d超过了字符串末尾
	private static int charAt(String s, int d) {
		if (d >= 0 && d < s.length())
			return s.charAt(d);
		else
			return -1;
	}

	private static void insertSort(String[] a, int lo, int hi, int d) {
		for (int i = lo; i <= hi; i++) {
			for (int j = i; j > lo && less(a[j], a[j - 1], d); j--) {
				exch(j,j-1,a);
			}
		}
	}

	private static boolean less(String v, String w, int d) {
		if (d >= 0 && d < Math.min(v.length(), w.length())) {
			return v.charAt(d) < w.charAt(d);
		} else {
			return v.length() < w.length();
		}
	}

	private static void exch(int v,int w,String[] a){
		String temp=a[v];
		a[v]=a[w];
		a[w]=temp;
	}

	public static void sort(String[] a) {
		int N = a.length;
		aux = new String[N];
		sort(a, 0, N - 1, 0);
	}

	private static void sort(String[] a, int lo, int hi, int d) {
		// 对小数组使用具有稳定性的插入排序
		if (hi <= lo + M) {
			insertSort(a, lo, hi, d);
			return;
		}
		int[] count = new int[R + 2];
		// 计算频率,0,1未使用,键索引计数法需要预留一位,count[charAt(a[i],d)]
		// 在指定位子d超过a[i]长度时返回-1,所以要预留一位得到非负int值
		for (int i = lo; i <= hi; i++)
			count[charAt(a[i], d) + 2]++;
		// 将频率转换为索引
		for (int i = 0; i < R + 1; i++)
			count[i + 1] += count[i];
		// 数据分类
		for (int i = lo; i <= hi; i++)
			// +1为了保证得到非负int值
			aux[count[charAt(a[i], d) + 1]++] = a[i];
		//	回写
		for (int i = lo; i <= hi; i++)
			a[i] = aux[i - lo];
		// 将每个分类的数组为a的子数组，将子数组进行递归排序,如果分类无数据
		// 或只有一个数据,则hi-lo<=0,无需排序,如果hi<=lo+M,则直接使用插入
		// 排序,因为插入排序具有稳定性,排序后不会破坏原有的排序,小数组使用
		// 插入排序由于键索引计数法
		for (int r = 0; r < R; r++) {
			sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
		}
	}

	public static void main(String[] args) {
		String x=asciiToString("0");

		String[] a = {"aaa", "sfwef", "sfsrq3r3", "adsfew"};
		sort(a);
	}
	public static String asciiToString(String value)
	{
		StringBuffer sbu = new StringBuffer();
		String[] chars = value.split(",");
		for (int i = 0; i < chars.length; i++) {
			sbu.append((char) Integer.parseInt(chars[i]));
		}
		return sbu.toString();
	}
}
