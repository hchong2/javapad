package concurrency;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

public class MsLunch {

	private long c1 = 0L;
	private long c2 = 0L;
	
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	public void inc1(){
		synchronized(lock1){
			c1++;
		}
	}
	
	public void inc2(){
		synchronized(lock2){
			c2++;
		}
	}
	
	
	
	public static void main(String [] args){
		Bag b = new HashBag<Integer>();
	}
}
