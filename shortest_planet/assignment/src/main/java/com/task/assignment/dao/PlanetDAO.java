package com.task.assignment.dao;

import java.util.List;

import com.task.assignment.model.DistancePlanet;
import com.task.assignment.model.Planet;

public interface PlanetDAO {

	public Planet save(Planet planet);

	public List<Planet> listOfPlanets();

	public Planet update(Planet planet);
	
	public Planet getPlanetById(int id);
	
	public Planet getPlanetByName(String name);
	
	public void delete(int id);

	public List<DistancePlanet> listOfDistancePlanets();
	
	public List<DistancePlanet> listOfDistancePlanetsBySource(String sourceName);

	public DistancePlanet saveDistancePlanet(DistancePlanet distancePlanet);
	
	public DistancePlanet updateDistancePlanet(DistancePlanet distancePlanet);
	
	public DistancePlanet getDistancePlanetById(int id);
	
	public void deleteDistancePlanet(int id);

	public void deletePlanetDistanceById(int id);
}
