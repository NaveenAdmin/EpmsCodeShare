/**

* @author  :Prasad
* @version :1.0
* @since   2015-10-09 
*/
package com.hpcl.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AjaxController {
	String tableName;
	String id;
	int tableRowId=0;
	String viewPage;
	JSONObject jsonBodyObject=new JSONObject();
	@Autowired
    DynamicMenusDao dynamicMenusDao;
	private static final Logger logger = Logger.getLogger(AjaxController.class);
	@Autowired
	AdministrationDao twoFieldDao;
	@Autowired
	RolePerimissionTablesDataDao rolePerimissionDao;
	@Autowired
	ConfigurationDao threeFieldDao;
	
	JSONArray jsonArray;
	JSONArray jsonLocationArray;
	JSONArray jsonDeviceArray;
	List<AppParameters> twoFieldPersistenceList;
	List<AppParameters> threeFieldPersistenceList;
/*	
	@RequestMapping("/myroles.htm")
    public ModelAndView listEmployes() throws IOException{
	logger.info("role controller started");
	List<EmployeRoles> employerolelist = employeRolesDao.getAllEmployeRoles();
    Map<String, Object> model = new HashMap<String, Object>();
	model.put("employees",  employerolelist);
	logger.info("role controller ended");
	return new ModelAndView("role", model);  
	}*/
	
	@RequestMapping("/ajaxActions.htm")
	public @ResponseBody String allDetails(@ModelAttribute AppParameters twoFieldPersistence,ModelMap model,
			HttpServletRequest request) throws IOException{	
		logger.info("Ajax controller started");
		String page=twoFieldPersistence.getPage();
		logger.info("page "+page);
		HttpSession session=request.getSession(false);
		String empLdapLocation=session.getAttribute("location").toString();
		String userId=session.getAttribute("userId").toString();
		
		switch(page){
		case "AM1": 
			/* AM1=role page.
			 * Operation 0 for select All
			 * Operation 1 for select Specific id
			 * Operation 2 for update
			 * Operation 3 for Delete
			 * */
		

				tableName=SchemaDef.ROLE;
				id=SchemaDef.ROLEID;
				
				
				twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),empLdapLocation,userId);
				
				jsonArray = new JSONArray();
				for(AppParameters details:twoFieldPersistenceList){
					JSONObject jsonObject=new JSONObject();
					try {
						jsonObject.put("id", details.getRoleID());
						jsonObject.put("desc", details.getDescription());
				    	jsonObject.put("status", details.getStatus());
					} catch (JSONException e) {
						
						e.printStackTrace();
						logger.info("JSONException "+e.toString());
					}
					jsonArray.put(jsonObject);
				}
				logger.info("ajax controller ended"+jsonArray.toString());
				
				
				try {
					jsonBodyObject.put("details", jsonArray);
				} catch (JSONException e) {
					
					e.printStackTrace();
				}	
			
			
			
			
			
			break;
		case "AM2":
			/* AM2=role module page.
			 * Operation 0 for select All
			 * Operation 1 for select Specific id
			 * Operation 2 for update
			 * Operation 3 for Delete
			 * */
			
			tableName=SchemaDef.MODULE;
			id=SchemaDef.MODULEID;
			
			twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),empLdapLocation,userId);
			
			jsonArray = new JSONArray();
			for(AppParameters details:twoFieldPersistenceList){
				JSONObject jsonObject=new JSONObject();
				try {
					jsonObject.put("id", details.getModuleID());
					jsonObject.put("desc", details.getDescription());
			    	jsonObject.put("status", details.getStatus());
				} catch (JSONException e) {
					
					e.printStackTrace();
					logger.info("JSONException "+e.toString());
				}
				jsonArray.put(jsonObject);
			}
			logger.info("ajax controller ended"+jsonArray.toString());
			
			
			try {
				jsonBodyObject.put("details", jsonArray);
			} catch (JSONException e) {
				
				e.printStackTrace();
			}

			break;
			
		case "AM3":
			/* AM3=action page.
			 * Operation 0 for select All
			 * Operation 1 for select Specific id
			 * Operation 2 for update
			 * Operation 3 for Delete
			 * Operation 4 for Save
			 * */
			tableName=SchemaDef.ACTION;
			id=SchemaDef.ACTIONID;
			
			twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),empLdapLocation,userId);
			
			jsonArray = new JSONArray();
			for(AppParameters details:twoFieldPersistenceList){
				JSONObject jsonObject=new JSONObject();
				try {
					jsonObject.put("id", details.getActionID());
					jsonObject.put("desc", details.getDescription());
			    	jsonObject.put("status", details.getStatus());
				} catch (JSONException e) {
					
					e.printStackTrace();
					logger.info("JSONException "+e.toString());
				}
				jsonArray.put(jsonObject);
			}
			logger.info("ajax controller ended"+jsonArray.toString());
			
			
			try {
				jsonBodyObject.put("details", jsonArray);
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
			
			break;
		case "AM4":
			/* AM4=category page.
			 * Operation 0 for select All
			 * Operation 1 for select Specific id
			 * Operation 2 for update
			 * Operation 3 for Delete
			 * */
			
			tableName=SchemaDef.CATEGORY;
			id=SchemaDef.CATEGORYID;
			
			twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),empLdapLocation,userId);
			
			jsonArray = new JSONArray();
			for(AppParameters details:twoFieldPersistenceList){
				JSONObject jsonObject=new JSONObject();
				try {
					jsonObject.put("id", details.getCategoryID());
					jsonObject.put("desc", details.getDescription());
			    	jsonObject.put("status", details.getStatus());
				} catch (JSONException e) {
					
					e.printStackTrace();
					logger.info("JSONException "+e.toString());
				}
				jsonArray.put(jsonObject);
			}
			logger.info("ajax controller ended"+jsonArray.toString());
			
			
			try {
				jsonBodyObject.put("details", jsonArray);
			} catch (JSONException e) {
				
				e.printStackTrace();
			}

			break;
		case "AM5":
			/* AM5=zone page.
			 * Operation 0 for select All
			 * Operation 1 for select Specific id
			 * Operation 2 for update
			 * Operation 3 for Delete
			 * */
			
			tableName=SchemaDef.ZONE;
			id=SchemaDef.ZONEID;
			
			twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),empLdapLocation,userId);
			
			jsonArray = new JSONArray();
			for(AppParameters details:twoFieldPersistenceList){
				JSONObject jsonObject=new JSONObject();
				try {
					jsonObject.put("id", details.getZoneID());
					jsonObject.put("desc", details.getDescription());
			    	jsonObject.put("status", details.getStatus());
				} catch (JSONException e) {
					
					e.printStackTrace();
					logger.info("JSONException "+e.toString());
				}
				jsonArray.put(jsonObject);
			}
			logger.info("ajax controller ended"+jsonArray.toString());
			
			
			try {
				jsonBodyObject.put("details", jsonArray);
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
			break;
		case "AM6":
			/* AM2=Usertype page.
			 * Operation 0 for select All
			 * Operation 1 for select Specific id
			 * Operation 2 for update
			 * Operation 3 for Delete
			 * */
			
			tableName="m_usertype";
			id=SchemaDef.USERTYPEID;
			//twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus());
			if(twoFieldPersistence.getStatus().equals("menu")){
			List<DynamicMenus> menulist = dynamicMenusDao.getAllDynamicPages(tableName, twoFieldPersistence);
				 
				jsonArray = new JSONArray();
				for(DynamicMenus details:menulist){
					JSONObject jsonObject=new JSONObject();
					try {
						jsonObject.put("pageid", details.getId());
						jsonObject.put("pagedesc", details.getDescription());
				    	
					} catch (JSONException e) {
						
						e.printStackTrace();
						logger.info("JSONException "+e.toString());
					}
					jsonArray.put(jsonObject);
				}
				logger.info("ajax controller ended"+jsonArray.toString());
				
				
				try {
					jsonBodyObject.put("details", jsonArray);
				   } catch (JSONException e) {
				    e.printStackTrace();
				}
			}else{
				twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),empLdapLocation,userId);
				
				jsonArray = new JSONArray();
				for(AppParameters details:twoFieldPersistenceList){
					JSONObject jsonObject=new JSONObject();
					try {
						jsonObject.put("id", details.getUserTypeID());
						jsonObject.put("desc", details.getDescription());
				    	jsonObject.put("status", details.getStatus());
					} catch (JSONException e) {
						
						e.printStackTrace();
						logger.info("JSONException "+e.toString());
					}
					jsonArray.put(jsonObject);
				}
				logger.info("ajax controller ended"+jsonArray.toString());
				
				
				try {
					jsonBodyObject.put("details", jsonArray);
				   } catch (JSONException e) {
				    e.printStackTrace();
				}
			}
			
            break;
            
		case "AM7":
			/* AM2=Usertype page.
			 * Operation 0 for select All
			 * Operation 1 for select Specific id
			 * Operation 2 for update
			 * Operation 3 for Delete
			 * */
			
			tableName="m_roleperimissions";
			logger.info("status "+twoFieldPersistence.getStatus());
			
			List<AppParameters> tableDataList= rolePerimissionDao.getTableData(tableName,twoFieldPersistence.getStatus(),empLdapLocation,userId);
			
			jsonArray = new JSONArray();
			for(AppParameters details:tableDataList){
				JSONObject jsonObject=new JSONObject();
				try {
					jsonObject.put("roleID", details.getRoles());
					jsonObject.put("roleDesc", details.getRoleDesc());
					jsonObject.put("moduleID", details.getModuleID());
					jsonObject.put("moduleDesc", details.getModuleDesc());
					jsonObject.put("actionID", details.getActionID());
					jsonObject.put("actionDesc", details.getActionDesc());
					jsonObject.put("pageID", details.getPageID());
					jsonObject.put("pageDesc", details.getPageDesc());
			    	jsonObject.put("status", details.getStatus());
				} catch (JSONException e) {
					
					e.printStackTrace();
					logger.info("JSONException "+e.toString());
				}
				jsonArray.put(jsonObject);
			}
			logger.info("ajax controller ended"+jsonArray.toString());
			
			
			try {
				jsonBodyObject.put("details", jsonArray);
			   } catch (JSONException e) {
			    e.printStackTrace();
			}
            break;
            
		case "AM8":
			/* AM2=Usertype page.
			 * Operation 0 for select All
			 * Operation 1 for select Specific id
			 * Operation 2 for update
			 * Operation 3 for Delete
			 * */
			
			tableName=SchemaDef.M_LOCATIONIP;
			id=SchemaDef.LOCATIONIPCODE;
			logger.info("status:"+twoFieldPersistence.getStatus());
			twoFieldPersistenceList = twoFieldDao.getAllLocationipDetails(tableName, id, twoFieldPersistence.getStatus());
			
			jsonArray = new JSONArray();
			for(AppParameters details:twoFieldPersistenceList){
				JSONObject jsonObject=new JSONObject();
				try {
					jsonObject.put("lid", details.getLocationID());
					jsonObject.put("ipadd", details.getIpAddress());
			    	jsonObject.put("status", details.getStatus());
				} catch (JSONException e) {
					
					e.printStackTrace();
					logger.info("JSONException "+e.toString());
				}
				jsonArray.put(jsonObject);
			}
			logger.info("ajax controller ended"+jsonArray.toString());
			
			
			try {
				jsonBodyObject.put("details", jsonArray);
			   } catch (JSONException e) {
			    e.printStackTrace();
			}
            break;
            
		case "CP1":
			/* CP1=employee page.
			 * Operation 0 for select All
			 * Operation 1 for select Specific id
			 * Operation 2 for update
			 * Operation 3 for Delete
			 * */
			logger.info("ajax cp1 controller Started");
			logger.info("Page : "+page);
			tableName=SchemaDef.m_employees;
			id=SchemaDef.EMPLOYEEID;
			//List<AppParameters> appParametersList =threeFieldDao.getAllDetails(tableName, null, page, threeFieldPersistenceList.getStatus())
		    
		    if(twoFieldPersistence.getStatus().equals("emploc"))
		    {
		    	threeFieldPersistenceList=threeFieldDao.getEmployeeLocations(SchemaDef.m_employees, twoFieldPersistence.getLocationID());	
		    }else
		    {
		    	threeFieldPersistenceList = threeFieldDao.getAllDetails(tableName,null,id,twoFieldPersistence.getStatus(),empLdapLocation,userId);	
		    }
			
			jsonArray = new JSONArray();
			for(AppParameters details:threeFieldPersistenceList){
				JSONObject jsonObject=new JSONObject();
				try {
					jsonObject.put("empid", details.getEmployeeID());
					jsonObject.put("empname", details.getEmployeeName());
			    	jsonObject.put("emploc", details.getLocation());
			    	jsonObject.put("emprole", details.getDescription());
			    	jsonObject.put("empstat", details.getStatus());
				} catch (JSONException e) {
					
					e.printStackTrace();
					logger.info("JSONException "+e.toString());
				}
				jsonArray.put(jsonObject);
			}
			logger.info("ajax controller ended"+jsonArray.toString());
			
			
			try {
				jsonBodyObject.put("details", jsonArray);
			   } catch (JSONException e) {
			    e.printStackTrace();
			}
			break;
		case "CP2":
			/* CP2=location page.
			 * Operation 0 for select All
			 * Operation 1 for select Specific id
			 * Operation 2 for update
			 * Operation 3 for Delete
			 * */
			logger.info("ajax cp2 controller Started");
			tableName=SchemaDef.M_LOCATIONS;
			id=SchemaDef.LOCATIONID;
			//List<AppParameters> appParametersList =threeFieldDao.getAllDetails(tableName, null, page, threeFieldPersistenceList.getStatus())
		    threeFieldPersistenceList = threeFieldDao.getAllLocationDetails(tableName,null,id,twoFieldPersistence.getStatus(),empLdapLocation,userId);
			logger.info("list size"+threeFieldPersistenceList.size());
			jsonArray = new JSONArray();
			for(AppParameters details:threeFieldPersistenceList){
				JSONObject jsonObject=new JSONObject();
				try {
					jsonObject.put("locid", details.getLocationID());
					jsonObject.put("locname", details.getLocationName());
			    	jsonObject.put("loczones", details.getZones());
			    	jsonObject.put("locstat", details.getStatus());
				} catch (JSONException e) {
					
					e.printStackTrace();
					logger.info("JSONException "+e.toString());
				}
				jsonArray.put(jsonObject);
			}
			logger.info("ajax controller ended"+jsonArray.toString());
			
			
			try {
				jsonBodyObject.put("details", jsonArray);
			   } catch (JSONException e) {
			    e.printStackTrace();
			}
			break;
			
		case "CP3":
			/* CP3=device page.
			 * Operation 0 for select All
			 * Operation 1 for select Specific id
			 * Operation 2 for update
			 * Operation 3 for Delete
			 * */
			logger.info("ajax cp3 controller Started");
			tableName=SchemaDef.M_DEVICE;
			id=SchemaDef.DEVICE_ID;
			//List<AppParameters> appParametersList =threeFieldDao.getAllDetails(tableName, null, page, threeFieldPersistenceList.getStatus())
			 if(twoFieldPersistence.getStatus().equals("devLocname"))
			    {
			    	threeFieldPersistenceList=threeFieldDao.getDeviceLocations(SchemaDef.M_DEVICE, twoFieldPersistence.getLocationID());	
			    }else
			    {
			    	threeFieldPersistenceList = threeFieldDao.getAllDeviceDetails(tableName,id,twoFieldPersistence.getStatus(),empLdapLocation,userId
);	
			    }
		    
			logger.info("list size"+threeFieldPersistenceList.size());
			jsonArray = new JSONArray();
			for(AppParameters details:threeFieldPersistenceList){
				JSONObject jsonObject=new JSONObject();
				try {
					jsonObject.put("devId", details.getDeviceId());
					jsonObject.put("devname", details.getDeviceName());
					jsonObject.put("devIp", details.getIpAddress());
			    	jsonObject.put("devLoc", details.getLocationID());
			    	jsonObject.put("devLocname", details.getLocationName());
			    	jsonObject.put("devEarthPit", details.getEarthPitValues());
			    	jsonObject.put("devInter", details.getTimeInterval());
			    	jsonObject.put("devInstall", details.getInstalledDate());
			    	jsonObject.put("devSno", details.getDeviceSno());
			    	jsonObject.put("devStatus", details.getStatus());
			    	
			    	
				} catch (JSONException e) {
					
					e.printStackTrace();
					logger.info("JSONException "+e.toString());
				}
				jsonArray.put(jsonObject);
			}
			
		
			List<AppParameters> twoFieldPersistenceList =threeFieldDao.getAppsetting(null);
			for(AppParameters app:twoFieldPersistenceList){
				try {
					jsonBodyObject.put("maxdevice",  app.getMaxDevices());
				} catch (JSONException e) {
				
					e.printStackTrace();
				}
			}
	
	
			int devicecount=twoFieldDao.homePageCount(SchemaDef.M_DEVICE);
			logger.info("devicecount: "+devicecount);
			try {
				jsonBodyObject.put("devicecount",  devicecount);
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
			
			try {
				jsonBodyObject.put("details", jsonArray);
			   } catch (JSONException e) {
			    e.printStackTrace();
			 logger.info("ajax controller ended"+jsonArray.toString());
			}
			break;
		case "CP4":
			/* CP4=earthpit  page.
			 * Operation 0 for select All
			 * Operation 1 for select Specific id
			 * Operation 2 for updatearg1
			 * Operation 3 for Delete
			 * */
			logger.info("ajax cp4 controller Started");
			tableName=SchemaDef.M_EARTHPITS;
			id=SchemaDef.EARTHPIT_ID;
			//List<AppParameters> appParametersList =threeFieldDao.getAllDetails(tableName, null, page, threeFieldPersistenceList.getStatus())
			if(twoFieldPersistence.getStatus().equals("earthlocname"))
			    {
			    	threeFieldPersistenceList=threeFieldDao.getEarthpitLocations(SchemaDef.M_EARTHPITS, twoFieldPersistence.getLocationID());	
			    }else
			    {
			    	 threeFieldPersistenceList = threeFieldDao.getAllEarthpitsDetails(tableName,id,twoFieldPersistence.getStatus(),empLdapLocation,userId);	
			    }
			
			logger.info("list size"+threeFieldPersistenceList.size());
			jsonArray = new JSONArray();
			for(AppParameters details:threeFieldPersistenceList){
				JSONObject jsonObject=new JSONObject();
				try {
					jsonObject.put("earthid", details.getEarthpitID());
					jsonObject.put("earthname", details.getEarthpitName());
			    	jsonObject.put("earthloc", details.getLocationID());
			    	jsonObject.put("earthlocname", details.getLocationName());
			    	jsonObject.put("earthstat", details.getStatus());
				} catch (JSONException e) {
					
					e.printStackTrace();
					logger.info("JSONException "+e.toString());
				}
				jsonArray.put(jsonObject);
			}
			//for getting maxdevices from appsetting table table.

			List<AppParameters> twoFieldPersistenceLists =threeFieldDao.getAppsetting(null);
			for(AppParameters app:twoFieldPersistenceLists){
				try {
					jsonBodyObject.put("maxdevice",  app.getMaxDevices());
				} catch (JSONException e) {
				
					e.printStackTrace();
				}
			}
			//for getting maxEarthpit from appsetting table table.
			
			List<AppParameters> twoFieldPersistenceListss =threeFieldDao.getAppsetting(null);
			for(AppParameters app:twoFieldPersistenceListss){
				try {
					jsonBodyObject.put("maxearthpit",  app.getEarthpit());
				} catch (JSONException e) {
				
					e.printStackTrace();
				}
			}
			//for getting devicecount from device table.
			int devicecount1=twoFieldDao.homePageCount(SchemaDef.M_DEVICE);
			try {
				jsonBodyObject.put("devicecount",  devicecount1);
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
			//for getting earthpitcount from earthpit table.
			int earthpitcount=twoFieldDao.homePageCount(SchemaDef.M_EARTHPITS);
			try {
				jsonBodyObject.put("earthcount",earthpitcount);
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
			logger.info("ajax controller ended"+jsonArray.toString());
			
			
			try {
				jsonBodyObject.put("details", jsonArray);
			   } catch (JSONException e) {
			    e.printStackTrace();
			}
			break;
		case "CP5":
			/* CP5=escalation  page.
			 * Operation 0 for select All
			 * Operation 1 for select Specific id
			 * Operation 2 for update
			 * Operation 3 for Delete
			 * */
			logger.info("ajax cp2 controller Started");
			tableName=SchemaDef.M_ESCALATION;
			id=SchemaDef.ESCALATIONID;
			List<AppParameters> threeFieldPersistenceList = threeFieldDao.getAllEscalationDetails( tableName,id,twoFieldPersistence.getStatus(),empLdapLocation,userId);
			
			
			logger.info("list size"+threeFieldPersistenceList.size());
			jsonArray = new JSONArray();
			for(AppParameters details:threeFieldPersistenceList){
				JSONObject jsonObject=new JSONObject();
				try {
					
		    		
					jsonObject.put("escalationID", details.getEscalationIDs());
					jsonObject.put("escalationType", details.getEscalationTypes());
			    	jsonObject.put("escalationLevel", details.getEscalationLevels());
			    	jsonObject.put("groupId", details.getGroupId());
			    	jsonObject.put("sMSMessage", details.getsMSMessages());
			    	jsonObject.put("eMailMessage", details.geteMailMessages());
			    	jsonObject.put("escalationInterval", details.getEscalationIntervals());
			    	jsonObject.put("repeatInterval", details.getRepeatIntervals());
			    	jsonObject.put("status", details.getStatus());
			    	
				} catch (JSONException e) {
					
					e.printStackTrace();
					logger.info("JSONException "+e.toString());
				}
				jsonArray.put(jsonObject);
			}
			logger.info("ajax controller ended"+jsonArray.toString());
			
			
			try {
				jsonBodyObject.put("details", jsonArray);
			   } catch (JSONException e) {
			    e.printStackTrace();
			}
			break;
		case "CP6":
			/* CP6=device-earthpits  page.
			 * Operation 0 for select All
			 * Operation 1 for select Specific id
			 * Operation 2 for update
			 * Operation 3 for Delete
			 * */
			logger.info("ajax cp6 controller Started");
			logger.info("status "+twoFieldPersistence.getStatus());
			if(twoFieldPersistence.getStatus().equals("location")){
				logger.info("location");
				twoFieldPersistence.setStatus("A");
				JSONArray	jsonLocEArray = new JSONArray();
				/*if(twoFieldPersistence.getStatus().equals("locName"))
			    {*/
			    	threeFieldPersistenceList=threeFieldDao.getDeviceEarthpitLocations(SchemaDef.M_DEVICE_EARTHPITS, twoFieldPersistence.getLocationID());	
			    	System.out.println("Details:-->"+threeFieldPersistenceList.size());
			    	for(AppParameters details:threeFieldPersistenceList){
						JSONObject jsonObject=new JSONObject();
						try {
							jsonObject.put("loc", details.getLocationID());
							jsonObject.put("locName", details.getLocationName());
							jsonObject.put("device", details.getDeviceId());
							jsonObject.put("deviceName", details.getDeviceName());
							jsonObject.put("earthpit", details.getEarthpitID());
							jsonObject.put("earthpitName", details.getEarthpitName());
							jsonObject.put("status", details.getStatus());
					    	
						} catch (JSONException e) {
							
							e.printStackTrace();
							logger.info("JSONException "+e.toString());
						}
						jsonLocEArray.put(jsonObject);
					}
			    	try {
						jsonBodyObject.put("location", jsonLocEArray);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    
			    /*}else
			    {*/
			    	List<AppParameters> appParametersList = threeFieldDao.getAllDeviceLocationDetails(SchemaDef.M_DEVICE,twoFieldPersistence.getLocationID(),twoFieldPersistence.getStatus());	
			    
				   jsonArray = new JSONArray();
				
				for(AppParameters details:appParametersList){
					JSONObject jsonObject=new JSONObject();
					try {
						jsonObject.put("devId", details.getDeviceId());
						jsonObject.put("devName", details.getDeviceName());
						jsonObject.put("maxVolt", details.getMaxVolt());
				    	
					} catch (JSONException e) {
						
						e.printStackTrace();
						logger.info("JSONException "+e.toString());
					}
					jsonArray.put(jsonObject);
				/*}*/
			    }
				List<AppParameters> appParametersEList = threeFieldDao.getAllEarthpitLocationDetails(SchemaDef.M_EARTHPITS,twoFieldPersistence.getLocationID(),twoFieldPersistence.getStatus());
				JSONArray	jsonEArray = new JSONArray();
				
				for(AppParameters details:appParametersEList){
					JSONObject jsonObject=new JSONObject();
					try {
						jsonObject.put("eId", details.getEarthpitID());
						jsonObject.put("eName", details.getEarthpitName());
				    } catch (JSONException e) {
						
						e.printStackTrace();
						logger.info("JSONException "+e.toString());
					}
					jsonEArray.put(jsonObject);
				}
				try {
					jsonBodyObject.put("details", jsonArray);
					jsonBodyObject.put("pits", jsonEArray);
					
				   } catch (JSONException e) {
				    e.printStackTrace();
				}
				
				
			}else 	if(twoFieldPersistence.getStatus().equals("device")){
				logger.info("location");
				twoFieldPersistence.setStatus("A");
				JSONArray	jsonLocEArray = new JSONArray();
				/*if(twoFieldPersistence.getStatus().equals("locName"))
			    {*/
			    	threeFieldPersistenceList=threeFieldDao.getDeviceEarthpitLocationsDetails(SchemaDef.M_DEVICE_EARTHPITS, twoFieldPersistence.getLocationID(),twoFieldPersistence.getDeviceId());	
			    	System.out.println("Details:-->"+threeFieldPersistenceList.size());
			    	for(AppParameters details:threeFieldPersistenceList){
						JSONObject jsonObject=new JSONObject();
						try {
							jsonObject.put("loc", details.getLocationID());
							jsonObject.put("locName", details.getLocationName());
							jsonObject.put("device", details.getDeviceId());
							jsonObject.put("deviceName", details.getDeviceName());
							jsonObject.put("earthpit", details.getEarthpitID());
							jsonObject.put("earthpitName", details.getEarthpitName());
							jsonObject.put("status", details.getStatus());
					    	
						} catch (JSONException e) {
							
							e.printStackTrace();
							logger.info("JSONException "+e.toString());
						}
						jsonLocEArray.put(jsonObject);
					}
			    	try {
						jsonBodyObject.put("location", jsonLocEArray);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    
			    /*}else
			    {*/
			    	List<AppParameters> appParametersList = threeFieldDao.getAllDeviceLocationDetails(SchemaDef.M_DEVICE,twoFieldPersistence.getLocationID(),twoFieldPersistence.getStatus());	
			    
				   jsonArray = new JSONArray();
				
				for(AppParameters details:appParametersList){
					JSONObject jsonObject=new JSONObject();
					try {
						jsonObject.put("devId", details.getDeviceId());
						jsonObject.put("devName", details.getDeviceName());
						jsonObject.put("maxVolt", details.getMaxVolt());
				    	
					} catch (JSONException e) {
						
						e.printStackTrace();
						logger.info("JSONException "+e.toString());
					}
					jsonArray.put(jsonObject);
				/*}*/
			    }
				List<AppParameters> appParametersEList = threeFieldDao.getAllEarthpitLocationDetails(SchemaDef.M_EARTHPITS,twoFieldPersistence.getLocationID(),twoFieldPersistence.getStatus());
				JSONArray	jsonEArray = new JSONArray();
				
				for(AppParameters details:appParametersEList){
					JSONObject jsonObject=new JSONObject();
					try {
						jsonObject.put("eId", details.getEarthpitID());
						jsonObject.put("eName", details.getEarthpitName());
				    } catch (JSONException e) {
						
						e.printStackTrace();
						logger.info("JSONException "+e.toString());
					}
					jsonEArray.put(jsonObject);
				}
				try {
					jsonBodyObject.put("details", jsonArray);
					jsonBodyObject.put("pits", jsonEArray);
					
				   } catch (JSONException e) {
				    e.printStackTrace();
				}
				
				
			}else if(twoFieldPersistence.getStatus().equals("count")){
				logger.info("count");
				twoFieldPersistence.setStatus("A");
				int count=twoFieldDao.deviceEarthpitPageCount(SchemaDef.M_DEVICE_EARTHPITS, twoFieldPersistence);
				
				jsonArray = new JSONArray();
				JSONObject jsonObject=new JSONObject();
					try {
						jsonObject.put("count",String.valueOf(count));
					} catch (JSONException e) {
						e.printStackTrace();
						logger.info("JSONException "+e.toString());
					}
					jsonArray.put(jsonObject);
				try {
					jsonBodyObject.put("details", jsonArray);
					
				   } catch (JSONException e) {
				    e.printStackTrace();
				}
				
				
			}else{
				
			tableName=SchemaDef.M_DEVICE_EARTHPITS;
			id=SchemaDef.DEVICE_EARTHPIT_ID;
			List<AppParameters> threeFieldPersistenceLists = threeFieldDao.getAllDeviceEarthpitsDetails( tableName,id,twoFieldPersistence.getStatus(),empLdapLocation,userId);
			
			logger.info("list size"+threeFieldPersistenceLists.size());
			jsonArray = new JSONArray();
			for(AppParameters details:threeFieldPersistenceLists){
				JSONObject jsonObject=new JSONObject();
				try {
					
		    		
					jsonObject.put("loc", details.getLocationID());
					jsonObject.put("locName", details.getLocationName());
					jsonObject.put("earthpit", details.getEarthpitID());
					jsonObject.put("earthpitName", details.getEarthpitName());
					jsonObject.put("device", details.getDeviceId());
			    	jsonObject.put("deviceName", details.getDeviceName());
			    	jsonObject.put("status", details.getStatus());
			    	
				} catch (JSONException e) {
					
					e.printStackTrace();
					logger.info("JSONException "+e.toString());
				}
				jsonArray.put(jsonObject);
			}
			try {
				jsonBodyObject.put("details", jsonArray);
			   } catch (JSONException e) {
			    e.printStackTrace();
			}
			}
			logger.info("ajax controller ended"+jsonArray.toString());
		 break;
		case "CP72":
			List<AppParameters> appEmpParametersList=threeFieldDao.getEmployeeGroupDetails(tableName, twoFieldPersistence);
			JSONArray	jsonEmpArray = new JSONArray();
			
			for(AppParameters details:appEmpParametersList){
				JSONObject jsonObject=new JSONObject();
				try {
					jsonObject.put("eId", details.getEmployeeID());
					jsonObject.put("eName", details.getEmployeeName());				
			    } catch (JSONException e) {
					
					e.printStackTrace();
					logger.info("JSONException "+e.toString());
				}
				jsonEmpArray.put(jsonObject);
			}
			try {
				jsonBodyObject.put("emp",jsonEmpArray);
			logger.info("Emp details:"+jsonBodyObject);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "CP71":
			logger.info("multiCheckbox group controller started");
			logger.info("Group ID "+twoFieldPersistence.getGroupId());
			logger.info("Employee "+twoFieldPersistence.getEmployeeID());
			twoFieldPersistence.setCreatedOn(CurrentDateTime.getCurrentDate());
			twoFieldPersistence.setCreatedBy("Admin");
			twoFieldPersistence.setStatus("A");
			String[] pits = request.getParameterValues("pits");
			String pitsValues=pits[0];
			if(pitsValues.contains(",")){
				pits=pitsValues.split(",");
			}
			if (pits != null) 
						   {
						      for (int i = 0; i < pits.length; i++) 
						      {
						    	String employeeID=pits[i];
						    	 twoFieldPersistence.setEmployeeID(employeeID);
						    	
						    	  threeFieldDao.saveAlertGroupDetails(twoFieldPersistence, SchemaDef.M_GROUPS);
						         logger.info("inserted employee id:"+pits[i]);
						      }
						   }	
						/*List<AppParameters> appParametersList = threeFieldDao.getAllAlertGroupDetails(SchemaDef.M_GROUPS, SchemaDef.GROUPID,SchemaDef.ACTIVE_FLAG);
						model.put("list",  appParametersList);
						System.out.println("Configuration alertgroup List size "+appParametersList.size());
						List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG);
						model.put("location",  locationTableDataList);
			            return "alertgroups";*/

		
		break;
		case "CP7":
			/* CP7=alertgroup  page.
			 * Operation 0 for select All
			 */
			tableName=SchemaDef.M_GROUPS;
			id=SchemaDef.GROUPID;
			/*List<AppParameters> appParametersList = threeFieldDao.getAllAlertGroupDetails(tableName, id,twoFieldPersistence.getStatus());
			*/
			if(twoFieldPersistence.getStatus()!=null){
				logger.info("Status::"+twoFieldPersistence.getStatus());
				List<AppParameters> appParametersList = threeFieldDao.getAllAlertGroupDetails(tableName, id,twoFieldPersistence.getStatus(),empLdapLocation,userId);
				JSONArray	jsonEArray = new JSONArray();
				
				for(AppParameters details:appParametersList){
					JSONObject jsonObject=new JSONObject();
					try {
						logger.info("LOCATION :"+details.getLocation());
						jsonObject.put("grpID", details.getGroupId());
						jsonObject.put("grpName", details.getGroupName());
						jsonObject.put("location", details.getLocation());
						jsonObject.put("escalevel", details.getLevel());
						jsonObject.put("empName", details.getEmployeeName());
						jsonObject.put("locName", details.getLocationName());
						jsonObject.put("emphno", details.getPhoneNo());
						jsonObject.put("empemail", details.getEmailId());
						jsonObject.put("emprole", details.getDescription());
						jsonObject.put("status", details.getStatus());
						jsonObject.put("locId", details.getLocn());
						jsonObject.put("empId", details.getEmployeeID());
						
				    } catch (JSONException e) {
						
						e.printStackTrace();
						logger.info("JSONException "+e.toString());
					}
					jsonEArray.put(jsonObject);
				}
				try {
					
					jsonBodyObject.put("details", jsonEArray);
				   } catch (JSONException e) {
				    e.printStackTrace();
				}
/*				System.out.println("Configuration alertgroup List size "+appParametersList.size());
				List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG);
				*/
			}
				List<AppParameters> appParametersList=threeFieldDao.getEmployeeDetails(tableName, twoFieldPersistence);
				JSONArray	jsonEArray = new JSONArray();
				
				for(AppParameters details:appParametersList){
					JSONObject jsonObject=new JSONObject();
					try {
						jsonObject.put("eId", details.getEmployeeID());
						jsonObject.put("eName", details.getEmployeeName());
						
						/*jsonObject.put("grpID", details.getGroupId());
						jsonObject.put("grpName", details.getGroupName());
						jsonObject.put("locName", details.getLocationName());
						jsonObject.put("escalevel", details.getEscalationLevels());
						jsonObject.put("empName", details.getEmployeeName());
						jsonObject.put("locName", details.getLocationName());
						jsonObject.put("emphno", details.getPhoneNo());
						jsonObject.put("empemail", details.getEmailId());
						jsonObject.put("emprole", details.getRoles());
						jsonObject.put("status", details.getStatus());*/
						
				    } catch (JSONException e) {
						
						e.printStackTrace();
						logger.info("JSONException "+e.toString());
					}
					jsonEArray.put(jsonObject);
				}
				try {
					
					jsonBodyObject.put("pits", jsonEArray);
				   } catch (JSONException e) {
				    e.printStackTrace();
				}
			

			
			break;
			
		case "EM1":
			/* EM1=earthpit monitoring  page.
			 * Operation 0 for select All
			 */
			logger.info("ajax EM1(earthpit monitoring) controller Started");
			tableName=SchemaDef.TR_DASHBOARD;
			logger.info("ajax location"+twoFieldPersistence.getLocationID());
			//id=SchemaDef.ESCALATIONID;
			List<AppParameters> earthphitmonitoringList = threeFieldDao.getAllEarthpitMonitoringDetails( tableName,twoFieldPersistence,twoFieldPersistence.getStatus(),twoFieldPersistence.getFromdatetime(), twoFieldPersistence.getTodatetime(),empLdapLocation,userId);
			logger.info("list size"+earthphitmonitoringList.size());
			jsonArray = new JSONArray();
			for(AppParameters details:earthphitmonitoringList){
				JSONObject jsonObject=new JSONObject();
				try {
				    jsonObject.put("locationName", details.getLocationName());
					jsonObject.put("deviceName", details.getDeviceName());
			    	jsonObject.put("earthpitName", details.getEarthpitName());
			    	jsonObject.put("voltage", details.getVoltage());
			    	jsonObject.put("receivedDate", details.getReceivedDate());
			    	
				} catch (JSONException e) {
					
					e.printStackTrace();
					logger.info("JSONException "+e.toString());
				}
				jsonArray.put(jsonObject);
				
				
				
			}
			List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,empLdapLocation,userId);
			logger.info("ajax EM1(earthpit monitoring) ended"+jsonArray.toString());
			jsonLocationArray = new JSONArray();
			for(AppParameters location:locationTableDataList){
				JSONObject jsonObject=new JSONObject();
				try {
				    jsonObject.put("locationId", location.getLocationID());
					jsonObject.put("locationName",location.getLocationName());

			    	
				} catch (JSONException e) {
					
					e.printStackTrace();
					logger.info("JSONException "+e.toString());
				}
				jsonLocationArray.put(jsonObject);
				
				
				
			}
			logger.info("**** location :"+twoFieldPersistence.getStatus());
			if(twoFieldPersistence.getStatus().equals("location")){
				logger.info("select device execution started");
				List<AppParameters> locationDeviceDataList= threeFieldDao.getAllLocationDeviceDetails(twoFieldPersistence.getLocationID());
				logger.info("ajax EM1(earthpit monitoring) ended"+locationDeviceDataList.size());
				jsonDeviceArray = new JSONArray();

				for(AppParameters location:locationDeviceDataList){
					JSONObject jsonObject=new JSONObject();
					try {
					    jsonObject.put("deviceId", location.getDeviceId());
						jsonObject.put("deviceName",location.getDeviceName());

				    	
					} catch (JSONException e) {
						
						e.printStackTrace();
						logger.info("JSONException "+e.toString());
					}
					jsonDeviceArray.put(jsonObject);
					
					
					
				}
				try {
					jsonBodyObject.put("device", jsonDeviceArray);
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
				logger.info("select device execution end");
			}
			
			
			try{
				jsonBodyObject.put("details", jsonArray);
				jsonBodyObject.put("location", jsonLocationArray);
				
			   } catch (JSONException e) {
			    e.printStackTrace();
			   }
			break;
		case "DATESEARCH":
			/* DATESEARCH for  earthpit monitoring.
			 * Operation 0 for select All
			 */
			logger.info("ajax EM1(DATESEARCH) controller Started");
			tableName=SchemaDef.TR_DASHBOARD;
			logger.info("ajax location"+twoFieldPersistence.getLocationID());
			tableName=SchemaDef.TR_DASHBOARD;
			//List<AppParameters> appParametersLists = threeFieldDao.getDateAndTimeSearch(tableName, twoFieldPersistence.getFromdatetime(), twoFieldPersistence.getTodatetime(), twoFieldPersistence);
			String emplocid=null;
			String emprole=session.getAttribute("role").toString();
			String emplocation=session.getAttribute("location").toString();
			
			if(session.getAttribute("userId").toString()!="admin")
			   {    
				   emplocid=session.getAttribute("locid").toString();
				   logger.info("equal to admin  condition executed : "+emplocid);
			   }else{
				   emplocid=session.getAttribute("location").toString();
				   logger.info("equal to admin flase condition executed:"+session.getAttribute("userId").toString()+" & locaion Id : "+emplocid);
			        
				}
				
			logger.info("emprole:"+emprole);
			logger.info("emplocation:"+emplocation);
			HashSet<String> hashSet=new HashSet<String>();
			List<AppParameters> appParametersLists = threeFieldDao.getDasboardSearchfilters(tableName, twoFieldPersistence.getLocationID(), twoFieldPersistence.getDeviceId(), twoFieldPersistence.getFromdatetime(), twoFieldPersistence.getTodatetime(), twoFieldPersistence,emprole,emplocid,userId);
			
			logger.info("list size"+appParametersLists.size());
			jsonArray = new JSONArray();
			for(AppParameters details:appParametersLists){
				hashSet.add(details.getLocationName()+ "-"+details.getEarthpitName());
				//hashSet.add(details.getEarthpitName());
				
			}
			logger.info("hashSet###########"+hashSet);
			logger.info("hashSet###########"+hashSet.size());
			int j=0;
			JSONObject jsonEpObject=new JSONObject();
			 Iterator itr1 = hashSet.iterator();
		        while(itr1.hasNext())
		        {
	
		        	try {
						jsonEpObject.put("earthpitName"+j, itr1.next());
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    	
		 
		            j++;
		        }
		
			for(AppParameters details:appParametersLists){
				JSONObject jsonObject=new JSONObject();
				try {
				    jsonObject.put("locationName", details.getLocationName());
					jsonObject.put("deviceName", details.getDeviceName());
					int i=0;
					 Iterator itr = hashSet.iterator();
				        while(itr.hasNext())
				        {
				           if(itr.next().equals(details.getLocationName()+ "-"+details.getEarthpitName())){
				        	   jsonObject.put("earthpitName"+i, details.getEarthpitName());
						    	jsonObject.put("voltage"+i, details.getVoltage());
						    	
						    	
						    	jsonObject.put("locationName", details.getLocationName());
						        jsonObject.put("deviceName", details.getDeviceName());
						    	jsonObject.put("earthpitName", details.getEarthpitName());
						    	jsonObject.put("voltage", details.getVoltage());
						    	jsonObject.put("receivedDate", details.getReceivedDate());
						    	
				           }
				            i++;
				        }
				
			    	jsonObject.put("receivedDate",details.getReceivedDate());
			    	jsonObject.put("size",hashSet.size());
			    	
				} catch (JSONException e) {
					
					e.printStackTrace();
					logger.info("JSONException for DATESEARCH try block1: "+e.toString());
				}
				jsonArray.put(jsonObject);
				
			}
			List<AppParameters> locationTableDataLists= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,empLdapLocation,userId);
			//logger.info("ajax EM1(DATESEARCH) ended"+jsonArray.toString());
			jsonLocationArray = new JSONArray();
			for(AppParameters location:locationTableDataLists){
				JSONObject jsonObject=new JSONObject();
				try {
				    jsonObject.put("locationId", location.getLocationID());
					jsonObject.put("locationName",location.getLocationName());

			    	
				} catch (JSONException e) {
					
					e.printStackTrace();
					logger.info("JSONException for DATESEARCH try block2: "+e.toString());
				}
				jsonLocationArray.put(jsonObject);
			}
			//logger.info("**** location :"+twoFieldPersistence.getStatus());
			if(twoFieldPersistence.getStatus().equals("location")){
				
				List<AppParameters> locationDeviceDataList= threeFieldDao.getAllLocationDeviceDetails(twoFieldPersistence.getLocationID());
				logger.info("ajax EM1(earthpit monitoring) ended"+locationDeviceDataList.size());
				jsonDeviceArray = new JSONArray();

				for(AppParameters location:locationDeviceDataList){
					JSONObject jsonObject=new JSONObject();
					try {
					    jsonObject.put("deviceId", location.getDeviceId());
						jsonObject.put("deviceName",location.getDeviceName());

				    	
					} catch (JSONException e) {
						
						e.printStackTrace();
						logger.info("JSONException "+e.toString());
					}
					jsonDeviceArray.put(jsonObject);
				}
				try {
					jsonBodyObject.put("device", jsonDeviceArray);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				logger.info("DATESEARCH execution end");
			}
			
			
			try{
				jsonBodyObject.put("details", jsonArray);
				jsonBodyObject.put("location", jsonLocationArray);
				jsonBodyObject.put("epname", jsonEpObject);
				
			   } catch (JSONException e) {
			    e.printStackTrace();
			   }
			break;
		case "DATESEARCHFORDASHBOARD":
			/* DATESEARCH for Datesearch for dashboard and earthpit monitoring.
			 * Operation 0 for select All
			 */
			logger.info("ajax EM1(DATESEARCH) controller Started");
			tableName=SchemaDef.TR_DASHBOARD;
			logger.info("ajax location"+twoFieldPersistence.getLocationID());
		
			tableName=SchemaDef.TR_DASHBOARD;
			//List<AppParameters> appParametersLists = threeFieldDao.getDateAndTimeSearch(tableName, twoFieldPersistence.getFromdatetime(), twoFieldPersistence.getTodatetime(), twoFieldPersistence);
			List<AppParameters> appParametersListss = threeFieldDao.getOnlyDasboardSearchfilters(tableName, twoFieldPersistence.getLocationID(), twoFieldPersistence.getDeviceId(), twoFieldPersistence.getFromdatetime(), twoFieldPersistence.getTodatetime(), twoFieldPersistence);
			logger.info("list size"+appParametersListss.size());
			jsonArray = new JSONArray();
			for(AppParameters details:appParametersListss){
				JSONObject jsonObject=new JSONObject();
				try {
				    jsonObject.put("location", details.getLocation());
					jsonObject.put("deviceName", details.getDeviceName());
			    	jsonObject.put("earthpitName", details.getEarthpitName());
			    	jsonObject.put("voltage", details.getVoltage());
			    	jsonObject.put("receivedDate", details.getReceivedDate());
			    	
				} catch (JSONException e) {
					
					e.printStackTrace();
					logger.info("JSONException "+e.toString());
				}
				jsonArray.put(jsonObject);
				
			}
			List<AppParameters> locationTableDataListss= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,empLdapLocation,userId);
			logger.info("ajax EM1(DATESEARCH) ended"+jsonArray.toString());
			jsonLocationArray = new JSONArray();
			for(AppParameters location:locationTableDataListss){
				JSONObject jsonObject=new JSONObject();
				try {
				    jsonObject.put("locationId", location.getLocationID());
					jsonObject.put("locationName",location.getLocationName());

			    	
				} catch (JSONException e) {
					
					e.printStackTrace();
					logger.info("JSONException "+e.toString());
				}
				jsonLocationArray.put(jsonObject);
			}
			logger.info("**** location :"+twoFieldPersistence.getStatus());
			if(twoFieldPersistence.getStatus().equals("location")){
				
				List<AppParameters> locationDeviceDataList= threeFieldDao.getAllLocationDeviceDetails(twoFieldPersistence.getLocationID());
				logger.info("ajax EM1(earthpit monitoring) ended"+locationDeviceDataList.size());
				jsonDeviceArray = new JSONArray();

				for(AppParameters location:locationDeviceDataList){
					JSONObject jsonObject=new JSONObject();
					try {
					    jsonObject.put("deviceId", location.getDeviceId());
						jsonObject.put("deviceName",location.getDeviceName());

				    	
					} catch (JSONException e) {
						
						e.printStackTrace();
						logger.info("JSONException "+e.toString());
					}
					jsonDeviceArray.put(jsonObject);
					
					
					
				}
				try {
					jsonBodyObject.put("device", jsonDeviceArray);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				logger.info("DATESEARCH execution end");
			}
			try{
				jsonBodyObject.put("details", jsonArray);
				jsonBodyObject.put("location", jsonLocationArray);
				
			   } catch (JSONException e) {
			    e.printStackTrace();
			   }
			break;
		case "ALTM":
			/* ALTM=alert management  page.
			 * Operation 0 for select All
			 */
			logger.info("ajax ALTM(alert management) controller Started");
			tableName=SchemaDef.TR_ALERTMANAGMENT;

			List<AppParameters> alertManagementList = threeFieldDao.getAllAlertManagementDetails( tableName,twoFieldPersistence,twoFieldPersistence.getStatus(),empLdapLocation,userId);
			logger.info("list size"+alertManagementList.size());
			jsonArray = new JSONArray();
			for(AppParameters details:alertManagementList){
				JSONObject jsonObject=new JSONObject();
				try {
				    jsonObject.put("Id", details.getId());
					jsonObject.put("location", details.getLocationName());
			    	jsonObject.put("devicename", details.getFailureType());
			    	jsonObject.put("DOF", details.getdOF());
			    	//jsonObject.put("EscalationID", details.getEscalationIDs());
			    	jsonObject.put("EscalationType", details.getEscalationTypes());
			    	jsonObject.put("MSD", details.getmSD());
			    	jsonObject.put("EscalationLevel", details.getEscalationLevels());
			    	jsonObject.put("GroupName", details.getGroupName());
			    	jsonObject.put("EscalationID", details.getEscalationIDs());
			    	jsonObject.put("ActionTaken", details.getActionTaken());
			    	jsonObject.put("ActionDate", details.getActionDate());
			    	jsonObject.put("diffTime", details.getDiffTime());
			    	jsonObject.put("RepeatTime", details.getRepeatTime());
			    	jsonObject.put("Status", details.getStatus());
			    	
				} catch (JSONException e) {
					
					e.printStackTrace();
					logger.info("JSONException "+e.toString());
				}
				jsonArray.put(jsonObject);
			}
			logger.info("ajax EM1(earthpit monitoring) ended"+jsonArray.toString());
			List<AppParameters> locationTableDataListsss= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,empLdapLocation,userId);
			logger.info("ajax EM1(DATESEARCH) ended"+jsonArray.toString());
			jsonLocationArray = new JSONArray();
			for(AppParameters location:locationTableDataListsss){
				JSONObject jsonObject=new JSONObject();
				try {
				    jsonObject.put("locationId", location.getLocationID());
					jsonObject.put("locationName",location.getLocationName());

			    } catch (JSONException e) {
					
					e.printStackTrace();
					logger.info("JSONException "+e.toString());
				}
				jsonLocationArray.put(jsonObject);
			}
			try{
				jsonBodyObject.put("details", jsonArray);
				jsonBodyObject.put("location", jsonLocationArray);
			   } catch (JSONException e) {
			    e.printStackTrace();
			   }
			break;
		case "DATESEARCHFORALERT":
			/* DATESEARCH for Alert management.
			 * Operation 0 for select All
			 */
			logger.info("ajax DATESEARCHFORALERT controller Started");
			tableName=SchemaDef.TR_ALERTMANAGMENT;
			logger.info("ajax location"+twoFieldPersistence.getLocationID());
		
			tableName=SchemaDef.TR_ALERTMANAGMENT;
			List<AppParameters> appParametersListsss = threeFieldDao.getDateAndTimeSearchFOrAlert(tableName,twoFieldPersistence.getLocationID(), twoFieldPersistence.getFailureType(), twoFieldPersistence.getFromdatetime(), twoFieldPersistence.getTodatetime(), twoFieldPersistence);
			logger.info("list size"+appParametersListsss.size());
			jsonArray = new JSONArray();
			for(AppParameters details:appParametersListsss){
				JSONObject jsonObject=new JSONObject();
				try {
					jsonObject.put("Id", details.getId());
					jsonObject.put("location", details.getLocationName());
			    	jsonObject.put("failureType", details.getFailureType());
			    	jsonObject.put("DOF", details.getdOF());
			    	//jsonObject.put("EscalationID", details.getEscalationIDs());
			    	jsonObject.put("EscalationType", details.getEscalationTypes());
			    	jsonObject.put("MSD", details.getmSD());
			    	jsonObject.put("EscalationLevel", details.getEscalationLevels());
			    	jsonObject.put("GroupName", details.getGroupName());
			    	jsonObject.put("EscalationID", details.getEscalationIDs());
			    	jsonObject.put("ActionTaken", details.getActionTaken());
			    	jsonObject.put("ActionDate", details.getActionDate());
			    	jsonObject.put("diffTime", details.getDiffTime());
			    	jsonObject.put("RepeatTime", details.getRepeatTime());
			    	jsonObject.put("Status", details.getStatus());
			    	
				} catch (JSONException e) {
					
					e.printStackTrace();
					logger.info("JSONException "+e.toString());
				}
				jsonArray.put(jsonObject);
				
			}
			List<AppParameters> locationTableDataListData= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,empLdapLocation,userId);
			logger.info("ajax EM1(DATESEARCH) ended"+jsonArray.toString());
			jsonLocationArray = new JSONArray();
			for(AppParameters location:locationTableDataListData){
				JSONObject jsonObject=new JSONObject();
				try {
				    jsonObject.put("locationId", location.getLocationID());
					jsonObject.put("locationName",location.getLocationName());

			    } catch (JSONException e) {
					
					e.printStackTrace();
					logger.info("JSONException "+e.toString());
				}
				jsonLocationArray.put(jsonObject);
			}
			logger.info("**** location :"+twoFieldPersistence.getStatus());
			if(twoFieldPersistence.getStatus().equals("location")){
				
				List<AppParameters> locationDeviceDataList= threeFieldDao.getAllLocationDeviceDetails(twoFieldPersistence.getLocationID());
				logger.info("ajax EM1(earthpit monitoring) ended"+locationDeviceDataList.size());
				jsonDeviceArray = new JSONArray();

				for(AppParameters location:locationDeviceDataList){
					JSONObject jsonObject=new JSONObject();
					try {
					    jsonObject.put("deviceId", location.getDeviceId());
						jsonObject.put("deviceName",location.getDeviceName());

				    	
					} catch (JSONException e) {
						
						e.printStackTrace();
						logger.info("JSONException "+e.toString());
					}
					jsonDeviceArray.put(jsonObject);
					
				}
				try {
					jsonBodyObject.put("device", jsonDeviceArray);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				logger.info("DATESEARCH execution end");
			}
			try{
				jsonBodyObject.put("details", jsonArray);
				jsonBodyObject.put("location", jsonLocationArray);
				
			   } catch (JSONException e) {
			    e.printStackTrace();
			   }
			break;
		case "ALTMUP":
			/* ALTMUP=alert management page.
			 *  Operation update
			 * */
		    tableName=SchemaDef.TR_ALERTMANAGMENT;
			id=SchemaDef.ID;
			threeFieldDao.updateAlertManagementDetails(twoFieldPersistence,tableName);

			break;
			
		    //for Device page changing Time intervel in deiveces.
		    case "TimeInter":
		    	
		      threeFieldPersistenceList = threeFieldDao.getAllDeviceDetails(tableName,id,twoFieldPersistence.getStatus(),empLdapLocation,userId
);
			  String timeintervel= twoFieldPersistence.getTimeInterval();
			  String ipaddress= twoFieldPersistence.getIpAddress();
			  SocketClient socketClient=new SocketClient();
			  socketClient.sendCommandToDevice("command", "ipaddress", 80);
			break;  
			
		    /*case "AMUD1":
		    tableName=SchemaDef.ROLE;	
		    String fieldName=SchemaDef.DESCRIPTION;
		    String value=twoFieldPersistence.getDescription();
		    boolean checkDuplicate=twoFieldDao.checkDuplicateData(tableName, SchemaDef.DESCRIPTION, twoFieldPersistence.getDescription());
			if(checkDuplicate){
				try {
					jsonBodyObject.put("result", "Already Exists Record");
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
		    break;  */
			
		    case "DevicePing":
		    	
			      threeFieldPersistenceList = threeFieldDao.getAllDeviceDetails(tableName,id,twoFieldPersistence.getStatus(),empLdapLocation,userId
);
				  String deviceId= twoFieldPersistence.getDeviceId();
				  SocketClient socket=new SocketClient();
				  socket.sendCommandToDevice("command", "10.90.124.168", 80);
				break;
		        default:
				logger.info("default block");
				break;
		}
	
	   logger.info("Ajax controller ended "+jsonBodyObject.toString());
		return jsonBodyObject.toString();
		}
	
}