package com.techelevator.model;

public class Task {
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private int taskId;
	private String type;
	private String taskName;
	private String taskDescription;
	private String reason;
	private String taskURL;
	private int taskInterval;
	
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	
	
	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", type=" + type + ", taskName=" + taskName + ", taskDescription="
				+ taskDescription + ", reason=" + reason + ", taskURL=" + taskURL + ", taskInterval=" + taskInterval
				+ "]";
	}

	public int getTaskInterval() {
		return taskInterval;
	}

	public void setTaskInterval(int taskInterval) {
		this.taskInterval = taskInterval;
	}

	public String getTaskURL() {
		return taskURL;
	}

	public void setTaskURL(String taskURL) {
		this.taskURL = taskURL;
	}



	public Task() {
		// TODO Auto-generated constructor stub
	}

	

	
	
	

}
