package com.charles.iface;

public class InterfaceExample
{
	public static class Iface1Impl implements Iface1{
		public void sayHello() {
			System.out.println("Hello :" + this);
		}
	}
	
	public static class Iface2Impl implements Iface2{
		public void doSomething() {
			System.out.println("something: " + this);
		}
	}
	public static class MultipleInterfaceImpl implements Iface1, Iface2
	{
		public void sayHello()
		{
			System.out.println("say hello: " + this);
		}

		public void doSomething()
		{
			System.out.println("doingSomething: " + this);
		}
	}

	public static void main(String[] args)
	{
		Iface1 i1 = new Iface1Impl();
		Iface2 i2 = new Iface2Impl();
		
		Iface1 i1_ = new MultipleInterfaceImpl();
		Iface2 i2_ = new MultipleInterfaceImpl();
		
		i1.sayHello();
		i2.doSomething();
		
		i1_.sayHello();
		i2_.doSomething();
	}
}
