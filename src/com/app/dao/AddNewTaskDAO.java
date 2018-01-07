package com.app.dao;

public interface AddNewTaskDAO {

	public void createNewTask(String taskDescription);

	public void deleteTask(String taskID);

	public void updateTask(String data);    
       
}
