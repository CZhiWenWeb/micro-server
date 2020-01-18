package com.czw.设计模式.观察者模式.java_util实现;

import java.util.Observable;

/**
 * @Author: czw
 * @CreateTime: 2019-12-27 16:26
 * @UpdeteTime: 2019-12-27 16:26
 * @Description:
 * 不在需要追踪观察者，也不用管理注册和删除，由超类管理
 * 但observable是一个类,实现该类的类无法实现另一个超类的行为,
 * 并且其将关键方法setChanged()保护了起来,这意味着除非继承它,
 * 否则无法创建observable实例并组合到自己的对象来,违反了设计
 * 原则:多用组合,少用继承
 */
public class WeatherData extends Observable {
	private float themperature;
	private float humidity;
	private float pressure;

	// 没有调用notifyObservers传送数据对象，说明采用pull方式
	private void measurementsChanged(){
		// 调用notifyObservers之前要用setChanged()表示状态
		// 已经改变
		setChanged();
		notifyObservers();
	}

	public void setMeasurements(float themperature,float humidity,float pressure){
		this.themperature=themperature;
		this.humidity=humidity;
		this.pressure=pressure;
		measurementsChanged();
	}

	// observable的pull方式的以下方法用于给observer获取状态
	public float getThemperature() {
		return themperature;
	}

	public float getHumidity() {
		return humidity;
	}

	public float getPressure() {
		return pressure;
	}
}
