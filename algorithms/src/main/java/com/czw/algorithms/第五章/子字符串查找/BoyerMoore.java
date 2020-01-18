package com.czw.algorithms.第五章.子字符串查找;

/**
 * @Author: czw
 * @CreateTime: 2020-01-14 14:14
 * @UpdeteTime: 2020-01-14 14:14
 * @Description:
 */
public class BoyerMoore {
	private int R=256;
	private String pat;
	private int[] right=new int[256];
	public BoyerMoore(String pat){
		this.pat=pat;
		int index=0;
		while (index<R){
			// 不包含在模式字符串中的字符的值为-1
			right[index]=-1;
			index++;
		}
		for (int j=0;j<pat.length();j++){
			right[pat.charAt(j)]=j;
		}
	}
	public int search(String txt){
		// 在txt中查找字符串
		int N=txt.length();
		int M=pat.length();
		int skip;
		for (int i=0;i<=N-M;i+=skip){
			// 模式字符串和文本在位置i匹配吗
			skip=0;
			for (int j=M-1;j>=0;j--){
				if (pat.charAt(j)!=txt.charAt(i+j)){
					skip=j-right[txt.charAt(i+j)];
					if (skip<1)
						skip=1;
					break;
				}
			}
			// 找到匹配
			if (skip==0)
				return i;
		}
		// 未找到匹配
		return N;
	}

}
