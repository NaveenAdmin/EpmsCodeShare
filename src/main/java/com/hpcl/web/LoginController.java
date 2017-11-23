/**
* @author  :Prasad
* @version :1.0
* @since   2015-10-09
* @ModifiedDate	:2015-11-02.
*/
package com.hpcl.web;

import java.util.List;
import java.util.Map;








import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hpcl.dao.ConfigurationDao;
import com.hpcl.dao.RolePerimissionTablesDataDao;
import com.hpcl.ldap.ADAuthenticator;
import com.hpcl.persistence.AppParameters;
import com.hpcl.persistence.LoginPersistance;
import com.hpcl.utill.SchemaDef;

@Controller
@Configuration
@ComponentScan(basePackages = { "com.hpcl.web*" })
@PropertySource("classpath:login.properties")
public class LoginController {
	private static final Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	ConfigurationDao threeFieldDao;
	@Autowired
	RolePerimissionTablesDataDao roleDao;
	@Value("${employeeid}")
	private String username;
	
	@Value("${password}")
	private String password;
	@Value("${loginfail}")
	private String loginfail;
	
	@Value("${version}")
	private String version;
	
	@Value("${zonaladmin}")
	private String zonaladmin;
	
	@Value("${SuperAdmin}")
	private String SuperAdmin;
	
	@Value("${zoneuserid}")
	private String zoneuserid;
	
	@Value("${zonepassword}")
	private String zonepassword;
	
	@Value("${eastzone}")
	private String eastzone;
	
	@Value("${eastzonepassword}")
	private String eastzonepassword;
	
	
	@Value("${westzone}")
	private String westzone;
	
	@Value("${westzonepassword}")
	private String westzonepassword;
	
	@Value("${southzone}")
	private String southzone;
	
	@Value("${southzonepassword}")
	private String southzonepassword;
	
	@Value("${northzone}")
	private String northzone;
	
	@Value("${northzonepassword}")
	private String northzonepassword;
	
	/* Zonal View */
	@Value("${eastview}")
	private String eastview;
	
	@Value("${eastviewpassword}")
	private String eastviewpassword;
	
	
	@Value("${westview}")
	private String westview;
	
	@Value("${westviewpassword}")
	private String westviewpassword;
	
	@Value("${southview}")
	private String southview;
	
	@Value("${southviewpassword}")
	private String southviewpassword;
	
	@Value("${northview}")
	private String northview;
	
	@Value("${northviewpassword}")
	private String northviewpassword;
	
	@Value("${managementview}")
	private String managementview;
	
	@Value("${managementviewpassword}")
	private String managementviewpassword;
	

	@RequestMapping("/welcome.htm")
	
	public ModelAndView redirect(Map<String, Object> model)
	{
    logger.info("Loading Application  ");
    LoginPersistance loginPersistance=new LoginPersistance();
    ModelAndView andView=new ModelAndView();
    andView.addObject("version", version);
    andView.setViewName("login");
    model.put("userForm", loginPersistance);
   
    return andView;
    }
@RequestMapping("/home.htm")
	
	public String redirectHomapage(Map<String, Object> model)
	{
    logger.info("Loading Application  ");
    LoginPersistance loginPersistance=new LoginPersistance();
    
    model.put("userForm", loginPersistance);
   
    return "welcome";
    }
	
