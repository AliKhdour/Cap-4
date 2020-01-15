package com.techelevator.DAO;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.authentication.PasswordHasher;
import com.techelevator.interfaces.ProfileDAO;
import com.techelevator.model.JdbcUserDao;
import com.techelevator.model.MaintenanceTask;
import com.techelevator.model.MechanicalAsset;
import com.techelevator.model.Profile;
import com.techelevator.model.User;
import com.techelevator.model.UserDao;


@Component
public class JDBCProfileDAO implements ProfileDAO {
	JdbcTemplate jdbcTemplate;
	UserDao userDao;
	
	@Autowired
	public JDBCProfileDAO(DataSource dataSource, UserDao userDao) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.userDao = userDao;
	}

	@Override
	public Profile createProfile(Profile newProfile) {
		//Grab userID from users table and set the userID for the profile to match
		newProfile.setId((int) userDao.getUserByUsername(newProfile.getEmail()).getId());
		String sql = "INSERT INTO user_account ( user_id, first_name, last_name, email_address, notification_preference, phone_number ) VALUES ( ?, ?, ?, ?, ?, ?)";
		String sqlGetProfile = "SELECT * FROM user_account WHERE user_id = ?";
		jdbcTemplate.update(sql, newProfile.getId(), newProfile.getFirstName() , newProfile.getLastName(), newProfile.getEmail(), newProfile.getNotificationPreference(), newProfile.getPhoneNumber());
		
		
		return getProfileForUser(newProfile.getEmail());
	}
	
	@Override
	public Profile getProfileForUser(String userName) {
		String sql = "SELECT * FROM user_account WHERE email_address = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userName);
		
		Profile userProfile = new Profile();
		while(results.next()) {
			userProfile = mapRowToProfile(results);
		}
		return userProfile;
	}


	@Override
	public boolean deleteProfile(int userId) {
		
		return false;
	}

	@Override
	public boolean updateProfile(int userId, String firstName, String lastName) {
		String sql = "UPDATE user_account SET first_name = ?, last_name = ? WHERE user_id = ?";
		jdbcTemplate.update(sql, firstName, lastName, userId);
		return true;
	}

	@Override
	public List<MaintenanceTask> getTasksForUser(int profileId) {
		String assetSql = "SELECT task_name, date_last_performed, task_description, url, associated_products"+
				" FROM user_task JOIN mechanical_asset "+
				" ON user_task.asset_id = mechanical_asset.asset_id "+
				" JOIN home ON mechanical_asset.home_id = home.home_id join user_account "+
				" on home.user_id = user_account.user_id WHERE user_account.user_id = ?; ";
		
		String applianceSql = " SELECT task_name, date_last_performed, task_description, "
				+" url, associated_products FROM user_task JOIN appliance ON user_task.appliance_id "
				+" = appliance.appliance_id JOIN home ON appliance.home_id = home.home_id "
				+" join user_account on home.user_id = user_account.user_id WHERE user_account.user_id = ?;";
		
		List<MaintenanceTask> tasksForUser = new ArrayList<MaintenanceTask>();
		SqlRowSet results = jdbcTemplate.queryForRowSet(assetSql, profileId);
		
		while(results.next()) {
			tasksForUser.add(mapRowToTask(results));
		}
		
		results = jdbcTemplate.queryForRowSet(applianceSql, profileId);
		
		while(results.next()) {
			tasksForUser.add(mapRowToTask(results));
		}
		
		return tasksForUser;
	}

/*----------------- HELPER METHODS -----------------------*/
	
	private Profile mapRowToProfile(SqlRowSet row) {
		Profile profile = new Profile();
		profile.setEmail(row.getString("email_address"));
		profile.setFirstName(row.getString("first_name"));
		profile.setLastName(row.getString("last_name"));
		profile.setNotificationPreference("notification_preference");
		profile.setId(row.getInt("user_id"));
		return profile;
	}
	
	private MaintenanceTask mapRowToTask(SqlRowSet sql) {
		MaintenanceTask task = new MaintenanceTask();
		task.setUrl(sql.getString("url"));
		task.setTaskName(sql.getString("task_name"));
		task.setDateLastPerformed(sql.getDate("date_last_performed").toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate());
		task.setTaskDescription(sql.getString("task_description"));
		task.setAssociatedProducts(sql.getString("associated_products"));
		return task;
	}


	
}
