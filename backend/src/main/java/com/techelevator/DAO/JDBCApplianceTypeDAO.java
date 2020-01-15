package com.techelevator.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.interfaces.ApplianceTypeDAO;
import com.techelevator.model.ApplianceType;

@Component
public class JDBCApplianceTypeDAO implements ApplianceTypeDAO{

	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCApplianceTypeDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public ApplianceType addAnApplianceType(ApplianceType aApplianceType) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<ApplianceType> getAllApplianceTypes() {
		List<ApplianceType> applianceList = new ArrayList<ApplianceType>();
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT * FROM appliance_type ");
		while(results.next()) {
			applianceList.add(mapRowToApplianceType(results));
		}
		return applianceList;
	}
	@Override
	public void deleteApplianceType(String aApplianceTypeId) {
		// TODO Auto-generated method stub
		
	}
	
//	-------------------------------	HELPER METHODS	---------------------------------

	private ApplianceType mapRowToApplianceType(SqlRowSet results) {
		ApplianceType theAppliance = new ApplianceType();

		theAppliance.setApplianceTypeId(results.getInt("appliance_type_id"));
		theAppliance.setType(results.getString("type"));

		return theAppliance;
	}

}
