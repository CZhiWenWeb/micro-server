package com.czw.设计模式.装饰着模式;

public abstract class Beverage {
	String description="Unknown Beverage";

	public String getDescription(){
		return description;
	}

	public abstract double cost();
}
