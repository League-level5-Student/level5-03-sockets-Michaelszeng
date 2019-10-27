package _02_Chat_Application;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatAppClient {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String ip = "192.168.7.194";
		int portNum = 8080;
		try {
			Socket socket = new Socket(ip, portNum);
			String input = scanner.nextLine();
			System.out.println("Client: " + input);
		} catch(IOException io) {
			io.printStackTrace();
		}
	}
}
