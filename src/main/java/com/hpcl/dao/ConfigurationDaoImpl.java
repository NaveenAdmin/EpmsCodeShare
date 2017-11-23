/**
* @author  :Prasad
* @version :1.0
* @since   2015-10-09
* @ModifiedDate	:2015-11-06
*/
package com.hpcl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.hpcl.persistence.AppParameters;
import com.hpcl.utill.CurrentDateTime;
import com.hpcl.utill.SchemaDef;

public class ConfigurationDaoImpl implements ConfigurationDao 
{
	private static final Logger logger = Logger.getLogger(ConfigurationDaoImpl.class);
	@SuppressWarnings("static-access")
	String currentDate=new CurrentDateTime().getCurrentDate();
	private JdbcTemplate jdbcTemplate; 
	@Autowired
    AdministrationDao twoFieldDao;
	
	public void setJdbcTemplate(DataSource dataSource)
	{  
		jdbcTemplate = new JdbcTemplate(dataSource);
	} 
	
	/*
	 * (non-Javadoc)
	 * @see com.hpcl.dao.ThreeFieldDao#saveDetails(com.hpcl.persistence.ThreeFieldPersistence, java.lang.String, java.lang.String)
	 */
	
	public void saveDetails(AppParameters appParameters,String tableName1, String tableName2)
	{
		logger.info("Save Details method started");
		String role=null;
		//String id=null;
		try
		{
			if(tableName1.equals(SchemaDef.m_employees))
			{
				logger.info("Add employees details method Started");
				role=SchemaDef.EMPLOYEEID;
				String query="insert into "+tableName1+" ("+SchemaDef.EMPLOYEEID+",roles,"+SchemaDef.STATUS+","+SchemaDef.CREATEDBY+") values('"+appParameters.getEmployeeID()+"','"+appParameters.getRoleID()+"','"+appParameters.getStatus()+"','"+appParameters.getCreatedBy()+"')";
	  		  	logger.info("Insert New Employee Details Query :"+query);
	  		  	jdbcTemplate.update(query); 
	  		  	logger.info("Add employees details method is compleated");	 
			}
			else if(tableName1.equals(SchemaDef.M_LOCATIONS))
			{
				logger.info("Add Location details method Started");
				role=SchemaDef.LOCATIONID;
				String query="insert into "+tableName1+" ("+SchemaDef.LOCATIONID+","+SchemaDef.STATUS+","+SchemaDef.CREATEDBY+") values('"+appParameters.getLocationID()+"','"+appParameters.getStatus()+"','"+appParameters.getCreatedBy()+"')";
				logger.info("Insert New location Query :"+query);
				jdbcTemplate.update(query);
				logger.info("Add Location details method Completed");
             }
	    }
		catch(Exception ex)
		{ 
			ex.printStackTrace();
			logger.info(ex);
        }
	    logger.info("Save Details method completed");	 	
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.hpcl.dao.ThreeFieldDao#getDetails(int, java.lang.String, java.lang.String)
	 */
	
	public AppParameters getDetails(String Id, String tableName1,String tableName2)
	{
		logger.info("get details method is started");
		String role=null;
		//String id=null;
		try
		{	
			if(tableName1.equals(SchemaDef.m_employees))
			{
				logger.info("getEmployee details method started");
				role=SchemaDef.EMPLOYEEID;
				String query="select "+SchemaDef.EMPLOYEEID+","+SchemaDef.EMPLOYENAMEE+","+SchemaDef.LACATION+","+SchemaDef.ROLES+","+SchemaDef.STATUS+" from "+tableName1+" where "+SchemaDef.EMPLOYEEID+" = "+Id;  
				logger.info("getEmployee details query:"+query);
				jdbcTemplate.update(query);
				logger.info("getEmployee details method Completed");
			}
			if(tableName1.equals(SchemaDef.M_LOCATIONS))
			{
				logger.info("getlocations method Started");
		  		role=SchemaDef.LOCATIONID;
		  		String query="select "+SchemaDef.LOCATIONID+","+SchemaDef.LOCATIONNAME+","+SchemaDef.ZONES+","+SchemaDef.STATUS+" from "+tableName1+" where "+SchemaDef.LOCATIONID+" = "+Id;  
		  		logger.info("getlocations details query:"+query);
		  		jdbcTemplate.update(query);
		  		logger.info("getlocations method is completed");
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		logger.info("get details method is completed"); 
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.hpcl.dao.ThreeFieldDao#updateDetails(com.hpcl.persistence.ThreeFieldPersistence, java.lang.String, java.lang.String)
	 */
	
	public void updateDetails(AppParameters appParameters,String tableName1, String tableName2)
	{	
		logger.info("updateDetails method started");
		String role=null;
		//String id=null;
		logger.info("table name:"+tableName1);
		if(tableName1.equals(SchemaDef.m_employees))
		{	  
			logger.info("Employee updateDetails method is started");
			role=SchemaDef.EMPLOYEEID;
	  		//id=appParameters.getEmployeeID();
	  		 try
	  		 {
	  			 //String sql="UPDATE "+tableName1+" org, "+tableName2+" temp set org."+SchemaDef.EMPLOYENAMEE+" = CONCAT(temp."+SchemaDef.FIRSTNAME+", temp."+SchemaDef.LASTNAME+"), org."+SchemaDef.LACATION+" =temp."+SchemaDef.LACATION+", org."+SchemaDef.ROLES+" =temp."+SchemaDef.ROLES+", org."+SchemaDef.USERTYPES+" =temp."+SchemaDef.USERTYPES+", org."+SchemaDef.PASSWORD+"='EMS@123', org."+SchemaDef.EDITEDBY+"='"+appParameters.getCreatedBy()+"', org."+SchemaDef.EDITEDON+"=sysdate() where org."+SchemaDef.EMPLOYEEID+" =temp."+SchemaDef.EMPLOYEEID+" and org."+SchemaDef.STATUS+"='A'" ;
	  			 //String sql="UPDATE "+tableName1+" org, "+tableName2+" temp set org."+SchemaDef.EMPLOYENAMEE+" = CONCAT(temp."+SchemaDef.FIRSTNAME+", temp."+SchemaDef.LASTNAME+"), org."+SchemaDef.LACATION+" =temp."+SchemaDef.LACATION+", org."+SchemaDef.ROLES+" =temp."+SchemaDef.ROLES+", org."+SchemaDef.USERTYPES+" =temp."+SchemaDef.USERTYPES+", org."+SchemaDef.EMP_PHONENO+" =temp."+SchemaDef.EMP_PHONENO+", org."+SchemaDef.EMP_EMAIL+" =temp."+SchemaDef.EMP_EMAIL+", org."+SchemaDef.PASSWORD+"='EMS@123', org."+SchemaDef.EDITEDBY+"='"+appParameters.getCreatedBy()+"', org."+SchemaDef.EDITEDON+"=sysdate() where org."+SchemaDef.EMPLOYEEID+" =temp."+SchemaDef.EMPLOYEEID+" and org."+SchemaDef.STATUS+"='A'" ;
	  			 String sql="UPDATE "+tableName1+" org, "+tableName2+" temp set org."+SchemaDef.LDAP_EMP_SAL_GROUP+" =temp."+SchemaDef.LDAP_EMP_SAL_GROUP+", org."+SchemaDef.LDAP_EMP_REP_TO+" =temp."+SchemaDef.LDAP_EMP_REP_TO+",org."+SchemaDef.LDAP_LOCN+" =temp."+SchemaDef.LDAP_LOCN+",org."+SchemaDef.LDAP_EMP_DESG_ERP+" =temp."+SchemaDef.LDAP_EMP_DESG_ERP+",org."+SchemaDef.LDAP_SBUCD+" =temp."+SchemaDef.LDAP_SBUCD+", org."+SchemaDef.LDAP_EMP_ROCODE+" =temp."+SchemaDef.LDAP_EMP_ROCODE+",org."+SchemaDef.LDAP_EMP_ZONECD+" =temp."+SchemaDef.LDAP_EMP_ZONECD+",org."+SchemaDef.LDAP_EMP_LOCTYPE+" =temp."+SchemaDef.LDAP_EMP_LOCTYPE+",org."+SchemaDef.LDAP_EMP_BUCODE+" =temp."+SchemaDef.LDAP_EMP_BUCODE+",org."+SchemaDef.LDAP_EMP_ERP_STATUS+" =temp."+SchemaDef.LDAP_EMP_ERP_STATUS+",org."+SchemaDef.LDAP_YAHMCO+" =temp."+SchemaDef.LDAP_YAHMCO+",org."+SchemaDef.LDAP_MAILING_NAME+" =temp."+SchemaDef.LDAP_MAILING_NAME+", org."+SchemaDef.EMPLOYENAMEE+" =temp."+SchemaDef.LDAP_EMP_NAME_ERP+", org."+SchemaDef.LACATION+" =temp."+SchemaDef.LACATION+",org."+SchemaDef.EMP_PHONENO+" =temp."+SchemaDef.EMP_PHONENO+", org."+SchemaDef.EMP_EMAIL+" =temp."+SchemaDef.LDAP_EMAIL_ID+", org."+SchemaDef.PASSWORD+"='EMS@123', org."+SchemaDef.STATUS+" =temp."+SchemaDef.STATUS+", org."+SchemaDef.EDITEDBY+"='admin' where org."+SchemaDef.EMPLOYEEID+" =temp.EMP_NO  and org."+SchemaDef.STATUS+"='A'" ;
                 logger.info("updateDetails Query :"+sql);
	  		     jdbcTemplate.update(sql);
	  		 }
	  		 catch(Exception ex)
	  		 {
	  			 ex.printStackTrace();
	  			 logger.info(ex);
	  		 }
	  		 logger.info("Employee updateDetails method completed");
		}
		else if(tableName1.equals(SchemaDef.M_LOCATIONS))
		{
			logger.info("locations updateDetails method is started");
			role=SchemaDef.LOCATIONID;
		  	//id=appParameters.getLocationID();
		  	try
		  	{		  		
		  		//String sql="UPDATE "+tableName1+" org, "+tableName2+" temp set org."+SchemaDef.LOCATIONNAME+" = temp."+SchemaDef.LOCATIONNAME+", org."+SchemaDef.ZONES+"=temp."+SchemaDef.ZONES+", org."+SchemaDef.STATUS+" =temp."+SchemaDef.STATUS+",  org."+SchemaDef.EDITEDBY+"='"+appParameters.getCreatedBy()+"', org."+SchemaDef.EDITEDON+"=sysdate() where org."+SchemaDef.LOCATIONID+" =temp."+SchemaDef.LOCATIONID+"  and org."+SchemaDef.STATUS+"='A'" ; 
		  		String sql="UPDATE "+tableName1+" org, "+tableName2+" temp set org."+SchemaDef.LOCATIONNAME +"= temp."+SchemaDef.LDP_LOCNAME+",org."+SchemaDef.ADD1 +"= temp."+SchemaDef.LDP_LOCADDRESS1+",org."+SchemaDef.ADD2 +"= temp."+SchemaDef.LDP_LOCADDRESS2+",org."+SchemaDef.ADD3 +"= temp."+SchemaDef.LDP_LOCADDRESS3+",org."+SchemaDef.ADD4 +"= temp."+SchemaDef.LDP_LOCADDRESS4+",org."+SchemaDef.CITY +"= temp."+SchemaDef.LDP_LOCCITY+",org."+SchemaDef.STATE +"= temp."+SchemaDef.LDP_LOCSTATE+",org."+SchemaDef.STATEDESC +"= temp."+SchemaDef.LDP_LOCSTATEDESC+",org."+SchemaDef.PINCODE +"= temp."+SchemaDef.LDP_LOCPINCODE+",org."+SchemaDef.TOWNCD +"= temp."+SchemaDef.LDP_LOCTOWNCD+",org."+SchemaDef.TOWNDESC +"= temp."+SchemaDef.LDP_LOCTOWNDESC+",org."+SchemaDef.ZONECD +"= temp."+SchemaDef.LDP_LOCZONECD+",org."+SchemaDef.ZONES +"= temp."+SchemaDef.LDP_LOCZONEDESC+",org."+SchemaDef.CONTOFF +"= temp."+SchemaDef.LDP_LOCCONTOFF+",org."+SchemaDef.CONTDESC +"= temp."+SchemaDef.LDP_LOCCONTDESC+",org."+SchemaDef.SBU +"= temp."+SchemaDef.LDP_LOCSBU+",org."+SchemaDef.SBUDESC +"= temp."+SchemaDef.LDP_LOCSBUDESC+",org."+SchemaDef.STATE1 +"= temp."+SchemaDef.LDP_LOCSTATE1+",org."+SchemaDef.STATEDESC1 +"= temp."+SchemaDef.LDP_LOCSTATEDESC1+",org."+SchemaDef.LOCTYPE +"= temp."+SchemaDef.LDP_LOCTYPE+",org."+SchemaDef.STATUS+" =temp."+SchemaDef.STATUS+",  org."+SchemaDef.EDITEDBY+"='"+appParameters.getCreatedBy()+"' where org."+SchemaDef.LOCATIONID+" =temp."+SchemaDef.LDP_LOCATIONID+"  and org."+SchemaDef.STATUS+"='A'" ;		  			
		  		logger.info("locations updateDetails Query :"+sql);
		  		jdbcTemplate.update(sql);
		  	}
		  	catch(Exception ex)
		  	{
		  		ex.printStackTrace();
		  		logger.info(ex);
		  	}
		  	logger.info("locations updateDetailsmethod is completed");
		}
		logger.info("updateDetails method Completed");
	}
		
	/*
	 * (non-Javadoc)
	 * @see com.hpcl.dao.ConfigurationDao#updateDetails(com.hpcl.persistence.AppParameters, java.lang.String, java.lang.String)
	 */
	
	public void updateSingleRecord(AppParameters appParameters,String tableName1, String tableName2)
	{
		logger.info("update SingleRecord method is started");
		String role=null;
		String id=null;
		if(tableName1.equals(SchemaDef.m_employees))
		{
			logger.info("Employee update SingleRecord method Started");
			role=SchemaDef.EMPLOYEEID;
	  		id=appParameters.getEmployeeID();
	  		try
	  		{
	  			//String sql="UPDATE "+tableName1+" org, "+tableName2+" temp set org."+SchemaDef.EMPLOYENAMEE+" =temp."+SchemaDef.LDAP_EMP_NAME_ERP+", org."+SchemaDef.LACATION+" =temp."+SchemaDef.LACATION+", org."+SchemaDef.ROLES+" =temp."+SchemaDef.ROLES+", org."+SchemaDef.USERTYPES+" =temp."+SchemaDef.USERTYPES+",org."+SchemaDef.EMP_PHONENO+" =temp."+SchemaDef.EMP_PHONENO+", org."+SchemaDef.EMP_EMAIL+" =temp."+SchemaDef.EMP_EMAIL+", org."+SchemaDef.PASSWORD+"='EMS@123', org."+SchemaDef.STATUS+" =temp."+SchemaDef.STATUS+", org."+SchemaDef.EDITEDBY+"='"+appParameters.getEditedBy()+"', org."+SchemaDef.EDITEDON+"=sysdate() where org."+SchemaDef.EMPLOYEEID+" ='"+id+"' and temp."+SchemaDef.EMPLOYEEID+" ='"+id+"'";
	  			String sql="UPDATE "+tableName1+" org, "+tableName2+" temp set org."+SchemaDef.LDAP_EMP_SAL_GROUP+" =temp."+SchemaDef.LDAP_EMP_SAL_GROUP+", org."+SchemaDef.LDAP_EMP_REP_TO+" =temp."+SchemaDef.LDAP_EMP_REP_TO+",org."+SchemaDef.LDAP_LOCN+" =temp."+SchemaDef.LDAP_LOCN+",org."+SchemaDef.LDAP_EMP_DESG_ERP+" =temp."+SchemaDef.LDAP_EMP_DESG_ERP+",org."+SchemaDef.LDAP_SBUCD+" =temp."+SchemaDef.LDAP_SBUCD+", org."+SchemaDef.LDAP_EMP_ROCODE+" =temp."+SchemaDef.LDAP_EMP_ROCODE+",org."+SchemaDef.LDAP_EMP_ZONECD+" =temp."+SchemaDef.LDAP_EMP_ZONECD+",org."+SchemaDef.LDAP_EMP_LOCTYPE+" =temp."+SchemaDef.LDAP_EMP_LOCTYPE+",org."+SchemaDef.LDAP_EMP_BUCODE+" =temp."+SchemaDef.LDAP_EMP_BUCODE+",org."+SchemaDef.LDAP_EMP_ERP_STATUS+" =temp."+SchemaDef.LDAP_EMP_ERP_STATUS+",org."+SchemaDef.LDAP_YAHMCO+" =temp."+SchemaDef.LDAP_YAHMCO+",org."+SchemaDef.LDAP_MAILING_NAME+" =temp."+SchemaDef.LDAP_MAILING_NAME+", org."+SchemaDef.EMPLOYENAMEE+" =temp."+SchemaDef.LDAP_EMP_NAME_ERP+", org."+SchemaDef.LACATION+" =temp."+SchemaDef.LACATION+",org."+SchemaDef.EMP_PHONENO+" =temp."+SchemaDef.EMP_PHONENO+", org."+SchemaDef.EMP_EMAIL+" =temp."+SchemaDef.LDAP_EMAIL_ID+", org."+SchemaDef.PASSWORD+"='EMS@123', org."+SchemaDef.STATUS+" =temp."+SchemaDef.STATUS+", org."+SchemaDef.EDITEDBY+"='admin'  where org.Status='A' and org."+SchemaDef.EMPLOYEEID+" ='"+id+"' and  temp."+SchemaDef.LDAP_EMP_NO+" ='"+id+"' and temp.EMP_ERP_STATUS='A'";
	  			logger.info("Employee update SingleRecord Query :"+sql);
	  		    jdbcTemplate.update(sql);
	  		}
	  		catch(Exception ex)
	  		{
	  			ex.printStackTrace();
	  			logger.info("Update Employee Single Record exception :" +ex);
	  		}
	  		logger.info("Employee update SingleRecord method compleated");
	  	}
		else if(tableName1.equals(SchemaDef.M_LOCATIONS))
	  	{
			logger.info("Locations update SingleRecord method Started");
		  	role=SchemaDef.LOCATIONID;
		  	id=appParameters.getLocationID();
		  	try
		  	{
		  		logger.info("Locations update SingleRecord method is started");
		  		//String sql="UPDATE "+tableName1+" org, "+tableName2+" temp set org."+SchemaDef.LOCATIONNAME+" = temp."+SchemaDef.LOCATIONNAME+", org."+SchemaDef.ZONES+"=temp."+SchemaDef.ZONES+", org."+SchemaDef.STATUS+" =temp."+SchemaDef.STATUS+",  org."+SchemaDef.EDITEDBY+"='"+appParameters.getEditedBy()+"', org."+SchemaDef.EDITEDON+"=sysdate() where org."+SchemaDef.LOCATIONID+" ='"+id+"' and temp."+SchemaDef.LOCATIONID+" ='"+id+"'";
		  		String sql="UPDATE "+tableName1+" org, "+tableName2+" temp set org."+SchemaDef.LOCATIONNAME +"= temp."+SchemaDef.LDP_LOCNAME+",org."+SchemaDef.ADD1 +"= temp."+SchemaDef.LDP_LOCADDRESS1+",org."+SchemaDef.ADD2 +"= temp."+SchemaDef.LDP_LOCADDRESS2+",org."+SchemaDef.ADD3 +"= temp."+SchemaDef.LDP_LOCADDRESS3+",org."+SchemaDef.ADD4 +"= temp."+SchemaDef.LDP_LOCADDRESS4+",org."+SchemaDef.CITY +"= temp."+SchemaDef.LDP_LOCCITY+",org."+SchemaDef.STATE +"= temp."+SchemaDef.LDP_LOCSTATE+",org."+SchemaDef.STATEDESC +"= temp."+SchemaDef.LDP_LOCSTATEDESC+",org."+SchemaDef.PINCODE +"= temp."+SchemaDef.LDP_LOCPINCODE+",org."+SchemaDef.TOWNCD +"= temp."+SchemaDef.LDP_LOCTOWNCD+",org."+SchemaDef.TOWNDESC +"= temp."+SchemaDef.LDP_LOCTOWNDESC+",org."+SchemaDef.ZONECD +"= temp."+SchemaDef.LDP_LOCZONECD+",org."+SchemaDef.ZONES +"= temp."+SchemaDef.LDP_LOCZONEDESC+",org."+SchemaDef.CONTOFF +"= temp."+SchemaDef.LDP_LOCCONTOFF+",org."+SchemaDef.CONTDESC +"= temp."+SchemaDef.LDP_LOCCONTDESC+",org."+SchemaDef.SBU +"= temp."+SchemaDef.LDP_LOCSBU+",org."+SchemaDef.SBUDESC +"= temp."+SchemaDef.LDP_LOCSBUDESC+",org."+SchemaDef.STATE1 +"= temp."+SchemaDef.LDP_LOCSTATE1+",org."+SchemaDef.STATEDESC1 +"= temp."+SchemaDef.LDP_LOCSTATEDESC1+",org."+SchemaDef.LOCTYPE +"= temp."+SchemaDef.LDP_LOCTYPE+",org."+SchemaDef.STATUS+" =temp."+SchemaDef.STATUS+", org."+SchemaDef.EDITEDBY+"='"+appParameters.getCreatedBy()+"' where org."+SchemaDef.LOCATIONID+" ='"+id+"' and temp."+SchemaDef.LDP_LOCATIONID+" ='"+id+"' and org."+SchemaDef.STATUS+"='A'";		  		   		
	            jdbcTemplate.update(sql);
	            logger.info("Locations update SingleRecord Query :"+sql);
		  	}
		  	catch(Exception ex)
		  	{
		  		ex.printStackTrace();
		  		logger.info("Update Location Single Record exception :" +ex);
		  	}
		  	logger.info("Locations update SingleRecord method is compleated");
	  	}
		logger.info("Update SingleRecord method Completed");
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.hpcl.dao.ThreeFieldDao#deleteDetails(int, java.lang.String, java.lang.String, java.lang.String)
	 */
	
	public void deleteDetails(String rowId, String tableName1, String tableName2,String id,AppParameters appParameters)
	{
		logger.info("Delete Details method started");
	    try
	    {
	    	String sql = "update "+tableName1+" set "+SchemaDef.STATUS+" ='"+SchemaDef.INACTIVE_FLAG+"',"+SchemaDef.EDITEDBY+"='"+appParameters.getEditedBy()+"' WHERE "+id+"=?";
		    logger.info("Delete Details Query : "+sql+" rowId"+rowId); 
		    jdbcTemplate.update(sql, rowId);
	    }
	    catch(Exception ex)
	    {
	    	ex.printStackTrace();
			logger.info("Delete details exception query :" +ex);
		}
	    logger.info("Delete Details method Completed");
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.hpcl.dao.ThreeFieldDao#getAllDetails(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	
	public List<AppParameters> getAllDetails(final String tableName1,String tableName2, String id, String status,String location,String userId)
	{
		logger.info("getAllDetails Details method is started");
		String sql=null;
		if(userId.equals("admin"))
		{
			logger.info("getAll Details method for admin user Started");			
			if(status!=null)
			{
				logger.info("get All Details method for admin user with selected status :" +status);
				if(status.equals("A"))
				{
					sql = "SELECT emp.employeeID,emp.employeeName,emp.location,rol.Description,emp.Status FROM m_employees emp,m_role rol where emp.status='A' and emp.roles = rol.RoleID ORDER BY emp.createdOn DESC"; 
					logger.info("get all details for admin user with Active Status :" +sql);
				 }
				else if(status.equals("I"))
				{
					sql = "SELECT emp.employeeID,emp.employeeName,emp.location,rol.Description,emp.Status FROM m_employees emp,m_role rol where emp.status='I' and emp.roles = rol.RoleID ORDER BY emp.createdOn DESC";
					logger.info("get all details for admin user with Inactive Status :" +sql);
				}
				else if(status.equals("all"))
				{
					sql="SELECT emp.employeeID,emp.employeeName,emp.location,rol.Description,emp.Status FROM m_employees emp,m_role rol where  emp.roles = rol.RoleID ORDER BY emp.createdOn DESC";
					logger.info("get all details for admin user irrespective of Status :" +sql);
				}
			}
			else
			{
				sql = "SELECT emp.employeeID,emp.employeeName,emp.location,rol.Description,emp.Status FROM m_employees emp,m_role rol where emp.status='A' and emp.roles = rol.RoleID ORDER BY emp.createdOn DESC";
				logger.info("get default details for admin user :" +sql);
			}
			logger.info("getAll Details method for admin user Completed");
		}
		else
		{
			logger.info("getAll Details method for loggedin user Started");
			if(status!=null)
			{
				if(status.equals("A"))
				{
					sql = "SELECT emp.employeeID,emp.employeeName,emp.location,rol.Description,emp.Status FROM m_employees emp,m_role rol where emp.status='A' and emp.roles = rol.RoleID and "+SchemaDef.LOCATION+"='"+location+"' ORDER BY emp.createdOn DESC";
					logger.info("get all details for loggedin user with Active Status :" +sql);
			 	}
				else if(status.equals("I"))
				{
					sql = "SELECT emp.employeeID,emp.employeeName,emp.location,rol.Description,emp.Status FROM m_employees emp,m_role rol where emp.status='I' and emp.roles = rol.RoleID and "+SchemaDef.LOCATION+"='"+location+"' ORDER BY emp.createdOn DESC";
					logger.info("get all details for loggedin user with Inactive Status :" +sql);
				 }
				else if(status.equals("all"))
				{
					sql="SELECT emp.employeeID,emp.employeeName,emp.location,rol.Description,emp.Status FROM m_employees emp,m_role rol where  emp.roles = rol.RoleID and "+SchemaDef.LOCATION+"='"+location+"' ORDER BY emp.createdOn DESC";
					logger.info("get all details for loggedin user irrespective of Status :" +sql);
				}
			}
			else
			{		
				sql = "SELECT emp.employeeID,emp.employeeName,emp.location,rol.Description,emp.Status FROM m_employees emp,m_role rol where emp.status='A' and emp.roles = rol.RoleID and "+SchemaDef.LOCATION+"='"+location+"' ORDER BY emp.createdOn DESC";
			 	logger.info("get default details for loggedin user :" +sql);
			}
			logger.info("getAll Details method for loggedin user Completed");
		}
		//logger.info("Query "+sql);
		List<AppParameters> appParametersList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() 
		{
			public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				AppParameters appParameters = new AppParameters();
			    if(tableName1.equals(SchemaDef.m_employees))
			    {
			    	appParameters.setEmployeeID(rs.getString(SchemaDef.EMPLOYEEID));
			    }
			    appParameters.setEmployeeName(rs.getString("employeeName"));
			    appParameters.setLocation(rs.getString("location"));
			    //appParameters.setRoles(rs.getString("roles"));
			    appParameters.setDescription(rs.getString("description"));
			    appParameters.setStatus(rs.getString("status"));
			    //appParameters.setRoleID(rs.getp("RoleID"));
		        return appParameters;
			}
		});
		logger.info("getAllDetails method is completed");
		return appParametersList;
	}


/******************************************************************************/
	/*
	 * getAllLocationDetails
	 * (non-Javadoc)
	 * @see com.hpcl.dao.ConfigurationDao#getAllLocationDetails(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<AppParameters> getAllLocationDetails(final String tableName1,String tableName2, String id, String status,String location,String userId)
	{
		logger.info("getAllLocationDetails Details method is started");
		String sql=null;
		//String id=SchemaDef.LOCATIONID;
		if(userId.equals("admin"))
		{
			logger.info("getAllLocationDetails method for admin user Started");	
			logger.info("getAllLocationDetails method Status"+status);
			if(status!=null)
			{
				logger.info("get All Location Details method for admin user with selected status :" +status);
				if(status.equals("A"))
				{
					sql = "SELECT "+id+","+SchemaDef.LOCATIONNAME+","+SchemaDef.ZONES+","+SchemaDef.STATUS+" FROM "+tableName1+"  where status='"+SchemaDef.ACTIVE_FLAG+"' ORDER BY createdOn DESC"; 
					logger.info("get all Location details for admin user with Active Status :" +sql);
				}
				else if(status.equals("I"))
				{
					sql = "SELECT "+id+","+SchemaDef.LOCATIONNAME+","+SchemaDef.ZONES+","+SchemaDef.STATUS+" FROM "+tableName1+"  where status='"+SchemaDef.INACTIVE_FLAG+"' ORDER BY createdOn DESC";
					logger.info("get all Location details for admin user with Inactive Status :" +sql);
				}
				else if(status.equals("all"))
				{
					sql = "SELECT "+id+","+SchemaDef.LOCATIONNAME+","+SchemaDef.ZONES+","+SchemaDef.STATUS+" FROM "+tableName1+" ORDER BY createdOn DESC";
					logger.info("get all Location details for admin user irrespective of Status :" +sql);
				}
			}
			else
			{
				sql = "SELECT "+id+","+SchemaDef.LOCATIONNAME+","+SchemaDef.ZONES+","+SchemaDef.STATUS+" FROM "+tableName1+" where status='"+SchemaDef.ACTIVE_FLAG+"' ORDER BY createdOn DESC";
				logger.info("default Location details query for admin user :" +sql);
			}
			logger.info("getAllLocationDetails method for admin user Completed");
		}
		else
		{
			logger.info("getAllLocationDetails method for loggedin user Started");	
			logger.info("getAllLocationDetails method Status"+status);			
			if(status!=null)
			{
				if(status.equals("A"))
				{
					sql = "SELECT "+id+","+SchemaDef.LOCATIONNAME+","+SchemaDef.ZONES+","+SchemaDef.STATUS+" FROM "+tableName1+"  where status='"+SchemaDef.ACTIVE_FLAG+"' and "+SchemaDef.LOCATIONNAME+"='"+location+"' ORDER BY createdOn DESC";
					logger.info("getAllLocationDetails for loggedin user with Active Status :" +sql);
				}
				else if(status.equals("I"))
				{
					sql = "SELECT "+id+","+SchemaDef.LOCATIONNAME+","+SchemaDef.ZONES+","+SchemaDef.STATUS+" FROM "+tableName1+"  where status='"+SchemaDef.INACTIVE_FLAG+"' and "+SchemaDef.LOCATIONNAME+"='"+location+"' ORDER BY createdOn DESC";
					logger.info("getAllLocationDetails for loggedin user with Inactive Status :" +sql);
				}
				else if(status.equals("all"))
				{
					sql = "SELECT "+id+","+SchemaDef.LOCATIONNAME+","+SchemaDef.ZONES+","+SchemaDef.STATUS+" FROM "+tableName1+" and "+SchemaDef.LOCATIONNAME+"='"+location+"' ORDER BY createdOn DESC";
					logger.info("getAllLocationDetails for loggedin user irrespective of Status :" +sql);
				}
			}
			else
			{
				sql = "SELECT "+id+","+SchemaDef.LOCATIONNAME+","+SchemaDef.ZONES+","+SchemaDef.STATUS+" FROM "+tableName1+" where status='"+SchemaDef.ACTIVE_FLAG+"' and "+SchemaDef.LOCATIONNAME+"='"+location+"' ORDER BY createdOn DESC";
				logger.info("getAllLocationDetails details query for loggedin user :" +sql);
			}
			logger.info("getAllLocationDetails method for loggedin user Completed");
		}
		List<AppParameters> appParametersList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() 
		{
			public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				AppParameters appParameters = new AppParameters();
			    if(tableName1.equals(SchemaDef.M_LOCATIONS))
			    {
			    	appParameters.setLocationID(rs.getString(SchemaDef.LOCATIONID));
			    	appParameters.setLocationName(rs.getString("locationName"));
				   	appParameters.setZones(rs.getString("zones"));
				   	appParameters.setStatus(rs.getString("status"));
			    }
			    return appParameters;
			}
		});
		logger.info("getAllLocationDetails method is compleated");
		return appParametersList;
	}
/******************************************************************************/

	
	public void saveRolePermissions(AppParameters appParameters,String tableName1, String tableName2, String tableName3)
	{
		logger.info("saveRolePermissions method Started");
	    String tableName=null;
	    //String currentDate=new CurrentDateTime().getCurrentDate();
		//String id=null;
	    String query="insert into "+tableName+" ("+SchemaDef.LOCATIONID+","+SchemaDef.EARTHPIT_NAME+","+SchemaDef.STATUS+","+SchemaDef.CREATEDBY+") values('"+appParameters.getLocation()+"','"+appParameters.getIpAddress()+"','"+appParameters.getStatus()+"','"+appParameters.getCreatedBy()+"')";  
	  	logger.info("Insert RolePermissions Query :"+query);
	  	jdbcTemplate.update(query);
	  	logger.info("saveRolePermissions method Completed");
	}

	
	public AppParameters getRolePermissions(String id, String tableName1,String tableName2, String tableName3)
	{
		// TODO Auto-generated method stub
		return null;
	}

	
	public void updateRolePermissions(AppParameters appParameters,String tableName1, String tableName2, String tableName3)
	{
		// TODO Auto-generated method stub		
	}

	
	public void deleteRolePermissions(String id, String tableName1,String tableName2, String tableName3)
	{
		// TODO Auto-generated method stub
		
	}

	/******************************************************************************/

	/*
	 * In configuration module->earthpits page
	 * @see com.hpcl.dao.ConfigurationDao#saveEarthptsDetails(com.hpcl.persistence.AppParameters, java.lang.String)
	 */
	
	
	public void saveEarthptsDetails(AppParameters appParameters,String tableName)
	{
		logger.info("saveEarthptsDetails method is started");
		try
		{
			String query="insert into "+tableName+" ("+SchemaDef.LOCATIONID+","+SchemaDef.EARTHPIT_NAME+","+SchemaDef.STATUS+","+SchemaDef.CREATEDBY+") values('"+appParameters.getLocationID()+"','"+appParameters.getEarthpitName()+"','"+appParameters.getStatus()+"','"+appParameters.getCreatedBy()+"')";  
			logger.info("Insert Earthpit details Query : "+query);
			jdbcTemplate.update(query); 
		}
		catch(Exception ex)
		{
	        ex.printStackTrace();
	        logger.info("Insert Earthpit Exception message :" +ex);
	    }
		logger.info("saveEarthptsDetails method is compleated");
	}

	
	public void updateEarthpitsDetails(AppParameters appParameters,String tableName)
	{
		logger.info("Update EarthpitsDetails method Started");
		String earthpitid=SchemaDef.EARTHPIT_ID;
		String locationid=SchemaDef.LOCATIONID;
  		String earid =appParameters.getEarthpitID();
  		String locname=appParameters.getLocationID();
  		try
  		{
  			String sql = "update "+tableName+" set "+SchemaDef.LOCATIONID+"=?,"+SchemaDef.EARTHPIT_NAME+"=?,"+SchemaDef.EDITEDBY+"='"+appParameters.getEditedBy()+"' where "+earthpitid+"=?";
  			logger.info("earthpitID:"+earid); 
  			logger.info("locationID:"+locname);
  		    jdbcTemplate.update(sql,appParameters.getLocationID(),appParameters.getEarthpitName(),earid);
  		    logger.info("updateEarthpitsDetails query: "+sql);
  		}
  		catch(Exception ex)
  		{
  			ex.printStackTrace();
  			logger.info("update Earthpit details exception :" +ex);
  		}
  		logger.info("Update EarthpitsDetails method Completed");
	}

	
	public void deleteEarthpitsDetails(AppParameters twoFieldPersistence,String tableName)
	{
		logger.info("Delete EarthpitsDetails method Started");
		final String earthpit_ID=SchemaDef.EARTHPIT_ID;		
        try
        {
       	   String sql = "update "+tableName+" set "+SchemaDef.STATUS+" ='"+SchemaDef.INACTIVE_FLAG+"',"+SchemaDef.EDITEDBY+"='"+twoFieldPersistence.getEditedBy()+"' WHERE "+earthpit_ID+"='"+twoFieldPersistence.getLocationID()+"' and locationId ='"+twoFieldPersistence.getEarthpitID()+"'";
	       //logger.info("EarthpitID in Earthpits page : "+twoFieldPersistence.getLocationID());
	       //logger.info("LocationId in Earthpits page : "+twoFieldPersistence.getEarthpitID());
	       jdbcTemplate.update(sql);
	       logger.info("Delete EarthpitsDetails method query"+sql);	        
	       String sql1="Update m_device_earthpits set status ='I', EditedBy='admin' where earthpitId ='"+twoFieldPersistence.getLocationID()+"' and locationId ='"+twoFieldPersistence.getEarthpitID()+"' and status ='A'";
	       logger.info("m_device_earthpit earthpitId"+twoFieldPersistence.getEarthpitID());
	       logger.info("m_device_earthpit locationid"+twoFieldPersistence.getLocationID());
	       jdbcTemplate.update(sql1);	       
	       logger.info("Update device Earthpit table with status : "+sql1);
        }
        catch(Exception ex)
        {
        	ex.printStackTrace();
        	logger.info("Delete earthpit details exception query :"+ex);
        }
        logger.info("Delete EarthpitsDetails method completed");
	}

	
	public List<AppParameters> getAllEarthpitsDetails(String tableName,String id, String status,String location,String userId)
	{
		logger.info("getAllEarthpitsDetails method Started");
		String sql=null;		
		logger.info("getAllEarthpitDetails method User :" +userId);
		if(userId.equals("admin"))
		{
			logger.info("getAllEarthpitsDetails for admin user Started");
			logger.info("Status value sent from applicaiton :" +status);
			if(status!=null)
			{
				if(status.equals("A"))
				{
					sql="SELECT e.earthpitid,e.earthpitname,e.locationid,m.locationname,e.status FROM m_earthpits e,m_locations m where e.LocationId=m.LocationId and e.status='A' ORDER BY e.createdOn DESC"; 
					logger.info("getAllEarthpitDetails for admin user with Active Status :" +sql);
			 	}
				else if(status.equals("I"))
				{
					sql="SELECT e.earthpitid,e.earthpitname,e.locationid,m.locationname,e.status FROM m_earthpits e,m_locations m where e.LocationId=m.LocationId and e.status='I' ORDER BY e.createdOn DESC"; 
					logger.info("getAllEarthpitDetails for admin user with Inactive Status :" +sql);	
				}
				else if(status.equals("All"))
				{
					sql="SELECT e.earthpitid,e.earthpitname,e.locationid,m.locationname,e.status FROM m_earthpits e,m_locations m where e.LocationId=m.LocationId ORDER BY e.createdOn DESC"; 
					logger.info("getAllEarthpitDetails for admin user irrespective Status :" +sql);	
				}
			}
			else
			{
				sql="SELECT e.earthpitid,e.earthpitname,e.locationid,m.locationname,e.status FROM m_earthpits e,m_locations m where e.LocationId=m.LocationId and e.status='A' ORDER BY e.createdOn DESC";
				logger.info("getAllEarthpitDetails with default query for admin user :" +sql); 
			}
			logger.info("getAllEarthpitsDetails for admin user Closed");
		}
		else
		{
			logger.info("getAllEarthpitsDetails for Loggedin user Started");
			logger.info("Status value sent from applicaiton :" +status);
			if(status!=null)
			{
				if(status.equals("A"))
				{
					sql="SELECT e.earthpitid,e.earthpitname,e.locationid,m.locationname,e.status FROM m_earthpits e,m_locations m where e.LocationId=m.LocationId and e.status='A' and m.locationname='"+location+"' ORDER BY e.createdOn DESC"; 
					logger.info("getAllEarthpitsDetails for Loggedin user with Active status :" +sql);
		 		}
				else if(status.equals("I"))
				{
					sql="SELECT e.earthpitid,e.earthpitname,e.locationid,m.locationname,e.status FROM m_earthpits e,m_locations m where e.LocationId=m.LocationId and e.status='Iand m.locationname='"+location+"' ORDER BY e.createdOn DESC"; 
					logger.info("getAllEarthpitsDetails for Loggedin user with Inactive status :" +sql);
			 	}
				else if(status.equals("All"))
				{
					sql="SELECT e.earthpitid,e.earthpitname,e.locationid,m.locationname,e.status FROM m_earthpits e,m_locations m where e.LocationId=m.LocationIdand m.locationname='"+location+"' ORDER BY e.createdOn DESC"; 
					logger.info("getAllEarthpitsDetails for Loggedin user irrespective of status :" +sql);
				}
		 	}
			else
			{
				sql="SELECT e.earthpitid,e.earthpitname,e.locationid,m.locationname,e.status FROM m_earthpits e,m_locations m where e.LocationId=m.LocationId and e.status='A' and m.locationname='"+location+"' ORDER BY e.createdOn DESC"; 
				logger.info("getAllEarthpitsDetails with default query for Loggedin user :" +sql);	
			}
			logger.info("getAllEarthpitsDetails for Loggedin user Closed");
		}
		List<AppParameters> twoFieldPersistenceList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() 
		{
			public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				AppParameters twoFieldPersistence = new AppParameters();
			    twoFieldPersistence.setEarthpitID(rs.getString(SchemaDef.EARTHPIT_ID));
			    twoFieldPersistence.setEarthpitName(rs.getString(SchemaDef.EARTHPIT_NAME));
			    twoFieldPersistence.setLocationID(rs.getString(SchemaDef.LOCATIONID));
			    twoFieldPersistence.setLocationName(rs.getString(SchemaDef.LOCATIONNAME));
			    twoFieldPersistence.setStatus(rs.getString(SchemaDef.STATUS));
		        return twoFieldPersistence;
			}
		});
		logger.info("getAllEarthpitsDetails method completed");
		return twoFieldPersistenceList;
	}
	
/******************************************************************************/

	
	public void saveDeviceDetails(AppParameters appParameters, String tableName)
	{
		logger.info("saveDeviceDetails method Started");
		try
		{
			String query="insert into "+tableName+" ("+SchemaDef.DEVICE_ID+","+SchemaDef.DEVICE_NAME+","+SchemaDef.IPADDRESS+","+SchemaDef.LOCATIONID+","+SchemaDef.EARTHPIT_VALUES+","+SchemaDef.EARTHPIT_NAMES+","+SchemaDef.TIME_INTERVAL+","+SchemaDef.LOCATION_HEAD_MOBILE+","+SchemaDef.EMAIL_ID+","+SchemaDef.INSTALL_DATE+","+SchemaDef.DEVICE_SNO+","+SchemaDef.DATE_AND_TIME+","+SchemaDef.STATUS+","+SchemaDef.CREATEDBY+") values('"+appParameters.getDeviceId()+"','"+appParameters.getDeviceName()+"','"+appParameters.getIpAddress()+"','"+appParameters.getLocationID()+"','"+appParameters.getEarthPitCount()+"','"+appParameters.getEarthPitNames()+"','"+appParameters.getTimeInterval()+"','"+appParameters.getLocationHeadsMobile()+"','"+appParameters.getEmailIDs()+"','"+appParameters.getInstalledDate()+"','"+appParameters.getDeviceSno()+"','"+appParameters.getInstalledDate()+"','A','"+appParameters.getCreatedBy()+"')";  
			logger.info("Insert Device Details Query :"+query);
			jdbcTemplate.update(query); 
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
	        logger.info("Save Device details exception query :"+ex);
		}
		   logger.info("saveDeviceDetails method is completed");
	}

	
	public void updateDeviceDetails(AppParameters appParameters,String tableName)
	{
		logger.info("updateDeviceDetails method Started");
		String ipcode=SchemaDef.DEVICE_ID;
  		final String deviceid=appParameters.getDeviceId();
  		try
  		{
  			String sql = "update "+tableName+" set "+SchemaDef.DEVICE_NAME+"='"+appParameters.getDeviceName()+"',"+SchemaDef.IPADDRESS+"='"+appParameters.getIpAddress()+"',"+SchemaDef.LOCATIONID+"='"+appParameters.getLocationID()+"',"+SchemaDef.EARTHPIT_VALUES+"='"+appParameters.getEarthPitCount()+"',"+SchemaDef.TIME_INTERVAL+"='"+appParameters.getTimeInterval()+"',"+SchemaDef.DEVICE_SNO+"='"+appParameters.getDeviceSno()+"',"+SchemaDef.EDITEDBY+"='"+appParameters.getEditedBy()+"' where "+ipcode+"=?";  
  		    jdbcTemplate.update(sql, deviceid);
  		    logger.info("update Device Details query: "+sql);
  		}
  		catch(Exception ex)
  		{
  			ex.printStackTrace();
  			logger.info("Update Device details exception query :" +ex);
  		}
  	    logger.info("updateDeviceDetails method Completed");
	}

	
	public List<AppParameters> getAllDeviceDetails(String tableName, String id,String status,String location,String userId)
	{
		logger.info("getAllDeviceDetails method is started");
		String sql=null;
		logger.info("getAllDeviceDetails method User :" +userId);
		if(userId.equals("admin"))
		{
			logger.info("getAllDeviceDetails for admin user Started");
			logger.info("Status value received for getAllDeviceDetails method :" +status);
			if(status!=null)
			{
				if(status.equals("A"))
				{
					sql="SELECT d.deviceId,d.deviceName,d.IPAddress,m.LocationId,m.Locationname,d.earthpitvalues,d.timeinterval,d.installedDate,d.deviceSno,d.status FROM m_device d, m_locations m  where m.LocationId=d.locationId and d.status='A' ORDER BY d.createdOn DESC"; 
					logger.info("getAllDeviceDetails for admin user with Active Status :" +sql);
				}
				else if(status.equals("I"))
				{
					sql="SELECT d.deviceId,d.deviceName,d.IPAddress,m.LocationId,m.Locationname,d.earthpitvalues,d.timeinterval,d.installedDate,d.deviceSno,d.status FROM m_device d, m_locations m  where m.LocationId=d.locationId and d.status='I' ORDER BY d.createdOn DESC"; 
					logger.info("getAllDeviceDetails for admin user with Inactive Status :" +sql);	
				}
				else if(status.equals("All"))
				{
					sql="SELECT d.deviceId,d.deviceName,d.IPAddress,m.LocationId,m.Locationname,d.earthpitvalues,d.timeinterval,d.installedDate,d.deviceSno,d.status FROM m_device d, m_locations m  where m.LocationId=d.locationId ORDER BY d.createdOn DESC"; 
					logger.info("getAllDeviceDetails for admin user Irrespective of Status :" +sql);	
				}
			}
			else
			{
				sql="SELECT d.deviceId,d.deviceName,d.IPAddress,m.LocationId,m.Locationname,d.earthpitvalues,d.timeinterval,d.installedDate,d.deviceSno,d.status FROM m_device d, m_locations m  where m.LocationId=d.locationId and d.status='A' ORDER BY d.createdOn DESC";
				logger.info("getAllDeviceDetails with default query for admin user :" +sql);
			}
			logger.info("getAllDeviceDetails for admin user Completed");
		}
		else
		{
			logger.info("getAllDeviceDetails for Loggedin user Started");
			logger.info("Status value received for getAllDeviceDetails method :" +status);
			if(status!=null)
			{
				if(status.equals("A"))
				{
		 		   sql="SELECT d.deviceId,d.deviceName,d.IPAddress,m.LocationId,m.Locationname,d.earthpitvalues,d.timeinterval,d.installedDate,d.deviceSno,d.status FROM m_device d, m_locations m  where m.LocationId=d.locationId and d.status='A' and  m.Locationname='"+location+"' ORDER BY d.createdOn DESC"; 
		 		  logger.info("getAllDeviceDetails for Loggedin user with Active Status :" +sql);
		 		}
				else if(status.equals("I"))
				{
		 			sql="SELECT d.deviceId,d.deviceName,d.IPAddress,m.LocationId,m.Locationname,d.earthpitvalues,d.timeinterval,d.installedDate,d.deviceSno,d.status FROM m_device d, m_locations m  where m.LocationId=d.locationId and d.status='I' and  m.Locationname='"+location+"' ORDER BY d.createdOn DESC"; 
		 			logger.info("getAllDeviceDetails for Loggedin user with Inactive Status :" +sql);
			 	}
				else if(status.equals("All"))
				{
					sql="SELECT d.deviceId,d.deviceName,d.IPAddress,m.LocationId,m.Locationname,d.earthpitvalues,d.timeinterval,d.installedDate,d.deviceSno,d.status FROM m_device d, m_locations m  where m.LocationId=d.locationId and  m.Locationname='"+location+"' ORDER BY d.createdOn DESC";
					logger.info("getAllDeviceDetails for Loggedin user irrespective Status :" +sql);
			 	}
			}
			else
			{
				sql="SELECT d.deviceId,d.deviceName,d.IPAddress,m.LocationId,m.Locationname,d.earthpitvalues,d.timeinterval,d.installedDate,d.deviceSno,d.status FROM m_device d, m_locations m  where m.LocationId=d.locationId and d.status='A' and  m.Locationname='"+location+"' ORDER BY d.createdOn DESC";
				logger.info("getAllDeviceDetails with  default query for Loggedin user:" +sql);
			}
			logger.info("getAllDeviceDetails for Loggedin user completed");
		 }
		List<AppParameters> twoFieldPersistenceList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() 
		{
			public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				AppParameters twoFieldPersistence = new AppParameters();
			    twoFieldPersistence.setDeviceId(rs.getString(SchemaDef.DEVICE_ID));
			    twoFieldPersistence.setDeviceName(rs.getString(SchemaDef.DEVICE_NAME));
			    twoFieldPersistence.setIpAddress(rs.getString(SchemaDef.IPADDRESS));
			    twoFieldPersistence.setLocationID(rs.getString(SchemaDef.LOCATIONID));
			    twoFieldPersistence.setLocationName(rs.getString(SchemaDef.LOCATIONNAME));
			    twoFieldPersistence.setEarthPitValues(rs.getString(SchemaDef.EARTHPIT_VALUES));
			    twoFieldPersistence.setTimeInterval(rs.getString(SchemaDef.TIME_INTERVAL));
			    twoFieldPersistence.setInstalledDate(rs.getString(SchemaDef.INSTALL_DATE));
			    twoFieldPersistence.setDeviceSno(rs.getString(SchemaDef.DEVICE_SNO));
			    twoFieldPersistence.setStatus(rs.getString(SchemaDef.STATUS));
		        return twoFieldPersistence;
			}
		});
		logger.info("getAllDetails method completed");
		return twoFieldPersistenceList;
	}
	

	
	public void deleteDeviceDetails(AppParameters appParameters,String tableName1)
	{
		final String device_ID=SchemaDef.DEVICE_ID;
		final String earthpit_ID=SchemaDef.EARTHPIT_ID;
		final String tableName2=SchemaDef.M_DEVICE_EARTHPITS;
		final String tableName3=SchemaDef.M_EARTHPITS;
		logger.info("Delete DeviceDetails method is started");
		try
		{			
			String sql="update m_device set Status ='I' where DeviceId =? and locationId =? and status ='A';";
		    logger.info("m_device LocationID :"+appParameters.getLocationID());
		    logger.info("m_device :"+appParameters.getDeviceId());
		    jdbcTemplate.update(sql, appParameters.getDeviceId(),appParameters.getLocationID());
		    logger.info("Update Status in Device table executed : "+sql);
		        
		    String sql1="update m_device_earthpits set status ='I' where DeviceId =? and locationId =? and status ='A';";
		    logger.info("m_device_earthpits LocationID :"+appParameters.getLocationID());
		    logger.info("m_device_earthpits :"+appParameters.getDeviceId());
		    jdbcTemplate.update(sql1, appParameters.getDeviceId(), appParameters.getLocationID());
		    logger.info("Update Status in device earthpit table executed : "+sql1);
		        
		    String sql2="Update m_earthpits e, m_device_earthpits de, m_device d set e.MappedStatus ='U' where e.status ='A' and e.earthpitId = de.earthpitId and de.DeviceID =? and de.locationId =?;";
		    logger.info("Earthpit LocationID :"+appParameters.getLocationID());
		    logger.info("Earthpit DeviceID :"+appParameters.getDeviceId());
		    jdbcTemplate.update(sql2,appParameters.getDeviceId(), appParameters.getLocationID());
		    logger.info("Updates earthpit table with unmapped status :"+sql2);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			logger.info("Delete Device details exception :" +ex);
		}
	    logger.info("deleteDeviceDetails method Completed");
	}
	

