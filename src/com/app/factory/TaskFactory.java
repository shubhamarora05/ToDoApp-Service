package com.app.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.app.dao.TaskDAO;
import com.app.dao.impl.AllTaskDAOImpl;
import com.app.dao.impl.BadRequestDAOImpl;
import com.app.dao.impl.CompletedTaskDAOImpl;
import com.app.dao.impl.PendingTaskDAOImpl;

/**
 * @author SHUBHAM
 *
 */
@Repository("taskFactory")
public class TaskFactory{  
    	   
	@Autowired
	AllTaskDAOImpl allTaskDAOImpl;
	
	@Autowired
	CompletedTaskDAOImpl completedTaskDAOImpl;
	
	@Autowired
	PendingTaskDAOImpl pendingTaskDAOImpl;
	
	@Autowired
	BadRequestDAOImpl badRequestDAOImpl;
	
	/** This method retrieves the dao on the basis of the task
	 * @param taskName
	 * @return
	 */
	public TaskDAO getTaskDetails(String taskName){  
		
		switch (taskName) {
		 
		   case "A": return allTaskDAOImpl;
		   case "C": return completedTaskDAOImpl;  
		   case "P": return pendingTaskDAOImpl;  
		   
		   default: return badRequestDAOImpl;    
		}
	        
		
	}  
}