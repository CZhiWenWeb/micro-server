package com.czw.设计模式.工厂模式;

/**
 * @Author: czw
 * @CreateTime: 2019-12-31 09:55
 * @UpdeteTime: 2019-12-31 09:55
 * @Description:披萨工厂类
 * 该实例化方法可以设置为静态，不过静态方法在类之前
 * 可以执行，不能通过继承来改版创建方法的行为
 */
public class SimplePizzaFactory {
	// 所有客户用这个方法来实例化新对象
	public Pizza createPizza(String type){
		Pizza pizza=null;
		if (type.equals("cheese")){
			pizza=new CheesePizza();
		}else if (type.equals("clam")){
			pizza=new ClamPizza();
		}

		return pizza;
	}
}
