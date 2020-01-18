package com.czw.jvm_test;

/**
 * @Author: czw
 * @CreateTime: 2020-01-10 10:53
 * @UpdeteTime: 2020-01-10 10:53
 * @Description:动态分派
 */
public class DynamicDispatch {
	static abstract class Human{
		protected abstract void sayHello();
	}

	static class Man extends Human{
		@Override
		protected void sayHello() {
			System.out.println("man");
		}
	}
	static class WoMan extends Human{

		protected void sayHello() {
			System.out.println("woman");
		}
	}

	public static void main(String[] args) {
		Human man=new Man();
		Human woman=new WoMan();

	}
}
