/**
* @author  :Prasad
* @version :1.0
* @since   2015-10-09 
*/
package com.hpcl.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpcl.dao.ConfigurationDao;
import com.hpcl.dao.AdministrationDao;
import com.hpcl.dao.DynamicMenusDao;
import com.hpcl.dao.RolePerimissionTablesDataDao;
import com.hpcl.persistence.AppParameters;
import com.hpcl.persistence.DynamicMenus;
import com.hpcl.persistence.Person;
import com.hpcl.socket.SocketClient;
import com.hpcl.utill.CurrentDateTime;
import com.hpcl.utill.SchemaDef;
import com.mysql.jdbc.log.Log;

@Controller
@PropertySource("classpath:messages.properties")
public class ValidationController {
	String tableName;
	String id;
	int tableRowId=0;
	String viewPage;
	JSONObject jsonBodyObject=new JSONObject();
	@Autowired
    DynamicMenusDao dynamicMenusDao;
	private static final Logger logger = Logger.getLogger(ValidationController.class);
	@Autowired
	AdministrationDao twoFieldDao;
	@Autowired
	RolePerimissionTablesDataDao rolePerimissionDao;
	@Autowired
	ConfigurationDao threeFieldDao;
	@Value("${port}")
	String port;
	String host;
	@Value("${ping}")
	String ping;
	JSONArray jsonArray;
	JSONArray jsonLocationArray;
	JSONArray jsonDeviceArray;
	List<AppParameters> twoFieldPersistenceList;
	List<AppParameters> threeFieldPersistenceList;

	
	@RequestMapping("/validation.htm")
	public @ResponseBody String allDetails(@ModelAttribute AppParameters twoFieldPersistence,ModelMap model,
			HttpServletRequest request) throws IOException{	
		logger.info("Validation controller started");
		String page=twoFieldPersistence.getPage();
		logger.info("page "+page);
		HttpSession session=request.getSession(false);
		String empLdapLocation=session.getAttribute("location").toString();
		String userId=session.getAttribute("userId").toString();
		
		switch(page){
		
		case "AMUD1":
		    tableName=SchemaDef.ROLE;	
		    boolean checkDuplicate=twoFieldDao.checkDuplicateData(tableName, SchemaDef.DESCRIPTION, twoFieldPersistence.getDescription());
			if(checkDuplicate){
				try {
					jsonBodyObject.put("result", "Already Exists Record.");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				try {
					jsonBodyObject.put("result", "valid");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		    
				break;
		case "AMUD2":
		    tableName=SchemaDef.CATEGORY;	
		    boolean checkDuplicate2=twoFieldDao.checkDuplicateData(tableName, SchemaDef.DESCRIPTION, twoFieldPersistence.getDescription());
			if(checkDuplicate2){
				try {
					jsonBodyObject.put("result", "Already Exists Record.");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				try {
					jsonBodyObject.put("result", "valid");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		    
				break;
		case "AMUD3":
		    tableName=SchemaDef.ZONE;	
		    boolean checkDuplicate3=twoFieldDao.checkDuplicateData(tableName, SchemaDef.DESCRIPTION, twoFieldPersistence.getDescription());
			if(checkDuplicate3){
				try {
					jsonBodyObject.put("result", "Already Exists Record.");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				try {
					jsonBodyObject.put("result", "valid");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		    
				break;
				
		case "AMUD4":
		    tableName=SchemaDef.USERTYPE;	
		    boolean checkDuplicate4=twoFieldDao.checkDuplicateData(tableName, SchemaDef.DESCRIPTION, twoFieldPersistence.getDescription());
			if(checkDuplicate4){
				try {
					jsonBodyObject.put("result", "Already Exists Record");
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
			}else{
				try {
					jsonBodyObject.put("result", "valid");
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
			}
		    
				break;
		case "AMUD5":
		    tableName=SchemaDef.M_ROLEPERIMISSION;	
		    boolean checkDuplicate5=twoFieldDao.checkDuplicateDataRolePermisions(tableName, SchemaDef.ROLEID,SchemaDef.MODULEID,SchemaDef.PAGEID,SchemaDef.ACTIONID, twoFieldPersistence.getRoleID(),twoFieldPersistence.getModuleID(),twoFieldPersistence.getPageID(),twoFieldPersistence.getActionID());
			if(checkDuplicate5){
				try {
					jsonBodyObject.put("result", "Already Exists Record");
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
			}else{
				try {
					jsonBodyObject.put("result", "valid");
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
			}
		    
				break;
		case "AMUD6":
			tableName=SchemaDef.M_ROLEPERIMISSION;
				logger.info("Role ID"+twoFieldPersistence.getRoleID());
				logger.info("Menu ID"+twoFieldPersistence.getMenuID());
				logger.info("Page ID"+twoFieldPersistence.getPageID());
				logger.info("Action ID"+twoFieldPersistence.getPageActions().length);
				String[] actionIds=twoFieldPersistence.getPageActions();
				ArrayList<String> arrayList=new ArrayList<String>();
				StringBuffer buffer=new StringBuffer();
				for(int i=0;i<actionIds.length;i++){
					String actionId=actionIds[i];
					boolean checkDuplicates=twoFieldDao.checkDuplicateRoleData(tableName, String.valueOf(twoFieldPersistence.getRoleID()), String.valueOf(twoFieldPersistence.getMenuID()), String.valueOf(twoFieldPersistence.getPageID()), actionId);
					 if(checkDuplicates){
							try {
								arrayList.add(actionId);
								if(i>0)buffer.append(" ");
								
								if(actionId.equals("1"))
								buffer.append("Select");
								if(actionId.equals("2"))
									buffer.append("Update");
								if(actionId.equals("3"))
									buffer.append("Delete");
								if(actionId.equals("4"))
									buffer.append("Insert");

							} catch (Exception e) {
								
								e.printStackTrace();
							}
						}else{

						}
				}

				if(arrayList.size()>0){
					try {
						if(arrayList.size()==1)jsonBodyObject.put("result", buffer.append(" Record Already Exist"));
						else if(arrayList.size()==3)jsonBodyObject.put("result", "All Records Exist");
						else jsonBodyObject.put("result", buffer.append(" Records Already Exist"));
					} catch (JSONException e) {
						
						e.printStackTrace();
					}
				}else{
					try {
						jsonBodyObject.put("result", "valid");
					} catch (JSONException e) {
						
						e.printStackTrace();
					}
				}
				 
				 break;
				
		case "CPUD1":
		    tableName=SchemaDef.m_employees;	
		    boolean checkDuplicate6=twoFieldDao.checkDuplicateData(tableName, SchemaDef.EMPLOYEEID, twoFieldPersistence.getEmployeeID());
			if(checkDuplicate6){
				try {
					jsonBodyObject.put("result", "Already Exists Record.");
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
			}else{
				try {
					jsonBodyObject.put("result", "valid");
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
			}
		    
				break;
				
		case "CPUD2":
		    tableName=SchemaDef.M_LOCATIONS;	
		    boolean checkDuplicate7=twoFieldDao.checkDuplicateData(tableName, SchemaDef.LOCATIONID, twoFieldPersistence.getLocationID());
			if(checkDuplicate7){
				try {
					jsonBodyObject.put("result", "Already Exists Record.");
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
			}else{
				try {
					jsonBodyObject.put("result", "valid");
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
			}
		    
				break;
				
		case "CPUD3":
		    tableName=SchemaDef.M_DEVICE;	
		    boolean checkDuplicate8=twoFieldDao.checkDuplicateData(tableName, SchemaDef.DEVICE_ID, twoFieldPersistence.getDeviceId());
			if(checkDuplicate8){
				try {
					jsonBodyObject.put("result", "Already Exists Record");
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
			}else{
				try {
					jsonBodyObject.put("result", "valid");
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
			}
		    
				break;
				
		case "CPUD31":
		    tableName=SchemaDef.M_DEVICE;	
		    boolean checkDuplicate31=twoFieldDao.checkDuplicateData(tableName, SchemaDef.DEVICE_NAME, twoFieldPersistence.getDeviceName());
			if(checkDuplicate31){
				try {
					jsonBodyObject.put("result", "Already Exists Record");
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
			}else{
				try {
					jsonBodyObject.put("result", "valid");
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
			}
		    
				break;
				
		case "CPUD4":
		    tableName=SchemaDef.M_EARTHPITS;	
		    boolean checkDuplicatee=twoFieldDao.checkDuplicateDataEarthpits(tableName, SchemaDef.EARTHPIT_NAME,SchemaDef.LOCATIONID, twoFieldPersistence.getEarthpitName(),twoFieldPersistence.getLocationID());
			if(checkDuplicatee){
				try {
					jsonBodyObject.put("result", "Already Exists Record.");
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
			}else{
				try {
					jsonBodyObject.put("result", "valid");
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
			}
		    
				break;
				
				case "CPUD5":
				    tableName=SchemaDef.M_ESCALATION;	
				    boolean checkDuplicate9=twoFieldDao.checkDuplicateData(tableName, SchemaDef.ESCALATIONID,twoFieldPersistence.getEscalationIDs());
					if(checkDuplicate9){
						try {
							jsonBodyObject.put("result", "Already Exists Record.");
						} catch (JSONException e) {
							
							e.printStackTrace();
						}
					}else{
						try {
							jsonBodyObject.put("result", "valid");
						} catch (JSONException e) {
							
							e.printStackTrace();
						}
					}
				    
						break;
						
						
				case "CPUD7":
				    tableName=SchemaDef.M_DEVICE_EARTHPITS;	
				    boolean checkDuplicate10=twoFieldDao.checkDuplicateDataDeviceEarthpit(tableName, SchemaDef.LOCATIONID,SchemaDef.DEVICE_ID,SchemaDef.EARTHPIT_ID,twoFieldPersistence.getLocationID(),twoFieldPersistence.getDeviceId(),twoFieldPersistence.getEarthpitID());
					if(checkDuplicate10){
						try {
							jsonBodyObject.put("result", "Already Exists Record.");
						} catch (JSONException e) {
							
							e.printStackTrace();
						}
					}else{
						try {
							jsonBodyObject.put("result", "valid");
						} catch (JSONException e) {
							
							e.printStackTrace();
						}
					}
				    
						break;
						
				case "CPD":
					
					
				   host=twoFieldPersistence.getIpAddress();
				   logger.info("Ip host: " +host);
				  boolean checkIp= hostAvailabilityCheck();
				  logger.info("Ip Address: " +checkIp);
					//String host = "192.168.0.179";

			    	 Socket socket=null;


			    	if(checkIp){
			    		
					        try
					        {
					          //Send the message to the server
					        	socket = new Socket(host, Integer.parseInt(port));
					            //
					            // Send a message to the client application
					            //

					        	 logger.info("Ip host: " +host);
					            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					            oos.writeObject(ping);
					            logger.info("Request sent from the client : " +ping);
					            //
					            // Read and display the response message sent by server application
					            //
					            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					            String message = (String) ois.readObject();
					            logger.info("Response received from the server : " +message);
					            if(message!=null){
									try {
										jsonBodyObject.put("result", message);
										jsonBodyObject.put("color", "black");
									} catch (JSONException e) {
										
										e.printStackTrace();
									}
								}else{
									try {
										jsonBodyObject.put("result", "Connection Fail");
										jsonBodyObject.put("color", "red");
									} catch (JSONException e) {
										
										e.printStackTrace();
									}
								}
					        }
					        catch (Exception exception)
					        {
					            exception.printStackTrace();
					        }
					        finally
					        {
					            //Closing the socket
					            try
					            {
					                socket.close();
					            }
					            catch(Exception e)
					            {
					                e.printStackTrace();
					            }
					        }
			    	}else{
			    		try {
							jsonBodyObject.put("result","IP : "+ host+" - Not Available");
							jsonBodyObject.put("color", "red");
						} catch (JSONException e) {
							
							e.printStackTrace();
						}
			    	}
			        		    
						break;
						
				case "CPDI":
					
					
					String ipadd=twoFieldPersistence.getIpAddress();
					String timeInterval=twoFieldPersistence.getTimeInterval();
					logger.info("IP Address :"+ipadd);
					logger.info("timeInterval :"+timeInterval);
					//String host = "192.168.0.179";
			    	 Socket clientSocket=null;
			    	 String command="$31,"+timeInterval+"#";
			        try
			        {
			          //Send the message to the server
			        	clientSocket = new Socket(ipadd, Integer.parseInt(port));
			            //
			            // Send a message to the client application
			            //

			            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
			            oos.writeObject(command);
			            logger.info("Request sent from the client : " +command);
			            //
			            // Read and display the response message sent by server application
			            //
			            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
			            String message = (String) ois.readObject();
			            logger.info("Response received from the server : " +message);
			            if(message!=null){
							try {
								//jsonBodyObject.put("result", message);
							} catch (Exception e) {
								
								e.printStackTrace();
							}
						}else{
							try {
								//jsonBodyObject.put("result", "Fail");
							} catch (Exception e) {
								
								e.printStackTrace();
							}
						}
			        }
			        catch (Exception exception)
			        {
			            exception.printStackTrace();
			        }
			        finally
			        {
			            //Closing the socket
			            try
			            {
			            	clientSocket.close();
			            }
			            catch(Exception e)
			            {
			                e.printStackTrace();
			            }
			        }
			        		    
						break;
				case "CPUD8":
				    tableName=SchemaDef.M_DEVICE_EARTHPITS;	
				    String tableName2=SchemaDef.M_DEVICE;
				 //   boolean checkDuplicate10=twoFieldDao.checkDuplicateDataDeviceEarthpit(tableName, SchemaDef.LOCATIONID,SchemaDef.DEVICE_ID,SchemaDef.EARTHPIT_ID,twoFieldPersistence.getLocationID(),twoFieldPersistence.getDeviceId(),twoFieldPersistence.getEarthpitID());
				    List<AppParameters> appListParameters=twoFieldDao.maxEarthPitValue(tableName2, twoFieldPersistence);			
				  int maxEpits=0;
				  for(AppParameters params:appListParameters){
					  maxEpits=Integer.parseInt(params.getEarthPitValues());
				  }
				    int 	currentEpits=twoFieldDao.deviceEarthpitPageCount(tableName, twoFieldPersistence);

						try {
							jsonBodyObject.put("max",maxEpits);
							jsonBodyObject.put("current",currentEpits);
						} catch (JSONException e) {
							
							e.printStackTrace();
						}
				    
						break;
				case "CPUD6":
				    tableName=SchemaDef.M_GROUPS;	
				    boolean checkDuplicate11=twoFieldDao.checkDuplicateDataEarthpits(tableName, SchemaDef.GROUPID,SchemaDef.GROUPNAME,twoFieldPersistence.getGroupId(),twoFieldPersistence.getGroupName());
					if(checkDuplicate11){
						try {
							jsonBodyObject.put("result", "Already Exists Record.");
						} catch (JSONException e) {
							
							e.printStackTrace();
						}
					}else{
						try {
							jsonBodyObject.put("result", "valid");
						} catch (JSONException e) {
							
							e.printStackTrace();
						}
					}
				    
						break;
						
						
		        default:
				logger.info("default block");
				break;
		}
	
	   logger.info("Ajax controller ended "+jsonBodyObject.toString());
		return jsonBodyObject.toString();
		}
	public boolean hostAvailabilityCheck() { 
		try{
			if (InetAddress.getByName(host).isReachable(100))
            {
				try (Socket s = new Socket(host,Integer.parseInt(port))) {
			        return true;
			    } catch (IOException ex) {
			    	return false;
			    }
            }else{
            	return false;
            }
		}catch(Exception e){
			return false;
		}	    
	}
}