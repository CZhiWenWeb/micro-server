package com.czw.algorithms.util;


import java.util.Iterator;

/**
 * @Author: czw
 * @CreateTime: 2019-11-20 09:57
 * @UpdeteTime: 2019-11-20 09:57
 * @Description:先进先出
 */
public class Queue<Item> implements Iterable<Item> {
	private Node first;
	private Node last;
	private int N=0;

	public void enqueue(Item item){
		Node newNode=new Node(item);
		// 队列是否存在元素
		if (first==null){
			first=newNode;
			last=newNode;
		}else {
			// 如果存在头部插入
			newNode.next=first;
			first.last=newNode;
			first=newNode;
		}
		N++;
	}

	public Item dequeue(){
		if (N==0){
			return null;
		}else if (N==1){
			Item item=first.item;
			first=null;
			last=null;
			N--;
			return item;
		}else {
			// 删除尾部元素
			Item item=last.item;
			last.last.next=null;
			last=last.last;
			N--;
			return item;
		}
	}
	public int size(){return N;}

	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}
	// 构建迭代器
	private class ListIterator<Item> implements Iterator<Item>{
		private Node current;
		public ListIterator(Node first){
			current=first;
		}
		public boolean hasNext() {
			return current!=null;
		}

		public Item next() {
			if (!hasNext()){
				return null;
			}
			Item item= (Item) current.item;
			current=current.next;
			return item;
		}

		public void remove() {
			try {
				throw new Exception("迭代删除违背先进先出");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	// 辅助类
	private class Node{
		Item item;
		Node next;
		Node last;
		public Node(Item item){
			this.item=item;
		}
	}

	public static void main(String[] args) {
		Queue<Integer> iq=new Queue<Integer>();
		Integer[] a=Common.gennerateArray(1,10,10);
		for (int i=0;i<a.length;i++){
			iq.enqueue(a[i]);
		}
		Iterator iterator=iq.iterator();
		while (iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
}
