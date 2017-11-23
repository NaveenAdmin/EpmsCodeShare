/**
* @author  :Prasad
* @version :1.0
* @since   2015-10-09 
*/
package com.hpcl.utill;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class CurrentDateTime {
	private static final Logger logger = Logger.getLogger(CurrentDateTime.class);
	
	public static String getCurrentDate(){
	logger.info("currentDate method is started");
		/*DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");*/
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		   //get current date time with Date()
		   Date date = new Date();
       logger.info("currentDate method is compleated");
	return dateFormat.format(date);
		
	}
	
/*	public static String getCreatedOnDate(){
		logger.info("currentDate method is started");
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			   //get current date time with Date()
			   Date date = new Date();
	       logger.info("currentDate method is compleated");
		return dateFormat.format(date);
			
		}*/

}
