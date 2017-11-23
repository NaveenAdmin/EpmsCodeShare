
/**
* @author  :Prasad
* @version :1.0
* @since   2015-10-09
* @ModifiedDate	:2015-11-02
*/
package com.hpcl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.hpcl.persistence.AppParameters;
import com.hpcl.utill.CurrentDateTime;
import com.hpcl.utill.SchemaDef;

public class AdministrationDaoImpl implements AdministrationDao {
private static final Logger logger = Logger.getLogger(AdministrationDaoImpl.class);
	private JdbcTemplate jdbcTemplate; 
	@Autowired
    AdministrationDao twoFieldDao;
	
	public void setJdbcTemplate(DataSource dataSource) 
	{  
		 jdbcTemplate = new JdbcTemplate(dataSource);
	} 
	
	/*
	 * (non-Javadoc)
	 * @see com.hpcl.dao.TwoFieldDao#getAllDetails(java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<AppParameters> getAllDetails(final String tableName,String id,String status,String location,String userId) 
	{
		logger.info("getAllDetails method Begins");
		String sql=null;
		logger.info("getAllDetails status :" +status);
		if(status!=null)
		{
			if(status.equals("A"))
			{
				logger.info("Activemethod status :" +status);
				sql = "SELECT "+id+","+SchemaDef.DESCRIPTION+","+SchemaDef.STATUS+" FROM "+tableName+"  where status='"+SchemaDef.ACTIVE_FLAG+"' ORDER BY "+id+" DESC";
				logger.info("Active method query :" +sql);
			}
			else if(status.equals("I"))
			{
				logger.info("Inactive method status :" +status);		 			
			 	sql = "SELECT "+id+","+SchemaDef.DESCRIPTION+","+SchemaDef.STATUS+" FROM "+tableName+"  where status='"+SchemaDef.INACTIVE_FLAG+"' ORDER BY "+id+" DESC";
			 	logger.info("Inactive method query :" +sql);
			 }
			else if(status.equals("all"))
			{
				logger.info("All method status :" +status);			 		
				sql = "SELECT "+id+","+SchemaDef.DESCRIPTION+","+SchemaDef.STATUS+" FROM "+tableName+" ORDER BY "+id+" DESC";
				logger.info("All status query :" +sql);
			}
		}
		else
		{
			logger.info("default status :" +status);
		 	sql = "SELECT "+id+","+SchemaDef.DESCRIPTION+","+SchemaDef.STATUS+" FROM "+tableName+"  where status='"+SchemaDef.ACTIVE_FLAG+"' ORDER BY "+id+" DESC";
		 	logger.info("default Loading Query :" +sql);
		}		 	
		List<AppParameters> twoFieldPersistenceList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() 
		{
			public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				AppParameters twoFieldPersistence = new AppParameters();
				if(tableName.equals(SchemaDef.ROLE))
				{
					twoFieldPersistence.setRoleID(rs.getInt(SchemaDef.ROLEID));
				}
				else if(tableName.equals(SchemaDef.MODULE))
				{
					twoFieldPersistence.setModuleID(rs.getInt(SchemaDef.MODULEID));
				}
				else if(tableName.equals(SchemaDef.ACTION))
				{
					twoFieldPersistence.setActionID(rs.getInt(SchemaDef.ACTIONID));
				}
				else if(tableName.equals(SchemaDef.CATEGORY))
				{
					twoFieldPersistence.setCategoryID(rs.getInt(SchemaDef.CATEGORYID));
				}
				else if(tableName.equals(SchemaDef.ZONE))
				{
					twoFieldPersistence.setZoneID(rs.getInt(SchemaDef.ZONEID));
				}
				else if(tableName.equals(SchemaDef.USERTYPE))
				{
					twoFieldPersistence.setUserTypeID(rs.getInt(SchemaDef.USERTYPEID));
				}
				twoFieldPersistence.setDescription(rs.getString("description"));
				twoFieldPersistence.setStatus(rs.getString("status"));
				return twoFieldPersistence;
			}		
		});
		logger.info("getAllDetails method is Completed");
		return twoFieldPersistenceList;
	}
	/*
	 * (non-Javadoc)
	 * @see com.hpcl.dao.TwoFieldDao#saveDetails(com.hpcl.persistence.TwoFieldPersistence, java.lang.String)
	 */
	
