package com.czw.设计模式.单例模式;

/**
 * @Author: czw
 * @CreateTime: 2020-01-09 14:35
 * @UpdeteTime: 2020-01-09 14:35
 * @Description:线程安全的延迟加载的单例demo
 *
 */
public class Singletion {
	public String name;

	private Singletion(){}

	private volatile static Singletion instance;
	// instance是类属性，getInstance也应该是类属性
	// 使用双重检查加锁减少同步
	public static Singletion getInstance(){
		if (instance==null){
			synchronized (Singletion.class){
				if (instance==null){
					instance=new Singletion();
				}
			}
		}
		return instance;
	}

	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		Singletion singletion=Singletion.getInstance();
		singletion.name="1";
		Singletion singletion1= (Singletion) Class.forName(Singletion.class.getName()).newInstance();
		singletion1.name="2";
	}
}
