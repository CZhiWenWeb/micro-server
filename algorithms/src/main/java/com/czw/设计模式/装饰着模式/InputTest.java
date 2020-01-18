package com.czw.设计模式.装饰着模式;

import java.io.*;

/**
 * @Author: czw
 * @CreateTime: 2019-12-30 16:34
 * @UpdeteTime: 2019-12-30 16:34
 * @Description:
 */
public class InputTest {
	public static void main(String[] args) {
		int c;
		byte[] bytes=new byte[2];
		try {
			InputStream in=
					//自定义的装饰类
					new LowerCaseInputStream(
					new BufferedInputStream(
							// InputStream的具体组件,被Buff和Lower装饰
							new FileInputStream("F:\\myrepository\\algorithms\\src\\main\\java\\com\\czw\\设计模式\\装饰着模式\\test.txt")
					)
			);
			while ((c=in.read(bytes))!=-1){
				for (byte b:bytes
				     ) {
					System.out.print((char) b);
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
