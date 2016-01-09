package com.charles.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Foo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		FileInputStream fin = new FileInputStream("foo.txt");
		
		FileChannel fc = fin.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		fc.read(buffer);
	}

}
