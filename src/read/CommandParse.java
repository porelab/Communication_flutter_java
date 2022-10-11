package read;

import machine.Mycommand;
import machine.SerialCommunicator;
import write.SendData;

public class CommandParse {

	public static void parse(String msg) {
		try {

			String[] msgs = msg.split(",");

			if (msg.startsWith("normal")) {
				System.out.println("Msg : " + msgs[1]);
				SendData.sendData("hello");
			} else if (msg.startsWith("hardReset")) {
				Mycommand.hardReset();
			} else if (msg.startsWith("refresh")) {
			//	Mycommand.refresh();
			} else if (msg.startsWith("connect")) {
				SerialCommunicator sr = new SerialCommunicator();
				sr.connect(msgs[1]);
			} else if (msg.startsWith("setLacthing")) {

				Mycommand.setLacthing(msgs[1], Integer.parseInt(msgs[2]));

			} else if (msg.startsWith("setStability")) {

				Mycommand.setStability(Integer.parseInt(msgs[1]), Integer.parseInt(msgs[2]), Integer.parseInt(msgs[3]));

			} else if (msg.startsWith("systemReset")) {

				Mycommand.systemReset();

			} else if (msg.startsWith("valveOn")) {

				Mycommand.valveOn(msgs[1].charAt(0), Integer.parseInt(msgs[2]));

			} else if (msg.startsWith("valveOff")) {

				Mycommand.valveOff(msgs[1].charAt(0), Integer.parseInt(msgs[2]));
			} else if (msg.startsWith("getADCSingleVal")) {

				Mycommand.getADCSingleVal(Integer.parseInt(msgs[1]), Integer.parseInt(msgs[2]));

			} else if (msg.startsWith("setDACIncrement")) {
				Mycommand.setDACIncrement(Integer.parseInt(msgs[1]), Integer.parseInt(msgs[2]),
						Integer.parseInt(msgs[3]), Integer.parseInt(msgs[4]), Integer.parseInt(msgs[5]));

			} else if (msg.startsWith("setDACValue")) {

				Mycommand.setDACValue(msgs[1].charAt(0), Integer.parseInt(msgs[2]), Integer.parseInt(msgs[3]));

			} else if (msg.startsWith("startADC")) {

				Mycommand.startADC(Integer.parseInt(msgs[1]));

			} else if (msg.startsWith("stopADC")) {

				Mycommand.stopADC(Integer.parseInt(msgs[1]));

			} else if (msg.startsWith("setDelay")) {

				Mycommand.setDelay(Integer.parseInt(msgs[1]), Integer.parseInt(msgs[2]));

			} else if (msg.startsWith("sendAdcEnableBits")) {

				Mycommand.sendAdcEnableBits(msgs[1], Integer.parseInt(msgs[1]));

			}
		} catch (Exception e) {

			System.err.println("Error while parsing: " + msg + " : " + e.getMessage());
			e.printStackTrace();

		}

	}

}
