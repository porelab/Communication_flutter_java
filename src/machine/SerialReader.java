package machine;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import mypack.Constants;
import write.SendData;
import write.SendObj;

public class SerialReader implements SerialPortEventListener {

	InputStream in;
	int ind = 0;
	List<Integer> readData = new ArrayList<Integer>();

	public SerialReader(InputStream in) {
		this.in = in;
	}

	public void serialEvent(SerialPortEvent arg0) {
		int data;
		try {
			int len = 0;
			char prev = '\0';
			// System.out.println("Reading Started:");

			while ((data = in.read()) > -1) {

				if (data == '\n' && prev == 'E') {
					break;
				}
				if (len > 0 || (data == '\r' && prev == '\n')) {
					readData.add(data);

					len++;
				}
				prev = (char) data;
				System.out.print(prev);

				// SendData.sendData(prev+"");
				// System.out.print(new String(buffer,0,len));
			}

			for (int i = 1; i < readData.size();) {

				if (readData.get(i) == 'F' && readData.get(i + 1) == (int) 'M' && readData.get(i + 2) == (int) 'A') {
					double pr = 0, fl = 0;
					List<Integer> reading = getAdcData(readData);
					
					SendData.sendData(new SendObj(getAdcDataMap(readData)));
					System.out.println("Reading : " + reading);

					System.out.println("" + reading);

					System.out.println("Pr2 : " + pr + "\nFc : " + fl);

				}

				readData.clear();

			}

			readData.clear();

		} catch (IOException e) {
			try {
				Constants.serialPort.removeEventListener();
				Constants.serialPort.close();
			} catch (Exception es) {
				System.out.println("serial error : " + es.getMessage());
			}

		}

	}

	List<Integer> getAdcData(List<Integer> data) {
		List<Integer> d = new ArrayList<Integer>();

		System.out.println("READ .... ");
		for (int i = 4; i < 49; i = i + 3) {
			d.add(getIntFromBit(data.get(i), data.get(i + 1), data.get(i + 2)));

		}
		System.out.println("READ DONE ..." + d.size());
		System.out.println("Adc Data :" + d);
		return d;
	}
	
	Map<String,Object> getAdcDataMap(List<Integer> data) {
		Map<String,Object> mapData = new LinkedHashMap();

		int index=0;
		System.out.println("READ .... ");
		for (int i = 4; i < 49; i = i + 3) {
			mapData.put(""+index, getIntFromBit(data.get(i), data.get(i + 1), data.get(i + 2)));
			index++;
		}
//		System.out.println("READ DONE ..." + d.size());
//		System.out.println("Adc Data :" + d);
		return mapData;
	}

	int getIntFromBit(int a1, int a2, int a3) {
		System.out.println(a1 + " : " + a2 + ": " + a3);
		int a = 0;

		a = a1 << 16;
		a2 = a2 << 8;
		a = a | a2;
		a = a | a3;

		return a;
	}

}
