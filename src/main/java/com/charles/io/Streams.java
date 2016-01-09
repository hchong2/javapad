package com.charles.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Streams {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		FileInputStream in = null;
//        FileOutputStream out = null;

        try {
            in = new FileInputStream("declarationOfIndy.txt");
//            out = new FileOutputStream("outagain.txt");
            int c;

            while ((c = in.read()) != -1) {
            	System.out.println(c + "\t" +  Integer.toHexString(c));
//                out.write(c);
            }
        } finally {
            if (in != null) {
                in.close();
            }
//            if (out != null) {
//                out.close();
//            }
        }

	}

}
