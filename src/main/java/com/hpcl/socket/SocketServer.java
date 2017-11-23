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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.hpcl.dao.SocketDao;
import com.hpcl.dao.SocketDaoImpl;
import com.hpcl.persistence.AppParameters;


public class SocketServer {
	
	 static HashMap<Integer, Integer> alert=new HashMap<Integer,Integer>();
     static String devVolt="0.0";
     static float maxVolt=7;
		String devicid="4";
		static boolean checkPrev=false;
    /**
     * Application method to run the server runs in an infinite loop
     * listening on port 9898.  When a connection is requested, it
     * spawns a new thread to do the servicing and immediately returns
     * to listening.  The server keeps a unique client number for each
     * client that connects just to show interesting logging
     * messages.  It is certainly not necessary to do this.
     */
    public static void main(String[] args) throws Exception {
    	
        System.out.println("Server is running.");
        System.out.println("Waiting for client request.....");
        int clientNumber = 0;
        ServerSocket listener = new ServerSocket(9898);
        try {
            while (true) {
                new Running(listener.accept(), clientNumber++).start();
            }
        } finally {
            listener.close();
        }
    }

    private static class Running extends Thread {
        private Socket socket;
        private int clientNumber;
        List<Integer> tempCheck;
        public Running(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
            log("New connection with client# " + clientNumber + " at " + socket);
        }

