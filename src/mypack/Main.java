package mypack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import machine.Mycommand;
import machine.SerialCommunicator;
import read.ReadData;

public class Main {

	public static void main(String[] args) {
		try {
			
			

			SerialCommunicator sr = new SerialCommunicator();
			sr.connect("COM3");
			
			Mycommand.sendAdcEnableBits("11111",5000);
			Mycommand.startADC(9000);
			
			
			
//			Constants.serverSocket = new ServerSocket(6667);
//			System.out.println("Waiting...");
//			Socket s = Constants.serverSocket.accept();
//			System.out.println("Connected");
//			Constants.dout = new DataOutputStream(s.getOutputStream());
//			Constants.din = new DataInputStream(s.getInputStream());
//
//			
//			ReadData r=new ReadData();
//			r.start();
//			

			
			
			//s.close();
		} catch (Exception e) {
			
			System.out.println("error : "+e);
		}	
	}

}
