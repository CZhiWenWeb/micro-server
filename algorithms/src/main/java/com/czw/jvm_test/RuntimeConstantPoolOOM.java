package com.czw.jvm_test;

/**
 * @Author: czw
 * @CreateTime: 2019-11-22 11:12
 * @UpdeteTime: 2019-11-22 11:12
 * @Description:
 */
public class RuntimeConstantPoolOOM {
	public static void main(String[] args) {
		String str1=new StringBuilder("计算机").append("软件").toString();
		System.out.println(str1.intern()==str1);

		String str2=new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern()==str2);
	}
}
