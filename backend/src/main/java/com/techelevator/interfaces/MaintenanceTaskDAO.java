package com.techelevator.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.techelevator.model.MaintenanceTask;


public interface MaintenanceTaskDAO {
	//CRUD - Create, Read, Update, Delete
	
	// Create
	public MaintenanceTask addAnMaintenanceTask(MaintenanceTask aMaintenanceTask);

	// Read
	public MaintenanceTask getMaintenanceTaskById(String aMaintenanceTaskId); // Select a Maintenance Task by it's ID (Primary Key)

	public MaintenanceTask getMaintenanceTaskByName(String aMaintenanceTaskName);

	public List<MaintenanceTask> getAllMaintenanceTasksByName(String aMaintenanceTaskName); // Select a Maintenance Task by it's Name
	
	public List<MaintenanceTask> getAllMaintenanceTasksByAppliance(int applianceId); // Select a Maintenance Task by it's Appliance Id

	public List<MaintenanceTask> getAllMaintenanceTasksByAsset(int mechanicalAssetId); // Select a Maintenance Task by it's Mechanical Asset Id

	public List<MaintenanceTask> getAllMaintenanceTasksByHome(int homeId); // Select a Maintenance Task by it's Home Id

	public List<MaintenanceTask> getAllMaintenanceTasks(); // Return all the Maintenance Task
	
	public List<MaintenanceTask> getAllMaintenanceTasksFromADate(LocalDate aDate);	//	Select all Maintenance Tasks from a specific date
	
	// Update
	public void changeMaintenanceTaskData(MaintenanceTask updatedMaintenanceTask);
	
	// Delete
	public void deleteMaintenanceTask(String aMaintenanceTaskId);
	

}
