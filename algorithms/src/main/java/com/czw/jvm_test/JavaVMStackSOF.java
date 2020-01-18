package com.czw.jvm_test;

/**
 * @Author: czw
 * @CreateTime: 2019-11-22 11:01
 * @UpdeteTime: 2019-11-22 11:01
 * @Description:方法造成堆溢出
 */
public class JavaVMStackSOF {
	private int stackLength=1;
	public void stackLeak(){
		this.stackLength++;
		stackLeak();
	}

	public static void main(String[] args) throws Throwable {
		JavaVMStackSOF oom=new JavaVMStackSOF();
		try {
			oom.stackLeak();
		}catch (Throwable e){
			System.out.println("stack length:"+oom.stackLength);
			throw e;
		}
	}
}
