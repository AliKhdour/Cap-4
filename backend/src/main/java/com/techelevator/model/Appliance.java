package com.techelevator.model;

import java.time.LocalDate;

public class Appliance {
	private String type;
	private String age;
	private String modelNumber;
	private int applianceId;
	private int homeId;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public int getApplianceId() {
		return applianceId;
	}
	public void setApplianceId(int applianceId) {
		this.applianceId = applianceId;
	}
	public int getHomeId() {
		return homeId;
	}
	public void setHomeId(int homeId) {
		this.homeId = homeId;
	}
	@Override
	public String toString() {
		return "Appliance [type=" + type + ", age=" + age + ", modelNumber=" + modelNumber + ", applianceId="
				+ applianceId + ", homeId=" + homeId + "]";
	}
	
	
}
