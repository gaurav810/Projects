package com.dailyupdate.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dailyupdate.bean.TaskDetails;
import com.dailyupdate.bean.UserTaskTransformer;
import com.dailyupdate.dto.TaskDetailsDTO;
import com.dailyupdate.utils.DateUtils;

@Repository
public class TaskDetailsDAOImpl implements TaskDetailsDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public TaskDetails save(TaskDetails details) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(details);
		return details;
	}

	@Override
	@Transactional
	public List<TaskDetails> listOfTask(String userSystemIP) {
		
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(TaskDetails.class);
		crit.createAlias("user", "user");
		crit.add(Restrictions.eq("user.ipAddress", userSystemIP));
		crit.addOrder(Order.desc("taskDate"));
		crit.setMaxResults(10);
		
		List<TaskDetails> list = crit.list();
		return list;
	}
	
	@Override
	@Transactional
	public TaskDetailsDTO getCurrentDateTaskDetailsByUser(String userSystemIP, Date now) {
		
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(TaskDetails.class);
		crit.createAlias("user", "user");
		crit.add(Restrictions.eq("user.ipAddress", userSystemIP));
		crit.add(Restrictions.ge("taskDate", DateUtils.getFromDateTime(now)));
		crit.add(Restrictions.le("taskDate", DateUtils.getToDateTime(now)));
		
		ProjectionList projection = Projections.projectionList();
		projection.add(Projections.property("id"), "id");
		projection.add(Projections.property("user.id"), "userId");	
		projection.add(Projections.property("clientTask"), "clientTask");
		projection.add(Projections.property("learnNewThings"), "learnNewThings");
		projection.add(Projections.property("achievement"), "achievement");
		
		crit.setProjection(projection);		
		crit.setResultTransformer(Transformers.aliasToBean(TaskDetailsDTO.class));
		
		List<TaskDetailsDTO> results = crit.list();
		
		return (results == null || results.isEmpty()) ? null : results.get(0);
	}

	@Override
	@Transactional
	public List<UserTaskTransformer> listOfTaskByDate(Date now) {
		
		String sql = " select U.ID as userId, U.NAME as userName, temp.taskId, temp.clientTask, temp.learnNewThings, temp.achievement, temp.taskDate from "
			  + "(select T.ID as taskId, T.USER_ID as UID, T.CLIENT_TASK as clientTask, T.LEARN_NEW_THINGS as learnNewThings, T.ACHIEVEMENT as achievement, T.TASK_DATE as taskDate from task_details T "
			  + " where T.TASK_DATE BETWEEN '"+ DateUtils.getFormattedFromDateTime(now)+"' AND '"+ DateUtils.getFormattedToDateTime(now)+"') temp " 
		      + " right join `user` U on U.ID = temp.UID where U.ROLE = 'User';";
		
		List<UserTaskTransformer> userTaskTransformer = sessionFactory.getCurrentSession().createSQLQuery(sql)
				  .addScalar("userId", new IntegerType())
				  .addScalar("userName", new StringType())
				  .addScalar("taskId", new IntegerType())
				  .addScalar("clientTask", new StringType())
				  .addScalar("learnNewThings", new StringType())
				  .addScalar("achievement", new StringType())
				  .addScalar("taskDate", new StringType())
				  .setResultTransformer(Transformers.aliasToBean(UserTaskTransformer.class))
				  .list();
		
		return userTaskTransformer;
	}
	
	@Override
	@Transactional
	public List<TaskDetails> listOfTaskByUserId(int userId, Date fromDate, Date toDate) {
		
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(TaskDetails.class);
		crit.createAlias("user", "user");
		crit.add(Restrictions.eq("user.id", userId));
		crit.add(Restrictions.ge("taskDate", DateUtils.getFromDateTime(fromDate)));
		crit.add(Restrictions.le("taskDate", DateUtils.getToDateTime(toDate)));
		crit.addOrder(Order.desc("taskDate"));
		
		List<TaskDetails> list = crit.list();
		return list;
	}
}
