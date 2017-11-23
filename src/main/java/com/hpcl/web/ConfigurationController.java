/**
* @author  :Prasad
* @version :1.0
* @since   2015-10-09 
*/package com.hpcl.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hpcl.dao.AdministrationDao;
import com.hpcl.dao.ConfigurationDao;
import com.hpcl.dao.RolePerimissionTablesDataDao;
import com.hpcl.persistence.AppParameters;
import com.hpcl.persistence.LoginPersistance;
import com.hpcl.utill.CurrentDateTime;
import com.hpcl.utill.SchemaDef;
/*
 * TwoFielController
 */
@Controller
public class ConfigurationController {
	String tableName1;
	String tableName2;
	
	String id;
	String tableRowId=null;
	String viewPage;
	
	private static final Logger logger = Logger.getLogger(ConfigurationController.class);
	@Autowired
	ConfigurationDao threeFieldDao;
	
	@Autowired
	AdministrationDao twoFieldDao;
	
	@Autowired
	RolePerimissionTablesDataDao rolePerimissionDao;
	ModelAndView andView=new ModelAndView();
	
	@RequestMapping("/configactions.htm")
    public ModelAndView allDetails(@ModelAttribute AppParameters appParameters,HttpServletRequest request,Map<String, Object> modelSessionOut) throws IOException{	
	logger.info("configactions controller started");
	Map<String, Object> model = new HashMap<String, Object>();
	String page=appParameters.getPage();
	HttpSession session=request.getSession(false);
	if (session == null || session.equals("")){
		LoginPersistance loginPersistance=new LoginPersistance();   
		modelSessionOut.put("userForm", loginPersistance);
		return new ModelAndView("login", modelSessionOut);  
	}else if (session.getAttribute("userId") == null || session.getAttribute("userId").equals("")){
		LoginPersistance loginPersistance=new LoginPersistance();   
		modelSessionOut.put("userForm", loginPersistance);
		
		return new ModelAndView("login", modelSessionOut);  
	}else{
	logger.info("sessin id "+session.getAttribute("userId").toString());
	int pageId=0;
	HashSet<Integer> hashSet=new HashSet<Integer>();
	if(session.getAttribute("userId").toString()!="admin"){
		List<AppParameters> roleList= rolePerimissionDao.getPageId(session.getAttribute("role").toString());
		for(AppParameters parameters:roleList){
			pageId=Integer.parseInt(parameters.getPageID());
			logger.info("Page IDs :"+pageId);
			hashSet.add(pageId);
		}	
	}
	andView.addObject("error", ""); 
	logger.info("Case "+page);
	logger.info("Operation "+appParameters.getOperation());
	String location=session.getAttribute("location").toString();
	String userId=session.getAttribute("userId").toString();
	switch(page){
	case "CP1": 
		
		/* AM1=Employe page.
		 * Operation 0 for select All
		 * Operation 1 for select Specific id
		 * Operation 2 for updateall
		 * Operation 3 for Delete
		 * Operation 4 for Save
		 * Operation 5 for update
		 * */
		 if(session.getAttribute("userId").toString().equals("admin")){
			 viewPage="employee";
				tableName1=SchemaDef.m_employees;
				id=SchemaDef.EMPLOYEEID;
				   if(appParameters.getOperation()==0){
					List<AppParameters> appParametersList = threeFieldDao.getAllDetails(tableName1,tableName2,id,appParameters.getStatus(),location,userId);
					model.put("list",  appParametersList);
					
                      List<AppParameters> roleTableDataList= rolePerimissionDao.getTableData(SchemaDef.ROLE,SchemaDef.ACTIVE_FLAG,location,userId);
                       model.put("role",  roleTableDataList);

					System.out.println("list size"+appParametersList.size());
				}else if(appParameters.getOperation()==1){
				    AppParameters  appParameters1 = threeFieldDao.getDetails(tableRowId, page, page);
					model.put("list",  appParameters1);
					
				}else if(appParameters.getOperation()==2){
					appParameters.setEditedBy(session.getAttribute("userId").toString());
					tableName2="ems_emp_master";
					threeFieldDao.updateDetails(appParameters, tableName1,tableName2);
					List<AppParameters> appParametersList = threeFieldDao.getAllDetails(tableName1,tableName2,id,appParameters.getStatus(),location,userId);
					model.put("list",  appParametersList);
					
					 List<AppParameters> roleTableDataList= rolePerimissionDao.getTableData(SchemaDef.ROLE,SchemaDef.ACTIVE_FLAG,location,userId);
                     model.put("role",  roleTableDataList);
					
				}else if(appParameters.getOperation()==3){
					appParameters.setEditedBy(session.getAttribute("userId").toString());
					tableRowId=appParameters.getEmployeeID();
					logger.info("Delecte Operation");
					threeFieldDao.deleteDetails(tableRowId, tableName1,tableName2,id,appParameters);
					List<AppParameters> appParametersList = threeFieldDao.getAllDetails(tableName1,tableName2,id,appParameters.getStatus(),location,userId);
					model.put("list",appParametersList);
				}else if(appParameters.getOperation()==4){
					appParameters.setCreatedOn(CurrentDateTime.getCurrentDate());
					appParameters.setCreatedBy(session.getAttribute("userId").toString());
					appParameters.setStatus("A");
					
					boolean checkDuplicate=twoFieldDao.checkDuplicateData(tableName1, SchemaDef.EMPLOYEEID, appParameters.getEmployeeID());
					if(checkDuplicate){
						model.put("error",  "Already Exists Record");
						andView.addObject("error", "Already Exists Record"); 
						logger.info("Already Exist");
					}else{
						logger.info("Data Inserted");
						threeFieldDao.saveDetails(appParameters, tableName1,tableName2);
					}
		         	
		         	List<AppParameters> appParametersList = threeFieldDao.getAllDetails(tableName1,tableName2,id,appParameters.getStatus(),location,userId);
					model.put("list",  appParametersList);
			      }else if(appParameters.getOperation()==5){
						tableName2="ems_emp_master";
						threeFieldDao.updateSingleRecord(appParameters, tableName1, tableName2);
						List<AppParameters> appParametersList = threeFieldDao.getAllDetails(tableName1,tableName2,id,appParameters.getStatus(),location,userId);
						model.put("list",  appParametersList);
						
						List<AppParameters> roleTableDataList= rolePerimissionDao.getTableData(SchemaDef.ROLE,SchemaDef.ACTIVE_FLAG,location,userId);
	                       model.put("role",  roleTableDataList);
						
					}
		 }else{
			 if(hashSet.contains(SchemaDef.PAGEEMPLOYEE)){
				 int actionID=0;
					HashSet<Integer> hashSetAction=new HashSet<Integer>();
						List<AppParameters> roleList= rolePerimissionDao.getActionId(session.getAttribute("role").toString());
						for(AppParameters parameters:roleList){
							actionID=parameters.getActionID();
							logger.info("Page IDs :"+actionID);
							hashSetAction.add(actionID);
						}
				    viewPage="employee";
					tableName1=SchemaDef.m_employees;
					id=SchemaDef.EMPLOYEEID;
					   if(appParameters.getOperation()==0){
						   if(hashSetAction.contains(SchemaDef.PAGESELECT)){
							   List<AppParameters> appParametersList = threeFieldDao.getAllDetails(tableName1,tableName2,id,appParameters.getStatus(),location,userId);
								model.put("list",  appParametersList);
								
								List<AppParameters> roleTableDataList= rolePerimissionDao.getTableData(SchemaDef.ROLE,SchemaDef.ACTIVE_FLAG,location,userId);
			                       model.put("role",  roleTableDataList);
								
								System.out.println("list size"+appParametersList.size());
						   }else{
							   viewPage="unauthorized";
						   }
						
					}else if(appParameters.getOperation()==1){
					    AppParameters  appParameters1 = threeFieldDao.getDetails(tableRowId, page, page);
						model.put("list",  appParameters1);
						
					}else if(appParameters.getOperation()==2){
						if(hashSetAction.contains(SchemaDef.PAGEUPDATE)){
							appParameters.setEditedBy(session.getAttribute("userId").toString());
							tableName2="ems_emp_master";
							threeFieldDao.updateDetails(appParameters, tableName1,tableName2);
							List<AppParameters> appParametersList = threeFieldDao.getAllDetails(tableName1,tableName2,id,appParameters.getStatus(),location,userId);
							model.put("list",  appParametersList);
						}else{
							viewPage="unauthorized";	
						}
						
						
					}else if(appParameters.getOperation()==3){
						if(hashSetAction.contains(SchemaDef.PAGEDELETE)){
							appParameters.setEditedBy(session.getAttribute("userId").toString());
							tableRowId=appParameters.getEmployeeID();
							logger.info("Delecte Operation");
							threeFieldDao.deleteDetails(tableRowId, tableName1,tableName2,id,appParameters);
							List<AppParameters> appParametersList = threeFieldDao.getAllDetails(tableName1,tableName2,id,appParameters.getStatus(),location,userId);
							model.put("list",appParametersList);
						}else{
							viewPage="unauthorized";
						}
						
					}else if(appParameters.getOperation()==4){
						if(hashSetAction.contains(SchemaDef.PAGEINSERT)){
							appParameters.setCreatedOn(CurrentDateTime.getCurrentDate());
							appParameters.setCreatedBy(session.getAttribute("userId").toString());
							appParameters.setStatus("A");
							
							boolean checkDuplicate=twoFieldDao.checkDuplicateData(tableName1, SchemaDef.EMPLOYEEID, appParameters.getEmployeeID());
							if(checkDuplicate){
								 model.put("error",  "Already Exists Record");
								 andView.addObject("error", "Already Exists Record");
								logger.info("Already Exists Record");
							}else{
								logger.info("Data Inserted");
								threeFieldDao.saveDetails(appParameters, tableName1,tableName2);
							}
				         	
				         	List<AppParameters> appParametersList = threeFieldDao.getAllDetails(tableName1,tableName2,id,appParameters.getStatus(),location,userId);
							model.put("list",  appParametersList);
						}else{
							viewPage="unauthorized";
						}
						
				      }else if(appParameters.getOperation()==5){
				    	  if(hashSetAction.contains(SchemaDef.PAGEINSERT)){
				    		  appParameters.setEditedBy(session.getAttribute("userId").toString());
				    		  tableName2="ems_emp_master";
								threeFieldDao.updateSingleRecord(appParameters, tableName1, tableName2);
								List<AppParameters> appParametersList = threeFieldDao.getAllDetails(tableName1,tableName2,id,appParameters.getStatus(),location,userId);
								model.put("list",  appParametersList);
				    	  }else{
				    		  viewPage="unauthorized";   
				    	  }
						}
					
			 }else
			 {
				 viewPage="unauthorized"; 
			 }
		 }
		
		break;
	case "CP2": 
		/* cp2=Location page.
		 * Operation 0 for select All
		 * Operation 1 for select Specific id
		 * Operation 2 for update
		 * Operation 3 for Delete
		 * Operation 4 for Save
		 * */
		   if(session.getAttribute("userId").toString().equals("admin")){
			   viewPage="location";
				tableName1=SchemaDef.M_LOCATIONS;
				id=SchemaDef.LOCATIONID;
				   if(appParameters.getOperation()==0){
					List<AppParameters> appParametersList = threeFieldDao.getAllLocationDetails(tableName1, tableName2,id,appParameters.getStatus(),location,userId);
					model.put("list",  appParametersList);
					
				}else if(appParameters.getOperation()==1){
				
					AppParameters  appParameters1 = threeFieldDao.getDetails(tableRowId, page, page);
					model.put("list",  appParameters1);
					
				}else if(appParameters.getOperation()==2){
					appParameters.setEditedBy(session.getAttribute("userId").toString());
					//tableName2="templdap_location";
					tableName2="ems_loc_master";
					threeFieldDao.updateDetails(appParameters, tableName1,tableName2);
					List<AppParameters> appParametersList = threeFieldDao.getAllLocationDetails(tableName1,tableName2,id,appParameters.getStatus(),location,userId);
					model.put("list",  appParametersList);
					
				}else if(appParameters.getOperation()==3){
					appParameters.setEditedBy(session.getAttribute("userId").toString());
					tableRowId=appParameters.getLocationID();
					logger.info("Delecte Operation");
					threeFieldDao.deleteDetails(tableRowId, tableName1,tableName2,id,appParameters);
					List<AppParameters> appParametersList = threeFieldDao.getAllLocationDetails(tableName1,tableName2,id,appParameters.getStatus(),location,userId);
					model.put("list",appParametersList);
				}else if(appParameters.getOperation()==4){
					appParameters.setCreatedOn(CurrentDateTime.getCurrentDate());
					appParameters.setCreatedBy(session.getAttribute("userId").toString());
					appParameters.setStatus("A");
					
					boolean checkDuplicate=twoFieldDao.checkDuplicateData(tableName1, SchemaDef.LOCATIONID, appParameters.getLocationID());
					if(checkDuplicate){
						model.put("error",  "Already Exists Record");
						andView.addObject("error", "Already Exists Record");
						logger.info("Already Exist");
					}else{
						logger.info("Data Inserted");
						threeFieldDao.saveDetails(appParameters, tableName1,tableName2);
					}
		         	
		         	List<AppParameters> appParametersList = threeFieldDao.getAllLocationDetails(tableName1,tableName2,id,appParameters.getStatus(),location,userId);
					model.put("list",  appParametersList);
			        System.out.println("List size "+appParametersList.size());
			        
			      }else if(appParameters.getOperation()==5){
						//tableName2="templdap_location";
			    	     tableName2="ems_loc_master";
						threeFieldDao.updateSingleRecord(appParameters, tableName1, tableName2);
						List<AppParameters> appParametersList = threeFieldDao.getAllLocationDetails(tableName1,tableName2,id,appParameters.getStatus(),location,userId);
						
				        model.put("list",  appParametersList);
						
					}
		   }else{
			   if(hashSet.contains(SchemaDef.PAGELOCATION)){
				   int actionID=0;
					HashSet<Integer> hashSetAction=new HashSet<Integer>();
						List<AppParameters> roleList= rolePerimissionDao.getActionId(session.getAttribute("role").toString());
						for(AppParameters parameters:roleList){
							actionID=parameters.getActionID();
							logger.info("Page IDs :"+actionID);
							hashSetAction.add(actionID);
						}
				   viewPage="location";
					tableName1=SchemaDef.M_LOCATIONS;
					id=SchemaDef.LOCATIONID;
					
					   if(appParameters.getOperation()==0){
						   if(hashSetAction.contains(SchemaDef.PAGESELECT)){
							   List<AppParameters> appParametersList = threeFieldDao.getAllLocationDetails(tableName1, tableName2,id,appParameters.getStatus(),location,userId);
								model.put("list",  appParametersList);  
						   }else{
							   viewPage="unauthorized";  
						   }
						
						
					}else if(appParameters.getOperation()==1){
					
						AppParameters  appParameters1 = threeFieldDao.getDetails(tableRowId, page, page);
						model.put("list",  appParameters1);
						
					}else if(appParameters.getOperation()==2){
						if(hashSetAction.contains(SchemaDef.PAGEUPDATE)){
							appParameters.setEditedBy(session.getAttribute("userId").toString());
							//tableName2="templdap_location";
							tableName2="ems_loc_master";
							threeFieldDao.updateDetails(appParameters, tableName1,tableName2);
							List<AppParameters> appParametersList = threeFieldDao.getAllLocationDetails(tableName1,tableName2,id,appParameters.getStatus(),location,userId);
							model.put("list",  appParametersList);
						}else{
							viewPage="unauthorized"; 
						}
						
						
					}else if(appParameters.getOperation()==3){
						if(hashSetAction.contains(SchemaDef.PAGEDELETE)){
							appParameters.setEditedBy(session.getAttribute("userId").toString());
							tableRowId=appParameters.getLocationID();
							logger.info("Delecte Operation");
							threeFieldDao.deleteDetails(tableRowId, tableName1,tableName2,id,appParameters);
							List<AppParameters> appParametersList = threeFieldDao.getAllLocationDetails(tableName1,tableName2,id,appParameters.getStatus(),location,userId);
							model.put("list",appParametersList);
						}else{
							viewPage="unauthorized"; 
						}
						
					}else if(appParameters.getOperation()==4){
						if(hashSetAction.contains(SchemaDef.PAGEINSERT)){
							appParameters.setCreatedOn(CurrentDateTime.getCurrentDate());
							appParameters.setCreatedBy(session.getAttribute("userId").toString());
							appParameters.setStatus("A");
							
							boolean checkDuplicate=twoFieldDao.checkDuplicateData(tableName1, SchemaDef.LOCATIONID, appParameters.getLocationID());
							if(checkDuplicate){
								model.put("error",  "Already Exists Record");
								andView.addObject("error", "Already Exists Record");
								logger.info("Already Exist");
							}else{
								logger.info("Data Inserted");
								threeFieldDao.saveDetails(appParameters, tableName1,tableName2);
							}
				         	
				         	List<AppParameters> appParametersList = threeFieldDao.getAllLocationDetails(tableName1,tableName2,id,appParameters.getStatus(),location,userId);
							model.put("list",  appParametersList);
					        System.out.println("List size "+appParametersList.size());
						}else{
							viewPage="unauthorized"; 
						}
						
				        
				      }else if(appParameters.getOperation()==5){
				    	  if(hashSetAction.contains(SchemaDef.PAGEINSERT)){
				    		  //tableName2="templdap_location";
				    		  tableName2="ems_loc_master";
								threeFieldDao.updateSingleRecord(appParameters, tableName1, tableName2);
								List<AppParameters> appParametersList = threeFieldDao.getAllLocationDetails(tableName1,tableName2,id,appParameters.getStatus(),location,userId);
								model.put("list",  appParametersList); 
				    	  }else{
				    		  viewPage="unauthorized";   
				    	  }
						}  
			   }else{
				   viewPage="unauthorized"; 
			   }
				   
		   }
		
		   break;
	   case "CP3": 
			 logger.info("Configuration device controller strarted");
			 
			/* CP3= device page.
			 * Operation 0 for select All
			 * Operation 1 for select Specific id
			 * Operation 2 for update
			 * Operation 3 for Delete
			 * Operation 4 for Save
			 * */
					 if(session.getAttribute("userId").toString().equals("admin")){
						 viewPage="device";
							tableName1=SchemaDef.M_DEVICE;
							id=SchemaDef.DEVICE_ID;
							   if(appParameters.getOperation()==0){
								   List<AppParameters> roleTableDataList= rolePerimissionDao.getTableData(SchemaDef.ROLE,SchemaDef.ACTIVE_FLAG,location,userId);
								List<AppParameters> appParametersList = threeFieldDao.getAllDeviceDetails(tableName1,id,appParameters.getStatus(),location,userId);
								model.put("list",  appParametersList);
								
								List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
								model.put("location",  locationTableDataList);
								model.put("role",  roleTableDataList);
							  }else if(appParameters.getOperation()==1){
							
								AppParameters  appParameters1 = threeFieldDao.getDetails(tableRowId, page, page);
								model.put("list",  appParameters1);
								
							}else if(appParameters.getOperation()==2){
								appParameters.setEditedBy(session.getAttribute("userId").toString());
					            threeFieldDao.updateDeviceDetails(appParameters, tableName1);
					            List<AppParameters> roleTableDataList= rolePerimissionDao.getTableData(SchemaDef.ROLE,SchemaDef.ACTIVE_FLAG,location,userId);
								List<AppParameters> appParametersList = threeFieldDao.getAllDeviceDetails(tableName1,id,appParameters.getStatus(),location,userId);
								model.put("list",  appParametersList);
								List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
								model.put("location",  locationTableDataList);
								model.put("role",  roleTableDataList);
								
							}else if(appParameters.getOperation()==3){
								appParameters.setEditedBy(session.getAttribute("userId").toString());
								//tableRowId=appParameters.getDeviceId();
								logger.info("DeviceDelecte Operation");
								threeFieldDao.deleteDeviceDetails(appParameters,tableName1);
								List<AppParameters> roleTableDataList= rolePerimissionDao.getTableData(SchemaDef.ROLE,SchemaDef.ACTIVE_FLAG,location,userId);
								List<AppParameters> appParametersList = threeFieldDao.getAllDeviceDetails(tableName1,id,appParameters.getStatus(),location,userId);
								model.put("list",  appParametersList);
								List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
								model.put("location",  locationTableDataList);
								model.put("role",  roleTableDataList);
							}else if(appParameters.getOperation()==4){
								appParameters.setCreatedOn(CurrentDateTime.getCurrentDate());
								appParameters.setCreatedBy(session.getAttribute("userId").toString());
								appParameters.setStatus("A");
								boolean checkDuplicate=twoFieldDao.checkDuplicateData(tableName1, SchemaDef.DEVICE_ID, appParameters.getDeviceId());
								if(checkDuplicate){
									model.put("error",  "Already Exists Record");
									andView.addObject("error", "Already Exists Record");
									logger.info("Already Exist");
								}else{
									logger.info("Data Inserted");
									threeFieldDao.saveDeviceDetails(appParameters, tableName1);
								}
								
					         	//threeFieldDao.saveDeviceDetails(appParameters, tableName1);
					         	List<AppParameters> roleTableDataList= rolePerimissionDao.getTableData(SchemaDef.ROLE,SchemaDef.ACTIVE_FLAG,location,userId);
								List<AppParameters> appParametersList = threeFieldDao.getAllDeviceDetails(tableName1,id,appParameters.getStatus(),location,userId);
								model.put("list",  appParametersList);
								List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
								model.put("location",  locationTableDataList);
								model.put("role",  roleTableDataList);
						        System.out.println("List size "+appParametersList.size());
						        
						      }
							 logger.info("Configuration devices controller strarted");
					 } else{
						 if(hashSet.contains(SchemaDef.PAGEDEVICE)){
							    int actionID=0;
								HashSet<Integer> hashSetAction=new HashSet<Integer>();
									List<AppParameters> roleList= rolePerimissionDao.getActionId(session.getAttribute("role").toString());
									for(AppParameters parameters:roleList){
										actionID=parameters.getActionID();
										logger.info("Page IDs :"+actionID);
										hashSetAction.add(actionID);
									}
							 viewPage="device";
								tableName1=SchemaDef.M_DEVICE;
								id=SchemaDef.DEVICE_ID;
								   if(appParameters.getOperation()==0){
									   if(hashSetAction.contains(SchemaDef.PAGESELECT)){
										   List<AppParameters> roleTableDataList= rolePerimissionDao.getTableData(SchemaDef.ROLE,SchemaDef.ACTIVE_FLAG,location,userId);
											List<AppParameters> appParametersList = threeFieldDao.getAllDeviceDetails(tableName1,id,appParameters.getStatus(),location,userId);
											model.put("list",  appParametersList);
											List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
											model.put("location",  locationTableDataList);
											model.put("role",  roleTableDataList); 
									   }else{
										   viewPage="unauthorized";   
									   }
									
									
								}else if(appParameters.getOperation()==1){
								
									AppParameters  appParameters1 = threeFieldDao.getDetails(tableRowId, page, page);
									model.put("list",  appParameters1);
									
								}else if(appParameters.getOperation()==2){
									if(hashSetAction.contains(SchemaDef.PAGEUPDATE)){
										appParameters.setEditedBy(session.getAttribute("userId").toString());
										threeFieldDao.updateDeviceDetails(appParameters, tableName1);
										List<AppParameters> roleTableDataList= rolePerimissionDao.getTableData(SchemaDef.ROLE,SchemaDef.ACTIVE_FLAG,location,userId);
										List<AppParameters> appParametersList = threeFieldDao.getAllDeviceDetails(tableName1,id,appParameters.getStatus(),location,userId);
										model.put("list",  appParametersList);
										List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
										model.put("location",  locationTableDataList);
										model.put("role",  roleTableDataList);
									}else{
										viewPage="unauthorized"; 
									}
						            
									
								}else if(appParameters.getOperation()==3){
									if(hashSetAction.contains(SchemaDef.PAGEDELETE)){
										appParameters.setEditedBy(session.getAttribute("userId").toString());
										//tableRowId=appParameters.getDeviceId();
										logger.info("DeviceDelecte Operation");
										threeFieldDao.deleteDeviceDetails(appParameters,tableName1);
										List<AppParameters> roleTableDataList= rolePerimissionDao.getTableData(SchemaDef.ROLE,SchemaDef.ACTIVE_FLAG,location,userId);
										List<AppParameters> appParametersList = threeFieldDao.getAllDeviceDetails(tableName1,id,appParameters.getStatus(),location,userId);
										model.put("list",  appParametersList);
										List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
										model.put("location",  locationTableDataList);
										model.put("role",  roleTableDataList);
									}else{
										viewPage="unauthorized";	
									}
									
								}else if(appParameters.getOperation()==4){
									if(hashSetAction.contains(SchemaDef.PAGEINSERT)){
										appParameters.setCreatedOn(CurrentDateTime.getCurrentDate());
										appParameters.setCreatedBy(session.getAttribute("userId").toString());
										appParameters.setStatus("A");
										boolean checkDuplicate=twoFieldDao.checkDuplicateData(tableName1, SchemaDef.DEVICE_ID, appParameters.getDeviceId());
										if(checkDuplicate){
											model.put("error",  "Already Exists Record");
											andView.addObject("error", "Already Exists Record");
											logger.info("Already Exist");
										}else{
											logger.info("Data Inserted");
											threeFieldDao.saveDeviceDetails(appParameters, tableName1);
										}
							         	//threeFieldDao.saveDeviceDetails(appParameters, tableName1);
							         	List<AppParameters> roleTableDataList= rolePerimissionDao.getTableData(SchemaDef.ROLE,SchemaDef.ACTIVE_FLAG,location,userId);
										List<AppParameters> appParametersList = threeFieldDao.getAllDeviceDetails(tableName1,id,appParameters.getStatus(),location,userId);
										model.put("list",  appParametersList);
										List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
										model.put("location",  locationTableDataList);
										model.put("role",  roleTableDataList);
								        System.out.println("List size "+appParametersList.size());
									}else{
										viewPage="unauthorized";
									}
								}
								 logger.info("Configuration devices controller strarted");
						 }else{
							 
							 viewPage="unauthorized";  
						 }
							 
						 
					 }
			
			break;
		   case "CP4": 
			 logger.info("Configuration earthpits controller strarted");
			/* CP4= earthpits page.
			 * Operation 0 for select All
			 * Operation 1 for select Specific id
			 * Operation 2 for update
			 * Operation 3 for Delete
			 * Operation 4 for Save
			 * */
			 if(session.getAttribute("userId").toString().equals("admin")){
				 viewPage="earthpits";
					tableName1=SchemaDef.M_EARTHPITS;
					id=SchemaDef.EARTHPIT_ID;
					   if(appParameters.getOperation()==0){
						List<AppParameters> appParametersList = threeFieldDao.getAllEarthpitsDetails(tableName1,id,appParameters.getStatus(),location,userId);
						model.put("list",  appParametersList);
						List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
						model.put("location",  locationTableDataList);
						
						
					}else if(appParameters.getOperation()==1){
					
						AppParameters  appParameters1 = threeFieldDao.getDetails(tableRowId, page, page);
						model.put("list",  appParameters1);
						
					}else if(appParameters.getOperation()==2){
						appParameters.setEditedBy(session.getAttribute("userId").toString());
						
						threeFieldDao.updateEarthpitsDetails(appParameters, tableName1);
						List<AppParameters> appParametersList = threeFieldDao.getAllEarthpitsDetails(tableName1,id,appParameters.getStatus(),location,userId);
						model.put("list",  appParametersList);
						List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
						model.put("location",  locationTableDataList);
						
					}else if(appParameters.getOperation()==3){
						appParameters.setEditedBy(session.getAttribute("userId").toString());
						tableRowId=appParameters.getEarthpitID();
						logger.info("Deletearthpit  Operation" +tableRowId);
						threeFieldDao.deleteEarthpitsDetails(appParameters, tableName1);
						List<AppParameters> appParametersList = threeFieldDao.getAllEarthpitsDetails(tableName1,id,appParameters.getStatus(),location,userId);
						model.put("list",  appParametersList);
						List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
						model.put("location",  locationTableDataList);
					}else if(appParameters.getOperation()==4){
						appParameters.setCreatedOn(CurrentDateTime.getCurrentDate());
						appParameters.setCreatedBy(session.getAttribute("userId").toString());
						appParameters.setStatus("A");
						boolean checkDuplicate=twoFieldDao.checkDuplicateData(tableName1, SchemaDef.EARTHPIT_NAME, appParameters.getEarthpitName());
						if(checkDuplicate){
							model.put("error",  "Already Exists Record");
							andView.addObject("error", "Already Exists Record");
							logger.info("Already Exist");
						}else{
							logger.info("Data Inserted");
							threeFieldDao.saveEarthptsDetails(appParameters, tableName1);
						}
			         	//threeFieldDao.saveEarthptsDetails(appParameters, tableName1);
			         	List<AppParameters> appParametersList = threeFieldDao.getAllEarthpitsDetails(tableName1,id,appParameters.getStatus(),location,userId);
						model.put("list",  appParametersList);
						List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
						model.put("location",  locationTableDataList);
				        System.out.println("List size "+appParametersList.size());
				        
				      }
					   logger.info("Configuration earthpits. controller end");
			 }else{
				 if(hashSet.contains(SchemaDef.PAGEEARTHPIT)){
					 int actionID=0;
						HashSet<Integer> hashSetAction=new HashSet<Integer>();
							List<AppParameters> roleList= rolePerimissionDao.getActionId(session.getAttribute("role").toString());
							for(AppParameters parameters:roleList){
								actionID=parameters.getActionID();
								logger.info("Page IDs :"+actionID);
								hashSetAction.add(actionID);
							}
					    viewPage="earthpits";
						tableName1=SchemaDef.M_EARTHPITS;
						id=SchemaDef.EARTHPIT_ID;
						   if(appParameters.getOperation()==0){
							   if(hashSetAction.contains(SchemaDef.PAGESELECT)){
								   List<AppParameters> appParametersList = threeFieldDao.getAllEarthpitsDetails(tableName1,id,appParameters.getStatus(),location,userId);
									model.put("list",  appParametersList);
									List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
									model.put("location",  locationTableDataList);
							   }else{
								   viewPage="unauthorized";    
							   }
							
							
							
						}else if(appParameters.getOperation()==1){
						
							AppParameters  appParameters1 = threeFieldDao.getDetails(tableRowId, page, page);
							model.put("list",  appParameters1);
							
						}else if(appParameters.getOperation()==2){
							if(hashSetAction.contains(SchemaDef.PAGEUPDATE)){
								appParameters.setEditedBy(session.getAttribute("userId").toString());
								threeFieldDao.updateEarthpitsDetails(appParameters, tableName1);
								logger.info("Update End...");
								List<AppParameters> appParametersList = threeFieldDao.getAllEarthpitsDetails(tableName1,id,appParameters.getStatus(),location,userId);
								model.put("list",  appParametersList);
								List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
								model.put("location",  locationTableDataList);
							}else{
								viewPage="unauthorized";
							}
							
							
							
						}else if(appParameters.getOperation()==3){
							if(hashSetAction.contains(SchemaDef.PAGEDELETE)){
								appParameters.setEditedBy(session.getAttribute("userId").toString());
								tableRowId=appParameters.getEarthpitID();
								logger.info("Deletearthpit  Operation" +tableRowId);
								threeFieldDao.deleteEarthpitsDetails(appParameters, tableName1);
								List<AppParameters> appParametersList = threeFieldDao.getAllEarthpitsDetails(tableName1,id,appParameters.getStatus(),location,userId);
								model.put("list",  appParametersList);
								List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
								model.put("location",  locationTableDataList);
							}else{
							  viewPage="unauthorized";
							}
							
						}else if(appParameters.getOperation()==4){
							if(hashSetAction.contains(SchemaDef.PAGEINSERT)){
								appParameters.setCreatedOn(CurrentDateTime.getCurrentDate());
								appParameters.setCreatedBy(session.getAttribute("userId").toString());
								appParameters.setStatus("A");
								
								boolean checkDuplicate=twoFieldDao.checkDuplicateData(tableName1, SchemaDef.EARTHPIT_NAME, appParameters.getEarthpitName());
								if(checkDuplicate){
									model.put("error",  "Already Exists Record");
									andView.addObject("error", "Already Exists Record");
									logger.info("Already Exist");
								}else{
									logger.info("Data Inserted");
									threeFieldDao.saveEarthptsDetails(appParameters, tableName1);
								}
					         	//threeFieldDao.saveEarthptsDetails(appParameters, tableName1);
					         	List<AppParameters> appParametersList = threeFieldDao.getAllEarthpitsDetails(tableName1,id,appParameters.getStatus(),location,userId);
								model.put("list",  appParametersList);
								List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
								model.put("location",  locationTableDataList);
						        System.out.println("List size "+appParametersList.size());
							}else{
								viewPage="unauthorized";
							}
						 }
						   logger.info("Configuration earthpits. controller end"); 
				     }else
				      {
					 viewPage="unauthorized";  
				 }
				  }
			
				break;
			   case "CP5": 
				 logger.info("Configuration escalation controller strarted");
				/* CP5= escalation page.
				 * Operation 0 for select All
				 * Operation 1 for select Specific id
				 * Operation 2 for update
				 * Operation 3 for Delete
				 * Operation 4 for Save
				 * */
				 if(session.getAttribute("userId").toString().equals("admin")){
						viewPage="escalation";
						String tableName=null;
						tableName=SchemaDef.M_ESCALATION;
						id=SchemaDef.ESCALATIONID;
						   if(appParameters.getOperation()==0){
							List<AppParameters> appParametersList = threeFieldDao.getAllEscalationDetails( tableName,id,appParameters.getStatus(),location,userId);
							model.put("list",  appParametersList);
							logger.info("all details size "+appParametersList.size());
							
							 List<AppParameters> roleTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_GROUPS ,SchemaDef.ACTIVE_FLAG,location,userId);
							 model.put("group",  roleTableDataList);
						   }else if(appParameters.getOperation()==1){
						
							AppParameters  appParameters1 = threeFieldDao.getDetails(tableRowId, page, page);
							model.put("list",  appParameters1);
							
						}else if(appParameters.getOperation()==2){
							appParameters.setEditedBy(session.getAttribute("userId").toString());
							threeFieldDao.updateEscalationDetails(appParameters, tableName);
							List<AppParameters> appParametersList = threeFieldDao.getAllEscalationDetails( tableName,id,appParameters.getStatus(),location,userId);
							model.put("list",  appParametersList);
							logger.info("all details size "+appParametersList.size());
							
							 List<AppParameters> roleTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_GROUPS ,SchemaDef.ACTIVE_FLAG,location,userId);
							 model.put("group",  roleTableDataList);
							
						}else if(appParameters.getOperation()==3){
							appParameters.setEditedBy(session.getAttribute("userId").toString());
							String tableRowId=appParameters.getEscalationIDs();
							logger.info("Delecte Operation");
							threeFieldDao.deleteEscalationDetails(appParameters,tableName,id);
							List<AppParameters> appParametersList = threeFieldDao.getAllEscalationDetails( tableName,id,appParameters.getStatus(),location,userId);
							model.put("list",  appParametersList);
							logger.info("all details size "+appParametersList.size());
							 List<AppParameters> roleTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_GROUPS ,SchemaDef.ACTIVE_FLAG,location,userId);
							 model.put("group",  roleTableDataList);
						}else if(appParameters.getOperation()==4){
							logger.info("operation 4 started");
							appParameters.setCreatedOn(CurrentDateTime.getCurrentDate());
							appParameters.setCreatedBy(session.getAttribute("userId").toString());
							appParameters.setStatus("A");
							boolean checkDuplicate=twoFieldDao.checkDuplicateData(tableName, SchemaDef.ESCALATIONID, appParameters.getEscalationIDs());
							if(checkDuplicate){
								model.put("error",  "Already Exists Record");
								andView.addObject("error", "Already Exists Record");
								logger.info("Already Exist");
							}else{
								logger.info("Data Inserted");
								threeFieldDao.saveEscalationDetails(appParameters, tableName);
							}
							tableName1=SchemaDef.M_ESCALATION;
				         	
				         	List<AppParameters> appParametersList = threeFieldDao.getAllEscalationDetails( tableName,id,appParameters.getStatus(),location,userId);
							model.put("list",  appParametersList);
							logger.info("all details size "+appParametersList.size());
							
							 List<AppParameters> roleTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_GROUPS ,SchemaDef.ACTIVE_FLAG,location,userId);
							 model.put("group",  roleTableDataList);
					        System.out.println("List size "+appParametersList.size());
					        
					      }
				 }else{
					if(hashSet.contains(SchemaDef.PAGEEASCALATION)){
						int actionID=0;
						HashSet<Integer> hashSetAction=new HashSet<Integer>();
							List<AppParameters> roleList= rolePerimissionDao.getActionId(session.getAttribute("role").toString());
							for(AppParameters parameters:roleList){
								actionID=parameters.getActionID();
								logger.info("Page IDs :"+actionID);
								hashSetAction.add(actionID);
							}
						viewPage="escalation";
						String tableName=null;
						tableName=SchemaDef.M_ESCALATION;
						id=SchemaDef.ESCALATIONID;
						   if(appParameters.getOperation()==0){
							   if(hashSetAction.contains(SchemaDef.PAGESELECT)){
								   List<AppParameters> appParametersList = threeFieldDao.getAllEscalationDetails( tableName,id,appParameters.getStatus(),location,userId);
									model.put("list",  appParametersList);
									 List<AppParameters> roleTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_GROUPS ,SchemaDef.ACTIVE_FLAG,location,userId);
									 model.put("group",  roleTableDataList);
									logger.info("all details size "+appParametersList.size());
								   
							   }else{
								   viewPage="unauthorized";  
							   }
							
							
							
						   }else if(appParameters.getOperation()==1){
						
							AppParameters  appParameters1 = threeFieldDao.getDetails(tableRowId, page, page);
							model.put("list",  appParameters1);
							
						}else if(appParameters.getOperation()==2){
							if(hashSetAction.contains(SchemaDef.PAGEUPDATE)){
								appParameters.setEditedBy(session.getAttribute("userId").toString());
								threeFieldDao.updateEscalationDetails(appParameters, tableName);
								 List<AppParameters> appParametersList = threeFieldDao.getAllEscalationDetails( tableName,id,appParameters.getStatus(),location,userId);
									model.put("list",  appParametersList);
									
									List<AppParameters> roleTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_GROUPS ,SchemaDef.ACTIVE_FLAG,location,userId);
									 model.put("group",  roleTableDataList);
							}else{
								viewPage="unauthorized";
							}
							
							
						}else if(appParameters.getOperation()==3){
							if(hashSetAction.contains(SchemaDef.PAGEUPDATE)){
								appParameters.setEditedBy(session.getAttribute("userId").toString());
								String tableRowId=appParameters.getEscalationIDs();
								logger.info("Delecte Operation");
								threeFieldDao.deleteEscalationDetails(appParameters,tableName,id);
								 List<AppParameters> appParametersList = threeFieldDao.getAllEscalationDetails( tableName,id,appParameters.getStatus(),location,userId);
									model.put("list",  appParametersList);
									 List<AppParameters> roleTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_GROUPS ,SchemaDef.ACTIVE_FLAG,location,userId);
									 model.put("group",  roleTableDataList);
							}else{
								viewPage="unauthorized";
							}
							
						}else if(appParameters.getOperation()==4){
							if(hashSetAction.contains(SchemaDef.PAGEINSERT)){
								logger.info("operation 4 started");
								appParameters.setCreatedOn(CurrentDateTime.getCurrentDate());
								appParameters.setCreatedBy(session.getAttribute("userId").toString());
								appParameters.setStatus("A");
								tableName1=SchemaDef.M_ESCALATION;
								boolean checkDuplicate=twoFieldDao.checkDuplicateData(tableName, SchemaDef.ESCALATIONID, appParameters.getEscalationIDs());
								if(checkDuplicate){
									model.put("error",  "Already Exists Record");
									andView.addObject("error", "Already Exists Record");
									logger.info("Already Exist");
								}else{
									logger.info("Data Inserted");
									threeFieldDao.saveEscalationDetails(appParameters, tableName);
								}
					         	
					         	 List<AppParameters> appParametersList = threeFieldDao.getAllEscalationDetails( tableName,id,appParameters.getStatus(),location,userId);
									model.put("list",  appParametersList);
									 List<AppParameters> roleTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_GROUPS ,SchemaDef.ACTIVE_FLAG,location,userId);
									 model.put("group",  roleTableDataList);
						        System.out.println("List size "+appParametersList.size());
							}else{
								viewPage="unauthorized";
							}
						   }
					     }else{
						 viewPage="unauthorized";  
					}
				 }
					 
				
				break;
			   case "CP6": 
				 logger.info("Configuration device-earthpits controller strarted");
				/* CP6= device-earthpit page.
				 * Operation 0 for select All
				 * Operation 1 for select Specific id
				 * Operation 2 for update
				 * Operation 3 for Delete
				 * Operation 4 for Save
				 * */
				 if(session.getAttribute("userId").toString().equals("admin")){
					 viewPage="device-earthpit";
						tableName1=SchemaDef.M_DEVICE_EARTHPITS;
						id=SchemaDef.DEVICE_EARTHPIT_ID;
						   if(appParameters.getOperation()==0){
							List<AppParameters> appParametersList = threeFieldDao.getAllDeviceEarthpitsDetails(tableName1,id,appParameters.getStatus(),location,userId);
							model.put("list",  appParametersList);
							
							List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
							model.put("location",  locationTableDataList);
							
							
						}else if(appParameters.getOperation()==1){
						
							AppParameters  appParameters1 = threeFieldDao.getDetails(tableRowId, page, page);
							model.put("list",  appParameters1);
							
						}else if(appParameters.getOperation()==2){
							appParameters.setEditedBy(session.getAttribute("userId").toString());
							//tableName2="templdap_location";
							tableName2="ems_loc_master";
							threeFieldDao.updateDetails(appParameters, tableName1,tableName2);
							List<AppParameters> appParametersList = threeFieldDao.getAllDeviceEarthpitsDetails(tableName1,id,appParameters.getStatus(),location,userId);
							model.put("list",  appParametersList);
							
							List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
							model.put("location",  locationTableDataList);
							
						}else if(appParameters.getOperation()==3){
							appParameters.setEditedBy(session.getAttribute("userId").toString());
							tableRowId=appParameters.getDeviceearthpitId();
							logger.info("Delecte Operation");
							
							threeFieldDao.deleteEarthptsDetails(appParameters, tableName1);
							List<AppParameters> appParametersList = threeFieldDao.getAllDeviceEarthpitsDetails(tableName1,id,appParameters.getStatus(),location,userId);
							model.put("list",  appParametersList);
							
							List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
							model.put("location",  locationTableDataList);
						}else if(appParameters.getOperation()==4){
							appParameters.setCreatedOn(CurrentDateTime.getCurrentDate());
							appParameters.setCreatedBy(session.getAttribute("userId").toString());
							appParameters.setStatus("A");
							
				         	threeFieldDao.saveDetails(appParameters, tableName1,tableName2);
				         	List<AppParameters> appParametersList = threeFieldDao.getAllDeviceEarthpitsDetails(tableName1,id,appParameters.getStatus(),location,userId);
							model.put("list",  appParametersList);
							
							List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
							model.put("location",  locationTableDataList);
					        System.out.println("List size "+appParametersList.size());
					        
					      }
				 }else{
					 if(hashSet.contains(SchemaDef.PAGEDEVICEEARTHPIT)){
						 int actionID=0;
							HashSet<Integer> hashSetAction=new HashSet<Integer>();
								List<AppParameters> roleList= rolePerimissionDao.getActionId(session.getAttribute("role").toString());
								for(AppParameters parameters:roleList){
									actionID=parameters.getActionID();
									logger.info("Page IDs :"+actionID);
									hashSetAction.add(actionID);
								}
							viewPage="device-earthpit";
							tableName1=SchemaDef.M_DEVICE_EARTHPITS;
							id=SchemaDef.DEVICE_EARTHPIT_ID;
							   if(appParameters.getOperation()==0){
								   if(hashSetAction.contains(SchemaDef.PAGESELECT)){
									   List<AppParameters> appParametersList = threeFieldDao.getAllDeviceEarthpitsDetails(tableName1,id,appParameters.getStatus(),location,userId);
										model.put("list",  appParametersList);
										
										List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
										model.put("location",  locationTableDataList);
										 
								   }else{
									   viewPage="unauthorized";  
								   }
								
								/*List<AppParameters> deivceTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_DEVICE);
								model.put("device",  deivceTableDataList);
								
								List<AppParameters> earthpitTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_EARTHPITS);
								model.put("earthpit",  earthpitTableDataList);*/
								
							}else if(appParameters.getOperation()==1){
								if(hashSetAction.contains(SchemaDef.PAGEUPDATE)){
									AppParameters  appParameters1 = threeFieldDao.getDetails(tableRowId, page, page);
									model.put("list",  appParameters1);
								}else{
									viewPage="unauthorized";  
								}
							
								
								
							}else if(appParameters.getOperation()==2){
								if(hashSetAction.contains(SchemaDef.PAGEUPDATE)){
									appParameters.setEditedBy(session.getAttribute("userId").toString());
									//tableName2="templdap_location";
									tableName2="ems_loc_master";
									threeFieldDao.updateDetails(appParameters, tableName1,tableName2);
									List<AppParameters> appParametersList = threeFieldDao.getAllDeviceEarthpitsDetails(tableName1,id,appParameters.getStatus(),location,userId);
									model.put("list",  appParametersList);
									
									List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
									model.put("location",  locationTableDataList);
								}else{
								   viewPage="unauthorized";
								}
								
								
							}else if(appParameters.getOperation()==3){
								if(hashSetAction.contains(SchemaDef.PAGEDELETE)){
									appParameters.setEditedBy(session.getAttribute("userId").toString());
									tableRowId=appParameters.getDeviceearthpitId();
									logger.info("Delecte Operation");
									threeFieldDao.deleteEarthptsDetails(appParameters, tableName1);
									List<AppParameters> appParametersList = threeFieldDao.getAllDeviceEarthpitsDetails(tableName1,id,appParameters.getStatus(),location,userId);
									model.put("list",  appParametersList);
									
									List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
									model.put("location",  locationTableDataList);
								}else{
									viewPage="unauthorized";
								}
								
							}else if(appParameters.getOperation()==4){
								if(hashSetAction.contains(SchemaDef.PAGEINSERT)){
									appParameters.setCreatedOn(CurrentDateTime.getCurrentDate());
									appParameters.setCreatedBy(session.getAttribute("userId").toString());
									appParameters.setStatus("A");
						         	threeFieldDao.saveDetails(appParameters, tableName1,tableName2);
						         	List<AppParameters> appParametersList = threeFieldDao.getAllDeviceEarthpitsDetails(tableName1,id,appParameters.getStatus(),location,userId);
									model.put("list",  appParametersList);
									
									List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
									model.put("location",  locationTableDataList);
							        System.out.println("List size "+appParametersList.size());
								}else{
									
								}
								viewPage="unauthorized";
						       } 
					 }else{
						 viewPage="unauthorized";   
					 }
					 logger.info("Configuration device-earthpits controller ended");
					 
				 }
					break;
			   case "CP7": 
					 logger.info("Configuration alertgroup controller strarted");
					/* CP7= alertgroups page.
					 * Operation 0 for select All
					 * Operation 1 for select Specific id
					 * Operation 2 for update
					 * Operation 3 for Delete
					 * Operation 4 for Save
					 * */
					 if(session.getAttribute("userId").toString().equals("admin")){
						 viewPage="alertgroups";
							tableName1=SchemaDef.M_GROUPS;
							id=SchemaDef.GROUPID;
							 logger.info("Group Id" +id);
							   if(appParameters.getOperation()==0){
								List<AppParameters> appParametersList = threeFieldDao.getAllAlertGroupDetails(tableName1, id,appParameters.getStatus(),location,userId);
								model.put("list",  appParametersList);
								System.out.println("Configuration alertgroup List size "+appParametersList.size());
								List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
								model.put("location",  locationTableDataList);
								
								/*List<AppParameters> deivceTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_DEVICE);
								model.put("device",  deivceTableDataList);
								
								List<AppParameters> earthpitTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_EARTHPITS);
								model.put("earthpit",  earthpitTableDataList);*/
								
							}else if(appParameters.getOperation()==1){
							
								AppParameters  appParameters1 = threeFieldDao.getDetails(tableRowId, page, page);
								model.put("list",  appParameters1);
								
							}else if(appParameters.getOperation()==2){
								appParameters.setEditedBy(session.getAttribute("userId").toString());
								
								//tableName2="templdap_location";
								tableName2="ems_loc_master";
								threeFieldDao.updateAlertGroupDetails(appParameters, tableName1);
								List<AppParameters> appParametersList = threeFieldDao.getAllAlertGroupDetails(tableName1, id,appParameters.getStatus(),location,userId);
								model.put("list",  appParametersList);
								
								System.out.println("Configuration alertgroup List size "+appParametersList.size());
								List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
								model.put("location",  locationTableDataList);
								
							}else if(appParameters.getOperation()==3){
								appParameters.setEditedBy(session.getAttribute("userId").toString());
								
								logger.info("Delecte Operation");
								threeFieldDao.deleteAlertGroupDetails(appParameters, tableName1);
								List<AppParameters> appParametersList = threeFieldDao.getAllAlertGroupDetails(tableName1, id,appParameters.getStatus(),location,userId);
								model.put("list",  appParametersList);
								System.out.println("Configuration alertgroup List size "+appParametersList.size());
								List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
								model.put("location",  locationTableDataList);
							}else if(appParameters.getOperation()==4){
								appParameters.setCreatedOn(CurrentDateTime.getCurrentDate());
								appParameters.setCreatedBy(session.getAttribute("userId").toString());
								appParameters.setStatus("A");
								boolean checkDuplicate=twoFieldDao.checkDuplicateData(tableName1, SchemaDef.GROUPID, appParameters.getGroupId());
								if(checkDuplicate){
									model.put("error",  "Already Exists Record");
									andView.addObject("error", "Already Exists Record");
									logger.info("Already Exist");
								}else{
									logger.info("Data Inserted");
									threeFieldDao.saveDetails(appParameters, tableName1,tableName2);
								}
					         	threeFieldDao.saveAlertGroupDetails(appParameters, tableName1);
					         	List<AppParameters> appParametersList = threeFieldDao.getAllAlertGroupDetails(tableName1, id,appParameters.getStatus(),location,userId);
								model.put("list",  appParametersList);
								System.out.println("Configuration alertgroup List size "+appParametersList.size());
								List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
		                        model.put("location",  locationTableDataList);
		                        System.out.println("List size "+appParametersList.size());
						        }
					 }else{
						 if(hashSet.contains(SchemaDef.PAGEDEVICEEARTHPIT)){
							 int actionID=0;
								HashSet<Integer> hashSetAction=new HashSet<Integer>();
									List<AppParameters> roleList= rolePerimissionDao.getActionId(session.getAttribute("role").toString());
									for(AppParameters parameters:roleList){
										actionID=parameters.getActionID();
										logger.info("Page IDs :"+actionID);
										hashSetAction.add(actionID);
									}
								viewPage="alertgroups";
								tableName1=SchemaDef.M_GROUPS;
								id=SchemaDef.GROUPID;
								   if(appParameters.getOperation()==0){
									   if(hashSetAction.contains(SchemaDef.PAGESELECT)){
										   List<AppParameters> appParametersList = threeFieldDao.getAllDeviceEarthpitsDetails(tableName1,id,appParameters.getStatus(),location,userId);
											model.put("list",  appParametersList);
											
											List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
											model.put("location",  locationTableDataList);
											 
									   }else{
										   viewPage="unauthorized";  
									   }
									
									/*List<AppParameters> deivceTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_DEVICE);
									model.put("device",  deivceTableDataList);
									List<AppParameters> earthpitTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_EARTHPITS);
									model.put("earthpit",  earthpitTableDataList);*/
									
								}else if(appParameters.getOperation()==1){
									if(hashSetAction.contains(SchemaDef.PAGEUPDATE)){
										AppParameters  appParameters1 = threeFieldDao.getDetails(tableRowId, page, page);
										model.put("list",  appParameters1);
									}else{
										viewPage="unauthorized";  
									}
								
									
									
								}else if(appParameters.getOperation()==2){
									if(hashSetAction.contains(SchemaDef.PAGEUPDATE)){
										appParameters.setEditedBy(session.getAttribute("userId").toString());
										//tableName2="templdap_location";
										tableName2="ems_loc_master";
										threeFieldDao.updateAlertGroupDetails(appParameters, tableName1);
										List<AppParameters> appParametersList = threeFieldDao.getAllAlertGroupDetails(tableName1, id,appParameters.getStatus(),location,userId);
										model.put("list",  appParametersList);
										System.out.println("Configuration alertgroup List size "+appParametersList.size());
										
										List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
										model.put("location",  locationTableDataList);
									}else{
									   viewPage="unauthorized";
									}
									
									
								}else if(appParameters.getOperation()==3){
									if(hashSetAction.contains(SchemaDef.PAGEDELETE)){
										appParameters.setEditedBy(session.getAttribute("userId").toString());
										tableRowId=appParameters.getGroupId();
										logger.info("Delecte Operation");
										threeFieldDao.deleteAlertGroupDetails(appParameters, tableName1);
										List<AppParameters> appParametersList = threeFieldDao.getAllAlertGroupDetails(tableName1, id,appParameters.getStatus(),location,userId);
										model.put("list",  appParametersList);
										
										System.out.println("Configuration alertgroup List size "+appParametersList.size());
										List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
										model.put("location",  locationTableDataList);
									}else{
										viewPage="unauthorized";
									}
									
								}else if(appParameters.getOperation()==4){
									if(hashSetAction.contains(SchemaDef.PAGEINSERT)){
										appParameters.setCreatedOn(CurrentDateTime.getCurrentDate());
										appParameters.setCreatedBy(session.getAttribute("userId").toString());
										appParameters.setStatus("A");
										boolean checkDuplicate=twoFieldDao.checkDuplicateData(tableName1, SchemaDef.GROUPID, appParameters.getGroupId());
										if(checkDuplicate){
											model.put("error",  "Already Exists Record");
											andView.addObject("error", "Already Exists Record");
											logger.info("Already Exist");
										}else{
											logger.info("Data Inserted");
											threeFieldDao.saveDetails(appParameters, tableName1,tableName2);
										}
							         	threeFieldDao.saveAlertGroupDetails(appParameters, tableName1);
							         	List<AppParameters> appParametersList = threeFieldDao.getAllAlertGroupDetails(tableName1, id,appParameters.getStatus(),location,userId);
										model.put("list",  appParametersList);
										System.out.println("Configuration alertgroup List size "+appParametersList.size());
										List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
										model.put("location",  locationTableDataList);
								        System.out.println("List size "+appParametersList.size());
									}else{
										
									}
									viewPage="unauthorized";
							       } 
						 }else{
							 viewPage="unauthorized";   
						 }
						 
					 
					 }

					 break;
			 case "DATESEARCH": 
				     logger.info("Configuration DATESEARCH controller strarted");
						/* DATESEARCH.
						 * Operation 0 for select All
					     * */
					String tableName=SchemaDef.TR_DASHBOARD;
					
					if(appParameters.getOperation()==0){
						tableName=SchemaDef.TR_DASHBOARD;
					List<AppParameters> appParametersList = threeFieldDao.getDateAndTimeSearch(tableName, appParameters.getFromdatetime(), appParameters.getTodatetime(), appParameters);
					model.put("list",  appParametersList);
					}
					
				   logger.info("Configuration DATESEARCH controller ended");
				            break;
			case "DATESEARCHFORALERT": 
				/* DATESEARCHFORALERT.
				* Operation 0 for select All
			    * */
				     logger.info("Configuration DATESEARCHFORALERT controller strarted");
					if(appParameters.getOperation()==0){
						tableName=SchemaDef.TR_ALERTMANAGMENT;
					List<AppParameters> appParametersList = threeFieldDao.getDateAndTimeSearchFOrAlert(tableName,appParameters.getLocationID(), appParameters.getDeviceId(), appParameters.getFromdatetime(), appParameters.getTodatetime(), appParameters);
					model.put("list",  appParametersList);
					}
					
				   logger.info("Configuration DATESEARCHFORALERT controller ended");
				
				break;
				default:
					viewPage="unauthorized"; 
					
							 
				}
			}
	
	andView.addAllObjects(model);
	andView.setViewName(viewPage);
				 return andView;
	     }
	}
	