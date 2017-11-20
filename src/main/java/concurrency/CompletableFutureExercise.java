package concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class CompletableFutureExercise {

	/**
	 * http://www.deadcoderising.com/java8-writing-asynchronous-code-with-completablefuture/
	 * 
	 * http://www.baeldung.com/java-completablefuture
	 * 
	 * https://www.callicoder.com/java-8-completablefuture-tutorial/
	 * 
	 * CompletableFuture is used for asynchronous programming in Java. Asynchronous programming is a
	 * means of writing non-blocking code by running a task on a separate thread than the main
	 * application thread and notifying the main thread about its progress, completion or failure.
	 */

	@Test
	public void simpleTest() throws Exception {
		{
			CompletableFuture<String> cf = new CompletableFuture<>();
			cf.complete("Done");

			// get blocks until future is complete
			String result = cf.get();
			System.out.println(result);
		}
	}

	@Test
	public void runAsyncTest() throws Exception {
		{
			// Use runAsync to use Runnable to do something without returning a value
			CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
				@Override
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						throw new IllegalStateException(e);
					}
					System.out.println("I'm running in a separate thread");
					System.out.println(Thread.currentThread());
				}
			});
			future.get();
		}
		{
			// Using lambda expression
			CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					throw new IllegalStateException();
				}
				System.out.println("Running on separate thread");
				System.out.println(Thread.currentThread());
			});
			future.get();
		}
	}

	@Test
	public void supplyAsyncTest() throws Exception {
		{
			// Use supplyAsync to return value CompletableFuture
			CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					throw new IllegalStateException();
				}
				return "Done-2";
			});
			System.out.println(future.get());
		}
	}

	@Test
	public void thenApplyTest() throws Exception {
		{
			// Create a CompletableFuture
			CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					throw new IllegalStateException();
				}
				return "charles";
			});

			// Attach a callback
			CompletableFuture<String> greetingFuture = future.thenApply(name -> {
				return "Hello " + name;
			});
			System.out.println(greetingFuture.get());
		}
		{
			// Create a CompletableFuture
			CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					throw new IllegalStateException();
				}
				return "charles";
			}).thenApply(name -> {
				// Executed in the same thread where the supplyAsync() task is executed
				return "Hello " + name;
			}).thenApply(input -> {
				return input + ", Welcome to Mars";
			});

			System.out.println(future.get());
		}
		{
			CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					throw new IllegalStateException();
				}
				return "charles";
			}).thenApplyAsync(name -> {
				// Executed in a different thread from ForkJoinPool.commonPool()
				return "Hello " + name;
			}).thenApplyAsync(input -> {
				return input + ", Welcome to Mars";
			});
		}
	}

	@Test
	public void thenAcceptTest() throws Exception {
		// The thenAccept callback doesn't return anything
		CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
			return "Charles";
		}).thenAccept(input -> {
			System.out.println("thenAccept:" + input);
		});

		future.get();
	}

	@Test
	public void thenRunTest() throws Exception {
		CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
			return "Charles";
		}).thenRun(() -> {
			System.out.println("This has no visibility into the CompletableFuture result");
		});

		future.get();
	}

	public static CompletableFuture<String> getNewFuture() {
		return CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException();
			}
			return "foo";
		});
	}

}
