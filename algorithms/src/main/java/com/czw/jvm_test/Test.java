package com.czw.jvm_test;

import sun.reflect.Reflection;

/**
 * @Author: czw
 * @CreateTime: 2020-01-10 08:58
 * @UpdeteTime: 2020-01-10 08:58
 * @Description:
 */
public class Test {
	public void go(String s){
	}
}
class Test2 extends Test{

	public void go(int i){

	}
	public void go(char c){

	}
	@Override
	public void go(String s){

	}

}
