package com.dailyupdate.bean;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="TASK_DETAILS")
public class TaskDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy")
	@Column(name="TASK_DATE")
	private Date taskDate;
	
	@Column(name="CLIENT_TASK", length = 500)
	private String clientTask;
	
	@Column(name="LEARN_NEW_THINGS", length = 500)
	private String learnNewThings;
	
	@Column(name="ACHIEVEMENT", length = 500)
	private String achievement;
	
	public int getId() {
		return id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getTaskDate() {
		return taskDate;
	}
	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getTaskDateString() {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		return dateFormat.format(this.taskDate);
	}
}
