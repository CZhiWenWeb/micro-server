package com.czw.设计模式.策略模式;

/**
 * @Author: czw
 * @CreateTime: 2019-12-24 11:57
 * @UpdeteTime: 2019-12-24 11:57
 * @Description:
 */
public class FlyWithWings implements FlyBehavior{
	public void fly() {
		System.out.println("fly");
	}
}
