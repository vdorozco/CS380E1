

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
import java.io.OutputStream;
import java.io.PrintStream;

public class EchoClient {

	public static void main(String[] args) throws Exception {
		
		try (Socket socket = new Socket("localhost", 22222)) {
			
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			Scanner kb = new Scanner(System.in);
			OutputStream os = socket.getOutputStream();
			PrintStream out = new PrintStream(os);
			System.out.print("Client> ");
			String message;
			
			while(!(message = kb.nextLine()).equals("exit")){
				
				out.println(message);
				System.out.println("Server> " + br.readLine());
				System.out.print("Client> ");
			}	
		}

	}

}
