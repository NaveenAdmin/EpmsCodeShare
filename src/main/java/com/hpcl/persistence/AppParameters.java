/**
* @author  :Prasad
* @version :1.0
* @since   2015-10-09
* @ModifiedDate	:2015-11-02
*/
package com.hpcl.persistence;

import org.hibernate.validator.constraints.NotEmpty;

public class AppParameters {

	// ----------------- Table Primarykey Columns begin ------------
	@NotEmpty
	private int roleID;
	@NotEmpty
	private int moduleID;
	@NotEmpty
	private int actionID;
	@NotEmpty
	private int categoryID;
	@NotEmpty
	private int zoneID;
	@NotEmpty
	private int userTypeID;
	// ----------------- Table Primarykey Columns End ------------

	// ----------------- Common Table Columns begin ------------
	private String description;
	private String tempDescription;
	private String status;
	private String createdBy;
	private String createdOn;
	private String editedBy;
	private String editedOn;
	private String page;
	private int operation;
	// ----------------- Common Table Columns End ------------

	// ----------------- Three field paramenters begin ------------
	private String employeeID;
	private String firstName;
	private String lastName;
	private String location;
	private String locn;
	

	private String employeeName;
	private String roles;
	private String userType;
	private String employeeType;
	// ---------------- Three field paramenters End ------------

	//----------------- m_Location table Columns begin ------------
	private String locationID;
	private String uplocationID;
	private String locationName;
	private String state;
	private String town;
	private String zones;
	private String locationType;

	// ----------------- m_Location table Columns end ------------

	// ----------------- m_LocationIP table Columns begin ------------
	private int locationIpCode;
	private String ipAddress;
	// ----------------- m_LocationIP table Columns end ------------

	// ----------------- m_EARTHPITStable Columns begin ------------
	private String earthpitID;
	private String earthpitName;
	// ----------------- m_EARTHPITS table Columns end ------------

	// ----------------- m_devicetable Columns begin ------------
	private String deviceId;
	private String updeviceId;
	private String earthPitValues;
	private String earthPitNames;
	private String timeInterval;
	private String locationHeadsMobile;
	private String emailIDs;
	private String installedDate;
	private String deviceSno;
	private String dateandTime;
	private String earthPitCount;
	
	private String actionDesc;
	private String moduleDesc;
	private String roleDesc;

	// ----------------- m_device table Columns end ------------
	
	// ----------------- m_device-earthpit table Columns start ------------
	 private String deviceName;
	 private String deviceearthpitId;
	 private String maxVolt;
	// ----------------- m_device-earthpit table Columns end ------------
	 
	 
	// ----------------- m_escalation Columns start ------------
	private String escalationIDs;
	private String escalationTypes;
	private String escalationLevels;
	private String sMSMessages;
	private String eMailMessages;
	private String escalationIntervals;
	private int repeatIntervals;
	// ----------------- m_escalation Columns END ------------
	
	
	// ----------------- tr_dashboard  Columns start ------------
    private String ReceivedDate;
    private String voltage;
    
    //home page count
	private String rolescount;
	private String pagescount;
	private String actioncount;
	private String categorycount;
	private String zonecount;
	private String usertypecount;
	private String rolepermissioncount;
	private String locationipcount;
	private String menuID;
	private String pageID;
	private String pageDesc;
	//page actions
	  private String actions;
	  private String[] pageActions;
	
	public String[] getPageActions() {
		return pageActions;
	}

	public void setPageActions(String[] pageActions) {
		this.pageActions = pageActions;
	}

		//Alert Management columns
		private String id;
		private String alertFlag;
		private String empNo;
		private String dOF;
		private String mSD;
		private String actionTaken;
		private String actionDate;
		private String delayTime;
		private String groupId;
		private String repeatTime;
		private String failureType;
		private String diffTime;
		
	
    //gropus table  columns
	
     private String groupName;
     private String level;
     private String phoneNo;
     private String emailId;
     private String deviceType;
     
     //Date Search Fields
     
     private String fromdatetime;
     private String todatetime;
    
	//for appsettings
     private String maxDevices;
     private String earthpit;
     
     
     
     public String getLocn() {
 		return locn;
 	}

 	public void setLocn(String locn) {
 		this.locn = locn;
 	} 
	
	public String getMaxDevices() {
		return maxDevices;
	}

	public void setMaxDevices(String maxDevices) {
		this.maxDevices = maxDevices;
	}

