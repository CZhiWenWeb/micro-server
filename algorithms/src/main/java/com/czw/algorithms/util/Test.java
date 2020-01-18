package com.czw.algorithms.util;


import java.util.HashMap;
import java.util.Map;

/**
 * @Author: czw
 * @CreateTime: 2019-11-19 17:18
 * @UpdeteTime: 2019-11-19 17:18
 * @Description:
 */
public class Test{
	private int[] arr=new int[2];

	public static void main(String[] args) {
		int[] a={3,2,4};
		int target=6;
		Test t=new Test();
		t.twoSum(a,target);
	}
	public  int[] twoSum(int[] nums, int target) {
		sort(nums);
		find(nums,0,nums.length-1,target);
		return arr;
	}
	public void find(int[] nums,int lo,int hi,int target){
		if(hi<=lo){
			return;
		}
		if(nums[lo]+nums[hi]==target){
			arr[0]=lo;arr[1]=hi;
		}else if(nums[lo]+nums[hi]>target){
			if(hi-lo<=2){
				find(nums,lo,hi-1,target);
			}else{
				find(nums,lo,(lo+hi)/2,target);
			}
		}else{
			if(hi-lo<=2){
				find(nums,lo+1,hi,target);
			}else{
				find(nums,(lo+hi)/2,hi,target);
			}
		}
	}
	public static void sort(int[] a){
		sort(a,0,a.length-1);
	}
	private static void sort(int[] a,int lo,int hi){
		if(hi<=lo){
			return;
		}
		int j=partition(a,lo,hi);
		sort(a,lo,j-1);
		sort(a,j+1,hi);
	}
	private static int partition(int[] a,int lo,int hi){
		int i=lo,j=hi+1;
		int v=a[lo];
		while(true){
			while(less(a[++i],v)){
				if(i==hi){
					break;
				}
			}
			while(less(v,a[--j])){
				if(j==lo){
					break;
				}
			}
			if(i>=j){
				break;
			}
		}
		exch(a,lo,j);
		return j;
	}
	private static boolean less(int a,int b){
		if(a<b){
			return true;
		}else{
			return false;
		}
	}
	private static void exch(int[] nums,int i,int j){
		int t=nums[i];
		nums[i]=nums[j];
		nums[j]=t;
	}
}
