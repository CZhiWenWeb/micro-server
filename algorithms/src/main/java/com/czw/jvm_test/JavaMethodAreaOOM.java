package com.czw.jvm_test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: czw
 * @CreateTime: 2019-11-22 11:19
 * @UpdeteTime: 2019-11-22 11:19
 * @Description:接组合CGLib时方法取出现内存溢出异常(未实现,估计动态生成的Class不够多)
 */
public class JavaMethodAreaOOM {
	public static void main(final String[] args) {
		while (true){
			Enhancer enhancer=new Enhancer();
			enhancer.setSuperclass(OOMObject.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor() {
				public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
					return methodProxy.invokeSuper(o,objects);
				}
			});
			enhancer.create();
		}
	}
	static class OOMObject{}
}
