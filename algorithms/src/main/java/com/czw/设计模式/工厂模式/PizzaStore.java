package com.czw.设计模式.工厂模式;

/**
 * @Author: czw
 * @CreateTime: 2020-01-02 09:05
 * @UpdeteTime: 2020-01-02 09:05
 * @Description:
 */
public abstract class PizzaStore {

	public Pizza orderPizza(String type){
		Pizza pizza;
		pizza=createPizza(type);

		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
	// 实例化披萨的责任被移到一个方法中，此方法就如同一个工厂，这样客户程序中
	// 关于超类的代码就和子类对象创建代码解耦了
	protected abstract Pizza createPizza(String type);
}
