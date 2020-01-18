package com.czw.设计模式.装饰着模式;

/**
 * @Author: czw
 * @CreateTime: 2019-12-30 09:37
 * @UpdeteTime: 2019-12-30 09:37
 * @Description:
 * 饮料代码
 */
public class Espresso extends Beverage{
	// 饮料描述,description继承自Beverage
	public Espresso(){
		description="Espresso";
	}

	public double cost() {
		return 1.99;
	}
}
