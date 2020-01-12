package com.dailyupdate.dao;

import java.util.List;

import com.dailyupdate.bean.User;

public interface UserDAO {

	User getUserDetailsByIp(String userSystemIP);
	
	List<User> listOfUserByRole(String role);
}
