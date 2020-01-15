package com.techelevator.authentication;

import java.sql.SQLException;
import java.time.LocalDate;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.DAO.JDBCApplianceDAO;
import com.techelevator.DAO.JDBCApplianceTypeDAO;
import com.techelevator.DAO.JDBCHomeDAO;
import com.techelevator.DAO.JDBCInfrastructureTypeDAO;
import com.techelevator.DAO.JDBCMaintenanceTask;
import com.techelevator.DAO.JDBCMechanicalAssetDAO;
import com.techelevator.DAO.JDBCMechanicalAssetTypeDAO;
import com.techelevator.DAO.JDBCProfileDAO;
import com.techelevator.DAO.JDBCTaskDAO;
import com.techelevator.DAO.JDBCUserTaskDAO;
import com.techelevator.model.Home;
import com.techelevator.model.JdbcUserDao;
import com.techelevator.model.MaintenanceTask;
import com.techelevator.model.MechanicalAsset;
import com.techelevator.model.MechanicalAssetType;
import com.techelevator.model.Profile;
import com.techelevator.model.Task;
import com.techelevator.model.UserTask;

public class DAOintergrationTest {
	private static SingleConnectionDataSource dataSource;

	private JDBCHomeDAO homeDAO;
	private JDBCApplianceDAO applianceDAO;
	private JDBCApplianceTypeDAO applianceTypeDAO;
	private JDBCInfrastructureTypeDAO infrastructreDAO;
	private JDBCMaintenanceTask maintenanceDAO;
	private JDBCMechanicalAssetDAO mechanicAssetDAO;
	private JDBCMechanicalAssetTypeDAO mechanicAssetTypeDAO;
	private JDBCProfileDAO profileDAO;
	private JDBCTaskDAO taskDAO;
	private JDBCUserTaskDAO userTaskDAO;
	private JdbcUserDao UserDao;

	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/homehelper");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/*
		 * The following line disables autocommit for connections returned by this
		 * DataSource. This allows us to rollback any changes after each test
		 */
		dataSource.setAutoCommit(false);
	}

	/*
	 * After all tests have finished running, this method will close the DataSource
	 */
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}

	/*
	 * After each test, we rollback any changes that were made to the database so
	 * that everything is clean for the next test
	 */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	/*
	 * This method provides access to the DataSource for subclasses so that they can
	 * instantiate a DAO for testing
	 */
	protected DataSource getDataSource() {
		return dataSource;
	}

	@Before
	public void setup() {
		homeDAO = new JDBCHomeDAO(dataSource);
		applianceDAO = new JDBCApplianceDAO(dataSource);
		applianceTypeDAO = new JDBCApplianceTypeDAO(dataSource);
		infrastructreDAO = new JDBCInfrastructureTypeDAO(dataSource);
		maintenanceDAO = new JDBCMaintenanceTask(dataSource);
		mechanicAssetDAO = new JDBCMechanicalAssetDAO(dataSource);
		mechanicAssetTypeDAO = new JDBCMechanicalAssetTypeDAO(dataSource);
		profileDAO = new JDBCProfileDAO(dataSource, UserDao);
		taskDAO = new JDBCTaskDAO(dataSource);
		userTaskDAO = new JDBCUserTaskDAO(dataSource);
		
		
		
		
		
	
		
		
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
		aMA.setAge(LocalDate.now());
		aMA.setModelNumber(666);
		aMA.setMaintenanceDuration(7);
		
		MechanicalAssetType aMat = new MechanicalAssetType();
		aMat.setMechanicalAssetTypeId(1);
		aMat.setType("Water Heater");
		
		UserTask userTask = new UserTask();
		userTask.setUserTaskId(1);
		userTask.setApplianceId(1);
		userTask.setAssetId(1);
		userTask.setInfrastructureId(1);
		userTask.setInfrastructureId(1);
		userTask.setHomeId(1);
		userTask.setTaskId(1);
		userTask.setMaintenanceDuration(8);
		userTask.setDateLastPerformed("01-01-2019");
		userTask.setTaskDescription("LOOK BEHIND YOU");
		userTask.setAssociatedProducts(null);
		
		profileDAO.createProfile(Mike);
		
		
		
		
		

	}
}
