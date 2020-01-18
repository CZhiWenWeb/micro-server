package com.czw.jvm_test;

import java.io.Serializable;

/**
 * @Author: czw
 * @CreateTime: 2020-01-10 10:28
 * @UpdeteTime: 2020-01-10 10:28
 * @Description:静态分派,主要发生在重载
 * java顺序转型：char>int>long>float>double;不会匹配byte和short类型，因为转型不安全
 * 静态分派发生在编译阶段，在不断注释被选择的重载方法后会发生以下过程：
 * 'a'是一个char类型的数据，会寻找参数类型为char的重载方法》‘a’除了可以代表
 * 一个字符串，还可以代表数字97，寻找参数类型为int的重载》继续寻找long的重载》
 * 这时会发生一次自动装箱，'a'被包装为它的封装类型Character》Character类实现了一个接口
 * Serializable,寻找接口类型,它的另外一个接口为comparable，如果存在两个参数分别为Serializable和
 * comparable的重载方法，他们的优先级是一样的，会提示类型模糊，需显示的指定字面变量》继续寻找所有类的
 * 父类object》可见变长参数的重载优先级是最低的，这时'a'被当作一个数组元素
 */

public class Overload {
	//public static void sayHello(Object arg){
	//	System.out.println("Obj");
	//}

	//public static void sayHello(int arg){
	//	System.out.println("int");
	//}
	//public static void sayHello(long arg){
	//	System.out.println("long");
	//}
	//public static void sayHello(Character arg){
	//	System.out.println("character");
	//}
	//public static void sayHello(char arg){
	//	System.out.println("char");
	//}

	public static void sayHello(char... arg){
		System.out.println("char...");
	}
	//public static void sayHello(Serializable arg){
	//	System.out.println("serializable");
	//}

	public static void main(String[] args) {
		sayHello('a');
	}
}
