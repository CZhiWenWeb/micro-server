package com.czw.algorithms.第五章.字符串排序;

/**
 * @Author: czw
 * @CreateTime: 2019-12-26 15:51
 * @UpdeteTime: 2019-12-26 15:51
 * @Description:低位优先的字符串排序
 */
public class LSD {
	public static void main(String[] args) {
		String[] a={"41","32"};
		sort(a,a[0].length());
	}
	public static void sort(String[] a,int W){
	//	通过前W个字符将a[]排序
		int N=a.length;
		// EXTENDED_ASCII基数
		int R=256;
		String[] aux=new String[N];

		for (int d=W-1;d>=0;d--){
		//	根据第d个字符用键索引计数法排序
			int[] count=new int[R+1];
			//  计算出现频率
			for (int i=0;i<N;i++)
				count[a[i].charAt(d)+1]++;
			// 将频率转换为索引
			for (int i=0;i<R;i++)
				count[i+1]+=count[i];
		//	元素分类
			for (int i=0;i<N;i++)
				aux[count[a[i].charAt(d)]++]=a[i];
			for (int i=0;i<N;i++)
				a[i]=aux[i];


		}
	}
}
