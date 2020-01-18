package com.czw.设计模式.装饰着模式;

/**
 * @Author: czw
 * @CreateTime: 2019-12-30 09:50
 * @UpdeteTime: 2019-12-30 09:50
 * @Description:
 * 摩卡是装饰者
 */
public class Mocha extends CondimentDecorator{
	//要让mocha能够引用一个Beverage,可以用一个实例变量记录被装饰者
	Beverage beverage;
	//让被装饰者被记录到实例变量中
	public Mocha(Beverage beverage){
		this.beverage=beverage;
	}
	// 完整的描述被装饰之后的饮料
	public String getDescription() {
		return beverage.getDescription()+",Mocha";
	}

	public double cost() {
		return .20+beverage.cost();
	}
}
