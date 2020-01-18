package com.czw.jvm_test.GC_test;

/**
 * @Author: czw
 * @CreateTime: 2019-11-22 11:44
 * @UpdeteTime: 2019-11-22 11:44
 * @Description:引用算法不能解决循环依赖的问题,jvm成功完成回收
 * 说明jvm并不是通过引用计数器来判断对象是否存活的(是通过可达性分析算法实现)
 */
public class ReferenceCountingGC {
	public Object instance=null;
	private static final int _1MB=1024*1024;
	/**
	 * 这个成员属性的唯一意思就是占内存,以便能在GC日志中看清楚是否被回收过
	 */

	private byte[] bigSize=new byte[2*_1MB];
	public static void testGC(){
		ReferenceCountingGC objA=new ReferenceCountingGC();
		ReferenceCountingGC objB=new ReferenceCountingGC();
		objA.instance=objB;
		objB.instance=objA;

		objA=null;
		objB=null;

		System.gc();
	}

	public static void main(String[] args) {
		testGC();
	}
}