	/******************************************************************************/
	
	public void saveDeviceEarthpitsDetails(AppParameters appParameters,String tableName)
	{
		logger.info("saveDeviceEarthpitsDetails method Started");
		try
		{
			String query="insert into "+tableName+" ("+SchemaDef.DEVICE_EARTHPIT_ID+","+SchemaDef.LOCATION_ID+","+SchemaDef.DEVICE_ID+","+SchemaDef.EARTHPIT_ID+","+SchemaDef.STATUS+","+SchemaDef.CREATEDBY+") values('"+appParameters.getDeviceearthpitId()+"','"+appParameters.getLocationName()+"','"+appParameters.getDeviceName()+"','"+appParameters.getEarthpitName()+"','"+appParameters.getEarthPitNames()+"','"+appParameters.getStatus()+"','"+appParameters.getCreatedBy()+"')";  
			jdbcTemplate.update(query);
			logger.info("Insert DeviceEarthpits Details Query :"+query);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
	        logger.info("saveDeviceEarthpitDetails exception :" +ex);
		}
		logger.info("saveDeviceEarthpitsDetails method Completed");
	}

	public void updateDeviceEarthpitsDetails(AppParameters appParameters,String tableName)
	{
		// TODO Auto-generated method stub		
	}

	
	public List<AppParameters> getAllDeviceEarthpitsDetails(String tableName,String id, String status,String location,String userId)
	{
		logger.info("getAllDeviceEarthpitsDetails method Started");
		String sql=null;
		logger.info("getAllDeviceEarthpitsDetails access requested User :" +userId);
		if(userId.equals("admin"))
		{
			logger.info("getAllDeviceEarthpitsDetails for admin user Started");
			logger.info("Status value received for getAllDeviceDetails method :" +status);  
			if(status!=null)
			{
				if(status.equals("A"))
				{
					sql = "select m.deviceearthpitId,m.status,m.locationid,l.LocationName ,m.deviceid,d.devicename,m.earthpitid,e.earthpitname from m_device_earthpits m  join m_locations l on m.locationid=l.locationid join m_device d on m.deviceid=d.DeviceId join m_earthpits e on m.earthpitid=e.earthpitid where m.status='A' ORDER BY m.createdOn DESC";
					logger.info("getAllDeviceEarthpitsDetails for admin user with Active Status :" +sql);
				}
				else if(status.equals("I"))
				{
					sql = "select m.deviceearthpitId,m.status,m.locationid,l.LocationName ,m.deviceid,d.devicename,m.earthpitid,e.earthpitname from m_device_earthpits m  join m_locations l on m.locationid=l.locationid join m_device d on m.deviceid=d.DeviceId join m_earthpits e on m.earthpitid=e.earthpitid where m.status='I' ORDER BY m.createdOn DESC";
					logger.info("getAllDeviceEarthpitsDetails for admin user with Inactive Status :" +sql);
				}
				else if(status.equals("All"))
				{
					sql = "select m.deviceearthpitId,m.status,m.locationid,l.LocationName ,m.deviceid,d.devicename,m.earthpitid,e.earthpitname from m_device_earthpits m  join m_locations l on m.locationid=l.locationid join m_device d on m.deviceid=d.DeviceId join m_earthpits e on m.earthpitid=e.earthpitid ORDER BY m.createdOn DESC";
					logger.info("getAllDeviceEarthpitsDetails for admin user irrespective of the Status :" +sql);
				}
			}
			else
			{
				sql = "select m.deviceearthpitId,m.status, m.locationid,l.LocationName ,m.deviceid,d.devicename,m.earthpitid,e.earthpitname from m_device_earthpits m  join m_locations l on m.locationid=l.locationid join m_device d on m.deviceid=d.DeviceId join m_earthpits e on m.earthpitid=e.earthpitid where m.status='A' ORDER BY m.createdOn DESC";
				logger.info("getAllDeviceEarthpitsDetails with default query for admin user :" +sql);
			}
			logger.info("getAllDeviceEarthpitsDetails for admin user Completed");
		}
		else
		{  
			logger.info("getAllDeviceEarthpitsDetails for Loggedin user Started");
			logger.info("Status value received for getAllDeviceDetails method :" +status);  
			if(status!=null)
			{
				if(status.equals("A"))
				{
		 		     sql = "select m.deviceearthpitId,m.status,m.locationid,l.LocationName ,m.deviceid,d.devicename,m.earthpitid,e.earthpitname from m_device_earthpits m  join m_locations l on m.locationid=l.locationid join m_device d on m.deviceid=d.DeviceId join m_earthpits e on m.earthpitid=e.earthpitid where m.status='A'  and  l.LocationName ='"+location+"' ORDER BY m.createdOn DESC";
		 		    logger.info("getAllDeviceEarthpitsDetails for Loggedin user with Active Status :" +sql);
		 		}
				else if(status.equals("I"))
				{
		 			 sql = "select m.deviceearthpitId,m.status,m.locationid,l.LocationName ,m.deviceid,d.devicename,m.earthpitid,e.earthpitname from m_device_earthpits m  join m_locations l on m.locationid=l.locationid join m_device d on m.deviceid=d.DeviceId join m_earthpits e on m.earthpitid=e.earthpitid where m.status='I' and  l.LocationName ='"+location+"' ORDER BY m.createdOn DESC";
		 			logger.info("getAllDeviceEarthpitsDetails for Loggedin user with Inactive Status :" +sql);
			 	}
				else if(status.equals("All"))
				{
					sql = "select m.deviceearthpitId,m.status,m.locationid,l.LocationName ,m.deviceid,d.devicename,m.earthpitid,e.earthpitname from m_device_earthpits m  join m_locations l on m.locationid=l.locationid join m_device d on m.deviceid=d.DeviceId join m_earthpits e on m.earthpitid=e.earthpitid and  l.LocationName ='"+location+"' ORDER BY m.createdOn DESC";
					logger.info("getAllDeviceEarthpitsDetails for Loggedin user Irrespective of Status :" +sql);
				}
			}
			else
			{
				sql = "select m.deviceearthpitId,m.status, m.locationid,l.LocationName ,m.deviceid,d.devicename,m.earthpitid,e.earthpitname from m_device_earthpits m  join m_locations l on m.locationid=l.locationid join m_device d on m.deviceid=d.DeviceId join m_earthpits e on m.earthpitid=e.earthpitid where m.status='A' and  l.LocationName ='"+location+"' ORDER BY m.createdOn DESC";
				logger.info("getAllDeviceEarthpitsDetails with default query for Loggedin user :" +sql);
		 	}
			logger.info("getAllDeviceEarthpitsDetails for Loggedin user Completed");
		}	
		List<AppParameters> twoFieldPersistenceList = jdbcTemplate.query(sql, new RowMapper<AppParameters>()
		{
			public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				AppParameters twoFieldPersistence = new AppParameters();
			    twoFieldPersistence.setDeviceearthpitId(rs.getString(SchemaDef.DEVICE_EARTHPIT_ID));
			    twoFieldPersistence.setLocationID(rs.getString(SchemaDef.LOCATION_ID));
			    twoFieldPersistence.setLocationName(rs.getString(SchemaDef.LOCATIONNAME));
			    twoFieldPersistence.setDeviceId(rs.getString(SchemaDef.DEVICE_ID));
			    twoFieldPersistence.setDeviceName(rs.getString(SchemaDef.DEVICE_NAME));
			    twoFieldPersistence.setEarthpitID(rs.getString(SchemaDef.EARTHPIT_ID));
			    twoFieldPersistence.setEarthpitName(rs.getString(SchemaDef.EARTHPIT_NAME));
			    twoFieldPersistence.setStatus(rs.getString(SchemaDef.STATUS));
		        return twoFieldPersistence;
			}
		});
		logger.info("getAllDeviceEarthpitsDetails method Completed");
		return twoFieldPersistenceList;
	}

	
	public void deleteDeviceEarthpitsDetails(AppParameters appParameters,String tableName1)
	{
		final String deviceEarthpit_ID=SchemaDef.DEVICE_EARTHPIT_ID;
		final String device_ID=SchemaDef.DEVICE_ID;
		String tableName2=SchemaDef.M_DEVICE;
		logger.info("Delete DeviceEarthpits Details method Started");
        try
        {
        	String sql = "update "+tableName1+" set "+SchemaDef.STATUS+" ='"+SchemaDef.INACTIVE_FLAG+"',"+SchemaDef.EDITEDBY+"='"+appParameters.getEditedBy()+"' WHERE "+deviceEarthpit_ID+"=?";
	        jdbcTemplate.update(sql, appParameters.getDeviceId());
	        logger.info("Update Status DeviceEarthpits table : "+sql);
	       
	        String sql1 = "update "+tableName2+" set "+SchemaDef.STATUS+" ='"+SchemaDef.INACTIVE_FLAG+"',"+SchemaDef.EDITEDBY+"='"+appParameters.getEditedBy()+"' WHERE "+device_ID+"=?";
	        jdbcTemplate.update(sql1, appParameters.getDeviceId());
	        logger.info("Update status in Device table : "+sql1);	        
        }
        catch(Exception ex)
        {
        	ex.printStackTrace();
        	logger.info("Delete DeviceEarthpit exception :" +ex);
        }
        logger.info("Delete DeviceEarthpits Details method is completed");
	}
	   
	/********************************escalation DAOImpl**********************************************/

	
	public void saveEscalationDetails(AppParameters appParameters,String tableName) {
		 logger.info("saveEscalationDetails method is started");
		   try{
	       String query="insert into "+tableName+" ("+SchemaDef.ESCALATIONID+","+SchemaDef.ESCALATIONTYPE+","+SchemaDef.ESCALATIONLEVEL+","+SchemaDef.GROUPID+","+SchemaDef.SMSMESSAGE+","+SchemaDef.EMAILMESSAGE+","+SchemaDef.ESCALATIONINTERVAL+","+SchemaDef.REPEATEINTERVAL+","+SchemaDef.STATUS+","+SchemaDef.CREATEDBY+") values('"+appParameters.getEscalationIDs()+"','"+appParameters.getEscalationTypes()+"',"+appParameters.getEscalationLevels()+",'"+appParameters.getGroupId()+"','"+appParameters.getsMSMessages()+"','"+appParameters.geteMailMessages()+"','"+appParameters.getEscalationIntervals()+"','"+appParameters.getRepeatIntervals()+"','"+appParameters.getStatus()+"','"+appParameters.getCreatedBy()+"')";  
	       logger.info("saveEscalationDetails method Query :"+query);
	       jdbcTemplate.update(query); 
	       
	       }catch(Exception ex){
	        ex.printStackTrace();
	        logger.info(ex);
	         }
		   logger.info("saveEscalationDetails method is compleated");
	         }
 /*
  * (non-Javadoc)
  * @see com.hpcl.dao.ConfigurationDao#updateEscalationDetails(com.hpcl.persistence.AppParameters, java.lang.String)
  */
	
	public void updateEscalationDetails(AppParameters appParameters,String tableName) {
		logger.info("updateEscalationDetails method is started");
		String escalationcode=SchemaDef.ESCALATIONID;
  		final String locationCode=appParameters.getEscalationIDs();
  		try{
  	        String sql = "update "+tableName+" set "+SchemaDef.SMSMESSAGE+"='"+appParameters.getsMSMessages()+"',"+SchemaDef.EMAILMESSAGE+"='"+appParameters.geteMailMessages()+"',"+SchemaDef.ESCALATIONINTERVAL+"='"+appParameters.getEscalationIntervals()+"',"+SchemaDef.REPEATEINTERVAL+"='"+appParameters.getRepeatIntervals()+"',"+SchemaDef.EDITEDBY+"='"+appParameters.getEditedBy()+"' where "+escalationcode+"=?";
  	    logger.info("updateEscalationDetails method query:"+sql);
  		    jdbcTemplate.update(sql,locationCode);
  		}catch(Exception ex){
  		ex.printStackTrace();
  		logger.info(ex);
  	   }
  		 logger.info("updateEscalationDetails method is compleated");
	        }
