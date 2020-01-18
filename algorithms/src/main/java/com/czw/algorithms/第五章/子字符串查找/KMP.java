package com.czw.algorithms.第五章.子字符串查找;

/**
 * @Author: czw
 * @CreateTime: 2020-01-11 15:30
 * @UpdeteTime: 2020-01-11 15:30
 * @Description:
 */
public class KMP {
	private String pat;
	private int[][] dfa;
	public KMP(String pat){
	//	由模式字符串构造DFA
		this.pat=pat;
		int M=pat.length();
		int R=256;
		dfa=new int[R][M];
		dfa[pat.charAt(0)][0]=1;
		int p=pat.charAt(0);
		for (int x=0,j=1;j<M;j++){
		//	计算dfa[][j]，每一列dfa[][i]存储着0-R匹配的M，M为以匹配的字符数,
		//	当M==pat.length时即匹配完成
			for (int c=0;c<R;c++)
				// 复制匹配失败情况下的值，即复制dfa[][x-1]列
				dfa[c][j]=dfa[c][x];
			// 对匹配成功情况下的值进行修改
			dfa[pat.charAt(j)][j]=j+1;
			// 更新重启状态
			x=dfa[pat.charAt(j)][x];
		}
	}
	public int search(String txt){
		int i,j,N=txt.length(),M=pat.length();
		for (i=0,j=0;i<N&&j<M;i++){
			j=dfa[txt.charAt(i)][j];
		}
		if (j==M)
			return i-M;
		else
			return N;
	}

	public static void main(String[] args) {
		KMP kmp=new KMP("AACAA");
		int index=kmp.search("AACAADABRA");

	}
}
