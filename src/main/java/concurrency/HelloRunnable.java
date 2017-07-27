package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HelloRunnable implements Runnable{
	static List<String> threadNames = new ArrayList<String>();
	static int count = 0;
	public void run(){
		System.out.println("Hello from a thread!");
	}
	
	public static void main(String [] args) throws Exception{
		(new Thread(new HelloRunnable())).start();
		(new Thread(new HelloRunnable())).start();
		
		Thread t = new Thread(new Runnable(){
			public void run(){
				System.out.println("This works too");
				threadNames.add(Thread.currentThread().getName());
			}
		});
//		System.out.println(t.toString());
//		System.out.println(t.getId());
//		System.out.println(t.getName());
//		System.out.println(t.getStackTrace());
//		System.out.println(t.getState());
//		System.out.println(t.getPriority());
//		System.out.println(t.getThreadGroup());
		
//		t.start();
		Thread []  threads = new Thread [10];
		for(int i=0; i<10; i++){
			threads[i] = (new Thread(new Runnable(){
			public void run(){
				System.out.println("This works too " + this);
				threadNames.add(Thread.currentThread().getName());

			}
		}));
		}
		
		for(int i=0;i<10; i++){
			threads[i].run();
		}
		
		while(true){
			Thread.sleep(2000);
			if(threadNames.size() > count){
				System.out.println(threadNames);
				System.out.println();
				count = threadNames.size();
			}
		}
	}

}
