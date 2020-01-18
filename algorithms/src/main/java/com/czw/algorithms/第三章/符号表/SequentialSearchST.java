package com.czw.algorithms.第三章.符号表;

import com.czw.algorithms.util.Common;
import com.czw.algorithms.util.Queue;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Author: czw
 * @CreateTime: 2019-11-20 09:08
 * @UpdeteTime: 2019-11-20 09:08
 * @Description:无序链表
 */
public class SequentialSearchST<Key , Value> {
	private class Node {
		// 链表节点的定义
		private Key key;
		private Value val;
		private Node next;

		public Node(Key key,Value val,Node next){
			this.key=key;
			this.val=val;
			this.next=next;
		}
	}
//	链表首节点
	private Node first;
	//  符号表大小
	private int N=0;

	public boolean contain(Key key){
		return get(key)!=null;
	}

	public int size(){return N;}
	// 返回迭代器供foreach查询，防止数据被修改
	public Iterable<Key> keys(){
		Queue queue=new Queue();
		for (Node x=first;x!=null;x=x.next)
			queue.enqueue(x.key);
		return queue;
	}

	public Value get(Key key){
		for (Node x=first;x!=null;x=x.next){
			if (key.equals(x.key))
				return x.val;
		}
		return null;
	}

	public void put(Key key,Value val){
		if (val==null){
			delete(key);
			return;
		}
	//	查找给定的键，找到则更新其值，否则在表中新建节点
		for (Node x=first;x!=null;x=x.next){
			if (key.equals(x.key))
			{x.val=val;return;}
		}
		N++;
		first=new Node(key,val,first);
	}

	public void delete(Key key){
		first=delete(key,first);
	}
	public Node delete(Key key,Node x){
		if (x==null){
			return null;
		}
		if (x.key.equals(key)){
			N--;
			return x.next;
		}
		x.next=delete(key,x.next);
		return x;
	}

	public static void main(String[] args) {
		SequentialSearchST<Integer,String> seq=new SequentialSearchST();
		Integer[] is= Common.gennerateArray(1,10,10);
		for (int i=0;i<is.length;i++){
			seq.put(i,is[i].toString());
		}
		for (Integer key:seq.keys()){
			System.out.println(key);
		}
	}
}
