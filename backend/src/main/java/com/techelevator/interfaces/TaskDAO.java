package com.techelevator.interfaces;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techelevator.model.Task;

@Component
public interface TaskDAO {

	public List<Task> getAllTasks();
	public List<Task> getTasksByType(String type);
	
	
}
