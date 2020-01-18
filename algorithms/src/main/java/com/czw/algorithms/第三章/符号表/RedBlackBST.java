package com.czw.algorithms.第三章.符号表;


import java.util.NoSuchElementException;

/**
 * @Author: czw
 * @CreateTime: 2019-11-26 14:32
 * @UpdeteTime: 2019-11-26 14:32
 * @Description:红黑二叉查找树：1.红链接均为左连接，2.没有任何一个结点同时和
 * 两条红链接相连3.该树是完美黑色平衡的，即任意空连接到跟的路径上的黑链接数量相同
 * 红色二叉树的基本思想：是用标准的二叉查找树（完全由2-节点构成）和一些额外的信息（3-节点）
 * 来表示2-3树。红链接将两个2-节点连接起来构成一个3-节点，黑链接则是普通连接
 */
public class RedBlackBST<Key extends Comparable<Key>,Value> {
	private static final boolean RED=true;
	private static final boolean BLACK=false;
	private class Node{
		Key key;
		Value val;
		Node left,right;
		// 这颗子数中的节点总数
		int N;
		// 由其父节点指向它的链接的颜色
		boolean color;

		Node(Key key,Value val,int N,boolean color){
			this.key=key;
			this.val=val;
			this.N=N;
			this.color=color;
		}
	}

	private boolean isRed(Node x){
		if (x==null)
			return false;
		return x.color== RED;
	}

	private Node root;

	private Node rotateLeft(Node h){
		// 接受root节点h,取x=h.right
		Node x=h.right;
		h.right=x.left;
		x.left=h;
		// 旋转后的节点继承源节点其父节点指向它的颜色
		x.color=h.color;
		// 旋转后h节点的父节点为x，x指向它的颜色为RED
		h.color=RED;
		// 旋转后x.N的节点数为h.N的节点数
		x.N=h.N;
		h.N=1+size(h.left)+size(h.right);
		// 返回旋转后该树的root节点
		return x;
	}

	private Node rotateRight(Node h){
	//	接受root节点h,取x=h.left
		Node x=h.left;
		h.left=x.right;
		x.right=h;
		x.color=h.color;
		h.color=RED;
		x.N=h.N;
		h.N=1+size(h.right)+size(h.left);
		return x;
	}

	public void flipColors(Node h){
		h.color=RED;
		h.left.color=BLACK;
		h.right.color=BLACK;
	}
	private int size(Node n){
		if (n==null)
			return 0;
		else
			return n.N;
	}

	public int size(){
		return size(root);
	}

	public boolean isEmpty(){
		return root==null;
	}
	public void put(Key key,Value val){
		root=put(root,key,val);
		root.color=BLACK;
	}
	private Node balance(Node h){
		if (isRed(h.right)&&!isRed(h.left))
			h=rotateLeft(h);
		if (isRed(h.left)&&isRed(h.left.left))
			h=rotateRight(h);
		if (isRed(h.left)&&isRed(h.right))
			flipColors(h);

		h.N=1+size(h.left)+size(h.right);
		return h;
	}

	private Node put(Node h, Key key, Value val) {
		if (root==null)
			// 和父节点用红链接相连
			return new Node(key,val,1,RED);
	//	二叉树插入方式
		int cmp=key.compareTo(h.key);
		if (cmp<0)
			h.left=put(h.left,key,val);
		if (cmp>0)
			h.right=put(h.right,key,val);
		else
			h.val=val;
	//	校验插入后是否需要旋转或颜色转换
		return balance(h);
	}
	// h.left和h.left.left是BLACK,为了保证树的平衡性，删除只能在3-节点或临时4-节点进行，
	// 所以如果h.right.left是红色连接，将该连接颜色移动至左边
	private Node moveRedLeft(Node h){
		flipColors(h);
		if (isRed(h.right.left)){
			h.right=rotateRight(h.right);
			h=rotateLeft(h);
			// 节点的位置改变
			flipColors(h);
			//	颜色移动完毕
		}
		// 返回一个h.left或者h.left.left是RED的节点
		return h;
	}
	// h.right或h.left或h.color至少有一个为红
	private Node deleteMin(Node h){
		if (h.left==null)
			return null;
		// 将h.left或h.left.left变为RED，保证删除后树的平衡性
		if (!isRed(h.left)&&!isRed(h.left.left))
			h=moveRedLeft(h);
		// 递归查找最小值
		h.left=deleteMin(h.left);
		// 校验删除后是否需要旋转或颜色转换
		return balance(h);
	}

	public void deleteMin(){
		if (isEmpty())
			throw new NoSuchElementException("BST has nothing");
		// 如果根节点为2-节点，则将其转为3-节点
		if (!isRed(root.left)&&!isRed(root.right))
			root.color=RED;
		root=deleteMin(root);
		if (!isEmpty())
			root.color=BLACK;
	}
	//public void deleteMax(){
	//	if (isEmpty())
	//		throw new NoSuchElementException("BST has nothing");
	//	if (!isRed(root.right)&&!isRed(root.left))
	//		root.color=RED;
	//	root=deleteMax(root);
	//	if (!isEmpty())
	//		root.color=BLACK;
	//}
	//private Node deleteMax(Node h){
	//	if (h.right==null)
	//		return null;
	//	if (!isRed(h.right)&&!isRed(h.right.right))
	//		moveRedRight(h);
	//	h.right=deleteMax(h.right);
	//	balance(h);
	//}
	//private Node moveRedRight(Node h){
	//
	//}
}
