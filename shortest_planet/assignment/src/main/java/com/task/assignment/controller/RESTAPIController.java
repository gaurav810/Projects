package com.task.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.assignment.dao.PlanetDAO;
import com.task.assignment.dto.DistancPlanetsDTO;
import com.task.assignment.dto.GraphDTO;
import com.task.assignment.model.DistancePlanet;
import com.task.assignment.model.Planet;
import com.task.assignment.service.ShortestPathService;

@RestController
public class RESTAPIController {
	
	@Autowired
	private PlanetDAO planetDAO;
	
	@Autowired
	private ShortestPathService shortestPathService; 

	@RequestMapping(value = "/planet", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Planet addPlanet(@RequestBody Planet planet) {

		return planetDAO.save(planet);
	}
	
	@RequestMapping(value = "/planet", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Planet> listOfPlanet() {
		
		List<Planet> planets = planetDAO.listOfPlanets();
		return planets;
	}
	
	@RequestMapping(value = "/planet", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public Planet updatePlanet(@RequestBody Planet planet) {
		
		return planetDAO.update(planet);
	}
	
	@RequestMapping(value = "/planet/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void deletePlanet(@PathVariable("id") int id) {
		
		planetDAO.delete(id);
		planetDAO.deletePlanetDistanceById(id);
	}

	@RequestMapping(value = "/planet-distance", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public DistancePlanet addPlanetDistance(@RequestBody DistancPlanetsDTO distancPlanetsDTO) {

		Planet sourcePlante = planetDAO.getPlanetByName(distancPlanetsDTO.getSourcePlanet());
		Planet destinationPlanet = planetDAO.getPlanetByName(distancPlanetsDTO.getDestinationPlanet());

		if(sourcePlante != null && destinationPlanet != null) {
			
			DistancePlanet distancePlanet = new DistancePlanet();
			distancePlanet.setDestinationPlanet(destinationPlanet);
			distancePlanet.setSourcePlanet(sourcePlante);
			distancePlanet.setDistance(distancPlanetsDTO.getDistance());
			
			distancePlanet = planetDAO.saveDistancePlanet(distancePlanet);
			
			return distancePlanet;
		} else {
			return null;
		}
	}	
	
	@RequestMapping(value = "/planet-distance", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public DistancePlanet updatePlanetDistance(@RequestBody DistancPlanetsDTO distancPlanetsDTO) {

			
		DistancePlanet distancePlanet = planetDAO.getDistancePlanetById(distancPlanetsDTO.getId());
		distancePlanet.setDistance(distancPlanetsDTO.getDistance());
		distancePlanet = planetDAO.updateDistancePlanet(distancePlanet);
		
		return distancePlanet;
	}
	
	@RequestMapping(value = "/planet-distance/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void deletePlanetDistance(@PathVariable("id") int id) {
		
		planetDAO.deleteDistancePlanet(id);
	}
	
	@RequestMapping(value = "/list-of-planet-distance", method = RequestMethod.GET)
	public List<DistancePlanet> listOfDistance() {
	
		List<DistancePlanet> distancePlanets = planetDAO.listOfDistancePlanets();
		return distancePlanets;
	}
	
	@RequestMapping(value = "/shortest-path-from-source", method = RequestMethod.GET)
	public List<GraphDTO> shortestPathFromSource() {
	
		List<Planet> planets = planetDAO.listOfPlanets();
		
		List<GraphDTO> graphDTOs =  shortestPathService.shortestPathDetails(planets.get(0).getName());
		return graphDTOs;
	}
	
}