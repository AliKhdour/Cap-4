package com.techelevator.interfaces;

import java.util.List;

import com.techelevator.model.MechanicalAssetType;


public interface MechanicalAssetTypeDAO {
	//CRUD - Create, Read, Update, Delete
	
	// Create
	public MechanicalAssetType addAMechanicalAssetType(MechanicalAssetType aMechanicalAssetType);

	// Read
	public List<MechanicalAssetType> getAllMechanicalAssetTypes();

	
	// Delete
	public void deleteMechanicalAssetType(String aMechanicalAssetTypeId);
}
