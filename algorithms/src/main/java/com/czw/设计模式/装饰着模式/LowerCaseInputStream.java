package com.czw.设计模式.装饰着模式;


import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: czw
 * @CreateTime: 2019-12-30 16:20
 * @UpdeteTime: 2019-12-30 16:20
 * @Description:
 * 继承抽象装饰类FilterInputStream,对InputStream抽象组件的具体实现类
 * 进行装饰,这里是对字母大小进行转换
 */
public class LowerCaseInputStream extends FilterInputStream {

	/**
	 * Creates a <code>FilterInputStream</code>
	 * by assigning the  argument <code>in</code>
	 * to the field <code>this.in</code> so as
	 * to remember it for later use.
	 *
	 * @param in the underlying input stream, or <code>null</code> if
	 *           this instance is to be created without an underlying stream.
	 */
	protected LowerCaseInputStream(InputStream in) {
		super(in);
	}

	@Override
	public int read() throws IOException {
		int c = super.read();
		return (c == -1 ? c : (Character.isUpperCase(c) ? Character.toLowerCase(c) : c));
	}

	@Override
	public int read(byte[] b) throws IOException {
		return read(b,0,b.length);
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		// 成功读取的字节个数
		int a=super.read(b,off,len);
		for (int i=off;i<off+a;i++){
			if (Character.isUpperCase(b[i])) {
				b[i]= (byte) Character.toLowerCase(b[i]);
			}
		}
		return a;
	}
}
