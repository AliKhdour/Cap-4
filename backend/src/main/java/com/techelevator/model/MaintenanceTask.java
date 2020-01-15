package com.techelevator.model;

import java.time.LocalDate;

public class MaintenanceTask {
	private int taskId;
	private int applianceId;
	private int assetId;
	private int homeId;
	private String taskName;
	private LocalDate dateLastPerformed;
	private String taskDescription;
	private String url;
	private String associatedProducts;

	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
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
	public int getHomeId() {
		return homeId;
	}
	public void setHomeId(int homeId) {
		this.homeId = homeId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public LocalDate getDateLastPerformed() {
		return dateLastPerformed;
	}
	public void setDateLastPerformed(LocalDate dateLastPerformed) {
		this.dateLastPerformed = dateLastPerformed;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAssociatedProducts() {
		return associatedProducts;
	}
	public void setAssociatedProducts(String associatedProducts) {
		this.associatedProducts = associatedProducts;
	}
	
}
