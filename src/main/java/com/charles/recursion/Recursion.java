package com.charles.recursion;

public class Recursion {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		System.out.println(recursiveSeriesAddition(5));
//		System.out.println(recursiveSeriesAddition(10));
		
//		System.out.println(recursiveSeriesSubtraction(5));
//		System.out.println(recursiveSeriesSubtraction(10));
		
		System.out.println(tailRecursiveAddition(10, 0));
	}
	
	public static int stackDepth = 0;
	public static int recursiveSeriesAddition(int i){
//		Thread.dumpStack();
		if(i == 1){
			return 1;
		}
		return i + recursiveSeriesAddition(i-1);
	}
	
	public static int tailRecursiveAddition(int i, int acc){
//		Thread.dumpStack();
		System.out.println(++stackDepth);
		if(i == 0){
			return acc;
		}
		if(i == 1){
			return i + acc;
		}
		
		return tailRecursiveAddition(i-1, i+acc);
	}
	
	public static int recursiveSeriesSubtraction(int i){
		if(i == 1){
			return -1;
		}
		return -i + recursiveSeriesSubtraction(i-1);
	}
	
	public static int recursiveSeriesMultiplication(int i){
		if(i == 1){
			return 1;
		}
		
		return i * recursiveSeriesMultiplication(i-1);
	}

}
