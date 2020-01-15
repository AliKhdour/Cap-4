package com.techelevator.interfaces;

import java.text.ParseException;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Task;
import com.techelevator.model.UserTask;

public interface UserTaskDAO {
	public List<UserTask>getAllUserTasks();
	public List<UserTask>getTasksByHomeId(int homeId);
	public void newUserApplianceTask(int taskId, int applianceId, int homeId);
	public UserTask mapRowToUserTask(SqlRowSet results);
	public List<UserTask> getNotifications(int homeId) throws ParseException;
	public List<UserTask> getDueDates(int userId);
	public void updateLastPerformed(int userTaskId);
	public UserTask getTaskByUserTaskId(int userTaskId);
	
}
