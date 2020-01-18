package com.czw.设计模式.观察者模式;

/**
 * @Author: czw
 * @CreateTime: 2019-12-27 16:04
 * @UpdeteTime: 2019-12-27 16:04
 * @Description:
 */
public class CurrentConditionDisplay implements MyObserver,DisplayElement{
	private float temperature;
	private float humidity;
	// 保存引用方便取消注册
	private MySubject weatherData;

	// 构造器注入MySubject，并注册
	public CurrentConditionDisplay(MySubject weatherData){
		this.weatherData=weatherData;
		weatherData.registerObserver(this);
	}

	public void display() {
		System.out.println(temperature+"--"+humidity);
	}
	// 显示最近的参数
	public void update(float temp, float humidity, float pressure) {
		this.temperature=temp;
		this.humidity=humidity;
		display();
	}
}
