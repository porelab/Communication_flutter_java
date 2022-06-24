package mypack;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;

import gnu.io.CommPort;
import gnu.io.SerialPort;
import machine.SerialReader;

public class Constants {

	public static DataOutputStream dout;
	public static DataInputStream din;
	public static ServerSocket serverSocket;

	public static OutputStream out;
	public static SerialPort serialPort;
	public static CommPort commPort;
	public static InputStream in;
	public static SerialReader sr;
	
	
	public static void clearSerialData() {
		out=null;
		serialPort=null;
		commPort=null;
		in=null;
		sr=null;
	}

}
