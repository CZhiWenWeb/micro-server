package com.czw.algorithms.第五章.单词查找树;

import com.czw.algorithms.util.Queue;

/**
 * @Author: czw
 * @CreateTime: 2020-01-06 14:32
 * @UpdeteTime: 2020-01-06 14:32
 * @Description:基于单词查找树的符号表
 *以char为基本操作单位，对String进行增删（所有的编码方式0-255对应的字符都是一样的，
 * 中文在不同的编码字符表中有不同的index）
 */
public class TrieST<Value> {
	// 基数
	private static int R=256;
	// 单词查找树的根节点
	private Node root;
	// val存储Value,Node[]用于存放该Node指向的Node,
	// Node[]长度与字母表长度一致，每个下标对应着相应
	// 字母结点的存放位子，如果Node[]没有存放Node,则
	// 说明该结点指向null
	private static class Node{
		private Object val;
		private Node[] next=new Node[R];
	}
	public int size(){
		return size(root);
	}
	public Iterable<String> keys(){
		return keysWithPrefix("");
	}

	public Iterable<String> keysThatMatch(String pat){
		Queue<String> q=new Queue<String>();
		collect(root,"",pat,q);
		return q;
	}
	public String longestPrefixOf(String s){
		int length=search(root,s,0,0);
		return s.substring(0,length);
	}
	public void delete(String key){
		root=delete(root,key,0);
	}

	private Node delete(Node x, String key, int d) {
		if (x==null)
			return null;
		if (d==key.length()){
			x.val=null;
		}else {
			char c=key.charAt(d);
			x.next[c]=delete(x.next[c],key,d+1);
		}
		if (x.val!=null)
			return x;
		for (char c=0;c<R;c++)
			if (x.next[c]!=null)
				return x;
		return null;
	}

	private int search(Node x,String s,int d,int length){
		if (x==null)
			return length;
		if (x.val!=null)
			length=d;
		if (d==s.length())
			return length;
		char c=s.charAt(d);
		return search(x.next[c],s,d+1,length);
	}
	private Iterable<String> keysWithPrefix(String pre){
		Queue<String> q=new Queue<String>();
		collect(get(root,pre,0),pre,q);
		return q;
	}
	private void collect(Node x,String pre,String pat,Queue<String> q){
		int d=pre.length();
		if (x==null)
			return;
		if (d==pat.length()&&x.val!=null)
			q.enqueue(pre);
		if (d==pat.length())
			return;
		char next=pat.charAt(d);
		for (char c=0;c<R;c++)
			if (next=='.'||next==c)
				collect(x.next[c],pre+c,pat,q);
	}

	private void collect(Node x,String pre,Queue<String> q){
		if (x==null)
			return;
		if (x.val!=null)
			q.enqueue(pre);
		for (char c=0;c<R;c++){
			collect(x.next[c],pre+c,q);
		}
	}
	private int size(Node x) {
		if (x==null)
			return 0;
		int cnt=0;
		if (x.val!=null)
			cnt++;
		for (char c=0;c<R;c++)
			cnt+=size(x.next[c]);
		return cnt;
	}

	public Value get(String key){
		Node x=get(root,key,0);
		if (x==null)
			return null;
		return (Value) x.val;
	}

	private Node get(Node x,String key,int d){
	//	返回以x作为根结点的子单词查找树中与key相关联的值
		if (x==null)
			return null;
		if (d==key.length())
			return x;
		// 获取下标为d的字符的ascii值，根据c去获取下一个Node
		char c=key.charAt(d);
		// 找到第d个字符所对应的子单词查找树
		return get(x.next[c],key,d+1);
	}
	// key为存储的字符串，val的值存在于尾字母所在的结点中
	public void put(String key,Value val){

		root=put(root,key,val,0);
	}
	// 如果存在x，则根据d判断该节点是否是尾节点，如果是则存放val，如果不是
	// 则为其Node[]添加指向的字母节点，并继续put下一字母；如果不存在x则新建
	private Node put(Node x, String key, Value val, int d) {
	//	如果key存在于以x为根结点的子单词查找树中则更新与它相关联的值
		if (x==null)
			x=new Node();
		// 当d==key.length时，说明key已经被遍历完成，该x即为key的尾字母，
		// val存放于尾字母所在的节点中
		if (d==key.length()){
			x.val=val;
			return x;
		}
		// 找到第d个字符所对应的子单词查找树
		char c=key.charAt(d);
		// 将x指向的字母存放在x的Node[]中，每个字母存放的下标为其ascii值
		x.next[c]=put(x.next[c],key,val,d+1);
		return x;
	}
}
