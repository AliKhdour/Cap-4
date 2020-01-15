package com.techelevator.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.interfaces.ApplianceDAO;
import com.techelevator.model.Appliance;

@Component
public class JDBCApplianceDAO implements ApplianceDAO {

	
	JdbcTemplate jdbcTemplate;
	@Autowired
	public JDBCApplianceDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int addAppliance(Appliance aAppliance) {
		String sql = "INSERT INTO appliance(home_id, type, age, model_number) VALUES ( ? , ? , ? , ?)";
		String sqlGetNewApplianceId = "SELECT MAX(appliance_id) as appliance_id FROM appliance";
		int applianceId = 0;
		
		jdbcTemplate.update(sql,
							aAppliance.getHomeId(),
							aAppliance.getType(),
							aAppliance.getAge(),
							aAppliance.getModelNumber()
		);
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetNewApplianceId);
		
		while(results.next()) {
			applianceId = results.getInt("appliance_id");
		}
		
		
		return applianceId;
	}

	@Override
	public Appliance getApplianceById(long aApplianceId) {
		Appliance aAppliance = null;

		String sqlSearchAppliancesById = "SELECT * FROM appliance WHERE appliance_id = ?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchAppliancesById, aApplianceId);

		while (results.next()) {
			aAppliance = mapRowToAppliance(results);
		}
		return aAppliance;
	}

	@Override
	public Appliance getApplianceByType(String aApplianceType) {
		Appliance aAppliance = null;

		String aApplianceTypeSearch = "%" + aApplianceType + "%";
		String sqlSearchAppliancesByType = "SELECT * FROM appliance WHERE type ilike ?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchAppliancesByType, aApplianceTypeSearch);

		while (results.next()) {
			aAppliance = mapRowToAppliance(results);
		}
		return aAppliance;
	}

	@Override
	public List<Appliance> getAllAppliancesByType(String aApplianceType, int homeId) {
		List<Appliance> applianceList = new ArrayList<Appliance>();
		
		String aApplianceTypeSearch = "%" + aApplianceType + "%";
		String sqlSearchAppliancesByType = "SELECT * FROM appliance WHERE home_id = ? AND type = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchAppliancesByType, homeId, aApplianceTypeSearch);
		while(results.next()) {
			Appliance anAppliance = mapRowToAppliance(results);
			applianceList.add(anAppliance);
		}
		return applianceList;
	}

	@Override
	public List<Appliance> getAllAppliances(int homeId) {
		List<Appliance> applianceList = new ArrayList<Appliance>();
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT * FROM appliance WHERE home_id =?", homeId);
		
		while(results.next()) {
			applianceList.add(mapRowToAppliance(results));
		}
		return applianceList;
	}

	@Override
	public void changeApplianceData(Appliance updatedAppliance) {
		String sqlUpdateAppliance =	"UPDATE appliance " 
										+ "SET asset_id = " + updatedAppliance.getApplianceId()
										+ ", home_id= " + updatedAppliance.getHomeId()
										+ ", type = " + updatedAppliance.getType()
										+ ", age= " + updatedAppliance.getAge()
										+ ", model_number = " + updatedAppliance.getModelNumber()
										+ " WHERE appliance_id = ? ";
	

		long applianceId = updatedAppliance.getApplianceId();
		jdbcTemplate.update(sqlUpdateAppliance, applianceId);	
	}

	@Override
	public void deleteAppliance(String aApplianceId) {
		jdbcTemplate.update("DELETE * FROM appliance WHERE appliance_id = " + aApplianceId);
		
	}
	
	private int mapDuration(SqlRowSet rowSet) {
		return (rowSet.getInt("maintenance_duration"));
	}
	
	@Override
	public List<Integer> getMaintenanceDurationsForUser(int userId) {
		List<Integer> maintenanceDurations = new ArrayList<Integer>();
		String assetSql = "SELECT maintenance_duration FROM appliance join home "+
		" ON home.home_id = mechasnical_asset.home_id join user_account ON home.user_id = user_account.user_id WHERE user_account.user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(assetSql, userId);
		while(results.next()) {
			maintenanceDurations.add(mapDuration(results));
		}
		
		return maintenanceDurations;
	}
	
	
//	-------------------------------	HELPER METHODS	---------------------------------

	private Appliance mapRowToAppliance(SqlRowSet results) {
		Appliance theAppliance = new Appliance();

		theAppliance.setApplianceId(results.getInt("appliance_id"));
		theAppliance.setHomeId(results.getInt("home_id"));
		theAppliance.setType(results.getString("type"));
		theAppliance.setAge(results.getString("age"));
		theAppliance.setModelNumber(results.getString("model_number"));

		return theAppliance;
	}


}
