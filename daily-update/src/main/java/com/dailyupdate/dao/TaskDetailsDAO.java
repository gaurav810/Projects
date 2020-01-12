package com.dailyupdate.dao;

import java.util.Date;
import java.util.List;

import com.dailyupdate.bean.TaskDetails;
import com.dailyupdate.bean.UserTaskTransformer;
import com.dailyupdate.dto.TaskDetailsDTO;

public interface TaskDetailsDAO {

	public TaskDetails save(TaskDetails details);

	public List<TaskDetails> listOfTask(String userSystemIP);
	
	public TaskDetailsDTO getCurrentDateTaskDetailsByUser(String userSystemIP, Date now);

	public List<UserTaskTransformer> listOfTaskByDate(Date now);

	public List<TaskDetails> listOfTaskByUserId(int userId, Date fromDate, Date toDate);
}
