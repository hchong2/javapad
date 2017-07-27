package spring;

import org.springframework.beans.factory.InitializingBean;

public class Soap implements InitializingBean{
	
	Bar bar;
	
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Soap");
	}

	public Bar getBar() {
		return bar;
	}

	public void setBar(Bar bar) {
		this.bar = bar;
	}
	
	
}
