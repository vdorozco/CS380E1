package cpp.edu.cs40;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public final class EchoServer {

	public static void main (String[] args) throws Exception{
		
		try (ServerSocket serverSocket = new ServerSocket(22222)){
			
			while(true){
				
				Socket socket = serverSocket.accept();
				Runnable client = () -> {
					
					try{
						
						String address = socket.getInetAddress().getHostAddress();
						System.out.printf("Client connected: %s%n", address);
						OutputStream os = socket.getOutputStream();
						PrintStream out = new PrintStream(os);
						InputStream is = socket.getInputStream();
						InputStreamReader isr = new InputStreamReader(is, "UTF-8");
						BufferedReader br = new BufferedReader(isr);
						String message;
						
						while((message = br.readLine()) !=null){
							
							out.println(message);
						}
						
						System.out.printf("Client disconnected: %s%n", address);
						socket.close();
					}
					catch(Exception e ){
						
					}
				};
				
				Thread clientThread = new Thread(client);
				clientThread.start();
			}
		}
	}
}
