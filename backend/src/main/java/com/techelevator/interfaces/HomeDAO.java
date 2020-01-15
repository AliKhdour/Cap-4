package com.techelevator.interfaces;

import java.util.List;

import com.techelevator.model.Home;


public interface HomeDAO {
	//CRUD - Create, Read, Update, Delete
	
		// Create
		public Home addHome(Home aHome);

		// Read
		public Home getHomeById(String aHomeId); // Select a Home by it's ID (Primary Key)

		public List<Home> getHomesByOwner(int userId);	//	Select a Home by it's Owner (userID - Foreign Key)

		public List<Home> getAllHomes(); // Return all the Homes
		
		// Update
		public void changeHomeData(Home updatedHome);
		
		// Delete
		public void deleteHome(String aHomeId);
}
