package com.charles.reddit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

public class Main {

	public static void main(String[] args) throws URISyntaxException, ClientProtocolException, IOException {

		String hot = "https://www.reddit.com/r/politics/.rss";
		String recent = "https://www.reddit.com/r/politics/new/.rss";
		
		URI uri = new URIBuilder()
				.setScheme("http")
				.setHost("www.reddit.com")
				.setPath("/r/politics/.rss")
				.build();
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(uri);
		System.out.println(request.getURI());
		
		request.addHeader("User-Agent", "Mozilla/5.0");
		
		HttpResponse response = client.execute(request);
		
		System.out.println(response.getStatusLine().getStatusCode());
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		
		StringBuffer result = new StringBuffer();
		String line = "";
		while((line = rd.readLine()) != null){
			result.append(line);
		}
		System.out.println(result);
		
	}

}