	public String getEarthpit() {
		return earthpit;
	}

	public void setEarthpit(String earthpit) {
		this.earthpit = earthpit;
	}

	public String getFromdatetime() {
		return fromdatetime;
	}

	public void setFromdatetime(String fromdatetime) {
		this.fromdatetime = fromdatetime;
	}

	public String getTodatetime() {
		return todatetime;
	}

	public void setTodatetime(String todatetime) {
		this.todatetime = todatetime;
	}

	public String getTempDescription() {
		return tempDescription;
	}

	public void setTempDescription(String tempDescription) {
		this.tempDescription = tempDescription;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	

	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
		 * @return the pageDesc
		 */
		public String getPageDesc() {
			return pageDesc;
		}

		/**
		 * @param pageDesc the pageDesc to set
		 */
		public void setPageDesc(String pageDesc) {
			this.pageDesc = pageDesc;
		}

	/**
		 * @return the menuID
		 */
		public String getMenuID() {
			return menuID;
		}

		/**
		 * @param menuID the menuID to set
		 */
		public void setMenuID(String menuID) {
			this.menuID = menuID;
		}

		/**
		 * @return the pageID
		 */
		public String getPageID() {
			return pageID;
		}

		/**
		 * @param pageID the pageID to set
		 */
		public void setPageID(String pageID) {
			this.pageID = pageID;
		}

	/**
		 * @return the maxVolt
		 */
		public String getMaxVolt() {
			return maxVolt;
		}

		/**
		 * @param maxVolt the maxVolt to set
		 */
		public void setMaxVolt(String maxVolt) {
			this.maxVolt = maxVolt;
		}

	/**
		 * @redeviceearthpitIdturn the uplocationID
		 */
		public String getUplocationID() {
			return uplocationID;
		}

		/**
		 * @param uplocationID the uplocationID to set
		 */
		public void setUplocationID(String uplocationID) {
			this.uplocationID = uplocationID;
		}

		/**
		 * @return the updeviceId
		 */
		public String getUpdeviceId() {
			return updeviceId;
		}

		/**
		 * @param updeviceId the updeviceId to set
		 */
		public void setUpdeviceId(String updeviceId) {
			this.updeviceId = updeviceId;
		}

	/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}

		/**
		 * @return the alertFlag
		 */
		public String getAlertFlag() {
			return alertFlag;
		}

		/**
		 * @param alertFlag the alertFlag to set
		 */
		public void setAlertFlag(String alertFlag) {
			this.alertFlag = alertFlag;
		}

		/**
		 * @return the empNo
		 */
		public String getEmpNo() {
			return empNo;
		}

		/**
		 * @param empNo the empNo to set
		 */
		public void setEmpNo(String empNo) {
			this.empNo = empNo;
		}

		/**
		 * @return the dOF
		 */
		public String getdOF() {
			return dOF;
		}

		/**
		 * @param dOF the dOF to set
		 */
		public void setdOF(String dOF) {
			this.dOF = dOF;
		}

		/**
		 * @return the mSD
		 */
		public String getmSD() {
			return mSD;
		}

		/**
		 * @param mSD the mSD to set
		 */
		public void setmSD(String mSD) {
			this.mSD = mSD;
		}

		/**
		 * @return the actionTaken
		 */
		public String getActionTaken() {
			return actionTaken;
		}

		/**
		 * @param actionTaken the actionTaken to set
		 */
		public void setActionTaken(String actionTaken) {
			this.actionTaken = actionTaken;
		}

		/**
		 * @return the actionDate
		 */
		public String getActionDate() {
			return actionDate;
		}

		/**
		 * @param actionDate the actionDate to set
		 */
		public void setActionDate(String actionDate) {
			this.actionDate = actionDate;
		}

		/**
		 * @return the delayTime
		 */
		public String getDelayTime() {
			return delayTime;
		}

		/**
		 * @param delayTime the delayTime to set
		 */
		public void setDelayTime(String delayTime) {
			this.delayTime = delayTime;
		}

		/**
		 * @return the groupId
		 */
		public String getGroupId() {
			return groupId;
		}

		/**
		 * @param groupId the groupId to set
		 */
		public void setGroupId(String groupId) {
			this.groupId = groupId;
		}

		/**
		 * @return the repeatTime
		 */
		public String getRepeatTime() {
			return repeatTime;
		}

