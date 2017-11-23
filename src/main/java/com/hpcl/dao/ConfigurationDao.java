/**
* @author  :Prasad.G
* @version :1.0
* @since   2015-10-09
* @ModifiedDate	:2015-11-06.
*/
package com.hpcl.dao;

import java.util.List;

import com.hpcl.persistence.AppParameters;

public interface ConfigurationDao 
{
	public void saveDetails(AppParameters appParameters,String tableName1,String tableName2 );
    public AppParameters getDetails(String Id, String tableName1,String tableName2);
    
    //Update all employee record
    public void updateDetails(AppParameters appParameters,String tableName1,String tableName2);
    
    //Update single record
    public void updateSingleRecord(AppParameters appParameters, String tableName1, String tableName2);
    public void deleteDetails(String rowId,String tableName1, String tableName2,String id,AppParameters appParameters);
    public void deleteEarthptsDetails(AppParameters appParameters,String tableName );
    public List<AppParameters> getAllDetails(String tableName1,String tableName2,String id,String status,String location,String userId);
    
    /*for getAll locations*/
	public List<AppParameters> getAllLocationDetails(String tableName1,String tableName2, String id, String status,String location,String userId);	
	
	/* for getRolePermissions */
	public void saveRolePermissions(AppParameters appParameters,String tableName1,String tableName2,String tableName3);
	public AppParameters getRolePermissions(String id, String tableName1,String tableName2,String tableName3);
	public void updateRolePermissions(AppParameters appParameters,String tableName1,String tableName2,String tableName3);
	public void deleteRolePermissions(String id,String tableName1, String tableName2,String tableName3);  

	/*for getearthpits details*/
	public void saveEarthptsDetails(AppParameters appParameters,String tableName );
	public void updateEarthpitsDetails(AppParameters appParameters,String tableName);
	public List<AppParameters> getAllEarthpitsDetails(String tableName,String id,String status,String location,String userId);
    public void deleteEarthpitsDetails(AppParameters appParameters,String tableName1);
    public List<AppParameters> getAllEarthpitLocationDetails(String tableName,String id,String status);		
     
    /*for getDevice details*/
    public void saveDeviceDetails(AppParameters appParameters,String tableName );
	public void updateDeviceDetails(AppParameters appParameters,String tableName);
	public List<AppParameters> getAllDeviceDetails(String tableName,String id,String status,String location,String userId);
    public void deleteDeviceDetails(AppParameters appParameters,String tableName1);
    public List<AppParameters> getAllDeviceLocationDetails(String tableName,String id,String status);
        
    /*for get device-Earthpits details*/
    public void saveEscalationDetails(AppParameters appParameters,String tableName );
	public void updateEscalationDetails(AppParameters appParameters,String tableName);
	public List<AppParameters> getAllEscalationDetails(String tableName,String id,String status,String location,String userId);
    public void deleteEscalationDetails(AppParameters appParameters,String tableName,String id);
    public List<AppParameters> getDeviceEarthpitLocationsDetails(String tableName,String location,String divice);
       
    /*for getdevice-Earthpits details*/
	public void saveDeviceEarthpitsDetails(AppParameters appParameters,String tableName);
	public void updateDeviceEarthpitsDetails(AppParameters appParameters,String tableName);
	public List<AppParameters> getAllDeviceEarthpitsDetails(String tableName,String id,String status,String location,String userId);
    public void deleteDeviceEarthpitsDetails(AppParameters appParameters,String tableName1);
	
    //public List<AppParameters> getAllEarthpitMonitoringDetails(String tableName, Object object, String status);
    public List<AppParameters> getAllEarthpitMonitoringDetails(String tableName,  AppParameters twoFieldPersistence, String status,String fromdatetime,String todatetime,String location,String userId);
	
    /*for AllAlertManagementDetails*/
    public List<AppParameters> getAllAlertManagementDetails(String tableName, AppParameters twoFieldPersistence, String status,String location,String userId);
    public void updateAlertManagementDetails(AppParameters appParameters,String tableName);
	
    //Authenticat user
    public List<AppParameters> userValidate(String tableName1,String tableName2,String id,String status);
	
    /*for AlertGropu details*/
    public void saveAlertGroupDetails(AppParameters appParameters,String tableName);
	public void updateAlertGroupDetails(AppParameters appParameters,String tableName);
	public List<AppParameters> getAllAlertGroupDetails(String tableName,String id,String status,String location,String userId);
    public void deleteAlertGroupDetails(AppParameters appParameters,String tableName1);
       
    /*for Alert group get the Employee details*/
    public List<AppParameters> getAllLocationEmployeesDetails(String locationid);
    
    /*for serarch field get the location device details*/
    public List<AppParameters> getAllLocationDeviceDetails(String locationid);
    public List<AppParameters> getDateAndTimeSearch(String tableName,String fromdatetime,String todatetime,AppParameters appParameters );
    public List<AppParameters> getDateAndTimeSearchFOrAlert(String tableName,String locationID, String deviceId,String fromdatetime,String todatetime,AppParameters appParameters );
        
    //created by naveen on 21-11-2015
    //public List<AppParameters> getDasboardSearchfilters(String tableName,String locid, String deviceid, String fromdatetime,String todatetime,AppParameters appParameters );
        
    // For employe page location serarch
    public List<AppParameters> getEmployeeLocations(String tableName,String location);
     
    // For device page location serarch
    public List<AppParameters> getDeviceLocations(String tableName,String location);
     
    // For device page location serarch
    public List<AppParameters> getEarthpitLocations(String tableName,String location);
       
    // For device-earthpit page location serarch
    public List<AppParameters> getDeviceEarthpitLocations(String tableName,String location);
    public List<AppParameters> getDasboardSearchfilters(String tableName, String locationID, String deviceId, String fromdatetime, String todatetime, AppParameters twoFieldPersistence,String empRole,String emplocid,String userId);
    public List<AppParameters> getOnlyDasboardSearchfilters(String tableName, String locationID, String deviceId, String fromdatetime, String todatetime, AppParameters twoFieldPersistence);
	
    //for group page getEmployee detais
	public List<AppParameters> getEmployeeDetails(String tableName, AppParameters appParameters);
	public List<AppParameters> getEmployeeGroupDetails(String tableName, AppParameters appParameters);
		
	//for getmaxdevice from appsettings
	public List<AppParameters> getAppsetting(AppParameters appParameters);
	public String getEmployeeID(String employeeID);
	public void updateGroups(AppParameters appParameters,String tableName);
}