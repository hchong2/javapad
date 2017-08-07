package datastructures;

import org.junit.Test;

public class LinkedList<E> {

    int size = 0;
    Node<E> first;
    Node<E> last;

    public LinkedList() {

    }

    private static class Node<T> {
	T value;
	Node<T> next;

	public Node(T value) {
	    this.value = value;
	}

	public void add(T value) {
	    Node<T> cur = this;
	    while (cur.next != null) {
		cur = cur.next;
	    }
	    cur.next = new Node<T>(value);
	}

	public String toString() {
	    return String.valueOf(value);
	}
    }

    @Test
    public void test() {
	Node<Integer> integerList = new Node<>(1);
	integerList.add(2);
	integerList.add(3);
	printList(integerList);
	new java.util.LinkedList<>();
    }

    public <T> void printList(Node<T> l) {
	Node<T> n = l;

	if (n == null)
	    return;

	do {
	    p(n);
	    n = n.next;
	} while (n != null);
    }

    public static void p(Object o) {
	System.out.println(o);
    }
}
