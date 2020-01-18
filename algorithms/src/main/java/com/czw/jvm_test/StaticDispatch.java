package com.czw.jvm_test;

/**
 * @Author: czw
 * @CreateTime: 2020-01-10 09:04
 * @UpdeteTime: 2020-01-10 09:04
 * @Description:
 */
public class StaticDispatch {

	static abstract class Human{}

	static class Man extends Human{}

	public void sayHello(Human guy){
		System.out.println(guy);
	}

	public void sayHello(Man guy){
		System.out.println(guy);
	}

	public static void main(String[] args) {
		StaticDispatch dispatch=new StaticDispatch();
		Human man=new Man();
		Man man1=new Man();
		dispatch.sayHello(man);
		dispatch.sayHello(man1);
	}
}
