package com.techelevator.interfaces;

import java.util.List;

import com.techelevator.model.MechanicalAsset;


public interface MechanicalAssetDAO {
//CRUD - Create, Read, Update, Delete
	
	// Create
	public MechanicalAsset addAnAsset(MechanicalAsset aMechanicalAsset);
	// Read
	public MechanicalAsset getMechanicalAssetById(String aMechanicalAssetId); // Select a MechanicalAsset by it's ID (Primary Key)

	public List<MechanicalAsset> getAllMechanicalAssetsByType(String aMechanicalAssetType, int homeId); // Select a MechanicalAsset by it's Name

	public List<MechanicalAsset> getAllMechanicalAssets(int homeId); // Return all the MechanicalAsset
	// Update
	public void changeMechanicalAssetData(MechanicalAsset updatedMechanicalAsset);
	// Delete
	public void deleteMechanicalAsset(String aMechanicalAssetId);
	
	public List<Integer> getMaintenanceDurationsForUser(int userId);
	
}
