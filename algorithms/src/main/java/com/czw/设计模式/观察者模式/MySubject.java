package com.czw.设计模式.观察者模式;

public interface MySubject {
	// 调用观察者用来注册或删除
	void registerObserver(MyObserver o);

	void removeObserver(MyObserver o);

	// 通知所有的观察者
	void notifyObservers();
}
