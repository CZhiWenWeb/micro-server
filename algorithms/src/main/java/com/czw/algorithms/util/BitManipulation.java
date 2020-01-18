package com.czw.algorithms.util;

/**
 * @Author: czw
 * @CreateTime: 2019-12-25 16:17
 * @UpdeteTime: 2019-12-25 16:17
 * @Description:
 */
public class BitManipulation {
	public static void main(String[] args) {
		pBinInt("MIN_INT", Integer.MIN_VALUE);
		pBinInt("MAX_INT",Integer.MAX_VALUE);
		pBinInt("-5",-5);
		pBinInt("0",0);
	}

	static void pBinInt(String s, int i) {
		System.out.println(s + ",int:" + i + ",binary:");
		System.out.print("   ");
		for (int j = 31; j >= 0; j--){
			if (((1 << j) & i) != 0) {
				System.out.print("1");
			} else {
				System.out.print("0");
			}
		}
		System.out.println();
	}

	static void pBinLong(String s, long l) {
		System.out.println(s + ",long:" + l + ",binary:");
		System.out.print("    ");
		for (int i = 63; i >= 0; i--)
			if (((1L << i) & l) != 0)
				System.out.print("1");
			else
				System.out.print("0");
		System.out.println();
	}
}