	@RequestMapping(value="/login.htm",method = RequestMethod.POST)
	public ModelAndView login(@Valid @ModelAttribute ("userForm") LoginPersistance loginPersistance, BindingResult result,Map<String, Object> model,HttpServletRequest request)
	{
		
		
		String user=loginPersistance.getEmployeeId();
		String pwd=loginPersistance.getPassword();
		
		
		
	String page;
    logger.info("login controller started  ");
    logger.info(version);
    ModelAndView modelView = new ModelAndView();
    modelView.addObject("title", "HPCL - Database Authentication");
    logger.info("username11 :"+user);
    logger.info("password :"+pwd);
   logger.info("config eastzone :"+eastzone);
    /*logger.info("config password :"+password);*/
    
    if(username.equals(user)&&password.equals(pwd)){
    	logger.info("admin");
    	//message.properties values authentication code start
    	HttpSession session=request.getSession(true);
    	session.setAttribute("userId", "admin");
    	session.setAttribute("location", "Super Administrator");
    	session.setAttribute("userName", "admin");
    	session.setAttribute("role", "0");
    	session.setAttribute("zoneStatic", "0");
    	session.setAttribute("version",version);
    	logger.info("Super Admin Login Success");
    	modelView.setViewName("welcome");
    }
    else if(zoneuserid.equals(user)&&zonepassword.equals(pwd)){
    	logger.info("zonal");
    	//message.properties values authentication code start
    	HttpSession session=request.getSession(true);
    	session.setAttribute("userId", "admin");
    	session.setAttribute("location", "Zonal Administrator");
    	session.setAttribute("userName", "Zonal Admin");
    	session.setAttribute("role", "1");
    	session.setAttribute("zoneStatic", "0");
    	session.setAttribute("version",version);
    	logger.info("Zonal Admin Login Success");
    	modelView.setViewName("welcome");
    }
    else if(eastzone.equals(user)&&eastzonepassword.equals(pwd)){
    	logger.info("eastzoneITAdmin");
    	//message.properties values authentication code start
    	HttpSession session=request.getSession(true);
    	session.setAttribute("userId", "admin");
    	session.setAttribute("location", "East Zone IT Admin");
    	session.setAttribute("userName", "East Zone Admin");
    	session.setAttribute("zoneStatic", "1");
    	session.setAttribute("role", "-1");
    	session.setAttribute("version",version);
    	logger.info("East zone IT Admin Login Success");
    	modelView.setViewName("welcome");
    }  
    
    else if(westzone.equals(user)&&westzonepassword.equals(pwd)){
    	logger.info("WestzoneITAdmin");
    	//message.properties values authentication code start
    	HttpSession session=request.getSession(true);
    	session.setAttribute("userId", "admin");
    	session.setAttribute("location", "West Zone IT Admin");
    	session.setAttribute("userName", "West Zone Admin");
    	session.setAttribute("zoneStatic", "2");
    	session.setAttribute("role", "-1");
    	session.setAttribute("version",version);
    	logger.info("West Zone IT Admin Login Success");
    	modelView.setViewName("welcome");
    }
    
    else if(northzone.equals(user)&&northzonepassword.equals(pwd)){
    	logger.info("NorthzoneITAdmin");
    	//message.properties values authentication code start
    	HttpSession session=request.getSession(true);
    	session.setAttribute("userId", "admin");
    	session.setAttribute("location", "North Zone IT Admin");
    	session.setAttribute("userName", "North Zone Admin");
    	session.setAttribute("zoneStatic", "3");
    	session.setAttribute("role", "-1");
    	session.setAttribute("version",version);
    	logger.info("North Zone IT Admin Login Success");
    	modelView.setViewName("welcome");
    }
    
    else if(southzone.equals(user)&&southzonepassword.equals(pwd)){
    	logger.info("SouthzoneITAdmin");
    	//message.properties values authentication code start
    	HttpSession session=request.getSession(true);
    	session.setAttribute("userId", "admin");
    	session.setAttribute("location", "South Zone IT Admin");
    	session.setAttribute("userName", "South Zone Admin");
    	session.setAttribute("zoneStatic", "4");
    	session.setAttribute("role", "-1");
    	session.setAttribute("version",version);
    	logger.info("South Zone IT Admin Login Success");
    	modelView.setViewName("welcome");
    }
        else if(eastview.equals(user)&&eastviewpassword.equals(pwd)){
    	logger.info("eastzoneView");
    	//message.properties values authentication code start
    	HttpSession session=request.getSession(true);
    	session.setAttribute("userId", "admin");
    	session.setAttribute("location", "East Zone View");
    	session.setAttribute("userName", "East Zone Admin");
    	session.setAttribute("zoneStatic", "1");
    	session.setAttribute("role", "-2");
    	session.setAttribute("version",version);
    	logger.info("East Zone View Login Success");
    	modelView.setViewName("welcome");
    }    
    
    else if(westview.equals(user)&&westviewpassword.equals(pwd)){
    	logger.info("WestzoneView");
    	//message.properties values authentication code start
    	HttpSession session=request.getSession(true);
    	session.setAttribute("userId", "admin");
    	session.setAttribute("location", "West Zone View");
    	session.setAttribute("userName", "West Zone Admin");
    	session.setAttribute("zoneStatic", "2");
    	session.setAttribute("role", "-2");
    	session.setAttribute("version",version);
    	logger.info("West Zone View Login Success");
    	modelView.setViewName("welcome");
    }
    
    else if(northview.equals(user)&&northviewpassword.equals(pwd)){
    	logger.info("NorthzoneView");
    	//message.properties values authentication code start
    	HttpSession session=request.getSession(true);
    	session.setAttribute("userId", "admin");
    	session.setAttribute("location", "North Zone View");
    	session.setAttribute("userName", "North Zone Admin");
    	session.setAttribute("zoneStatic", "3");
    	session.setAttribute("role", "-2");
    	session.setAttribute("version",version);
    	logger.info("North Zone View Login Success");
    	modelView.setViewName("welcome");
    }
    
    else if(southview.equals(user)&&southviewpassword.equals(pwd)){
    	logger.info("SouthzoneView");
    	//message.properties values authentication code start
    	HttpSession session=request.getSession(true);
    	session.setAttribute("userId", "admin");
    	session.setAttribute("location", "South Zone View");
    	session.setAttribute("userName", "South Zone Admin");
    	session.setAttribute("zoneStatic", "4");
    	session.setAttribute("role", "-2");
    	session.setAttribute("version",version);
    	logger.info("South Zone view Login Success");
    	modelView.setViewName("welcome");
    }
	else if(managementview.equals(user)&&managementviewpassword.equals(pwd)){
    	logger.info("managementview");
    	//message.properties values authentication code start
    	HttpSession session=request.getSession(true);
    	session.setAttribute("userId", "admin");
    	session.setAttribute("location", "Management View");
    	session.setAttribute("userName", "South Zone Admin");
    	session.setAttribute("zoneStatic", "0");
    	session.setAttribute("role", "-2");
    	session.setAttribute("version",version);
    	logger.info("Management View Login Success");
    	modelView.setViewName("welcome");
    }
    
    else
    {
    	logger.info("ldap123");
    	List<AppParameters> appParametersList = threeFieldDao.userValidate(SchemaDef.m_employees, null, user, null);

    	if(appParametersList.size()>0)
    	{
    		//ldap authentication code start
        	
        	ADAuthenticator adAuthenticator = new ADAuthenticator();
        	Map<String, Object> attrs = null; 
        	try 
        	{
        	    attrs = adAuthenticator.authenticate(user.trim(), pwd.trim());
        	    //attrs = adAuthenticator.authenticate("sritcs", "Sritcs");
        	} 
        	catch (Exception ex) 
        	{
        	   ex.printStackTrace();
        	}
        	 
        	// { "name", "department","sAMAccountName","title","mail" };
        	if (attrs != null) 
        	{
        		String employeeName=null;
        		if(attrs.get("name").toString()!= null || attrs.get("name").toString()!= "")
        		{
        		employeeName=attrs.get("name").toString();
        		System.out.println("Name:"+employeeName);
        		}
        		else
        		{
        			
        		}
        		if(attrs.get("sAMAccountName").toString()!= null || attrs.get("sAMAccountName").toString()!= "")
        		{
        			String EmployeeId=attrs.get("sAMAccountName").toString();
        			System.out.println(EmployeeId);
        		}       		
        		
        		List<AppParameters> rolelist=roleDao.getSecurityRole(null, null, null, loginPersistance.getEmployeeId());
        		int role = 0;
        		String locid=null;
        		String location=null;
        		for(AppParameters parameters:rolelist){
        			role=parameters.getRoleID();
        			location=parameters.getLocation();
        			locid=parameters.getLocn();
        		}
        		  int rolescompare=role;
        		if(String.valueOf(role).equals(zonaladmin)){
        			logger.info("role 2");
        			HttpSession session=request.getSession(true);
        	    	session.setAttribute("userId", "admin");
        	    	session.setAttribute("location", location);
        	    	session.setAttribute("userName", employeeName);
        	    	session.setAttribute("zoneStatic", "0");
        	    	  
        	    	session.setAttribute("role", String.valueOf(role));
        	    	session.setAttribute("version",version);
        	    	session.setAttribute("locid",locid);
        	    	logger.info("Admin Login Success");
        	    	modelView.setViewName("welcome");
        		}
        		else{
        			logger.info("role 3");
        			logger.info("string role value "+String.valueOf(role));
        			if(role==1){
            			logger.info("ldap admin role");            			
            			/*HttpSession session=request.getSession(true);
            	    	session.setAttribute("userId", "admin");
            	    	session.setAttribute("location","Super User");
            	    	session.setAttribute("userName", employeeName);
            	    	session.setAttribute("role", "0");
            	    	session.setAttribute("version",version);
            	    	session.setAttribute("locid",locid);
            	    	logger.info("Admin Login Success");
            	    	modelView.setViewName("welcome");*/
            			
            	    	//message.properties values authentication code start
            	    	HttpSession session=request.getSession(true);
            	    	session.setAttribute("userId", "admin");
            	    	session.setAttribute("location", "Super Admin");
            	    	session.setAttribute("userName", employeeName);
            	    	session.setAttribute("role", "0");
            	    	session.setAttribute("zoneStatic", "0");
            	    	session.setAttribute("version",version);
            	    	logger.info("Admin Login Success");
            	    	modelView.setViewName("welcome");
            		}else{
            			logger.info("other ldap users");
        			logger.info("role List "+rolelist.size());
            		HttpSession httpSession=request.getSession(true);
            		logger.info("Session Id "+httpSession.getId());
            		httpSession.setAttribute("userId", loginPersistance.getEmployeeId());
            		httpSession.setAttribute("userName", employeeName);        		
            		httpSession.setAttribute("role", String.valueOf(role));
            		httpSession.setAttribute("location", location);
            		httpSession.setAttribute("version",version);
            		httpSession.setAttribute("zoneStatic", "0");
            		httpSession.setAttribute("locid",locid);
            		logger.info("userId  "+loginPersistance.getEmployeeId());
            		logger.info("userName "+employeeName);
            		logger.info("role "+String.valueOf(role));
            		logger.info("location "+location);
            		logger.info("locationid "+locid);
            		//String EmployeeTitle=attrs.get("title").toString();
            		//String EmployeeMail=attrs.get("mail").toString();
            		//System.out.println("Mail:"+EmployeeMail);
            		//System.out.println(UserName);
            	    for (String attrKey : attrs.keySet()) 
            		{        	    	
            			if (attrs.get(attrKey) instanceof String) 
            			{       				
            				//System.out.println(UserName);
            	            //System.out.println(attrKey +": "+attrs.get(attrKey));        	           
            	        }
            	    }   
            		//System.out.println("1:"+attrs);
            		//"redirect to homepage"        		
            	   modelView.setViewName("welcome");
        		}
        		
        		}
        	    }
        	else
        	{
        		System.out.println("2 LDAP authentication failed");
        		
        	}  		  
        }
    	else
    	{
    		//failed condition and should navigate to login page
    		modelView.addObject("error", "Invalid Employee ID Or Password");
    	}
    	
    	
    }
	logger.info("login controller ended");
    return modelView;
    }
	@RequestMapping(value="/login.htm",method = RequestMethod.GET)
	public ModelAndView loginGET()
	{
		 ModelAndView modelView = new ModelAndView();
		 modelView.setViewName("index");
		 return modelView;
	}
	
}