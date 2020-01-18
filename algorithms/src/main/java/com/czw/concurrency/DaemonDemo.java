package com.czw.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: czw
 * @CreateTime: 2019-12-09 16:30
 * @UpdeteTime: 2019-12-09 16:30
 * @Description:
 */
public class DaemonDemo {
	public static class DaemonT extends Thread{
		@Override
		public void run(){
			while (true){
				System.out.println("I an alive");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		Thread t=new DaemonT();
		//t.setDaemon(true);
		try {
			t.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t.start();
	}
}
