package machine;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import mypack.Constants;

public class SerialReader implements SerialPortEventListener {

	   InputStream in;
    int ind=0;
     List<Integer> readData= new ArrayList<Integer>();
     
     public SerialReader ( InputStream in )
     {
         this.in = in;
     }
    
     public void serialEvent(SerialPortEvent arg0) {
         int data;
         try
         {
             int len = 0;
             char prev='\0';
            // System.out.println("Reading Started:");
             
             while ( ( data = in.read()) > -1 )
             {
             	
                 if ( data == '\n'&& prev == 'E') {
                    break;
                 }
                 if(len>0 || (data=='\r'&& prev=='\n') )
                 {
                 	readData.add(data);
                 
                 	len++;
                 }
                 prev = (char) data; 
               System.out.print(prev);
                 
                 //System.out.print(new String(buffer,0,len));
             }
     		
             for(int i=1;i<readData.size();)
             {
            
             
             	
             
             			
             			
             		readData.clear();
             		
             }
             	
             
            
            
             readData.clear();
             
         }
         catch ( IOException e )
         {
         	try{
					Constants.serialPort.removeEventListener();
					Constants.serialPort.close();
	            	}
	            	catch(Exception es)
	            	{
	            		System.out.println("serial error : "+es.getMessage());
	            	}
					
		    }             

}

}
