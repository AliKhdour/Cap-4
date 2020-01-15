package com.techelevator.DAO;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.interfaces.ApplianceDAO;
import com.techelevator.interfaces.MaintenanceTaskDAO;
import com.techelevator.interfaces.MechanicalAssetDAO;
import com.techelevator.model.Appliance;
import com.techelevator.model.MaintenanceTask;
import com.techelevator.model.MechanicalAsset;
@Component
public class JDBCMaintenanceTask implements MaintenanceTaskDAO {
	JdbcTemplate jdbcTemplate;
	MechanicalAssetDAO assetDao;
	ApplianceDAO applianceDao;
	
	@Autowired
	public JDBCMaintenanceTask(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		assetDao = new JDBCMechanicalAssetDAO(dataSource);
		applianceDao = new JDBCApplianceDAO(dataSource);
	}
	
	@Override
	public MaintenanceTask addAnMaintenanceTask(MaintenanceTask aMaintenanceTask) {
		String sql = "INSERT INTO maintenance_task (appliance_id, asset_id,  "
				+ "home_id, task_name, date_last_performed, task_description, url, associated_products )";
		jdbcTemplate.update(sql, aMaintenanceTask.getApplianceId(),
				aMaintenanceTask.getAssetId(),
				aMaintenanceTask.getHomeId(),
				aMaintenanceTask.getTaskName(),
				aMaintenanceTask.getDateLastPerformed(),
				aMaintenanceTask.getTaskDescription(),
				aMaintenanceTask.getUrl());
		return aMaintenanceTask;
	}

	@Override
	public MaintenanceTask getMaintenanceTaskById(String aMaintenanceTaskId) {
		MaintenanceTask task = mapRowToTask(jdbcTemplate.queryForRowSet("SELECT * FROM maintenance_task where task_id=?", aMaintenanceTaskId));
		return task;
	}

	@Override
	public MaintenanceTask getMaintenanceTaskByName(String aMaintenanceTaskName) {
		MaintenanceTask task = mapRowToTask(jdbcTemplate.queryForRowSet("SELECT * FROM maintenance_task where task_name=?", aMaintenanceTaskName));
		return task;
	}

	@Override
	public List<MaintenanceTask> getAllMaintenanceTasksByName(String aMaintenanceTaskName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MaintenanceTask> getAllMaintenanceTasksByAppliance(int applianceId) {
		List<MaintenanceTask> tasks = new ArrayList<MaintenanceTask>();
		SqlRowSet rows = jdbcTemplate.queryForRowSet("SELECT * FROM maintenance_task where appliance_id=?", applianceId);
		while(rows.next()) {
			tasks.add(mapRowToTask(rows));
		}
		return tasks;
	}

	@Override
	public List<MaintenanceTask> getAllMaintenanceTasksByAsset(int mechanicalAssetId) {
		List<MaintenanceTask> tasks = new ArrayList<MaintenanceTask>();
		SqlRowSet rows = jdbcTemplate.queryForRowSet("SELECT * FROM maintenance_task where asset_id=?", mechanicalAssetId);
		while(rows.next()) {
			tasks.add(mapRowToTask(rows));
		}
		return tasks;
	}

	@Override
	public List<MaintenanceTask> getAllMaintenanceTasksByHome(int homeId) {
		List<MaintenanceTask> tasks = new ArrayList<MaintenanceTask>();
		SqlRowSet rows = jdbcTemplate.queryForRowSet("SELECT * FROM maintenance_task where home_id=?", homeId);
		while(rows.next()) {
			tasks.add(mapRowToTask(rows));
		}
		return tasks;
	}

	@Override //needs to be changed if we want a different result
	public List<MaintenanceTask> getAllMaintenanceTasks() {
		List<MaintenanceTask> tasks = new ArrayList<MaintenanceTask>();
		SqlRowSet rows = jdbcTemplate.queryForRowSet("SELECT * FROM maintenance_task");
		while(rows.next()) {
			tasks.add(mapRowToTask(rows));
		}
		return tasks;
	}

	@Override
	public List<MaintenanceTask> getAllMaintenanceTasksFromADate(LocalDate aDate) {
		List<MaintenanceTask> tasks = new ArrayList<MaintenanceTask>();
		SqlRowSet rows = jdbcTemplate.queryForRowSet("SELECT * FROM maintenance_task where ");
		while(rows.next()) {
			tasks.add(mapRowToTask(rows));
		}
		return tasks;
	}

	@Override
	public void changeMaintenanceTaskData(MaintenanceTask updatedMaintenanceTask) {
		String sql ="UPDATE maintenance_task SET appliance_id=?, asset_id=?, "
				+ "home_id=?, task_name=?, date_last_performed=?, task_description=?, url=? WHERE taskId = ?";
		jdbcTemplate.update(sql, updatedMaintenanceTask.getApplianceId(),
				updatedMaintenanceTask.getAssetId(),
				updatedMaintenanceTask.getHomeId(),
				updatedMaintenanceTask.getTaskName(),
				updatedMaintenanceTask.getDateLastPerformed(),
				updatedMaintenanceTask.getTaskDescription(),
				updatedMaintenanceTask.getUrl(),
				updatedMaintenanceTask.getTaskId());
		
		
	}

	@Override
	public void deleteMaintenanceTask(String aMaintenanceTaskId) {
		jdbcTemplate.update("DELETE * FROM maintenance_task WHERE task_id = ?", aMaintenanceTaskId);
		
	}
	
	private MaintenanceTask mapRowToTask(SqlRowSet sql) {
//		 task_id serial NOT NULL,
//	        appliance_id int,
//	        asset_id int,
//	        infrastructure_id int,
//	        home_id int NOT NULL,
//	        task_name varchar(50),
//	        date_last_performed date NOT NULL,
//	        task_description varchar NOT NULL,
//	        url varchar,
//	        associated_products varchar,
		MaintenanceTask task = new MaintenanceTask();
		task.setUrl(sql.getString("url"));
		task.setApplianceId(sql.getInt("appliance_id"));
		task.setAssetId(sql.getInt("asset_id"));
		task.setHomeId(sql.getInt("home_id"));
		task.setTaskName(sql.getString("task_name"));
		task.setDateLastPerformed(sql.getDate("date_last_performed").toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate());
		task.setTaskDescription(sql.getString("task_description"));
		task.setAssociatedProducts(sql.getString("associated_products"));
		return task;
	}



}
