/**
* @author  :Prasad
* @version :1.0
* @since   2015-10-09
* @ModifiedDate	:2015-11-02.
*/
package com.hpcl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.hpcl.persistence.AppParameters;
import com.hpcl.utill.CurrentDateTime;
import com.hpcl.utill.SchemaDef;


public class RolePerimissionTablesDataDaoImpl implements
	RolePerimissionTablesDataDao {
	private static final Logger logger = Logger.getLogger(RolePerimissionTablesDataDaoImpl.class);
	private JdbcTemplate jdbcTemplate; 

	
	public void setJdbcTemplate(DataSource dataSource) {  
		 jdbcTemplate = new JdbcTemplate(dataSource);
	} 
	
	
	public List<AppParameters> getTableData(final String tablename,String status,String location,String userId) {
		
		logger.info("getTable data rolepermissions started ");
		String id;
		String sql=null;
		List<AppParameters> tableDataList=null;
		
		if(userId.equals("admin")){
			if(tablename.equals("m_roleperimissions")){
				if(status.equals("All")){
					sql="select m.status,m.roleID,r.Description,m.moduleid,menu.description,m.actionid,a.Description,m.pageid,md.description from m_roleperimissions m join m_role r on r.RoleID=m.RoleID join m_menu menu on menu.ID=m.ModuleID join m_action a on a.ActionID=m.ActionID join m_module md on md.ModuleID=m.pageid;";
				}else
				if(status!=null){
					sql="select m.status,m.roleID,r.Description,m.moduleid,menu.description,m.actionid,a.Description,m.pageid,md.description from m_roleperimissions m join m_role r on r.RoleID=m.RoleID join m_menu menu on menu.ID=m.ModuleID join m_action a on a.ActionID=m.ActionID join m_module md on md.ModuleID=m.pageid where m.Status='"+status+"';";
				}else
			   sql="select m.status,m.roleID,r.Description,m.moduleid,menu.description,m.actionid,a.Description,m.pageid,md.description from m_roleperimissions m join m_role r on r.RoleID=m.RoleID join m_menu menu on menu.ID=m.ModuleID join m_action a on a.ActionID=m.ActionID join m_module md on md.ModuleID=m.pageid where m.Status='A';";
				logger.info("getTable data rolepermissions :"+sql);
				logger.info("getTableData Template :"+jdbcTemplate);
				tableDataList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
			        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
			    	AppParameters appParameters = new AppParameters();
			    	appParameters.setStatus(rs.getString(1));
			    	appParameters.setRoles(rs.getString(2));
			    	appParameters.setRoleDesc(rs.getString(3));
			    	appParameters.setModuleID(Integer.parseInt(rs.getString(4)));
			    	appParameters.setModuleDesc(rs.getString(5));
			    	appParameters.setActionID(Integer.parseInt(rs.getString(6)));
			    	appParameters.setActionDesc(rs.getString(7));
			    	appParameters.setPageID(rs.getString(8));
			    	appParameters.setPageDesc(rs.getString(9));
			    	
			    	 return appParameters;
		              }
			 
			        });
			}else{
				try{
					if(tablename.equals(SchemaDef.ROLE)){
				  		id=SchemaDef.ROLEID;
				  		sql="select "+id+","+SchemaDef.DESCRIPTION+" from "+tablename+" where "+SchemaDef.STATUS+" = '"+SchemaDef.ACTIVE_FLAG+"'";
				  		logger.info("Role table Query: " +sql);				       
					}else
					if(tablename.equals(SchemaDef.MODULE)){
				  		id=SchemaDef.MODULEID;
				  		sql="select "+id+","+SchemaDef.DESCRIPTION+" from "+tablename+" where "+SchemaDef.STATUS+" = '"+SchemaDef.ACTIVE_FLAG+"'";
				  		logger.info("module table Query: " +sql);	
					}else
					if(tablename.equals(SchemaDef.ACTION)){
				  		id=SchemaDef.ACTIONID;
				  		sql="select "+id+","+SchemaDef.DESCRIPTION+" from "+tablename+" where "+SchemaDef.STATUS+" = '"+SchemaDef.ACTIVE_FLAG+"'";
				  		logger.info("action table Query: " +sql);	
					}else if(tablename.equals(SchemaDef.M_LOCATIONS)){
				  		id=SchemaDef.LOCATIONID;
				  		sql="select "+id+","+SchemaDef.LOCATIONNAME+" from "+tablename+" where "+SchemaDef.STATUS+" = '"+SchemaDef.ACTIVE_FLAG+"'";
				  		logger.info("location table Query: " +sql);	
					}else if(tablename.equals(SchemaDef.M_GROUPS)){
				  		id=SchemaDef.GROUPID;
				  		sql="select DISTINCT "+id+","+SchemaDef.GROUPNAME+" from "+tablename+" where "+SchemaDef.STATUS+" = '"+SchemaDef.ACTIVE_FLAG+"'";
				  		logger.info("Group table Query: " +sql);	
					}					
					   tableDataList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
				        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
				    	AppParameters appParameters = new AppParameters();
				    	boolean flag=true;
				    	if(tablename.equals(SchemaDef.M_GROUPS)){
				    		appParameters.setGroupId(rs.getString(SchemaDef.GROUPID));
				    		appParameters.setGroupName(rs.getString(SchemaDef.GROUPNAME));
				    		flag=false;
				    	}else
				    	if(tablename.equals(SchemaDef.ROLE)){
				    		appParameters.setRoleID(rs.getInt(SchemaDef.ROLEID));
				    	}else if(tablename.equals(SchemaDef.MODULE)){
				    		appParameters.setModuleID(rs.getInt(SchemaDef.MODULEID));
				    	}else if(tablename.equals(SchemaDef.ACTION)){
				    		appParameters.setActionID(rs.getInt(SchemaDef.ACTIONID));
				    	}else if(tablename.equals(SchemaDef.M_LOCATIONS)){
				    		appParameters.setLocationID(rs.getString(SchemaDef.LOCATIONID));
				    	}
				    	if(flag){
					    	if(tablename.equals(SchemaDef.M_LOCATIONS)){
					    		appParameters.setLocationName(rs.getString(SchemaDef.LOCATIONNAME));
					    	
					    	}else		    	
					    	appParameters.setDescription(rs.getString("description"));
					        }
				    	return appParameters;
				    	}
	                });
					
				   }catch(Exception ex){
				        ex.printStackTrace();
				 }
			}
		}else{
		  if(tablename.equals("m_roleperimissions")){
			if(status.equals("All")){
				sql="select m.status,m.roleID,r.Description,m.moduleid,menu.description,m.actionid,a.Description,m.pageid,md.description from m_roleperimissions m join m_role r on r.RoleID=m.RoleID join m_menu menu on menu.ID=m.ModuleID join m_action a on a.ActionID=m.ActionID join m_module md on md.ModuleID=m.pageid;";
			}else
			if(status!=null){
				sql="select m.status,m.roleID,r.Description,m.moduleid,menu.description,m.actionid,a.Description,m.pageid,md.description from m_roleperimissions m join m_role r on r.RoleID=m.RoleID join m_menu menu on menu.ID=m.ModuleID join m_action a on a.ActionID=m.ActionID join m_module md on md.ModuleID=m.pageid where m.Status='"+status+"';";
			}else
		   sql="select m.status,m.roleID,r.Description,m.moduleid,menu.description,m.actionid,a.Description,m.pageid,md.description from m_roleperimissions m join m_role r on r.RoleID=m.RoleID join m_menu menu on menu.ID=m.ModuleID join m_action a on a.ActionID=m.ActionID join m_module md on md.ModuleID=m.pageid where m.Status='A';";
			logger.info("getTable data rolepermissions :"+sql);
			logger.info("getTableData Template :"+jdbcTemplate);
			tableDataList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
		        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
		    	AppParameters appParameters = new AppParameters();
		    	logger.info("data fetched");
		    	appParameters.setStatus(rs.getString(1));
		    	appParameters.setRoles(rs.getString(2));
		    	appParameters.setRoleDesc(rs.getString(3));
		    	appParameters.setModuleID(Integer.parseInt(rs.getString(4)));
		    	appParameters.setModuleDesc(rs.getString(5));
		    	appParameters.setActionID(Integer.parseInt(rs.getString(6)));
		    	appParameters.setActionDesc(rs.getString(7));
		    	appParameters.setPageID(rs.getString(8));
		    	appParameters.setPageDesc(rs.getString(9));
		    	
		    	 return appParameters;
	              }
		 
		        });
		}else{
			try{
				if(tablename.equals(SchemaDef.ROLE)){
			  		id=SchemaDef.ROLEID;
			  		sql="select "+id+","+SchemaDef.DESCRIPTION+" from "+tablename+" where "+SchemaDef.STATUS+" = '"+SchemaDef.ACTIVE_FLAG+"'";  
			       
				}else
				if(tablename.equals(SchemaDef.MODULE)){
			  		id=SchemaDef.MODULEID;
			  		sql="select "+id+","+SchemaDef.DESCRIPTION+" from "+tablename+" where "+SchemaDef.STATUS+" = '"+SchemaDef.ACTIVE_FLAG+"'";
			       
				}else
				if(tablename.equals(SchemaDef.ACTION)){
			  		id=SchemaDef.ACTIONID;
			  		sql="select "+id+","+SchemaDef.DESCRIPTION+" from "+tablename+" where "+SchemaDef.STATUS+" = '"+SchemaDef.ACTIVE_FLAG+"'";
			       
				}else if(tablename.equals(SchemaDef.M_LOCATIONS)){
			  		id=SchemaDef.LOCATIONID;
			  		sql="select "+id+","+SchemaDef.LOCATIONNAME+" from "+tablename+" where "+SchemaDef.STATUS+" = '"+SchemaDef.ACTIVE_FLAG+"' and "+SchemaDef.LOCATIONNAME+"='"+location+"'";
			       
				}else if(tablename.equals(SchemaDef.M_GROUPS)){
			  		id=SchemaDef.GROUPID;
			  		sql="select "+id+","+SchemaDef.GROUPNAME+" from "+tablename+" where "+SchemaDef.STATUS+" = '"+SchemaDef.ACTIVE_FLAG+"'";
			       
				}
				logger.info(sql);
				   tableDataList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
			        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
			    	AppParameters appParameters = new AppParameters();
			    	boolean flag=true;
			    	if(tablename.equals(SchemaDef.M_GROUPS)){
			    		appParameters.setGroupId(rs.getString(SchemaDef.GROUPID));
			    		appParameters.setGroupName(rs.getString(SchemaDef.GROUPNAME));
			    		flag=false;
			    	}else
			    	if(tablename.equals(SchemaDef.ROLE)){
			    		appParameters.setRoleID(rs.getInt(SchemaDef.ROLEID));
			    	}else if(tablename.equals(SchemaDef.MODULE)){
			    		appParameters.setModuleID(rs.getInt(SchemaDef.MODULEID));
			    	}else if(tablename.equals(SchemaDef.ACTION)){
			    		appParameters.setActionID(rs.getInt(SchemaDef.ACTIONID));
			    	}else if(tablename.equals(SchemaDef.M_LOCATIONS)){
			    		appParameters.setLocationID(rs.getString(SchemaDef.LOCATIONID));
			    	}
			    	if(flag){
				    	if(tablename.equals(SchemaDef.M_LOCATIONS)){
				    		appParameters.setLocationName(rs.getString(SchemaDef.LOCATIONNAME));
				    	
				    	}else		    	
				    	appParameters.setDescription(rs.getString("description"));
				        }
			    	return appParameters;
			    	}
                });
				
			   }catch(Exception ex){
			        ex.printStackTrace();
			 }
		}
	}
		logger.info("getTable data rolepermissions end");
		return tableDataList;
		
	}
  /*
   * (non-Javadoc)
   * @see com.hpcl.dao.RolePerimissionTablesDataDao#saveData(java.lang.String, com.hpcl.persistence.AppParameters)
   */
	
	public void saveData(String tablename,
			AppParameters appParameters) {
		logger.info("saveDetails rolepermission method is started");
	    
		   try{
	       String query="insert into "+tablename+" ("+SchemaDef.ROLEID+","+SchemaDef.MODULEID+","+SchemaDef.ACTIONID+","+SchemaDef.PAGEID+","+SchemaDef.STATUS+","+SchemaDef.CREATEDBY+") values('"+appParameters.getRoleID()+"','"+appParameters.getMenuID()+"','"+appParameters.getActionID()+"','"+appParameters.getPageID()+"','"+appParameters.getStatus()+"','"+appParameters.getCreatedBy()+"')";  
	       logger.info("saveDetails rolepermission method Query :"+query);
	       jdbcTemplate.update(query); 
	        }catch(Exception ex){
	        ex.printStackTrace();
	        logger.info(ex);
	         }
		   logger.info("saveDetails rolepermission method is compleated");
		 }
 /*
  * (non-Javadoc)
  * @see com.hpcl.dao.RolePerimissionTablesDataDao#updateData(java.lang.String, com.hpcl.persistence.AppParameters)
  */
	
	public void updateData(String tablename, AppParameters appParameters) {
		
		appParameters.setCreatedOn(CurrentDateTime.getCurrentDate());
		
		logger.info("update method started");
		String sql;
		logger.info("update Template :"+jdbcTemplate);
		boolean flag=new RolePerimissionTablesDataDaoImpl().validateData(tablename, appParameters,jdbcTemplate);
		if(flag){
			logger.info("Data alread exist");
		}else{
			logger.info(" else block flag "+flag);
			 String query="insert into "+tablename+" ("+SchemaDef.ROLEID+","+SchemaDef.MODULEID+","+SchemaDef.ACTIONID+","+SchemaDef.PAGEID+","+SchemaDef.STATUS+","+SchemaDef.EDITEDBY+") values('"+appParameters.getRoleID()+"','"+appParameters.getModuleID()+"','"+appParameters.getActionID()+"','"+appParameters.getPageID()+"','"+appParameters.getStatus()+"','"+appParameters.getEditedBy()+"')"; 
			 logger.info("update Data method Query :"+query);
			 jdbcTemplate.update(query); 
			 logger.info("inserted");
		}
		logger.info("update method end");
		
	}
  /*
   * (non-Javadoc)
   * @see com.hpcl.dao.RolePerimissionTablesDataDao#validateData(java.lang.String, com.hpcl.persistence.AppParameters, org.springframework.jdbc.core.JdbcTemplate)
   */
	
	public boolean validateData(String tablename, AppParameters appParameters,JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		logger.info("validate method started");

		String sql="select * from "+tablename+" where "+SchemaDef.ROLEID+"="+appParameters.getRoleID()+" and "+SchemaDef.MODULEID+"="+appParameters.getModuleID()+" and "+SchemaDef.ACTIONID+"="+appParameters.getActionID()+" and "+SchemaDef.PAGEID+"="+appParameters.getPageID();
		logger.info("validateData Query :"+sql);
		logger.info("valid Template :"+jdbcTemplate);
		RowCountCallbackHandler countCallback = new RowCountCallbackHandler();  // not reusable
		 jdbcTemplate.query(sql, countCallback);
		 int rowCount = countCallback.getRowCount();
		boolean flag=true;
		if(rowCount>0){
			flag=true;
		}else
			flag=false;
		logger.info("validate method "+flag);
		logger.info("validate method end");
		return flag;
		
	}
	
	
	public boolean validateEarthpitsData(String tablename, AppParameters appParameters,JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		logger.info("validateEarthpitsData method started");

		String sql="select * from "+tablename+" where "+SchemaDef.LOCATION_ID+"='"+appParameters.getLocationID()+"' and "+SchemaDef.DEVICE_ID+"='"+appParameters.getDeviceId()+"' and "+SchemaDef.EARTHPIT_ID+"='"+appParameters.getEarthpitID()+"' and "+SchemaDef.STATUS+"='A'";
		logger.info("validateEarthpitsData Query :"+sql);
		logger.info("valid Template :"+jdbcTemplate);
		RowCountCallbackHandler countCallback = new RowCountCallbackHandler();  // not reusable
		 jdbcTemplate.query(sql, countCallback);
		 int rowCount = countCallback.getRowCount();
		boolean flag=true;
		if(rowCount>0){
			flag=true;
		}else
			flag=false;
		logger.info("validate method "+flag);
		logger.info("validate method end");
		return flag;
		
	}
