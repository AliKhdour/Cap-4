package com.techelevator.model;

public class Home {
	private int homeId;
	private String address;
	private String addressLineTwo;
	private String state;
	private String city;
	private int zip;
	private int userId;
	private int occupants;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressLineTwo() {
		return addressLineTwo;
	}

	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
	}

	public int getHomeId() {
		return homeId;
	}
	
	public void setHomeId(int homeId) {
		this.homeId = homeId;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public int getZip() {
		return zip;
	}
	
	public void setZip(int zip) {
		this.zip = zip;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getOccupants() {
		return occupants;
	}
	
	public void setOccupants(int occupants) {
		this.occupants = occupants;
	}

	
	
	
}
