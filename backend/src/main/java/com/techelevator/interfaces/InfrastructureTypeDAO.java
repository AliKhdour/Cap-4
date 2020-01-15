package com.techelevator.interfaces;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techelevator.model.InfrastructureType;

public interface InfrastructureTypeDAO {
	//CRUD - Create, Read, Update, Delete
	
	// Create
	public InfrastructureType addAInfrastructureType(InfrastructureType aInfrastructureType);

	// Read
	public List<InfrastructureType> getAllInfrastructureTypes();

	
	// Delete
	public void deleteInfrastructureType(String aInfrastructureTypeId);
	
}
