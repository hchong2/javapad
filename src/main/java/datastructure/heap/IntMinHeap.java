package datastructure.heap;

import java.util.Arrays;

public class IntMinHeap {

    int capacity = 10;
    int size = 0;
    int[] heap = new int[capacity];

    public String toString() {
	return Arrays.toString(heap);
    }

    private int left(int index) {
	return heap[getLeftChildIndex(index)];
    }

    private int right(int index) {
	return heap[getRightChildIndex(index)];
    }

    private int parent(int index) {
	return heap[getParentIndex(index)];
    }

    private int getLeftChildIndex(int parentIndex) {
	return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
	return 2 * parentIndex + 2;
    }

    private int getParentIndex(int index) {
	return index / 2;
    }

    private boolean hasLeftChild(int index) {
	return getLeftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
	return getRightChildIndex(index) > size;
    }

    private boolean hasParent(int index) {
	return getParentIndex(index) >= 0;
    }

    private void ensureCapacity() {
	if (capacity == size) {
	    heap = Arrays.copyOf(heap, capacity * 2);
	    capacity *= 2;
	}
    }

    private void swap(int x, int y) {
	int temp = heap[x];
	heap[x] = heap[y];
	heap[y] = temp;
    }

    public int peek() throws IllegalStateException {
	if (size == 0) {
	    throw new IllegalStateException();
	}
	return heap[0];
    }

    public int poll() throws IllegalStateException {
	if (size == 0) {
	    throw new IllegalStateException();
	}
	int val = heap[0];
	heap[0] = heap[size - 1];
	heap[size - 1] = 0;
	size--;
	heapifyDownRecursive(0);
	return val;
    }

    public void add(int val) {
	ensureCapacity();
	heap[size] = val;
	heapifyUp(size);
	size++;
    }

    public void heapifyUp(int index) {
	int val = heap[index];
	while (hasParent(index) && parent(index) > val) {
	    swap(getParentIndex(index), index);
	    index = getParentIndex(index);
	}
    }

    public void heapifyDownIterative(int index) {
	if (index < 0 || index > size - 1)
	    throw new IllegalStateException();

	int smallest = heap[index];
	int i = index;
	while (hasLeftChild(index)) {
	    if (left(index) < smallest) {
		smallest = left(index);
		i = index;
	    }
	    if (right(index) < smallest) {
		smallest = right(index);
		i = index;
	    }

	    if (i != index) {
		swap(index, i);
		index = i;
	    }
	}
    }

    public void heapifyDownRecursive(int index) {
	if (index < 0 || index > size - 1)
	    throw new IllegalStateException();

	int smallest = heap[index];
	int i = index;
	if (getLeftChildIndex(index) < size && left(index) < smallest) {
	    smallest = left(index);
	    i = getLeftChildIndex(index);
	}
	if (getRightChildIndex(index) < size && right(index) < smallest) {
	    smallest = right(index);
	    i = getRightChildIndex(index);
	}
	if (index != i) {
	    swap(index, i);
	    heapifyDownRecursive(i);
	}
    }

    public static void heapify(int[] array) {

    }
}
