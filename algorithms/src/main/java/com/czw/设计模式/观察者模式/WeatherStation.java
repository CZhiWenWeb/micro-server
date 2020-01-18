package com.czw.设计模式.观察者模式;

/**
 * @Author: czw
 * @CreateTime: 2019-12-27 16:14
 * @UpdeteTime: 2019-12-27 16:14
 * @Description:
 */
public class WeatherStation {
	public static void main(String[] args) {
		WeatherData weatherData=new WeatherData();

		DisplayElement element=new CurrentConditionDisplay(weatherData);
		weatherData.setMeasurements(10,10,10);
		weatherData.setMeasurements(20,20,20);
	}
}
