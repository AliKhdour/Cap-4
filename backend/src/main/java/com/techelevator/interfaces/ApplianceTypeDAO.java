package com.techelevator.interfaces;

import java.util.List;

import com.techelevator.model.ApplianceType;

public interface ApplianceTypeDAO {
	//CRUD - Create, Read, Update, Delete
	
	// Create
	public ApplianceType addAnApplianceType(ApplianceType aApplianceType);

	// Read
	public List<ApplianceType> getAllApplianceTypes();

	
	// Delete
	public void deleteApplianceType(String aApplianceTypeId);
}
