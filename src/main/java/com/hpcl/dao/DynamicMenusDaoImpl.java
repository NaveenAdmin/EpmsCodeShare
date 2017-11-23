/**
* @author  :Prasad
* @version :1.0
* @since   2015-10-12 
*/
package com.hpcl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.hpcl.persistence.AppParameters;
import com.hpcl.persistence.DynamicMenus;


public class DynamicMenusDaoImpl implements DynamicMenusDao {
	private static final Logger logger = Logger.getLogger(DynamicMenusDaoImpl.class);
	
	private JdbcTemplate jdbcTemplate; 
	
	@Autowired
    DynamicMenusDao dynamicMenusDao;
	
	public void setJdbcTemplate(DataSource dataSource) {  
		 jdbcTemplate = new JdbcTemplate(dataSource);
	}
	/*
	 * (non-Javadoc)
	 * @see com.hpcl.dao.DynamicMenusDao#getAllDynamicMenus(int)
	 */
	
	public List<DynamicMenus> getAllDynamicMenus(int roleID) {
	logger.info("getDynamicMenus method is started");
	String sql="";
		 
	if(roleID==0){
		sql = "SELECT id,description,url FROM m_menu where status='A'";
	}
	else if(roleID==-1){
		sql = "SELECT id,description,url FROM m_menu where status='A' and id in(1,2,3,4)";
	}
	else if(roleID==-2){
		sql = "SELECT id,description,url FROM m_menu where status='A' and id in(1,2,3)";
	}
	else{
		sql="SELECT distinct a.id,a.description,a.url FROM m_menu a, m_roleperimissions b where a.status='A' and b.ModuleID=a.id and b.RoleID='"+roleID+"'";
	}
	logger.info("Menu Query :"+sql);
	
	    List<DynamicMenus> dynamicMenusDao = jdbcTemplate.query(sql, new RowMapper<DynamicMenus>() {
	    public DynamicMenus mapRow(ResultSet rs, int rowNum) throws SQLException {
	    DynamicMenus dynamicMenusDao = new DynamicMenus();
	    	
	    	dynamicMenusDao.setId(rs.getString("id"));
	    	dynamicMenusDao.setDescription(rs.getString("Description"));
	    	dynamicMenusDao.setUrl(rs.getString("url"));
            return dynamicMenusDao;
	        }
	 
	      });
	 logger.info("getDynamicMenus method is compleated");
	 return dynamicMenusDao;
	  
	}
	/*
	 * (non-Javadoc)
	 * @see com.hpcl.dao.DynamicMenusDao#getAllDynamicPages(java.lang.String, com.hpcl.persistence.AppParameters)
	 */
	
	public List<DynamicMenus> getAllDynamicPages(String tablename,AppParameters appParameters) {
		logger.info("getDynamicpages method is started");
		    
			String sql = "SELECT ModuleID,Description FROM m_module where status='A' and menuId="+appParameters.getId();
		    logger.info("Query:"+sql);
			List<DynamicMenus> dynamicMenusDao = jdbcTemplate.query(sql, new RowMapper<DynamicMenus>() {
		    public DynamicMenus mapRow(ResultSet rs, int rowNum) throws SQLException {
		    DynamicMenus dynamicMenusDao = new DynamicMenus();
		    	
		    	dynamicMenusDao.setId(rs.getString("ModuleID"));
		    	dynamicMenusDao.setDescription(rs.getString("Description"));
		    	
	            return dynamicMenusDao;
		        }
		 
		      });
		 logger.info("getDynamicMenus method is compleated");
		 return dynamicMenusDao;
		  
		}
}
