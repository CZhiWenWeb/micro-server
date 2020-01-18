package com.czw.jvm_test;

import com.czw.设计模式.单例模式.Singletion;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: czw
 * @CreateTime: 2019-11-25 14:29
 * @UpdeteTime: 2019-11-25 14:29
 * @Description:
 */
public class ClassLoaderTest {
	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		ClassLoader myLoader=new ClassLoader() {
			// 重写findClass,只尝试加载父加载器加载失败的类，符合双亲委派模型，使用的会是同一个加载器,返回true（即父类加载器加载成功，子加载器无效）
			// 如果重写loadClass则破坏双亲委派模型，使用的是自定义的加载器，返回false
			@Override
			public Class<?> findClass(String name) throws ClassNotFoundException {
				try {
					String fileName=name.substring(name.lastIndexOf(".")+1)
							+".class";
					InputStream is=getClass().getResourceAsStream(fileName);
					if (is==null){
						return super.loadClass(name);
					}
					byte[] b=new byte[is.available()];
					is.read(b);
					return defineClass(name,b,0,b.length);
				} catch (IOException e) {
					throw new ClassNotFoundException(name);
				}
			}
		};
		Object obj=myLoader.loadClass("com.czw.jvm_test.JavaVMStackSOF").newInstance();
		System.out.println(obj.getClass());
		System.out.println(obj instanceof com.czw.jvm_test.JavaVMStackSOF);
	}
}
