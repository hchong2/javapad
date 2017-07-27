package jetty;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.ExecutorThreadPool;

public class Foo {

	public static void main(String[] args) throws Exception {
		
		int queueSize = 200;
		int minThreads = 5;
		int maxThreads = 10;
		long maxIdleTime = 30000;
		
		Server server = new Server();
		LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(queueSize);
		
		ExecutorThreadPool pool = new ExecutorThreadPool(minThreads, maxThreads, maxIdleTime, TimeUnit.MILLISECONDS, queue);
		server.setThreadPool(pool);
		
		server.start();
		
	}

}
