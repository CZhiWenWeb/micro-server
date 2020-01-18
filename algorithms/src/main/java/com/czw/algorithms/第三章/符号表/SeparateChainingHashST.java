package com.czw.algorithms.第三章.符号表;


import com.czw.algorithms.util.Queue;


/**
 * @Author: czw
 * @CreateTime: 2019-12-23 14:13
 * @UpdeteTime: 2019-12-23 14:13
 * @Description:拉链法
 */
public class SeparateChainingHashST<Key, Value> {
	private final static int INIT_CAPACITY = 4;
	// 键值对总数
	private int N;
	// 散列表的大小
	private int M;
	// 存放链表对象的数组
	private SequentialSearchST<Key, Value>[] st;

	public SeparateChainingHashST() {
		this(INIT_CAPACITY);
	}

	private SeparateChainingHashST(int capacity) {
		// 创建M条链表
		this.M = capacity;
		st = new SequentialSearchST[M];
		for (int i = 0; i < M; i++)
			st[i] = new SequentialSearchST<Key, Value>();
	}

	private void resize(int capacity) {
		SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<Key, Value>(capacity);
		for (int i = 0; i < M; i++) {
			for (Key key : st[i].keys()) {
				temp.put(key, st[i].get(key));
			}
		}
		this.M = temp.M;
		this.st = temp.st;
		this.N = temp.N;
	}

	public boolean contain(Key key) {
		return get(key) != null;
	}

	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	public Value get(Key key) {
		return st[hash(key)].get(key);
	}

	public void put(Key key, Value val) {
		if (val == null) {
			delete(key);
			return;
		}
		if (N > 10 * M) {
			resize(M << 1);
		}
		int i = hash(key);
		if (!st[i].contain(key))
			N++;
		st[i].put(key, val);
	}

	public void delete(Key key) {
		//	找到数组下标
		int i = hash(key);
		if (st[i].contain(key)) {
			N--;
			st[i].delete(key);
			if (M > INIT_CAPACITY && N <= M << 1)
				resize(M >> 1);
		}
	}

	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		for (SequentialSearchST<Key, Value> s : st) {
			for (Key key : s.keys()) {
				queue.enqueue(key);
			}
		}
		return queue;
	}

	public static void main(String[] args) {
		int w = -1;
		w >>>= 10;
		System.out.println(w);
		long l = -1;
		l >>>= 10;
		System.out.println(l);
		short s = -1;
		s >>>= 10;
		System.out.println(s);
		byte b = -1;
		b >>>= 10;
		System.out.println(b);
		SeparateChainingHashST<Integer, String> st = new SeparateChainingHashST<Integer, String>();
		for (int i = 0; i < 10; i++) {
			st.put(i, String.valueOf(i));
		}
		for (int i = 0; i < 8; i++) {
			st.delete(i);
		}
		for (Integer i : st.keys()) {
			System.out.println(i);
		}
	}
}
