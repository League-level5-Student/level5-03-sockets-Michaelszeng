package _02_Chat_Application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Scanner;

import javax.swing.JOptionPane;

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
		Socket socket;
		boolean connected = true;
		while (connected) {
			try {
				socket = serverSocket.accept();
				while (true) {
					DataInputStream dis = new DataInputStream(socket.getInputStream());
					System.out.println("Client: " + dis.readUTF());
					DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
					String input = JOptionPane.showInputDialog("To Client: ");
					System.out.println("Server: " + input);
					dos.writeUTF(input);
				}
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
