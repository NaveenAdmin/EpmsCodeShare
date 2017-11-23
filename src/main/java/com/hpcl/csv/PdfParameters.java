package com.hpcl.csv;

public class PdfParameters {
	private String locationName;
	private String deviceName;
	private String earthpitName;
	private String voltage;
	private String receivedDate;
	
	private String locationID;
	private String deviceId;
	private String fromdatetime;
	private String todatetime;

	public PdfParameters(String locationName, String deviceName, String earthpitName, String voltage,String receivedDate) {
		this.locationName = locationName;
		this.deviceName = deviceName;
		this.earthpitName = earthpitName;
		this.voltage = voltage;
		this.receivedDate = receivedDate;
	}
	
	
	

	public String getLocationID() {
		return locationID;
	}




	public void setLocationID(String locationID) {
		this.locationID = locationID;
	}




	public String getDeviceId() {
		return deviceId;
	}




	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
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




	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getEarthpitName() {
		return earthpitName;
	}

	public void setEarthpitName(String earthpitName) {
		this.earthpitName = earthpitName;
	}

	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}

	public String getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}


}