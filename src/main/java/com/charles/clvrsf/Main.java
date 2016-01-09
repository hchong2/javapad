package com.charles.clvrsf;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		String s = FileUtils.readFileToString(new File("/home/charles/Desktop/beans"));
		
		String[] sp = s.split(",");
		for(int i=0; i<sp.length; i++){
			System.out.println(sp[i]);
		}
	}
}
