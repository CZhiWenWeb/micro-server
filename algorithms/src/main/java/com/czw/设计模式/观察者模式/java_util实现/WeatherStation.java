package com.czw.设计模式.观察者模式.java_util实现;

import com.czw.设计模式.观察者模式.DisplayElement;

import java.util.Observable;

/**
 * @Author: czw
 * @CreateTime: 2019-12-28 16:18
 * @UpdeteTime: 2019-12-28 16:18
 * @Description:
 */
public class WeatherStation {
	public static void main(String[] args) {
	//	新建主题
		WeatherData weatherData=new WeatherData();
		Observable observable=new Observable();
		DisplayElement displayElement=new CurrentCondittionsDisplay(weatherData);
		weatherData.setMeasurements(1,1,1);
		weatherData.setMeasurements(2,2,2);
	}
}
