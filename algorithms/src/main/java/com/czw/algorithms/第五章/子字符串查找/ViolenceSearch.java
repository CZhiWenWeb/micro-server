package com.czw.algorithms.第五章.子字符串查找;

/**
 * @Author: czw
 * @CreateTime: 2020-01-11 14:02
 * @UpdeteTime: 2020-01-11 14:02
 * @Description:暴力查找法
 */
public class ViolenceSearch {
	public static int search(String pat, String txt) {
		int j, M = pat.length();
		int i, N = txt.length();
		for (i = 0, j = 0; i < N && j < M; i++) {
			if (txt.charAt(i) == pat.charAt(j)) {
				j++;
			} else {
				i -= j;
				j = 0;
			}
		}
		if (j == M) {
			// 找到匹配返回匹配首字母的下标
			return i - M;
		} else {
			// 未找到返回越界下标
			return N;
		}
	}

	public static void main(String[] args) {
		String pat="aabx";
		String txt="xaxaab";
		int i=search(pat,txt);
	}
}
