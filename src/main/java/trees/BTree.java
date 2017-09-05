package trees;

public class BTree<T extends Comparable<T>> {

    private Node<T> root;
    private int size = 0;

    public static class Node<T extends Comparable<T>> {
	Node<T> left;
	Node<T> right;
	T value;

	public Node(T value) {
	    this.value = value;
	}
    }

    public BTree() {
	root = null;
    }

    public Node<T> getRoot() {
	return root;
    }

    public int getSize() {
	return size;
    }

    public void add(T val) {
	add(new Node<T>(val));
    }

    public void add(Node<T> node) {
	if (root == null) {
	    root = node;
	    return;
	}

	Node<T> prev = null;
	Node<T> temp = root;
	while (temp != null) {
	    prev = temp;
	    int c = node.value.compareTo(temp.value);
	    if (c == 0) {
		// Do nothing. No duplicates allowed
		return;
	    } else if (c > 0) {
		temp = temp.right;
	    } else if (c < 0) {
		temp = temp.left;
	    }
	}

	int c = node.value.compareTo(prev.value);
	if (c > 0) {
	    prev.right = node;
	} else if (c < 0) {
	    prev.left = node;
	}
    }

    public Node<T> breadthSearch(Node<T> target) {
	return null;
    }

    public Node<T> depthSearch(Node<T> target) {
	return null;
    }

    /**
     * root, left, right
     */
    public void preorder(Node<T> node) {
	if (node == null) {
	    return;
	}

	System.out.println(node.value);
	preorder(node.left);
	preorder(node.right);
    }

    /**
     * Find leaves first then root
     */
    public void postorder(Node<T> node) {
	if (node == null) {
	    return;
	}

	postorder(node.left);
	postorder(node.right);
	System.out.println(node.value);
    }

    /**
     * left, root, right
     */
    public void inorder(Node<T> node) {
	if (node == null) {
	    return;
	}

	inorder(node.left);
	System.out.println(node.value);
	inorder(node.right);
    }

    public static void main(String[] args) {
	BTree<Integer> t = new BTree<>();

	t.add(5);
	t.add(2);
	t.add(1);
	t.add(8);
	t.add(10);
	t.add(4);
	t.add(6);
	show(t);
    }

    public static void show(BTree<Integer> t) {
	System.out.println("\npreorder: ");
	t.preorder(t.getRoot());

	System.out.println("postorder: ");
	t.postorder(t.getRoot());

	System.out.println("inorder: ");
	t.inorder(t.getRoot());
    }
}