        public void run() {
            try {

                // Decorate the streams so we can send characters
                // and not just bytes.  Ensure output is flushed
                // after every newline.
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // Send a welcome message to the client.
                out.println("Server is running ");

                // Get messages from the client, line by line; return them and store into database
                while (true) {
                    String input = in.readLine();
                    System.out.println("Message From Client "+input);
                    out.println("Message From server "+input);
                    /*System.out.println("input size:"+input.length());*/
                    if(input.length()>1){
                        String originalData=input.substring(1, input.length()-1);
                        System.out.println(originalData);
                        StringTokenizer st = new StringTokenizer(originalData,",");
    	        		HashMap<Integer, String> tokens=new HashMap<Integer,String>();
    	        		
    	        		int alertCount=1;
    	        		int inc=1;
    	        		while (st.hasMoreElements()) {
    	        			tokens.put(inc, st.nextElement().toString());
    	        			inc++;
    	        		}
    	        		System.out.println("tokens--> "+tokens);
    	        		System.out.println("size--> "+tokens.size());
    	        		
    	        		if(tokens.size()>2){
    	        			if(tokens.get(2).contains(".")){
    	        				/*System.out.println("Alert Message Sent");
    	        				System.out.println("Invalid Data");*/
    	        			}else{
        	        			int numOfEarthPits=tokens.size()-2;
        	        			/*System.out.println("Number Of Earthpits connected "+numOfEarthPits);*/
        	        			for(int k=3;k<=tokens.size();k++){
        	        				if(Float.valueOf(tokens.get(k).toString())>maxVolt){
        	        					System.out.println(tokens.get(k).toString());
        	        					System.out.println("Alert Message Sent");
        	        					System.out.println("MaxVolts Exceeded");
        	        				}
        	        			}
        	        			System.out.println("Size OF Alert::"+alert.size());
        	        			int zCount=0;
        	        			for (Map.Entry<Integer, String> e : tokens.entrySet()) {
    	        				    
    	        				    Object value = e.getValue();
    	        				    if(value.equals(devVolt)){
    	        				    	zCount++;
    	        				    }
    	        				    }
        	        			System.out.println("count "+zCount);
        	        			tempCheck=new ArrayList<Integer>();
        	        			if(tokens.containsValue(devVolt)){
        	        				for (Map.Entry<Integer, String> e : tokens.entrySet()) {
        	        					
        	        				    Object value = e.getValue();
        	        				    if(value.equals(devVolt)){
        	        				    	int key=e.getKey();
        	        				    	
/*       	        				    	if(alert.size()==0){
        	        				    		System.out.println("alertKey IF::"+key);
        	        				    		alert.put(key, alertCount);
        	        				    		System.out.println("Data Inserted 107");
        	        				    	}else{*/
        	        				    		
        	        				    		boolean hint=true;
        	        				    		
        	        				    		
        				        				    if(alert.containsKey(key)){
        				        				    	
        				        				    	System.out.println("Inside IF");
        				        				    	hint=false;   
        				        				    	int count=alert.get(key);
        				        				    	
        				        				    	if(count==2){
        				        				    		System.out.println("alertKey::"+key);
        				        				    		System.out.println("Alert Message Sent");
        				        				    		System.out.println("failure type : Earthpit");
        				        				    		alert.remove(key);
        				        				    		System.out.println("alertKey Removed::"+key);
        				        				    		
        				        				    	}else{
        				        				    		count++;
        				        				    		System.out.println("Data Inserted 123");
        				        				    		
        				        				    		alert.put(key, count);
        				        				    		System.out.println(alert);
        				        				    		tempCheck.add(key);
        				        				    	}
        				        				    	
        				        				    }else{
        				        				    	System.out.println("Inside ELSE");
        				        				    	System.out.println("135");
        				        				    	
        				        				    	hint=true;
        				        				    }
        				        				
        	        				    		if(hint){
        	        				    			System.out.println("Data Inserted 132");
        	        				    			alert.put(key, alertCount);
        	        				    			System.out.println(alert);
        	        				    			tempCheck.add(key);
        	        				    		}
        	        				    	}
        	        				    	
        	        				    /*}*/
        	        				}
        	        			}else{
        	        				alert.clear();
        	        				tempCheck.clear();
        	        				SocketDao socketDao=new SocketDaoImpl();
        	        				List<AppParameters> deviceList=socketDao.getAllDeviceDetails("4");
        	        				for(AppParameters appParameters:deviceList){
        	        					System.out.println(appParameters.getLocation());
        	        				}
        	        				System.out.println("Data Inserted 140");
        	        			}

        	        			
        	        			
    	        			}	
    	        		}else if(tokens.size()==2){
    	        			if(tokens.get(2).contains(".")){
    	        				System.out.println("Alert Message Sent");
    	        				System.out.println("Invalid Data");
    	        			}else{
    	        				System.out.println("Alert Message Sent");
    				    		System.out.println("failure type : Device");
    	        			}
    	        			
    	        		}else{
    	        			System.out.println("Reject Data Message");
    	        		}
                        
                    }else{
                    	System.out.println("Empty Data");
                    }
                    if(checkPrev){
    				System.out.println("Temp List Set CheckPrev::"+tempCheck);
    				HashMap<Integer, Integer> tempAlert=alert;
    				List<Integer> removedItems=new ArrayList<Integer>();
    				System.out.println("tempAlert "+tempAlert);
       			    for (Map.Entry<Integer, Integer> e : tempAlert.entrySet()) {
        				if(tempCheck.contains(e.getKey())){
        					System.out.println("valid key "+e.getKey());
        				}else{
        					removedItems.add(e.getKey());
        					System.out.println("removed key "+e.getKey());
        				}
        			}
       			    for(Integer keys:removedItems){
       			    	alert.remove(keys);
       			    }
    			}
                    System.out.println("Temp List Set::"+tempCheck);
                    System.out.println("Alert Map Set::"+alert);
                    if(alert.size()>0)
                    checkPrev=true;

                    //earthPitDataUtil.convertEarthPitValues(input).toString();
                }
            } catch (IOException e) {
                log("Error handling client# " + clientNumber + ": " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    log("Couldn't close a socket, what's going on?");
                }
                log("Connection with client# " + clientNumber + " closed");
            }
        }

        /**
         * Logs a simple message.  In this case we just write the
         * message to the server applications standard output.
         */
        private void log(String message) {
            System.out.println(message);
        }
    }
}