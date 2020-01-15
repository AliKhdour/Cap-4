package com.techelevator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 1227422333792346806L;
	
	private String username;
	
	public UserNotFoundException(String username, String message) {
		super(message);
		setUsername(username);
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

}