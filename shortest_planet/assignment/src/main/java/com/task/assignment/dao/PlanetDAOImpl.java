package com.task.assignment.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.task.assignment.model.DistancePlanet;
import com.task.assignment.model.Planet;

@Repository
public class PlanetDAOImpl implements PlanetDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Planet save(Planet planet) {
		
		sessionFactory.getCurrentSession().save(planet);
		return planet;
	}

	@Override
	@Transactional
	public List<Planet> listOfPlanets() {
		
		List<Planet> list = sessionFactory.getCurrentSession().createCriteria(Planet.class).addOrder(Order.asc("id")).list();
		return list;
	}

	@Override
	@Transactional
	public Planet update(Planet planet) {
	
		sessionFactory.getCurrentSession().update(planet);
		return planet;
	}

	@Override
	@Transactional
	public Planet getPlanetById(int id) {
		
		Planet planet = sessionFactory.getCurrentSession().get(Planet.class, id);
		return planet;
	}
	
	@Override
	@Transactional
	public Planet getPlanetByName(String name) {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Planet.class);
		criteria.add(Restrictions.eq("name", name));
		
		return criteria.list().size() > 0 ? (Planet) criteria.list().get(0) : null;
	}

	@Override
	@Transactional
	public void delete(int id) {
		
		Planet planet = this.getPlanetById(id);
		
		sessionFactory.getCurrentSession().delete(planet);
	}

	@Override
	@Transactional
	public List<DistancePlanet> listOfDistancePlanets() {
		
		List<DistancePlanet> list = sessionFactory.getCurrentSession().createCriteria(DistancePlanet.class).list();
		return list;
	}

	@Override
	@Transactional
	public List<DistancePlanet> listOfDistancePlanetsBySource(String sourceName) {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DistancePlanet.class, "distancePlanet");
		criteria.createAlias("distancePlanet.sourcePlanet", "sourcePlanet");
		criteria.add(Restrictions.eq("sourcePlanet.name", sourceName));
		
		return criteria.list();
	}

	@Override
	@Transactional
	public DistancePlanet saveDistancePlanet(DistancePlanet distancePlanet) {
		
		sessionFactory.getCurrentSession().save(distancePlanet);
		return distancePlanet;
	}

	@Override
	@Transactional
	public DistancePlanet getDistancePlanetById(int id) {
		
		DistancePlanet distancePlanet = sessionFactory.getCurrentSession().get(DistancePlanet.class, id);
		return distancePlanet;
	}

	@Override
	@Transactional
	public DistancePlanet updateDistancePlanet(DistancePlanet distancePlanet) {

		sessionFactory.getCurrentSession().update(distancePlanet);
		return distancePlanet;
	}

	@Override
	@Transactional
	public void deleteDistancePlanet(int id) {
		
		DistancePlanet distancePlanet = this.getDistancePlanetById(id);
		sessionFactory.getCurrentSession().delete(distancePlanet);
	}

	@Override
	@Transactional
	public void deletePlanetDistanceById(int id) {
	
		SQLQuery  query = sessionFactory.getCurrentSession().createSQLQuery("delete from distance_planet where DESTINATIOIN = :id or SOURCE = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}
}
