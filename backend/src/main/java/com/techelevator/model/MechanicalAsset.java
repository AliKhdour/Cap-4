package com.techelevator.model;

import java.time.LocalDate;

public class MechanicalAsset {
	private long id;
	private String type;
	private LocalDate age;
	private int modelNumber;
	private int maintenanceDuration;
	private int homeId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public LocalDate getAge() {
		return age;
	}
	public void setAge(LocalDate age) {
		this.age = age;
	}
	public int getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(int modelNumber) {
		this.modelNumber = modelNumber;
	}
	public int getMaintenanceDuration() {
		return maintenanceDuration;
	}
	public void setMaintenanceDuration(int maintenanceDuration) {
		this.maintenanceDuration = maintenanceDuration;
	}
	public int getHomeId() {
		return homeId;
	}
	public void setHomeId(int homeId) {
		this.homeId = homeId;
	}
	@Override
	public String toString() {
		return "MechanicalAsset [id=" + id + ", type=" + type + ", age=" + age + ", modelNumber=" + modelNumber
				+ ", maintenanceDuration=" + maintenanceDuration + ", homeId=" + homeId + "]";
	}

}