/*
 * (non-Javadoc)
 * @see com.hpcl.dao.ConfigurationDao#getAllEscalationDetails(java.lang.String, java.lang.String, java.lang.String)
 */
	
	public List<AppParameters> getAllEscalationDetails(final String tableName,String id, String status,String location,String userId) {
		logger.info("getAllEscalationDetails method is started"+status);
		   String sql=null;
		   if(userId.equals("admin")){
		   
			   if(status!=null){
			 		if(status.equals("A")){
			 		sql = "SELECT "+id+","+SchemaDef.ESCALATIONTYPE+","+SchemaDef.ESCALATIONLEVEL+","+SchemaDef.GROUPID+","+SchemaDef.SMSMESSAGE+","+SchemaDef.EMAILMESSAGE+","+SchemaDef.ESCALATIONINTERVAL+","+SchemaDef.REPEATEINTERVAL+","+SchemaDef.STATUS+" FROM "+tableName+"  where status='"+SchemaDef.ACTIVE_FLAG+"' ORDER BY CreatedOn DESC "; 
			 		}else if(status.equals("I")){
			 			sql = "SELECT "+id+","+SchemaDef.ESCALATIONTYPE+","+SchemaDef.ESCALATIONLEVEL+","+SchemaDef.GROUPID+","+SchemaDef.SMSMESSAGE+","+SchemaDef.EMAILMESSAGE+","+SchemaDef.ESCALATIONINTERVAL+","+SchemaDef.REPEATEINTERVAL+","+SchemaDef.STATUS+" FROM "+tableName+"  where status='"+SchemaDef.INACTIVE_FLAG+"' ORDER BY CreatedOn DESC"; 
				 	}else if(status.equals("all")){
				 		sql = "SELECT "+id+","+SchemaDef.ESCALATIONTYPE+","+SchemaDef.ESCALATIONLEVEL+","+SchemaDef.GROUPID+","+SchemaDef.SMSMESSAGE+","+SchemaDef.EMAILMESSAGE+","+SchemaDef.ESCALATIONINTERVAL+","+SchemaDef.REPEATEINTERVAL+","+SchemaDef.STATUS+" FROM "+tableName+" ORDER BY CreatedOn DESC"; 
				 	    }
			 	    }else{
			 	    	sql = "SELECT "+id+","+SchemaDef.ESCALATIONTYPE+","+SchemaDef.ESCALATIONLEVEL+","+SchemaDef.GROUPID+","+SchemaDef.SMSMESSAGE+","+SchemaDef.EMAILMESSAGE+","+SchemaDef.ESCALATIONINTERVAL+","+SchemaDef.REPEATEINTERVAL+","+SchemaDef.STATUS+" FROM "+tableName+" where status='"+SchemaDef.ACTIVE_FLAG+"' ORDER BY CreatedOn DESC"; 
			 	}
		   }else{
			if(status!=null){
		 		if(status.equals("A")){
		 		sql = "SELECT "+id+","+SchemaDef.ESCALATIONTYPE+","+SchemaDef.ESCALATIONLEVEL+","+SchemaDef.GROUPID+","+SchemaDef.SMSMESSAGE+","+SchemaDef.EMAILMESSAGE+","+SchemaDef.ESCALATIONINTERVAL+","+SchemaDef.REPEATEINTERVAL+","+SchemaDef.STATUS+" FROM "+tableName+"  where status='"+SchemaDef.ACTIVE_FLAG+"' ORDER BY CreatedOn DESC"; 
		 		}else if(status.equals("I")){
		 			sql = "SELECT "+id+","+SchemaDef.ESCALATIONTYPE+","+SchemaDef.ESCALATIONLEVEL+","+SchemaDef.GROUPID+","+SchemaDef.SMSMESSAGE+","+SchemaDef.EMAILMESSAGE+","+SchemaDef.ESCALATIONINTERVAL+","+SchemaDef.REPEATEINTERVAL+","+SchemaDef.STATUS+" FROM "+tableName+"  where status='"+SchemaDef.INACTIVE_FLAG+"' ORDER BY CreatedOn DESC"; 
			 	}else if(status.equals("all")){
			 		sql = "SELECT "+id+","+SchemaDef.ESCALATIONTYPE+","+SchemaDef.ESCALATIONLEVEL+","+SchemaDef.GROUPID+","+SchemaDef.SMSMESSAGE+","+SchemaDef.EMAILMESSAGE+","+SchemaDef.ESCALATIONINTERVAL+","+SchemaDef.REPEATEINTERVAL+","+SchemaDef.STATUS+" FROM "+tableName+" ORDER BY CreatedOn DESC"; 
			 	    }
		 	    }else{
		 	    	sql = "SELECT "+id+","+SchemaDef.ESCALATIONTYPE+","+SchemaDef.ESCALATIONLEVEL+","+SchemaDef.GROUPID+","+SchemaDef.SMSMESSAGE+","+SchemaDef.EMAILMESSAGE+","+SchemaDef.ESCALATIONINTERVAL+","+SchemaDef.REPEATEINTERVAL+","+SchemaDef.STATUS+" FROM "+tableName+" where status='"+SchemaDef.ACTIVE_FLAG+"' ORDER BY CreatedOn DESC"; 
		 	}
		   }
               logger.info("getAllEscalationDetails method is started Query "+sql);
		        List<AppParameters> appParametersList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
		        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
		        	AppParameters appParameters = new AppParameters();
		    	if(tableName.equals(SchemaDef.M_ESCALATION)){
		    		appParameters.setEscalationIDs(rs.getString("escalationID"));
		    		appParameters.setEscalationTypes(rs.getString("escalationType"));
		    		appParameters.setEscalationLevels(rs.getString("escalationLevel"));
		    		appParameters.setGroupId(rs.getString("groupId"));
		    		appParameters.setsMSMessages(rs.getString("sMSMessage"));
		    		appParameters.seteMailMessages(rs.getString("eMailMessage"));
		    		appParameters.setEscalationIntervals(rs.getString("escalationInterval"));
		    		appParameters.setRepeatIntervals(rs.getInt("repeatInterval"));
		    		appParameters.setStatus(rs.getString("status"));
		           }
		    	return appParameters;
	                }
		           });
		      logger.info("getAllEscalationDetails method is compleated");
		      return appParametersList;
	       }
   /*
    * (non-Javadoc)
    * @see com.hpcl.dao.ConfigurationDao#deleteEscalationDetails(com.hpcl.persistence.AppParameters, java.lang.String, java.lang.String)
    */
	
	public void deleteEscalationDetails(AppParameters appParameters,String tableName,String id) {
		final String escalation_ID=SchemaDef.ESCALATIONID;
		
		logger.info("deleteEscalationDetails method is started");
        try{
       	 
	       String sql = "update "+tableName+" set "+SchemaDef.STATUS+" ='"+SchemaDef.INACTIVE_FLAG+"',"+SchemaDef.EDITEDBY+"='"+appParameters.getEditedBy()+"' WHERE "+escalation_ID+"=?";
	       logger.info("deleteEscalationDetails method Query : "+sql+" Id"+appParameters.getEscalationIDs()); 
	       jdbcTemplate.update(sql, appParameters.getEscalationIDs());
	
		    }catch(Exception ex){
		        ex.printStackTrace();
		        logger.info(ex);
		        }
        logger.info("deleteEscalationDetails method is compleated");
		
	      }
