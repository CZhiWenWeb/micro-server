package com.czw.设计模式.装饰着模式;

import java.util.HashMap;

/**
 * @Author: czw
 * @CreateTime: 2019-12-30 09:47
 * @UpdeteTime: 2019-12-30 09:47
 * @Description:
 * 饮料代码
 */
public class HouseBlend extends Beverage{
	public HouseBlend(){
		description="House Blend Coffee";
	}

	public double cost() {
		return .89;
	}
}
