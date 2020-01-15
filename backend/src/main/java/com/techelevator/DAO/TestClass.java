package com.techelevator.DAO;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.interfaces.UserTaskDAO;
import com.techelevator.model.Home;
import com.techelevator.model.MechanicalAsset;
import com.techelevator.model.Profile;
import com.techelevator.model.Task;

public class TestClass {

	public static void main(String[] args) {
		
		Home aHome = new Home();
		aHome.setHomeId(1);
		aHome.setUserId(1);
		aHome.setAddress("12345 Test Street");
		aHome.setAddressLineTwo(null);
		aHome.setCity("Lakewood");
		aHome.setState("OH");
		aHome.setZip(44107);
		aHome.setOccupants(3);
		
		Profile Mike = new Profile();
		Mike.setId(1);
		Mike.setFirstName("Mike");
		Mike.setLastName("Trout");
		Mike.setEmail("BBall1@mlb.com");
		Mike.setNotificationPreference("SMS");
		
		Task aTask = new Task();
		aTask.setTaskId(1);
		aTask.setType("Water Heater");
		aTask.setTaskName("Flush");
		aTask.setTaskDescription("Flush out hot water from the water heater to remove accumulated sediment.");
		aTask.setReason("Reason");
		aTask.setTaskURL("www.coolmath.com");
		aTask.setTaskInterval(6);
		
		MechanicalAsset aMA = new MechanicalAsset();
		aMA.setHomeId(1);
		aMA.setId(1);
		aMA.setType("Water Heater");
		SingleConnectionDataSource dataSource;
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/homehelper");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/* The following line disables autocommit for connections
		 * returned by this DataSource. This allows us to rollback
		 * any changes after each test */
		dataSource.setAutoCommit(false);
	    UserTaskDAO userTaskDAO = new JDBCUserTaskDAO(dataSource);
	    
		
		
		
		
		
		// TODO Auto-generated method stub

	}

}
