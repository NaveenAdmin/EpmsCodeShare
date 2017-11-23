/**
* @author  :Raj.
* @version :1.0
* @since   2015-10-25. 
*/
package com.hpcl.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hpcl.dao.AdministrationDao;
import com.hpcl.dao.DynamicMenusDao;
import com.hpcl.dao.RolePerimissionTablesDataDao;
import com.hpcl.persistence.AppParameters;
import com.hpcl.persistence.DynamicMenus;
import com.hpcl.persistence.LoginPersistance;
import com.hpcl.utill.SchemaDef;

@Controller
public class DynamicMenusController {
	@Autowired
    DynamicMenusDao dynamicMenusDao;
	
	@Autowired
	RolePerimissionTablesDataDao rolePerimissionTablesDataDao;
	
	private static final Logger logger = Logger.getLogger(DynamicMenusController.class);
	
	@Autowired
	AdministrationDao twoFieldDao;
	Map<String, Object> model = new HashMap<String, Object>();
	@RequestMapping("/ajaxGetDynamicMenus")
    public @ResponseBody String ajaxRoleDetails(HttpServletRequest request) throws IOException{
	logger.info("ajax dynamic menu controller started");
	HttpSession httpSession=request.getSession(false);
	logger.info("Role ID:"+httpSession.getAttribute("role").toString());
	List<DynamicMenus> menulist=null;
	int roleID=0;	
	if(httpSession.getAttribute("role").toString()!=null){
		roleID=Integer.parseInt(httpSession.getAttribute("role").toString());
		menulist = dynamicMenusDao.getAllDynamicMenus(roleID);
	}else{
		
		menulist = dynamicMenusDao.getAllDynamicMenus(roleID);
	}
	
	JSONObject jsonBodyObject=new JSONObject();
	JSONArray jsonArray = new JSONArray();
	for(DynamicMenus menus:menulist){
		JSONObject jsonObject=new JSONObject();
		try {
			jsonObject.put("desc", menus.getDescription());
			jsonObject.put("id", menus.getId());
	    	jsonObject.put("url", menus.getUrl());
		} catch (JSONException e) {
			e.printStackTrace();
			logger.info("JSONException "+e.toString());
		}
		jsonArray.put(jsonObject);
	   }
	    logger.info("ajax dynamic menu controller ended"+jsonArray.toString());
	   try {
		jsonBodyObject.put("menus", jsonArray);
	    } catch (JSONException e) {
			e.printStackTrace();
	      }
		return jsonBodyObject.toString();
	}
	
