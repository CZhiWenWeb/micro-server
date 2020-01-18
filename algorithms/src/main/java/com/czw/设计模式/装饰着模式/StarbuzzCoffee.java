package com.czw.设计模式.装饰着模式;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @Author: czw
 * @CreateTime: 2019-12-30 11:06
 * @UpdeteTime: 2019-12-30 11:06
 * @Description:
 */
public class StarbuzzCoffee {
	public static void main(String[] args) throws FileNotFoundException {
		InputStream inputStream=new FileInputStream("1");
		BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);
		Beverage beverage=new Espresso();
		System.out.println(beverage.getDescription()+"$"+beverage.cost());
		beverage=new Mocha(beverage);
		beverage=new Mocha(beverage);
		System.out.println(beverage.getDescription()+"$"+beverage.cost());
	}
}
