package com.app.controller;

/**
 * @author SHUBHAM
 *
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.app.dao.TaskDAO;
import com.app.dao.AddNewTaskDAO;
import com.app.factory.TaskFactory;


@Controller
public class TaskController {
	
	@Autowired
	TaskFactory taskFactory;

	@Autowired
	AddNewTaskDAO newTaskDao;
	
	
	
   /** This method is used to fetch the tasks on the basis of parameter passed
 * @param taskType
 * @return
 */
@RequestMapping(value = "/ShowTask/{taskType}", method = RequestMethod.POST, produces = "application/json")
   public  @ResponseBody String  getTaskDetails(@PathVariable("taskType") String taskType) {
	      	   
	   TaskDAO taskDao = taskFactory.getTaskDetails(taskType.toUpperCase());
	      
	   return taskDao.getTask(taskType.toUpperCase()).toString();
	   
   }
   
   /** This method is used to create a new task in the db
 * @param taskDescription
 */
@RequestMapping(value = "/AddNewTask", method = RequestMethod.POST, produces = "application/json")
   public  @ResponseBody void  addItems(@RequestBody String taskDescription) {
	   
	   newTaskDao.createNewTask(taskDescription);
	   
	   
   }
   
   /** This method is used to delete the task
 * @param taskID
 */
@RequestMapping(value = "/DeleteTask", method = RequestMethod.POST, produces = "application/json")
   public  @ResponseBody void  deleteTask(@RequestBody String taskID) {
	   
	   newTaskDao.deleteTask(taskID);
	   
	   
   }
   
   /** This method is used to update the status of the task
 * @param data
 */
@RequestMapping(value = "/UpdateTask", method = RequestMethod.POST, produces = "application/json")
   public  @ResponseBody void  updateTask(@RequestBody String data) {
	   	   	   
	   newTaskDao.updateTask(data);
	   
	   
   }

} 