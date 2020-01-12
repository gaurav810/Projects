package com.dailyupdate.dto;

public class TaskDetailsDTO {

	private int id;
	private int userId;
	private String clientTask;
	private String learnNewThings;
	private String achievement;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
}
