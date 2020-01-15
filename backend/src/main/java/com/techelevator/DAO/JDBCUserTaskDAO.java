package com.techelevator.DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.interfaces.UserTaskDAO;
import com.techelevator.model.Task;
import com.techelevator.model.UserTask;

@Component
public class JDBCUserTaskDAO implements UserTaskDAO{
	JdbcTemplate jdbcTemplate;
	
	public JDBCUserTaskDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<UserTask> getAllUserTasks() {
		List<UserTask> userTaskArray = new ArrayList<UserTask>();
		String sqlQuery = "SELECT * FROM user_task";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery);
		
		while(results.next()) {
			UserTask aUserTask = new UserTask();
			aUserTask = mapRowToUserTask(results);
			userTaskArray.add(aUserTask);
		}
		
		return userTaskArray;
	}

	@Override
	public List<UserTask> getTasksByHomeId(int homeId) {
		List<UserTask> userTaskArray = new ArrayList<UserTask>();
		String sqlQuery = "SELECT * FROM user_task WHERE home_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, homeId);
		
		while(results.next()) {
			UserTask aUserTask = new UserTask();
			aUserTask = mapRowToUserTask(results);
			userTaskArray.add(aUserTask);
		}
		
		return userTaskArray;
	}

	@Override
	public void newUserApplianceTask(int taskId, int applianceId, int homeId) {
		String sqlInsertQuery = "INSERT INTO user_task (appliance_id,home_id,task_id) " +
								"VALUES(?,?,?)"
								;
		
		int newUserTaskId = jdbcTemplate.update(sqlInsertQuery, applianceId, homeId, taskId);
		
	}

	@Override
	public UserTask mapRowToUserTask(SqlRowSet result) {
		
		UserTask aUserTask = new UserTask();
		
		aUserTask.setUserTaskId(result.getInt("user_task_id"));
		aUserTask.setHomeId(result.getInt("home_id"));
		aUserTask.setTaskId(result.getInt("task_id"));
		aUserTask.setDateLastPerformed(result.getString("date_last_performed"));
		aUserTask.setTaskDescription(result.getString("task_description"));
		aUserTask.setAssociatedProducts(result.getString("associated_products"));
		aUserTask.setApplianceId(result.getInt("appliance_id"));
		aUserTask.setInfrastructureId(result.getInt("infrastructure_id"));
		aUserTask.setAssetId(result.getInt("asset_id"));
		aUserTask.setMaintenanceDuration(result.getInt("maintenance_duration"));
		
		return aUserTask;
	}
	
	@Override
	public List<UserTask> getNotifications(int homeId) throws ParseException{
		List<UserTask> allTasks = getTasksByHomeId(homeId);
		List<UserTask> pressingTasks = new ArrayList<UserTask>();
		for(UserTask task: allTasks) {
			String strLastPerformed = task.getDateLastPerformed();
			Date lastPerformed = new SimpleDateFormat("dd/MM/yyyy").parse(strLastPerformed);
			Date today = new Date();
		    long diffInMillies = Math.abs(today.getTime() - lastPerformed.getTime());
		    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		    if(task.getMaintenanceDuration()-diff<=7) {
		    	pressingTasks.add(task);
		    }
		}
		return pressingTasks;
	}

	@Override
	public List<UserTask> getDueDates(int userId) {
		List<UserTask> userTaskArray = new ArrayList<UserTask>();
		String sqlQuery = "SELECT * FROM user_task join home ON user_task.home_id = home.home_id JOIN user_account on home.user_id = user_account.user_id WHERE user.user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, userId);
		while(results.next()) {
			UserTask aUserTask = new UserTask();
			aUserTask = mapRowToUserTask(results);
			userTaskArray.add(aUserTask);
		}
		return userTaskArray;
	}

	@Override
	public void updateLastPerformed(int userTaskId) {
		String sql ="UPDATE user_task SET date_last_performed = ? WHERE user_id = ?";
		SimpleDateFormat sdFormat=new SimpleDateFormat("dd/MM/yyyy");
		jdbcTemplate.update(sql, sdFormat.format(new Date()).toString(), userTaskId);
	}

	@Override
	public UserTask getTaskByUserTaskId(int userTaskId) {
		SqlRowSet row = jdbcTemplate.queryForRowSet("SELECT * FROM user_task WHERE user_task_id = ?", userTaskId);
		UserTask task = mapRowToUserTask(row);
		return task;
	}

}
