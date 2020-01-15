package com.techelevator.model;


public class ApplianceType {
	
	private int applianceTypeId;	
	private String type;
	
	public int getApplianceTypeId() {
		return applianceTypeId;
	}
	public void setApplianceTypeId(int applianceTypeId) {
		this.applianceTypeId = applianceTypeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "ApplianceType [applianceTypeId=" + applianceTypeId + ", type=" + type + "]";
	}


}
