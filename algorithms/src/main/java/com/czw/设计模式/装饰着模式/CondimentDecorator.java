package com.czw.设计模式.装饰着模式;

/**
 * @Author: czw
 * @CreateTime: 2019-12-30 09:32
 * @UpdeteTime: 2019-12-30 09:32
 * @Description:
 * 装饰者类,必须让Condiment Decorator能取代Beverage,所以必须
 * 继承Beverage,保证类型一致
 */
public abstract class CondimentDecorator extends Beverage{
	public abstract String getDescription();
}
