package io;

import java.io.BufferedInputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;

public class Streams {

    public static void main(String[] args) throws IOException {
	readFile2();
    }

    public static void readFile() {
	FileInputStream in = null;

	try {
	    in = new FileInputStream("declarationOfIndependence.txt");
	    int c;

	    while ((c = in.read()) != -1) {
		System.out.print((char) c);
	    }
	} catch (IOException e) {

	} finally {
	    if (in != null) {
		try {
		    in.close();
		} catch (IOException e) {
		    System.out.print("Error: " + e);
		}
	    }
	}
    }

    public static void readFile2() {
	BufferedInputStream in = null;

	try {
	    in = new BufferedInputStream(new FileInputStream("declarationOfIndependence.txt"));
	    int c;

	    while ((c = in.read()) != -1) {
		System.out.print((char) c);
	    }
	} catch (IOException e) {

	} finally {
	    if (in != null) {
		try {
		    in.close();
		} catch (IOException e) {
		    System.out.print("Error: " + e);
		}
	    }
	}
    }

    public static void fd() {
	FileDescriptor fd = new FileDescriptor();
    }

}
