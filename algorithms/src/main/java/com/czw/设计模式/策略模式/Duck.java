package com.czw.设计模式.策略模式;

/**
 * @Author: czw
 * @CreateTime: 2019-12-24 11:37
 * @UpdeteTime: 2019-12-24 11:37
 * @Description:
 * 针对接口编程关键在于多态，利用多态程序可以针对超类型编程，执行时回根据
 * 实际状况执行到真正的行为，不会被绑死在超类型的行为上，因此变量的声明类
 * 型通常是一个超类型（抽象类或者接口），只要是具体实现此超类型的类所产生的
 * 对象都可以指定给这个变量，意味着声明类时不用理会以后执行时的真正对象类型
 */
public abstract class Duck {
	// 将fly和quack委托其他类处理，而不是使用定义在Duck类的fly和quack方法
	protected FlyBehavior flyBehavior;
	protected QuackBehavior quackBehavior;
	// setter method可以动态设定行为
	public void setFlyBehavior(FlyBehavior flyBehavior) {
		this.flyBehavior = flyBehavior;
	}

	public void setQuackBehavior(QuackBehavior quackBehavior) {
		this.quackBehavior = quackBehavior;
	}

	public abstract void display();

	public void performQuack(){
		//duck不处理quack，而是委托给quackBehavior引用的对象
		quackBehavior.quack();
	}

	public void performFly(){
		flyBehavior.fly();
	}
	public void swim() {
		System.out.println("All ducks float,even decoys");
	}
}
