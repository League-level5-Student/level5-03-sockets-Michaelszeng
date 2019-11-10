package _02_Chat_Application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class ChatAppClient {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String ip = "192.168.7.126";
		int portNum = 8080;
		try {
			Socket socket = new Socket(ip, portNum);
			while(socket.isConnected()) {
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				String input = JOptionPane.showInputDialog("To Server: ");
				System.out.println("Client: " + input);
				dos.writeUTF(input);
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				System.out.println("Server: " + dis.readUTF());
			}
		} catch(IOException io) {
			io.printStackTrace();
		}
	}
}
