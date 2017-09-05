package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) {

	String hostname = "hostname";
	int portNumber = 789;

	try (Socket socket = new Socket(hostname, portNumber);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));) {

	    String input;
	    while ((input = stdIn.readLine()) != null) {
		out.println(input);
		System.out.println("echo: " + in.readLine());
	    }
	} catch (UnknownHostException e) {
	    System.err.println("Bad host");
	    System.exit(1);
	} catch (IOException e) {
	    System.err.println("IO Exception");
	    System.exit(1);
	}
    }

}
