/**
* @author  :Prasad
* @version :1.0
* @since   2015-10-09
* @ModifiedDate	:2015-11-02.
*/
package com.hpcl.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.hpcl.persistence.AppParameters;

public interface RolePerimissionTablesDataDao {

	public List<AppParameters> getTableData(final String tablename,String status,String location,String userId);
	public void saveData(String tablename,AppParameters appParameters);
	public void deleteData(String tablename,AppParameters appParameters);
	public void updateData(String tablename,AppParameters appParameters);
	public boolean validateData(String tablename,AppParameters appParameters,JdbcTemplate jdbcTemplate);
	public void saveEarthpitData(String tablename,AppParameters appParameters);
	public boolean validateEarthpitsData(String tablename,AppParameters appParameters,JdbcTemplate jdbcTemplate);
	public List<AppParameters> getSecurityRole(final String tablename1,final String tablename2,final String tablename3,String empID);
	public List<AppParameters> getModuleId(String roleID);
	public List<AppParameters> getPageId(String roleID);
	public List<AppParameters> getActionId(String roleID);
}
