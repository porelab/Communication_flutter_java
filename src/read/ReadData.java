
package read;

import mypack.Constants;

public class ReadData extends Thread {

	boolean isRead = true;

	public void startRead() {
		isRead = true;
	}

	public void stopRead() {
		isRead = false;
	}

	public void run() {

		while (true) {

			if (isRead) {

				try {
					System.out.println("waiting for mags");
					//String msg=Constants.din.readUTF();
					int n;
					String data="";
					while((n=Constants.din.read())!=-1)
					{
						char c=(char)n;
						
						
						if(c!='\n')
						data=data+c;
					
						
						if(c=='\n')
						{
							CommandParse.parse(data);
							data="";
						}
					}
					
					
					
				//	System.out.println("Msg re : "+msg);
					isRead=false;

				} catch (Exception e) {
					
					System.out.println("Stop Reading!\nError while reading : "+e);
					isRead=false;

				}

			}

		//	System.out.println("Main reading while");
			
		}

	}

}
