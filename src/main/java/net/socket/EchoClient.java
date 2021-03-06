package net.socket;

import java.io.*;
import java.net.*;

public class EchoClient {
	public static void main(String[] args) {

		String serverHostname = "127.0.0.1";

		if (args.length > 0){
			serverHostname = args[0];
		}
		System.out.println("Attemping to connect to host " + serverHostname + " on port 8080.");

		Socket echoSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		try {
			echoSocket = new Socket(serverHostname, 8080);
			echoSocket.setSoTimeout(2000);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String userInput;
	
			System.out.print("input: ");
			while ((userInput = stdIn.readLine()) != null) {
				if(userInput.length()==0)break;
				out.println(userInput);
				System.out.println("from server: " + in.readLine());
				System.out.print("input: ");
			}
	
			out.close();
			in.close();
			stdIn.close();
			echoSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