/********************************Earthpit Monitorng DAOImpl**********************************************/
   /*
    * (non-Javadoc)
    * @see com.hpcl.dao.ConfigurationDao#getAllEarthpitMonitoringDetails(java.lang.String, com.hpcl.persistence.AppParameters, java.lang.String)
    */
	
	public List<AppParameters> getAllEarthpitMonitoringDetails(final String tableName, AppParameters twoFieldPersistence, String status,String fromdatetime,String todatetime,String location,String userId) {
		logger.info("getAllEarthpitMonitoringDetails method is started"+status);
		   String sql=null;
		   if(userId.equals("admin")){
			   
			   if(status!=null){
			 		if(status.equals("A")){
			 		sql ="SELECT mloca.LocationName, dev.DeviceName, ert.earthpitName, dabord.Voltage, dabord.ReceivedDate FROM tr_dashboard dabord, m_device dev, m_earthpits ert, m_locations mloca where dabord.DeviceId = dev.DeviceId and  dabord.EarthpitId= ert.earthpitId and dabord.Location = mloca.locationId and dabord.status ='A' and (dabord.ReceivedDate between '"+fromdatetime+"' AND '"+todatetime+"')  ORDER BY dabord.ReceivedDate,dev.DeviceId DESC"; 
			 		//sql = "SELECT mloc.LOCATIONNAME,alt.DEVICENAME,alt.EARTHPITNAME,alt.VOLTAGE,alt.RECEIVEDDATE FROM tr_dashboard alt,m_locations mloc where alt.location=mloc.locationId and alt.status='A';";
			 		logger.info("getAllEarthpitMonitoringDetails with Active status: "+sql); 
			 		logger.info("fromdatetime: "+fromdatetime);
			 		logger.info("todatetime: "+todatetime); 	
			 		}
			 		else if(status.equals("I")){
			 			//sql = "SELECT mloc.LOCATIONNAME,alt.DEVICENAME,alt.EARTHPITNAME,alt.VOLTAGE,alt.RECEIVEDDATE FROM tr_dashboard alt,m_locations mloc where alt.location=mloc.locationId and alt.status='I';";
			 			sql ="SELECT mloca.LocationName, dev.DeviceName, ert.earthpitName, dabord.Voltage, dabord.ReceivedDate FROM tr_dashboard dabord, m_device dev, m_earthpits ert, m_locations mloca where dabord.DeviceId = dev.DeviceId and  dabord.EarthpitId= ert.earthpitId and dabord.Location = mloca.locationId and dabord.status ='A' and (dabord.ReceivedDate between '"+fromdatetime+"' AND '"+todatetime+"')  ORDER BY dabord.ReceivedDate,dev.DeviceId DESC";
			 			logger.info("getAllEarthpitMonitoringDetails with Inactive status: "+sql); 	
				 	}else if(status.equals("all")){
				 		sql = "SELECT "+SchemaDef.LOCATIONNAME+","+SchemaDef.DEVICE_NAME+","+SchemaDef.EARTHPIT_NAME+","+SchemaDef.VOLTAGE+","+SchemaDef.RECEIVEDDATE+" FROM "+tableName+" ";
				 		logger.info("getAllEarthpitMonitoringDetails with All status: "+sql); 	
				 	    }else if(status.equals("location")){
					 		
					 		//sql="SELECT mloc.LOCATIONNAME,alt.DEVICENAME,alt.EARTHPITNAME,alt.VOLTAGE,alt.RECEIVEDDATE FROM tr_dashboard alt,m_locations mloc where alt.location=mloc.locationId and alt.status='A' and "+SchemaDef.LACATION+"='"+twoFieldPersistence.getLocationID()+"'";
				 	    	sql="SELECT mloca.LocationName, dev.DeviceName, ert.earthpitName, dabord.Voltage, dabord.ReceivedDate FROM tr_dashboard dabord, m_device dev, m_earthpits ert, m_locations mloca where dabord.DeviceId = dev.DeviceId and  dabord.EarthpitId= ert.earthpitId and dabord.Location = mloca.locationId and dabord.status ='A' and (dabord.ReceivedDate between '"+fromdatetime+"' AND '"+todatetime+"')  ORDER BY dabord.ReceivedDate,dev.DeviceId DESC";
				 	    	//sql ="SELECT mloca.LocationName, dev.DeviceName, ert.earthpitName, dabord.Voltage, dabord.ReceivedDate FROM tr_dashboard dabord, m_device dev, m_earthpits ert, m_locations mloca where dabord.DeviceId = dev.DeviceId and  dabord.EarthpitId= ert.earthpitId and dabord.Location = mloca.locationId and dabord.status ='A' and (dabord.ReceivedDate between '"+fromdatetime+"' AND '"+todatetime+"') and "+SchemaDef.LACATION+"='"+twoFieldPersistence.getLocationID()+"' ORDER BY dabord.ReceivedDate DESC";
					 		logger.info("getAllEarthpitMonitoringDetails with Location filter: "+sql); 	
				 	    }
					 	   else if(status.equals("device")){
						 		
						 		//sql="SELECT mloc.LOCATIONNAME,alt.DEVICENAME,alt.EARTHPITNAME,alt.VOLTAGE,alt.RECEIVEDDATE FROM tr_dashboard alt,m_locations mloc where alt.location=mloc.locationId and alt.status='A' and "+SchemaDef.DEVICE_NAME+"='"+twoFieldPersistence.getDeviceId()+"' and "+SchemaDef.LACATION+"='"+twoFieldPersistence.getLocationID()+"'";
					 		  sql ="SELECT mloca.LocationName, dev.DeviceName, ert.earthpitName, dabord.Voltage, dabord.ReceivedDate FROM tr_dashboard dabord, m_device dev, m_earthpits ert, m_locations mloca where dabord.DeviceId = dev.DeviceId and  dabord.EarthpitId= ert.earthpitId and dabord.Location = mloca.locationId and dabord.status ='A' and (dabord.ReceivedDate between '"+fromdatetime+"' AND '"+todatetime+"')  ORDER BY dabord.ReceivedDate,dev.DeviceId DESC";
						 	   logger.info("LocationID "+twoFieldPersistence.getLocationID()); 	
						 	  logger.info("getAllEarthpitMonitoringDetails with device filter: "+sql);
				 	       }
			 	        }
	                  else{
			 	    	 //sql="SELECT mloc.LOCATIONNAME,alt.DEVICENAME,alt.EARTHPITNAME,alt.VOLTAGE,alt.RECEIVEDDATE FROM tr_dashboard alt,m_locations mloc where alt.location=mloc.locationId and alt.status='A' ";
	                	  sql ="SELECT mloca.LocationName, dev.DeviceName, ert.earthpitName, dabord.Voltage, dabord.ReceivedDate FROM tr_dashboard dabord, m_device dev, m_earthpits ert, m_locations mloca where dabord.DeviceId = dev.DeviceId and  dabord.EarthpitId= ert.earthpitId and dabord.Location = mloca.locationId and dabord.status ='A' and (dabord.ReceivedDate between '"+fromdatetime+"' AND '"+todatetime+"')  ORDER BY dabord.ReceivedDate,dev.DeviceId DESC";
	                	  logger.info("getAllEarthpitMonitoringDetails with no filters selected: "+sql);
			 	     }  
			   
           }
           else
             {
           if(status!=null){
		 		if(status.equals("A")){
		 		sql = "SELECT mloc.LOCATIONNAME,alt.DEVICENAME,alt.EARTHPITNAME,alt.VOLTAGE,alt.RECEIVEDDATE FROM tr_dashboard alt,m_locations mloc where alt.location=mloc.locationId and alt.status='A' and  mloc.LOCATIONNAME='"+location+"' and (alt.ReceivedDate between '"+fromdatetime+"' AND '"+todatetime+"')  ORDER BY alt.ReceivedDate DESC";
		 		logger.info("getAllEarthpitMonitoringDetails with Active status: "+sql); 	
		 		}else if(status.equals("I")){
		 			sql = "SELECT mloc.LOCATIONNAME,alt.DEVICENAME,alt.EARTHPITNAME,alt.VOLTAGE,alt.RECEIVEDDATE FROM tr_dashboard alt,m_locations mloc where alt.location=mloc.locationId and alt.status='A' and  mloc.LOCATIONNAME='"+location+"' and (alt.ReceivedDate between '"+fromdatetime+"' AND '"+todatetime+"')  ORDER BY alt.ReceivedDate DESC";
		 			//sql = "SELECT mloc.LOCATIONNAME,alt.DEVICENAME,alt.EARTHPITNAME,alt.VOLTAGE,alt.RECEIVEDDATE FROM tr_dashboard alt,m_locations mloc where alt.location=mloc.locationId and alt.status='I' and  mloc.LOCATIONNAME='"+location+"'";
		 			logger.info("getAllEarthpitMonitoringDetails with Inactive status: "+sql); 	
			 	}else if(status.equals("all")){
			 		sql = "SELECT mloc.LOCATIONNAME,alt.DEVICENAME,alt.EARTHPITNAME,alt.VOLTAGE,alt.RECEIVEDDATE FROM tr_dashboard alt,m_locations mloc where alt.location=mloc.locationId and alt.status='A' and  mloc.LOCATIONNAME='"+location+"' and (alt.ReceivedDate between '"+fromdatetime+"' AND '"+todatetime+"')  ORDER BY alt.ReceivedDate DESC";
			 		//sql = "SELECT mloc.LOCATIONNAME,alt.DEVICENAME,alt.EARTHPITNAME,alt.VOLTAGE,alt.RECEIVEDDATE FROM tr_dashboard alt,m_locations mloc where alt.location=mloc.locationId and alt.status='A' and  mloc.LOCATIONNAME='"+location+"' and (alt.ReceivedDate between '"+fromdatetime+"' AND '"+todatetime+"')  ORDER BY alt.ReceivedDate DESC";//sql = "SELECT "+SchemaDef.LOCATIONNAME+","+SchemaDef.DEVICE_NAME+","+SchemaDef.EARTHPIT_NAME+","+SchemaDef.VOLTAGE+","+SchemaDef.RECEIVEDDATE+" FROM "+tableName+" and  mloc.LOCATIONNAME='"+location+"'";
			 		logger.info("getAllEarthpitMonitoringDetails with All status: "+sql); 	
			 	    }else if(status.equals("location")){
			 	    	sql = "SELECT mloc.LOCATIONNAME,alt.DEVICENAME,alt.EARTHPITNAME,alt.VOLTAGE,alt.RECEIVEDDATE FROM tr_dashboard alt,m_locations mloc where alt.location=mloc.locationId and alt.status='A' and  mloc.LOCATIONNAME='"+location+"' and (alt.ReceivedDate between '"+fromdatetime+"' AND '"+todatetime+"')  ORDER BY alt.ReceivedDate DESC";
				 		//sql="SELECT mloc.LOCATIONNAME,alt.DEVICENAME,alt.EARTHPITNAME,alt.VOLTAGE,alt.RECEIVEDDATE FROM tr_dashboard alt,m_locations mloc where alt.location=mloc.locationId and alt.status='A' and "+SchemaDef.LACATION+"='"+twoFieldPersistence.getLocationID()+"' and  mloc.LOCATIONNAME='"+location+"'";
				 		logger.info("getAllEarthpitMonitoringDetails with Location filter: "+sql); 	
			 	    }
				 	   else if(status.equals("device")){
				 		  sql = "SELECT mloc.LOCATIONNAME,alt.DEVICENAME,alt.EARTHPITNAME,alt.VOLTAGE,alt.RECEIVEDDATE FROM tr_dashboard alt,m_locations mloc where alt.location=mloc.locationId and alt.status='A' and  mloc.LOCATIONNAME='"+location+"' and (alt.ReceivedDate between '"+fromdatetime+"' AND '"+todatetime+"')  ORDER BY alt.ReceivedDate DESC";
					 		//sql="SELECT mloc.LOCATIONNAME,alt.DEVICENAME,alt.EARTHPITNAME,alt.VOLTAGE,alt.RECEIVEDDATE FROM tr_dashboard alt,m_locations mloc where alt.location=mloc.locationId and alt.status='A' and "+SchemaDef.DEVICE_NAME+"='"+twoFieldPersistence.getDeviceId()+"' and "+SchemaDef.LACATION+"='"+twoFieldPersistence.getLocationID()+"' and  mloc.LOCATIONNAME='"+location+"'";
					 	   logger.info("LocationID "+twoFieldPersistence.getLocationID()); 	
					 	  logger.info("getAllEarthpitMonitoringDetails with device filter: "+sql);
			 	       }
		 	        }
                  else{
                	  sql = "SELECT mloc.LOCATIONNAME,alt.DEVICENAME,alt.EARTHPITNAME,alt.VOLTAGE,alt.RECEIVEDDATE FROM tr_dashboard alt,m_locations mloc where alt.location=mloc.locationId and alt.status='A' and  mloc.LOCATIONNAME='"+location+"' and (alt.ReceivedDate between '"+fromdatetime+"' AND '"+todatetime+"')  ORDER BY alt.ReceivedDate DESC";
                	  //sql="SELECT mloc.LOCATIONNAME,alt.DEVICENAME,alt.EARTHPITNAME,alt.VOLTAGE,alt.RECEIVEDDATE FROM tr_dashboard alt,m_locations mloc where alt.location=mloc.locationId and alt.status='A' and  mloc.LOCATIONNAME='"+location+"'";
                	  logger.info("getAllEarthpitMonitoringDetails with no filters selected: "+sql);
		 	     } 
           
             }
		        List<AppParameters> appParametersList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
		        	
		        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
		        	  
		        	AppParameters appParameters = new AppParameters();
		    	if(tableName.equals(SchemaDef.TR_DASHBOARD)){
		    		appParameters.setLocationName(rs.getString("locationName"));
		    		appParameters.setDeviceName(rs.getString("deviceName"));
		    		appParameters.setEarthpitName(rs.getString("earthpitName"));
		    		appParameters.setVoltage(rs.getString("voltage"));
		    		appParameters.setReceivedDate(rs.getString("receivedDate"));
		    		
		           }
		    	return appParameters;
	                }
		           });
		      logger.info("getAllEscalationDetails method is compleated");
		      return appParametersList;
	       }
	/*
	 * (non-Javadoc)
	 * @see com.hpcl.dao.ConfigurationDao#getAllDeviceLocationDetails(java.lang.String, java.lang.String, java.lang.String)
	 */
			
	public List<AppParameters> getAllDeviceLocationDetails(String tableName,		
			String location, String status) {		
			
		String id=SchemaDef.DEVICE_ID;		
		logger.info("getAllDeviceDetails method is started");		
		   String sql=null;		
		   sql = "SELECT "+id+","+SchemaDef.DEVICE_NAME+" FROM "+tableName+"  where status='"+SchemaDef.ACTIVE_FLAG+"' and "+SchemaDef.LOCATIONID+"='"+location+"'";		
	            logger.info("Devices based on Location selection: "+sql);		
			    List<AppParameters> twoFieldPersistenceList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {		
			        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {		
			    	AppParameters twoFieldPersistence = new AppParameters();		
			    	twoFieldPersistence.setDeviceId(rs.getString(SchemaDef.DEVICE_ID));		
			     	twoFieldPersistence.setDeviceName(rs.getString(SchemaDef.DEVICE_NAME));		
			     			
		            return twoFieldPersistence;		
		          }		
			 		
			        });		
			      logger.info("getAllDetails method is compleated");		
			
				return twoFieldPersistenceList;		
	}	
	/*
	 * (non-Javadoc)
	 * @see com.hpcl.dao.ConfigurationDao#getAllEarthpitLocationDetails(java.lang.String, java.lang.String, java.lang.String)
	 */
			
	public List<AppParameters> getAllEarthpitLocationDetails(String tableName,		
			String location, String status) {		
		// TODO Auto-generated method stub		
		String id=SchemaDef.EARTHPIT_ID;		
		logger.info("getAllDeviceDetails method is started");		
		   String sql=null;		
		   sql = "SELECT "+id+","+SchemaDef.EARTHPIT_NAME+" FROM "+tableName+"  where status='"+SchemaDef.ACTIVE_FLAG+"' and "+SchemaDef.LOCATIONID+"='"+location+"' and MappedStatus='U'";		
	            logger.info("getAllDeviceDetails method Query "+sql);		
			    List<AppParameters> twoFieldPersistenceList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {		
			        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {		
			    	AppParameters twoFieldPersistence = new AppParameters();		
			    	twoFieldPersistence.setEarthpitID(rs.getString(SchemaDef.EARTHPIT_ID));		
			     	twoFieldPersistence.setEarthpitName(rs.getString(SchemaDef.EARTHPIT_NAME));		
			     	return twoFieldPersistence;		
		          }		
			 		});		
			      logger.info("getAllDetails method is compleated");		
			
				return twoFieldPersistenceList;		
			}
  /*
   * (non-Javadoc)
   * @see com.hpcl.dao.ConfigurationDao#deleteEarthptsDetails(com.hpcl.persistence.AppParameters, java.lang.String)
   */
	
	public void deleteEarthptsDetails(AppParameters appParameters, String tableName) {
		// TODO Auto-generated method stub
		logger.info("delete device earth pit Details method is started");
		String tableName2=SchemaDef.M_EARTHPITS;
        try{
       	   String sql = "update "+tableName+" set "+SchemaDef.STATUS+" ='"+SchemaDef.INACTIVE_FLAG+"',"+SchemaDef.EDITEDBY+"='"+appParameters.getEditedBy()+"' WHERE "+SchemaDef.LOCATION_ID+"=? and "+SchemaDef.EARTHPIT_ID+"=? and "+SchemaDef.DEVICE_ID+"=?";
       	  String sql2 = "update "+tableName2+" set "+SchemaDef.EARTHPIT_MAPPEDSTATUS+" ='U',"+SchemaDef.EDITEDBY+"='"+appParameters.getEditedBy()+"' WHERE "+SchemaDef.LOCATION_ID+"=? and "+SchemaDef.EARTHPIT_ID+"=?";
       	   //String sql3 = "Delete from "+tableName+" WHERE "+SchemaDef.LOCATION_ID+"=? and "+SchemaDef.EARTHPIT_ID+"=? and "+SchemaDef.DEVICE_ID+"=?";
       	   //update m_earthpits a, m_device_earthpits b set a.MappedStatus ='U' where a.earthpitid = b.earthpitId and a.locationId = b.locationId ;
	       logger.info("delete device earth pit Details method Query : "+sql+" Id"+appParameters.getEscalationIDs());
	       logger.info("Earthpit unmapped : "+sql2+" Id"+appParameters.getEscalationIDs());
	       logger.info("locID" +appParameters.getLocationID());
	       logger.info("earthID "+appParameters.getEarthpitID());
	       logger.info("devID "+appParameters.getDeviceId());
	       jdbcTemplate.update(sql, appParameters.getLocationID(),appParameters.getEarthpitID(),appParameters.getDeviceId());
	       jdbcTemplate.update(sql2, appParameters.getLocationID(),appParameters.getEarthpitID());
	       //jdbcTemplate.update(sql3, appParameters.getLocationID(),appParameters.getEarthpitID(),appParameters.getDeviceId());
        }catch(Exception ex){
		        ex.printStackTrace();
		        logger.info(ex);
		        }
         logger.info("delete device earth pit Details method is compleated");
	}

	/******************************** AlertManagementDAOImpl**********************************************/
	/*
	 * (non-Javadoc)
	 * @see com.hpcl.dao.ConfigurationDao#getAllAlertManagementDetails(java.lang.String, com.hpcl.persistence.AppParameters, java.lang.String)
	 */
	
	public List<AppParameters> getAllAlertManagementDetails(final String tableName, AppParameters twoFieldPersistence, String status,String location,String userId) {
		
		logger.info("getAllAlertManagementDetails method is started"+status);
		
		String sql=null;
		if(userId.equals("admin")){
			

	           if(status!=null){
			 		if(status.equals("O")){
			 			sql="select distinct tr.id,loc.locationName,case when FailureType='Device' then dev.DeviceName when FailureType='Earthpit' then ert.earthpitName end as devicename,tr.DOF,es.EscalationType,IF(tr.MSD is null,sysdate(),MSD) as MSD,es.EscalationLevel,grp.GroupName,IF(tr.ActionTaken is null,'N/A',ActionTaken) as ActionTaken,IF(tr.ActionDate is null,'N/A',ActionDate) as ActionDate,IF(actiondate is null,DATEDIFF(sysdate(),tr.DOF),DATEDIFF(tr.actiondate,tr.DOF)) as Downtime,tr.RepeatTime,tr.actiondate,tr.status from tr_alertmanagment tr left join m_locations loc on ltrim(rtrim(tr.locationid)) = ltrim(rtrim(loc.locationid)) left join m_escalation es on ltrim(rtrim(tr.escalationid))=ltrim(rtrim(es.EscalationID)) left join m_groups grp on ltrim(rtrim(tr.groupid)) =ltrim(rtrim(grp.GroupId)) left join m_device dev on tr.EquipementID = dev.deviceId and tr.FailureType='Device' left join m_earthpits ert on tr.EquipementID = ert.earthpitid and tr.FailureType='Earthpit' WHERE tr.status ='O';";
			 			//sql="select distinct tr.id,loc.locationName,case when FailureType='Device' then dev.DeviceName when FailureType='Earthpit' then ert.earthpitName end as devicename,tr.DOF,es.EscalationType,IF(tr.MSD is null,sysdate(),MSD) as MSD,es.EscalationLevel,grp.GroupName,IF(tr.ActionTaken is null,'N/A',ActionTaken) as ActionTaken,IF(tr.ActionDate is null,'N/A',ActionDate) as ActionDate,IF(actiondate is null,DATEDIFF(sysdate(),tr.DOF),DATEDIFF(tr.actiondate,tr.DOF)) as Downtime,es.EscalationInterval,tr.actiondate,tr.status from tr_alertmanagment tr left join m_locations loc on ltrim(rtrim(tr.locationid)) = ltrim(rtrim(loc.locationid)) left join m_escalation es on ltrim(rtrim(tr.escalationid))=ltrim(rtrim(es.EscalationID)) left join m_groups grp on ltrim(rtrim(tr.groupid)) =ltrim(rtrim(grp.GroupId)) left join m_device dev on tr.EquipementID = dev.deviceId and tr.FailureType='Device' left join m_earthpits ert on tr.EquipementID = ert.earthpitid and tr.FailureType='Earthpit' WHERE tr.status ='O';";
			 			logger.info("Get Open Alert Details for Admin role:  "+sql);
			 		}else if(status.equals("E")){
			 			sql="select distinct tr.id,loc.locationName,case when FailureType='Device' then dev.DeviceName when FailureType='Earthpit' then ert.earthpitName end as devicename,tr.DOF,es.EscalationType,IF(tr.MSD is null,sysdate(),MSD) as MSD,es.EscalationLevel,grp.GroupName,IF(tr.ActionTaken is null,'N/A',ActionTaken) as ActionTaken,IF(tr.ActionDate is null,'N/A',ActionDate) as ActionDate,IF(actiondate is null,DATEDIFF(sysdate(),tr.DOF),DATEDIFF(tr.actiondate,tr.DOF)) as Downtime,tr.RepeatTime,tr.actiondate,tr.status from tr_alertmanagment tr left join m_locations loc on ltrim(rtrim(tr.locationid)) = ltrim(rtrim(loc.locationid)) left join m_escalation es on ltrim(rtrim(tr.escalationid))=ltrim(rtrim(es.EscalationID)) left join m_groups grp on ltrim(rtrim(tr.groupid)) =ltrim(rtrim(grp.GroupId)) left join m_device dev on tr.EquipementID = dev.deviceId and tr.FailureType='Device' left join m_earthpits ert on tr.EquipementID = ert.earthpitid and tr.FailureType='Earthpit' WHERE tr.status ='L' OR tr.status ='M' OR tr.status ='H' OR tr.status ='Escalated';";
			 			logger.info("Get Escalated Alert Details for Admin role: "+sql);
				 	}else if(status.equals("C"))
				 	{
				 		sql="select distinct tr.id,loc.locationName,case when FailureType='Device' then dev.DeviceName when FailureType='Earthpit' then ert.earthpitName end as devicename,tr.DOF,es.EscalationType,IF(tr.MSD is null,sysdate(),MSD) as MSD,es.EscalationLevel,grp.GroupName,IF(tr.ActionTaken is null,'N/A',ActionTaken) as ActionTaken,IF(tr.ActionDate is null,'N/A',ActionDate) as ActionDate,IF(actiondate is null,DATEDIFF(sysdate(),tr.DOF),DATEDIFF(tr.actiondate,tr.DOF)) as Downtime,tr.RepeatTime,tr.actiondate,tr.status from tr_alertmanagment tr left join m_locations loc on ltrim(rtrim(tr.locationid)) = ltrim(rtrim(loc.locationid)) left join m_escalation es on ltrim(rtrim(tr.escalationid))=ltrim(rtrim(es.EscalationID)) left join m_groups grp on ltrim(rtrim(tr.groupid)) =ltrim(rtrim(grp.GroupId)) left join m_device dev on tr.EquipementID = dev.deviceId and tr.FailureType='Device' left join m_earthpits ert on tr.EquipementID = ert.earthpitid and tr.FailureType='Earthpit' WHERE tr.status ='C';";
				 		logger.info("Get Closed Alert Details for Admin role: "+sql);				 		
				 	}
			 		else if(status.equals("all")){
				 		sql="select distinct tr.id,loc.locationName,case when FailureType='Device' then dev.DeviceName when FailureType='Earthpit' then ert.earthpitName end as devicename,tr.DOF,es.EscalationType,IF(tr.MSD is null,sysdate(),MSD) as MSD,es.EscalationLevel,grp.GroupName,IF(tr.ActionTaken is null,'N/A',ActionTaken) as ActionTaken,IF(tr.ActionDate is null,'N/A',ActionDate) as ActionDate,IF(actiondate is null,DATEDIFF(sysdate(),tr.DOF),DATEDIFF(tr.actiondate,tr.DOF)) as Downtime,tr.RepeatTime,tr.actiondate,tr.status from tr_alertmanagment tr left join m_locations loc on ltrim(rtrim(tr.locationid)) = ltrim(rtrim(loc.locationid)) left join m_escalation es on ltrim(rtrim(tr.escalationid))=ltrim(rtrim(es.EscalationID)) left join m_groups grp on ltrim(rtrim(tr.groupid)) =ltrim(rtrim(grp.GroupId)) left join m_device dev on tr.EquipementID = dev.deviceId and tr.FailureType='Device' left join m_earthpits ert on tr.EquipementID = ert.earthpitid and tr.FailureType='Earthpit';";
				 		logger.info("Get All Alert Details for Admin role: "+sql);
				 	    }else if(status.equals("A")){
					 		sql="select distinct tr.id,loc.locationName,case when FailureType='Device' then dev.DeviceName when FailureType='Earthpit' then ert.earthpitName end as devicename,tr.DOF,es.EscalationType,IF(tr.MSD is null,sysdate(),MSD) as MSD,es.EscalationLevel,grp.GroupName,IF(tr.ActionTaken is null,'N/A',ActionTaken) as ActionTaken,IF(tr.ActionDate is null,'N/A',ActionDate) as ActionDate,IF(actiondate is null,DATEDIFF(sysdate(),tr.DOF),DATEDIFF(tr.actiondate,tr.DOF)) as Downtime,tr.RepeatTime,tr.actiondate,tr.status from tr_alertmanagment tr left join m_locations loc on ltrim(rtrim(tr.locationid)) = ltrim(rtrim(loc.locationid)) left join m_escalation es on ltrim(rtrim(tr.escalationid))=ltrim(rtrim(es.EscalationID)) left join m_groups grp on ltrim(rtrim(tr.groupid)) =ltrim(rtrim(grp.GroupId)) left join m_device dev on tr.EquipementID = dev.deviceId and tr.FailureType='Device' left join m_earthpits ert on tr.EquipementID = ert.earthpitid and tr.FailureType='Earthpit';";
					 		logger.info("Get All Alert Details for Admin role: "+sql);
					 	    }
		            }else{
		    	   	    sql="select distinct tr.id,loc.locationName,case when FailureType='Device' then dev.DeviceName when FailureType='Earthpit' then ert.earthpitName end as devicename,tr.DOF,es.EscalationType,IF(tr.MSD is null,sysdate(),MSD) as MSD,es.EscalationLevel,grp.GroupName,IF(tr.ActionTaken is null,'N/A',ActionTaken) as ActionTaken,IF(tr.ActionDate is null,'N/A',ActionDate) as ActionDate,IF(actiondate is null,DATEDIFF(sysdate(),tr.DOF),DATEDIFF(tr.actiondate,tr.DOF)) as Downtime,tr.RepeatTime,tr.actiondate,tr.status from tr_alertmanagment tr left join m_locations loc on ltrim(rtrim(tr.locationid)) = ltrim(rtrim(loc.locationid)) left join m_escalation es on ltrim(rtrim(tr.escalationid))=ltrim(rtrim(es.EscalationID)) left join m_groups grp on ltrim(rtrim(tr.groupid)) =ltrim(rtrim(grp.GroupId)) left join m_device dev on tr.EquipementID = dev.deviceId and tr.FailureType='Device' left join m_earthpits ert on tr.EquipementID = ert.earthpitid and tr.FailureType='Earthpit';";
		                logger.info("Default Get All Alert Details for Admin Role: "+sql);		                
					  }
        }
        else
          {  
           if(status!=null){
		 		if(status.equals("O")){
		 			sql="select distinct tr.id,loc.locationName,case when FailureType='Device' then dev.DeviceName when FailureType='Earthpit' then ert.earthpitName end as devicename,tr.DOF,es.EscalationType,IF(tr.MSD is null,sysdate(),MSD) as MSD,es.EscalationLevel,grp.GroupName,IF(tr.ActionTaken is null,'N/A',ActionTaken) as ActionTaken,IF(tr.ActionDate is null,'N/A',ActionDate) as ActionDate,IF(actiondate is null,DATEDIFF(sysdate(),tr.DOF),DATEDIFF(tr.actiondate,tr.DOF)) as Downtime,tr.RepeatTime,tr.actiondate,tr.status from tr_alertmanagment tr left join m_locations loc on ltrim(rtrim(tr.locationid)) = ltrim(rtrim(loc.locationid)) left join m_escalation es on ltrim(rtrim(tr.escalationid))=ltrim(rtrim(es.EscalationID)) left join m_groups grp on ltrim(rtrim(tr.groupid)) =ltrim(rtrim(grp.GroupId)) left join m_device dev on tr.EquipementID = dev.deviceId and tr.FailureType='Device' left join m_earthpits ert on tr.EquipementID = ert.earthpitid and tr.FailureType='Earthpit' WHERE tr.status ='O' AND loc.locationName='"+location+"'";
		 			logger.info("Get All Open Alert Details for Location User:"+sql);
		 		}else if(status.equals("E")){
		 			sql="select distinct tr.id,loc.locationName,case when FailureType='Device' then dev.DeviceName when FailureType='Earthpit' then ert.earthpitName end as devicename,tr.DOF,es.EscalationType,IF(tr.MSD is null,sysdate(),MSD) as MSD,es.EscalationLevel,grp.GroupName,IF(tr.ActionTaken is null,'N/A',ActionTaken) as ActionTaken,IF(tr.ActionDate is null,'N/A',ActionDate) as ActionDate,IF(actiondate is null,DATEDIFF(sysdate(),tr.DOF),DATEDIFF(tr.actiondate,tr.DOF)) as Downtime,tr.RepeatTime,tr.actiondate,tr.status from tr_alertmanagment tr left join m_locations loc on ltrim(rtrim(tr.locationid)) = ltrim(rtrim(loc.locationid)) left join m_escalation es on ltrim(rtrim(tr.escalationid))=ltrim(rtrim(es.EscalationID)) left join m_groups grp on ltrim(rtrim(tr.groupid)) =ltrim(rtrim(grp.GroupId)) left join m_device dev on tr.EquipementID = dev.deviceId and tr.FailureType='Device' left join m_earthpits ert on tr.EquipementID = ert.earthpitid and tr.FailureType='Earthpit' WHERE tr.status ='L' OR tr.status ='M' OR tr.status ='H' AND loc.locationName='"+location+"'";
		 			logger.info("Get All Escalated Alert Details for Location User :"+sql);
			 	}else if(status.equals("C"))
			 	{
			 		sql="select distinct tr.id,loc.locationName,case when FailureType='Device' then dev.DeviceName when FailureType='Earthpit' then ert.earthpitName end as devicename,tr.DOF,es.EscalationType,IF(tr.MSD is null,sysdate(),MSD) as MSD,es.EscalationLevel,grp.GroupName,IF(tr.ActionTaken is null,'N/A',ActionTaken) as ActionTaken,IF(tr.ActionDate is null,'N/A',ActionDate) as ActionDate,IF(actiondate is null,DATEDIFF(sysdate(),tr.DOF),DATEDIFF(tr.actiondate,tr.DOF)) as Downtime,tr.RepeatTime,tr.actiondate,tr.status from tr_alertmanagment tr left join m_locations loc on ltrim(rtrim(tr.locationid)) = ltrim(rtrim(loc.locationid)) left join m_escalation es on ltrim(rtrim(tr.escalationid))=ltrim(rtrim(es.EscalationID)) left join m_groups grp on ltrim(rtrim(tr.groupid)) =ltrim(rtrim(grp.GroupId)) left join m_device dev on tr.EquipementID = dev.deviceId and tr.FailureType='Device' left join m_earthpits ert on tr.EquipementID = ert.earthpitid and tr.FailureType='Earthpit' WHERE tr.status ='C' AND loc.locationName='"+location+"'";
			 		logger.info("Get All Closed Alert Details for Location User:"+sql);
			 	}
		 		else if(status.equals("all")){
			 		sql="select distinct tr.id,loc.locationName,case when FailureType='Device' then dev.DeviceName when FailureType='Earthpit' then ert.earthpitName end as devicename,tr.DOF,es.EscalationType,IF(tr.MSD is null,sysdate(),MSD) as MSD,es.EscalationLevel,grp.GroupName,IF(tr.ActionTaken is null,'N/A',ActionTaken) as ActionTaken,IF(tr.ActionDate is null,'N/A',ActionDate) as ActionDate,IF(actiondate is null,DATEDIFF(sysdate(),tr.DOF),DATEDIFF(tr.actiondate,tr.DOF)) as Downtime,tr.RepeatTime,tr.actiondate,tr.status from tr_alertmanagment tr left join m_locations loc on ltrim(rtrim(tr.locationid)) = ltrim(rtrim(loc.locationid)) left join m_escalation es on ltrim(rtrim(tr.escalationid))=ltrim(rtrim(es.EscalationID)) left join m_groups grp on ltrim(rtrim(tr.groupid)) =ltrim(rtrim(grp.GroupId)) left join m_device dev on tr.EquipementID = dev.deviceId and tr.FailureType='Device' left join m_earthpits ert on tr.EquipementID = ert.earthpitid and tr.FailureType='Earthpit' WHERE tr.status='O' OR tr.status ='L' OR tr.status ='M' OR tr.status ='H' OR tr.status='C' AND loc.locationName='"+location+"'";
			 		logger.info("Get All Alert Details for Location User:"+sql);
			 	    }else if(status.equals("A")){
				 		sql="select distinct tr.id,loc.locationName,case when FailureType='Device' then dev.DeviceName when FailureType='Earthpit' then ert.earthpitName end as devicename,tr.DOF,es.EscalationType,IF(tr.MSD is null,sysdate(),MSD) as MSD,es.EscalationLevel,grp.GroupName,IF(tr.ActionTaken is null,'N/A',ActionTaken) as ActionTaken,IF(tr.ActionDate is null,'N/A',ActionDate) as ActionDate,IF(actiondate is null,DATEDIFF(sysdate(),tr.DOF),DATEDIFF(tr.actiondate,tr.DOF)) as Downtime,tr.RepeatTime,tr.actiondate,tr.status from tr_alertmanagment tr left join m_locations loc on ltrim(rtrim(tr.locationid)) = ltrim(rtrim(loc.locationid)) left join m_escalation es on ltrim(rtrim(tr.escalationid))=ltrim(rtrim(es.EscalationID)) left join m_groups grp on ltrim(rtrim(tr.groupid)) =ltrim(rtrim(grp.GroupId)) left join m_device dev on tr.EquipementID = dev.deviceId and tr.FailureType='Device' left join m_earthpits ert on tr.EquipementID = ert.earthpitid and tr.FailureType='Earthpit' WHERE tr.status='O' OR tr.status ='L' OR tr.status ='M' OR tr.status ='H' OR tr.status='C' AND loc.locationName='"+location+"'";
				 		logger.info("Get All Alert Details for Location User:"+sql);
				 	    }
	                  }else{
	    	         //sql="select tr.id,loc.locationName,case when FailureType='Device' then dev.DeviceName when FailureType='Earthpit' then ert.earthpitName end as devicename,tr.DOF,tr.EscalationID,IF(tr.MSD is null,sysdate(),MSD) as MSD,es.EscalationLevel,grp.GroupName,IF(tr.ActionTaken is null,'N/A',ActionTaken) as ActionTaken,IF(tr.ActionDate is null,'N/A',ActionDate) as ActionDate,IF(actiondate is null,DATEDIFF(sysdate(),tr.DOF),DATEDIFF(tr.actiondate,tr.DOF)) as Downtime,es.EscalationInterval,tr.actiondate,tr.status from tr_alertmanagment tr left join m_locations loc on ltrim(rtrim(tr.locationid)) = ltrim(rtrim(loc.locationid)) left join m_escalation es on ltrim(rtrim(tr.escalationid))=ltrim(rtrim(es.EscalationID)) left join m_groups grp on ltrim(rtrim(tr.groupid)) =ltrim(rtrim(grp.GroupId)) left join m_device dev on tr.EquipementID = dev.deviceId and tr.FailureType='Device' left join m_earthpits ert on tr.EquipementID = ert.earthpitid and tr.FailureType='Earthpit' WHERE  loc.locationName='"+location+"'";
	    	         //logger.info("Get D Alert Details for Location User:"+sql);
				 	  }
          }
        
		        List<AppParameters> appParametersList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
		        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
		        	AppParameters appParameters = new AppParameters();
		    	if(tableName.equals(SchemaDef.TR_ALERTMANAGMENT)){
		    		appParameters.setId(rs.getString("Id"));
		    		appParameters.setLocationName(rs.getString("locationName"));
		    		appParameters.setFailureType(rs.getString("devicename"));
			    	appParameters.setdOF(rs.getString("DOF"));
		    		//appParameters.setEscalationIDs(rs.getString("EscalationID"));
			    	appParameters.setEscalationTypes(rs.getString("EscalationType"));
		    		appParameters.setmSD(rs.getString("MSD"));
		    		appParameters.setEscalationLevels(rs.getString("EscalationLevel"));
		    		appParameters.setGroupName(rs.getString("GroupName"));
		    		appParameters.setActionTaken(rs.getString("ActionTaken"));
		    		appParameters.setActionDate(rs.getString("ActionDate"));
		    		appParameters.setDiffTime(rs.getString(11));
		    		//appParameters.setRepeatTime(rs.getString("EscalationInterval"));
		    		appParameters.setRepeatTime(rs.getString("RepeatTime"));
		    		appParameters.setStatus(rs.getString("Status"));
		    		}
		    	    return appParameters;
	                }
		         });
		      logger.info("getAllAlertManagementDetails method is compleated");
		 return appParametersList;
	     }
