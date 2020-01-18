package com.czw.algorithms.第五章.字符串排序;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.sort;

/**
 * @Author: czw
 * @CreateTime: 2020-01-06 15:04
 * @UpdeteTime: 2020-01-06 15:04
 * @Description:
 */
public class Test {
	public int myAtoi(String str) {
		str = str.trim();
		if (str.length() == 0) {
			return 0;
		}
		char[] chars = str.toCharArray();
		ListNode listNode = new ListNode('0');
		ListNode temp = null;
		long result = 0;
		int index = -1;

		if (chars[0] == 43 || chars[0] == 45 || (chars[0] >= 48 && chars[0] <= 57))
			index = 0;

		if (index != -1) {
			listNode.next = new ListNode(chars[index]);
			temp = listNode.next;
			listNode = listNode.next;
			index++;
			while (index < chars.length && chars[index] >= 48 && chars[index] <= 57) {
				listNode.next = new ListNode(chars[index]);
				listNode = listNode.next;
				index++;
			}
			// 负号前缀
			if (temp.val == 45) {
				while (temp.next != null) {
					temp = temp.next;
					result = -Integer.parseInt(String.valueOf(temp.val)) + result * 10;
					if (Integer.MIN_VALUE > result) {
						return Integer.MIN_VALUE;
					}
				}
				return (int) result;
			} else if (temp.val == 43) {
				while (temp.next != null) {
					temp = temp.next;
					result = Integer.parseInt(String.valueOf(temp.val)) + result * 10;
					if (Integer.MAX_VALUE < result) {
						return Integer.MAX_VALUE;
					}
				}
				return (int) result;
			} else {
				while (temp != null) {
					result = Integer.parseInt(String.valueOf(temp.val)) + result * 10;
					if (Integer.MAX_VALUE < result) {
						return Integer.MAX_VALUE;
					}
					temp = temp.next;
				}
				return (int) result;
			}

		} else {
			return 0;
		}
	}

	public int maxArea(int[] height) {
		int area = 0;
		for (int i = 0; i < height.length; i++) {
			for (int j = i + 1; j < height.length; j++) {
				int temp = area(height, i, j);
				area = area > temp ? area : temp;
			}
		}
		return area;
	}

	private int area(int[] nums, int i, int j) {
		return (j - i) * (nums[j] > nums[i] ? nums[i] : nums[j]);
	}

	public int titleToNumber(String s) {
		int sum = 0;
		int length = s.length();
		for (int i = 0; i < length; i++) {
			sum += (s.charAt(i) - 'A' + 1) * Math.pow(26, length - i - 1);
		}
		return sum;
	}

	private int size = 0;

	public int[] addNegabinary(int[] arr1, int[] arr2) {
		long sum = 0;
		int length1 = arr1.length;
		int length2 = arr2.length;
		while (length1 > 0 || length2 > 0) {
			length1--;
			length2--;
			if (length1 >= 0) {
				if (length1 % 2 == 0) {
					sum += arr1[length1] << length1;
				} else {
					sum += -arr1[length1] << length1;
				}
			}
			if (length2 >= 0) {
				if (length2 % 2 == 0) {
					sum += arr2[length2] << length2;
				} else {
					sum += -arr2[length2] << length2;
				}
			}
		}
		return change(sum);
	}

	private int[] change(long a) {
		if (a == 0) {
			return new int[]{0};
		}
		Node root = new Node(0);
		Node result = root;
		while (a != 0) {
			int i = (int) (a % 2);
			a = -a / 2;
			root.next = new Node(Math.abs(i));
			root = root.next;
			size++;
		}
		int[] nums = new int[size];
		int i = 0;
		while (result.next != null) {
			nums[nums.length - 1 - i] = result.next.val;
			result = result.next;
			i++;
		}
		return nums;
	}

	public static void main(String[] args) {
		int[] arr1 = {0};
		int[] arr2 = {1, 0};
		Test test = new Test();
		test.addNegabinary(arr1, arr2);
	}
}

class Node {
	public int val;
	public Node next;

	public Node(int x) {
		val = x;
	}
}

class ListNode {
	public char val;
	public ListNode next;

	public ListNode(char x) {
		val = x;
	}
}

