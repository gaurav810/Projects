package com.dailyupdate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dailyupdate.bean.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public User getUserDetailsByIp(String userSystemIP) {
		
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(User.class);
		crit.add(Restrictions.eq("ipAddress", userSystemIP));
		List<User> results = crit.list();
		
		return (results == null || results.isEmpty()) ? null : results.get(0);
	}

	@Override
	@Transactional
	public List<User> listOfUserByRole(String role) {
		
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(User.class);
		crit.add(Restrictions.eq("role", role));
		List<User> results = crit.list();
		return results;
	}

}
