/**
* @author  :Prasad
* @version :1.0
* @since   2015-10-09
* @ModifiedDate	:2015-11-02
*/
package com.hpcl.dao;

import java.util.List;

import com.hpcl.persistence.AppParameters;

public interface AdministrationDao 
{	
	public void saveDetails(AppParameters twoFieldPersistence,String tableName );
	public AppParameters getDetails(int Id, String tableName);
    public void updateDetails(AppParameters twoFieldPersistence,String tableName);
    public void deleteDetails(int rowId,String tableName,String id,AppParameters twoFieldPersistence);
    public List<AppParameters> getAllDetails(String tableName,String id,String status, String location,String userId);    
    public void saveLocationipDetails(AppParameters appParameters,String tableName );
    public void updateLocationipDetails(AppParameters appParameters,String tableName);
    public void deleteLocationipDetails(int locipId,String tableName,AppParameters twoFieldPersistence);
    public List<AppParameters> getAllLocationipDetails(String tableName,String id,String status);    
    public int homePageCount(String tableName);
    public int deviceEarthpitPageCount(String tableName,AppParameters appParameters);
    public List<AppParameters> maxEarthPitValue(String tableName,AppParameters appParameters);
    public boolean checkDuplicateData(String tableName,String fieldName,String value);
    public boolean checkDuplicateRoleData(String tableName, String roleId, String menuId,String pageId,String actionID);
    public boolean checkDuplicateDataEarthpits(String tableName,String fieldName1,String fieldName2,String value1,String value2);
    public boolean checkDuplicateDataRolePermisions(String tableName,String fieldName1,String fieldName2,String fieldName3,String fieldName4,int value1,int value2,String value3,int value4);
    public boolean checkDuplicateDataDeviceEarthpit(String tableName,String fieldName1,String fieldName2,String fieldName3,String value1,String value2,String value3);
    public List<AppParameters> getAllMenus(String status);
}