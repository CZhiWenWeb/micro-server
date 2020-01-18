package com.czw.algorithms.第二章.排序;

import java.lang.reflect.Array;

import static com.czw.algorithms.util.Common.*;

/**
 * @Author: czw
 * @CreateTime: 2019-11-18 17:05
 * @UpdeteTime: 2019-11-18 17:05
 * @Description:
 */
public class ThirdMaxPQ<Key extends Comparable<Key>> {
	private static int sinkIndex=0;
	private static int count=3;
	private Key[] pq;
	private int N=0;
	private void dynamicArray(){
		if (N+1==pq.length){
			Key[] pq2= (Key[]) new Comparable[pq.length*2];
			for (int i=0;i<pq.length;i++){
				pq2[i]=pq[i];
			}
			pq=pq2;
		}else if (N+1<=pq.length/4){
			Key[] pq2= (Key[]) new Comparable[pq.length/2];
			for (int i=0;i<=N;i++){
				pq2[i]=pq[i];
			}
			pq=pq2;
		}
	}
	public ThirdMaxPQ(int maxLen){
		pq= (Key[]) new Comparable[maxLen+1];
	}
	public ThirdMaxPQ(){
		pq= (Key[]) new Comparable[5];
	}
	// 三叉分布规律：父节点（k+1）/3，k,子节点3k+1,3k,3k-1
	private void swim(int k){
		while (k>1&&less(pq[(k+1)/3],pq[k])){
			exch(pq,(k+1)/3,k);
			k=(k+1)/3;
		}
	}
	// 下沉
	private void sink(int k){
		while (3*k-1<=N){
			count=3;sinkIndex=0;
			int j=3*k-1;
			if (j+1<N){
				while (count>0) {
					if (less(pq[k], pq[j + count-1])) {
						exch(pq, k, j + count-1);
						sinkIndex=j+count-1;
					}
					count--;
				}
			}else if (j<N){
				if (less(pq[j],pq[j+1])){
					if (less(pq[k],pq[j+1])){
						exch(pq,k,j+1);
					}
				}else {
					if (less(pq[k],pq[j])){
						exch(pq,k,j);
					}
				}
			}else {
				if (less(pq[k],pq[j])){
					exch(pq,k,j);
				}
			}
			if (sinkIndex==0){
				k=j;
			}else {
				k=sinkIndex;
			}
		}
	}
	public void insert(Key v){
		pq[++N]=v;
		swim(N);
		dynamicArray();
	}
	public Key deleteMax(){
		Key max=pq[1];
		exch(pq,1,N--);
		pq[N+1]=null;
		sink(1);
		dynamicArray();
		return max;
	}
	public static void main(String[] args) {
		ThirdMaxPQ maxPQ=new ThirdMaxPQ();
		Integer[] a=gennerateArray(1,15,10);
		for (Integer i:a
		) {
			maxPQ.insert(i);
		}
		while (maxPQ.N>0){
			System.out.println(maxPQ.deleteMax());
		}
	}
}
