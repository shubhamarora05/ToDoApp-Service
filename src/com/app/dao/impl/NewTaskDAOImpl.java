package com.app.dao.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.dao.AddNewTaskDAO;

/** This file makes connection with db and retrieves the data.
 * @author SHUBHAM
 *
 */
@Repository("newTaskDao")
public class NewTaskDAOImpl implements AddNewTaskDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplateObject;
    }
	
	public void createNewTask( String taskDescription) {
	      String SQL = "INSERT INTO TABLE_ALL_TASK (TASK_ID, TASK_TYPE, TASK_DESCRIPTION) VALUES (SEQ_ALL_TASK.NEXTVAL,?,?)";
	      
	      jdbcTemplateObject.update( SQL, "P", taskDescription);
	      
	}
	
	public void deleteTask(String taskID) {
	      String SQL = "DELETE FROM TABLE_ALL_TASK WHERE TASK_ID=?";
	      
	      jdbcTemplateObject.update( SQL, taskID);
	      
	}
	
	public void updateTask(String data){
		  
		  JSONObject jsonObject = new JSONObject(data);
		  
		  String taskType = jsonObject.getString("taskType");
		  String taskID = jsonObject.getString("taskID");
		  
		  if(taskType.equalsIgnoreCase("C"))
			  taskType = "P";
		  else
			  taskType = "C";
		
		  String SQL = "UPDATE TABLE_ALL_TASK SET TASK_TYPE=? WHERE TASK_ID=?";
	     
	      jdbcTemplateObject.update( SQL, taskType, taskID);
	      
	}
}