package datastructures;

public class BinarySearchTree<T extends Comparable<T>> implements Tree {

    public class Node<T> {
	T value;
	Node left;
	Node right;

	public Node(T value) {
	    this.value = value;
	}
    }

    private Node<T> root;

    public BinarySearchTree(T value) {
	root = new Node<T>(value);
    }

    public void add(Node node) {

    }

    public static void main(String[] args) {

    }
}
