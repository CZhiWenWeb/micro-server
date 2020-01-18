package com.czw.设计模式.观察者模式;

public interface MyObserver {
	// 主题会把这些状态值当作方法参数传递给观察者
	void update(float temp, float humidity, float pressure);
}
