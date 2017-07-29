package rss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RssService {

    public static void main(String[] args) throws IOException {

	String urlString = "http://www.wsj.com/xml/rss/3_7455.xml";
	String a = getResponseFromRssFeed(urlString);
	p(a);
    }

    public static String getResponseFromRssFeed(String urlString) throws IOException {
	URL url = new URL(urlString);

	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	conn.setRequestMethod("GET");

	BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

	StringBuilder result = new StringBuilder();
	String line;
	while ((line = rd.readLine()) != null) {
	    result.append(line);
	}
	rd.close();

	return result.toString();
    }

    public static void p(String p) {
	System.out.println(p.toString());
    }
}
