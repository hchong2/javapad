package spring;

import org.springframework.beans.factory.InitializingBean;

public class Bar implements InitializingBean{
	
	String name;

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("Bar class afterPropertiesSet()");
	}
	
}
