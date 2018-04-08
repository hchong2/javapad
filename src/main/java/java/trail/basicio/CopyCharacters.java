package java.trail.basicio;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyCharacters {
    public static void main(String[] args) throws IOException {

	FileReader inStream = null;
	FileWriter outStream = null;

	try {
	    inStream = new FileReader("xanadu.txt");
	    outStream = new FileWriter("characteroutput.txt");

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
