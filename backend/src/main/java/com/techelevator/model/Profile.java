package com.techelevator.model;

public class Profile {
	private String notificationPreference;
	private String firstName;
	private String lastName;
	private String email;
	private int id;
	private String phoneNumber;
	
	@Override
	public String toString() {
		return "Profile [notificationPreference=" + notificationPreference + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + "]";
	}
	public String getNotificationPreference() {
		return notificationPreference;
	}
	public void setNotificationPreference(String notificationPreference) {
		this.notificationPreference = notificationPreference;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	

}
