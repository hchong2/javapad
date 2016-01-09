package com.charles.spring;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

	public static void main(String [] args) throws Exception{
		
		File f = new File(".");
		System.out.println(f.getAbsolutePath());
		
		String appCtxPath = "./src/main/appCtx/appCtx.xml";
		
		ApplicationContext ctx = new FileSystemXmlApplicationContext(appCtxPath);
		System.out.println("Main Class");
	}
}