	/*
	 * For First Dynamic Menu
	 */
	@RequestMapping("/menu1.htm")
	public ModelAndView dashboardRedirect(@ModelAttribute DynamicMenus menus,HttpServletRequest request,Map<String, Object> models)	
	 {
		ModelAndView andView= new ModelAndView();
		HttpSession session=request.getSession(false);
		 if (session == null || session.equals("")){
				LoginPersistance loginPersistance=new LoginPersistance();   
				models.put("userForm", loginPersistance);
				andView.setViewName("login");
				return andView;
			}else if (session.getAttribute("userId") == null || session.getAttribute("userId").equals("")){
				LoginPersistance loginPersistance=new LoginPersistance();   
				models.put("userForm", loginPersistance);
				andView.setViewName("login");
				return andView;
			}else{
		logger.info("sessin id "+session.getId());
		System.out.println(" user id is 107 "+session.getAttribute("userId").toString());
		if(session.getAttribute("userId").toString().equals("admin")){
			 logger.info(menus.getUrl()+" controller started");
			  String path=menus.getUrl();
			  logger.info(menus.getUrl()+" controller ended");
			  andView.setViewName(path.substring(0, path.length() - 4));
				return andView;
		}else{
			List<AppParameters> roleList= rolePerimissionTablesDataDao.getModuleId(session.getAttribute("role").toString());
			int roleId=0;
			logger.info("listsize "+roleList.size());
			HashSet<Integer> hashSet=new HashSet<Integer>();
			for(AppParameters parameters:roleList){
				roleId=parameters.getModuleID();
				hashSet.add(roleId);
			}
			logger.info("Role ID: "+roleId);
			logger.info("Admin Role :"+SchemaDef.ADMINISTRATION);
			logger.info("Checking value "+hashSet.contains(SchemaDef.DASHBOARD));
			if(hashSet.contains(SchemaDef.DASHBOARD)){
				 logger.info(menus.getUrl()+" controller started");
				  String path=menus.getUrl();
				  logger.info(menus.getUrl()+" controller ended");
				  andView.setViewName(path.substring(0, path.length() - 4));
					return andView;
			}else{
				return andView;
			}
			
		}
			}
	 
	}
	/*
	 * For second Dynamic Menu
	 */
	  @RequestMapping("/menu2.htm")
	  public ModelAndView earthpitRedirect(@ModelAttribute DynamicMenus menus,HttpServletRequest request,Map<String, Object> models)
	   {
		  ModelAndView andView= new ModelAndView();
		  HttpSession session=request.getSession(false);
		  if (session == null || session.equals("")){
				LoginPersistance loginPersistance=new LoginPersistance();   
				models.put("userForm", loginPersistance);
				andView.setViewName("login");
				return andView;
			}else if (session.getAttribute("userId") == null || session.getAttribute("userId").equals("")){
				LoginPersistance loginPersistance=new LoginPersistance();   
				models.put("userForm", loginPersistance);
				andView.setViewName("login");
				return andView;
			}else{
		  if(session.getAttribute("userId").toString().equals("admin")){
			  logger.info(menus.getUrl()+" controller started");
				 String path=menus.getUrl();
				 logger.info(menus.getUrl()+" controller ended");
				    andView.setViewName(path.substring(0, path.length() - 4));
					return andView;
		  }else{
			  logger.info("sessin id "+session.getId());
				List<AppParameters> roleList= rolePerimissionTablesDataDao.getModuleId(session.getAttribute("role").toString());
				int roleId=0;
				logger.info("listsize "+roleList.size());
				HashSet<Integer> hashSet=new HashSet<Integer>();
				for(AppParameters parameters:roleList){
					roleId=parameters.getModuleID();
					hashSet.add(roleId);
				}
				logger.info("Role ID: "+roleId);
				logger.info("Admin Role :"+SchemaDef.ADMINISTRATION);
				if(hashSet.contains(SchemaDef.EARTHPITMONITORING)){
					logger.info(menus.getUrl()+" controller started");
					 String path=menus.getUrl();
					 logger.info(menus.getUrl()+" controller ended");
					    andView.setViewName(path.substring(0, path.length() - 4));
						return andView;
				}else{
					return andView;
				}
		  }
			
			}
	   }
	     /*
		 * For third Dynamic Menu
		 */
	  @RequestMapping("/menu3.htm")
	  public ModelAndView alertRedirect(@ModelAttribute DynamicMenus menus,HttpServletRequest request,Map<String, Object> models)
	   {
		  
		    ModelAndView andView= new ModelAndView();
			HttpSession session=request.getSession(false);
			if (session == null || session.equals("")){
				LoginPersistance loginPersistance=new LoginPersistance();   
				models.put("userForm", loginPersistance);
				andView.setViewName("login");
				return andView;
			}else if (session.getAttribute("userId") == null || session.getAttribute("userId").equals("")){
				LoginPersistance loginPersistance=new LoginPersistance();   
				models.put("userForm", loginPersistance);
				andView.setViewName("login");
				return andView;
			}else{
			 if(session.getAttribute("userId").toString().equals("admin")){
				 logger.info(menus.getUrl()+" controller started");
				  String path=menus.getUrl();
				  logger.info(menus.getUrl()+" controller ended");
				 
				  andView.setViewName(path.substring(0, path.length() - 4));
				return andView;
			 }else{
				 logger.info("sessin id "+session.getId());
					List<AppParameters> roleList= rolePerimissionTablesDataDao.getModuleId(session.getAttribute("role").toString());
					int roleId=0;
					HashSet<Integer> hashSet=new HashSet<Integer>();
					for(AppParameters parameters:roleList){
						roleId=parameters.getModuleID();
						hashSet.add(roleId);
					}
					logger.info("Role ID: "+roleId);
					logger.info("Admin Role :"+SchemaDef.ADMINISTRATION);
					if(hashSet.contains(SchemaDef.ALERTMANAGEMENT)){
						logger.info(menus.getUrl()+" controller started");
						  String path=menus.getUrl();
						  logger.info(menus.getUrl()+" controller ended");
						  
						  andView.setViewName(path.substring(0, path.length() - 4));
							return andView;
					}else{

						 andView.setViewName("unauthorized");
							return andView;
					}
				  
			 }
			
			}
		  
	    }
	    /*
		 * For Fourth Dynamic Menu
		 */
	  @RequestMapping("/menu4.htm")
	  public ModelAndView configPageCount(@ModelAttribute DynamicMenus menus,HttpServletRequest request,Map<String, Object> models)
		{
		  ModelAndView andView= new ModelAndView();
			HttpSession session=request.getSession(false);
			if (session == null || session.equals("")){
				LoginPersistance loginPersistance=new LoginPersistance();   
				models.put("userForm", loginPersistance);
				andView.setViewName("login");
				return andView;
			}else if (session.getAttribute("userId") == null || session.getAttribute("userId").equals("")){
				LoginPersistance loginPersistance=new LoginPersistance();   
				models.put("userForm", loginPersistance);
				andView.setViewName("login");
				return andView;
			}else{
			 if(session.getAttribute("userId").toString().equals("admin")){
					logger.info("config Page count Controller Started..");
					 int employecount=twoFieldDao.homePageCount(SchemaDef.m_employees);
					 int locationcount=twoFieldDao.homePageCount(SchemaDef.M_LOCATIONS);
					 int devicecount=twoFieldDao.homePageCount(SchemaDef.M_DEVICE);
					 int earthpitcount=twoFieldDao.homePageCount(SchemaDef.M_EARTHPITS);
					 int escalationcount=twoFieldDao.homePageCount(SchemaDef.M_ESCALATION);
					 int deviearthcount=twoFieldDao.homePageCount(SchemaDef.M_DEVICE_EARTHPITS);
					 int groupcount=twoFieldDao.homePageCount(SchemaDef.M_GROUPS);
					
					 andView.addObject("employe",  employecount);
					 andView.addObject("locations",  locationcount);
					 andView.addObject("device",  devicecount);
					 andView.addObject("earthpit",earthpitcount);
					 andView.addObject("escalation",  escalationcount);
					 andView.addObject("deviearth",  deviearthcount);
					 andView.addObject("group", groupcount);
					 logger.info(menus.getUrl()+" controller started");
					 String path=menus.getUrl();
					 logger.info(menus.getUrl()+" controller ended");
					 andView.setViewName(path.substring(0, path.length() - 4));
					 logger.info("config Page count Controller end..");
					 return andView;
			 }else{
				 logger.info("sessin id "+session.getId());
					List<AppParameters> roleList= rolePerimissionTablesDataDao.getModuleId(session.getAttribute("role").toString());
					int roleId=0;
					HashSet<Integer> hashSet=new HashSet<Integer>();
					for(AppParameters parameters:roleList){
						roleId=parameters.getModuleID();
						hashSet.add(roleId);
					}
					logger.info("Role ID: "+roleId);
					logger.info("Admin Role :"+SchemaDef.ADMINISTRATION);
					if(hashSet.contains(SchemaDef.CONFIGURATION)){
						logger.info("config Page count Controller Started..");
						 int employecount=twoFieldDao.homePageCount(SchemaDef.m_employees);
						 int locationcount=twoFieldDao.homePageCount(SchemaDef.M_LOCATIONS);
						 int devicecount=twoFieldDao.homePageCount(SchemaDef.M_DEVICE);
						 int earthpitcount=twoFieldDao.homePageCount(SchemaDef.M_EARTHPITS);
						 int escalationcount=twoFieldDao.homePageCount(SchemaDef.M_ESCALATION);
						 int deviearthcount=twoFieldDao.homePageCount(SchemaDef.M_DEVICE_EARTHPITS);
						 
						
						 
						 andView.addObject("employe",  employecount);
						 andView.addObject("location",  locationcount);
						 andView.addObject("device",  devicecount);
						 andView.addObject("earthpit",earthpitcount);
						 andView.addObject("escalation",  escalationcount);
						 andView.addObject("deviearth",  deviearthcount);
						
						 logger.info(menus.getUrl()+" controller started");
						 String path=menus.getUrl();
						 logger.info(menus.getUrl()+" controller ended");
						 andView.setViewName(path.substring(0, path.length() - 4));
						 logger.info("config Page count Controller end..");
						 return andView;
					}else{
						andView.setViewName("unauthorized");
						return andView;
					}
			 }
			
			}
			
			 
		}
	/*
	 * For fifth Dynamic Menu
	 */
	@RequestMapping("/menu5.htm")
	public ModelAndView adminPageCount(@ModelAttribute DynamicMenus menus,HttpServletRequest request,Map<String, Object> models)
	