	public void saveDetails(AppParameters twoFieldPersistence,String tableName)
	{
		logger.info("saveDetails method Begins");
		try
		{
			String query="insert into "+tableName+" ("+SchemaDef.DESCRIPTION+","+SchemaDef.STATUS+","+SchemaDef.CREATEDBY+") values('"+twoFieldPersistence.getDescription()+"','"+twoFieldPersistence.getStatus()+"','"+twoFieldPersistence.getCreatedBy()+"')";
			//String query="insert into "+tableName+" ("+SchemaDef.DESCRIPTION+","+SchemaDef.STATUS+","+SchemaDef.CREATEDBY+","+SchemaDef.CREATEDON+") values('"+twoFieldPersistence.getDescription()+"','"+twoFieldPersistence.getStatus()+"','"+twoFieldPersistence.getCreatedBy()+"',TO_DATE('"+twoFieldPersistence.getCreatedOn()+"','yyyy/mm/dd hh24:mi:ss'))";  
			jdbcTemplate.update(query); 	     
			logger.info("saveDetails Query :" +query);
		}
		catch(Exception ex)
		{
		   ex.printStackTrace();
		   logger.info(ex);
		}
		logger.info("saveDetails method is completed");
	}
	/*
    * (non-Javadoc)
    * @see com.hpcl.dao.AdministrationDao#getDetails(int, java.lang.String)
    */

	 
	public AppParameters getDetails(int id, String tableName) 
	{
		logger.info("getEmployeRoles method is started");		 
		try
		{
			String query="select "+SchemaDef.ROLEID+","+SchemaDef.DESCRIPTION+","+SchemaDef.STATUS+" from "+tableName+" where "+SchemaDef.ROLEID+" = "+id;  
		    jdbcTemplate.update(query); 
		    logger.info("getDetails query :" +query);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		logger.info("getEmployeRoles method is completed"); 
		return null;
	 }

     /*
      * (non-Javadoc)
      * @see com.hpcl.dao.TwoFieldDao#updateDetails(com.hpcl.persistence.TwoFieldPersistence, java.lang.String)
      */
	   
	  public void updateDetails(AppParameters twoFieldPersistence,String tableName) 
	  {
		  logger.info("updateDetails method is started");
		  String role=null;
		  int id=0;
		  if(tableName.equals(SchemaDef.ROLE))
		  {
			  role=SchemaDef.ROLEID;
			  id=twoFieldPersistence.getRoleID();
		  }
		  else if(tableName.equals(SchemaDef.MODULE))
		  {
			  role=SchemaDef.MODULEID;
			  id=twoFieldPersistence.getModuleID();
		  }
		  else if(tableName.equals(SchemaDef.ACTION))
		  {
			  role=SchemaDef.ACTIONID;
			  id=twoFieldPersistence.getActionID();
		  }
		  else if(tableName.equals(SchemaDef.CATEGORY))
		  {
			  role=SchemaDef.CATEGORYID;
			  id=twoFieldPersistence.getCategoryID();
		  }
		  else if(tableName.equals(SchemaDef.ZONE))
		  {
			  role=SchemaDef.ZONEID;
			  id=twoFieldPersistence.getZoneID();
		  }
		  else if(tableName.equals(SchemaDef.USERTYPE))
		  {
			  role=SchemaDef.USERTYPEID;
			  id=twoFieldPersistence.getUserTypeID();
		  }	  
		  try
		  {
			  String currentDate=new CurrentDateTime().getCurrentDate();
			  String sql = "update "+tableName+" set "+SchemaDef.DESCRIPTION+"='"+twoFieldPersistence.getDescription()+"',"+SchemaDef.EDITEDBY+"='"+twoFieldPersistence.getEditedBy()+"' where "+role+"=?";  
			  jdbcTemplate.update(sql,id);
			  logger.info("update Details query:"+sql);
		  }
		  catch(Exception ex)
		  {
			  ex.printStackTrace();
		      logger.info(ex);
		  }
		  logger.info("updateDetailsmethod is completed");
	  }
	  /*
       * (non-Javadoc)
       * @see com.hpcl.dao.TwoFieldDao#deleteDetails(int, java.lang.String)
       */
	   
	  public void deleteDetails(int rowId, String tableName,String id,AppParameters twoFieldPersistence)
	  {
		  logger.info("deleteDetails method is started");
		  try
		  {
			  String currentDate=new CurrentDateTime().getCurrentDate();
			  String sql = "update "+tableName+" set "+SchemaDef.STATUS+" ='"+SchemaDef.INACTIVE_FLAG+"',"+SchemaDef.EDITEDBY+"='"+twoFieldPersistence.getEditedBy()+"' WHERE "+id+"=?";
			  logger.info("deleteDetails Query : "+sql+" "+rowId); 
			  jdbcTemplate.update(sql, rowId);
		  }
		  catch(Exception ex)
		  {
			  ex.printStackTrace();
			  logger.info(ex);
		  }
		  logger.info("deleteDetails method is completed");
	  }
	  
	  /*
	   * (Location-ip page saveLocationipDetails)
	   * @see com.hpcl.dao.AdministrationDao#saveLocationipDetails(com.hpcl.persistence.AppParameters, java.lang.String)
	   */
	   
	  public void saveLocationipDetails(AppParameters appParameters,String tableName)
	  {
		  logger.info("saveLocationipDetails method is started");
		  try
		  {
			  String query="insert into "+tableName+" ("+SchemaDef.LOCATIONID+","+SchemaDef.IPADDRESS+","+SchemaDef.STATUS+","+SchemaDef.CREATEDBY+") values('"+appParameters.getLocation()+"','"+appParameters.getIpAddress()+"','"+appParameters.getStatus()+"','"+appParameters.getCreatedBy()+"')";  
			  logger.info("saveLocationipDetails Query :"+query);
			  jdbcTemplate.update(query);
	      }
		  catch(Exception ex)
		  {
			  ex.printStackTrace();
			  logger.info(ex);
		  }
		  logger.info("saveLocationipDetails method is completed");
	  }

	 
	public void updateLocationipDetails(AppParameters appParameters,String tableName)
	{
		String ipcode=SchemaDef.LOCATION_ID;
		logger.info("updateLocationipDetailsmethod is started");
  		final String locationCode=appParameters.getLocationID();
  		try
  		{
  			String sql = "update "+tableName+" set "+SchemaDef.IPADDRESS+"=? where "+ipcode+"=?";  
  		    jdbcTemplate.update(sql, appParameters.getDescription(),locationCode);
  		    logger.info("updateLocationipDetails Query :"+sql);
  		}
  		catch(Exception ex)
  		{
  			ex.printStackTrace();
  			logger.info(ex);
  		}
  		logger.info("updateLocationipDetailsmethod is completed");
	}

	 
	public void deleteLocationipDetails(int locipId, String tableName, AppParameters twoFieldPersistence)
	{		 
		final String location_ID=SchemaDef.LOCATION_ID;
		logger.info("deleteLocationipDetails method is started");
        try
        {
        	String sql = "update "+tableName+" set "+SchemaDef.STATUS+" ='"+SchemaDef.INACTIVE_FLAG+"' WHERE "+location_ID+"=?";
        	logger.info("deleteLocationipDetails Query : "+sql+" "+location_ID); 
        	jdbcTemplate.update(sql, twoFieldPersistence.getLocationID());
        }
        catch(Exception ex)
        {
        	ex.printStackTrace();
        	logger.info(ex);
        }
        logger.info("deleteLocationipDetails method is completed");
	}


	 
	public List<AppParameters> getAllLocationipDetails(String tableName,String id, String status)
	{
		logger.info("getAllLocationIPDetails method is started");
		String sql=null;
		if(status!=null)
		{
			if(status.equals("A"))
			{
				logger.info("getAllLocationipDetails  Active status query:"+sql);
				sql = "SELECT "+id+","+SchemaDef.LOCATIONID+","+SchemaDef.IPADDRESS+","+SchemaDef.STATUS+" FROM "+tableName+"  where status='"+SchemaDef.ACTIVE_FLAG+"'";
		 	}
			else if(status.equals("I"))
			{
		 		sql = "SELECT "+id+","+SchemaDef.LOCATIONID+","+SchemaDef.IPADDRESS+","+SchemaDef.STATUS+" FROM "+tableName+"  where status='"+SchemaDef.INACTIVE_FLAG+"'";
		 		logger.info("getAllLocationipDetails  Inactive status query:"+sql);
			}
			else if(status.equals("All"))
			{
				sql = "SELECT "+id+","+SchemaDef.LOCATIONID+","+SchemaDef.IPADDRESS+","+SchemaDef.STATUS+" FROM "+tableName;
				logger.info("getAllLocationipDetails  All status query:"+sql);
			}
		}
		else
		{
		   	sql = "SELECT "+id+","+SchemaDef.LOCATIONID+","+SchemaDef.IPADDRESS+","+SchemaDef.STATUS+" FROM "+tableName+"  where status='"+SchemaDef.ACTIVE_FLAG+"'";
		   	logger.info("Default Query for getting all locationIP : " +sql);
		}	 	
		List<AppParameters> twoFieldPersistenceList = jdbcTemplate.query(sql, new RowMapper<AppParameters>()
		{
			public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				AppParameters twoFieldPersistence = new AppParameters();
			    twoFieldPersistence.setLocationIpCode(rs.getInt(SchemaDef.LOCATIONIPCODE));
			    twoFieldPersistence.setLocationID(rs.getString(SchemaDef.LOCATIONID));
			    twoFieldPersistence.setIpAddress(rs.getString(SchemaDef.IPADDRESS));
			    twoFieldPersistence.setStatus(rs.getString(SchemaDef.STATUS));
		        return twoFieldPersistence;
			}
		});
		logger.info("getAllDetails method is completed");
		return twoFieldPersistenceList;
	}
 
