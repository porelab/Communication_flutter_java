package write;

import mypack.Constants;

public class SendData {

	public static void sendData(SendObj data) {
		try {

			if (data != null)
				Constants.dout.write(new String("data:" + data.toString()).getBytes());
 
		} catch (Exception e) {

			System.out.println("error in sending data to software : " + e);
		}

	}

	
	public static void sendData(String msg) {

		try {

			if (msg != null)
				Constants.dout.write(new String(msg).getBytes());

		} catch (Exception e) {

			System.out.println("error in sending data to software : " + e);
		}

	}
}