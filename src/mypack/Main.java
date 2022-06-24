package mypack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import read.ReadData;

public class Main {

	public static void main(String[] args) {
		try {
			Constants.serverSocket = new ServerSocket(6667);
			System.out.println("Waiting...");
			Socket s = Constants.serverSocket.accept();
			System.out.println("Connected");
			Constants.dout = new DataOutputStream(s.getOutputStream());
			Constants.din = new DataInputStream(s.getInputStream());

			
			ReadData r=new ReadData();
			r.start();
			

			
			//s.close();
		} catch (Exception e) {
			
			System.out.println("error : "+e);
		}	
	}

}