/*
 * (non-Javadoc)
 * @see com.hpcl.dao.ConfigurationDao#updateAlertManagementDetails(com.hpcl.persistence.AppParameters, java.lang.String)
 */
	
	public void updateAlertManagementDetails(AppParameters appParameters, String tableName) {
		logger.info("updateAlertManagementDetails method is start");
		String alertid=SchemaDef.ID;
 		 String earid =appParameters.getId();
 		 logger.info("stats info ::"+appParameters.getStatus());
 		 String actionStatus=null;
 		 String sql=null;
 		    try{
 			if(appParameters.getStatus().equals("C")){
 				
 				 actionStatus="C";
 				logger.info("Action Status = Closed: true section of if");
 				  sql = "update "+tableName+" set "+SchemaDef.ACTIONTAKEN+"='"+appParameters.getActionTaken()+"',"+SchemaDef.ACTIONDATE+"=sysdate(),"+SchemaDef.STATUS+"='C',"+SchemaDef.EDITEDBY+"='admin',"+SchemaDef.EDITEDON+"=sysdate() where "+alertid+"=?";
 			  }else if(appParameters.getStatus().equals("O")){
 	 				
  				 actionStatus="O";
  				logger.info("Action Status = Opented: true section of if");
  				  sql = "update "+tableName+" set "+SchemaDef.ACTIONTAKEN+"='"+appParameters.getActionTaken()+"',"+SchemaDef.ACTIONDATE+"=sysdate(),"+SchemaDef.STATUS+"='O',"+SchemaDef.EDITEDBY+"='admin',"+SchemaDef.EDITEDON+"=sysdate() where "+alertid+"=?";
  			  }else{
 				logger.info("No Action taken: false section of if");
 				sql = "update "+tableName+" set "+SchemaDef.ACTIONTAKEN+"='"+appParameters.getActionTaken()+"',"+SchemaDef.EDITEDBY+"='admin',"+SchemaDef.EDITEDON+"=sysdate() where "+alertid+"=?";
 			}
 		   logger.info("query: "+sql);
 		   logger.info("earthpitID:"+earid);
 		   logger.info("action taken:"+ appParameters.getActionTaken());
 		    jdbcTemplate.update(sql,earid);
 			}catch(Exception ex){
 		    ex.printStackTrace();
 			logger.info(ex);
 			        }
 		  logger.info("updateAlertManagementDetails method is compleated");
	     }
