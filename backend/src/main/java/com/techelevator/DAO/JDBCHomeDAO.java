package com.techelevator.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.interfaces.HomeDAO;
import com.techelevator.model.Home;

@Component
public class JDBCHomeDAO implements HomeDAO {

	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCHomeDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Home addHome(Home aHome) {
		String sql = "INSERT INTO Home (user_id, street_address, address_two, city, state, zip, occupants) VALUES(?, ?, ?, ?, ?, ?, ?);"; 
			jdbcTemplate.update(sql,
					aHome.getUserId(),
					aHome.getAddress(),
					aHome.getAddressLineTwo(),
					aHome.getCity(),
					aHome.getState(),
					aHome.getZip(),
					aHome.getOccupants());	
		
		return aHome;
	}

	@Override
	public Home getHomeById(String aHomeId) {
		
		return mapRowToHome(jdbcTemplate.queryForRowSet("select * from home where home_id = ? ; ", aHomeId));
	}

	@Override
	public List<Home> getHomesByOwner(int userId) {
		List<Home> homeList = new ArrayList<Home>();
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT * FROM home WHERE user_id = ?", userId );
				while(results.next()) {
					homeList.add(mapRowToHome(results));
				}
				return homeList;
			}

	@Override
	public List<Home> getAllHomes() {
		List<Home> homeList = new ArrayList<Home>();
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT * FROM home");

				while(results.next()) {
					homeList.add(mapRowToHome(results));
				}
				return homeList;
			}

	@Override
	public void changeHomeData(Home updatedHome) {
		String sql = "UPDATE home SET user_id = ?, street_address = ?, address_two = ?, city = ? , state = ?, zip = ? , occupants = ? WHERE home_id = ?;";
		jdbcTemplate.update(sql,
				updatedHome.getUserId(),
				updatedHome.getAddress(),
				updatedHome.getAddressLineTwo(),
				updatedHome.getCity(),
				updatedHome.getState(),
				updatedHome.getZip(),
				updatedHome.getOccupants());	
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteHome(String aHomeId) {
		jdbcTemplate.update("DELETE * FROM home WHERE home_id = "+aHomeId);
		
	}
	
	private Home mapRowToHome(SqlRowSet results) {
		Home theHome = new Home();
		
		theHome.setHomeId(results.getInt("home_id"));
		theHome.setUserId(results.getInt("user_id"));
		theHome.setAddress(results.getString("street_address"));
		theHome.setAddressLineTwo(results.getString("address_two"));
		theHome.setCity(results.getString("city"));
		theHome.setState(results.getString("state"));
		theHome.setZip(results.getInt("zip"));
		theHome.setOccupants(results.getInt("occupants"));
		
		return theHome;
	}

}
