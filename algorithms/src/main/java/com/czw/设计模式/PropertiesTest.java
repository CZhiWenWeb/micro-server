package com.czw.设计模式;

import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.util.Properties;

/**
 * @Author: czw
 * @CreateTime: 2020-01-16 15:44
 * @UpdeteTime: 2020-01-16 15:44
 * @Description:
 */
public class PropertiesTest {
	public static void main(String[] args) throws IOException {
		Properties properties=new Properties();
		String path=PropertiesTest.class.getClassLoader().getResource("")+"/test.properties";
		InputStream in=new FileInputStream(path);
		if (in!=null)
			properties.load(in);
		String data=properties.getProperty("read1","");
		in.close();
		File file=new File("F:\\myrepository\\algorithms\\src\\main\\java\\com\\czw\\设计模式\\设计原则.txt");
		InputStream in2=new FileInputStream(file);


	}
}
