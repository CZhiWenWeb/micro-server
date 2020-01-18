package com.czw.jvm_test.GC_test;

/**
 * @Author: czw
 * @CreateTime: 2019-11-22 16:16
 * @UpdeteTime: 2019-11-22 16:16
 * @Description:1.对象可以在GC时在finalize()方法中自我拯救2.finalize()只会调用一次
 */
public class FinalizeEscapeGC {
	public static FinalizeEscapeGC SAVE_HOOK=null;
	public void isAlive(){
		System.out.println("I an still alive");
	}

	@Override
	protected void finalize() throws Throwable{
		super.finalize();
		System.out.println("finalize method executed");
		FinalizeEscapeGC.SAVE_HOOK=this;
	}

	public static void main(String[] args) throws Throwable {
		SAVE_HOOK=new FinalizeEscapeGC();
		SAVE_HOOK=null;
		// finalize()方法使之重新关联
		System.gc();
	//	finalize()方法优先级低,暂停0.5秒等待其结束
		Thread.sleep(500);
		if (SAVE_HOOK!=null){
			SAVE_HOOK.isAlive();
		}else {
			//死亡了不能调用方法,所以没有isDead()
			System.out.println("I am dead");
		}

		SAVE_HOOK=null;
		System.gc();
		//	finalize()方法优先级低,暂停0.5秒等待其结束
		Thread.sleep(500);
		if (SAVE_HOOK!=null){
			SAVE_HOOK.isAlive();
		}else {
			//死亡了不能调用方法,所以没有isDead()
			System.out.println("I am dead");
		}
	}
}
