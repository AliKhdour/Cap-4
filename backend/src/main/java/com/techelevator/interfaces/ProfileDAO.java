package com.techelevator.interfaces;

import java.util.List;

import com.techelevator.model.JdbcUserDao;
import com.techelevator.model.MaintenanceTask;
import com.techelevator.model.Profile;
import com.techelevator.model.UserDao;

public interface ProfileDAO {
	//each time a user is created, a profile must be created with the same value for id
//	public Profile getProfileForUser(int userId); 
	public Profile createProfile(Profile newProfile);
	public boolean deleteProfile(int userId);
	public List<MaintenanceTask> getTasksForUser(int profileId);
	public Profile getProfileForUser(String userName);
	public boolean updateProfile(int userId, String firstName, String lastName);
}
