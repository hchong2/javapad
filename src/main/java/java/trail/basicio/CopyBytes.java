package java.trail.basicio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyBytes {
    public static void main(String[] args) throws IOException, InterruptedException {

	FileInputStream in = null;
	FileOutputStream out = null;

	try {
	    in = new FileInputStream("xanadu.txt");
	    out = new FileOutputStream("outagain.txt");

	    int c;

	    long start = System.nanoTime();
	    while ((c = in.read()) != -1) {
		out.write(c);
	    }
	    long end = System.nanoTime();
	    System.out.println((end - start) / 1000);
	} finally {
	    if (in != null) {
		in.close();
	    }
	    if (out != null) {
		out.close();
	    }
	}
    }

    public static void logTime(long start, long end, String message) {

    }
}
