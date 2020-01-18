package com.czw.algorithms.第五章.单词查找树;

/**
 * @Author: czw
 * @CreateTime: 2020-01-09 09:11
 * @UpdeteTime: 2020-01-09 09:11
 * @Description:
 */
public class TST<Value> {
	// 根节点
	private Node root;

	private class Node {
		char c;
		Node left, mid, right;
		Value val;
	}

	public Value get(String key) {
		return get(root, key, 0).val;
	}

	private Node get(Node x, String key, int d) {
		if (x == null)
			return null;
		// 获取key的d位字符
		char c = key.charAt(d);
		// 如果c<x.c，则往左边查找
		if (c < x.c)
			return get(x.left, key, d);
			// c>x.c，往右边查找
		else if (c > x.c)
			return get(x.right, key, d);
			// 如果c=x.c，并且d位不是尾字符，则查询key中的下一位字符
		else if (d < (key.length() - 1))
			return get(x.mid, key, d + 1);
			// 当d为尾字符时，且c=x.c，即x为存放val的节点，查询完毕
		else
			return x;
	}
	//public void delete(String key){
	//	root=delete(root,key,0);
	//}
	//
	//private Node delete(Node x,String key,int d){
	//	if (x==null)
	//		return null;
	//	char c=key.charAt(d);
	//	if (c>x.c)
	//		x.right=delete(x.right,key,d);
	//	else if (c<x.c)
	//		x.left=delete(x.left,key,d);
	//	else if (d==key.length())
	//		x.val=null;
	//	else
	//		x.mid=delete(x.mid,key,d+1);
	//
	//}

	public void put(String key, Value val) {
		root = put(root, key, val, 0);
	}

	private Node put(Node x, String key, Value val, int d) {
		char c = key.charAt(d);
		if (x == null) {
			x = new Node();
			x.c=c;
		}
		// 这个递归插入就是将0-key.length()-1的每个字符
		// 插入一遍，如果存在就继续插入下一个；否则新建
		if (x.c>c)
			x.left=put(x.left,key,val,d);
		else if (x.c<c)
			x.right=put(x.right,key,val,d);
		else if (d<key.length()-1)
			x.mid=put(x.mid,key,val,d+1);
		// 尾节点存放值
		else
			x.val=val;
		return x;
	}

}