	/*
	 * Home Page TableRows count
	 * @see com.hpcl.dao.AdministrationDao#homePageCount(java.lang.String)
	 */
	 
	public int homePageCount(String tableName) 
	{
		logger.info("Home page count method is started");
		String sql="select * from "+tableName+" where Status='A'";
		logger.info("homePageCount Query :"+sql);
		RowCountCallbackHandler countCallback = new RowCountCallbackHandler(); 
		jdbcTemplate.query(sql, countCallback);
		int rowCount = countCallback.getRowCount();
		logger.info("Home page count method is end");
		return rowCount;
	}
	
	 
	public int deviceEarthpitPageCount(String tableName,AppParameters appParameters)
	{
		logger.info("deviceEarthpitPageCount method is started");
		String sql="select * from "+tableName+" where locationId='"+appParameters.getLocationID()+"' and deviceId='"+appParameters.getDeviceId()+"' and status='A'";
		logger.info("deviceEarthpitPageCount Query :"+sql);
		RowCountCallbackHandler countCallback = new RowCountCallbackHandler(); 
		jdbcTemplate.query(sql, countCallback);
		int rowCount = countCallback.getRowCount();
		logger.info("deviceEarthpitPageCount method is end");
		return rowCount;
	}
	
	 
	public List<AppParameters> maxEarthPitValue(String tableName,AppParameters appParameters) 
	{
		logger.info("MaxCount method is started");
		String sql="select "+SchemaDef.EARTHPIT_VALUES+" from "+tableName+" where locationId='"+appParameters.getLocationID()+"' and deviceId='"+appParameters.getDeviceId()+"'";
		int rowCount=0;
		logger.info("maxEarthPitValue Query :"+sql);
		List<AppParameters> twoFieldPersistenceList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() 
		{
			public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				AppParameters twoFieldPersistence = new AppParameters();
				twoFieldPersistence.setEarthPitValues(rs.getString(SchemaDef.EARTHPIT_VALUES));
				return twoFieldPersistence;
			}
		});
		logger.info("MaxCount method is end");
		return twoFieldPersistenceList;
	}

	 
	public boolean checkDuplicateData(String tableName, String fieldName, String value)
	{
		// TODO Auto-generated method stub
		boolean check=false;
		int rowCount=0;
		logger.info("checkDuplicateData method is started");
		String sql="select * from "+tableName+" where status='A' and "+fieldName+"='"+value+"'";
		logger.info("checkDuplicateData Query :"+sql);
		RowCountCallbackHandler countCallback = new RowCountCallbackHandler(); 
		jdbcTemplate.query(sql, countCallback);
		rowCount = countCallback.getRowCount();
		logger.info("checkDuplicateData is end");
		if(rowCount>0)
		{
		   check=true;
		}
		return check;
	}
	
	 
	public boolean checkDuplicateRoleData(String tableName, String roleId, String menuId,String pageId,String actionID) 
	{
		// TODO Auto-generated method stub
		boolean check=false;
		int rowCount=0;
		logger.info("checkDuplicateRoleData method is started");
		String sql="select * from "+tableName+" where status='A' and "+SchemaDef.ACTIONID+"='"+actionID+"' and "+SchemaDef.PAGEID+"='"+pageId+"'and "+SchemaDef.MODULEID+"='"+menuId+"'and "+SchemaDef.ROLEID+"='"+roleId+"'";
		logger.info("checkDuplicateData Query :"+sql);
		RowCountCallbackHandler countCallback = new RowCountCallbackHandler(); 
		jdbcTemplate.query(sql, countCallback);
		rowCount = countCallback.getRowCount();
		logger.info("checkDuplicateRoleData is end");
		if(rowCount>0)
		{
			check=true;
		}
		return check;
	}
	
	
	 
	public boolean checkDuplicateDataEarthpits(String tableName,String fieldName1, String fieldName2, String value1, String value2)
	{
		// TODO Auto-generated method stub
		boolean check=false;
		int rowCount=0;
		logger.info("checkDuplicateData method is started");
		String sql="select * from "+tableName+" where status='A' and "+fieldName1+"='"+value1+"'  and "+fieldName2+"='"+value2+"'";
		logger.info("checkDuplicateData Query :"+sql);
		RowCountCallbackHandler countCallback = new RowCountCallbackHandler(); 
		jdbcTemplate.query(sql, countCallback);
		rowCount = countCallback.getRowCount();
		logger.info("checkDuplicateData is end");
		if(rowCount>0)
		{
			check=true;
		}
		return check;
	}
	
	 
	public boolean checkDuplicateDataRolePermisions(String tableName,String fieldName1, String fieldName2, String fieldName3,String fieldName4, int value1, int value2, String value3,int value4)
	{
		boolean check=false;
		int rowCount=0;
		logger.info("checkDuplicateData method is started");
		String sql="select * from "+tableName+" where status='A' and "+fieldName1+"='"+value1+"' and "+fieldName2+"='"+value2+"' and "+fieldName3+"="+value3+" and "+fieldName4+"='"+value4+"'";
		logger.info("checkDuplicateData Query :"+sql);
		RowCountCallbackHandler countCallback = new RowCountCallbackHandler(); 
		jdbcTemplate.query(sql, countCallback);
		rowCount = countCallback.getRowCount();
		logger.info("checkDuplicateData is end");
		if(rowCount>0)
		{
			check=true;
		}
		return check;
	}
		
	 
	public boolean checkDuplicateDataDeviceEarthpit(String tableName,String fieldName1, String fieldName2, String fieldName3,String value1, String value2, String value3)
	{
		boolean check=false;
		int rowCount=0;
		logger.info("checkDuplicateData method is started");
		String sql="select * from "+tableName+" where status='A' and "+fieldName1+"='"+value1+"' and "+fieldName2+"='"+value2+"' and "+fieldName3+"="+value3+"'";
		logger.info("checkDuplicateData Query :"+sql);
		RowCountCallbackHandler countCallback = new RowCountCallbackHandler(); 
		jdbcTemplate.query(sql, countCallback);
		rowCount = countCallback.getRowCount();
		logger.info("checkDuplicateData is end");
		if(rowCount>0)
		{
			check=true;
		}
		return check;
	}
	
	 /*  DAOImpl for displaying menus and page*/   
	 
	public List<AppParameters> getAllMenus(String status)
	{
		logger.info("getAllMenus method is started");
		String sql=null;
		if(status!=null)
		{
			if(status.equals("A"))
			{		
				sql = "select me.Description ,md.Description from m_menu me, m_module md where md.MenuId = me.ID and me.Status ='A'";
				logger.info("get All Active Menus list :" +sql);
		 	}
			else if(status.equals("I"))
			{
		 		sql = "select me.Description,md.Description from m_menu me, m_module md where md.MenuId = me.ID and me.Status ='I'";
		 		logger.info("get All Inactive Menus list :" +sql);
			}
			else if(status.equals("All"))
			{
			 	sql = "select me.Description,md.Description from m_menu me, m_module md where md.MenuId = me.ID";
			 	logger.info("get All Menus irrespective of status :" +sql);
			}
		}
		else
		{
		   	sql = "select me.Description,md.Description from m_menu me, m_module md where md.MenuId = me.ID and me.Status ='A'";
		   	logger.info("getAllMenus query:"+sql);
		}
	    List<AppParameters> twoFieldPersistenceList = jdbcTemplate.query(sql, new RowMapper<AppParameters>()
	    {
	    	public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException 
	    	{
	    		AppParameters twoFieldPersistence = new AppParameters();
			    twoFieldPersistence.setDescription(rs.getString(1));
			    twoFieldPersistence.setTempDescription(rs.getString(2));
		        return twoFieldPersistence;
	    	}
	    });
		logger.info("getall Menusmethod is completed");
		return twoFieldPersistenceList;
	}
}