/*
 * (non-Javadoc)
 * @see com.hpcl.dao.ConfigurationDao#userValidate(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
 */
	
	public List<AppParameters> userValidate(final String tableName1,
			String tableName2, String id, String status) {
		   logger.info("getAllDetails method is started"+status);
		   String sql=null;
		   sql = "SELECT "+SchemaDef.EMPLOYENAMEE+","+SchemaDef.LACATION+","+SchemaDef.ROLES+","+SchemaDef.STATUS+" FROM "+tableName1+" where status='"+SchemaDef.ACTIVE_FLAG+"' and  "+SchemaDef.EMPLOYEEID+"='"+id+"'";
	         logger.info("userValidate Query "+sql);
		      List<AppParameters> appParametersList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
		        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
		        	AppParameters appParameters = new AppParameters();
                /*if(tableName1.equals(SchemaDef.m_employees)){
		    		appParameters.setEmployeeID(rs.getString(SchemaDef.EMPLOYEEID));
		    	}*/
		    	appParameters.setEmployeeName(rs.getString("employeeName"));
		    	appParameters.setLocation(rs.getString("location"));
		    	appParameters.setRoles(rs.getString("roles"));
		    	appParameters.setStatus(rs.getString("status"));
	                return appParameters;
	                }
		       });
		      logger.info("getAllDetails method is compleated");
		      return appParametersList;
	}
	/*
	 * (non-Javadoc)
	 * @see com.hpcl.dao.ConfigurationDao#saveAlertGroupDetails(com.hpcl.persistence.AppParameters, java.lang.String)
	 */
	
	public void saveAlertGroupDetails(AppParameters appParameters,String tableName) {
		logger.info("saveAlertGroupDetails method is started");
		try{
	       String query="insert into "+tableName+" ("+SchemaDef.GROUPID+","+SchemaDef.GROUPNAME+","+SchemaDef.LEVEL+","+SchemaDef.LOCATIONID+","+SchemaDef.EMPLOYEEID+","+SchemaDef.STATUS+","+SchemaDef.CREATEDBY+") values('"+appParameters.getGroupId()+"','"+appParameters.getGroupName()+"','"+appParameters.getLevel()+"','"+appParameters.getLocationID()+"','"+appParameters.getEmployeeID()+"','"+appParameters.getStatus()+"','"+appParameters.getCreatedBy()+"')";
	         logger.info("saveAlertGroupDetails Query :"+query);
	         jdbcTemplate.update(query); 
	        		    
	       }catch(Exception ex){
	        ex.printStackTrace();
	         }
		   logger.info("saveAlertGroupDetails method is compleated");
	    }
  /*
   * (non-Javadoc)
   * @see com.hpcl.dao.ConfigurationDao#updateAlertGroupDetails(com.hpcl.persistence.AppParameters, java.lang.String)
   */
	
	public void updateAlertGroupDetails(AppParameters appParameters,String tableName) {
		
		logger.info("updateAlertGroupDetails method is started");
		String groupID=SchemaDef.GROUPID;
  		final String locationCode=appParameters.getGroupId();
  		 try{
  	        String sql = "update "+tableName+" set "+SchemaDef.GROUPID+"='"+appParameters.getGroupId()+"',"+SchemaDef.GROUPNAME+"='"+appParameters.getGroupName()+"',"+SchemaDef.LEVEL+"='"+appParameters.getLevel()+"',"+SchemaDef.EDITEDBY+"='"+appParameters.getEditedBy()+"' where "+groupID+"=?";
  			//String sql = "update "+tableName+" set "+SchemaDef.EMPLOYEEID+"='"+appParameters.getEmployeeID()+"',"+SchemaDef.LEVEL+"='"+appParameters.getLevel()+"',"+SchemaDef.EDITEDBY+"='"+appParameters.getEditedBy()+"',"+SchemaDef.EDITEDON+"='"+currentDate+"' where "+groupID+"=?";
  	      logger.info("updateAlertGroupDetails method Query:"+sql);
  		    jdbcTemplate.update(sql,groupID);
  			}catch(Exception ex){
  			    ex.printStackTrace();
  			    logger.info(ex);
  			        }
  		logger.info("updateAlertGroupDetails method is compleated");
	
		}
  /*
   * (non-Javadoc)
   * @see com.hpcl.dao.ConfigurationDao#getAllAlertGroupDetails(java.lang.String, java.lang.String, java.lang.String)
   */
	
	public List<AppParameters> getAllAlertGroupDetails(String tableName,String id, String status,String location,String userId) {
		logger.info("getAlertGroupDetails method is started");
		logger.info("status"+status);
		
		   String sql=null;
		   if(userId.equals("admin")){
			   
			   if(status!=null){
			 		if(status.equals("A")){
			 			sql="select g.groupid,g.groupname,m.locn,m.location,g.level,m.employeename,m.employeeID,m.phoneno,m.emailid,r.Description,g.status from m_role r, m_groups g join m_employees m on m.EmployeeID=g.EmployeeId  where g.status='A' and r.RoleID=m.roles ORDER BY g.createdOn DESC";
			 		      
			 		}else if(status.equals("I")){
			 			 sql="select g.groupid,g.groupname,m.locn,m.location,g.level,m.employeename,m.employeeID,m.phoneno,m.emailid,r.Description,g.status from m_role r, m_groups g join m_employees m on m.EmployeeID=g.EmployeeId  where g.status='I' and r.RoleID=m.roles ORDER BY g.createdOn DESC";
			 			  
				 	}else if(status.equals("All")){
				 		
				 		 sql="select g.groupid,g.groupname,m.locn,m.location,g.level,m.employeename,m.employeeID,m.phoneno,m.emailid,r.Description,g.status from m_role r, m_groups g join m_employees m on m.EmployeeID=g.EmployeeId  where r.RoleID=m.roles ORDER BY g.createdOn DESC";
				 		 
				 	    }
			 	       }else{
			 	    	   sql="select g.groupid,g.groupname,m.locn,m.location,g.level,m.employeename,m.employeeID,m.phoneno,m.emailid,r.Description,g.status from m_role r, m_groups g join m_employees m on m.EmployeeID=g.EmployeeId  where g.status='A' and r.RoleID=m.roles ORDER BY g.createdOn DESC";
			 	    	  
			 	      }
           }
           else
             {
             if(status!=null){
		 		if(status.equals("A")){
		 			    
		 			sql="select g.groupid,g.groupname,m.locn,m.location,g.level,m.employeename,m.employeeID,m.phoneno,m.emailid,r.Description,g.status from m_role r, m_groups g join m_employees m on m.EmployeeID=g.EmployeeId  where g.status='A' and r.RoleID=m.roles and m.location ='"+location+"' ORDER BY g.createdOn DESC"; 
		 		}else if(status.equals("I")){
		 			 
		 			sql="select g.groupid,g.groupname,m.locn,m.location,g.level,m.employeename,m.employeeID,m.phoneno,m.emailid,r.Description,g.status from m_role r, m_groups g join m_employees m on m.EmployeeID=g.EmployeeId  where g.status='I' and r.RoleID=m.roles  and m.location ='"+location+"' ORDER BY g.createdOn DESC";
		 			 
			 	}else if(status.equals("All")){
			 		
			 		 
			 		sql="select g.groupid,g.groupname,m.locn,m.location,g.level,m.employeename,m.employeeID,m.phoneno,m.emailid,r.Description,g.status from m_role r, m_groups g join m_employees m on m.EmployeeID=g.EmployeeId  where r.RoleID=m.roles and m.location ='"+location+"' ORDER BY g.createdOn DESC";
			 	    }
		 	       }else{
		 	    	
		 	    	  sql="select g.groupid,g.groupname,m.locn,m.location,g.level,m.employeename,m.employeeID,m.phoneno,m.emailid,r.Description,g.status from m_role r, m_groups g join m_employees m on m.EmployeeID=g.EmployeeId  where g.status='A' and r.RoleID=m.roles and m.location ='"+location+"' ORDER BY g.createdOn DESC";
		 	      }
             }
	               logger.info("getAlertGroupDetails method Query-> "+sql);
	               
			        List<AppParameters> twoFieldPersistenceList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
			        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
			    	AppParameters twoFieldPersistence = new AppParameters();
			    	twoFieldPersistence.setGroupId(rs.getString(SchemaDef.GROUPID));
			     	twoFieldPersistence.setGroupName(rs.getString(SchemaDef.GROUPNAME));
			     	twoFieldPersistence.setLocation(rs.getString(SchemaDef.LOCATION));
			     	twoFieldPersistence.setLevel(rs.getString(SchemaDef.LEVEL));
			     	twoFieldPersistence.setEmployeeName(rs.getString(SchemaDef.EMPLOYENAMEE));
			       //twoFieldPersistence.setEmployeeID(rs.getString(SchemaDef.EMPLOYEEID));
			     	twoFieldPersistence.setEmailId(rs.getString(SchemaDef.EMP_EMAIL));
			     	twoFieldPersistence.setPhoneNo(rs.getString(SchemaDef.EMP_PHONENO));
			     	//twoFieldPersistence.setRoleDesc(rs.getString(SchemaDef.ROLES));
			     	twoFieldPersistence.setDescription(rs.getString("description"));
                    twoFieldPersistence.setStatus(rs.getString(SchemaDef.STATUS));
                    
                    twoFieldPersistence.setLocn(rs.getString(SchemaDef.LOCN));
                    twoFieldPersistence.setEmployeeID(rs.getString(SchemaDef.EMPLOYEEID));
		              return twoFieldPersistence;
		          }
			 
			        });
			      logger.info("getAlertGroupDetails method is compleated");
	
				return twoFieldPersistenceList;
	}
