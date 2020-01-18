package com.czw.algorithms.第三章.符号表;

import com.czw.algorithms.util.Queue;

import java.util.Iterator;

/**
 * @Author: czw
 * @CreateTime: 2019-12-24 14:03
 * @UpdeteTime: 2019-12-24 14:03
 * @Description:基于线性探测的符号表
 */
public class LinearProbingHashST<Key, Value> {
	private static final int INIT_CAPACITY = 4;
	// 符号表键值对的总数
	private int n;
	// 线性探测表的大小
	private int m;
	// 键
	private Key[] keys;
	// 值
	private Value[] vals;

	public LinearProbingHashST() {
		this(INIT_CAPACITY);
	}

	public LinearProbingHashST(int capacity) {
		m = capacity;
		n = 0;
		keys = (Key[]) new Object[m];
		vals = (Value[]) new Object[m];
	}

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		for (int i = 0; i < m; i++) {
			if (keys[i] != null)
				queue.enqueue(keys[i]);
		}
		return queue;
	}

	public boolean contains(Key key) {
		if (key == null)
			throw new IllegalArgumentException("key为空");
		return get(key) != null;
	}

	// 分布在0 -  m-1
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % m;
	}

	// 用不同容量的新表代替旧表并复制旧表key-val
	private void resize(int capacity) {
		LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<Key, Value>(capacity);
		for (int i = 0; i < m; i++) {
			if (keys[i] != null) {
				temp.put(keys[i], vals[i]);
			}
		}
		keys = temp.keys;
		vals = temp.vals;
		m = temp.m;
	}

	public void put(Key key, Value val) {
		if (key == null)
			throw new IllegalArgumentException("KEY为空");
		if (val == null) {
			delete(key);
			return;
		}
		if (n > m >> 1)
			resize(m << 1);
		int i;
		// keys[i]!=null则冲突，检测key是否相同，相同则修改，不同则i+1，继续探测
		for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
			if (keys[i].equals(key)) {
				vals[i] = val;
				return;
			}
		}
		//	得到一个keys[i]必为空的i
		keys[i] = key;
		vals[i] = val;
		n++;
	}

	public void delete(Key key) {
		if (key == null)
			throw new IllegalArgumentException("key为空");
		if (!contains(key))
			return;
		// find position i of key
		int i = hash(key);
		while (!key.equals(keys[i])) {
			i = (i + 1) % m;
		}
		//	delete key and associated value
		keys[i] = null;
		vals[i] = null;

		i = (i + 1) % m;
		while (keys[i] != null) {
			Key keyToRehash = keys[i];
			Value valToRehash = vals[i];
			keys[i] = null;
			vals[i] = null;
			n--;
			put(keyToRehash, valToRehash);
			i = (i + 1) % m;
		}
		n--;
		if (n > 0 && n <= m >> 3)
			resize(m >> 1);
	}

	public Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException("key为空");
		// 由此可知，当发生碰撞或者n/m过大时，查询会变慢
		for (int i = hash(key); keys[i] != null; i = (i + 1) % m)
			if (keys[i].equals(key))
				return vals[i];
		return null;
	}

	public static void main(String[] args) {
		LinearProbingHashST<String, Integer> st = new LinearProbingHashST<String, Integer>();
		for (int i = 0; i < 10; i++) {
			String s=String.valueOf(i);
			st.put(s, i);
		}
		st.delete("0");
		for (String s : st.keys()) {
			System.out.println(s);
		}
	}
}
