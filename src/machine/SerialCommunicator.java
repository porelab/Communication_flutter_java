package machine;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mypack.Constants;

// for communication of PC and MCU

public class SerialCommunicator {

	int ind = 0;

	public SerialCommunicator() {

	}

	public void connect(String portName) throws Exception {
		CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		if (portIdentifier.isCurrentlyOwned()) {
			System.out.println("Error: Port is currently in use1");

			if (Constants.commPort != null) {
				Constants.commPort.close();
			}

		}
		setUpConnect(portIdentifier);

	}

	void setUpConnect(CommPortIdentifier portIdentifier) {

		try {

			Constants.commPort = portIdentifier.open(this.getClass().getName(), 10000);
			if (Constants.commPort instanceof SerialPort) {
				Constants.serialPort = (SerialPort) Constants.commPort;
				Constants.serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
						SerialPort.PARITY_NONE);

				Constants.in = Constants.serialPort.getInputStream();
				Constants.out = Constants.serialPort.getOutputStream();
				Constants.sr = new SerialReader(Constants.in);

				Constants.serialPort.addEventListener(Constants.sr);
				Constants.serialPort.notifyOnDataAvailable(true);

				Mycommand.hardReset();
				 

			} else {
				System.out.println("Error: Only serial ports are handled by this example.");
			}
		} catch (Exception e) {

			System.out.println("Error in serial communicator : " + e.getMessage());

			if (Constants.commPort != null) {
				System.out.println("error it is not null");

				Constants.commPort.close();
				Constants.clearSerialData();
				setUpConnect(portIdentifier);

			} else {
				setUpConnect(portIdentifier);
				System.out.println("error but it is null");
			}

		}
	}

}