/*
 * (non-Javadoc)
 * @see com.hpcl.dao.ConfigurationDao#deleteAlertGroupDetails(com.hpcl.persistence.AppParameters, java.lang.String)
 */
	
	public void deleteAlertGroupDetails(AppParameters appParameters,String tableName1) {
       final String group_ID=SchemaDef.GROUPID;
		logger.info("deleteAlertGroupDetails method is started");
		logger.info("GroupId:"+appParameters.getGroupId() );
		logger.info("GroupName:"+appParameters.getGroupName() );
		logger.info("location:"+appParameters.getLocationID());
		logger.info("empID:"+appParameters.getEmployeeID() );
		logger.info("Level:"+appParameters.getLevel());
        try{
       	   String sql = "update "+tableName1+" set "+SchemaDef.STATUS+" ='"+SchemaDef.INACTIVE_FLAG+"',"+SchemaDef.EDITEDBY+"='"+appParameters.getEditedBy()+"' WHERE "+group_ID+"=? and GroupName=? and LocationId=? and EmployeeId=? and level=?";
	       logger.info("deleteAlertGroupDetails Query : "+sql); 
	       jdbcTemplate.update(sql, appParameters.getGroupId(),appParameters.getGroupName(),appParameters.getLocationID(),appParameters.getEmployeeID(),appParameters.getLevel());
	       logger.info("Id"+appParameters.getGroupId()); 
	      
		    }catch(Exception ex){
		        ex.printStackTrace();
		        logger.info(ex);
		        }
           logger.info("deleteAlertGroupDetails method is compleated");
        }
	/*
	 * (non-Javadoc)
	 * @see com.hpcl.dao.ConfigurationDao#getAllLocationEmployeesDetails(java.lang.String)
	 */
	
	public List<AppParameters> getAllLocationEmployeesDetails(String locationid) {
		    String sql=null;
            sql = "select e.EmployeeID,e.employeeName from m_employees e join m_locations l on l.LocationId='"+locationid+"' where l.LocationName=e.Location ";
	        logger.info("getAllLocationEmployeesDetails Query "+sql);
		      List<AppParameters> appParametersList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
		        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
		        	AppParameters appParameters = new AppParameters();
		         appParameters.setEmployeeID(rs.getString(SchemaDef.EMPLOYEEID));
		    	 appParameters.setEmployeeName(rs.getString(SchemaDef.EMPLOYENAMEE));
                 return appParameters;
	                }
		      });
		      logger.info("getAllDetails method is compleated");
		      return appParametersList;
         }
  /*
   * (non-Javadoc)
   * @see com.hpcl.dao.ConfigurationDao#getAllLocationDeviceDetails(java.lang.String)
   */
	
	public List<AppParameters> getAllLocationDeviceDetails(String locationid) {
		logger.info("Device inplementation started");
		
		String sql="select DeviceId,DeviceName from "+SchemaDef.M_DEVICE+" where locationId='"+locationid+"'";
		logger.info("getAllLocationDeviceDetails Query:"+sql);
        List<AppParameters> appParametersList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
        	AppParameters appParameters = new AppParameters();
            appParameters.setDeviceId(rs.getString("DeviceId"));
    		appParameters.setDeviceName(rs.getString("DeviceName"));
            return appParameters;
            }
           });
        logger.info("Device implementation end");
        return appParametersList;
	}
   /*
    * (non-Javadoc)
    * @see com.hpcl.dao.ConfigurationDao#getDateAndTimeSearch(java.lang.String, java.lang.String, java.lang.String)
    */
		   
	
	public List<AppParameters> getDateAndTimeSearch(String tableName,String fromdatetime, String todatetime,AppParameters appParameters) {
		 logger.info("getDateAndTimeSearch method is started");
		 String sql=null ;
		 List<AppParameters> appParametersList=null;
		 try{
          sql="SELECT mloc.LOCATIONNAME,alt.DEVICENAME,alt.EARTHPITNAME,alt.VOLTAGE,alt.RECEIVEDDATE FROM tr_dashboard alt,m_locations mloc where alt.location=mloc.locationId and alt.status='A' and ReceivedDate BETWEEN '"+fromdatetime+"' AND '"+todatetime+"';";
           logger.info("getDateAndTimeSearch method Qeury: "+sql);
	        appParametersList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
	        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	AppParameters appParameters = new AppParameters();
	    	
	    		appParameters.setLocationName(rs.getString("locationName"));
	    		appParameters.setDeviceName(rs.getString("deviceName"));
	    		appParameters.setEarthpitName(rs.getString("earthpitName"));
	    		appParameters.setVoltage(rs.getString("voltage"));
	    		appParameters.setReceivedDate(rs.getString("receivedDate"));
	    		return appParameters;
              }
	           });
		   }catch(Exception ex){ 
	        ex.printStackTrace();
	        logger.info(ex);
	         }
		    logger.info("getDateAndTimeSearch method is compleated");
			return appParametersList;
     }
   /*
    * (non-Javadoc)
    * @see com.hpcl.dao.ConfigurationDao#getDateAndTimeSearchFOrAlert(java.lang.String, java.lang.String, java.lang.String, com.hpcl.persistence.AppParameters)
    */
	
	public List<AppParameters> getDateAndTimeSearchFOrAlert(String tableName,String locid, String failureType,String fromdatetime, String todatetime, AppParameters appParameters) {
		logger.info("getDateAndTimeSearchFOrAlert method is started");
		 String sql=null ;
		 List<AppParameters> appParametersList=null;
		 logger.info("FailureType:"+failureType);
		 logger.info("LocationId:"+locid);
		 try{
			 if((locid.equals("0")) && (failureType.equals("0")))
			 {
				 sql="select distinct tr.id,loc.locationName,case when FailureType='Device' then dev.DeviceName when FailureType='Earthpit' then ert.earthpitName end as devicename,tr.DOF,es.EscalationType,IF(tr.MSD is null,sysdate(),MSD) as MSD,es.EscalationLevel,grp.GroupName,IF(tr.ActionTaken is null,'N/A',ActionTaken) as ActionTaken,IF(tr.ActionDate is null,'N/A',ActionDate) as ActionDate,IF(actiondate is null,DATEDIFF(sysdate(),tr.DOF),DATEDIFF(tr.actiondate,tr.DOF)) as Downtime,tr.RepeatTime,tr.actiondate,tr.status from tr_alertmanagment tr left join m_locations loc on ltrim(rtrim(tr.locationid)) = ltrim(rtrim(loc.locationid)) left join m_escalation es on ltrim(rtrim(tr.escalationid))=ltrim(rtrim(es.EscalationID)) left join m_groups grp on ltrim(rtrim(tr.groupid)) =ltrim(rtrim(grp.GroupId)) left join m_device dev on tr.EquipementID = dev.deviceId and tr.FailureType='Device' left join m_earthpits ert on tr.EquipementID = ert.earthpitid and tr.FailureType='Earthpit' WHERE  (date(tr.DOF) BETWEEN '"+fromdatetime+"' AND '"+todatetime+"')";
				 //sql="select distinct tr.id,loc.locationName,case when FailureType='Device' then dev.DeviceName when FailureType='Earthpit' then ert.earthpitName end as devicename,tr.DOF,es.EscalationType,IF(tr.MSD is null,sysdate(),MSD) as MSD,es.EscalationLevel,grp.GroupName,IF(tr.ActionTaken is null,'N/A',ActionTaken) as ActionTaken,IF(tr.ActionDate is null,'N/A',ActionDate) as ActionDate,IF(actiondate is null,DATEDIFF(sysdate(),tr.DOF),DATEDIFF(tr.actiondate,tr.DOF)) as Downtime,es.EscalationInterval,tr.actiondate,tr.status from tr_alertmanagment tr left join m_locations loc on ltrim(rtrim(tr.locationid)) = ltrim(rtrim(loc.locationid)) left join m_escalation es on ltrim(rtrim(tr.escalationid))=ltrim(rtrim(es.EscalationID)) left join m_groups grp on ltrim(rtrim(tr.groupid)) =ltrim(rtrim(grp.GroupId)) left join m_device dev on tr.EquipementID = dev.deviceId and tr.FailureType='Device' left join m_earthpits ert on tr.EquipementID = ert.earthpitid and tr.FailureType='Earthpit' WHERE  (date(tr.DOF) BETWEEN '"+fromdatetime+"' AND '"+todatetime+"')";
		           logger.info("getSearchfilters with only Date Range: "+sql);
			 }
			 else if(locid!=("0") && (failureType.equals("0")))
			 {
				 sql="select distinct tr.id,loc.locationName,case when FailureType='Device' then dev.DeviceName when FailureType='Earthpit' then ert.earthpitName end as devicename,tr.DOF,es.EscalationType,IF(tr.MSD is null,sysdate(),MSD) as MSD,es.EscalationLevel,grp.GroupName,IF(tr.ActionTaken is null,'N/A',ActionTaken) as ActionTaken,IF(tr.ActionDate is null,'N/A',ActionDate) as ActionDate,IF(actiondate is null,DATEDIFF(sysdate(),tr.DOF),DATEDIFF(tr.actiondate,tr.DOF)) as Downtime,tr.RepeatTime,tr.actiondate,tr.status from tr_alertmanagment tr left join m_locations loc on ltrim(rtrim(tr.locationid)) = ltrim(rtrim(loc.locationid)) left join m_escalation es on ltrim(rtrim(tr.escalationid))=ltrim(rtrim(es.EscalationID)) left join m_groups grp on ltrim(rtrim(tr.groupid)) =ltrim(rtrim(grp.GroupId)) left join m_device dev on tr.EquipementID = dev.deviceId and tr.FailureType='Device' left join m_earthpits ert on tr.EquipementID = ert.earthpitid and tr.FailureType='Earthpit' WHERE (date(tr.DOF) BETWEEN '"+fromdatetime+"' AND '"+todatetime+"')";
				   logger.info("getSearchfilters with daterange and location: "+sql);
			 }
			 else
			 {
				 sql="select distinct tr.id,loc.locationName,case when FailureType='Device' then dev.DeviceName when FailureType='Earthpit' then ert.earthpitName end as devicename,tr.DOF,es.EscalationType,IF(tr.MSD is null,sysdate(),MSD) as MSD,es.EscalationLevel,grp.GroupName,IF(tr.ActionTaken is null,'N/A',ActionTaken) as ActionTaken,IF(tr.ActionDate is null,'N/A',ActionDate) as ActionDate,IF(actiondate is null,DATEDIFF(sysdate(),tr.DOF),DATEDIFF(tr.actiondate,tr.DOF)) as Downtime,tr.RepeatTime,tr.actiondate,tr.status from tr_alertmanagment tr left join m_locations loc on ltrim(rtrim(tr.locationid)) = ltrim(rtrim(loc.locationid)) left join m_escalation es on ltrim(rtrim(tr.escalationid))=ltrim(rtrim(es.EscalationID)) left join m_groups grp on ltrim(rtrim(tr.groupid)) =ltrim(rtrim(grp.GroupId)) left join m_device dev on tr.EquipementID = dev.deviceId and tr.FailureType='Device' left join m_earthpits ert on tr.EquipementID = ert.earthpitid and tr.FailureType='Earthpit' WHERE  tr.LocationId='"+locid+"'AND tr.FailureType='"+failureType+"'AND (date(tr.DOF) BETWEEN '"+fromdatetime+"' AND '"+todatetime+"')";
			       logger.info("getSearchfilters with daterange, location and devices: "+sql);
			 }

			 //sql=" select tr.id,loc.locationName,IF(FailureType='Device',(select dev.DeviceName from m_device dev, tr_alertmanagment alt where alt.EquipementID = dev.deviceId),(select ert.earthpitName from m_earthpits ert, tr_alertmanagment alt where alt.EquipementID = ert.earthpitid)) as devicename,tr.DOF,tr.EscalationID,IF(tr.MSD is null,sysdate(),MSD) as MSD,es.EscalationLevel,grp.GroupName,IF(tr.ActionTaken is null,'N/A',ActionTaken) as ActionTaken,IF(tr.ActionDate is null,'N/A',ActionDate) as ActionDate,IF(actiondate is null,DATEDIFF(sysdate(),tr.DOF),DATEDIFF(tr.actiondate,tr.DOF)) as Downtime,es.EscalationInterval,tr.actiondate,tr.status from tr_alertmanagment tr, m_locations loc, m_escalation es, m_groups grp WHERE tr.locationid=loc.locationid AND tr.escalationid=es.EscalationID and tr.groupid =grp.GroupId AND DOF BETWEEN '"+fromdatetime+"' AND '"+todatetime+"'";
		      //sql="select tr.id,loc.locationName,dev.devicename,tr.dof,tr.escalationid,tr.msd,es.EscalationLevel,grp.GroupName,tr.actiontaken,tr.ActionDate,DATEDIFF(tr.actiondate,tr.dof),es.EscalationInterval,tr.actiondate,tr.status from tr_alertmanagment tr,m_locations loc,m_device dev,m_escalation es, m_groups grp WHERE tr.locationid=loc.locationid AND dev.deviceid=tr.EquipementID AND tr.escalationid=es.EscalationID AND tr.groupid = grp.GroupId AND DOF BETWEEN '"+fromdatetime+"' AND '"+todatetime+"';";
			 //sql="select tr.id,loc.locationName,case when FailureType='Device' then dev.DeviceName when FailureType='Earthpit' then ert.earthpitName end as devicename,tr.DOF,es.EscalationType,IF(tr.MSD is null,sysdate(),MSD) as MSD,es.EscalationLevel,grp.GroupName,IF(tr.ActionTaken is null,'N/A',ActionTaken) as ActionTaken,IF(tr.ActionDate is null,'N/A',ActionDate) as ActionDate,IF(actiondate is null,DATEDIFF(sysdate(),tr.DOF),DATEDIFF(tr.actiondate,tr.DOF)) as Downtime,es.EscalationInterval,tr.actiondate,tr.status from tr_alertmanagment tr left join m_locations loc on ltrim(rtrim(tr.locationid)) = ltrim(rtrim(loc.locationid)) left join m_escalation es on ltrim(rtrim(tr.escalationid))=ltrim(rtrim(es.EscalationID)) left join m_groups grp on ltrim(rtrim(tr.groupid)) =ltrim(rtrim(grp.GroupId)) left join m_device dev on tr.EquipementID = dev.deviceId and tr.FailureType='Device' left join m_earthpits ert on tr.EquipementID = ert.earthpitid and tr.FailureType='Earthpit' WHERE (date(tr.DOF) BETWEEN '"+fromdatetime+"' AND '"+todatetime+"')";

	        appParametersList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
	        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	AppParameters appParameters = new AppParameters();
	    	
	        	appParameters.setId(rs.getString("Id"));
	    		appParameters.setLocationName(rs.getString("locationName"));
	    		appParameters.setFailureType(rs.getString("devicename"));
		    	appParameters.setdOF(rs.getString("DOF"));
	    		//appParameters.setEscalationIDs(rs.getString("EscalationID"));
		    	appParameters.setEscalationTypes(rs.getString("EscalationType"));
	    		appParameters.setmSD(rs.getString("MSD"));
	    		appParameters.setEscalationLevels(rs.getString("EscalationLevel"));
	    		appParameters.setGroupName(rs.getString("GroupName"));
	    		appParameters.setActionTaken(rs.getString("ActionTaken"));
	    		appParameters.setActionDate(rs.getString("ActionDate"));
	    		appParameters.setDiffTime(rs.getString(11));
	    		//appParameters.setRepeatTime(rs.getString("EscalationInterval"));
	    		appParameters.setRepeatTime(rs.getString("RepeatTime"));
	    		appParameters.setStatus(rs.getString("Status"));
	    	return appParameters;
             }
	           });
		 }catch(Exception ex){ 
	        ex.printStackTrace();
	        logger.info(ex);
	         }
		    logger.info("getDateAndTimeSearchFOrAlert method is compleated");
			return appParametersList;

	}
