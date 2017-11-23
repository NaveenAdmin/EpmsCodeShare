/**
* @author  :Raj
* @version :1.0
* @since   2015-10-09
* @ModifiedDate	:2015-11-02
*/
package com.hpcl.web;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hpcl.dao.AdministrationDao;
import com.hpcl.dao.ConfigurationDao;
import com.hpcl.dao.DynamicMenusDao;
import com.hpcl.dao.RolePerimissionTablesDataDao;
import com.hpcl.persistence.ActionListPOJO;
import com.hpcl.persistence.AppParameters;
import com.hpcl.persistence.DynamicMenus;
import com.hpcl.persistence.LoginPersistance;
import com.hpcl.persistence.Person;
import com.hpcl.utill.CurrentDateTime;
import com.hpcl.utill.SchemaDef;

@Controller
public class PagePermissionsController {
	private static final Logger logger = Logger.getLogger(PagePermissionsController.class);
	@Autowired
    DynamicMenusDao dynamicMenusDao;
	@Autowired
	RolePerimissionTablesDataDao rolePerimissionDao;
	@Autowired
	AdministrationDao twoFieldDao;
	@Autowired
	ConfigurationDao threeFieldDao;
	@RequestMapping(value="/pagepermissions.htm",
			method = RequestMethod.GET)
	public ModelAndView printWelcome(@ModelAttribute AppParameters twoFieldPersistence,ModelMap model,
			HttpServletRequest request,Map<String, Object> modelSessionOut) {

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

		logger.info("sessin id "+session.getId());
		logger.info("sessin id "+session.getAttribute("userId").toString());
		String location=session.getAttribute("location").toString();
		String userId=session.getAttribute("userId").toString();
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
		 if(session.getAttribute("userId").toString().equals("admin")){
			 model.addAttribute("message", "How to pass data");
				model.addAttribute("selected", "Select single, "
						+ "multiple or all check box");
				logger.info("Page permissions controller started ");
				
				/*tableName=SchemaDef.USERTYPE;
				id=SchemaDef.USERTYPEID;*/
				if(twoFieldPersistence.getOperation()==0){
					logger.info("Operation 0 ");
					List<AppParameters> roleTableDataList= rolePerimissionDao.getTableData(SchemaDef.ROLE,SchemaDef.ACTIVE_FLAG,location,userId);
					//List<AppParameters> moduleTableDataList= rolePerimissionDao.getTableData(SchemaDef.MODULE,SchemaDef.ACTIVE_FLAG);
					List<AppParameters> actionTableDataList= rolePerimissionDao.getTableData(SchemaDef.ACTION,SchemaDef.ACTIVE_FLAG,location,userId);
					List<AppParameters> tableDataList= rolePerimissionDao.getTableData("m_roleperimissions",SchemaDef.ACTIVE_FLAG,location,userId);
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
					model.addAttribute("menu",  menulist);
					model.addAttribute("role",  roleTableDataList);
					//model.addAttribute("module",  moduleTableDataList);
					model.addAttribute("action",  actionTableDataList);
					model.addAttribute("table",  tableDataList);

					
					
					List<Person> personsList = new ArrayList<Person>();
					Person person2 = new Person();
					
					
					for(AppParameters appParameters:actionTableDataList){
						
						Person person = new Person();
						
						person.setId(String.valueOf(appParameters.getActionID()));
						person.setDepartment(appParameters.getDescription());
						
						personsList.add(person);

						
					}

					person2.setPersonsList(personsList);
					model.addAttribute("person",person2);
					request.getSession().setAttribute("personsList", 
							personsList);
					
					logger.info("role "+roleTableDataList);
				//	logger.info("module "+moduleTableDataList);
					logger.info("action "+actionTableDataList);
					logger.info("table "+tableDataList.size());
					
				}
				logger.info("Page permissions controller end ");

				return new ModelAndView("pagepermissions");

		 }else{
			 if(hashSet.contains(SchemaDef.PAGEROLESPERMISSION)){
				 int actionID=0;
					HashSet<Integer> hashSetAction=new HashSet<Integer>();
						List<AppParameters> roleList= rolePerimissionDao.getActionId(session.getAttribute("role").toString());
						for(AppParameters parameters:roleList){
							actionID=parameters.getActionID();
							logger.info("Page IDs :"+actionID);
							hashSetAction.add(actionID);
						}
					model.addAttribute("message", "How to pass data");
					model.addAttribute("selected", "Select single, "
							+ "multiple or all check box");
					logger.info("Page permissions controller started ");
					
					/*tableName=SchemaDef.USERTYPE;
					id=SchemaDef.USERTYPEID;*/
					if(twoFieldPersistence.getOperation()==0){
						logger.info("Operation 0 ");
						List<AppParameters> roleTableDataList= rolePerimissionDao.getTableData(SchemaDef.ROLE,SchemaDef.ACTIVE_FLAG,location,userId);
						//List<AppParameters> moduleTableDataList= rolePerimissionDao.getTableData(SchemaDef.MODULE,SchemaDef.ACTIVE_FLAG);
						List<AppParameters> actionTableDataList= rolePerimissionDao.getTableData(SchemaDef.ACTION,SchemaDef.ACTIVE_FLAG,location,userId);
						List<AppParameters> tableDataList= rolePerimissionDao.getTableData("m_roleperimissions",SchemaDef.ACTIVE_FLAG,location,userId);
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
						model.addAttribute("role",  roleTableDataList);
						//model.addAttribute("module",  moduleTableDataList);
						model.addAttribute("action",  actionTableDataList);
						model.addAttribute("table",  tableDataList);
						for(DynamicMenus dynamicMenus:menulist){
							logger.info("menu id:"+dynamicMenus.getId());
						}
						model.addAttribute("menu",  menulist);
						
						List<Person> personsList = new ArrayList<Person>();
						Person person2 = new Person();
						
						
						for(AppParameters appParameters:actionTableDataList){
							
							Person person = new Person();
							
							person.setId(String.valueOf(appParameters.getActionID()));
							person.setDepartment(appParameters.getDescription());
							
							personsList.add(person);

							
						}

						person2.setPersonsList(personsList);
						model.addAttribute("person",person2);
						request.getSession().setAttribute("personsList", 
								personsList);
						
						logger.info("role "+roleTableDataList);
					//	logger.info("module "+moduleTableDataList);
						logger.info("action "+actionTableDataList);
						logger.info("table "+tableDataList.size());
						
					}
					logger.info("Page permissions controller end ");
		
					return new ModelAndView("pagepermissions");
				}else{

					return new ModelAndView("unauthorized");
				}
 
		 }
				
		}

	}
	

	
	@RequestMapping(value="/multiCheckbox.htm",
			method = RequestMethod.POST)
	public String multiCheckbox(@ModelAttribute AppParameters twoFieldPersistence,Person person, 
			ModelMap model,HttpServletRequest request) {
		logger.info("multiCheckbox controller started");
		model.addAttribute("message", "How to pass data");
		model.addAttribute("selected", "Selected check box data");
		List<Person> personList = (List<Person>)request.getSession().getAttribute("personsList");
		logger.info("total checkbox size"+personList.size());
		
		List<Person> selectedPersonList = new ArrayList<Person>();
		if (null != personList && personList.size() != 0) {
			
			for (Person person3 : personList) {
				List<String> selectedPerson = person.getSelectedCheckBox();
			
				if (null != selectedPerson && selectedPerson.size()!=0) {
					for (String string : selectedPerson) {
						
						if (string.equalsIgnoreCase(person3.getId())) {
							
							selectedPersonList.add(person3);
						}
					}
				}
				
			}
		}
		logger.info("Role ID "+twoFieldPersistence.getRoleID());
		logger.info("Menu ID "+twoFieldPersistence.getMenuID());
		logger.info("Page ID "+twoFieldPersistence.getPageID());
		twoFieldPersistence.setCreatedOn(CurrentDateTime.getCurrentDate());
		twoFieldPersistence.setCreatedBy("Admin");
		twoFieldPersistence.setStatus("A");
		for(Person persons:selectedPersonList){
			twoFieldPersistence.setActionID(Integer.parseInt(persons.getId()));
			rolePerimissionDao.saveData("m_roleperimissions", twoFieldPersistence);
			logger.info("Action id "+persons.getId()+" inserted");
		}
		HttpSession session=request.getSession(false);
		String location=session.getAttribute("location").toString();
		String userId=session.getAttribute("userId").toString();
		
		List<AppParameters> roleTableDataList= rolePerimissionDao.getTableData(SchemaDef.ROLE,SchemaDef.ACTIVE_FLAG,location,userId);
		//List<AppParameters> moduleTableDataList= rolePerimissionDao.getTableData(SchemaDef.MODULE,SchemaDef.ACTIVE_FLAG);
		List<AppParameters> actionTableDataList= rolePerimissionDao.getTableData(SchemaDef.ACTION,SchemaDef.ACTIVE_FLAG,location,userId);
		List<AppParameters> tableDataList= rolePerimissionDao.getTableData("m_roleperimissions",twoFieldPersistence.getStatus(),location,userId);
		
		model.addAttribute("role",  roleTableDataList);
		//model.addAttribute("module",  moduleTableDataList);
		model.addAttribute("action",  actionTableDataList);
		model.addAttribute("table",  tableDataList);
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
		
		model.addAttribute("menu",  menulist);
		
		List<Person> personsList = new ArrayList<Person>();
		Person person2 = new Person();
		
		
		for(AppParameters appParameters:actionTableDataList){
			
			Person persons = new Person();
			
			persons.setId(String.valueOf(appParameters.getActionID()));
			persons.setDepartment(appParameters.getDescription());
			
			personsList.add(persons);

			
		}

		person2.setPersonsList(personsList);
		model.addAttribute("person",person2);
		request.getSession().setAttribute("personsList", 
				personsList);
		logger.info("multiCheckbox controller end");
		return "pagepermissions";

	}
	@RequestMapping(value="/updatemultiCheckbox.htm",
			method = RequestMethod.POST)
	public ModelAndView updateMultiCheckbox(@ModelAttribute AppParameters twoFieldPersistence,Person person, 
			ModelMap model,HttpServletRequest request) {
		logger.info("Update multiCheckbox controller started");
		model.addAttribute("message", "How to pass data");
		model.addAttribute("selected", "Selected check box data");
		List<Person> personList = (List<Person>)request.getSession().getAttribute("personsList");
		logger.info("total checkbox size"+personList.size());
		
		List<Person> selectedPersonList = new ArrayList<Person>();
		if (null != personList && personList.size() != 0) {
			
			for (Person person3 : personList) {
				List<String> selectedPerson = person.getSelectedCheckBox();
				
				if (null != selectedPerson && selectedPerson.size()!=0) {
					for (String string : selectedPerson) {
						
						if (string.equalsIgnoreCase(person3.getId())) {
							
							selectedPersonList.add(person3);
						}
					}
				}
				
			}
		}
		logger.info("Role ID "+twoFieldPersistence.getRoleID());
		logger.info("Module ID "+twoFieldPersistence.getModuleID());
		logger.info("Page ID "+twoFieldPersistence.getPageID());
		twoFieldPersistence.setEditedOn(CurrentDateTime.getCurrentDate());
		twoFieldPersistence.setEditedBy("Admin");
		twoFieldPersistence.setStatus("A");
		for(Person persons:selectedPersonList){
			twoFieldPersistence.setActionID(Integer.parseInt(persons.getId()));
			rolePerimissionDao.updateData("m_roleperimissions", twoFieldPersistence);
			logger.info("action id "+persons.getId()+" inserted");
		}
		HttpSession session=request.getSession(false);
		String location=session.getAttribute("location").toString();
		String userId=session.getAttribute("userId").toString();
		
		List<AppParameters> roleTableDataList= rolePerimissionDao.getTableData(SchemaDef.ROLE,SchemaDef.ACTIVE_FLAG,location,userId);
		List<AppParameters> moduleTableDataList= rolePerimissionDao.getTableData(SchemaDef.MODULE,SchemaDef.ACTIVE_FLAG,location,userId);
		List<AppParameters> actionTableDataList= rolePerimissionDao.getTableData(SchemaDef.ACTION,SchemaDef.ACTIVE_FLAG,location,userId);
		List<AppParameters> tableDataList= rolePerimissionDao.getTableData("m_roleperimissions",twoFieldPersistence.getStatus(),location,userId);
		
		model.addAttribute("role",  roleTableDataList);
		//model.addAttribute("module",  moduleTableDataList);
		model.addAttribute("action",  actionTableDataList);
		model.addAttribute("table",  tableDataList);
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
		model.addAttribute("menu",  menulist);
		List<Person> personsList = new ArrayList<Person>();
		Person person2 = new Person();
		
		
		for(AppParameters appParameters:actionTableDataList){
			
			Person persons = new Person();
			
			persons.setId(String.valueOf(appParameters.getActionID()));
			persons.setDepartment(appParameters.getDescription());
			
			personsList.add(persons);

			
		}

		person2.setPersonsList(personsList);
		model.addAttribute("person",person2);
		request.getSession().setAttribute("personsList", 
				personsList);
		logger.info("Update multiCheckbox controller end");
		return new ModelAndView("pagepermissions");

	}
	@RequestMapping(value="/deletemultiCheckbox.htm",
			method = RequestMethod.POST)
	public ModelAndView deleteMultiCheckbox(@ModelAttribute AppParameters twoFieldPersistence,Person person, 
			ModelMap model,HttpServletRequest request) {
		
		rolePerimissionDao.deleteData("m_roleperimissions", twoFieldPersistence);
		
		HttpSession session=request.getSession(false);
		String location=session.getAttribute("location").toString();
		String userId=session.getAttribute("userId").toString();
		
		List<AppParameters> roleTableDataList= rolePerimissionDao.getTableData(SchemaDef.ROLE,SchemaDef.ACTIVE_FLAG,location,userId);
		List<AppParameters> moduleTableDataList= rolePerimissionDao.getTableData(SchemaDef.MODULE,SchemaDef.ACTIVE_FLAG,location,userId);
		List<AppParameters> actionTableDataList= rolePerimissionDao.getTableData(SchemaDef.ACTION,SchemaDef.ACTIVE_FLAG,location,userId);
		List<AppParameters> tableDataList= rolePerimissionDao.getTableData("m_roleperimissions","A",location,userId);

		model.addAttribute("role",  roleTableDataList);
		//model.addAttribute("module",  moduleTableDataList);
		model.addAttribute("action",  actionTableDataList);
		model.addAttribute("table",  tableDataList);
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
		model.addAttribute("menu",  menulist);
		List<Person> personsList = new ArrayList<Person>();
		Person person2 = new Person();
		
		
		for(AppParameters appParameters:actionTableDataList){
			
			Person persons = new Person();
			
			persons.setId(String.valueOf(appParameters.getActionID()));
			persons.setDepartment(appParameters.getDescription());
			
			personsList.add(persons);

			
		}

		person2.setPersonsList(personsList);
		model.addAttribute("person",person2);
		request.getSession().setAttribute("personsList", 
				personsList);
		logger.info("Update multiCheckbox controller end");
		return  new ModelAndView("pagepermissions");
		
	}
	
	
	
	@RequestMapping(value="/devicemultiCheckbox.htm",
			method = RequestMethod.POST)
	   public String DevicemultiCheckbox(@ModelAttribute AppParameters twoFieldPersistence,Person person, 
	   ModelMap model,HttpServletRequest request) {
		logger.info("multiCheckbox Device controller started");
		model.addAttribute("message", "How to pass data");
		model.addAttribute("selected", "Selected check box data");
		
		
		logger.info("Location ID "+twoFieldPersistence.getLocationID());
		logger.info("Device ID "+twoFieldPersistence.getDeviceId());
		twoFieldPersistence.setCreatedOn(CurrentDateTime.getCurrentDate());
		twoFieldPersistence.setCreatedBy("Admin");
		twoFieldPersistence.setStatus("A");
		String[] pits = request.getParameterValues("pits");
		int count=twoFieldDao.deviceEarthpitPageCount(SchemaDef.M_DEVICE_EARTHPITS, twoFieldPersistence);
		logger.info("Existing Count: "+count);
		List<AppParameters> appParameters=twoFieldDao.maxEarthPitValue(SchemaDef.M_DEVICE, twoFieldPersistence);
		int maxCount = 0;
		for(AppParameters appParameters2:appParameters){
			maxCount=Integer.parseInt(appParameters2.getEarthPitValues());
		}
		logger.info("MaxCount: "+maxCount);
		if(count<maxCount){
			logger.info("Earth pit count "+count);
			if(pits.length<=maxCount){
				logger.info("selected checkbox count "+pits.length);
				int pitscount=count+pits.length;
				if(pitscount<=maxCount){
					logger.info("total earth pit  count "+pitscount);
					if (pits != null) 
					   {
					      for (int i = 0; i < pits.length; i++) 
					      {
					    	  String earthpitID=pits[i];
					    	  twoFieldPersistence.setEarthpitID(earthpitID);
					    	  rolePerimissionDao.saveEarthpitData(SchemaDef.M_DEVICE_EARTHPITS ,twoFieldPersistence);
					         logger.info("inserted earth pit id:"+pits[i]);
					      }
					   }
				}
				
			}
		}
		
		String tableName1=SchemaDef.M_DEVICE_EARTHPITS;
		String id=SchemaDef.DEVICE_EARTHPIT_ID;
		HttpSession session=request.getSession(false);
		String location=session.getAttribute("location").toString();
		String userId=session.getAttribute("userId").toString();
		List<AppParameters> appParametersList = threeFieldDao.getAllDeviceEarthpitsDetails(tableName1,id,SchemaDef.ACTIVE_FLAG,location,userId);
		model.put("list",  appParametersList);
		
		
		List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
		model.put("location",  locationTableDataList);
		logger.info("multiCheckbox Device controller end");
		return "device-earthpit";

	}
	@RequestMapping(value="/groupmultiCheckbox.htm",
			method = RequestMethod.POST)
			public String GroupemultiCheckbox(@ModelAttribute AppParameters twoFieldPersistence,Person person, 
				ModelMap model,HttpServletRequest request) {
				logger.info("multiCheckbox group controller started");
				model.addAttribute("message", "How to pass data");
				model.addAttribute("selected", "Selected check box data");
				
				
				logger.info("Group ID "+twoFieldPersistence.getGroupId());
				logger.info("Employee "+twoFieldPersistence.getEmployeeID());
				twoFieldPersistence.setCreatedOn(CurrentDateTime.getCurrentDate());
				twoFieldPersistence.setCreatedBy("Admin");
				twoFieldPersistence.setStatus("A");
				String[] pits = request.getParameterValues("pits");
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
				HttpSession session=request.getSession(false);
				String location=session.getAttribute("location").toString();
				String userId=session.getAttribute("userId").toString();
							List<AppParameters> appParametersList = threeFieldDao.getAllAlertGroupDetails(SchemaDef.M_GROUPS, SchemaDef.GROUPID,SchemaDef.ACTIVE_FLAG,location,userId);
							model.put("list",  appParametersList);
							System.out.println("Configuration alertgroup List size "+appParametersList.size());
							List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
							model.put("location",  locationTableDataList);
				            return "alertgroups";

			}
			
	
	
	
	@RequestMapping(value="/deviceupdatemultiCheckbox.htm",
			method = RequestMethod.POST)
	public String DeviceUpdatemultiCheckbox(@ModelAttribute AppParameters twoFieldPersistence,Person person, 
			ModelMap model,HttpServletRequest request) {
		logger.info("multiCheckbox update Device controller started");
		model.addAttribute("message", "How to pass data");
		model.addAttribute("selected", "Selected check box data");
		
		
		logger.info("Location ID "+twoFieldPersistence.getLocationID());
		logger.info("Device ID "+twoFieldPersistence.getDeviceId());
		twoFieldPersistence.setCreatedOn(CurrentDateTime.getCurrentDate());
		twoFieldPersistence.setCreatedBy("Admin");
		twoFieldPersistence.setStatus("A");
		String[] pits = request.getParameterValues("uppits");
		int count=twoFieldDao.deviceEarthpitPageCount(SchemaDef.M_DEVICE_EARTHPITS, twoFieldPersistence);
		if(count<5){
			logger.info("Earth pit count "+count);
			if(pits!=null){
			if(pits.length<=5){
				logger.info("selected checkbox count "+pits.length);
				int pitscount=count+pits.length;
				if(pitscount<=5){
					logger.info("total earth pit  count "+pitscount);
					if (pits != null) 
					   {
					      for (int i = 0; i < pits.length; i++) 
					      {
					    	  String earthpitID=pits[i];
					    	  twoFieldPersistence.setEarthpitID(earthpitID);
					    	  rolePerimissionDao.saveEarthpitData(SchemaDef.M_DEVICE_EARTHPITS ,twoFieldPersistence);
					         logger.info("inserted earth pit id:"+pits[i]);
					      }
					   }
				}
				
			}
			}
			else{
				String tableName1=SchemaDef.M_DEVICE_EARTHPITS;
				String id=SchemaDef.DEVICE_EARTHPIT_ID;
				HttpSession session=request.getSession(false);
				String location=session.getAttribute("location").toString();
				String userId=session.getAttribute("userId").toString();
				List<AppParameters> appParametersList = threeFieldDao.getAllDeviceEarthpitsDetails(tableName1,id,SchemaDef.ACTIVE_FLAG,location,userId);
				model.put("list",  appParametersList);

				List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
				model.put("location",  locationTableDataList);
				logger.info("multiCheckbox Device controller end");
				return "device-earthpit";	
			}
		}		
		String tableName1=SchemaDef.M_DEVICE_EARTHPITS;
		String id=SchemaDef.DEVICE_EARTHPIT_ID;
		HttpSession session=request.getSession(false);
		String location=session.getAttribute("location").toString();
		String userId=session.getAttribute("userId").toString();
		List<AppParameters> appParametersList = threeFieldDao.getAllDeviceEarthpitsDetails(tableName1,id,SchemaDef.ACTIVE_FLAG,location,userId);
		model.put("list",  appParametersList);
		
		List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
		model.put("location",  locationTableDataList);
		logger.info("multiCheckbox Device controller end");
		return "device-earthpit";

	}
	
	@RequestMapping(value="/groupupdatemultiCheckbox.htm",
			method = RequestMethod.POST)
	public String GroupUpdatemultiCheckbox(@ModelAttribute AppParameters twoFieldPersistence,Person person, 
			ModelMap model,HttpServletRequest request) {
		logger.info("groupupdatemultiCheckbox update Device controller started");
		model.addAttribute("message", "How to pass data");
		model.addAttribute("selected", "Selected check box data");
		
		
		logger.info("Location ID "+twoFieldPersistence.getLocationID());
		logger.info("Group ID "+twoFieldPersistence.getGroupId());
		logger.info("Level "+twoFieldPersistence.getLevel());
		twoFieldPersistence.setCreatedOn(CurrentDateTime.getCurrentDate());
		twoFieldPersistence.setCreatedBy("Admin");
		twoFieldPersistence.setStatus("A");
		String[] pits = request.getParameterValues("uppits");
		logger.info("uppits Size "+pits.length+"  "+pits);

					if (pits != null) 
					   {
					      for (int i = 0; i < pits.length; i++) 
					      {
					    	  String eID=pits[i];
					    	  logger.info("EmpID "+pits[i]);
					    	  twoFieldPersistence.setEmployeeID(eID);
					    	  logger.info("'"+twoFieldPersistence.getGroupId()+"','"+twoFieldPersistence.getLevel()+"','"+twoFieldPersistence.getLocationID()+"','"+twoFieldPersistence.getEmployeeID()+"','"+twoFieldPersistence.getStatus()+"','"+twoFieldPersistence.getCreatedBy()+"','"+twoFieldPersistence.getCreatedOn()+"'");
					    	  String empName=threeFieldDao.getEmployeeID(pits[i]);
					    	  if(empName!=null){
					    		  threeFieldDao.updateGroups(twoFieldPersistence, SchemaDef.M_GROUPS);
					    	  }else{
					    		  threeFieldDao.saveAlertGroupDetails(twoFieldPersistence, SchemaDef.M_GROUPS);
					    	  }
					    	  
					         logger.info("inserted earth pit id:"+pits[i]);
					      }
					   }
		
		String tableName1=SchemaDef.M_GROUPS;
		String id=SchemaDef.GROUPID;
		HttpSession session=request.getSession(false);
		String location=session.getAttribute("location").toString();
		String userId=session.getAttribute("userId").toString();
		List<AppParameters> appParametersList = threeFieldDao.getAllAlertGroupDetails(SchemaDef.M_GROUPS, SchemaDef.GROUPID,SchemaDef.ACTIVE_FLAG,location,userId);
		model.put("list",  appParametersList);
		
		List<AppParameters> locationTableDataList= rolePerimissionDao.getTableData(SchemaDef.M_LOCATIONS,SchemaDef.ACTIVE_FLAG,location,userId);
		model.put("location",  locationTableDataList);
		
		logger.info("groupupdatemultiCheckbox Device controller end");
		return "alertgroups";

	}
	
	
}