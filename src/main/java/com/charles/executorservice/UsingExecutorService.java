package com.charles.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsingExecutorService {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		ExecutorService executorService = Executors.newFixedThreadPool(10);
		executorService.execute(new Runnable() {
			public void run(){
				System.out.println("This thread is running - " + Thread.currentThread().getName());
			}
		});
		
		executorService.shutdown();
	}

}