/*
 * (non-Javadoc) for search  location fileds in employe page.
 * @see com.hpcl.dao.ConfigurationDao#getEmployeeLocations(java.lang.String, java.lang.String)
 */
	
	public List<AppParameters> getEmployeeLocations(String tableName,String location) {
		logger.info("getAllDetails method is started");
		 String sql = "SELECT "+SchemaDef.EMPLOYEEID+","+SchemaDef.EMPLOYENAMEE+","+SchemaDef.LACATION+","+SchemaDef.ROLES+","+SchemaDef.STATUS+" FROM "+tableName+"  where status='"+SchemaDef.ACTIVE_FLAG+"' and "+SchemaDef.LOCATION+"='"+location+"'";
		 	
		 	 logger.info("etEmployeeLocations Query "+sql);
		      List<AppParameters> appParametersList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
		        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
		        AppParameters appParameters = new AppParameters();
		    	appParameters.setEmployeeID(rs.getString(SchemaDef.EMPLOYEEID));
		    	appParameters.setEmployeeName(rs.getString("employeeName"));
		    	appParameters.setLocation(rs.getString("location"));
		    	appParameters.setRoles(rs.getString("roles"));
		    	appParameters.setStatus(rs.getString("status"));
	            return appParameters;
	                }
		 
		        });
		      logger.info("getAllDetails method is compleated");
		      return appParametersList;
}
/*
 * (non-Javadoc) for location search field for device page.
 * @see com.hpcl.dao.ConfigurationDao#getDeviceLocations(java.lang.String, java.lang.String)
 */
	
	public List<AppParameters> getDeviceLocations(String tableName,String location) {
		
		logger.info("getDeviceLocations(Device page search field) method is started");
		   String sql=null;
		  sql="SELECT d.deviceId,d.deviceName,d.IPAddress,m.LocationId,m.Locationname,d.earthpitvalues,d.timeinterval,d.installedDate,d.deviceSno,d.status FROM m_device d, m_locations m  where m.LocationId=d.locationId and "+SchemaDef.LOCATIONNAME+"='"+location+"'";
		  
		          logger.info("getDeviceLocations Query "+sql);
			        List<AppParameters> twoFieldPersistenceList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
			        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
			    	AppParameters twoFieldPersistence = new AppParameters();
			    	twoFieldPersistence.setDeviceId(rs.getString(SchemaDef.DEVICE_ID));
			    	twoFieldPersistence.setDeviceName(rs.getString(SchemaDef.DEVICE_NAME));
			     	twoFieldPersistence.setIpAddress(rs.getString(SchemaDef.IPADDRESS));
			     	twoFieldPersistence.setLocationID(rs.getString(SchemaDef.LOCATIONID));
			     	twoFieldPersistence.setLocationName(rs.getString(SchemaDef.LOCATIONNAME));
			     	twoFieldPersistence.setEarthPitValues(rs.getString(SchemaDef.EARTHPIT_VALUES));
			     	twoFieldPersistence.setTimeInterval(rs.getString(SchemaDef.TIME_INTERVAL));
			     	twoFieldPersistence.setInstalledDate(rs.getString(SchemaDef.INSTALL_DATE));
			     	twoFieldPersistence.setDeviceSno(rs.getString(SchemaDef.DEVICE_SNO));
			    	twoFieldPersistence.setStatus(rs.getString(SchemaDef.STATUS));
		            return twoFieldPersistence;
		          }
			 
			        });
			      logger.info("(getDeviceLocations(Device page search field) compleated");
	
				return twoFieldPersistenceList;
	
    }
  /*
   * (non-Javadoc) for earthpit page location search
   * @see com.hpcl.dao.ConfigurationDao#getEarthpitLocations(java.lang.String, java.lang.String)
   */
	
	public List<AppParameters> getEarthpitLocations(String tableName,String location) {

		logger.info("getEarthpitLocations(Earthpit search field) method is started");
		   String sql=null;
		 	  sql="SELECT e.earthpitid,e.earthpitname,e.locationid,m.locationname,e.status FROM m_earthpits e,m_locations m where e.LocationId=m.LocationId and "+SchemaDef.LOCATIONNAME+"='"+location+"'";
		 logger.info("getEarthpitLocations Query "+sql);
			    List<AppParameters> twoFieldPersistenceList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
			        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
			    	AppParameters twoFieldPersistence = new AppParameters();
			    	twoFieldPersistence.setEarthpitID(rs.getString(SchemaDef.EARTHPIT_ID));
			    	twoFieldPersistence.setEarthpitName(rs.getString(SchemaDef.EARTHPIT_NAME));
			     	twoFieldPersistence.setLocationID(rs.getString(SchemaDef.LOCATIONID));
			     	twoFieldPersistence.setLocationName(rs.getString(SchemaDef.LOCATIONNAME));
			        twoFieldPersistence.setStatus(rs.getString(SchemaDef.STATUS));
		                return twoFieldPersistence;
		                }
			  });
			      logger.info("getEarthpitLocations(Earthpit search field) method is compleated");
	   return twoFieldPersistenceList;
}
    /*
     * (non-Javadoc) for device-earthpit page location search page.
     * @see com.hpcl.dao.ConfigurationDao#getDeviceEarthpitLocations(java.lang.String, java.lang.String)
     */
	 
		public List<AppParameters> getDeviceEarthpitLocations(String tableName,String location) {
			logger.info("getDeviceEarthpitLocations(Search field) method is started");
			   String sql=null;
			            sql="select m.deviceearthpitId,m.status,m.locationid,l.LocationName ,m.deviceid,d.devicename,m.earthpitid,e.earthpitname from m_device_earthpits m  join m_locations l on m.locationid=l.locationid join m_device d on m.deviceid=d.DeviceId join m_earthpits e on m.earthpitid=e.earthpitid where m.status='A' and l.LocationName='"+location+"'";
			logger.info("getDeviceEarthpitLocations Query "+sql);
			 	       List<AppParameters> twoFieldPersistenceList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
				        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
				    	AppParameters twoFieldPersistence = new AppParameters();
				    	twoFieldPersistence.setEarthpitID(rs.getString(SchemaDef.EARTHPIT_ID));
				    	twoFieldPersistence.setEarthpitName(rs.getString(SchemaDef.EARTHPIT_NAME));
				     	twoFieldPersistence.setLocationID(rs.getString(SchemaDef.LOCATIONID));
				     	twoFieldPersistence.setLocationName(rs.getString(SchemaDef.LOCATIONNAME));
				     	twoFieldPersistence.setDeviceId(rs.getString(SchemaDef.DEVICE_ID));
				     	twoFieldPersistence.setDeviceName(rs.getString(SchemaDef.DEVICE_NAME));
				        twoFieldPersistence.setStatus(rs.getString(SchemaDef.STATUS));
			                return twoFieldPersistence;
			                }
				 
				        });
	      logger.info("getDeviceEarthpitLocations(Search field) method is compleated");
		  return twoFieldPersistenceList;
		}
	 /*
	  * (non-Javadoc)
	  * @see com.hpcl.dao.ConfigurationDao#getDeviceEarthpitLocationsDetails(java.lang.String, java.lang.String, java.lang.String)
	  */
		
		public List<AppParameters> getDeviceEarthpitLocationsDetails(String tableName,String location,String divice) {
			logger.info("getDeviceEarthpitLocations(Search field) method is started");
			   String sql=null;
			            sql="select m.deviceearthpitId,m.status,m.locationid,l.LocationName ,m.deviceid,d.devicename,m.earthpitid,e.earthpitname from m_device_earthpits m  join m_locations l on m.locationid=l.locationid join m_device d on m.deviceid=d.DeviceId join m_earthpits e on m.earthpitid=e.earthpitid where m.status='A' and l.LocationName='"+location+"' and d.devicename='"+divice+"'";
			 	       logger.info("getDeviceEarthpitLocationsDetails Query "+sql);
			 	       List<AppParameters> twoFieldPersistenceList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
				        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
				    	AppParameters twoFieldPersistence = new AppParameters();
				    	twoFieldPersistence.setEarthpitID(rs.getString(SchemaDef.EARTHPIT_ID));
				    	twoFieldPersistence.setEarthpitName(rs.getString(SchemaDef.EARTHPIT_NAME));
				     	twoFieldPersistence.setLocationID(rs.getString(SchemaDef.LOCATIONID));
				     	twoFieldPersistence.setLocationName(rs.getString(SchemaDef.LOCATIONNAME));
				     	twoFieldPersistence.setDeviceId(rs.getString(SchemaDef.DEVICE_ID));
				     	twoFieldPersistence.setDeviceName(rs.getString(SchemaDef.DEVICE_NAME));
				        twoFieldPersistence.setStatus(rs.getString(SchemaDef.STATUS));
			                return twoFieldPersistence;
			                }
				       });
			logger.info("getDeviceEarthpitLocations(Search field) method is compleated");
		   return twoFieldPersistenceList;
		}

//getOnlyDasboardSearchfilters  search filter implementation method written by naveen on 21-11-2015
	
	    public List<AppParameters> getDasboardSearchfilters(String tableName, String locid, String deviceid, String fromdatetime, String todatetime, AppParameters appParameters,String emprole,String emplocid,String userId ) {
		logger.info("DasboardSearchfilters method is started");
		 String sql=null ;
		
		 locid=appParameters.getLocationID();
		 deviceid=appParameters.getDeviceId();
		 
		 logger.info("Location id in Dashboard: "+locid);
		 logger.info("Device id in Dashboard:"+deviceid);
		 
		 List<AppParameters> appParametersList=null;
		 try{
			 
			 if(userId.equals("admin")){
				 if((locid.equals("0")) && (deviceid.equals("0")))
				 {
				  
			       sql ="SELECT mloca.LocationName, dev.DeviceName, ert.earthpitName, dabord.ReceivedDate, dabord.Voltage FROM tr_dashboard dabord, m_device dev, m_earthpits ert, m_locations mloca where dabord.DeviceId = dev.DeviceId and  dabord.EarthpitId= ert.earthpitId and dabord.Location = mloca.locationId and dabord.Status='A' and  (dabord.ReceivedDate between '"+fromdatetime+"' AND '"+todatetime+"') order by dabord.ReceivedDate,dev.DeviceId Desc;";
					 logger.info("Admin user getSearchfilters with only Date Range: "+sql);
				 }
				 else if((locid!="0") && (deviceid.equals("0")))
				 {
					
					 sql ="SELECT mloca.LocationName, dev.DeviceName, ert.earthpitName, dabord.ReceivedDate, dabord.Voltage FROM tr_dashboard dabord, m_device dev, m_earthpits ert, m_locations mloca where dabord.DeviceId = dev.DeviceId and  dabord.EarthpitId= ert.earthpitId and dabord.Location = mloca.locationId  and dabord.Status='A' and (dabord.ReceivedDate between '"+fromdatetime+"' AND '"+todatetime+"') and dabord.Location = '"+locid+"' order by dabord.ReceivedDate,dev.DeviceId Desc;";
					 logger.info("Admin user getSearchfilters with daterange and location: "+sql);
				 }
				 else
				 {   
					 sql ="SELECT mloca.LocationName, dev.DeviceName, ert.earthpitName, dabord.ReceivedDate, dabord.Voltage FROM tr_dashboard dabord, m_device dev, m_earthpits ert, m_locations mloca where dabord.DeviceId = dev.DeviceId and  dabord.EarthpitId= ert.earthpitId and dabord.Location = mloca.locationId and dabord.Status='A' and (dabord.ReceivedDate between '"+fromdatetime+"' AND '"+todatetime+"') and dabord.Location = '"+locid+"' and dabord.DeviceId = '"+deviceid+"' order by dabord.ReceivedDate,dev.DeviceId Desc;";
					 logger.info("Admin user getSearchfilters with daterange, location and devices: "+sql);
				 }
				
			 }else{
				 if((locid.equals("0")) && (deviceid.equals("0")))
				 {
				  
			       sql ="SELECT mloca.LocationName, dev.DeviceName, ert.earthpitName, dabord.ReceivedDate, dabord.Voltage FROM tr_dashboard dabord, m_device dev, m_earthpits ert, m_locations mloca where dabord.DeviceId = dev.DeviceId and  dabord.EarthpitId= ert.earthpitId and dabord.Location = mloca.locationId and dabord.Status='A' and (dabord.ReceivedDate between '"+fromdatetime+"' AND '"+todatetime+"') and dabord.Location = '"+emplocid+"' order by dabord.ReceivedDate,dev.DeviceId Desc;";
					 logger.info("Local User/Admin role getSearchfilters with only Date Range: "+sql);
				 }
				 else if((locid!="0") && (deviceid.equals("0")))
				 {
					
					 sql ="SELECT mloca.LocationName, dev.DeviceName, ert.earthpitName, dabord.ReceivedDate, dabord.Voltage FROM tr_dashboard dabord, m_device dev, m_earthpits ert, m_locations mloca where dabord.DeviceId = dev.DeviceId and  dabord.EarthpitId= ert.earthpitId and dabord.Location = mloca.locationId and dabord.Status='A' and (dabord.ReceivedDate between '"+fromdatetime+"' AND '"+todatetime+"') and dabord.Location = '"+emplocid+"' order by dabord.ReceivedDate,dev.DeviceId Desc;";
					 logger.info("Local User/Admin role getSearchfilters with daterange and location: "+sql);
				 }
				 else
				 {   
					 sql ="SELECT mloca.LocationName, dev.DeviceName, ert.earthpitName, dabord.ReceivedDate, dabord.Voltage FROM tr_dashboard dabord, m_device dev, m_earthpits ert, m_locations mloca where dabord.DeviceId = dev.DeviceId and  dabord.EarthpitId= ert.earthpitId and dabord.Location = mloca.locationId and dabord.Status='A' and (dabord.ReceivedDate between '"+fromdatetime+"' AND '"+todatetime+"') and dabord.Location = '"+emplocid+"' and dabord.DeviceId = '"+deviceid+"' order by dabord.ReceivedDate,dev.DeviceId Desc;";
					 logger.info("Local User/Admin role getSearchfilters with daterange, location and devices: "+sql);
				 } 
			 }
	        appParametersList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
	        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	AppParameters appParameters = new AppParameters();
	    	    appParameters.setLocationName(rs.getString("locationName"));
	    		appParameters.setDeviceName(rs.getString("deviceName"));
	    		appParameters.setEarthpitName(rs.getString("earthpitName"));
	    		appParameters.setVoltage(rs.getString("voltage"));
	    		appParameters.setReceivedDate(rs.getString("receivedDate"));	    		
	           
	    	return appParameters;	    	
             }
	           });
		 }catch(Exception ex){ 
	        ex.printStackTrace();
	        logger.info(ex);
	         }
		    logger.info("DasboardSearchfilters method is compleated");
			return appParametersList;
	}
 /*
  * (non-Javadoc)
  * @see com.hpcl.dao.ConfigurationDao#getOnlyDasboardSearchfilters(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.hpcl.persistence.AppParameters)
  */
	
	public List<AppParameters> getOnlyDasboardSearchfilters(String tableName,
			String locid, String deviceid, String fromdatetime,
			String todatetime, AppParameters appParameters) {
		logger.info("Only DasboardSearchfilters method is started");
		 String sql=null ;
		 locid=appParameters.getLocationID();
		 deviceid=appParameters.getDeviceId();
		 
		 logger.info("Location id "+locid);
		 logger.info("Device id "+deviceid);
		 
		 List<AppParameters> appParametersList=null;
		 try{
			if((locid.equals("0")) && (deviceid.equals("0")))
			 {
			  sql="SELECT loc.LocationName,dash.deviceName,dash.earthpitname,dash.voltage,dash.ReceivedDate FROM tr_dashboard dash, m_locations loc  WHERE dash.Location = loc.LocationId and dash.ReceivedDate BETWEEN '"+fromdatetime+"' AND '"+todatetime+"';";
		      logger.info("getSearchfilters with only Date Range: "+sql);
			 }
			 else if(locid!="0")
			 {
			      sql="SELECT loc.LocationName,dash.deviceName,dash.earthpitname,dash.voltage,dash.ReceivedDate FROM tr_dashboard dash, m_locations loc WHERE dash.Location = loc.LocationId and dash.Location ='"+locid+"' AND  dash.ReceivedDate BETWEEN '"+fromdatetime+"' AND '"+todatetime+"';";
				 //sql ="SELECT mloca.LocationName, dev.DeviceName, ert.earthpitName, dabord.ReceivedDate, dabord.Voltage FROM tr_dashboard dabord, m_device dev, m_earthpits ert, m_locations mloca where dabord.DeviceId = dev.DeviceId and  dabord.EarthpitId= ert.earthpitId and dabord.Location = mloca.locationId and (date(dabord.ReceivedDate) between '"+fromdatetime+"' AND '"+todatetime+"') and dabord.Location = '"+locid+"' and dabord.DeviceId = '"+deviceid+"';"; 
				 logger.info("getSearchfilters with daterange and location: "+sql);
			 }
			 else
			 {   sql="SELECT loc.LocationName,dash.deviceName,dash.earthpitname,dash.voltage,dash.ReceivedDate FROM tr_dashboard dash, m_locations loc WHERE dash.Location ='"+locid+"' AND dash.DeviceId ='"+deviceid+"' AND dash.ReceivedDate BETWEEN '"+fromdatetime+"' AND '"+todatetime+"';";
				 logger.info("getSearchfilters with daterange, location and devices: "+sql);
			 }
		 
           logger.info("***: "+sql);
	        appParametersList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
	        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	AppParameters appParameters = new AppParameters();
	    	
	    		appParameters.setLocationName(rs.getString("locationName"));
	    		appParameters.setDeviceName(rs.getString("deviceName"));
	    		appParameters.setEarthpitName(rs.getString("earthpitName"));
	    		appParameters.setVoltage(rs.getString("voltage"));
	    		appParameters.setReceivedDate(rs.getString("receivedDate"));
	    	return appParameters;
            }
	           });
		 }catch(Exception ex){ 
	        ex.printStackTrace();
	        logger.info(ex);
	         }
		    logger.info("getDateAndTimeSearch method is compleated");
			return appParametersList;
	}
	/*
	 * (non-Javadoc)
	 * @see com.hpcl.dao.ConfigurationDao#getEmployeeDetails(java.lang.String, com.hpcl.persistence.AppParameters)
	 */
	
	public List<AppParameters> getEmployeeDetails(String tableName, AppParameters appParameters) {
		logger.info("getEmployeeDetails method is started");
		   String sql=null;
		            sql="select EmployeeID,EmployeeName from m_employees where Locn='"+appParameters.getLocationID()+"' and status='A'";
		 	        logger.info("getEmployeeDetails Query "+sql);
		 	       List<AppParameters> twoFieldPersistenceList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
			        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
			    	AppParameters twoFieldPersistence = new AppParameters();
			    	twoFieldPersistence.setEmployeeID(rs.getString(SchemaDef.EMPLOYEEID));
			    	twoFieldPersistence.setEmployeeName(rs.getString(SchemaDef.EMPLOYENAMEE));
		                return twoFieldPersistence;
		                }
			       });
			      logger.info("getEmployeeDetails method is compleated");
	    return twoFieldPersistenceList;
	}
	
	public List<AppParameters> getEmployeeGroupDetails(String tableName, AppParameters appParameters) {
		logger.info("getEmployeeDetails method is started");
		   String sql=null;
		   
		            //sql="select EmployeeID,EmployeeName from m_employees where Location='"+appParameters.getLocationID()+"' and status='A'";
		 	      //  sql="SELECT t2.EmployeeID,t2.EmployeeName FROM m_groups t1 LEFT JOIN m_employees t2 ON t2.EmployeeID != t1.EmployeeID  WHERE t2.EmployeeID IS NULL and t1.locationId='"+appParameters.getLocationID()+"' and t2.status='A'";
		           
		 	        sql="SELECT EmployeeID,EmployeeName FROM m_employees WHERE EmployeeID NOT IN (SELECT EmployeeID FROM m_groups where locationId='"+appParameters.getLocationID()+"' and level='"+appParameters.getLevel()+"' and GroupId='"+appParameters.getGroupId()+"' and status='A' ) and Locn='"+appParameters.getLocationID()+"' and m_employees.status='A'";
		 	        
		 	        logger.info("getEmployeeDetails Query "+sql);
		 	       List<AppParameters> twoFieldPersistenceList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
			        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
			    	AppParameters twoFieldPersistence = new AppParameters();
			    	twoFieldPersistence.setEmployeeID(rs.getString(SchemaDef.EMPLOYEEID));
			    	twoFieldPersistence.setEmployeeName(rs.getString(SchemaDef.EMPLOYENAMEE));
		                return twoFieldPersistence;
		                }
			       });
			      logger.info("getEmployeeDetails method is compleated");
	    return twoFieldPersistenceList;
	}
 /*
  * (non-Javadoc)
  * @see com.hpcl.dao.ConfigurationDao#getAppsetting(com.hpcl.persistence.AppParameters)
  */
	
	public List<AppParameters> getAppsetting(AppParameters appParameters) {
		logger.info("getAppsetting method is started");
		   String sql=null;
		            sql="SELECT MaxDevices,Earthpit FROM m_appsetting where status='A'";
		 	     logger.info("getAppsetting Query "+sql);
		 	       List<AppParameters> twoFieldPersistenceList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
			        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
			    	AppParameters twoFieldPersistence = new AppParameters();
			    	twoFieldPersistence.setMaxDevices(rs.getString("maxDevices"));
			    	twoFieldPersistence.setEarthpit(rs.getString("earthpit"));
			    	return twoFieldPersistence;
		                }
			      });
			      logger.info("getAppsetting method is compleated");
	
				return twoFieldPersistenceList;
           }
  /*
   * (non-Javadoc)
   * @see com.hpcl.dao.ConfigurationDao#getEmployeeID(java.lang.String)
   */
	
	public String getEmployeeID(String employeeID) {
		String query = "select " + SchemaDef.EMPLOYEEID + " from " + SchemaDef.M_GROUPS + " where " + SchemaDef.EMPLOYEEID+ "=?";
        final AppParameters appParameters = new AppParameters();
		RowCallbackHandler callback = new RowCallbackHandler() {
         public void processRow(ResultSet rs) throws SQLException {
		appParameters.setEmployeeID(rs.getString(SchemaDef.EMPLOYEEID));
			}
		};
     jdbcTemplate.query(query, callback, employeeID);
		logger.info("getEmployeeID Query : "+query);
		logger.info("Employee ID" + appParameters.getEmployeeID());
		return appParameters.getEmployeeID();
	}
  /*
   * (non-Javadoc)
   * @see com.hpcl.dao.ConfigurationDao#updateGroups(com.hpcl.persistence.AppParameters, java.lang.String)
   */
	
	public void updateGroups(AppParameters appParameters, String tableName) {
		logger.info("updateGroups method started");
		logger.info("employeeID:"+ appParameters.getEmployeeID());
		logger.info("groupID:"+ appParameters.getGroupId());
		String query="update "+tableName+" set locationid='"+appParameters.getLocationID()+"', level='"+appParameters.getLevel()+"',employeeID='"+appParameters.getEmployeeID()+"' where groupId=? and employeeid=?";
		logger.info("updateGroups method query"+query);
		jdbcTemplate.update(query,appParameters.getGroupId(), appParameters.getEmployeeID());
		logger.info("updateGroups method end");
	}
	/*logger.info("saveEarthptsDetails method is started");		    
	   try{
		   String graphLocName ="select loc.locationName from m_locations loc, m_earthpits ep where ep.locationId = loc.LocationId and loc.status ='A' and ep.locationId= '"+appParameters.getLocationID()+"'";
		   logger.info("Display location name of graph Query : "+graphLocName);
		   jdbcTemplate.update(graphLocName);
    String query="insert into "+tableName+" ("+SchemaDef.LOCATIONID+","+SchemaDef.EARTHPIT_NAME+",graphLegend,"+SchemaDef.STATUS+","+SchemaDef.CREATEDBY+") values('"+appParameters.getLocationID()+"','"+appParameters.getEarthpitName()+"','"+graphLocName+"','"+appParameters.getStatus()+"','"+appParameters.getCreatedBy()+"')";  
    logger.info("saveEarthptsDetails Query : "+query);
    jdbcTemplate.update(query); 
     }catch(Exception ex){
     ex.printStackTrace();
     logger.info(ex);
      }
	   logger.info("saveEarthptsDetails method is compleated");
	
   }*/
}