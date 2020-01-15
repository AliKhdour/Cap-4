package com.techelevator.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.interfaces.TaskDAO;
import com.techelevator.model.Task;

@Component
public class JDBCTaskDAO implements TaskDAO {
	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCTaskDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Task> getAllTasks() {
		
		List<Task> theTasks = new ArrayList<Task>();
		String sqlQuery = "SELECT * FROM task";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery);
		
		while(results.next()) {
			theTasks.add(mapRowToTask(results));
		}
		
		
		return theTasks;
	}

	@Override
	public List<Task> getTasksByType(String type) {
		List<Task> theTasks = new ArrayList<Task>();
		String sqlQuery = "SELECT * FROM task WHERE type = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, type);
		
		while(results.next()) {
			theTasks.add(mapRowToTask(results));
		}
		
		return theTasks;
	}
	
//	-------------------------------	HELPER METHODS	---------------------------------

	private Task mapRowToTask(SqlRowSet results) {
		Task aTask = new Task();
	
		aTask.setTaskId(results.getInt("the_task_id"));
		aTask.setType(results.getString("type"));
		aTask.setTaskName(results.getString("task_name"));
		aTask.setTaskDescription(results.getString("task_description"));
		aTask.setReason(results.getString("reason"));
		aTask.setTaskURL(results.getString("task_url"));
		aTask.setTaskInterval(results.getInt("task_interval"));
		
		return aTask;
	}

	
}
