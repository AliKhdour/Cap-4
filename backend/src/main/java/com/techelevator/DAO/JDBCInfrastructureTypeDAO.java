package com.techelevator.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.interfaces.InfrastructureTypeDAO;
import com.techelevator.model.InfrastructureType;

@Component
public class JDBCInfrastructureTypeDAO implements InfrastructureTypeDAO {

JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCInfrastructureTypeDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public InfrastructureType addAInfrastructureType(InfrastructureType aInfrastructureType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InfrastructureType> getAllInfrastructureTypes() {
		List<InfrastructureType> infrastructureList = new ArrayList<InfrastructureType>();
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT * FROM infrastructure_type ");
		while(results.next()) {
			infrastructureList.add(mapRowToInfrastructureType(results));
		}
		return infrastructureList;
	}

	@Override
	public void deleteInfrastructureType(String aInfrastructureTypeId) {
		// TODO Auto-generated method stub
		
	}
	
//	-------------------------------	HELPER METHODS	---------------------------------

	private InfrastructureType mapRowToInfrastructureType(SqlRowSet results) {
		InfrastructureType theinfrastructureItem = new InfrastructureType();

		theinfrastructureItem.setInfrastructureTypeId(results.getInt("infrastructure_type_id"));
		theinfrastructureItem.setType(results.getString("type"));

		return theinfrastructureItem;
	}
	
}
