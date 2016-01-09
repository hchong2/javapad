package com.charles.concurrency;

public class Foo {
	
	public void run(){
		System.out.println("Will this work?");
	}
	
	public static void main(String [] args){
		
//		(new Thread(new Foo())).start();
	}
}
