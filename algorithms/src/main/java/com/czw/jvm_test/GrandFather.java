package com.czw.jvm_test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * @Author: czw
 * @CreateTime: 2020-01-10 14:10
 * @UpdeteTime: 2020-01-10 14:10
 * @Description:
 */

class Tst {
	 class GrandFather {
		private void thinking() {
			System.out.println("grandF");
		}
	}

	class Father extends GrandFather {

		void thinking() {
			System.out.println("Father");
		}
	}

	class Son extends Father {
		void thinking() {
			try {
				MethodType mt = MethodType.methodType(void.class);
				MethodHandle mh = lookup().findVirtual(GrandFather.class, "thinking", mt);
				mh.invokeExact();
			} catch (Throwable e) {

			}

		}
	}

	public static void main(String[] args) throws Throwable {
		Object x,y;String s;int i;
		MethodType mt;
		MethodHandle mh;
		MethodHandles.Lookup lookup=MethodHandles.lookup();

		//mt=MethodType.methodType(String.class,char.class,char.class);
		//// 调用虚方法
		//mh=lookup.findVirtual(String.class,"replace",mt);
		//s= (String) mh.invokeExact("daddy",'d','n');
		//System.out.println(s);
		// 调用F静态方法
		//mt=MethodType.methodType(List.class,Object[].class);
		//mh=lookup.findStatic(Arrays.class,"asList",mt);
		//x=mh.invoke("one",'v');
		//// 当方法的入参为数组时，可以限定参数个数
		//mt=MethodType.genericMethodType(3);
		//mh=mh.asType(mt);
		//x=mh.invoke(1,2,3);
		Field IMPL_LOOKUP=lookup.getClass().getDeclaredField("IMPL_LOOKUP");
		IMPL_LOOKUP.setAccessible(true);
		MethodHandles.Lookup lkp= (MethodHandles.Lookup) IMPL_LOOKUP.get(null);
		mt=MethodType.methodType(void.class);
		mh=lkp.findSpecial(GrandFather.class,"thinking",mt,GrandFather.class);
		mh.invoke(new Tst().new GrandFather());
	}
}
