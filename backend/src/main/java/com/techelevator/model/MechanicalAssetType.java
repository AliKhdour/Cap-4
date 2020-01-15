package com.techelevator.model;

public class MechanicalAssetType {

	private int mechanicalAssetTypeId;	
	private String type;
	
	public int getMechanicalAssetTypeId() {
		return mechanicalAssetTypeId;
	}
	public void setMechanicalAssetTypeId(int mechanicalAssetTypeId) {
		this.mechanicalAssetTypeId = mechanicalAssetTypeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "MechanicalAssetType [mechanicalAssetTypeId=" + mechanicalAssetTypeId + ", type=" + type + "]";
	}
	
}
