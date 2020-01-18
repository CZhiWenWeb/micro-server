package com.czw.algorithms.第五章.字符串排序;

/**
 * @Author: czw
 * @CreateTime: 2019-12-26 14:35
 * @UpdeteTime: 2019-12-26 14:35
 * @Description:
 * 键索引计数法
 */
public class StringSort {
	public static void main(String[] args) {
		// 基数R=4,下面直接使用4代替基数
		int[] nums={2,3,4,1,2,4,3,1,2,2,1};
		int N=nums.length;
		int[] aux=new int[N];
		// 数组索引对应nums中的元素，数值记录频率，索引0不记录
		int[] count=new int[4+1];
	//	 计算出现频率，因为i的起始索引为count[i-1],在这里加1可以使放入
	//	辅助数组时直接适应count[nums[i]]
		for (int i=0;i<N;i++)
			count[nums[i]+1]++;
	//	将频率转换为索引
		for (int i=0;i<4;i++)
			count[i+1]+=count[i];
	//	将元素分类放入辅助数组
		for (int i=0;i<N;i++)
			aux[count[nums[i]]++]=nums[i];
	//	回写
		for (int i=0;i<N;i++)
			nums[i]=aux[i];
		for (int i:nums){
			System.out.println(i);
		}
	}
}
