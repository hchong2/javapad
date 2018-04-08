package java.trail.basicio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CopyLines {
    public static void main(String[] args) throws IOException {

	BufferedReader inStream = null;
	PrintWriter outStream = null;

	try {
	    inStream = new BufferedReader(new FileReader("xanadu.txt"));
	    outStream = new PrintWriter(new FileWriter("characteroutputbyline.txt"));

	    String l;
	    while ((l = inStream.readLine()) != null) {
		outStream.println(l);
	    }
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
