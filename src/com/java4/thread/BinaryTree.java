package com.java4.thread;

import java.util.*;

public class BinaryTree {
	protected Node root;

	public BinaryTree(Node root) {
		this.root = root;
	}

	public Node getRoot() {
		return root;
	}

	public static Node init() {
		Node a = new Node('A');
		Node b = new Node('B', null, a);
		Node c = new Node('C');
		Node d = new Node('D', b, c);
		Node e = new Node('E');
		Node f = new Node('F', e, null);
		Node g = new Node('G', null, f);
		Node h = new Node('H', d, g);
		return h;// root
	}

	/** 访问节点 */
	public static void visit(Node p) {
		System.out.print(p.getKey() + " ");
	}

	/** 递归实现前序遍历 */
	static void preorder(Node p) {
		if (p != null) {
			visit(p);
			preorder(p.getLeft());
			preorder(p.getRight());
		}
	}

	/** 层次遍历 */
	static void levelorder(Node p) {
		if (p == null)
			return;
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(p);
		while (queue.size() > 0) {
			Node temp = queue.poll();
			visit(temp);
			if (temp.getLeft() != null) {
				queue.offer(temp.getLeft());
			}
			if (temp.getRight() != null) {
				queue.offer(temp.getRight());
			}
		}
	}

	// 将二叉树所有结点的左右子树交换
	static void swapTree(Node root) {
		if (root != null) {
			Node tmp = root.getLeft();
			root.setLeft(root.getRight());
			root.setRight(tmp);
			swapTree(root.getLeft());
			swapTree(root.getRight());
		}
	}

	// 输出二叉树的嵌套括号表示
	static void display(Node tree) {
		if (tree != null) {
			System.out.printf("%c", tree.getKey());
			if (tree.getLeft() != null || tree.getRight() != null) {
				System.out.printf("(");
				display(tree.getLeft());
				if (tree.getRight() != null)
					System.out.printf(",");
				display(tree.getRight());
				System.out.printf(")");
			}
		}
	}

	/** * @param args */
	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree(init());
		display(tree.getRoot());
		System.out.printf("/n/n");
		swapTree(tree.getRoot());
		display(tree.getRoot());
	}
}

class Node {
	private char key;
	private Node left, right;

	public Node(char key) {
		this(key, null, null);
	}

	public Node(char key, Node left, Node right) {
		this.key = key;
		this.left = left;
		this.right = right;
	}

	public char getKey() {
		return key;
	}

	public void setKey(char key) {
		this.key = key;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}
}