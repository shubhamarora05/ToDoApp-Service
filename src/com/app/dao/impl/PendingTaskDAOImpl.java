package com.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.app.dao.TaskDAO;

/** This file makes connection with db and retrieves the data.
 * @author SHUBHAM
 *
 */
@Repository("pendingTaskDAOImpl")
public class PendingTaskDAOImpl implements TaskDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplateObject;
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public StringBuilder getTask(String taskType) {
		
		StringBuilder json = new StringBuilder();
		String SQL = "SELECT TASK_ID, TASK_DESCRIPTION, TASK_TYPE FROM TABLE_ALL_TASK WHERE TASK_TYPE=?";
		try {    
		   JSONArray jsonArr = (JSONArray)jdbcTemplateObject.query(SQL, new Object[] {taskType},new ResultSetExtractor() {
				   public Object extractData(ResultSet rs) throws SQLException {
					   
					   JSONArray jsonArr = new JSONArray();
					   					   				   
					   while (rs.next()) 
					   {
						   JSONObject obj = new JSONObject();
						   obj.put("taskID", String.valueOf(rs.getInt(1)));
						   obj.put("taskDescription", rs.getString(2));
						   obj.put("taskType", rs.getString(3));
						   
						   jsonArr.put(obj);
					   }
					   return jsonArr;
				   };
		  });
		
		  json.append(jsonArr);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage()); 
			JSONArray jsonArr = new JSONArray();
			JSONObject obj = new JSONObject();
			obj.put("ErrorDescription", e.getMessage());
			jsonArr.put(obj);
			json.append(jsonArr);
		}
		  return json;
	}
}