/**
* @author  :Prasad
* @version :1.0
* @since   2015-10-09
* @ModifiedDate	:2015-11-05.
*/
package com.hpcl.utill;

public class SchemaDef {
	
	/*
	 * Menus ID
	 * */
	public static int DASHBOARD=1;
	public static int EARTHPITMONITORING=2;
	public static int ALERTMANAGEMENT=3;
	public static int CONFIGURATION=4;
	public static int ADMINISTRATION=5;
	  
	public static int PAGEROLES=1;
	public static int PAGECATEGORY=2;
	public static int PAGEZONE=3;
	public static int PAGEUSERTYPE=4;
	public static int PAGEROLESPERMISSION=5;
	public static int PAGELOCATIONIP=6;
	
	
	public static int PAGEEMPLOYEE=7;
	public static int PAGELOCATION=8;
	public static int PAGEDEVICE=9;
	public static int PAGEEARTHPIT=10;
	public static int PAGEEASCALATION=11;
	public static int PAGEDEVICEEARTHPIT=12;
	
	public static int PAGESELECT=1;
	public static int PAGEUPDATE=2;
	public static int PAGEDELETE=3;
	public static int PAGEINSERT=4;
	

	
   /*
    * Table names
    */
	public static String ROLE="m_role";
	public static String MODULE="m_module";
	public static String ACTION="m_action";
	public static String CATEGORY="m_category";
	public static String ZONE="m_zone";
	public static String USERTYPE="m_usertype";
	public static String m_employees="m_employees";
	public static String M_LOCATIONS="m_locations";
	public static String M_DEVICE="m_device";
	public static String M_LOCATIONIP="m_locationip";
	public static String M_EARTHPITS ="m_earthpits";
	public static String M_DEVICE_EARTHPITS="m_device_earthpits";
	public static String M_ESCALATION="m_escalation";
	public static String TR_DASHBOARD="tr_dashboard";
	public static String M_ROLEPERIMISSION="m_roleperimissions";
	public static String TR_ALERTMANAGMENT="tr_alertmanagment";
	public static String M_GROUPS="m_groups";
	public static String MENU_PAGE="m_menupage";
	public static String M_APPSETTING="m_appsetting";
	
	/*
	 * Adminstration columns
	 */
	public static String ROLEID="RoleID";
	public static String DESCRIPTION="Description";
	public static String STATUS="Status";
	public static String CREATEDBY="CreatedBy";
	public static String CREATEDON="CreatedOn";
	public static String EDITEDBY="EditedBy";
	public static String EDITEDON="EditedOn";
	public static String MODULEID="ModuleID";
	public static String ACTIONID="ActionID";
	public static String PAGEID="PageID";
	public static String CATEGORYID="CategoryID";
	public static String ZONEID="ZoneID";
	public static String USERTYPEID="UserTypeID";
	
		/*
	 * Configuration location columns
	 */
	public static String LOCATIONID="locationID";
	public static String LOCATIONNAME="locationName";
	public static String STATE="state";
	public static String TOWN="town";
	public static String ZONES="zones";
	public static String LOCATIONTYPE="locationType";

	/*
	 * Configuration locationip  columns
	 */
	public static String LOCATIONIPCODE="locationIpCode";
	public static String LOCATION_ID = "locationId";
	public static String LOCATION="location";
	public static String IPADDRESS="ipAddress";
	
	/*
	 * Configuration Earthpits columns
	 */
	public static String EARTHPIT_ID="earthpitid";
	public static String EARTHPIT_NAME = "earthpitname";
	public static String EARTHPIT_MAPPEDSTATUS = "MappedStatus";

	/*
	 * Configuration device columns
	 */
	public static String DEVICE_ID="deviceId";
	public static String EARTHPIT_VALUES="earthPitValues";
	public static String EARTHPIT_NAMES="earthPitNames";
	public static String TIME_INTERVAL="timeInterval";
	public static String LOCATION_HEAD_MOBILE="locationHeadsMobile";
	public static String EMAIL_ID="emailIDs";
	public static String INSTALL_DATE="installedDate";
	public static String DEVICE_SNO="deviceSno";
	public static String DATE_AND_TIME="dateandTime";
	
	
	
	
	public static String DEVICE_NAME="deviceName";
	public static String DEVICE_EARTHPIT_ID="deviceearthpitId";
	
	
	/*
	 * Configuration M_escalation columns
	 */
	 public static String LOCN="LOCN";
	public static String ESCALATIONID="escalationID";
	public static String ESCALATIONTYPE="escalationType";
	public static String ESCALATIONLEVEL="escalationLevel";
	public static String SMSMESSAGE="sMSMessage";
	public static String EMAILMESSAGE="eMailMessage";
	public static String ESCALATIONINTERVAL="escalationInterval";
	public static String REPEATEINTERVAL="repeatInterval";
	
	/*
	 * Configuration TR_DASHBOARD columns
	 */
	
	public static  String RECEIVEDDATE="ReceivedDate";
    public static String VOLTAGE="voltage";
    
