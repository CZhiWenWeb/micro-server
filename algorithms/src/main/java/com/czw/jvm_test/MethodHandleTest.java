package com.czw.jvm_test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @Author: czw
 * @CreateTime: 2020-01-10 11:42
 * @UpdeteTime: 2020-01-10 11:42
 * @Description:
 */
public class MethodHandleTest {
	static class ClassA{
		public void println(String s){
			System.out.println("classA "+s);
		}
	}

	public static void main(String[] args) throws Throwable {
		Object obj=System.currentTimeMillis()%2==0
				?System.out:new ClassA();

		getPrintlnMH(obj).invokeExact("icyfenix");

	}
	private static MethodHandle getPrintlnMH(Object reveiver) throws NoSuchMethodException, IllegalAccessException {
		// 第一个参数为返回值，第二个参数为入参
		MethodType mt=MethodType.methodType(void.class,String.class);

		return MethodHandles.lookup().findVirtual(reveiver.getClass(),"println",mt).bindTo(reveiver);
	}
}
