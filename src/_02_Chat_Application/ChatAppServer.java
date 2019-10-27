package _02_Chat_Application;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Scanner;

import _01_Intro_To_Sockets.server.ServerGreeter;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatAppServer extends Thread {
	ServerSocket serverSocket;
	Scanner scanner = new Scanner(System.in);
	public ChatAppServer() {
		try {
			serverSocket = new ServerSocket(8080);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			serverSocket.setSoTimeout(3600000);
		} catch(SocketException se) {
			se.printStackTrace();
		}
	}
	public void run() {
		boolean connected = true;
		while (connected) {
			try {
				Socket socket = serverSocket.accept();
				System.out.println("Client has connected to server.");
				String input = scanner.nextLine();
				System.out.println("Server: " + input);
				
			} catch(SocketTimeoutException ste) {
				System.out.println("Socket Timeout Occurred");
				connected = false;
			} catch(IOException ioe) {
				System.out.println("IO exception occurred.");
				connected = false;
			}
		}
	}
	
	public static void main(String[] args) {
		(new ChatAppServer()).start();
	}
}
