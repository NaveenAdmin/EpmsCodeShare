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
import com.hpcl.utill.SchemaDef;

public class SocketDaoImpl implements SocketDao {

	private static final Logger logger = Logger.getLogger(SocketDao.class);
	private JdbcTemplate jdbcTemplate; 

	public void setJdbcTemplate(DataSource dataSource) {  
		 jdbcTemplate = new JdbcTemplate(dataSource);
	} 
	
	
	public void saveDeviceDetails(AppParameters twoFieldPersistence, String tableName) {
		// TODO Auto-generated method stub

	}

	
	public void saveAlertDetails(AppParameters twoFieldPersistence, String tableName) {
		// TODO Auto-generated method stub

	}

	
	public List<AppParameters> getAllDeviceDetails(String deviceID) {
		logger.info("Socket getAllDeviceDetails Method Started");
		 String sql="select d.LocationId,d.DeviceId,d.deviceName,d.IPAddress,e.earthpitId,ep.earthpitName from m_device d, m_device_earthpits e, m_earthpits ep where d.DeviceId = e.deviceId and  e.earthpitId = ep.earthpitid and d.DeviceId="+deviceID+"";
	 	 logger.info("Query "+sql);
		    List<AppParameters> twoFieldPersistenceList = jdbcTemplate.query(sql, new RowMapper<AppParameters>() {
		        public AppParameters mapRow(ResultSet rs, int rowNum) throws SQLException {
		    	AppParameters twoFieldPersistence = new AppParameters();
		     	twoFieldPersistence.setLocationID(rs.getString("LocationId"));
		    	twoFieldPersistence.setDeviceId(rs.getString("DeviceId"));
		    	twoFieldPersistence.setDeviceId(rs.getString("deviceName"));
		    	twoFieldPersistence.setDeviceId(rs.getString("IPAddress"));
		    	twoFieldPersistence.setDeviceId(rs.getString("earthpitId"));
		    	twoFieldPersistence.setDeviceId(rs.getString("earthpitName"));
	                return twoFieldPersistence;
	                }
		        });
		    logger.info("Socket getAllDeviceDetails Method End");
		return twoFieldPersistenceList;
	}

}