/*
 * (non-Javadoc)
 * @see com.hpcl.dao.RolePerimissionTablesDataDao#deleteData(java.lang.String, com.hpcl.persistence.AppParameters)
 */
	
	public void deleteData(String tablename, AppParameters appParameters) {
		// TODO Auto-generated method stub
		String currentDate=new CurrentDateTime().getCurrentDate();
		 logger.info("deletedata method is started");
	        try{
	        String sql = "update "+tablename+" set "+SchemaDef.STATUS+" ='"+SchemaDef.INACTIVE_FLAG+"',"+SchemaDef.EDITEDBY+"='"+appParameters.getEditedBy()+"' WHERE "+SchemaDef.ROLEID+"=? and "+SchemaDef.MODULEID+"=? and "+SchemaDef.ACTIONID+"=? and "+SchemaDef.PAGEID+"=?";
		       logger.info("deletedata method Query : "+sql+" rowId"+appParameters.getRoleID()); 
		       jdbcTemplate.update(sql, appParameters.getRoleID(),appParameters.getModuleID(),appParameters.getActionID(),appParameters.getPageID());
		
			    }catch(Exception ex){
			        ex.printStackTrace();
			        logger.info(ex);
			        }
	        logger.info("deletedata method is compleated");
		
	}
  /*
   * (non-Javadoc)
   * @see com.hpcl.dao.RolePerimissionTablesDataDao#saveEarthpitData(java.lang.String, com.hpcl.persistence.AppParameters)
   */
	
	public void saveEarthpitData(String tablename, AppParameters appParameters) {
		
		logger.info("save Earth pit Details rolepermission method is started");
		boolean flag=new RolePerimissionTablesDataDaoImpl().validateEarthpitsData(tablename, appParameters, jdbcTemplate);
		if(!flag){
			try{
				   String upquery="update "+SchemaDef.M_EARTHPITS+" set MappedStatus='M' where "+SchemaDef.EARTHPIT_ID+"="+appParameters.getEarthpitID();
			       logger.info("updateEarthpitData Query :"+upquery);
			       jdbcTemplate.update(upquery); 
		       
			      String query="insert into "+tablename+" ("+SchemaDef.LOCATION_ID+","+SchemaDef.DEVICE_ID+","+SchemaDef.EARTHPIT_ID+","+SchemaDef.STATUS+","+SchemaDef.CREATEDBY+") values('"+appParameters.getLocationID()+"','"+appParameters.getDeviceId()+"','"+appParameters.getEarthpitID()+"','"+appParameters.getStatus()+"','"+appParameters.getCreatedBy()+"')";  
		       logger.info("Insert Query :"+query);
		       jdbcTemplate.update(query); 
		      
			   }catch(Exception ex){
		        ex.printStackTrace();
		        logger.info(ex);
		         }	
		}
		
		logger.info("save Earth pit Details rolepermission method is compleated");
		
	}
  /*
   * (non-Javadoc)
   * @see com.hpcl.dao.RolePerimissionTablesDataDao#getSecurityRole(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
   */
	
	public List<AppParameters> getSecurityRole(String tablename1, String tablename2,String tablename3, String empID) {

				 logger.info("getSecurityRole method is started");
			    // String sql="select e.location,p.moduleId,p.actionId,p.pageId from m_employees e join m_role r on e.Roles=r.Description join m_roleperimissions p on r.roleId=p.RoleID where e.EmployeeID='"+empID+"' and e.Status='A'";
				 String sql="select e.location,r.roleId,e.locn from m_employees e join m_role r on e.roles=r.RoleID where e.EmployeeID='"+empID+"' and e.Status='A'";
				 logger.info("getSecurityRole Query :"+sql);
				 List<AppParameters> tableDataList=null;
					logger.info("getTableData Template :"+jdbcTemplate);
					tableDataList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
				        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
				    	AppParameters appParameters = new AppParameters();
				    	logger.info("data fetched");
				    	appParameters.setLocation(rs.getString(1));
				    	appParameters.setRoleID(Integer.parseInt(rs.getString(2)));
				    	appParameters.setLocn(rs.getString(3));
				    	return appParameters;
				        };
					});
					logger.info("getSecurityRole method is end");
					return tableDataList;
					 
	}
 /*
  * (non-Javadoc)
  * @see com.hpcl.dao.RolePerimissionTablesDataDao#getModuleId(java.lang.String)
  */
	
	public List<AppParameters> getModuleId(String roleID) {
		logger.info("getModuleId method started");

		String sql="select moduleId from m_roleperimissions where "+SchemaDef.ROLEID+"="+roleID;
		logger.info("getModuleId method Query :"+sql);
		logger.info("valid Template :"+jdbcTemplate);
		 List<AppParameters> tableDataList=null;
			logger.info("getTableData Template :"+jdbcTemplate);
			tableDataList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
		        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
		    	AppParameters appParameters = new AppParameters();
		    	logger.info("data fetched");
		    	appParameters.setModuleID(rs.getInt(1));
		        return appParameters;
		        };
			});
		
		logger.info("getModuleId method end");
		return tableDataList;
	}
  /*
   * (non-Javadoc)
   * @see com.hpcl.dao.RolePerimissionTablesDataDao#getPageId(java.lang.String)
   */
	
	public List<AppParameters> getPageId(String roleID) {
		// TODO Auto-generated method stub
		logger.info("getPageId method started");

		String sql="select pageID from m_roleperimissions where "+SchemaDef.ROLEID+"="+roleID;
		logger.info("getPageId method Query :"+sql);
		logger.info("valid Template :"+jdbcTemplate);
		 List<AppParameters> tableDataList=null;
			logger.info("getTableData Template :"+jdbcTemplate);
			tableDataList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
		        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
		    	AppParameters appParameters = new AppParameters();
		    	logger.info("data fetched");
		    	appParameters.setPageID(rs.getString(1));
		    	return appParameters;
		        };
			});
		
		logger.info("getPageId method end");
		return tableDataList;

	}
/*
 * (non-Javadoc)
 * @see com.hpcl.dao.RolePerimissionTablesDataDao#getActionId(java.lang.String)
 */
	
	public List<AppParameters> getActionId(String roleID) {
		
		logger.info("getActionId method started");

		String sql="select actionID from m_roleperimissions where "+SchemaDef.ROLEID+"="+roleID;
		logger.info("getActionId Query :"+sql);
		logger.info("valid Template :"+jdbcTemplate);
		try{
		}catch(Exception e){
			
		}
		 List<AppParameters> tableDataList=null;
			logger.info("getTableData Template :"+jdbcTemplate);
			tableDataList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
		        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
		    	AppParameters appParameters = new AppParameters();
		    	logger.info("data fetched");
		    	appParameters.setActionID(rs.getInt(1));
		      return appParameters;
		        };
			});
		
		logger.info("getActionId method end");
		return tableDataList;

	}
}