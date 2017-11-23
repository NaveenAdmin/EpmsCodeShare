package com.hpcl.dao;

import java.util.List;

import com.hpcl.persistence.AppParameters;

public interface SocketDao {
	public void saveDeviceDetails(AppParameters twoFieldPersistence,String tableName );
	public void saveAlertDetails(AppParameters twoFieldPersistence,String tableName );
	public List<AppParameters> getAllDeviceDetails(String deviceID);
}
