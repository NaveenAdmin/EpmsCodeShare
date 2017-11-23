 /*
Author		    :Raj kumar
creation Date	:14-11-2015
Descripition	:HPCL EMS application device page for accessing the application.
Modified Date	:
Modified By	    :
*/

package com.hpcl.socket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class SocketClient {

    private BufferedReader in;
    private PrintWriter out;

    public void sendCommandToDevice(String command,String IP,int Port){
    	String hostName = IP;
        int portNumber = Port;

        Socket socket;
		try {
			socket = new Socket(hostName, portNumber);	
        in = new BufferedReader(
             new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println(command);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
    	
    }
    public void sendCommandToDevicePing(String command,String IP,int Port){
    	String hostName = IP;
        int portNumber = Port;

        Socket socket;
		try {
			socket = new Socket(hostName, portNumber);	
        in = new BufferedReader(
             new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println(command);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
    	
    }


}
