package com.czw.algorithms.第三章.符号表;

import com.czw.algorithms.util.Common;
import com.czw.algorithms.util.Queue;
import com.czw.algorithms.第五章.子字符串查找.A;
import com.czw.algorithms.第五章.子字符串查找.C;

/**
 * @Author: czw
 * @CreateTime: 2019-11-21 14:07
 * @UpdeteTime: 2019-11-21 14:07
 * @Description:二叉查找树,运行时间取决于树的形状,当为完全平衡树时最佳
 */
public class BST<Key extends Comparable<Key>, Value> {
	// 数据表示
	private class Node {
		private Key key;
		private Value val;
		// 指向子数的链接
		private Node left, right;
		// 该结点的size
		private int N;

		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}

	// 二叉查找树的根节点
	private Node root;

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null)
			return 0;
		else
			return x.N;
	}

	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return get(x.left, key);
		else if (cmp > 0)
			return get(x.right, key);
		else
			return x.val;
	}

	// 有则修改，无则新建
	public void put(Key key, Value val) {
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
		if (x == null)
			// 头部插入,新建结点初始结点的排序始终为1
			return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		// 递归算法,key小于当前节点的总是在左侧,key大于当前节点的总是在右侧,
		// 保证任意结点左侧永远小于右侧
		if (cmp < 0)
			x.left = put(x.left, key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else
			x.val = val;
		// 以任意结点为根的结点的size总是等于=左子数+右子数+1(初始size),保证跟新后结点的size总是正确的
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public Key min() {
		return min(root).key;
	}

	private Node min(Node x) {
		if (x.left == null)
			return x;
		return min(x.left);
	}

	public Key max() {
		return max(root).key;
	}

	private Node max(Node x) {
		if (x.right == null)
			return x;
		return max(x.right);
	}

	// 向下取key
	public Key floor(Key key) {
		Node x = floor(root, key);
		if (x == null)
			return null;
		return x.key;
	}

	private Node floor(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		// 如果key小于x.key则往左边查找,如果左边不存在即x.key为最小,
		// 该树中不存在Key的向下取整
		if (cmp < 0)
			return floor(x.left, key);
		//如果Key大于x.key,则往右边递归查找且Key的向下取整一定大于或等于x.key,
		Node t = floor(x.right, key);
		// 如果t存在则Key向下取整大于x.key
		if (t != null)
			return t;
		else
			return x;
	}
	// 向上取Key
	public Key ceiling(Key key) {
		Node x = ceiling(root, key);
		if (x == null)
			return null;
		return x.key;
	}

	private Node ceiling(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		if (cmp > 0)
			return ceiling(x.right, key);
		Node t = ceiling(x.left, key);
		if (t != null)
			return t;
		else
			return x;
	}
	//查询任意排序为k的Key
	public Key select(int k){
		return select(root,k).key;
	}
	//对于任意结点，其排名t=size(x.left),左侧结点排名<t;右侧排名>=size(x.right.left)+t+1
	private Node select(Node x,int k){
		if (x==null)
			return null;
		int t=size(x.left);
		if (t>k)
			// 排名小于左侧结点，则继续在左侧查找
			return select(x.left,k);
		if (t<k)
			// 即排名为k的结点在以x.right为根的相对排名为k-t-1
			return select(x.right,k-t-1);
		else
			return x;
	}
	// 查询任意Key的排名
	public int rank(Key key){
		return rank(key,root);
	}

	private int rank(Key key,Node x){
		// 返回以x为根节点的子树中小于x.key的键的数量
		if (x==null)
			return 0;
		int cmp=key.compareTo(x.key);
		if (cmp<0)
			return rank(key, x.left);
		else if (cmp>0)
			return 1+size(x.left)+rank(key,x.right);
		else
			return size(x.left);
	}
	// 删除最小值
	public void deleteMin(){
		root=deleteMin(root);
	}
	private Node deleteMin(Node x){
		// 设当前结点为x1,x.left=x1;当x1.left=null时，说明x1为最小值，
		// x.left=x1.right，结点x.left指向x1.right，x1失去连接，会被垃圾
		//回收，完成删除
		if (x.left==null)
			return x.right;
		// 递归操作，接受一个结点，返回其左连接
		x.left=deleteMin(x.left);
		// 重新计算size
		x.N=size(x.left)+size(x.right)+1;
		return x;
	}
	// 删除最大值
	public void deleteMax(){
		root=deleteMax(root);
	}
	private Node deleteMax(Node x){
		if (x.right==null)
			return x.left;
		x.right=deleteMax(x.right);
		x.N=size(x.right)+size(x.left)+1;
		return x;
	}
	// 删除任意结点
	public void delete(Key key){
		root=delete(root,key);
	}
	private Node delete(Node x,Key key){
		if (x==null)
			return null;
		int cmp=key.compareTo(x.key);
		//1. 先找到Key对应的x1
		if (cmp<0)
			x.left=delete(x.left,key);
		else if (cmp>0)
			x.right=delete(x.right,key);
		else {
			if (x.right==null)
				return x.left;
			if (x.left==null)
				return x.right;
			// 新建引用t指向x1结点对象（即要被删除的对象）
			Node t=x;
			//2.引用x1指向其后继结点对象（保证指向x1的引用无需改变）
			x=min(t.right);
			//3.删除x1右链接的最小结点的引用，最小结点引用指向的对象被x1指向，
			//不会被回收
			x.right=deleteMin(t.right);
			//4.后继结点左链接指向x1.left
			x.left=t.left;
		//	5.t引用失去关联被回收，t引用指向的结点对象失去引用被回收，删除完成
		}
		//重新计算结点size
		x.N=size(x.left)+size(x.right)+1;
		return x;
	}
	public Iterable<Key> keys(){
		return keys(min(),max());
	}
	//顺序打印
	private void print(Node x){
		if (x==null)
			return;
		print(x.left);
		System.out.println(x.key);
		print(x.right);
	}
	// 范围查询
	public Iterable<Key> keys(Key lo,Key hi){
		Queue<Key> queue=new Queue<Key>();
		keys(root,queue,lo,hi);
		return queue;
	}
	//顺序打印原理,从左往右进入队列，即按Key的升序进入队列
	private void keys(Node x,Queue<Key> queue,Key lo,Key hi){
		if (x==null)
			return;
		int cmplo=lo.compareTo(x.key);
		int cmphi=hi.compareTo(x.key);
		if (cmplo<0)
			keys(x.left,queue,lo,hi);
		if (cmplo<=0&&cmphi>=0)
			queue.enqueue(x.key);
		if (cmphi>0)
			keys(x.right,queue,lo,hi);
	}

	public static void main(String[] args) {
		BST<Integer,Integer> bst=new BST();
		Integer[] a= Common.gennerateArray(1,20,15);
		for (int i=0;i<15;i++){
			bst.put(i,a[i]);
		}
		bst.delete(7);
		Integer key=14;
		System.out.println(key+"排名："+bst.rank(key));
		Iterable<Integer> iterable=bst.keys(3,9);
		for (Integer i:iterable
		     ) {
			System.out.println(i);
		}
	}
}