		/**
		 * @param repeatTime the repeatTime to set
		 */
		public void setRepeatTime(String repeatTime) {
			this.repeatTime = repeatTime;
		}

		/**
		 * @return the failureType
		 */
		public String getFailureType() {
			return failureType;
		}

		/**
		 * @param failureType the failureType to set
		 */
		public void setFailureType(String failureType) {
			this.failureType = failureType;
		}

		/**
		 * @return the diffTime
		 */
		public String getDiffTime() {
			return diffTime;
		}

		/**
		 * @param diffTime the diffTime to set
		 */
		public void setDiffTime(String diffTime) {
			this.diffTime = diffTime;
		}

	/**
	 * @return the actions
	 */
	public String getActions() {
		return actions;
	}

	/**
	 * @param actions the actions to set
	 */
	public void setActions(String actions) {
		this.actions = actions;
	}

	public String getRolescount() {
		return rolescount;
	}

	public void setRolescount(String rolescount) {
		this.rolescount = rolescount;
	}

	public String getPagescount() {
		return pagescount;
	}

	public void setPagescount(String pagescount) {
		this.pagescount = pagescount;
	}

	public String getActioncount() {
		return actioncount;
	}

	public void setActioncount(String actioncount) {
		this.actioncount = actioncount;
	}

	public String getCategorycount() {
		return categorycount;
	}

	public void setCategorycount(String categorycount) {
		this.categorycount = categorycount;
	}

	public String getZonecount() {
		return zonecount;
	}

	public void setZonecount(String zonecount) {
		this.zonecount = zonecount;
	}

	public String getUsertypecount() {
		return usertypecount;
	}

	public void setUsertypecount(String usertypecount) {
		this.usertypecount = usertypecount;
	}

	public String getRolepermissioncount() {
		return rolepermissioncount;
	}

	public void setRolepermissioncount(String rolepermissioncount) {
		this.rolepermissioncount = rolepermissioncount;
	}

	public String getLocationipcount() {
		return locationipcount;
	}

	public void setLocationipcount(String locationipcount) {
		this.locationipcount = locationipcount;
	}

	/**
	 * @return the actionDesc
	 */
	public String getActionDesc() {
		return actionDesc;
	}

