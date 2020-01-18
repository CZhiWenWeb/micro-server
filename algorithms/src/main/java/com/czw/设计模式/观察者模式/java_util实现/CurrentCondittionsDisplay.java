package com.czw.设计模式.观察者模式.java_util实现;

import com.czw.设计模式.观察者模式.DisplayElement;

import java.util.Observable;
import java.util.Observer;

/**
 * @Author: czw
 * @CreateTime: 2019-12-28 16:05
 * @UpdeteTime: 2019-12-28 16:05
 * @Description:
 */
public class CurrentCondittionsDisplay implements Observer, DisplayElement {
	Observable observer;
	private float temperature;
	private float humidity;
	// 需要observable当参数,并将该对象登记为观察者
	public CurrentCondittionsDisplay(Observable observable){
		this.observer=observable;
		observable.addObserver(this);
	}

	public void display() {
		System.out.println(temperature+"---"+humidity);
	}
	// 参数1为被观察者,参数2为主题(被观察者)推送的数据,如果采用pull
	// 方式,则arg为null
	public void update(Observable o, Object arg) {
		if (o instanceof WeatherData){
			WeatherData weatherData= (WeatherData) o;
			this.temperature=weatherData.getThemperature();
			this.humidity=weatherData.getHumidity();
			display();
		}
	}
}
