package com.techelevator.interfaces;

import java.util.List;

import com.techelevator.model.Appliance;


public interface ApplianceDAO {
	//CRUD - Create, Read, Update, Delete
	
		// Create
		public int addAppliance(Appliance aAppliance);

		// Read
		public Appliance getApplianceById(long aApplianceId); // Select a Appliance by it's ID (Primary Key)

		public Appliance getApplianceByType(String aApplianceType);

		public List<Appliance> getAllAppliancesByType(String aApplianceType, int homeId); // Select a Appliance by it's Type

		public List<Appliance> getAllAppliances(int homeId); // Return all the Appliances in a home
		
		// Update
		public void changeApplianceData(Appliance updatedAppliance);
		
		// Delete
		public void deleteAppliance(String aApplianceId);
		
		public List<Integer> getMaintenanceDurationsForUser(int userId);
}
