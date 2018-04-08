package datastructure.heap;

import org.junit.Test;

public class IntMinHeapTest {

    @Test
    public void test() {

	IntMinHeap heap = new IntMinHeap();
	heap.add(10);
	System.out.println(heap);
	heap.add(1);
	System.out.println(heap);
	heap.add(2);
	System.out.println(heap);
	heap.poll();
	System.out.println(heap);
    }

}
