package spring;

import org.springframework.beans.factory.InitializingBean;

public class Foo implements InitializingBean {

    public void afterPropertiesSet() throws Exception {
	// TODO Auto-generated method stub
	System.out.println("foo class afterPropertiesSet()");
    }

}
