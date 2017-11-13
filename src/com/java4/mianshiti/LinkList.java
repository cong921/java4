package com.java4.mianshiti;

/**
 * 反转单向链表
 * 
 * @author Bob
 *
 */
public class LinkList {
	Node first, last;

	int size;

	public void add(Object obj) {
		Node n = new Node();
		if (first == null) {
			first = n;
			n.next = null;
			n.obj = obj;
			last = n;

			size++;
		} else {
			last.next = n;
			n.obj = obj;
			last = n;
			last.next = null;
			size++;
		}
	}

	public Object get(int index) {
		if (index < 0 || index >= size) {
			try {
				throw new Exception();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Node temp = new Node();
		temp = first;
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		return temp.obj;
	}

	public static void main(String[] args) {
		LinkList list = new LinkList();
		list.add("abc");
		list.reverse();
		Object object = list.get(0);
		System.out.println(object);
	}

	Node current, privious;
	int i = 0;

	public void reverse() {
		swapnode(first);
		Node temp=new Node();
		temp=last;
		last=first;
		first=temp;
		
		/*
		 * rever(); Node temp=new Node(); first.next=null; temp=last;
		 * last=first; first=temp;
		 */
	}

	private Node swapnode(Node head) {
		if(head==null||head.getNext()==null)
			return head;
		Node head1=swapnode(head.getNext());
		head.getNext().setNext(head);
		head.setNext(null);
		return head1;
		
	}

	// private LinkList reverses(LinkList list){
	// Node temp=new Node();
	//
	// if(first==last){
	// return list;
	// }else{
	// if(temp==null)
	// {
	// temp=first;
	// }else{
	// if(temp.next==null){
	// temp.next=temp;
	// }else{
	//
	// }
	// temp.next.next=temp;
	// }
	// }
	// }
	private void rever() {
		Node temp = new Node();
		if (last != first) {
			if (privious == null) {
				privious = first;
			}
			// 当前节点
			current = privious.next;
			// 不是最后后一个

			if (current.next != null) {
				privious = current;
				rever();
				current.next = privious;

			} else {
				current.next = privious;
			}

		}

	}
}

class Node {
	Node next;
	Object obj;

	public Node() {
	}

	public Node(Node next, Object obj) {
		super();
		this.next = next;
		this.obj = obj;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
