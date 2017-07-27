package datastructures;

public class Array {

	/**
	 * @param args
	 */
	
	String changeme = "hi";
	static {
		String greetings = "Hello world";
		System.out.println("hello");
	}
	static {
		System.out.println("these must be in order");
	}
	{
		System.out.println("yoyoyo");
		changeme = "bye";
	}
	
	public Array(){
//		System.out.println(this.greetings);
		System.out.println(changeme);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] intArray = new int[10];
		
//		System.out.println(intArray.length);
//		
//		for(int i=0; i < intArray.length; i++){
//			System.out.println(intArray[i]);
//		}
//		
//		Integer[] a = new Integer[10];
//		for(int i=0; i < intArray.length; i++){
//			System.out.println(a[i]);
//		}
		
		System.out.println( 5 +( 5 << 1));
		
		new Array();
	}
	
	
	static {
		System.out.println("these must be in order 2");
	}
}
