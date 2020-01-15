package com.techelevator.DAO;

import java.time.ZoneId;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.interfaces.MechanicalAssetDAO;
import com.techelevator.model.MechanicalAsset;

@Component
public class JDBCMechanicalAssetDAO implements MechanicalAssetDAO {
	JdbcTemplate jdbcTemplate;
	@Autowired
	public JDBCMechanicalAssetDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public MechanicalAsset addAnAsset(MechanicalAsset aMechanicalAsset) {
		
		String sql = "INSERT INTO mechanical_asset(home_id, type, age, model_number, maintenance_duration) VALUES ( ? , ? , ? , ? , ?)";
		jdbcTemplate.update(sql, 
							aMechanicalAsset.getHomeId(),
							aMechanicalAsset.getType(),
							aMechanicalAsset.getAge(),
							aMechanicalAsset.getModelNumber(),
							aMechanicalAsset.getMaintenanceDuration());
		return aMechanicalAsset;
	}

	@Override
	public MechanicalAsset getMechanicalAssetById(String aMechanicalAssetId) {
		return mapRowToAsset(jdbcTemplate.queryForRowSet("SELECT * FROM mechanical_asset WHERE asset_id = ?", aMechanicalAssetId));
	}

	@Override
	public List<MechanicalAsset> getAllMechanicalAssetsByType(String aMechanicalAssetType, int homeId) {
		List<MechanicalAsset> assetList = new ArrayList<MechanicalAsset>();
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT * FROM mechanical_asset WHERE home_id =? AND type = ?",homeId,aMechanicalAssetType);
		while(results.next()) {
			assetList.add(mapRowToAsset(results));
		}
		return assetList;
	}

	@Override
	public List<MechanicalAsset> getAllMechanicalAssets(int homeId) {
		List<MechanicalAsset> assetList = new ArrayList<MechanicalAsset>();
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT * FROM mechanical_asset WHERE home_id =?", homeId);
		while(results.next()) {
			assetList.add(mapRowToAsset(results));
		}
		return assetList;
	}

	@Override
	public void changeMechanicalAssetData(MechanicalAsset updatedMechanicalAsset) {
		jdbcTemplate.update("UPDATE mechanical_asset SET asset_id = "+updatedMechanicalAsset.getId()+", home_id= "
	        +updatedMechanicalAsset.getHomeId()+", type = "+updatedMechanicalAsset.getType()+", age="+updatedMechanicalAsset.getAge()+
	        ", model_number = "+updatedMechanicalAsset.getModelNumber()+", maintenance_duration = "+updatedMechanicalAsset.getMaintenanceDuration());	
	}

	@Override
	public void deleteMechanicalAsset(String aMechanicalAssetId) {
		jdbcTemplate.update("DELETE * FROM mechanical_asset WHERE asset_id = "+aMechanicalAssetId);
	}
	
	private int mapDuration(SqlRowSet rowSet) {
		return (rowSet.getInt("maintenance_duration"));
	}
	
	@Override
	public List<Integer> getMaintenanceDurationsForUser(int userId) {
		List<Integer> maintenanceDurations = new ArrayList<Integer>();
		String assetSql = "SELECT maintenance_duration FROM mechanical_asset join home "+
		" ON home.home_id = mechasnical_asset.home_id join user_account ON home.user_id = user_account.user_id WHERE user_account.user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(assetSql, userId);
		while(results.next()) {
			maintenanceDurations.add(mapDuration(results));
		}
		
		return maintenanceDurations;
	}
	
	private MechanicalAsset mapRowToAsset(SqlRowSet rowSet) {
		MechanicalAsset mechAsset = new MechanicalAsset();
		mechAsset.setId(rowSet.getInt("id"));
		mechAsset.setAge(rowSet.getDate("age").toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate());
		mechAsset.setHomeId(rowSet.getInt("homeId"));
		mechAsset.setMaintenanceDuration(rowSet.getInt("maintenance_duration"));
		mechAsset.setModelNumber(rowSet.getInt("model_number"));
		mechAsset.setType(rowSet.getString("type"));
		return mechAsset;
	}
	
	

}
