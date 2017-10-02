package concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;

public class ConcurrencyExamples {

    static ExecutorService pool = Executors.newFixedThreadPool(1);

    public static void main(String[] args) throws Exception {

	// Simple Runnable and Thread examples
	Runnable task = () -> {
	    String threadName = Thread.currentThread().getName();
	    System.out.println("Hello " + threadName);
	};
	task.run();

	Thread thread = new Thread(task);
	thread.start();

	System.out.println("Done");

	divider();

	// Example 2
	Runnable task2 = () -> {
	    try {
		String name = Thread.currentThread().getName();
		long t1 = System.currentTimeMillis();
		System.out.println("Thread name: " + name + " " + t1 / 1000);

		TimeUnit.SECONDS.sleep(1);

		long t2 = (System.currentTimeMillis() - t1) / 1000;
		System.out.println("Repeating thread name again: " + name + " " + t2);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	};

	Thread thread2 = new Thread(task2);
	thread2.start();

	divider();

	// ExecutorService example
	ExecutorService executor = Executors.newSingleThreadExecutor();
	executor.submit(() -> {
	    String threadName = Thread.currentThread().getName();
	    System.out.println("Hello " + threadName);
	});

	try {
	    p("Attempting to shutdown executor");
	    executor.shutdown();
	    executor.awaitTermination(5, TimeUnit.SECONDS);
	} catch (InterruptedException e) {
	    p("Shutdown interrupted");

	} finally {
	    if (!executor.isTerminated()) {
		p("Canceling unfinished tasks");
	    }
	    executor.shutdownNow();
	    p("Shutdown finished");
	}

	// Callable example
	Callable<Integer> callableTask = () -> {
	    try {
		TimeUnit.SECONDS.sleep(1);
		return 123;
	    } catch (InterruptedException e) {
		throw new IllegalStateException("Callable task interrupted", e);
	    }
	};

	ExecutorService newService = Executors.newFixedThreadPool(1);
	Future<Integer> future = newService.submit(callableTask);

	p("Future done? " + future.isDone());

	Integer result = future.get();

	p("Future done? " + future.isDone());

	p("Result: " + result);

	divider();

	// Future with timeout
	ExecutorService executor3 = Executors.newFixedThreadPool(1);
	Future<Integer> f = executor3.submit(() -> {
	    try {
		TimeUnit.SECONDS.sleep(2);
		return 123;
	    } catch (InterruptedException e) {
		throw new IllegalStateException("Task interrupted", e);
	    }
	});
	try {
	    f.get(1, TimeUnit.SECONDS);
	} catch (Exception e) {
	    p(e);
	}

	divider();

	// ExecutorService invokeAll
	ExecutorService workStealExecutor = Executors.newWorkStealingPool();
	List<Callable<String>> callables = Arrays.asList(() -> "task1", () -> "task2", () -> "task3");

	workStealExecutor.invokeAll(callables).stream().map(fut -> {
	    try {
		return fut.get();
	    } catch (Exception e) {
		throw new IllegalStateException();
	    }
	}).forEach(System.out::println);

	// ExecutorService invokeAny
	ExecutorService executor4 = Executors.newWorkStealingPool();
	List<Callable<String>> callables2 = Arrays.asList(callable("task1", 2), callable("task2", 1),
		callable("task3", 3));

	String result2 = executor4.invokeAny(callables2);
	p(result2);

	divider();
	// ScheduledExecutor example
	ScheduledExecutorService executor5 = Executors.newScheduledThreadPool(1);
	Runnable task5 = () -> p("Scheduling: " + System.nanoTime());
	ScheduledFuture<?> future5 = executor5.schedule(task5, 3, TimeUnit.SECONDS);

	TimeUnit.MILLISECONDS.sleep(1337);

	long remainingDelay = future5.getDelay(TimeUnit.MILLISECONDS);
	p("Remaining deplay: " + remainingDelay);

	// Why is this call necessary? ExecutorService needs to be stopped
	// manually.
	System.exit(1);
    }

    public static Callable<String> callable(String result, long sleepSeconds) {
	return () -> {
	    TimeUnit.SECONDS.sleep(sleepSeconds);
	    return result;
	};
    }

    public static Future<String> startDownloading(final URL url) throws IOException {
	return pool.submit(new Callable<String>() {
	    @Override
	    public String call() throws Exception {
		try (InputStream input = url.openStream()) {
		    return IOUtils.toString(input, StandardCharsets.UTF_8);
		}
	    }
	});
    }

    public static Future<Integer> calculate(final Integer input) {
	return pool.submit(() -> {
	    Thread.sleep(1000);
	    return input * input;
	});
    }

    public static void divider() {
	p("");
	p("");
	p("");
    }

    public static void p(Object o) {
	System.out.println(o.toString());
    }
}
