package com.techelevator.model;

import java.time.LocalDate;

public class UserTask {
	private int userTaskId;
	private int applianceId;
	private int assetId;
	private int infrastructureId;
	private int homeId;
	private int taskId;
	private int maintenanceDuration;
	private String dateLastPerformed;
	private String taskDescription;
	private String associatedProducts;
	
	public int getUserTaskId() {
		return userTaskId;
	}

	public void setUserTaskId(int userTaskId) {
		this.userTaskId = userTaskId;
	}

	public int getApplianceId() {
		return applianceId;
	}

	public void setApplianceId(int applianceId) {
		this.applianceId = applianceId;
	}

	public int getAssetId() {
		return assetId;
	}

	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}

	public int getInfrastructureId() {
		return infrastructureId;
	}

	public void setInfrastructureId(int infrastructureId) {
		this.infrastructureId = infrastructureId;
	}

	public int getHomeId() {
		return homeId;
	}

	public void setHomeId(int homeId) {
		this.homeId = homeId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getDateLastPerformed() {
		return dateLastPerformed;
	}

	public void setDateLastPerformed(String dateLastPerformed) {
		this.dateLastPerformed = dateLastPerformed;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getAssociatedProducts() {
		return associatedProducts;
	}

	public void setAssociatedProducts(String associatedProducts) {
		this.associatedProducts = associatedProducts;
	}

	@Override
	public String toString() {
		return "UserTask [userTaskId=" + userTaskId + ", applianceId=" + applianceId + ", assetId=" + assetId
				+ ", infrastructureId=" + infrastructureId + ", homeId=" + homeId + ", taskId=" + taskId
				+ ", dateLastPerformed=" + dateLastPerformed + ", taskDescription=" + taskDescription
				+ ", associatedProducts=" + associatedProducts + "]";
	}

	public int getMaintenanceDuration() {
		return maintenanceDuration;
	}

	public void setMaintenanceDuration(int maintenanceDuration) {
		this.maintenanceDuration = maintenanceDuration;
	}
	
	
}
