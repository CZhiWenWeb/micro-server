package com.czw.concurrency;

import java.util.Date;

/**
 * @Author: czw
 * @CreateTime: 2019-12-09 17:19
 * @UpdeteTime: 2019-12-09 17:19
 * @Description:
 */
public class AccountingSync implements Runnable{
	// 静态属性为类属性
	static  AccountingSync instance=new AccountingSync();
	static volatile int i=0;
	public  void increase(){
		i++;
	}
	public void run() {
		for (int j=0;j<1000000;j++){
				increase();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		long date=new Date().getTime();
		final Thread t1= new Thread(new AccountingSync());
		final Thread t2= new Thread(new AccountingSync());
		t1.start();t2.start();
		t1.join();t2.join();
		System.out.println("i:"+i);
		System.out.println("Time:"+(new Date().getTime()-date));
	}
}
