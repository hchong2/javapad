package java.trail.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UrlConnectionReader {
    public static void main(String[] args) throws IOException {

	URL oracle = new URL("http://www.oracle.com");
	URLConnection yc = oracle.openConnection();
	BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

	String line;

	while ((line = in.readLine()) != null) {
	    System.out.println(line);
	}

	in.close();
    }
}
