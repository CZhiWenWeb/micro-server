package com.czw.设计模式.策略模式;

/**
 * @Author: czw
 * @CreateTime: 2019-12-24 11:53
 * @UpdeteTime: 2019-12-24 11:53
 * @Description:
 */
public class Quack implements QuackBehavior{
	public void quack() {
		System.out.println("quack");
	}
}
