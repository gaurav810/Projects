package com.dailyupdate.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserTaskTransformer {

	private Integer userId;
	private String userName;
	private Integer taskId;
	private String taskDate;
	private String clientTask;
	private String learnNewThings;
	private String achievement;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public String getClientTask() {
		return clientTask;
	}
	public void setClientTask(String clientTask) {
		this.clientTask = clientTask;
	}
	public String getLearnNewThings() {
		return learnNewThings;
	}
	public void setLearnNewThings(String learnNewThings) {
		this.learnNewThings = learnNewThings;
	}
	public String getAchievement() {
		return achievement;
	}
	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}
	public String getTaskDate() {
		return taskDate;
	}
	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
	}
	
	final SimpleDateFormat dateFormatString = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	final SimpleDateFormat dateFormatDate = new SimpleDateFormat("dd-MMM-yyyy");
	
	public String getTaskDateString() throws ParseException {
		
		if(this.taskDate != null && !(this.taskDate.isEmpty())) {
			Date date = dateFormatString.parse(this.taskDate);
			return dateFormatDate.format(date);
		} else {
			return "";
		}
		
	}
	
}
