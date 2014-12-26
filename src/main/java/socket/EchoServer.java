package socket;

import java.net.*;
import java.io.*;

public class EchoServer {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(8888);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 8888.");
			System.exit(1);
		}

		Socket clientSocket = null;
		System.out.println("Waiting for connection.....");

		while (true) {
			try {
				clientSocket = serverSocket.accept();
				EchoServerThread est = new EchoServerThread(clientSocket);
				new Thread(est).start();
			} catch (IOException e) {
				System.err.println("Accept failed.");
				serverSocket.close();
				System.exit(1);
			}
		}
	}

}

class EchoServerThread implements Runnable {
	private Socket clientSocket = null;

	public EchoServerThread(Socket socket) {
		this.clientSocket = socket;
	}

	@Override
	public void run() {
		System.out.println("Connection successful");
		System.out.println("Waiting for input.....");
		try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
					true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));

			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				System.out.println("from client: " + inputLine);
				out.println(inputLine + "!");

				if (inputLine.equals(""))break;
			}

			out.close();
			in.close();
			clientSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