	/**
	 * @param actionDesc the actionDesc to set
	 */
	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}

	/**
	 * @return the moduleDesc
	 */
	public String getModuleDesc() {
		return moduleDesc;
	}

	/**
	 * @param moduleDesc the moduleDesc to set
	 */
	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}

	/**
	 * @return the roleDesc
	 */
	public String getRoleDesc() {
		return roleDesc;
	}

	/**
	 * @param roleDesc the roleDesc to set
	 */
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	/**
	 * @return the receivedDate
	 */
	public String getReceivedDate() {
		return ReceivedDate;
	}

	/**
	 * @param receivedDate the receivedDate to set
	 */
	public void setReceivedDate(String receivedDate) {
		ReceivedDate = receivedDate;
	}

	/**
	 * @return the voltage
	 */
	public String getVoltage() {
		return voltage;
	}

	/**
	 * @param voltage the voltage to set
	 */
	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}

	/**
	 * @return the deviceName
	 */
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * @return the escalationIDs
	 */
	public String getEscalationIDs() {
		return escalationIDs;
	}

	/**
	 * @param escalationIDs the escalationIDs to set
	 */
	public void setEscalationIDs(String escalationIDs) {
		this.escalationIDs = escalationIDs;
	}

	/**
	 * @return the escalationTypes
	 */
	public String getEscalationTypes() {
		return escalationTypes;
	}

	/**
	 * @param escalationTypes the escalationTypes to set
	 */
	public void setEscalationTypes(String escalationTypes) {
		this.escalationTypes = escalationTypes;
	}

	/**
	 * @return the escalationLevels
	 */
	public String getEscalationLevels() {
		return escalationLevels;
	}

	/**
	 * @param escalationLevels the escalationLevels to set
	 */
	public void setEscalationLevels(String escalationLevels) {
		this.escalationLevels = escalationLevels;
	}

	/**
	 * @return the sMSMessages
	 */
	public String getsMSMessages() {
		return sMSMessages;
	}

	/**
	 * @param sMSMessages the sMSMessages to set
	 */
	public void setsMSMessages(String sMSMessages) {
		this.sMSMessages = sMSMessages;
	}

	/**
	 * @return the eMailMessages
	 */
	public String geteMailMessages() {
		return eMailMessages;
	}

	/**
	 * @param eMailMessages the eMailMessages to set
	 */
	public void seteMailMessages(String eMailMessages) {
		this.eMailMessages = eMailMessages;
	}

	/**
	 * @return the escalationIntervals
	 */
	public String getEscalationIntervals() {
		return escalationIntervals;
	}

	/**
	 * @param escalationIntervals the escalationIntervals to set
	 */
	public void setEscalationIntervals(String escalationIntervals) {
		this.escalationIntervals = escalationIntervals;
	}

	/**
	 * @return the repeatIntervals
	 */
	public int getRepeatIntervals() {
		return repeatIntervals;
	}

	/**
	 * @param repeatIntervals the repeatIntervals to set
	 */
	public void setRepeatIntervals(int repeatIntervals) {
		this.repeatIntervals = repeatIntervals;
	}

	/**
	 * @return the deviceearthpitId
	 */
	public String getDeviceearthpitId() {
		return deviceearthpitId;
	}

	/**
	 * @param deviceearthpitId the deviceearthpitId to set
	 */
	public void setDeviceearthpitId(String deviceearthpitId) {
		this.deviceearthpitId = deviceearthpitId;
	}

	/**
	 * @param deviceName the deviceName to set
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getEarthPitValues() {
		return earthPitValues;
	}

	public void setEarthPitValues(String earthPitValues) {
		this.earthPitValues = earthPitValues;
	}

	public String getEarthPitNames() {
		return earthPitNames;
	}

	public void setEarthPitNames(String earthPitNames) {
		this.earthPitNames = earthPitNames;
	}

	public String getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}

	public String getLocationHeadsMobile() {
		return locationHeadsMobile;
	}

	public void setLocationHeadsMobile(String locationHeadsMobile) {
		this.locationHeadsMobile = locationHeadsMobile;
	}

	public String getEmailIDs() {
		return emailIDs;
	}

	public void setEmailIDs(String emailIDs) {
		this.emailIDs = emailIDs;
	}

	public String getInstalledDate() {
		return installedDate;
	}

	public void setInstalledDate(String installedDate) {
		this.installedDate = installedDate;
	}

	public String getDeviceSno() {
		return deviceSno;
	}

	public void setDeviceSno(String deviceSno) {
		this.deviceSno = deviceSno;
	}

	public String getDateandTime() {
		return dateandTime;
	}

	public void setDateandTime(String dateandTime) {
		this.dateandTime = dateandTime;
	}

	public String getEarthpitID() {
		return earthpitID;
	}

	public void setEarthpitID(String earthpitID) {
		this.earthpitID = earthpitID;
	}

	public String getEarthpitName() {
		return earthpitName;
	}

	public void setEarthpitName(String earthpitName) {
		this.earthpitName = earthpitName;
	}

	public int getLocationIpCode() {
		return locationIpCode;
	}

	public void setLocationIpCode(int locationIpCode) {
		this.locationIpCode = locationIpCode;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getLocationID() {
		return locationID;
	}

	public void setLocationID(String locationID) {
		this.locationID = locationID;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getZones() {
		return zones;
	}

	public void setZones(String zone) {
		this.zones = zone;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public int getOperation() {
		return operation;
	}

	public void setOperation(int operation) {
		this.operation = operation;
	}

	public int getModuleID() {
		return moduleID;
	}

	public void setModuleID(int moduleID) {
		this.moduleID = moduleID;
	}

	public int getActionID() {
		return actionID;
	}

	public void setActionID(int actionID) {
		this.actionID = actionID;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getEditedBy() {
		return editedBy;
	}

	public void setEditedBy(String editedBy) {
		this.editedBy = editedBy;
	}

	public String getEditedOn() {
		return editedOn;
	}

	public void setEditedOn(String editedOn) {

		this.editedOn = editedOn;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public int getZoneID() {
		return zoneID;
	}

	public void setZoneID(int zoneID) {
		this.zoneID = zoneID;
	}

	public int getUserTypeID() {
		return userTypeID;
	}

	public void setUserTypeID(int userTypeID) {
		this.userTypeID = userTypeID;
	}

	/**
	 * @return the earthPitCount
	 */
	public String getEarthPitCount() {
		return earthPitCount;
	}

	/**
	 * @param earthPitCount the earthPitCount to set
	 */
	public void setEarthPitCount(String earthPitCount) {
		this.earthPitCount = earthPitCount;
	}
	
	
}