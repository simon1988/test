package socket;

import java.io.*;
import java.net.*;

public class Client{
	Socket socket;
	BufferedReader in;
	PrintWriter out;

	public Client(){
		try
		{
			socket = new Socket("127.0.0.1", 8888);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			BufferedReader line = new BufferedReader(new InputStreamReader(System.in));

			out.println("abc");
			System.out.println(in.readLine());
			line.close();
			out.close();
			in.close();
			socket.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		new Client();
	}
} 
