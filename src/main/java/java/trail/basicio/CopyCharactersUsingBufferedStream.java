package java.trail.basicio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyCharactersUsingBufferedStream {
    public static void main(String[] args) throws IOException {

	BufferedReader inStream = null;
	BufferedWriter outStream = null;

	try {
	    inStream = new BufferedReader(new FileReader("xanadu.txt"));
	    outStream = new BufferedWriter(new FileWriter("characteroutput.txt"));

	    int c;

	    long start = System.nanoTime();

	    while ((c = inStream.read()) != -1) {
		outStream.write(c);
	    }

	    long end = System.nanoTime();

	    System.out.println((double) (end - start) / 1000000);

	} finally {
	    if (inStream != null) {
		inStream.close();
	    }
	    if (outStream != null) {
		outStream.close();
	    }
	}
    }
}
