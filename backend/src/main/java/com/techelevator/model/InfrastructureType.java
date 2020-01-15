package com.techelevator.model;

public class InfrastructureType {

	private int infrastructureTypeId;	
	private String type;
	
	public int getInfrastructureTypeId() {
		return infrastructureTypeId;
	}
	public void setInfrastructureTypeId(int infrastructureTypeId) {
		this.infrastructureTypeId = infrastructureTypeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "InfrastructureType [infrastructureTypeId=" + infrastructureTypeId + ", type=" + type + "]";
	}
	

	
}