	{
		ModelAndView andView= new ModelAndView();
		HttpSession session=request.getSession(false);
		logger.info(session);
		
		
		if (session == null || session.equals("")){
			LoginPersistance loginPersistance=new LoginPersistance();   
			models.put("userForm", loginPersistance);
			andView.setViewName("login");
			return andView;
		}else if (session.getAttribute("userId") == null || session.getAttribute("userId").equals("")){
			LoginPersistance loginPersistance=new LoginPersistance();   
			models.put("userForm", loginPersistance);
			andView.setViewName("login");
			return andView;
		}else{
		 if(session.getAttribute("userId").toString().equals("admin")){
			 int rolescount=twoFieldDao.homePageCount(SchemaDef.ROLE);
			 int pagescount=twoFieldDao.homePageCount(SchemaDef.MODULE);
			 int actioncount=twoFieldDao.homePageCount(SchemaDef.ACTION);
			 int categorycount=twoFieldDao.homePageCount(SchemaDef.CATEGORY);
			 int zonecount=twoFieldDao.homePageCount(SchemaDef.ZONE);
			 int usertypecount=twoFieldDao.homePageCount(SchemaDef.USERTYPE);
			 int rolepermissioncount=twoFieldDao.homePageCount(SchemaDef.M_ROLEPERIMISSION);
			 int locationipcount=twoFieldDao.homePageCount(SchemaDef.M_LOCATIONIP);
			 int menucount=twoFieldDao.homePageCount(SchemaDef.MODULE);
			 
			 
			 andView.addObject("roles",  rolescount);
			 andView.addObject("page",  pagescount);
			 andView.addObject("action",  actioncount);
			 andView.addObject("category",categorycount);
			 andView.addObject("zone",  zonecount);
			 andView.addObject("usertype",  usertypecount);
			 andView.addObject("rolepermission",  rolepermissioncount);
			 andView.addObject("locationip",  locationipcount);
			 andView.addObject("menu",  menucount);
			logger.info(menus.getUrl()+" controller started");
			 String path=menus.getUrl();
			 logger.info(menus.getUrl()+" controller ended");
			 andView.setViewName(path.substring(0, path.length() - 4));
			 logger.info("admin Page count Controller end..");
			 return andView;
		 }else{
			 logger.info("sessin id "+session.getId());
				List<AppParameters> roleList= rolePerimissionTablesDataDao.getModuleId(session.getAttribute("role").toString());
				int roleId=0;
				HashSet<Integer> hashSet=new HashSet<Integer>();
				for(AppParameters parameters:roleList){
					roleId=parameters.getModuleID();
					hashSet.add(roleId);
				}
				logger.info("Role ID: "+roleId);
				logger.info("Admin Role :"+SchemaDef.ADMINISTRATION);
				if(hashSet.contains(SchemaDef.ADMINISTRATION)){
					logger.info("admin Page count Controller Started..");
					 int rolescount=twoFieldDao.homePageCount(SchemaDef.ROLE);
					 int pagescount=twoFieldDao.homePageCount(SchemaDef.MODULE);
					 int actioncount=twoFieldDao.homePageCount(SchemaDef.ACTION);
					 int categorycount=twoFieldDao.homePageCount(SchemaDef.CATEGORY);
					 int zonecount=twoFieldDao.homePageCount(SchemaDef.ZONE);
					 int usertypecount=twoFieldDao.homePageCount(SchemaDef.USERTYPE);
					 int rolepermissioncount=twoFieldDao.homePageCount(SchemaDef.M_ROLEPERIMISSION);
					 int locationipcount=twoFieldDao.homePageCount(SchemaDef.M_LOCATIONIP);
					 int menucount=twoFieldDao.homePageCount(SchemaDef.MODULE);
					 
					 andView.addObject("roles",  rolescount);
					 andView.addObject("page",  pagescount);
					 andView.addObject("action",  actioncount);
					 andView.addObject("category",categorycount);
					 andView.addObject("zone",  zonecount);
					 andView.addObject("usertype",  usertypecount);
					 andView.addObject("rolepermission",  rolepermissioncount);
					 andView.addObject("locationip",  locationipcount);
					 andView.addObject("menu",  menucount);
					logger.info(menus.getUrl()+" controller started");
					 String path=menus.getUrl();
					 logger.info(menus.getUrl()+" controller ended");
					 andView.setViewName(path.substring(0, path.length() - 4));
					 logger.info("admin Page count Controller end..");
					 return andView;
				}else{
				andView.setViewName("unauthorized");
				return andView;
				}
		 }
		
	}
	}
}