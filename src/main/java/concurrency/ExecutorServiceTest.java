package concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class ExecutorServiceTest {
	static int counter = 0;
	static AtomicInteger atomicInt = new AtomicInteger();

	@Test
	public void test() {
		System.out.println(Thread.currentThread().getName());

		testExecutorService(Executors.newCachedThreadPool());
		testExecutorService(Executors.newFixedThreadPool(3));
		testExecutorService(Executors.newSingleThreadExecutor());
		testExecutorService(Executors.newWorkStealingPool());
	}

	@Test
	public void testRaceCondition() throws Exception {
		// 3 threads and 100 updates each
		ExecutorService service = Executors.newFixedThreadPool(3);

		Future<Void> fut1 = service.submit(getCallable());
		Future<Void> fut2 = service.submit(getCallable());
		fut1.get();
		fut2.get();
		System.out.println("Expected count == 300; actual count: " + ExecutorServiceTest.counter + " Atomic Integer: "
				+ atomicInt);
	}

	public Callable<Void> getCallable() {
		return new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				System.out.println(Thread.currentThread().getName());
				for (int i = 0; i < 1_000_000; i++) {
					ExecutorServiceTest.counter++;
					ExecutorServiceTest.atomicInt.incrementAndGet();
				}
				return null;
			}
		};
	}

	public void testExecutorService(ExecutorService s) {
		for (int i = 0; i < 5; i++) {
			s.submit(() -> System.out.println(s.getClass() + " " + Thread.currentThread().getName()));
		}
	}
}
