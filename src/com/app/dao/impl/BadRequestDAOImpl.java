package com.app.dao.impl;

import org.springframework.stereotype.Repository;

import com.app.dao.TaskDAO;

/**
 * @author SHUBHAM
 *
 */
@Repository("badRequestDAOImpl")
public class BadRequestDAOImpl implements TaskDAO {
		
	public StringBuilder getTask(String taskType) {
		
		StringBuilder json = new StringBuilder();
			
		json.append("'Request Status','Bad Request'");
		  		   
		  return json;
	}
}