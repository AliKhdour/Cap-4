package com.techelevator.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.interfaces.MechanicalAssetTypeDAO;
import com.techelevator.model.MechanicalAssetType;

@Component
public class JDBCMechanicalAssetTypeDAO implements MechanicalAssetTypeDAO {

	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCMechanicalAssetTypeDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public MechanicalAssetType addAMechanicalAssetType(MechanicalAssetType aMechanicalAssetType) {
		// TODO Auto-generated method stub		
		return null;
	}

	@Override
	public List<MechanicalAssetType> getAllMechanicalAssetTypes() {
		List<MechanicalAssetType> mechAssetList = new ArrayList<MechanicalAssetType>();
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT * FROM mechanical_asset_type ");
		while(results.next()) {
			mechAssetList.add(mapRowToMechanicalAssetType(results));
		}
		return mechAssetList;
	}

	@Override
	public void deleteMechanicalAssetType(String aMechanicalAssetTypeId) {
		// TODO Auto-generated method stub
	}
	
//	-------------------------------	HELPER METHODS	---------------------------------

	private MechanicalAssetType mapRowToMechanicalAssetType(SqlRowSet results) {
		MechanicalAssetType theMechAsset = new MechanicalAssetType();

		theMechAsset.setMechanicalAssetTypeId(results.getInt("mechanical_asset_type_id"));
		theMechAsset.setType(results.getString("type"));

		return theMechAsset;
	}

}
