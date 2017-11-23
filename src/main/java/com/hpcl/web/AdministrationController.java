
/**
* @author  :Prasad
* @version :1.0
* @since   2015-10-09 
*/
package com.hpcl.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hpcl.dao.AdministrationDao;
import com.hpcl.dao.RolePerimissionTablesDataDao;
import com.hpcl.persistence.ActionListPOJO;
import com.hpcl.persistence.AppParameters;
import com.hpcl.persistence.LoginPersistance;
import com.hpcl.utill.CurrentDateTime;
import com.hpcl.utill.SchemaDef;

/*
 * TwoFielController
 */
@Controller
public class AdministrationController {
	String tableName;
	String id;
	int tableRowId=0;
	String viewPage;
	
	private static final Logger logger = Logger.getLogger(AdministrationController.class);
	@Autowired
	AdministrationDao twoFieldDao;
	
	@Autowired
	RolePerimissionTablesDataDao rolePerimissionDao;
	ModelAndView andView=new ModelAndView();
	@RequestMapping("/actions.htm")
    public ModelAndView allDetails(@ModelAttribute AppParameters twoFieldPersistence,HttpServletRequest request,ActionListPOJO actions,ModelMap models,Map<String, Object> modelSessionOut) throws IOException{	
	logger.info("actions controller started");
	Map<String, Object> model = new HashMap<String, Object>();
	String page=twoFieldPersistence.getPage();
	HttpSession session=request.getSession(false);
	String ldaplocation=session.getAttribute("location").toString();
	String userid=session.getAttribute("userId").toString();
	
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
	
	logger.info("Page ID: "+pageId);
	logger.info("Page Rolepage :"+SchemaDef.PAGEROLES);
	logger.info("Case "+page);
	switch(page){
	case "AM1": 
		/* AM1=role page.
		 * Operation 0 for select All
		 * Operation 1 for select Specific id
		 * Operation 2 for update
		 * Operation 3 for Delete
		 * Operation 4 for Save
		 * */
		if(session.getAttribute("userId").toString().equals("admin")){
			
			 viewPage="role";
				tableName=SchemaDef.ROLE;
				id=SchemaDef.ROLEID;
				 logger.info("Page:::"+twoFieldPersistence.getPage());
				 logger.info("Operation:::"+twoFieldPersistence.getOperation());
				 logger.info("Description:::"+twoFieldPersistence.getDescription());
				   if(twoFieldPersistence.getOperation()==0){
					List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
					model.put("list",  twoFieldPersistenceList);
				}else if(twoFieldPersistence.getOperation()==1){
					AppParameters  twoFieldPersistence1 = twoFieldDao.getDetails(tableRowId, tableName);
					model.put("list",  twoFieldPersistence1);
					
				}else if(twoFieldPersistence.getOperation()==2){
					twoFieldPersistence.setEditedBy(session.getAttribute("userId").toString());
					twoFieldDao.updateDetails(twoFieldPersistence, tableName);
					List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
					model.put("list",  twoFieldPersistenceList);
					
				}else if(twoFieldPersistence.getOperation()==3){
					twoFieldPersistence.setEditedBy(session.getAttribute("userId").toString());
					tableRowId=twoFieldPersistence.getRoleID();
					logger.info("Delecte Operation");
					twoFieldDao.deleteDetails(tableRowId, tableName,id,twoFieldPersistence);
					List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,SchemaDef.ROLEID,SchemaDef.ACTIVE_FLAG,ldaplocation,userid);
					model.put("list",twoFieldPersistenceList);
				}else if(twoFieldPersistence.getOperation()==4){
					twoFieldPersistence.setCreatedOn(CurrentDateTime.getCurrentDate());
				    twoFieldPersistence.setCreatedBy(session.getAttribute("userId").toString());
					twoFieldPersistence.setStatus("A");
					
					boolean checkDuplicate=twoFieldDao.checkDuplicateData(tableName, SchemaDef.DESCRIPTION, twoFieldPersistence.getDescription());
					if(checkDuplicate){
						model.put("error",  "Already Exists Record");
						andView.addObject("error", "Already Exists Record"); 
						logger.info("Already Exist");
					}else{
						logger.info("Data Inserted");
						twoFieldDao.saveDetails(twoFieldPersistence, tableName);
					}
		            List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,SchemaDef.ROLEID,SchemaDef.ACTIVE_FLAG,ldaplocation,userid);
			        model.put("list",  twoFieldPersistenceList);
					
				  }
		
		 }else{
			 if(hashSet.contains(SchemaDef.PAGEROLES)){
				 int actionID=0;
					HashSet<Integer> hashSetAction=new HashSet<Integer>();
						List<AppParameters> roleList= rolePerimissionDao.getActionId(session.getAttribute("role").toString());
						for(AppParameters parameters:roleList){
							actionID=parameters.getActionID();
							logger.info("Page IDs :"+actionID);
							hashSetAction.add(actionID);
						}
						logger.info("operation :"+twoFieldPersistence.getOperation());
					viewPage="role";
					tableName=SchemaDef.ROLE;
					id=SchemaDef.ROLEID;
					   if(twoFieldPersistence.getOperation()==0){
						   if(hashSetAction.contains(SchemaDef.PAGESELECT)){
							   List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
								model.put("list",  twoFieldPersistenceList); 
						   }else{
							   viewPage="unauthorized";
						   }
						
					}else if(twoFieldPersistence.getOperation()==1){
						AppParameters  twoFieldPersistence1 = twoFieldDao.getDetails(tableRowId, tableName);
						model.put("list",  twoFieldPersistence1);
						
					}else if(twoFieldPersistence.getOperation()==2){
						 if(hashSetAction.contains(SchemaDef.PAGEUPDATE)){
							 twoFieldPersistence.setEditedBy(session.getAttribute("userId").toString());
							 twoFieldDao.updateDetails(twoFieldPersistence, tableName);
								List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
								model.put("list",  twoFieldPersistenceList);
						 }else{
							 viewPage="unauthorized"; 
						 }
						
						
					}else if(twoFieldPersistence.getOperation()==3){
						twoFieldPersistence.setEditedBy(session.getAttribute("userId").toString());
						 if(hashSetAction.contains(SchemaDef.PAGEDELETE)){
							 tableRowId=twoFieldPersistence.getRoleID();
								logger.info("Delecte Operation");
								twoFieldDao.deleteDetails(tableRowId, tableName,id,twoFieldPersistence);
								List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,SchemaDef.ROLEID,SchemaDef.ACTIVE_FLAG,ldaplocation,userid);
								model.put("list",twoFieldPersistenceList);
						 }else{
							 viewPage="unauthorized";  
						 }
						
					}else if(twoFieldPersistence.getOperation()==4){
						 if(hashSetAction.contains(SchemaDef.PAGEINSERT)){
							    twoFieldPersistence.setCreatedOn(CurrentDateTime.getCurrentDate());
							    twoFieldPersistence.setCreatedBy(session.getAttribute("userId").toString());
								twoFieldPersistence.setStatus("A");
								boolean checkDuplicate=twoFieldDao.checkDuplicateData(tableName, SchemaDef.DESCRIPTION, twoFieldPersistence.getDescription());
								if(checkDuplicate){
									model.put("error",  "Already Exists Record");
									andView.addObject("error", "Already Exists Record"); 
									logger.info("Already Exist");
								}else{
									logger.info("Data Inserted");
									twoFieldDao.saveDetails(twoFieldPersistence, tableName);
								}
								
								List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,SchemaDef.ROLEID,SchemaDef.ACTIVE_FLAG,ldaplocation,userid);
						        model.put("list",  twoFieldPersistenceList);
						 }else{
							 viewPage="unauthorized";  
						 }
					}
			
				}else{
					viewPage="unauthorized";
				} 
		 }
		
		
				
		break;
	case "AM2":
		/* AM2=role module page.
		 * Operation 0 for select All
		 * Operation 1 for select Specific id
		 * Operation 2 for update
		 * Operation 3 for Delete
		 * Operation 4 for Save
		 * */
		
		if(SchemaDef.PAGEROLES==pageId){
			viewPage="module";
			tableName=SchemaDef.MODULE;
			id=SchemaDef.MODULEID;
			if(twoFieldPersistence.getOperation()==0){
				List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
				model.put("list",  twoFieldPersistenceList);
				
			}else if(twoFieldPersistence.getOperation()==1){
				AppParameters  twoFieldPersistence1 = twoFieldDao.getDetails(tableRowId, tableName);
				model.put("list",  twoFieldPersistence1);
				
			}else if(twoFieldPersistence.getOperation()==2){
				twoFieldPersistence.setEditedBy(session.getAttribute("userId").toString());
				twoFieldDao.updateDetails(twoFieldPersistence, tableName);
				List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
				model.put("list",  twoFieldPersistenceList);
				
			}else if(twoFieldPersistence.getOperation()==3){
				twoFieldPersistence.setEditedBy(session.getAttribute("userId").toString());
				tableRowId=twoFieldPersistence.getModuleID();
				logger.info("Delecte Operation");
				twoFieldDao.deleteDetails(tableRowId, tableName,id,twoFieldPersistence);
				List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,SchemaDef.MODULEID,SchemaDef.ACTIVE_FLAG,ldaplocation,userid);
				model.put("list",twoFieldPersistenceList);
				
			}else if(twoFieldPersistence.getOperation()==4){
				twoFieldPersistence.setCreatedOn(CurrentDateTime.getCurrentDate());
			    twoFieldPersistence.setCreatedBy(session.getAttribute("userId").toString());
				twoFieldPersistence.setStatus("A");
				
				boolean checkDuplicate=twoFieldDao.checkDuplicateData(tableName, SchemaDef.DESCRIPTION, twoFieldPersistence.getDescription());
				if(checkDuplicate){
					model.put("error",  "Already Exists Record");
					andView.addObject("error", "Already Exists Record"); 
					logger.info("Already Exist");
				}else{
					logger.info("Data Inserted");
					twoFieldDao.saveDetails(twoFieldPersistence, tableName);
				}
				
	        	List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
				model.put("list",  twoFieldPersistenceList);
				
				
			}
		}else{
			viewPage="unauthorized";
		}
		
		logger.info("View Page "+viewPage);
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
		viewPage="action";
		id=SchemaDef.ACTIONID;
		if(twoFieldPersistence.getOperation()==0){
			List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
			model.put("list",  twoFieldPersistenceList);
			
		}else if(twoFieldPersistence.getOperation()==1){
			AppParameters  twoFieldPersistence1 = twoFieldDao.getDetails(tableRowId, tableName);
			model.put("list",  twoFieldPersistence1);
		}else if(twoFieldPersistence.getOperation()==2){
			twoFieldDao.updateDetails(twoFieldPersistence, tableName);
			List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
			model.put("list",  twoFieldPersistenceList);
			
		}else if(twoFieldPersistence.getOperation()==3){
			tableRowId=twoFieldPersistence.getActionID();
			logger.info("Delecte Operation");
			twoFieldDao.deleteDetails(tableRowId, tableName,id,twoFieldPersistence);
			List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,SchemaDef.ACTIONID,SchemaDef.ACTIVE_FLAG,ldaplocation,userid);
			model.put("list",twoFieldPersistenceList);
		}else if(twoFieldPersistence.getOperation()==4){
			twoFieldPersistence.setCreatedOn(CurrentDateTime.getCurrentDate());
			twoFieldPersistence.setCreatedBy("Admin");
			twoFieldPersistence.setStatus("A");
		    twoFieldDao.saveDetails(twoFieldPersistence, tableName);
			List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
			
			model.put("list",  twoFieldPersistenceList);
	       }
		
		
		break;
	case "AM4":
		/* AM3=category page.
		 * Operation 0 for select All
		 * Operation 1 for select Specific id
		 * Operation 2 for update
		 * Operation 3 for Delete
		 * Operation 4 for Save
		 * */
		 if(session.getAttribute("userId").toString().equals("admin")){
			 tableName=SchemaDef.CATEGORY;
				viewPage="category";
				id=SchemaDef.CATEGORYID;
				if(twoFieldPersistence.getOperation()==0){
					List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
					model.put("list",  twoFieldPersistenceList);
					
				}else if(twoFieldPersistence.getOperation()==1){
					
					AppParameters  twoFieldPersistence1 = twoFieldDao.getDetails(tableRowId, tableName);
					model.put("list",  twoFieldPersistence1);
				}else if(twoFieldPersistence.getOperation()==2){
					twoFieldPersistence.setEditedBy(session.getAttribute("userId").toString());
					twoFieldDao.updateDetails(twoFieldPersistence, tableName);
					List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
					model.put("list",  twoFieldPersistenceList);
					
				}else if(twoFieldPersistence.getOperation()==3){
					twoFieldPersistence.setEditedBy(session.getAttribute("userId").toString());
					tableRowId=twoFieldPersistence.getCategoryID();
					logger.info("Delecte Operation");
					twoFieldDao.deleteDetails(tableRowId, tableName,id,twoFieldPersistence);
					List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,SchemaDef.CATEGORYID,SchemaDef.ACTIVE_FLAG,ldaplocation,userid);
					model.put("list",twoFieldPersistenceList);
				}else if(twoFieldPersistence.getOperation()==4){
					 twoFieldPersistence.setCreatedOn(CurrentDateTime.getCurrentDate());
					    twoFieldPersistence.setCreatedBy(session.getAttribute("userId").toString());
						twoFieldPersistence.setStatus("A");
						
						boolean checkDuplicate=twoFieldDao.checkDuplicateData(tableName, SchemaDef.DESCRIPTION, twoFieldPersistence.getDescription());
						if(checkDuplicate){
							model.put("error",  "Already Exists Record");
							andView.addObject("error", "Already Exists Record"); 
							logger.info("Already Exist");
						}else{
							logger.info("Data Inserted");
							  twoFieldDao.saveDetails(twoFieldPersistence, tableName);
						}
						
				  
					List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
					model.put("list",  twoFieldPersistenceList);
			       }
		 }else{
			 if(hashSet.contains(SchemaDef.PAGECATEGORY)){
				 int actionID=0;
					HashSet<Integer> hashSetAction=new HashSet<Integer>();
						List<AppParameters> roleList= rolePerimissionDao.getActionId(session.getAttribute("role").toString());
						for(AppParameters parameters:roleList){
							actionID=parameters.getActionID();
							logger.info("Page IDs :"+actionID);
							hashSetAction.add(actionID);
						}
					tableName=SchemaDef.CATEGORY;
					viewPage="category";
					id=SchemaDef.CATEGORYID;
					if(twoFieldPersistence.getOperation()==0){
						if(hashSetAction.contains(SchemaDef.PAGESELECT)){
							List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
							model.put("list",  twoFieldPersistenceList);
						}else{
							viewPage="unauthorized";
							
						}
						
						
					}else if(twoFieldPersistence.getOperation()==1){
						AppParameters  twoFieldPersistence1 = twoFieldDao.getDetails(tableRowId, tableName);
						model.put("list",  twoFieldPersistence1);
						
					}else if(twoFieldPersistence.getOperation()==2){
						if(hashSetAction.contains(SchemaDef.PAGEUPDATE)){
							twoFieldPersistence.setEditedBy(session.getAttribute("userId").toString());
							twoFieldDao.updateDetails(twoFieldPersistence, tableName);
							List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
							model.put("list",  twoFieldPersistenceList);
						}else{
							viewPage="unauthorized";
						}
						
						
					}else if(twoFieldPersistence.getOperation()==3){
						if(hashSetAction.contains(SchemaDef.PAGEDELETE)){
							twoFieldPersistence.setEditedBy(session.getAttribute("userId").toString());
							tableRowId=twoFieldPersistence.getCategoryID();
							logger.info("Delecte Operation");
							twoFieldDao.deleteDetails(tableRowId, tableName,id,twoFieldPersistence);
							List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,SchemaDef.CATEGORYID,SchemaDef.ACTIVE_FLAG,ldaplocation,userid);
							model.put("list",twoFieldPersistenceList);
						}else{
							viewPage="unauthorized";
						}
						
					}else if(twoFieldPersistence.getOperation()==4){
						if(hashSetAction.contains(SchemaDef.PAGEINSERT)){
							 twoFieldPersistence.setCreatedOn(CurrentDateTime.getCurrentDate());
							    twoFieldPersistence.setCreatedBy(session.getAttribute("userId").toString());
								twoFieldPersistence.setStatus("A");
								
								boolean checkDuplicate=twoFieldDao.checkDuplicateData(tableName, SchemaDef.DESCRIPTION, twoFieldPersistence.getDescription());
								if(checkDuplicate){
									model.put("error",  "Already Exists Record");
									andView.addObject("error", "Already Exists Record"); 
									logger.info("Already Exist");
								}else{
									logger.info("Data Inserted");
									twoFieldDao.saveDetails(twoFieldPersistence, tableName);
								}
						    
							List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
							model.put("list",  twoFieldPersistenceList);
						}else
						{
							viewPage="unauthorized";
						}
						
				       }
				}else{
					viewPage="unauthorized";
				}
		 }
		
		
		break;
	case "AM5":
		/* AM3=Zone page.
		 * Operation 0 for select All
		 * Operation 1 for select Specific id
		 * Operation 2 for update
		 * Operation 3 for Delete
		 * Operation 4 for Save
		 * */
		
		 if(session.getAttribute("userId").toString().equals("admin")){
			 tableName=SchemaDef.ZONE;
				viewPage="zone";
				id=SchemaDef.ZONEID;
				if(twoFieldPersistence.getOperation()==0){
					List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
					model.put("list",  twoFieldPersistenceList);
					
				}else if(twoFieldPersistence.getOperation()==1){
					AppParameters  twoFieldPersistence1 = twoFieldDao.getDetails(tableRowId, tableName);
					model.put("list",  twoFieldPersistence1);
				}else if(twoFieldPersistence.getOperation()==2){
					twoFieldPersistence.setEditedBy(session.getAttribute("userId").toString());
					twoFieldDao.updateDetails(twoFieldPersistence, tableName);
					List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
					model.put("list",  twoFieldPersistenceList);
					
				}else if(twoFieldPersistence.getOperation()==3){
					twoFieldPersistence.setEditedBy(session.getAttribute("userId").toString());
					tableRowId=twoFieldPersistence.getZoneID();
					logger.info("Delecte Operation");
					twoFieldDao.deleteDetails(tableRowId, tableName,id,twoFieldPersistence);
					List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,SchemaDef.ZONEID,SchemaDef.ACTIVE_FLAG,ldaplocation,userid);
					model.put("list",twoFieldPersistenceList);
				}else if(twoFieldPersistence.getOperation()==4){
					 twoFieldPersistence.setCreatedOn(CurrentDateTime.getCurrentDate());
					    twoFieldPersistence.setCreatedBy(session.getAttribute("userId").toString());
						twoFieldPersistence.setStatus("A");
						
						boolean checkDuplicate=twoFieldDao.checkDuplicateData(tableName, SchemaDef.DESCRIPTION, twoFieldPersistence.getDescription());
						if(checkDuplicate){
							model.put("error",  "Already Exists Record");
							andView.addObject("error", "Already Exists Record"); 
							logger.info("Already Exist");
						}else{
							logger.info("Data Inserted");
							 twoFieldDao.saveDetails(twoFieldPersistence, tableName);
						}
				   
					List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
					model.put("list",  twoFieldPersistenceList);
			       }
		 }else{
			 if(hashSet.contains(SchemaDef.PAGEZONE)){
				 int actionID=0;
					HashSet<Integer> hashSetAction=new HashSet<Integer>();
						List<AppParameters> roleList= rolePerimissionDao.getActionId(session.getAttribute("role").toString());
						for(AppParameters parameters:roleList){
							actionID=parameters.getActionID();
							logger.info("Page IDs :"+actionID);
							hashSetAction.add(actionID);
						}
					tableName=SchemaDef.ZONE;
					viewPage="zone";
					id=SchemaDef.ZONEID;
					if(twoFieldPersistence.getOperation()==0){
						if(hashSetAction.contains(SchemaDef.PAGESELECT)){
							List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
							model.put("list",  twoFieldPersistenceList);
						}else{
							viewPage="unauthorized";
						}
						
						
					}else if(twoFieldPersistence.getOperation()==1){
						AppParameters  twoFieldPersistence1 = twoFieldDao.getDetails(tableRowId, tableName);
						model.put("list",  twoFieldPersistence1);
						
					}else if(twoFieldPersistence.getOperation()==2){
						
						if(hashSetAction.contains(SchemaDef.PAGEUPDATE)){
							twoFieldPersistence.setEditedBy(session.getAttribute("userId").toString());
							twoFieldDao.updateDetails(twoFieldPersistence, tableName);
							List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
							model.put("list",  twoFieldPersistenceList);
						}else{
							viewPage="unauthorized";
						}
						
						
					}else if(twoFieldPersistence.getOperation()==3){
						if(hashSetAction.contains(SchemaDef.PAGEDELETE)){
							twoFieldPersistence.setEditedBy(session.getAttribute("userId").toString());
							tableRowId=twoFieldPersistence.getZoneID();
							logger.info("Delecte Operation");
							twoFieldDao.deleteDetails(tableRowId, tableName,id,twoFieldPersistence);
							List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,SchemaDef.ZONEID,SchemaDef.ACTIVE_FLAG,ldaplocation,userid);
							model.put("list",twoFieldPersistenceList);
						}else{
							viewPage="unauthorized";
						}
						
					}else if(twoFieldPersistence.getOperation()==4){
						if(hashSetAction.contains(SchemaDef.PAGEINSERT)){
							 twoFieldPersistence.setCreatedOn(CurrentDateTime.getCurrentDate());
							    twoFieldPersistence.setCreatedBy(session.getAttribute("userId").toString());
								twoFieldPersistence.setStatus("A");
								
								boolean checkDuplicate=twoFieldDao.checkDuplicateData(tableName, SchemaDef.DESCRIPTION, twoFieldPersistence.getDescription());
								if(checkDuplicate){
									model.put("error",  "Already Exists Record");
									andView.addObject("error", "Already Exists Record"); 
									logger.info("Already Exist");
								}else{
									logger.info("Data Inserted");
									twoFieldDao.saveDetails(twoFieldPersistence, tableName);
								}
						    List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
							model.put("list",  twoFieldPersistenceList);
						}else{
							viewPage="unauthorized";
						}
						
				       }
				}else{
					viewPage="unauthorized";
				}
		 }
		
		
		break;
	case "AM6":
		/* AM3=UserType page.
		 * Operation 0 for select All
		 * Operation 1 for select Specific id
		 * Operation 2 for update
		 * Operation 3 for Delete
		 * Operation 4 for Save
		 * */
		 if(session.getAttribute("userId").toString().equals("admin")){
			 tableName=SchemaDef.USERTYPE;
				viewPage="usertype";
				id=SchemaDef.USERTYPEID;
				if(twoFieldPersistence.getOperation()==0){
					List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
					model.put("list",  twoFieldPersistenceList);
					
				}else if(twoFieldPersistence.getOperation()==1){
					
					AppParameters  twoFieldPersistence1 = twoFieldDao.getDetails(tableRowId, tableName);
					model.put("list",  twoFieldPersistence1);
				}else if(twoFieldPersistence.getOperation()==2){
					twoFieldPersistence.setEditedBy(session.getAttribute("userId").toString());
					twoFieldDao.updateDetails(twoFieldPersistence, tableName);
					List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
					model.put("list",  twoFieldPersistenceList);
					
				}else if(twoFieldPersistence.getOperation()==3){
					twoFieldPersistence.setEditedBy(session.getAttribute("userId").toString());
					tableRowId=twoFieldPersistence.getUserTypeID();
					logger.info("Delecte Operation");
					twoFieldDao.deleteDetails(tableRowId, tableName,id,twoFieldPersistence);
					List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,SchemaDef.USERTYPEID,SchemaDef.ACTIVE_FLAG,ldaplocation,userid);
					model.put("list",twoFieldPersistenceList);
				}else if(twoFieldPersistence.getOperation()==4){
					 twoFieldPersistence.setCreatedOn(CurrentDateTime.getCurrentDate());
					    twoFieldPersistence.setCreatedBy(session.getAttribute("userId").toString());
						twoFieldPersistence.setStatus("A");
						boolean checkDuplicate=twoFieldDao.checkDuplicateData(tableName, SchemaDef.DESCRIPTION, twoFieldPersistence.getDescription());
						if(checkDuplicate){
							model.put("error",  "Already Exists Record");
							andView.addObject("error", "Already Exists Record"); 
							logger.info("Already Exist");
						}else{
							logger.info("Data Inserted");
							twoFieldDao.saveDetails(twoFieldPersistence, tableName);
						}
				    
					List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
					model.put("list",  twoFieldPersistenceList);
			       }
		 }else{
			 if(hashSet.contains(SchemaDef.PAGEUSERTYPE)){
				 int actionID=0;
					HashSet<Integer> hashSetAction=new HashSet<Integer>();
						List<AppParameters> roleList= rolePerimissionDao.getActionId(session.getAttribute("role").toString());
						for(AppParameters parameters:roleList){
							actionID=parameters.getActionID();
							logger.info("Page IDs :"+actionID);
							hashSetAction.add(actionID);
						}
					tableName=SchemaDef.USERTYPE;
					viewPage="usertype";
					id=SchemaDef.USERTYPEID;
					if(twoFieldPersistence.getOperation()==0){
						if(hashSetAction.contains(SchemaDef.PAGESELECT)){
							List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
							model.put("list",  twoFieldPersistenceList);
							
						}else{
							viewPage="unauthorized";
						}
						
					}else if(twoFieldPersistence.getOperation()==1){
						AppParameters  twoFieldPersistence1 = twoFieldDao.getDetails(tableRowId, tableName);
						model.put("list",  twoFieldPersistence1);
					}else if(twoFieldPersistence.getOperation()==2){
						if(hashSetAction.contains(SchemaDef.PAGEUPDATE)){
							twoFieldPersistence.setEditedBy(session.getAttribute("userId").toString());
							twoFieldDao.updateDetails(twoFieldPersistence, tableName);
							List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
							model.put("list",  twoFieldPersistenceList);
						}else{
							viewPage="unauthorized";
						}
						
						
					}else if(twoFieldPersistence.getOperation()==3){
						if(hashSetAction.contains(SchemaDef.PAGEDELETE)){
							twoFieldPersistence.setEditedBy(session.getAttribute("userId").toString());
							tableRowId=twoFieldPersistence.getUserTypeID();
							logger.info("Delecte Operation");
							twoFieldDao.deleteDetails(tableRowId, tableName,id,twoFieldPersistence);
							List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,SchemaDef.USERTYPEID,SchemaDef.ACTIVE_FLAG,ldaplocation,userid);
							model.put("list",twoFieldPersistenceList);
						}else{
							viewPage="unauthorized";	
						}
						
					}else if(twoFieldPersistence.getOperation()==4){
						if(hashSetAction.contains(SchemaDef.PAGEINSERT)){
							    twoFieldPersistence.setCreatedOn(CurrentDateTime.getCurrentDate());
							    twoFieldPersistence.setCreatedBy(session.getAttribute("userId").toString());
								twoFieldPersistence.setStatus("A");
								
								boolean checkDuplicate=twoFieldDao.checkDuplicateData(tableName, SchemaDef.DESCRIPTION, twoFieldPersistence.getDescription());
								if(checkDuplicate){
									model.put("error", "Already Exists Record");
									andView.addObject("error", "Already Exists Record"); 
									logger.info("Already Exist");
								}else{
									logger.info("Data Inserted");
									 twoFieldDao.saveDetails(twoFieldPersistence, tableName);
								}
						   
							List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllDetails(tableName,id,twoFieldPersistence.getStatus(),ldaplocation,userid);
							model.put("list",  twoFieldPersistenceList);	
						}else{
							viewPage="unauthorized";	
						}
						
				       }
				}else{
					viewPage="unauthorized";
				}
		 }
		
		
	
		break;
		case "AM7":
			/* AM3=Rolepermissions page.
			 * Operation 0 for select All
			 * Operation 1 for select Specific id
			 * Operation 2 for update
			 * Operation 3 for Delete
			 * Operation 4 for Save
			 * */
			viewPage="pagepermissions";
			
			
			/*tableName=SchemaDef.USERTYPE;
			id=SchemaDef.USERTYPEID;*/
			if(twoFieldPersistence.getOperation()==0){

				
			}else if(twoFieldPersistence.getOperation()==4){
				logger.info("Insert Controller started");
				List<ActionListPOJO> actionListPOJOs=(List<ActionListPOJO>)request.getSession().getAttribute("actionsList");
				ActionListPOJO actionListPOJO = new ActionListPOJO();
				List<ActionListPOJO> actionListPOJOList = new ArrayList<ActionListPOJO>();
				if (null != actionListPOJOs && actionListPOJOs.size() != 0) {
					
					for (ActionListPOJO action : actionListPOJOs) {
						List<String> selectedPerson = actions.getSelectedCheckBox();
						
						if (null != selectedPerson && selectedPerson.size()!=0) {
							for (String string : selectedPerson) {
								
								if (string.equalsIgnoreCase(action.getId())) {
									logger.info("selected values :"+string);
									actionListPOJOList.add(action);
								}
							}
						}
						
					}
				}
				
		       }
			break;
		case "AM8":
			/* AM3=location-ip page.
			 * Operation 0 for select All
			 * Operation 1 for select Specific id
			 * Operation 2 for update
			 * Operation 3 for Delete
			 * Operation 4 for Save
			 * */
			 if(session.getAttribute("userId").toString().equals("admin")){
				    viewPage="location-ip";
					tableName="m_locationip";
					logger.info(tableName);
					id=SchemaDef.LOCATIONIPCODE;
					/*tableName=SchemaDef.M_LOCATIONIP;
					id=SchemaDef.LOCATIONIPCODE;*/
					if(twoFieldPersistence.getOperation()==0){
						List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllLocationipDetails(tableName, id, twoFieldPersistence.getStatus());
						
						model.put("list",  twoFieldPersistenceList);
						List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,ldaplocation,userid);
						model.put("location",  locationTableDataList);
						
					}
					else if(twoFieldPersistence.getOperation()==1){
						AppParameters  twoFieldPersistence1 = twoFieldDao.getDetails(tableRowId, tableName);
						model.put("list",  twoFieldPersistence1);
					}else if(twoFieldPersistence.getOperation()==2){
						twoFieldDao.updateLocationipDetails(twoFieldPersistence, tableName);
						List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllLocationipDetails(tableName,id,twoFieldPersistence.getStatus());
						model.put("list",  twoFieldPersistenceList);
						
					}else if(twoFieldPersistence.getOperation()==3){
						tableRowId=twoFieldPersistence.getLocationIpCode();
						logger.info("Delecte Operation");
						twoFieldDao.deleteLocationipDetails(tableRowId, tableName, twoFieldPersistence);
						List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllLocationipDetails(tableName, id, twoFieldPersistence.getStatus());
						
						model.put("list",  twoFieldPersistenceList);
						List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,ldaplocation,userid);
						model.put("location",  locationTableDataList);
					}
					else if(twoFieldPersistence.getOperation()==4){
						twoFieldPersistence.setCreatedOn(CurrentDateTime.getCurrentDate());
						twoFieldPersistence.setCreatedBy("Admin");
						twoFieldPersistence.setStatus("A");
						twoFieldDao.saveLocationipDetails(twoFieldPersistence, tableName);
						List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllLocationipDetails(tableName, id, twoFieldPersistence.getStatus());
						model.put("list",  twoFieldPersistenceList);
				       }
			 }else{
				 if(hashSet.contains(SchemaDef.PAGELOCATIONIP)){
					 int actionID=0;
						HashSet<Integer> hashSetAction=new HashSet<Integer>();
							List<AppParameters> roleList= rolePerimissionDao.getActionId(session.getAttribute("role").toString());
							for(AppParameters parameters:roleList){
								actionID=parameters.getActionID();
								logger.info("Page IDs :"+actionID);
								hashSetAction.add(actionID);
							}
						viewPage="location-ip";
						tableName="m_locationip";
						logger.info(tableName);
						id=SchemaDef.LOCATIONIPCODE;
						/*tableName=SchemaDef.M_LOCATIONIP;
						id=SchemaDef.LOCATIONIPCODE;*/
						if(twoFieldPersistence.getOperation()==0){
							if(hashSetAction.contains(SchemaDef.PAGESELECT)){
								List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllLocationipDetails(tableName, id, twoFieldPersistence.getStatus());
								model.put("list",  twoFieldPersistenceList);
								List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,ldaplocation,userid);
								model.put("location",  locationTableDataList);
							}else{
								viewPage="unauthorized";
							}
							
							
						}
						else if(twoFieldPersistence.getOperation()==1){
							AppParameters  twoFieldPersistence1 = twoFieldDao.getDetails(tableRowId, tableName);
							model.put("list",  twoFieldPersistence1);
						}else if(twoFieldPersistence.getOperation()==2){
							if(hashSetAction.contains(SchemaDef.PAGEUPDATE)){
								twoFieldDao.updateLocationipDetails(twoFieldPersistence, tableName);
								List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllLocationipDetails(tableName, id, twoFieldPersistence.getStatus());
								
								model.put("list",  twoFieldPersistenceList);
								List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,ldaplocation,userid);
								model.put("location",  locationTableDataList);
							}else{
								viewPage="unauthorized";	
							}
							
							
						}else if(twoFieldPersistence.getOperation()==3){
							if(hashSetAction.contains(SchemaDef.PAGEDELETE)){
								tableRowId=twoFieldPersistence.getLocationIpCode();
								logger.info("Delecte Operation");
								twoFieldDao.deleteLocationipDetails(tableRowId, tableName, twoFieldPersistence);
								List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllLocationipDetails(tableName, id, twoFieldPersistence.getStatus());
								
								model.put("list",  twoFieldPersistenceList);
								List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,ldaplocation,userid);
								model.put("location",  locationTableDataList);
							}else{
								viewPage="unauthorized";	
							}
							
						}
						else if(twoFieldPersistence.getOperation()==4){
							if(hashSetAction.contains(SchemaDef.PAGEINSERT)){
								twoFieldPersistence.setCreatedOn(CurrentDateTime.getCurrentDate());
								twoFieldPersistence.setCreatedBy("Admin");
								twoFieldPersistence.setStatus("A");
								
								twoFieldDao.saveLocationipDetails(twoFieldPersistence, tableName);
								List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllLocationipDetails(tableName, id, twoFieldPersistence.getStatus());
						        model.put("list",  twoFieldPersistenceList);
								List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,ldaplocation,userid);
								model.put("location",  locationTableDataList);
								
							}else{
								viewPage="unauthorized";
							}
							
					       }
					}else{
						viewPage="unauthorized";
					} 
			 }
			
			
			break;
		case "AM9": 
			/* AM9=menu pages.
			 * Operation 0 for select All
			 */
				
			
			 if(session.getAttribute("userId").toString().equals("admin")){
				 viewPage="menu";
				 logger.info("pagemenu controler is started");
				        String status="A";
					   if(twoFieldPersistence.getOperation()==0){
						List<AppParameters> twoFieldPersistenceList = twoFieldDao.getAllMenus(status);
						model.put("menulist",  twoFieldPersistenceList);
						logger.info("size-->"+twoFieldPersistenceList.size());
					}
					   logger.info("pagemenu controler is end");
					   
					   return new ModelAndView(viewPage, model);  
			 }
			
			 
					
			break;
	    default:
	    	viewPage="unauthorized";
		logger.info("default block");
		break;
		
	}
   logger.info("adminactions controller ended");
   
   andView.setViewName(viewPage);
  
	return andView;  
	}
	}
}
