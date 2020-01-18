package com.czw.设计模式.观察者模式;

import java.util.ArrayList;

/**
 * @Author: czw
 * @CreateTime: 2019-12-27 15:38
 * @UpdeteTime: 2019-12-27 15:38
 * @Description:
 */
public class WeatherData implements MySubject{
	// 订阅MySubject的Ob
	private ArrayList<MyObserver> observers;

	private float temperature;
	private float humidity;
	private float pressure;

	public WeatherData(){
		this.observers=new ArrayList<MyObserver>();
	}
	public void registerObserver(MyObserver o) {
		observers.add(o);
	}

	public void removeObserver(MyObserver o) {
		int i=observers.indexOf(o);
		if (i>=0){
			observers.remove(i);
		}
	}

	public void notifyObservers() {
		for (MyObserver ob:observers){
			ob.update(temperature,humidity,pressure);
		}
	}

	private void measurementsChanged(){
		notifyObservers();
	}

	public void setMeasurements(float temperature,float humidity,float pressure){
		this.temperature=temperature;
		this.humidity=humidity;
		this.pressure=pressure;
		measurementsChanged();
	}
}