    /*
   	 * Configuration tr_alertmanagment columns
        */
       public static String ID="Id";
       public static String ALERTFLAG="AlertFlag";
       public static String EMPNO="EmpNo";
       public static String DOR="DOF";
       public static String MSD="Msd";
       public static String ACTIONTAKEN="ActionTaken";
       public static String ACTIONDATE="ActionDate";
       public static String DELAYTIME="DelayTime";
       public static String GROUPID="GroupId";
       public static String REPEATTIME="RepeatTime";
   	
    
	/*
	 * All modules status
	 */
	public static String ACTIVE_FLAG="A";
	public static String INACTIVE_FLAG="I";
	
	/* MaxVolts
	 * */
	public static String MAX_VOLTS="MaxVolt";
	
	
	/*
	 * groups table columns
	 * 
	 */
	
	public static String GROUPNAME="GroupName";
	public static String LEVEL="Level";
	
	
	/*
	 * DateSearch fileds
	 */
	public static String FROMDATETEME="CreatedOn";
	public static String TODATETIME="CreatedOn";
	
	//for appsetting
	public static String EARTHPIT="Earthpit";
	public static String MAXDEVICES="MaxDevices";
	
	//HPCL Ldap Location table columns
		public static String LDP_LOCATIONID="LOCNCD";
		public static String LDP_LOCNAME="NAME";
		public static String LDP_LOCADDRESS1="ADD1";
		public static String LDP_LOCADDRESS2="ADD2";
		public static String LDP_LOCADDRESS3="ADD3";
		public static String LDP_LOCADDRESS4="ADD4";
		public static String LDP_LOCCITY="CITY";
		public static String LDP_LOCSTATE="STATE";
		public static String LDP_LOCSTATEDESC="STATEDESC";
		public static String LDP_LOCPINCODE="PINCODE";
		public static String LDP_LOCTOWNCD="TOWNCD";
		public static String LDP_LOCTOWNDESC="TOWNDESC";
		public static String LDP_LOCZONECD="ZONECD";
		public static String LDP_LOCZONEDESC="ZONEDESC";
		public static String LDP_LOCCONTOFF="CONTOFF";
		public static String LDP_LOCCONTDESC="CONTDESC";
		public static String LDP_LOCSBU="SBU";
		public static String LDP_LOCSBUDESC="SBUDESC";
		public static String LDP_LOCSTATE1="STATE1";
		public static String LDP_LOCSTATEDESC1="STATEDESC1";
		public static String LDP_LOCTYPE="LOCTYPE";
		
		
		//HPCL m_LocationS table columns	
		public static String ADD1="ADD1";
		public static String ADD2="ADD2";
		public static String ADD3="ADD3";
		public static String ADD4="ADD4";
		public static String CITY="CITY";
		public static String STATEDESC="STATEDESC";
		public static String PINCODE="PINCODE";
		public static String TOWNCD="TOWNCD";
		public static String TOWNDESC="TOWNDESC";
		public static String ZONECD="ZONECD";
		public static String ZONEDESC="ZONEDESC";
		public static String CONTOFF="CONTOFF";
		public static String CONTDESC="CONTDESC";
		public static String SBU="SBU";
		public static String SBUDESC="SBUDESC";
		public static String STATE1="STATE1";
		public static String STATEDESC1="STATEDESC1";
		public static String LOCTYPE="LOCTYPE";
			
		
		
		
		//HPCL Ldap EMPLOYEEE table columns
		public static String LDAP_EMP_NO="EMP_NO";
		public static String LDAP_EMP_SAL_GROUP="EMP_SAL_GROUP";
		public static String LDAP_EMP_REP_TO="EMP_REP_TO";
		public static String LDAP_EMAIL_ID="EMAIL_ID";
		public static String LDAP_LOCN="LOCN";
		public static String LDAP_LOCATION="LOCATION";
		public static String LDAP_EMP_NAME_ERP="EMP_NAME_ERP";
		public static String LDAP_EMP_DESG_ERP="EMP_DESG_ERP";
		public static String LDAP_SBUCD="SBUCD";
		public static String LDAP_EMP_ROCODE="EMP_ROCODE";
		public static String LDAP_EMP_ZONECD="EMP_ZONECD";
		public static String LDAP_EMP_LOCTYPE="EMP_LOCTYPE";
		public static String LDAP_EMP_BUCODE="EMP_BUCODE";
		public static String LDAP_EMP_ERP_STATUS="EMP_ERP_STATUS";
		public static String LDAP_YAHMCO="YAHMCO";
		public static String LDAP_MAILING_NAME="MAILING_NAME";
		
		
		//HPCL m_employees table columns
		public static String EMPLOYEEID="employeeID";
		public static String FIRSTNAME="firstName";
		public static String LASTNAME="lastName";
		public static String EMPLOYENAMEE="employeeName";
		public static String LACATION="location";
		public static String ROLES="roles";
		public static String USERTYPES="userTypes";
		public static String PASSWORD="password";
		public static String EMP_EMAIL="emailId";
		public static String EMP_PHONENO="PhoneNo";
		
		
}

